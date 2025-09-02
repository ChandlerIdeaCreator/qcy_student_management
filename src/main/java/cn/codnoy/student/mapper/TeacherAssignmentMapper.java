package cn.codnoy.student.mapper;

import cn.codnoy.student.dto.TeacherQueryDTO;
import cn.codnoy.student.entity.TeacherAssignment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 教师授课安排 Mapper 接口
 * 提供教师授课安排信息的数据库操作方法
 */
public interface TeacherAssignmentMapper extends BaseMapper<TeacherAssignment> {

    /**
     * 根据条件分页查询教师授课安排信息
     *
     * @param page  分页对象
     * @param query 查询条件DTO
     * @return 教师授课安排分页结果
     */
    IPage<TeacherAssignment> selectByCondition(Page<TeacherAssignment> page, @Param("query") TeacherQueryDTO query);

    /**
     * 检查教师是否已有同一时间段的授课安排
     *
     * @param teacherId 教师ID
     * @param semester  学期
     * @param schedule  时间安排
     * @param excludeId 排除的ID（用于更新时）
     * @return 冲突记录数
     */
    int countTeacherScheduleConflict(@Param("teacherId") Long teacherId,
                                     @Param("semester") String semester,
                                     @Param("schedule") String schedule,
                                     @Param("excludeId") Long excludeId);

    /**
     * 检查教师时间安排冲突
     *
     * @param teacherId 教师ID
     * @param semester  学期
     * @param schedule  时间安排
     * @param excludeId 排除的ID（用于更新时）
     * @return 冲突记录数
     */
    @Select("SELECT COUNT(*) FROM teacher_assignments " +
            "WHERE teacher_id = #{teacherId} " +
            "AND semester = #{semester} " +
            "AND schedule = #{schedule} " +
            "AND deleted = 0 " +
            "AND (#{excludeId} IS NULL OR id != #{excludeId})")
    int checkTeacherScheduleConflict(
            @Param("teacherId") Long teacherId,
            @Param("semester") String semester,
            @Param("schedule") String schedule,
            @Param("excludeId") Long excludeId);

    /**
     * 检查教室使用冲突
     *
     * @param classroom 教室
     * @param semester  学期
     * @param schedule  时间安排
     * @param excludeId 排除的ID（用于更新时）
     * @return 冲突记录数
     */
    @Select("SELECT COUNT(*) FROM teacher_assignments " +
            "WHERE classroom = #{classroom} " +
            "AND semester = #{semester} " +
            "AND schedule = #{schedule} " +
            "AND deleted = 0 " +
            "AND (#{excludeId} IS NULL OR id != #{excludeId})")
    int checkClassroomConflict(
            @Param("classroom") String classroom,
            @Param("semester") String semester,
            @Param("schedule") String schedule,
            @Param("excludeId") Long excludeId);

    /**
     * 检查授课安排是否有成绩记录
     *
     * @param assignmentId 授课安排ID
     * @return 成绩记录数量
     */
    @Select("SELECT COUNT(*) FROM scores WHERE teaching_assignment_id = #{assignmentId} AND deleted = 0")
    int checkIfHasScores(@Param("assignmentId") Long assignmentId);
}
