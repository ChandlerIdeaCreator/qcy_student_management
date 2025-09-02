package cn.codnoy.student.mapper;

import cn.codnoy.student.dto.ScoreQueryDTO;
import cn.codnoy.student.entity.Score;
import cn.codnoy.student.vo.CourseScoreVO;
import cn.codnoy.student.vo.ScoreStatVO;
import cn.codnoy.student.vo.StudentScoreVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 成绩 Mapper 接口
 * 提供成绩信息的数据库操作方法
 */
public interface ScoreMapper extends BaseMapper<Score> {

    /**
     * 根据条件分页查询成绩信息
     *
     * @param page  分页对象
     * @param query 查询条件DTO
     * @return 成绩分页结果
     */
    IPage<Score> selectByCondition(Page<Score> page, @Param("query") ScoreQueryDTO query);

    /**
     * 查询课程成绩统计信息
     *
     * @param courseId 课程ID
     * @param semester 学期
     * @return 课程成绩统计列表
     */
    List<CourseScoreVO> selectCourseScoreStats(@Param("courseId") Long courseId,
                                               @Param("semester") String semester);

    /**
     * 查询学生成绩统计信息
     *
     * @param studentId 学生ID
     * @return 学生成绩统计列表
     */
    List<StudentScoreVO> selectStudentScoreStats(@Param("studentId") Long studentId);

    /**
     * 查询成绩分布统计信息
     *
     * @param courseId 课程ID
     * @param semester 学期
     * @return 成绩统计VO
     */
    ScoreStatVO selectScoreStatistics(@Param("courseId") Long courseId,
                                      @Param("semester") String semester);

    /**
     * 检查学生某课程成绩是否已存在
     *
     * @param studentId 学生ID
     * @param courseId  课程ID
     * @param semester  学期
     * @return 记录数量
     */
    int countByStudentAndCourse(@Param("studentId") Long studentId,
                                @Param("courseId") Long courseId,
                                @Param("semester") String semester);
}
