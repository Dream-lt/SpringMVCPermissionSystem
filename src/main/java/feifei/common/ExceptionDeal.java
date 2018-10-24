package feifei.common;

import feifei.exception.ParamException;
import feifei.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName ExceptionDeal
 * @Description 全局异常处理
 * @Author : liutao
 * @Date : 2018/9/26 20:02
 * @Version : 1.0
 **/
@Slf4j
public class ExceptionDeal implements HandlerExceptionResolver{

    //实现这个HandlerExceptionResolver类里面处理异常的方法
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        //根据request获取当前返回的url
        String url = httpServletRequest.getRequestURL().toString();
        //声明一个ModelAndView
        ModelAndView mv;
        //声明一个全局的异常
        String defaultMsg = "System Error";
        //根据后缀判断是json请求，还是页面请求。  .json    .page
        //项目中所有请求json数据，都是用.json结尾
        if(url.endsWith(".json")){
            //如果当前的异常时我们自己定义的异常
            if(e instanceof PermissionException || e instanceof ParamException){
                //这里使用msg应该是我们抛出来的msg
                JsonData jsonData = JsonData.fail(e.getMessage());
                //这里面的jsonView是springTest-servlet.xml里面定义的
                mv = new ModelAndView("jsonView",jsonData.toMap());
            }else{
                log.error("unknow json exception, url:" + url,e);
                JsonData jsonData = JsonData.fail(defaultMsg);
                mv = new ModelAndView("jsonView",jsonData.toMap());
            }
        //项目中所有请求page数据，都是用.page结尾
        }else if(url.endsWith(".page")){
            log.error("unknow page exception, url:" + url,e);
            JsonData jsonData = JsonData.fail(defaultMsg);
            mv = new ModelAndView("exception",jsonData.toMap());
        }else{
            log.error("unknow exception, url:" + url,e);
            //既不是.jsp和.page后缀结尾
            JsonData jsonData = JsonData.fail(defaultMsg);
            mv = new ModelAndView("jsonView",jsonData.toMap());
        }
        /************* 对数据异常进行处理 *****************/


        /************* 对页面异常进行处理 *****************/

        return mv;
    }
}
