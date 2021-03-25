package com.tools;

import java.util.HashMap;

public class FindCodeName
{
    static HashMap<String, String> Map = new HashMap<String, String>();
    static
    {
        Map.put("mem1", "未驗證");
        Map.put("mem2", "已驗證");

        Map.put("resac1", "已停權");
        Map.put("resac2", "休假");
        Map.put("resac3", "工作中");

        Map.put("repofea1", "通過");
        Map.put("repofea2", "未通過");
        Map.put("repofea3", "未處理");

        Map.put("repopost1", "通過");
        Map.put("repopost2", "未通過");
        Map.put("repopost3", "未處理");

        Map.put("repolm1", "通過");
        Map.put("repolm2", "未通過");
        Map.put("repolm3", "未處理");

        Map.put("fo1", "上架");
        Map.put("fo2", "下架");
        Map.put("fo3", "餐點待審核");
        
        Map.put("f1", "未接受邀請");
        Map.put("f2", "已接受邀請");

        Map.put("lm1", "上架");
        Map.put("lm2", "下架");
        
        Map.put("post1", "上架");
        Map.put("post2", "下架");

        Map.put("fea1", "報名中");
        Map.put("fea2", "進行中");
        Map.put("fea3", "已結束");
        Map.put("fea4", "已取消");
        Map.put("fea5", "被檢舉後下架");

        Map.put("res1", "未驗證");
        Map.put("res2", "資訊未審核");
        Map.put("res3", "上線商家");
        Map.put("res4", "被下架商家");

        Map.put("ords1", "未處理");
        Map.put("ords2", "未出發");
        Map.put("ords3", "已出發");
        Map.put("ords4", "已接單");
        Map.put("ords5", "拒絕接單");
        Map.put("ords6", "已處理");

        Map.put("ordt1", "外帶");
        Map.put("ordt2", "外送");
        Map.put("ordt3", "訂位");

        Map.put("ads1", "未審核");
        Map.put("ads2", "通過");
        Map.put("ads3", "未通過");

    };

    public static String meaning(String StatusNum)
    {
        return Map.get(StatusNum);
    }

//    public static void main(String[] args)
//    {
//        String test = FindCodeName.meaning("f1");
//        System.out.println(test);
//        System.out.println(FindCodeName.meaning(null));
//    }
}
