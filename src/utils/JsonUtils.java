package utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonUtils {

    /**
     * FastJSON的序列化设置
     */
    private static SerializerFeature[] features = new SerializerFeature[]{
            //输出Map中为Null的值
            SerializerFeature.WriteMapNullValue,

            //如果Boolean对象为Null，则输出为false
            SerializerFeature.WriteNullBooleanAsFalse,

            //如果List为Null，则输出为[]
            SerializerFeature.WriteNullListAsEmpty,

            //如果Number为Null，则输出为0
            SerializerFeature.WriteNullNumberAsZero,

            //输出Null字符串
            SerializerFeature.WriteNullStringAsEmpty,

            //格式化输出日期
            SerializerFeature.WriteDateUseDateFormat
    };

    /**
     * 把Java对象JSON序列化
     */
    public static String toJson(Object obj) {
        return JSON.toJSONString(obj, features);
    }


}

