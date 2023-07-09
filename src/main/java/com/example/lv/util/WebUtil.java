package com.example.lv.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description webUtil
 * @date 2023/6/18 11:05:02
 */
public class WebUtil {
    /**
     * 将字符串渲染到客户端
     * @param response
     * @param string
     * @return
     */
    public static String renderString(HttpServletResponse response,String string){
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(string);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }


}
