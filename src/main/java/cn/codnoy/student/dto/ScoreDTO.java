package cn.codnoy.student.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

/**
 * 成绩数据传输对象
 * 用于成绩信息的创建和更新操作
 */
@Data
public class ScoreDTO {
    /**
     * 学生ID，不能为空
     */
    @NotNull(message = "学生ID不能为空")
    private Long studentId;

    /**
     * 课程ID，不能为空
     */
    @NotNull(message = "课程ID不能为空")
    private Long courseId;

    /**
     * 教师ID，不能为空
     */
    @NotNull(message = "教师ID不能为空")
    private Long teacherId;

    /**
     * 成绩，不能为空，取值范围0-100
     */
    @NotNull(message = "成绩不能为空")
    @DecimalMin(value = "0", message = "成绩不能小于0")
    @DecimalMax(value = "100", message = "成绩不能大于100")
    private Double score;

    /**
     * 学期，不能为空，格式应为YYYY-YYYY-1或YYYY-YYYY-2
     */
    @NotBlank(message = "学期不能为空")
    @Pattern(regexp = "\\d{4}-\\d{4}-[12]", message = "学期格式应为YYYY-YYYY-1或YYYY-YYYY-2")
    private String semester;

    /**
     * 考试日期，不能是未来时间
     */
    @PastOrPresent(message = "考试日期不能是未来时间")
    private Date examDate;

    /**
     * 备注信息
     */
    private String remark;
}
