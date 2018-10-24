package feifei.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import feifei.dao.SysDeptMapper;
import feifei.dto.DeptLevelDto;
import feifei.model.SysDept;
import feifei.util.LevelUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @ClassName SysTreeService
 * @Description 计算树的类
 * @Author : liutao
 * @Date : 2018/9/27 21:43
 * @Version : 1.0
 **/
@Service
public class SysTreeService {

    @Resource
    private SysDeptMapper sysDeptMapper;

    //返回部门树
    public List<DeptLevelDto> deptTree(){
        //取出所有的部门信息
        List<SysDept> sysDeptList = sysDeptMapper.getAllDept();
        List<DeptLevelDto> deptLevelDtoList = Lists.newArrayList();
        for (SysDept dept : sysDeptList){
            DeptLevelDto dto = DeptLevelDto.adapt(dept);
            deptLevelDtoList.add(dto);
        }
        return listToTree(deptLevelDtoList);
    }

    //将deptLevelDtoList做成树形结构
    public List<DeptLevelDto> listToTree(List<DeptLevelDto> deptLevelDtoList){
        if(CollectionUtils.isEmpty(deptLevelDtoList)){
            return Lists.newArrayList();
        }
        //定义一个特殊的数据结构：把当前的tree的level这个字段当成key，相同level的部门做value
        // level -> [dept1,dept2,dept3...] 这个结构
        Multimap<String,DeptLevelDto> levelDtoMultimap = ArrayListMultimap.create();
        //取出一级部门
        List<DeptLevelDto> rootList = Lists.newArrayList();
        //遍历所有部门
        for(DeptLevelDto deptLevelDto : deptLevelDtoList){
            levelDtoMultimap.put(deptLevelDto.getLevel(),deptLevelDto);//把每一次遍历的level取出来放入levelDtoMultimap中,把相同level的list可以取出来
            if(LevelUtil.ROOT.equals(deptLevelDto.getLevel())){//若当前部门时顶级部门
                rootList.add(deptLevelDto);
            }
        }
        //对ROOT进行排序,按照seq从小到大进行排序
        Collections.sort(rootList, new Comparator<DeptLevelDto>() {
            @Override
            public int compare(DeptLevelDto o1, DeptLevelDto o2) {
                 return o1.getSeq() - o2.getSeq();
            }
        });
        //递归生成树
        transformDeptTree(deptLevelDtoList,LevelUtil.ROOT,levelDtoMultimap);
        return rootList;
    }

    //对rootList进行递归排序
    public void transformDeptTree(List<DeptLevelDto> deptLevelDtoList,String level,Multimap<String,DeptLevelDto> levelDtoMultimap){
        for(int i = 0; i < deptLevelDtoList.size(); i++){
            //遍历每层的元素

            //处理当前层级的数据

            //处理下一层
        }
    }

}
