package cn.codnoy.student.service;

import cn.codnoy.student.dto.ScoreDTO;
import cn.codnoy.student.dto.ScoreQueryDTO;
import cn.codnoy.student.entity.Score;
import cn.codnoy.student.vo.CourseScoreVO;
import cn.codnoy.student.vo.ScoreStatVO;
import cn.codnoy.student.vo.StudentScoreVO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 成绩服务接口
 * 定义了成绩相关的业务操作方法
 */
public interface ScoreService {

    /**
     * 分页查询成绩列表
     *
     * @param query 成绩查询条件DTO
     * @return 成绩分页结果
     */
    IPage<Score> listScores(ScoreQueryDTO query);

    /**
     * 根据ID获取成绩信息
     *
     * @param id 成绩ID
     * @return 成绩实体对象
     */
    Score getScoreById(Long id);

    /**
     * 保存成绩信息
     *
     * @param scoreDTO 成绩数据传输对象
     * @return 是否保存成功
     */
    boolean saveScore(ScoreDTO scoreDTO);

    /**
     * 更新成绩信息
     *
     * @param id 成绩ID
     * @param scoreDTO 成绩数据传输对象
     * @return 是否更新成功
     */
    boolean updateScore(Long id, ScoreDTO scoreDTO);

    /**
     * 删除成绩信息
     *
     * @param id 成绩ID
     * @return 是否删除成功
     */
    boolean deleteScore(Long id);

    /**
     * 获取课程成绩统计信息
     *
     * @param courseId 课程ID
     * @param semester 学期
     * @return 课程成绩统计列表
     */
    List<CourseScoreVO> getCourseScoreStats(Long courseId, String semester);

    /**
     * 获取学生成绩统计信息
     *
     * @param studentId 学生ID
     * @return 学生成绩统计列表
     */
    List<StudentScoreVO> getStudentScoreStats(Long studentId);

    /**
     * 获取成绩统计信息
     *
     * @param courseId 课程ID
     * @param semester 学期
     * @return 成绩统计VO对象
     */
    ScoreStatVO getScoreStatistics(Long courseId, String semester);

    /**
     * 批量导入成绩
     *
     * @param scoreList 成绩列表
     * @return 是否导入成功
     */
    boolean batchImportScores(List<ScoreDTO> scoreList);
}
