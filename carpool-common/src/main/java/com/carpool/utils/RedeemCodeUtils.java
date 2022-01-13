package com.carpool.utils;

/**
 * Created by zuimeideshiguang on 18/1/29.
 */
public class RedeemCodeUtils  {

    public static void main(String[] args) {
        String st1 = createBigSmallLetterStrOrNumberRadom(32);
        String st2 = createSmallStrOrNumberRadom(32);
        String st3 = createBigStrOrNumberRadom(32);
        System.out.println(st1);
        System.out.println(st2);
        System.out.println(st3);
    }
    /**
     * @author wangqing@9fbank.com 2015-6-26 下午2:51:44
     * @function 生成num位的随机字符串(数字、大写字母随机混排)
     * @param num
     * @return
     */
    public static String createBigSmallLetterStrOrNumberRadom(int num) {

        String str = "";
        for(int i=0;i < num;i++){
            int intVal=(int)(Math.random()*58+65);
            if(intVal >= 91 && intVal <= 96){
                i--;
            }
            if(intVal < 91 || intVal > 96){
                if(intVal%2==0){
                    str += (char)intVal;
                }else{
                    str += (int)(Math.random()*10);
                }
            }
        }
        return str;
    }
    /**
     * @author wangqing@9fbank.com 2015-6-26 下午2:51:44
     * @function 生成num位的随机字符串(数字、小写字母随机混排)
     * @param num
     * @return
     */
    public static String createSmallStrOrNumberRadom(int num) {

        String str = "";
        for(int i=0;i < num;i++){
            int intVal=(int)(Math.random()*26+97);
            if(intVal%2==0){
                str += (char)intVal;
            }else{
                str += (int)(Math.random()*10);
            }
        }
        return str;
    }
    /**
     * @author wangqing@9fbank.com 2015-6-26 下午2:51:44
     * @function 生成num位的随机字符串(小写字母与数字混排)
     * @param num
     * @return
     */
    public static String createBigStrOrNumberRadom(int num) {

        String str = "";
        for(int i=0;i < num;i++){
            int intVal=(int)(Math.random()*26+65);
            if(intVal%2==0){
                str += (char)intVal;
            }else{
                str += (int)(Math.random()*10);
            }
        }
        return str;
    }
}
