package cn.codnoy.student.mapper;

import cn.codnoy.student.dto.ClassQueryDTO;
import cn.codnoy.student.entity.Class;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * 班级 Mapper 接口
 * 提供班级信息的数据库操作方法
 */
public interface ClassMapper extends BaseMapper<Class> {

    /**
     * 根据条件分页查询班级信息
     *
     * @param page  分页对象
     * @param query 查询条件DTO
     * @return 班级分页结果
     */
    IPage<Class> selectByCondition(Page<Class> page, @Param("query") ClassQueryDTO query);

    /**
     * 更新班级学生人数
     *
     * @param classId 班级ID
     * @param count   学生人数
     * @return 更新记录数
     */
    int updateStudentCount(@Param("classId") String classId, @Param("count") int count);
}
