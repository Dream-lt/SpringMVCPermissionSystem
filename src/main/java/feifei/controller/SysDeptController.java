package feifei.controller;

import feifei.common.JsonData;
import feifei.param.DeptParam;
import feifei.service.SysDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @ClassName SysDeptController
 * @Description TODO
 * @Author : liutao
 * @Date : 2018/9/27 21:02
 * @Version : 1.0
 **/
@Controller
@RequestMapping("/sys/dept")
@Slf4j
public class SysDeptController {

    @Resource
    private SysDeptService sysDeptService;

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData saveDept(DeptParam deptParam){
        sysDeptService.save(deptParam);
        return JsonData.success();
    }
}
