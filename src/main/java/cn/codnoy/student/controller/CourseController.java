package cn.codnoy.student.controller;

import cn.codnoy.student.common.R;
import cn.codnoy.student.dto.CourseDTO;
import cn.codnoy.student.dto.CourseQueryDTO;
import cn.codnoy.student.entity.Course;
import cn.codnoy.student.service.CourseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "courses", description = "课程管理")
@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Operation(summary = "分页查询课程列表", description = "根据课程名称和课程代码等条件分页查询课程信息")
    @GetMapping
    public R<IPage<Course>> list(CourseQueryDTO query) {
        return R.success(courseService.listCourses(query));
    }

    @Operation(summary = "根据ID获取课程详情", description = "通过课程ID获取课程的详细信息")
    @GetMapping("/{id}")
    public R<Course> getById(@PathVariable Long id) {
        return R.success(courseService.getCourseById(id));
    }

    @Operation(summary = "新增课程", description = "创建新的课程信息，需要提供唯一的课程代码")
    @PostMapping
    public R<Boolean> save(@RequestBody @Validated CourseDTO courseDTO) {
        return R.success(courseService.saveCourse(courseDTO));
    }

    @Operation(summary = "更新课程信息", description = "根据课程ID更新课程信息，课程代码不能与其他课程冲突")
    @PutMapping("/{id}")
    public R<Boolean> update(@PathVariable Long id, @RequestBody @Validated CourseDTO courseDTO) {
        return R.success(courseService.updateCourse(id, courseDTO));
    }

    @Operation(summary = "删除课程", description = "根据课程ID删除课程信息，有关联数据的课程无法删除")
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable Long id) {
        return R.success(courseService.deleteCourse(id));
    }
}
