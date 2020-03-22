package com.wcq.tang.bean;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/12 7:39
 */
public class CixinTable {
    private Map map;

    public String[] getEn() {
        return en;
    }

    public String[] getCh() {
        return ch;
    }

    private String[] en;
    private String[] ch;
    public CixinTable(){
        map = new LinkedHashMap();
        String[] en={
          "a","ad","ag","al","an", "b","begin","bg","bl", "c","cc",
          "d","dg","dl","e","end","f","g","gb","gbc","gc","gg","gi","gm","gp","h","i","j","k","l","m",
                "mg","Mg","mq","n","nb","nba","nbc","nbp","nf","ng","nh","nhd","nhm","ni","nic","nis",
                "nit","nl","nm","nmc","nn","nnd","nnt","nr","nr1","nr2","nrf","nrj","ns","nsf","nt","ntc",
                "ntcb","ntcf","ntch","nth","nto","nts","ntu","nx","nz","o","p","pba","pbei","q","qg","qt",
                "qv","r","rg","Rg","rr","ry","rys","ryt","ryv","rz","rzs","rzt","rzv","s","t","tg","u","ud",
                "ude1","ude2","ude3","udeng","udh","ug","uguo","uj","ul","ule","ulian","uls","usuo","uv","uyy",
                "uz","uzhe","uzhi","v","vd","vf","vg","vi","vl","vn","vshi","vx","vyou","w","wb","wd","wf","wh",
                "wj","wky","wkz","wm","wn","wp","ws","wt","ww","wyy","wyz","x","xx","y","yg","z","zg"
        };
        String[] ch={
                "形容词","副形词","形容词性语素","形容词性惯用语","名形词","区别词","仅用于始##始",
                "区别语素","区别词性惯用语","连词","并列连词","副词","辄,俱,复之类的副词","连语","叹词",
                "仅用于终##终","方位词","学术词汇","生物相关词汇","生物类别","化学相关词汇","地理地质相关词汇",
                "计算机相关词汇","数学相关词汇","物理相关词汇","前缀","成语","简称略语","后缀","习惯用语","数词",
                "数语素","甲乙丙丁之类的数词","数量词","名词","生物名","动物名","动物纲目","植物名","食品",
                "名词性语素","医药疾病等健康相关名词","疾病","药品","机构相关（不是独立机构名）","下属机构",
                "机构后缀","教育相关机构","名词性惯用语","物品名","化学品名","工作相关名词","职业","职务职称",
                "人名","复姓","蒙古姓名","音译人名","日语人名","地名","音译地名","机构团体名","公司名","银行",
                "工厂","酒店宾馆","医院","政府机构","中小学","大学","字母专名","其他专名","拟声词","介词","介词'把'",
                "介词'被'","量词","量词语素","时量词","动量词","代词","代词性语素","古汉语代词性语素","人称代词",
                "疑问代词","处所疑问代词","时间疑问代词","谓词性疑问代词","指示代词","处所指示代词","时间指示代词",
                "谓词性指示代词","处所词","时间词","时间词性语素","助词","助词","的 底","地","得","等 等等 云云","的话",
                "过","过","助词","连词","了 喽","连","来讲 来说 而言 说来","所","连词","一样 一般 似的 般","着",
                "着","之","动词","副动词","趋向动词","动词性语素","不及物动词","动词性惯用语","名动词","动词'是'",
                "形式动词","动词'有'","标点符号","百分号千分号","逗号","分号","单位符号","句号","右括号","左括号",
                "冒号","顿号","破折号","省略号","叹号","问号","右引号","左引号","字符串","非语素字","语气词(delete yg)",
                "语气语素","状态词","状态词"
        };
        this.en=en;
        this.ch = ch;
        for(int i = 0;i<en.length;i++){
            map.put(en[i],ch[i]);
        }
    }
    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
