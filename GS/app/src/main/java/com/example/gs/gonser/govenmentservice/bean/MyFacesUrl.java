package com.example.gs.gonser.govenmentservice.bean;

/**
 * Created by yangzhao on 2018/5/10.
 */

public class MyFacesUrl {

    //天乐园机器服务地址
//    public static String IP = "http://192.168.0.81:8878/APP";

    //大佐机器服务地址
//    public static String IP = "http://10.129.170.141/APP";

//  公司服务地址
//    public static String IP = "http://192.168.0.111/APP";

    //正式服务器地址
    public static String IP = "http://202.100.24.83/APP";

    public static String PIC_IP = "http://192.168.0.94:8888/sxslfj/";

    //登录
    public static String login = IP +  "/app/login?";
    //注册
    public static String register = IP +  "/app/register?";
    //修改个人信息
    public static String update = IP +  "/app/update?";
    //修改密码
    public static String updatePassword = IP +  "/app/updatePassword?";
    //个人主题
    public static String personTheme = IP +  "/app/item/queryServiceTopicPerson?";
    //法人主题
    public static String legalTheme = IP +  "/app/item/queryServiceTopicLegal?";
    //事项详情
    public static String itemInfo = IP +  "/app/item/listContent";
    //事项清单
    public static String itemThingList = IP +  "/app/item/list?";
    //在线办理 暂存
    public static String onlineDo = IP +  "/app/item/saveBusinessInfor";
    //在线办理 提交
    public static String permitSave = IP +  "/app/item/permitSave";
    //部门列表
    public static String departmentList = IP +  "/app/servicePublicNotice/findDepartment";
    //部门下面的事项列表（提交咨询投诉时候用到）
    public static String deptItems = IP +  "/app/servicePublicNotice/deptItems";
    //主题事项列表
    public static String itemList = IP +  "/app/item/list?";
    //提交咨询/投诉
    public static String consultSubmit = IP +  "/app/servicePublicNotice/doComplaints";
    //办件公示
    public static String doThingShowList = IP +  "/app/servicePublicNotice/showList";
    //政策解读 (这个接口,废弃)
    public static String policyList = IP +  "/app/servicePublicNotice/policyList";
    //首页政策解读
    public static String noticeLimitList = IP +  "/app/notice/list";
    //政策解读
    public static String noticeList = IP +  "/app/notice/list";
    //政策解读详情
    public static String viewinfo = IP +  "/app/notice/view";
    //我要收藏
    public static String collCetSave = IP +  "/app/item/collCetSave";
    //我的收藏
    public static String collCetFind = IP +  "/app/item/collCetFind";
    //我的评论
    public static String myComment = IP +  "/app/item/business_evaluate";
    //我的办件
    public static String business_list = IP +  "/app/item/business_list";
    //我的咨询/投诉
    public static String business_ask = IP +  "/app/item/business_ask";
    //我去评价
    public static String goEvaluate = IP +  "/app/item/doEvaluate";
    //办件详情
    public static String business_look = IP +  "/app/item/business_look";

    //阅读须知
    public static String findConditionsReading = IP +  "/app/item/findConditionsReading";
    //获取在线办理申报材料和form表单
    public static String baseinfo = IP +  "/app/item/baseinfo";
    //webView 地址链接
    public static String baseInfoForm = IP +  "/app/item/baseInfoForm?";
    //获取token 值
    public static String getToken = IP +  "/app/item/getToken?";
    //单独上传文件 前缀IP 由 上面获取token值返回
    public static String uploadStream ="/uploadFile/uploadStream?";

}
