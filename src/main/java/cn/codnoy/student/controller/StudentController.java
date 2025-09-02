package cn.codnoy.student.controller;

import cn.codnoy.student.common.R;
import cn.codnoy.student.dto.StudentDTO;
import cn.codnoy.student.dto.StudentQueryDTO;
import cn.codnoy.student.entity.Student;
import cn.codnoy.student.service.StudentService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "students", description = "学生管理")
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Operation(summary = "查询所有学生列表", description = "获取系统中所有学生的列表信息")
    @GetMapping("all")
    public R<List<Student>> listAll() {
        return R.success(studentService.listAll());
    }

    @Operation(summary = "分页查询学生列表", description = "根据学生姓名、学号等条件分页查询学生信息")
    @GetMapping
    public R<IPage<Student>> list(StudentQueryDTO query) {
        return R.success(studentService.listStudents(query));
    }

    @Operation(summary = "根据ID获取学生详情", description = "通过学生ID获取学生的详细信息")
    @GetMapping("/{id}")
    public R<Student> getById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        return R.success(student);
    }

    @Operation(summary = "新增学生", description = "创建新的学生信息，会自动关联班级并更新班级人数")
    @PostMapping
    public R<Boolean> save(@RequestBody @Validated StudentDTO studentDTO) {
        return R.success(studentService.saveStudent(studentDTO));
    }

    @Operation(summary = "更新学生信息", description = "根据学生ID更新学生信息，会自动处理班级调动和人数变更")
    @PutMapping("/{id}")
    public R<Boolean> update(@PathVariable Long id, @RequestBody @Validated StudentDTO studentDTO) {
        return R.success(studentService.updateStudent(id, studentDTO));
    }

    @Operation(summary = "删除学生", description = "根据学生ID删除学生信息，会自动减少班级人数")
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable Long id) {
        return R.success(studentService.deleteStudent(id));
    }
}
