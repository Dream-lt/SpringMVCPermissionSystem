package feifei.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName LevelUtil
 * @Description 对于业务中出现的级别的工具类
 * @Author : liutao
 * @Date : 2018/9/27 21:12
 * @Version : 1.0
 **/
public class LevelUtil {

    //定义每个层级之间的分隔符
    public final static String SEPERATE = ".";

    public final static String ROOT = "0";

    //定义部门级别的计算规则,   0,0.1,0.1.1,0.2 类似这种结构
    public static String calculateLevel(String parentLvel,int parentId){
        if(StringUtils.isBlank(parentLvel)){//若等级为空
            return ROOT;
        }else{
            return StringUtils.join(parentLvel,SEPERATE,parentId);
        }


    }
}
