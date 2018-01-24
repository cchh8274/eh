package com.ycb.util;

/**
 * Created by zhifu
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by baolong on 2015/12/1.
 */
public class IDGeneratorTools {

    public static void main(String[] args) throws Exception {

        /*for (int i = 0; i < 100; i++) {
            String cashierNo = createId();
            Thread.currentThread().sleep(100);
            System.out.println(cashierNo);
        }*/
        String cashierNo = createId();
        System.out.println(cashierNo);
//        System.out.println("00000000000000" + DateUtils.getCurrDateTime() + new Random().nextInt(9999));

    }

    /**
     * 设置基础时间为2014年9月1日
     */
    private static final Date dateStart = new Date(2014 - 1900, 9, 1);

    /**
     * @param @param  userNo
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     * @Title: createFixLengthNumberNo
     * @Description: 生成固定32位长度的序列
     */
    public static String createId() {
        try {
            long longnum = createStandardLong(new Random().nextInt(3), new Random().nextInt(7), new Random().nextInt(15), new Random().nextInt(16383));
            return fillString("" + longnum, '0', 32, false);
        } catch (Exception var1) {
            var1.printStackTrace();
            return "00000000000000" + getCurrDateTime() + new Random().nextInt(9999);
        }

    }


    public static long createStandardLong(int l1center, int l2center, int machine, int number) throws Exception {
        long origin = 0L;
        long d = System.currentTimeMillis();//距离1970年的毫秒数
        d = d - dateStart.getTime();//设置起始时间为2014-9-1
        long pk = setValue(origin, d, 1, 40);//0为正负号，1到40位为毫秒时间，可以储存2^40毫秒，使用34.8年
        pk = setValue(pk, l1center, 41, 42);//设置41到42位为l1center，可储存2^2-1,即0-3以内的数字，四个一级中心
        pk = setValue(pk, l2center, 43, 45);//设置43到45位为l2center，可储存2^3-1,即0-7以内的数字，八个二级中心
        pk = setValue(pk, machine, 46, 49);//设置46到49位为machine，可储存2^4-1,即0-15以内的数字，16台机器
        pk = setValue(pk, number, 50, 63);//设置51到63位为随机数，可储存2^14-1,即0-16383以内的数字，支持1毫秒16383的并发
        return pk;
    }


    /**
     * 掩码制造，从0开始，包含start，包含end，start>=0，end<=63
     *
     * @param start 0<=start<=63
     * @param end   0<=end<=63
     * @return
     */
    private static long createMask(int start, int end) throws Exception {
        if (start < 0 || start > 63) throw new Exception("IDGeneratorTools createMask must: 0<=start<=63 ");
        if (end < 0 || end > 63) throw new Exception("IDGeneratorTools createMask must: 0<=end<=63 ");
        if (end < start) throw new Exception("IDGeneratorTools createMask must: end >= start");
        long result = 0xffffffffffffffffl;
        result = result << (64 - (end + 1 - start));
        result = result >>> start;
        return result;
    }

    /**
     * 给old代表的64位数字中的第start到第end位，设置一个数字为val，如果val超出start和end的长度区间，则舍弃掉超出的内容
     *
     * @param old   原始数据
     * @param val   要设置进去的数据
     * @param start 开始位置，0<=start<=end<=63，闭区间
     * @param end   结束位置，0<=start<=end<=63, 闭区间
     * @return
     * @throws Exception
     */
    public static long setValue(long old, long val, int start, int end) throws Exception {
        if (start < 0 || start > 63) throw new Exception("IDGeneratorTools createMask must: 0<=start<=63 ");
        if (end < 0 || end > 63) throw new Exception("IDGeneratorTools createMask must: 0<=end<=63 ");
        if (end < start) throw new Exception("IDGeneratorTools createMask must: end >= start");
        long result = old;
        long mask = createMask(start, end);
        result = result & (0xffffffffffffffffl ^ mask);//result与掩码的反码进行与操作，将指定位置为0
        long value = val;
        value = value << (64 - (end + 1 - start));        //左移到指定位置
        value = value >>> start;
        value = value & mask;                       //与掩码进行计算
        result = result | value;                    //将原值与新值进行组合
        return result;
    }
    
    public static String fillString(String string, char filler, int totalLength, boolean atEnd) {
        if(string.length() >= totalLength){
            return string;
        }
        byte[] bytes = new byte[totalLength];
        int stringlength = string.length();
        if(!atEnd){
            //左补0
            int c_space = totalLength - stringlength;
            System.arraycopy(string.getBytes(), 0, bytes, c_space, stringlength);
            for(int i = 0;i<c_space;i++){
                bytes[i]= (byte)filler;
            }
        }else{
            //右补0
            System.arraycopy(string.getBytes(), 0, bytes,0 , stringlength);
            for(int i=stringlength;i<totalLength;i++){
                bytes[i]= (byte)filler;
            }
        }

        return new String(bytes);
    }
    
    public static String getCurrDateTime() {
        return getDateFormate("yyyyMMddHHmmss").format(new Date());
    }
    public static SimpleDateFormat getDateFormate(String format){
        if(StringUtils.isEmpty(format)){
            return null;
        }
        if(format.equals("yyyyMMdd")){
            return new SimpleDateFormat("yyyyMMdd");
        }
        if(format.equals("yyyy")){
            return new SimpleDateFormat("yyyy");
        }
        if(format.equals("yyyyMMdd_")){
            return new SimpleDateFormat("yyyy-MM-dd");
        }
        if(format.equals("yyyyMMdd_IU")){
            return new SimpleDateFormat("yyyy/MM/dd");
        }
        if(format.equals("yyyyMMddC")){
            return new SimpleDateFormat("yyyy年MM月dd日");
        }
        if(format.equals("yyyyMM")){
            return new SimpleDateFormat("yyyyMM");
        }
        if(format.equals("yyyyMM_")){
            return new SimpleDateFormat("yyyy-MM");
        }
        if(format.equals("yyyy")){
            return new SimpleDateFormat("yyyy");
        }
        if(format.equals("HHmmss")){
            return new SimpleDateFormat("HHmmss");
        }
        if(format.equals("HHmmss_")){
            return new SimpleDateFormat("HH:mm:ss");
        }
        if(format.equals("HHmmssC")){
            return new SimpleDateFormat("HH点mm分ss秒");
        }
        if(format.equals("yyyyMMddHHmmss")){
            return new SimpleDateFormat("yyyyMMddHHmmss");
        }
        if(format.equals("yyyyMMddHHmmssC")){
            return new SimpleDateFormat("yyyy年MM月dd日HH点mm分ss秒");
        }
        if(format.equals("yyyyMMddHHmmssSSS")){
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        }
        if(format.equals("yyyyMMddHHmmss_")){
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        if(format.equals("yyyyMMddHHmmss_UI")){
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        if(format.equals("yyyyMMddHHmmss_IU")){
            return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        }
        return null;
    }
}