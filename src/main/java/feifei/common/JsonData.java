package feifei.common;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName JsonData
 * @Description 前台传回来json字符串的处理
 * @Author : liutao
 * @Date : 2018/9/26 19:32
 * @Version : 1.0
 **/
@Getter
@Setter
public class JsonData {

    //返回结果
    private boolean ret;

    //异常
    private String msg;

    //正常返回给前台的数据
    private Object data;

    //只传入一个结果
    public JsonData(boolean ret){
        this.ret = ret;//直接让结果等于前台传过来的结果
    }

    /*************** 定义几个全局的方法 ****************/
    //定义返回成功的方法,返回成功，则ret就有值，只需要返回msg和data就行
    public static JsonData success(Object object,String msg){
        JsonData jsonData = new JsonData(true);
        jsonData.data = object;
        jsonData.msg = msg;
        return jsonData;
    }

    //成功后不需要返回数据，只需要返回data就行,就是不需要传值到前端
    public static JsonData success(Object object){
        JsonData jsonData = new JsonData(true);
        jsonData.data = object;
        return jsonData;
    }

    //成功后什么都不返回
    public static JsonData success(){
        return new JsonData(true);
    }

    //失败的时候，只需要返回给前端异常信息就行
    public static JsonData fail(String msg){
        JsonData jsonData = new JsonData(false);
        jsonData.msg = msg;
        return jsonData;
    }

    public Map<String,Object> toMap(){
        HashMap<String,Object> result = new HashMap<>();
        result.put("ret",ret);
        result.put("msg",msg);
        result.put("data",data);
        return result;
    }


}
