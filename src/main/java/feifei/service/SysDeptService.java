package feifei.service;

import feifei.dao.SysDeptMapper;
import feifei.exception.ParamException;
import feifei.model.SysDept;
import feifei.param.DeptParam;
import feifei.util.BeanValidator;
import feifei.util.LevelUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @ClassName SysDeptService
 * @Description TODO
 * @Author : liutao
 * @Date : 2018/9/27 21:05
 * @Version : 1.0
 **/
@Service
public class SysDeptService {

    @Resource
    private SysDeptMapper sysDeptMapper;

    //新增
    public void save(DeptParam deptParam){
        BeanValidator.check(deptParam);//校验是否抛出异常
        if(checkExist(deptParam.getId(),deptParam.getParentid(),deptParam.getName())){
            //若出现，直接抛出异常
            throw new ParamException("同一层级下存在相同部门，请修改");
        }
        SysDept sysDept = SysDept.builder().name(deptParam.getName()).parentid(deptParam.getParentid())
                .seq(deptParam.getSeq()).remark(deptParam.getRemark()).build();
        //首先调用getLevel方法查出父级ID对应的level，在调用LevelUtil方法定义级别
        sysDept.setLevel(LevelUtil.calculateLevel(getLevel(deptParam.getParentid()),deptParam.getParentid()));
        sysDept.setOperator("admin");   //TODO:
        sysDept.setOperateTime(new Date());
        sysDept.setOperateIp("127.0.0.1");  //TODO:
        sysDeptMapper.insert(sysDept);
    }

    //通过部门ID获取level
    public String getLevel(Integer deptId){
        SysDept sysDept = sysDeptMapper.selectByPrimaryKey(deptId);
        if(sysDept == null){
            return null;
        }
        return sysDept.getLevel();
    }

    //校验新增的部门是否重复,名称不能相同
    public boolean checkExist(Integer deptId,Integer parentId,String deptName){
        //通过部门ID，父级部门ID，部门名称判断新增部门是否重复   TODO:
        return true;
    }



}
