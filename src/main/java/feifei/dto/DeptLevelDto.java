package feifei.dto;

import com.google.common.collect.Lists;
import feifei.model.SysDept;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @ClassName DeptLevelDto
 * @Description 更好的展示部门树的层级
 * @Author : liutao
 * @Date : 2018/9/27 21:38
 * @Version : 1.0
 **/
@Getter
@Setter
@ToString
public class DeptLevelDto extends SysDept {

    private List<DeptLevelDto> deptLevelDtoList = Lists.newArrayList();

    public static DeptLevelDto adapt(SysDept sysDept){
        DeptLevelDto deptLevelDto = new DeptLevelDto();
        BeanUtils.copyProperties(sysDept,deptLevelDto);//完成两个类相同字段的copy
        return deptLevelDto;
    }
}
