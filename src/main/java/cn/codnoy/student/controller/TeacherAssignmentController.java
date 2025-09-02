package cn.codnoy.student.controller;

import cn.codnoy.student.common.R;
import cn.codnoy.student.dto.TeacherAssignmentDTO;
import cn.codnoy.student.dto.TeacherAssignmentQueryDTO;
import cn.codnoy.student.entity.TeacherAssignment;
import cn.codnoy.student.service.TeacherAssignmentService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "teaching-assignments", description = "授课安排管理")
@RestController
@RequestMapping("/teaching-assignments")
public class TeacherAssignmentController {

    @Autowired
    private TeacherAssignmentService teachingAssignmentService;

    @Operation(summary = "分页查询授课安排列表", description = "根据教师、课程等条件分页查询授课安排信息")
    @GetMapping
    public R<IPage<TeacherAssignment>> list(TeacherAssignmentQueryDTO query) {
        return R.success(teachingAssignmentService.listTeachingAssignments(query));
    }

    @Operation(summary = "根据ID获取授课安排详情", description = "通过授课安排ID获取授课安排的详细信息")
    @GetMapping("/{id}")
    public R<TeacherAssignment> getById(@PathVariable Long id) {
        return R.success(teachingAssignmentService.getTeachingAssignmentById(id));
    }

    @Operation(summary = "新增授课安排", description = "创建新的授课安排，会检查教师时间冲突和教室占用情况")
    @PostMapping
    public R<Boolean> save(@RequestBody @Validated TeacherAssignmentDTO assignmentDTO) {
        return R.success(teachingAssignmentService.saveTeachingAssignment(assignmentDTO));
    }

    @Operation(summary = "更新授课安排", description = "根据授课安排ID更新授课安排信息，会检查教师时间冲突和教室占用情况")
    @PutMapping("/{id}")
    public R<Boolean> update(@PathVariable Long id, @RequestBody @Validated TeacherAssignmentDTO assignmentDTO) {
        return R.success(teachingAssignmentService.updateTeachingAssignment(id, assignmentDTO));
    }

    @Operation(summary = "删除授课安排", description = "根据授课安排ID删除授课安排信息，已有关联成绩的不能删除")
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable Long id) {
        return R.success(teachingAssignmentService.deleteTeachingAssignment(id));
    }
}
