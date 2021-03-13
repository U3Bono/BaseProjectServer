package utils;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ResponseUtils {

    /**
     * 返回数据
     */
    public static void respBack(HttpServletResponse response, String json) {
        //设置编码格式
        String encoding = "UTF-8";
        response.setCharacterEncoding(encoding);
        ServletOutputStream out;
        try {
            out = response.getOutputStream();
            out.print(json);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
