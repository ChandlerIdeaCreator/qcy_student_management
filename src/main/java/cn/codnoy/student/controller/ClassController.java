package cn.codnoy.student.controller;

import cn.codnoy.student.common.R;
import cn.codnoy.student.dto.ClassDTO;
import cn.codnoy.student.dto.ClassQueryDTO;
import cn.codnoy.student.entity.Class;
import cn.codnoy.student.service.ClassService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 班级管理控制器
 * 提供班级信息的增删改查功能
 */
@Tag(name = "class", description = "班级管理")
@RestController
@RequestMapping("/classes")
public class ClassController {

    @Autowired
    private ClassService classService;

    /**
     * 分页查询班级列表
     * 根据查询条件分页获取班级信息列表
     *
     * @param query 班级查询条件对象
     * @return 班级信息分页结果
     */
    @Operation(
            summary = "分页查询班级列表",
            description = "根据查询条件分页获取班级信息列表"
    )
    @ApiResponse(responseCode = "200", description = "查询成功")
    @GetMapping
    public R<IPage<Class>> list(
            @Parameter(description = "班级查询条件") ClassQueryDTO query) {
        return R.success(classService.listClasses(query));
    }

    /**
     * 根据ID获取班级详情
     * 通过班级ID获取班级的详细信息
     *
     * @param id 班级ID
     * @return 班级详细信息
     */
    @Operation(
            summary = "根据ID获取班级详情",
            description = "通过班级ID获取班级的详细信息"
    )
    @ApiResponse(responseCode = "200", description = "查询成功")
    @GetMapping("/{id}")
    public R<Class> getById(
            @Parameter(description = "班级ID", required = true) @PathVariable Long id) {
        return R.success(classService.getClassById(id));
    }

    /**
     * 新增班级
     * 创建新的班级信息
     *
     * @param classDTO 班级信息对象
     * @return 是否创建成功
     */
    @Operation(
            summary = "新增班级",
            description = "创建新的班级信息"
    )
    @ApiResponse(responseCode = "200", description = "创建成功")
    @RequestBody(description = "班级信息", required = true)
    @PostMapping
    public R<Boolean> save(
            @RequestBody @Validated ClassDTO classDTO) {
        return R.success(classService.saveClass(classDTO));
    }

    /**
     * 更新班级信息
     * 根据班级ID更新班级信息
     *
     * @param id       班级ID
     * @param classDTO 班级信息对象
     * @return 是否更新成功
     */
    @Operation(
            summary = "更新班级信息",
            description = "根据班级ID更新班级信息"
    )
    @ApiResponse(responseCode = "200", description = "更新成功")
    @RequestBody(description = "班级信息", required = true)
    @PutMapping("/{id}")
    public R<Boolean> update(
            @Parameter(description = "班级ID", required = true) @PathVariable Long id,
            @RequestBody @Validated ClassDTO classDTO) {
        return R.success(classService.updateClass(id, classDTO));
    }

    /**
     * 删除班级
     * 根据班级ID删除班级信息
     *
     * @param id 班级ID
     * @return 是否删除成功
     */
    @Operation(
            summary = "删除班级",
            description = "根据班级ID删除班级信息"
    )
    @ApiResponse(responseCode = "200", description = "删除成功")
    @DeleteMapping("/{id}")
    public R<Boolean> delete(
            @Parameter(description = "班级ID", required = true) @PathVariable Long id) {
        return R.success(classService.deleteClass(id));
    }
}
