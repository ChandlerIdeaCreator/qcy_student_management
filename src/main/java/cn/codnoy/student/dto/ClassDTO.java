package cn.codnoy.student.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * 班级数据传输对象
 * 用于班级信息的创建和更新操作
 */
@Data
public class ClassDTO {
    /**
     * 班级编号，不能为空，长度必须在3-20个字符之间
     */
    @NotBlank(message = "班级编号不能为空")
    @Size(min = 3, max = 20, message = "班级编号长度必须在3-20个字符之间")
    private String classId;

    /**
     * 班级名称，不能为空，长度必须在2-50个字符之间
     */
    @NotBlank(message = "班级名称不能为空")
    @Size(min = 2, max = 50, message = "班级名称长度必须在2-50个字符之间")
    private String className;

    /**
     * 年级，不能为空，取值范围1-12
     */
    @NotNull(message = "年级不能为空")
    @Min(value = 1, message = "年级最小为1")
    @Max(value = 12, message = "年级最大为12")
    private Integer grade;

    /**
     * 班主任，不能为空
     */
    @NotBlank(message = "班主任不能为空")
    private String headTeacher;

    /**
     * 教室信息
     */
    private String classroom;
}
