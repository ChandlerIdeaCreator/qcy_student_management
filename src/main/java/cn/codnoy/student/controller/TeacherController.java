package cn.codnoy.student.controller;

import cn.codnoy.student.common.R;
import cn.codnoy.student.dto.TeacherDTO;
import cn.codnoy.student.dto.TeacherQueryDTO;
import cn.codnoy.student.entity.Teacher;
import cn.codnoy.student.service.TeacherService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "teachers", description = "教师管理")
@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Operation(summary = "分页查询教师列表", description = "根据教师姓名等条件分页查询教师信息列表")
    @GetMapping
    public R<IPage<Teacher>> list(TeacherQueryDTO query) {
        return R.success(teacherService.listTeachers(query));
    }

    @Operation(summary = "根据ID获取教师详情", description = "通过教师ID获取教师的详细信息")
    @GetMapping("/{id}")
    public R<Teacher> getById(@PathVariable Long id) {
        return R.success(teacherService.getTeacherById(id));
    }

    @Operation(summary = "新增教师", description = "创建新的教师信息，可自动设置入职日期")
    @PostMapping
    public R<Boolean> save(@RequestBody @Validated TeacherDTO teacherDTO) {
        return R.success(teacherService.saveTeacher(teacherDTO));
    }

    @Operation(summary = "更新教师信息", description = "根据教师ID更新教师的基本信息")
    @PutMapping("/{id}")
    public R<Boolean> update(@PathVariable Long id, @RequestBody @Validated TeacherDTO teacherDTO) {
        return R.success(teacherService.updateTeacher(id, teacherDTO));
    }

    @Operation(summary = "删除教师", description = "根据教师ID删除教师信息")
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable Long id) {
        return R.success(teacherService.deleteTeacher(id));
    }
}
