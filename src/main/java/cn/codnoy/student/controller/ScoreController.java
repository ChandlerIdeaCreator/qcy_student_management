package cn.codnoy.student.controller;

import cn.codnoy.student.common.R;
import cn.codnoy.student.dto.ScoreDTO;
import cn.codnoy.student.dto.ScoreQueryDTO;
import cn.codnoy.student.entity.Score;
import cn.codnoy.student.service.ScoreService;
import cn.codnoy.student.vo.CourseScoreVO;
import cn.codnoy.student.vo.ScoreStatVO;
import cn.codnoy.student.vo.StudentScoreVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "scores", description = "成绩管理")
@RestController
@RequestMapping("/scores")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @Operation(summary = "分页查询成绩列表", description = "根据学生、课程等条件分页查询成绩信息")
    @GetMapping
    public R<IPage<Score>> list(ScoreQueryDTO query) {
        return R.success(scoreService.listScores(query));
    }

    @Operation(summary = "根据ID获取成绩详情", description = "通过成绩ID获取成绩的详细信息")
    @GetMapping("/{id}")
    public R<Score> getById(@PathVariable Long id) {
        return R.success(scoreService.getScoreById(id));
    }

    @Operation(summary = "新增成绩", description = "录入新的成绩信息，同一学生同一课程同一学期成绩不能重复")
    @PostMapping
    public R<Boolean> save(@RequestBody @Validated ScoreDTO scoreDTO) {
        return R.success(scoreService.saveScore(scoreDTO));
    }

    @Operation(summary = "更新成绩", description = "根据成绩ID更新成绩分数等信息")
    @PutMapping("/{id}")
    public R<Boolean> update(@PathVariable Long id, @RequestBody @Validated ScoreDTO scoreDTO) {
        return R.success(scoreService.updateScore(id, scoreDTO));
    }

    @Operation(summary = "删除成绩", description = "根据成绩ID删除成绩记录")
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable Long id) {
        return R.success(scoreService.deleteScore(id));
    }

    @Operation(summary = "批量导入成绩", description = "批量导入成绩数据，会检查重复记录")
    @PostMapping("/batch-import")
    public R<Boolean> batchImport(@RequestBody List<ScoreDTO> scoreList) {
        return R.success(scoreService.batchImportScores(scoreList));
    }

    @Operation(summary = "课程成绩统计", description = "获取指定课程和学期的成绩统计信息，包括各分数段人数")
    @GetMapping("/course-stats")
    public R<List<CourseScoreVO>> getCourseScoreStats(
            @RequestParam Long courseId,
            @RequestParam String semester) {
        return R.success(scoreService.getCourseScoreStats(courseId, semester));
    }

    @Operation(summary = "学生成绩统计", description = "获取指定学生的各科成绩统计信息")
    @GetMapping("/student-stats/{studentId}")
    public R<List<StudentScoreVO>> getStudentScoreStats(@PathVariable Long studentId) {
        return R.success(scoreService.getStudentScoreStats(studentId));
    }

    @Operation(summary = "成绩分布统计", description = "获取指定课程和学期的成绩分布统计信息，包括平均分、最高分、最低分等")
    @GetMapping("/score-stats")
    public R<ScoreStatVO> getScoreStatistics(
            @RequestParam Long courseId,
            @RequestParam String semester) {
        return R.success(scoreService.getScoreStatistics(courseId, semester));
    }
}
