package game_server_parent.master.tools;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Set;

import com.baidu.bjf.remoting.protobuf.ProtobufIDLGenerator;

import game_server_parent.master.net.Message;
import game_server_parent.master.utils.ClassScanner;

/**
 * <p>Filename:ProtoFileGenerator.java</p>
 * <p>Description: 导出.proto文件(供客户端用) </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月18日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class ProtoFileGenerator {
    public static void main(String[] args) {
        String rootPath = "game_server_parent.master.game.rank.message";
        Set<Class<?>> messages = ClassScanner.listAllSubclasses(rootPath, Message.class);
        writeProtoFile(messages);
    }

    private static void writeProtoFile(Set<Class<?>> sourceClazzs){
        for(Class clazz:sourceClazzs){
            String filePath = MessageFormat.format("{0}.proto", new Object[]{clazz.getSimpleName()});
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                String source = ProtobufIDLGenerator.getIDL(clazz);
                String result = "";
                for (String s : source.split("\n")) {
                    if (s.startsWith("package ") || s.startsWith("option ")) {
                        continue;
                    }
                    result += s + "\n";
                }
                writer.write(result);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        System.out.println("completed");
    }

}
