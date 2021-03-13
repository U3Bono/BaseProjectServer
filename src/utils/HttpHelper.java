package utils;

import jakarta.servlet.http.HttpServletRequest;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class HttpHelper {

    public static Map requestToMap(HttpServletRequest req, Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        Map<String, Object> map = new HashMap<>();
        for (Field field : fields) {
            field.setAccessible(true);
            map.put(field.getName(), req.getParameter(field.getName()));
        }
        return map;
    }

    public static void requestToEntity(HttpServletRequest req, Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                field.set(obj, req.getParameter(field.getName()));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
