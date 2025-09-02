package cn.codnoy.student.service.impl;

import cn.codnoy.student.dto.ScoreDTO;
import cn.codnoy.student.dto.ScoreQueryDTO;
import cn.codnoy.student.entity.Score;
import cn.codnoy.student.mapper.ScoreMapper;
import cn.codnoy.student.service.ScoreService;
import cn.codnoy.student.vo.CourseScoreVO;
import cn.codnoy.student.vo.ScoreStatVO;
import cn.codnoy.student.vo.StudentScoreVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 成绩服务实现类
 * 提供成绩相关的业务逻辑处理
 */
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements ScoreService {

    /**
     * 分页查询成绩列表
     *
     * @param query 成绩查询条件DTO
     * @return 成绩分页结果
     */
    @Override
    public IPage<Score> listScores(ScoreQueryDTO query) {
        Page<Score> page = new Page<>(query.getPageNum(), query.getPageSize());
        return baseMapper.selectByCondition(page, query);
    }

    /**
     * 根据ID获取成绩信息
     *
     * @param id 成绩ID
     * @return 成绩实体对象
     */
    @Override
    public Score getScoreById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 保存成绩信息
     *
     * @param scoreDTO 成绩数据传输对象
     * @return 是否保存成功
     */
    @Override
    @Transactional
    public boolean saveScore(ScoreDTO scoreDTO) {
        // 检查是否已存在该学生该课程的成绩
        int count = baseMapper.countByStudentAndCourse(
                scoreDTO.getStudentId(),
                scoreDTO.getCourseId(),
                scoreDTO.getSemester()
        );
        if (count > 0) {
            throw new RuntimeException("该学生该课程的成绩已存在");
        }

        Score score = new Score();
        BeanUtils.copyProperties(scoreDTO, score);
        return this.save(score);
    }

    /**
     * 更新成绩信息
     *
     * @param id 成绩ID
     * @param scoreDTO 成绩数据传输对象
     * @return 是否更新成功
     */
    @Override
    @Transactional
    public boolean updateScore(Long id, ScoreDTO scoreDTO) {
        Score score = this.getById(id);
        if (score == null) {
            return false;
        }
        BeanUtils.copyProperties(scoreDTO, score);
        return this.updateById(score);
    }

    /**
     * 删除成绩信息
     *
     * @param id 成绩ID
     * @return 是否删除成功
     */
    @Override
    @Transactional
    public boolean deleteScore(Long id) {
        return this.removeById(id);
    }

    /**
     * 获取课程成绩统计信息
     *
     * @param courseId 课程ID
     * @param semester 学期
     * @return 课程成绩统计列表
     */
    @Override
    public List<CourseScoreVO> getCourseScoreStats(Long courseId, String semester) {
        return baseMapper.selectCourseScoreStats(courseId, semester);
    }

    /**
     * 获取学生成绩统计信息
     *
     * @param studentId 学生ID
     * @return 学生成绩统计列表
     */
    @Override
    public List<StudentScoreVO> getStudentScoreStats(Long studentId) {
        return baseMapper.selectStudentScoreStats(studentId);
    }

    /**
     * 获取成绩统计信息
     *
     * @param courseId 课程ID
     * @param semester 学期
     * @return 成绩统计VO对象
     */
    @Override
    public ScoreStatVO getScoreStatistics(Long courseId, String semester) {
        return baseMapper.selectScoreStatistics(courseId, semester);
    }

    /**
     * 批量导入成绩
     *
     * @param scoreList 成绩列表
     * @return 是否导入成功
     */
    @Override
    @Transactional
    public boolean batchImportScores(List<ScoreDTO> scoreList) {
        for (ScoreDTO scoreDTO : scoreList) {
            this.saveScore(scoreDTO);
        }
        return true;
    }
}
