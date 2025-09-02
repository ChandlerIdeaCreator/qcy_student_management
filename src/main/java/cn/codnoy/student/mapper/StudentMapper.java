package cn.codnoy.student.mapper;

import cn.codnoy.student.dto.StudentQueryDTO;
import cn.codnoy.student.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * 学生 Mapper 接口
 * 提供学生信息的数据库操作方法
 */
public interface StudentMapper extends BaseMapper<Student> {

    /**
     * 根据条件分页查询学生信息
     *
     * @param page  分页对象
     * @param query 查询条件DTO
     * @return 学生分页结果
     */
    IPage<Student> selectByCondition(Page<Student> page, @Param("query") StudentQueryDTO query);
}
