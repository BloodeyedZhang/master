package game_server_parent.master.orm;

import game_server_parent.master.orm.annotation.Column;
import game_server_parent.master.orm.annotation.Entity;
import game_server_parent.master.orm.annotation.Id;
import game_server_parent.master.orm.utils.StringUtils;
import game_server_parent.master.utils.ClassFilter;
import game_server_parent.master.utils.ClassScanner;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>Filename:OrmProcessor.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月28日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public enum OrmProcessor {

    INSTANCE;
    /** entity与对应的ormbridge的映射关系 */
    private Map<Class<?>, OrmBridge> classOrmMapperr = new HashMap<Class<?>, OrmBridge>();

    public void initOrmBridges() {
        Set<Class<?>> entityClazzs = ClassScanner.listClassesWithAnnotation("game_server_parent.master.game", Entity.class);

        for (Class<?> clazz:entityClazzs) {
            OrmBridge bridge = createBridge(clazz);
            this.classOrmMapperr.put(clazz, bridge);
        }
    }

    private OrmBridge createBridge(Class<?> clazz) {
        OrmBridge bridge = new OrmBridge();
        Entity entity = (Entity) clazz.getAnnotation(Entity.class);
        //没有设置tablename,则用entity名首字母小写
        if (entity.table().length() <= 0) {
            bridge.setTableName(StringUtils.firstLetterToLowerCase(clazz.getSimpleName()));
        }else {
            bridge.setTableName(entity.table());
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field field:fields) {
            Column column = field.getAnnotation(Column.class);
            String fieldName = field.getName();
            try{
                if (column == null) {
                    continue;
                }
                Method m = clazz.getMethod("get" + StringUtils.firstLetterToUpperCase(field.getName()));
                bridge.addGetterMethod(fieldName, m);
                Method m2 = clazz.getMethod("set" + StringUtils.firstLetterToUpperCase(field.getName()), field.getType());
                bridge.addSetterMethod(fieldName, m2);
                if (field.getAnnotation(Id.class) != null) {
                    bridge.addUniqueKey(fieldName);
                }
                if (!StringUtils.isEmpty(column.name())) {
                    bridge.addPropertyColumnOverride(fieldName, column.name());
                }
                bridge.addProperty(fieldName);
            }catch(Exception e) {
                throw new OrmConfigExcpetion(e);
            }
            //如果实体没有主键的话，一旦涉及更新，会影响整张表数据，后果是灾难性的
            //但readOnly的表只做查询，没这种限制
            if (!entity.readOnly() && bridge.getQueryProperties().size() <= 0) {
                throw new OrmConfigExcpetion(clazz.getSimpleName() + " entity 没有查询索引主键字段");
            }
        }

        return bridge;
    }

    private Set<Class<?>> listEntityClazzs() {
        return ClassScanner.getClasses("game_server_parent.master.game", 
                new ClassFilter() {
            @Override
            public boolean accept(Class<?> clazz) {
                return clazz.getAnnotation(Entity.class) != null;
            }
        });
    }

    public OrmBridge getOrmBridge(Class<?> clazz) {
        return this.classOrmMapperr.get(clazz);
    }
}
