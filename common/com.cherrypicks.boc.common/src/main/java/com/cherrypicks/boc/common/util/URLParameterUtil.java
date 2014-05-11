package com.cherrypicks.boc.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 类说明：URL参数解析
 * 
 */
public class URLParameterUtil {
    private Map<String, String> paramMap = new HashMap<String, String>();

    public void analysis(String url) {
        paramMap.clear();
        if (!"".equals(url)) {// 如果URL不是空字符串
            url = url.substring(url.indexOf('?') + 1);
            String paramaters[] = url.split("&");
            for (String param : paramaters) {
                String values[] = param.split("=");
                if(values.length>1){
                	paramMap.put(values[0],values[1]);
                }
                if(values.length==1){
                	paramMap.put(values[0],null);
                }
            }
        }
    }

    public String getParam(String name) {
        return paramMap.get(name);
    }

    public static void main(String[] args) {
        String test = "name=helddlo&id=100";
        URLParameterUtil urlAnalysis = new URLParameterUtil();
        urlAnalysis.analysis(test);
        System.out.println("name = " + urlAnalysis.getParam("name"));
        System.out.println("id = " + urlAnalysis.getParam("id"));
    }
}