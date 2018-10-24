package feifei.controller;

import feifei.common.JsonData;
import feifei.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author : liutao
 * @Date : 2018/9/20 20:08
 * @Version : 1.0
 **/
//@RequestMapping("/test")指定请求
@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

    @RequestMapping("/hello.json")
    @ResponseBody
    public JsonData hello(){
        log.info("hello");
        return JsonData.success("hello,permission");
    }
}
