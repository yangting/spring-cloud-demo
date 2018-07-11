package one.yate.spring.cloud.provider.config;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtils {

    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);
    private static final ObjectMapper mapper = new ObjectMapper()//定义全局操作mapper 线程安全可以全局使用
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);  //对不能序列化的部分对象不进行序列化操作

    static {
        mapper.getSerializationConfig().getDefaultPropertyFormat(JsonDoubleFormat.class);
        mapper.getSerializationConfig().getDefaultPropertyFormat(JsonDateFormat.class);
    }

    /**
     * 把对象转换成json字符串
     * 注意：如果是list
     *
     * @param obj 可以是JavaBean 也可以是list
     * @return obj==null return {}
     */
    public static String toJson(Object obj) {
        if (obj == null) return "{}";
        String json = "";
        try {
            json = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 返回指定对象
     *
     * @param json
     * @return Map<String       ,       Object>
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> toObject(String json) {
        if (StringUtils.isBlank(json)) return null;
        try {
            return mapper.readValue(json, Map.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 返回指定对象
     *
     * @param <T>
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T toObject(String json, Class<T> clazz) {
        if (StringUtils.isBlank(json) || clazz == null) return null;
        try {
            //==解决json中属性比class属性多报错的问题
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(json, clazz);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 返回指定List<T>
     *
     * @param json
     * @param clazz T
     * @return
     */
    public static List<Map<String, Object>> toListObject(String json) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if (StringUtils.isBlank(json)) return list;
        JavaType javaType = getCollectionType(ArrayList.class, Map.class);
        try {
            list = mapper.readValue(json, javaType);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Map<String, Object> toMap(String json) {

        return toObject(json);
    }


    /**
     * 返回指定List<T>
     *
     * @param json
     * @param clazz T
     * @return
     */
    public static <T> List<T> toListObject(String json, Class<T> clazz) {
        List<T> list = new ArrayList<T>();
        if (StringUtils.isBlank(json) || clazz == null) return list;
        JavaType javaType = getCollectionType(ArrayList.class, clazz);
        try {
            list = mapper.readValue(json, javaType);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * 结构化数据转DTO对象<br>
     * 这个是Dao使用的，不建议其他人使用<br>
     *
     * @param objs
     * @param clazz
     * @return
     */
    public static <T> List<T> toListObject(List<?> objs, Class<T> clazz) {
        List<T> list = new ArrayList<T>();
        if (objs == null || clazz == null)
            return list;
        log.info(clazz + toJson(objs));
        for (Object obj : objs) {
            list.add(toObject(toJson(obj), clazz));
        }
        return list;
    }

    //==============其他方法========================================

    private static Map<String, String> obj2Map(Object obj) {

        Map<String, String> map = new HashMap<String, String>();
        // System.out.println(obj.getClass());  
        // 获取f对象对应类中的所有属性域  
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
            String varName = fields[i].getName();
            varName = varName.toLowerCase();//将key置为小写，默认为对象的属性  
            try {
                // 获取原来的访问控制权限  
                boolean accessFlag = fields[i].isAccessible();
                // 修改访问控制权限  
                fields[i].setAccessible(true);
                // 获取在对象f中属性fields[i]对应的对象中的变量  
                Object o = fields[i].get(obj);
                if (o != null)
                    map.put(varName, o.toString());
                // System.out.println("传入的对象中包含一个如下的变量：" + varName + " = " + o);  
                // 恢复访问控制权限  
                fields[i].setAccessible(accessFlag);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
        return map;
    }


    //========================私有方法=================================

    /**
     * 获取泛型的Collection Type
     *
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类
     * @return JavaType Java类型
     * @since 1.0
     */
    private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }


}
