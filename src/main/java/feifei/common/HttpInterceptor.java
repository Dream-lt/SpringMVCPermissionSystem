package feifei.common;

import feifei.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @ClassName HttpInterceptor
 * @Description HTTP请求前后的监听
 * @Author : liutao
 * @Date : 2018/9/27 20:22
 * @Version : 1.0
 **/
@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter{

    private static final String START_TIME = "requestStartTime";

    //放在请求处理之前的
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().toString();
        Map map = request.getParameterMap();
        log.info("Request Start. url:{},param:{}",url, JsonMapper.objToJson(map));
        long start = System.currentTimeMillis();
        request.setAttribute(START_TIME,start);
        return true;
    }

    //一个请求结束后回调用这个方法(正常情况)
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        String url = request.getRequestURL().toString();
//        long start = (long) request.getAttribute(START_TIME);
//        long end = System.currentTimeMillis();
//        log.info("Request End. url:{},cost time:{}",url, end - start);
    }

    //一个请求结束后回调用这个方法(任何情况都可以调用，包括出现异常)
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String url = request.getRequestURL().toString();
        long start = (long) request.getAttribute(START_TIME);
        long end = System.currentTimeMillis();
        log.info("Request Completion. url:{},cost time:{}",url, end - start);
    }
}
