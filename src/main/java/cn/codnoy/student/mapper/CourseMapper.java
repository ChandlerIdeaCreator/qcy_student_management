package cn.codnoy.student.mapper;

import cn.codnoy.student.dto.CourseQueryDTO;
import cn.codnoy.student.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 课程 Mapper 接口
 * 提供课程信息的数据库操作方法
 */
public interface CourseMapper extends BaseMapper<Course> {

    /**
     * 根据条件分页查询课程信息
     *
     * @param page  分页对象
     * @param query 查询条件DTO
     * @return 课程分页结果
     */
    IPage<Course> selectByCondition(Page<Course> page, @Param("query") CourseQueryDTO query);

    /**
     * 检查课程是否有授课安排
     *
     * @param courseId 课程ID
     * @return 授课安排数量
     */
    @Select("SELECT COUNT(*) FROM teaching_assignments WHERE course_id = #{courseId} AND deleted = 0")
    int checkIfHasTeachingAssignments(@Param("courseId") Long courseId);

    /**
     * 检查课程是否有成绩记录
     *
     * @param courseId 课程ID
     * @return 成绩记录数量
     */
    @Select("SELECT COUNT(*) FROM scores WHERE course_id = #{courseId} AND deleted = 0")
    int checkIfHasScores(@Param("courseId") Long courseId);
}
