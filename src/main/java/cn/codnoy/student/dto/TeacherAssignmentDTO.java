package cn.codnoy.student.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * 教学任务分配数据传输对象
 * 用于教学任务分配信息的创建和更新操作
 */
@Data
public class TeacherAssignmentDTO {
    /**
     * 教师ID，不能为空
     */
    @NotNull(message = "教师ID不能为空")
    private Long teacherId;

    /**
     * 课程ID，不能为空
     */
    @NotNull(message = "课程ID不能为空")
    private Long courseId;

    /**
     * 学期，不能为空，格式应为YYYY-YYYY-1或YYYY-YYYY-2
     */
    @NotBlank(message = "学期不能为空")
    @Pattern(regexp = "\\d{4}-\\d{4}-[12]", message = "学期格式应为YYYY-YYYY-1或YYYY-YYYY-2")
    private String semester;

    /**
     * 教室，不能为空
     */
    @NotBlank(message = "教室不能为空")
    private String classroom;

    /**
     * 上课时间安排，不能为空
     */
    @NotBlank(message = "上课时间安排不能为空")
    private String schedule;
}
