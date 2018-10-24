package feifei.util;


import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.type.TypeReference;


/**
 * @ClassName JsonMapper
 * @Description 将一个类对象转换为json对象,将json转换为类对象
 * @Author : liutao
 * @Date : 2018/9/26 21:51
 * @Version : 1.0
 **/
@Slf4j
public class JsonMapper {

    //使用一个全局ObjectMapper对象
    private static ObjectMapper objectMapper = new ObjectMapper();

    //变量的初始化
    static {
        //配置，排除掉为空的字段
        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS,false);
        objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
    }

    //把对象转换为字符串
    public static <T> String objToJson(T src){
        if(src == null){
            return null;
        }
        try{
            //若src本身就是个String的话，将src强转成String，
            return src instanceof String ? (String)src : objectMapper.writeValueAsString(src);
        }catch (Exception e){
            log.warn("parse object to String exception,error:{}",e);
            return null;
        }
    }

    //字符串转换为对象
    public static <T> T jsonToObject(String src, TypeReference<T> typeReference){
        if(typeReference == null && src == null){
            return null;
        }
        try{
            return (T) (typeReference.getType().equals(String.class) ? src : objectMapper.readValue(src,typeReference));
        }catch (Exception e){
            log.warn("parse String to Object exception,String:{},typeReference<T>:{},error:{}",src,typeReference.getType(),e);
            return null;
        }
    }

}
