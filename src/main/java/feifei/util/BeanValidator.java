package feifei.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import feifei.exception.ParamException;
import org.apache.commons.collections.MapUtils;

import javax.validation.*;
import java.util.*;
import java.util.prefs.PreferenceChangeEvent;

/**
 * @ClassName BeanValidator
 * @Description 前台传输参数后，后台对参数校验
 * @Author : liutao
 * @Date : 2018/9/26 20:50
 * @Version : 1.0
 **/
public class BeanValidator {

    //定义一个validator工厂
    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    //单个类的校验
    public static <T> Map<String,String > validate(T t, Class... groups){
        Validator validator = validatorFactory.getValidator();
        //自动获取到校验的结果
        Set validateResult = validator.validate(t,groups);
        if(validateResult.isEmpty()){
            //若校验的结果为空，直接返回一个空的map
            return Collections.emptyMap();
        }else{
            //定义一个校验出错的map集合
            LinkedHashMap errors = Maps.newLinkedHashMap();
            Iterator iterator = validateResult.iterator();
            while (iterator.hasNext()){
                ConstraintViolation constraintViolation = (ConstraintViolation) iterator.next();
                errors.put(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage());
            }
            return errors;
        }
    }

    //封装list的校验方法
    public static Map<String,String> validatorList(Collection<?> collection){
        Preconditions.checkNotNull(collection);//校验是否为空
        Iterator iterator = collection.iterator();
        Map errors;
        //给errors赋值后对errors进行判空处理
        do {
            if(!iterator.hasNext()){
                return Collections.emptyMap();
            }
            Object object = iterator.next();
            errors = validate(object,new Class[0]);
        }while (errors.isEmpty());
        return errors;
    }

    //传入一个对象(所有的校验都可以通过这个方法做，包括以上两个方法)
    public static Map<String,String> validatorObject(Object first,Object... objects){
        if(objects != null && objects.length > 0){
            return validatorList(Lists.asList(first,objects));
        }else{
            return validate(first,new Class[0]);
        }
    }

    //判断是否有异常,若前台需要返回错误信息，则只需要调用这个方法就可以了
    public static void check(Object param) throws ParamException{
        Map<String,String> map = BeanValidator.validatorObject(param);
        if(MapUtils.isEmpty(map)){//判断当前map是否为空
            //若出现异常信息，则直接抛出
            throw new ParamException(map.toString());
        }
    }


}
