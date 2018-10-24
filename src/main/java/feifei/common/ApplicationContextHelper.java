package feifei.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName ApplicationContextHelper
 * @Description 获取spring上下文的类的bean
 * @Author : liutao
 * @Date : 2018/9/26 22:23
 * @Version : 1.0
 **/
//@Component这个注解使这个类被spring所管理,当项目启动时会把applicationContext注入到这个类里面
@Component("applicationContextHelper")
public class ApplicationContextHelper implements ApplicationContextAware{

    //定义一个全局的ApplicationContext
    public static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    //从applicationContext中取出spring上下文的bean
    public static <T> T popBean(Class<T> tClass){
        if(applicationContext == null){
            return null;
        }
        return applicationContext.getBean(tClass);
    }

    public static <T> T popBean(String name,Class<T> tClass){
        if(applicationContext == null){
            return null;
        }
        return applicationContext.getBean(name,tClass);
    }


}
