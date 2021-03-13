package utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FieldUtils {

    public static Map<String, Object> getEntity(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        Map<String, Object> map = new HashMap<>();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public static void setEntity(Object obj, Map<String, Object> map) {
        for (String key : map.keySet()) {
            Field field = null;
            try {
                field = obj.getClass().getField(key);
                field.setAccessible(true);
                field.set(obj, map.get(key));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }

    public static List<Class<?>> entityType(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        List<Class<?>> typeList = new ArrayList<>();
        for (Field field : fields) {
            field.setAccessible(true);
            typeList.add(field.getType());
        }
        return typeList;
    }

    public static String varName(Object obj, Object value) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.get(obj).toString().equals(value)) {
                    return field.getName();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
