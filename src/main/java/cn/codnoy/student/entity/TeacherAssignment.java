package cn.codnoy.student.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * 教师授课安排实体类
 * 用于记录教师的授课安排信息
 */
@Data
@TableName("teacher_assignments")
public class TeacherAssignment {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 教师ID
     */
    private Long teacherId;

    /**
     * 课程ID
     */
    private Long courseId;

    /**
     * 学期，如2023-2024-1
     */
    private String semester;

    /**
     * 上课教室
     */
    private String classroom;

    /**
     * 上课时间安排
     */
    private String schedule;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 逻辑删除标志(0:未删除, 1:已删除)
     */
    @TableLogic
    private Integer deleted;
}
