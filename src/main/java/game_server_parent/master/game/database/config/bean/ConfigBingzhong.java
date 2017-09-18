package game_server_parent.master.game.database.config.bean;

import game_server_parent.master.orm.annotation.Column;
import game_server_parent.master.orm.annotation.Entity;

@Entity(readOnly=true)
public class ConfigBingzhong {
	/**
	 * 唯一编号
	 */
	@Column
	private int id;
	/**
	 * 兵种
	 */
	@Column
	private String bingzhong;
	/**
	 * 基础生命
	 */
	@Column
	private int shengming_base;
	/**
	 * 基础攻击
	 */
	@Column
	private int gongji_base;
	/**
	 * 基础治疗
	 */
	@Column
	private int zhiliao_base;
	/**
	 * 大类
	 */
	@Column
	private int dalei;
	/**
	 * 预览图
	 */
	@Column
	private int yulantu;
	/**
	 * 配置上限
	 */
	@Column
	private int shangxian;
	/**
	 * 伤害范围
	 */
	@Column
	private float a_fanweishanghai;
	/**
	 * 攻击间隔浮动值
	 */
	@Column
	private float a_gongjifudong;
	/**
	 * 攻击间隔
	 */
	@Column
	private float a_gongjijiange;
	/**
	 * 攻击间隔原始参数
	 */
	@Column
	private float a_gongjijiange_huifuzhi;
	/**
	 * 攻击前摇时间
	 */
	@Column
	private float a_gongjiqianyao;
	/**
	 * 攻击前摇原始参数
	 */
	@Column
	private float a_gongjiqianyao_huifuzhi;
	/**
	 * 贯穿伤害单位上限个数
	 */
	@Column
	private int a_guanchuangesu;
	/**
	 * 减速效果比率
	 */
	@Column
	private float a_jiansu;
	/**
	 * 减速效果持续时间
	 */
	@Column
	private float a_jiansu_shijian;
	/**
	 * 炮击类型精准度
	 */
	@Column
	private float a_jingzhundu;
	/**
	 * 攻击击退距离
	 */
	@Column
	private float a_jitui;
	/**
	 * 击退质量比重
	 */
	@Column
	private int a_jitui_zhiliang;
	/**
	 * 武器类型
	 */
	@Column
	private int a_leixing;
	/**
	 * 射程
	 */
	@Column
	private float a_shecheng;
	/**
	 * 受击判定区域模型(暂无效)
	 */
	@Column
	private String a_shoujipandingmoxing;
	/**
	 * 攻击眩晕持续时间(暂无效)
	 */
	@Column
	private float a_xuanyun;
	/**
	 * 重力抛射力度(暂无效)
	 */
	@Column
	private float a_zhonglipaoshe;
	/**
	 * 子弹弧度比率
	 */
	@Column
	private float a_zidanhudubilv;
	/**
	 * 子弹模型
	 */
	@Column
	private String a_zidanmoxing;
	/**
	 * 子弹速度
	 */
	@Column
	private float a_zidansudu;
	/**
	 * 子弹是制导的吗?(暂无效)
	 */
	@Column
	private boolean a_zidao;
	/**
	 * 治疗值
	 */
	@Column
	private float a_zilaoshuzi;
	/**
	 * 是否属于牧师
	 */
	@Column
	private boolean a_ziliao;
	/**
	 * 技能
	 */
	@Column
	private String c_jineng;
	/**
	 * 子弹爆炸特效
	 */
	@Column
	private String f_zidan_baozha;
	/**
	 * 子弹发射特效
	 */
	@Column
	private String f_zidan_fashe;
	/**
	 * 名字美国英文
	 */
	@Column
	private String n_mingzi_usen;
	/**
	 * 名字中文
	 */
	@Column
	private String n_mingzi_zhcn;
	/**
	 * 单位音频数据
	 */
	@Column
	private String s_shengyin;
	/**
	 * 子弹音效数据
	 */
	@Column
	private String s_zidan_yinxiao;
	/**
	 * 保护套大小
	 */
	@Column
	private float u_baohutao_daxiao;
	/**
	 * 保护套生命
	 */
	@Column
	private float u_baohutao_shengming;
	/**
	 * 动画速率
	 */
	@Column
	private float u_donghuasulv;
	/**
	 * 动画速率原始参数
	 */
	@Column
	private float u_donghuasulv_huifuzhi;
	/**
	 * 击杀奖励
	 */
	@Column
	private float u_jiangli;
	/**
	 * 单位碰撞体参数
	 */
	@Column
	private float u_jiaosetiji_h;
	/**
	 * 单位碰撞体参数
	 */
	@Column
	private float u_jiaosetiji_r;
	/**
	 * 单位碰撞体参数
	 */
	@Column
	private float u_jiaosetiji_y;
	/**
	 * 单位是否激活
	 */
	@Column
	private boolean u_jihuo;
	/**
	 * 单位模型
	 */
	@Column
	private String u_moxing;
	/**
	 * 移动速度
	 */
	@Column
	private float u_yidongsudu;
	/**
	 * 移动速度原始参数
	 */
	@Column
	private float u_yidongsudu_huifuzhi;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBingzhong() {
		return bingzhong;
	}
	public void setBingzhong(String bingzhong) {
		this.bingzhong = bingzhong;
	}
	public int getShengming_base() {
		return shengming_base;
	}
	public void setShengming_base(int shengming_base) {
		this.shengming_base = shengming_base;
	}
	public int getGongji_base() {
		return gongji_base;
	}
	public void setGongji_base(int gongji_base) {
		this.gongji_base = gongji_base;
	}
	public int getZhiliao_base() {
		return zhiliao_base;
	}
	public void setZhiliao_base(int zhiliao_base) {
		this.zhiliao_base = zhiliao_base;
	}
	public int getDalei() {
		return dalei;
	}
	public void setDalei(int dalei) {
		this.dalei = dalei;
	}
	public int getYulantu() {
		return yulantu;
	}
	public void setYulantu(int yulantu) {
		this.yulantu = yulantu;
	}
	public int getShangxian() {
		return shangxian;
	}
	public void setShangxian(int shangxian) {
		this.shangxian = shangxian;
	}
	public float getA_fanweishanghai() {
		return a_fanweishanghai;
	}
	public void setA_fanweishanghai(float a_fanweishanghai) {
		this.a_fanweishanghai = a_fanweishanghai;
	}
	public float getA_gongjifudong() {
		return a_gongjifudong;
	}
	public void setA_gongjifudong(float a_gongjifudong) {
		this.a_gongjifudong = a_gongjifudong;
	}
	public float getA_gongjijiange() {
		return a_gongjijiange;
	}
	public void setA_gongjijiange(float a_gongjijiange) {
		this.a_gongjijiange = a_gongjijiange;
	}
	public float getA_gongjijiange_huifuzhi() {
		return a_gongjijiange_huifuzhi;
	}
	public void setA_gongjijiange_huifuzhi(float a_gongjijiange_huifuzhi) {
		this.a_gongjijiange_huifuzhi = a_gongjijiange_huifuzhi;
	}
	public float getA_gongjiqianyao() {
		return a_gongjiqianyao;
	}
	public void setA_gongjiqianyao(float a_gongjiqianyao) {
		this.a_gongjiqianyao = a_gongjiqianyao;
	}
	public float getA_gongjiqianyao_huifuzhi() {
		return a_gongjiqianyao_huifuzhi;
	}
	public void setA_gongjiqianyao_huifuzhi(float a_gongjiqianyao_huifuzhi) {
		this.a_gongjiqianyao_huifuzhi = a_gongjiqianyao_huifuzhi;
	}
	public int getA_guanchuangesu() {
		return a_guanchuangesu;
	}
	public void setA_guanchuangesu(int a_guanchuangesu) {
		this.a_guanchuangesu = a_guanchuangesu;
	}
	public float getA_jiansu() {
		return a_jiansu;
	}
	public void setA_jiansu(float a_jiansu) {
		this.a_jiansu = a_jiansu;
	}
	public float getA_jiansu_shijian() {
		return a_jiansu_shijian;
	}
	public void setA_jiansu_shijian(float a_jiansu_shijian) {
		this.a_jiansu_shijian = a_jiansu_shijian;
	}
	public float getA_jingzhundu() {
		return a_jingzhundu;
	}
	public void setA_jingzhundu(float a_jingzhundu) {
		this.a_jingzhundu = a_jingzhundu;
	}
	public float getA_jitui() {
		return a_jitui;
	}
	public void setA_jitui(float a_jitui) {
		this.a_jitui = a_jitui;
	}
	public int getA_jitui_zhiliang() {
		return a_jitui_zhiliang;
	}
	public void setA_jitui_zhiliang(int a_jitui_zhiliang) {
		this.a_jitui_zhiliang = a_jitui_zhiliang;
	}
	public int getA_leixing() {
		return a_leixing;
	}
	public void setA_leixing(int a_leixing) {
		this.a_leixing = a_leixing;
	}
	public float getA_shecheng() {
		return a_shecheng;
	}
	public void setA_shecheng(float a_shecheng) {
		this.a_shecheng = a_shecheng;
	}
	public String getA_shoujipandingmoxing() {
		return a_shoujipandingmoxing;
	}
	public void setA_shoujipandingmoxing(String a_shoujipandingmoxing) {
		this.a_shoujipandingmoxing = a_shoujipandingmoxing;
	}
	public float getA_xuanyun() {
		return a_xuanyun;
	}
	public void setA_xuanyun(float a_xuanyun) {
		this.a_xuanyun = a_xuanyun;
	}
	public float getA_zhonglipaoshe() {
		return a_zhonglipaoshe;
	}
	public void setA_zhonglipaoshe(float a_zhonglipaoshe) {
		this.a_zhonglipaoshe = a_zhonglipaoshe;
	}
	public float getA_zidanhudubilv() {
		return a_zidanhudubilv;
	}
	public void setA_zidanhudubilv(float a_zidanhudubilv) {
		this.a_zidanhudubilv = a_zidanhudubilv;
	}
	public String getA_zidanmoxing() {
		return a_zidanmoxing;
	}
	public void setA_zidanmoxing(String a_zidanmoxing) {
		this.a_zidanmoxing = a_zidanmoxing;
	}
	public float getA_zidansudu() {
		return a_zidansudu;
	}
	public void setA_zidansudu(float a_zidansudu) {
		this.a_zidansudu = a_zidansudu;
	}
	public boolean getA_zidao() {
		return a_zidao;
	}
	public void setA_zidao(boolean a_zidao) {
		this.a_zidao = a_zidao;
	}
	public float getA_zilaoshuzi() {
		return a_zilaoshuzi;
	}
	public void setA_zilaoshuzi(float a_zilaoshuzi) {
		this.a_zilaoshuzi = a_zilaoshuzi;
	}
	public boolean getA_ziliao() {
		return a_ziliao;
	}
	public void setA_ziliao(boolean a_ziliao) {
		this.a_ziliao = a_ziliao;
	}
	public String getC_jineng() {
		return c_jineng;
	}
	public void setC_jineng(String c_jineng) {
		this.c_jineng = c_jineng;
	}
	public String getF_zidan_baozha() {
		return f_zidan_baozha;
	}
	public void setF_zidan_baozha(String f_zidan_baozha) {
		this.f_zidan_baozha = f_zidan_baozha;
	}
	public String getF_zidan_fashe() {
		return f_zidan_fashe;
	}
	public void setF_zidan_fashe(String f_zidan_fashe) {
		this.f_zidan_fashe = f_zidan_fashe;
	}
	public String getN_mingzi_usen() {
		return n_mingzi_usen;
	}
	public void setN_mingzi_usen(String n_mingzi_usen) {
		this.n_mingzi_usen = n_mingzi_usen;
	}
	public String getN_mingzi_zhcn() {
		return n_mingzi_zhcn;
	}
	public void setN_mingzi_zhcn(String n_mingzi_zhcn) {
		this.n_mingzi_zhcn = n_mingzi_zhcn;
	}
	public String getS_shengyin() {
		return s_shengyin;
	}
	public void setS_shengyin(String s_shengyin) {
		this.s_shengyin = s_shengyin;
	}
	public String getS_zidan_yinxiao() {
		return s_zidan_yinxiao;
	}
	public void setS_zidan_yinxiao(String s_zidan_yinxiao) {
		this.s_zidan_yinxiao = s_zidan_yinxiao;
	}
	public float getU_baohutao_daxiao() {
		return u_baohutao_daxiao;
	}
	public void setU_baohutao_daxiao(float u_baohutao_daxiao) {
		this.u_baohutao_daxiao = u_baohutao_daxiao;
	}
	public float getU_baohutao_shengming() {
		return u_baohutao_shengming;
	}
	public void setU_baohutao_shengming(float u_baohutao_shengming) {
		this.u_baohutao_shengming = u_baohutao_shengming;
	}
	public float getU_donghuasulv() {
		return u_donghuasulv;
	}
	public void setU_donghuasulv(float u_donghuasulv) {
		this.u_donghuasulv = u_donghuasulv;
	}
	public float getU_donghuasulv_huifuzhi() {
		return u_donghuasulv_huifuzhi;
	}
	public void setU_donghuasulv_huifuzhi(float u_donghuasulv_huifuzhi) {
		this.u_donghuasulv_huifuzhi = u_donghuasulv_huifuzhi;
	}
	public float getU_jiangli() {
		return u_jiangli;
	}
	public void setU_jiangli(float u_jiangli) {
		this.u_jiangli = u_jiangli;
	}
	public float getU_jiaosetiji_h() {
		return u_jiaosetiji_h;
	}
	public void setU_jiaosetiji_h(float u_jiaosetiji_h) {
		this.u_jiaosetiji_h = u_jiaosetiji_h;
	}
	public float getU_jiaosetiji_r() {
		return u_jiaosetiji_r;
	}
	public void setU_jiaosetiji_r(float u_jiaosetiji_r) {
		this.u_jiaosetiji_r = u_jiaosetiji_r;
	}
	public float getU_jiaosetiji_y() {
		return u_jiaosetiji_y;
	}
	public void setU_jiaosetiji_y(float u_jiaosetiji_y) {
		this.u_jiaosetiji_y = u_jiaosetiji_y;
	}
	public boolean getU_jihuo() {
		return u_jihuo;
	}
	public void setU_jihuo(boolean u_jihuo) {
		this.u_jihuo = u_jihuo;
	}
	public String getU_moxing() {
		return u_moxing;
	}
	public void setU_moxing(String u_moxing) {
		this.u_moxing = u_moxing;
	}
	public float getU_yidongsudu() {
		return u_yidongsudu;
	}
	public void setU_yidongsudu(float u_yidongsudu) {
		this.u_yidongsudu = u_yidongsudu;
	}
	public float getU_yidongsudu_huifuzhi() {
		return u_yidongsudu_huifuzhi;
	}
	public void setU_yidongsudu_huifuzhi(float u_yidongsudu_huifuzhi) {
		this.u_yidongsudu_huifuzhi = u_yidongsudu_huifuzhi;
	}
}
