package feifei.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;


/**
 * @ClassName DeptParam
 * @Description 部门参数
 * @Author : liutao
 * @Date : 2018/9/27 20:54
 * @Version : 1.0
 **/
@Getter
@Setter
@ToString
public class DeptParam {

    private Integer id;

    @NotBlank(message = "部门名称不能为空")
    @Length(max = 15,min = 2,message = "部门名称在2-15个字符之间")
    private String name;

    private Integer parentid;

    @NotNull(message = "展示顺序不能为空")
    private Integer seq;

    @Length(max = 150,message = "备注字数在150内")
    private String remark;
}
