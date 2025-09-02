package cn.codnoy.student.mapper;

import cn.codnoy.student.dto.TeacherQueryDTO;
import cn.codnoy.student.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * 教师 Mapper 接口
 * 提供教师信息的数据库操作方法
 */
public interface TeacherMapper extends BaseMapper<Teacher> {

    /**
     * 根据条件分页查询教师信息
     *
     * @param page  分页对象
     * @param query 查询条件DTO
     * @return 教师分页结果
     */
    IPage<Teacher> selectByCondition(Page<Teacher> page, @Param("query") TeacherQueryDTO query);
}
