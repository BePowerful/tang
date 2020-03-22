package com.wcq.tang.bean;

import com.wcq.tang.dto.CleanDto;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wcq
 * @date 2019/12/15 21:18
 */
public class Utils {

    /**
     * 获取时间
     * @param toDayIsZero
     * @return
     */
    public static String getOtherDay(Integer toDayIsZero){
        Date d=getDay(toDayIsZero);
        SimpleDateFormat sp=new SimpleDateFormat("yyyy-MM-dd");
        String result =sp.format(d);//获取昨天日期
        return result;
    }

    /**
     * 获取时间
     * @param toDayIsZero
     * @return
     */
    public static Date getDay(Integer toDayIsZero){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,toDayIsZero);
        return cal.getTime();
    }
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    /**
     * 判断是否是邮箱
     * @param emailStr
     * @return
     */
    public static boolean isEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    /**
     * MD5加密
     * @param source    数据源
     * @return  加密字符串
     */
    public static String MD5encode(String source) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        MessageDigest messageDigest = null;

        try {
            // 得到一个信息摘要器
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        byte[] encode = messageDigest.digest(source.getBytes());
        StringBuffer hexString = new StringBuffer();
        // 把每一个byte 做一个与运算 0xff;
        for (byte anEncode : encode) {
            // 与运算
            String hex = Integer.toHexString(0xff & anEncode);// 加盐
            if (hex.length() == 1) {
                hexString.append("0");
            }
            hexString.append(hex);
        }
        // 标准的md5加密后的结果
        return hexString.toString();

    }
    /**
     * 清洗
     * @return
     */
    public static String cleanFunction(CleanDto cleanDto){
        String select = cleanDto.getSelect();
        String math = "off";
        String space = "off";
        if(select.contains("2"))
            space="on";
        if(select.contains("4"))
            math = "on";

        char[] chars = cleanDto.getCleanContent().toCharArray();
        String res = "";
        for (char ch : chars) {
            if(math.equals("on") && space.equals("on")){//去空格和简单运算符
                switch (ch) {
                    case ' ':
                    case '\b':
                    case '\n':
                    case '\r':
                    case '\t':
                    case '\\':
                    case '+':
                    case '-':
                    case '*':
                    case '/':
                    case '^':
                    case '<':
                    case '>':
                    case '&': break;
                    default:{
                        res += String.valueOf(ch);
                        break;
                    }
                }
            }else if (math.equals("on") && space.equals("off")){//去简单运算符不去空格
                switch (ch){
                    case '\n':
                    case '\r':
                    case '\t':
                    case '\\':
                    case '+':
                    case '-':
                    case '*':
                    case '/':
                    case '^':
                    case '<':
                    case '>':
                    case '&': break;
                    default:{
                        res += String.valueOf(ch);
                        break;
                    }
                }
            }else if(math.equals("off") && space.equals("on")){//去空格不去简单运算符
                switch (ch){
                    case ' ':
                    case '\b':
                    case '\n':
                    case '\r':
                    case '\t':
                    case '\\':
                    case '&': break;
                    default:{
                        res += String.valueOf(ch);
                        break;
                    }
                }
            }else{//都不去
                switch (ch){
                    case '\n':
                    case '\r':
                    case '\t':
                    case '\\':
                    case '&':break;
                    default:{
                        res += String.valueOf(ch);
                        break;
                    }
                }
            }
        }
        return res;
    }

    /**
     * 读取文件
     * @param txtFilePath
     * @return
     */
    public static String readTxtFile(String txtFilePath){
        if(txtFilePath !=null){
            File file = new File(txtFilePath);
            if(file.exists()){
                Long size = file.length();
                byte[] fileContent = new byte[size.intValue()];
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    fileInputStream.read(fileContent);
                    fileInputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    return new String(fileContent,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return null;
                }
            }else{
                System.out.println("读取文件发生错误，文件不存在！");
                return null;
            }
        }else{
            System.out.println("读取文件发生错误，传入的文件路径为空！");
            return null;
        }
    }

    /**
     * 更新或创建存储文件
     * @param content
     * @param url
     * @return
     */
    public static boolean uploadFile(String content,String url){
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(url));
            bufferedWriter.write(content);
            bufferedWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }finally {
            if(bufferedWriter!=null){
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 写文件公共代码抽取
     * @param txtPath
     * @param buffer
     * @return
     */
    public static boolean writerTxtFile(String txtPath,String buffer) {
        FileOutputStream fileOutputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        try {
            //打开文件输出流
            fileOutputStream = new FileOutputStream(txtPath);
            //打开文件写
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
            //写文件
            outputStreamWriter.write(buffer);
            //关闭文件写
            outputStreamWriter.close();
            //关闭文件输出流
            fileOutputStream.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (outputStreamWriter != null) {
                try {
                    outputStreamWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 把字符串解析成HTML可以显示的样式
     * @param str
     * @return
     */
    public static String makeStringToHTML(String str){
        char[] chars = str.toCharArray();
        String document = "&nbsp;&nbsp;&nbsp;&nbsp;";
        for (char ch:chars){
            if(ch == '\n'){
                document +="<br/>";
            }else if(ch == '\t'){
                document +="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
            }else{
                document += String.valueOf(ch);
            }
        }
        return document;
    }

    /**
     * 把文档分成句子
     * @param cleanResult
     * @return
     */
    public static String getSentence(String cleanResult) {
        StringBuffer stringBuffer = new StringBuffer();
        for (char ch:cleanResult.toCharArray()) {
            switch (ch){
                case '。':
                case '！':
                case '？':
                case '!':
                case '?':{
                    stringBuffer.append(new char[]{ch,'\n'});
                    break;
                }
                default:{
                    stringBuffer.append(ch);
                    break;
                }
            }
        }
        String res = stringBuffer.toString();
        if(res.lastIndexOf("\n")==res.length()-1){
            res = res.substring(0, res.lastIndexOf('\n'));
        }
        return res;
    }
    public static String dateToString(String pattern, Date date){
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}
