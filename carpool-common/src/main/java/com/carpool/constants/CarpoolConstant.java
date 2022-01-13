package com.carpool.constants;

/**
 * Created by zuimeideshiguang on 18/2/13.
 *
 * 拼车常量
 */
public class CarpoolConstant {


    // '拼车信息状态 0 发布中  1 完成  2 取消  3 过期',
    // 发布中
    public static final  int  PUBLISHING_STATUS = 0;
    //完成
    public static final  int  FINISHED_STATUS = 1;
    //取消
    public static final  int  CANCEL_STATUS = 2;
    //过期
    public static final  int  EXPIRE_STATUS = 3;

    //发布者确认中
    public static final  int  CONFIRMING_STATUS = 4;

    // 拼车单 '状态  0 预约中  1  预约成功  2 拒绝   3 取消  4 失效',

    // 预约中
    public static final  int  ORDERING_STATUS = 0;
    //预约成功
    public static final  int  ORDER_SUCCESS_STATUS = 1;
    //拒绝
    public static final  int  ORDER_REFUSE_STATUS = 2;
    //取消
    public static final  int  ORDER_CANCEL_STATUS = 3;
    //失效
    public static final  int  ORDER_EXPIRE_STATUS = 4;



}
