package cn.codnoy.student.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * 课程数据传输对象
 * 用于课程信息的创建和更新操作
 */
@Data
public class CourseDTO {
    /**
     * 课程编号，不能为空，长度必须在3-20个字符之间
     */
    @NotBlank(message = "课程编号不能为空")
    @Size(min = 3, max = 20, message = "课程编号长度必须在3-20个字符之间")
    private String courseId;

    /**
     * 课程名称，不能为空，长度必须在2-100个字符之间
     */
    @NotBlank(message = "课程名称不能为空")
    @Size(min = 2, max = 100, message = "课程名称长度必须在2-100个字符之间")
    private String courseName;

    /**
     * 学分，不能为空，取值范围0.5-10.0
     */
    @NotNull(message = "学分不能为空")
    @DecimalMin(value = "0.5", message = "学分最小为0.5")
    @DecimalMax(value = "10.0", message = "学分最大为10.0")
    private Double credit;

    /**
     * 课时，不能为空，取值范围1-200
     */
    @NotNull(message = "课时不能为空")
    @Min(value = 1, message = "课时最小为1")
    @Max(value = 200, message = "课时最大为200")
    private Integer classHours;

    /**
     * 课程类型，不能为空，只能是"必修"或"选修"
     */
    @NotBlank(message = "课程类型不能为空")
    @Pattern(regexp = "必修|选修", message = "课程类型必须是必修或选修")
    private String courseType;

    /**
     * 课程描述
     */
    private String description;
}
