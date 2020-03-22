package com.wcq.tang.bean;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/3 16:39
 */
public class Constant {

    public static final String SESSION_USER = "sessionUser";

    public static final Integer SUCCESS = 0;
    public static final Integer FAIL = 1;
    public static final String VERCODE = "vercode";

    /**
     * 语料类型
     */
    public static final String DOC ="doc";
    public static final String DOCX ="docx";
    public static final String XLS ="xls";
    public static final String XLSX ="xlsx";
    public static final String PDF ="pdf";
    public static final String TXT ="txt";

    public static final String ON_LINE_USER = "onlineuser";
    public static final String BROWSE_TIMES = "browseTimes";

    /**
     * 操作分类
     */
    public static final Integer OPERATION_UPLOAD = 1;
    public static final Integer OPERATION_CLEAN =2;
    public static final Integer OPERATION_PARTICIPLE = 3;
    public static final Integer OPERATION_OTHER=4;
    public static final Integer OPERATION_THREE = 5;

    public static final Integer OP_KIND_COL=3;
    public static final Integer OP_KIND_PAR=2;
    public static final Integer OP_KIND_CLEAN=1;
}
