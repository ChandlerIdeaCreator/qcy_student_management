package cn.codnoy.student.entity;

import com.baomidou.mybatisplus.annotation.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

import java.util.Date;

/**
 * 成绩实体类
 * 用于记录学生的课程成绩信息
 */
@Data
@TableName("scores")
public class Score {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 学生ID
     */
    private String studentId;

    /**
     * 课程ID
     */
    private String courseId;

//    /**
//     * 教师ID
//     */
//    private Long teacherId;

    /**
     * 成绩(0-100之间)
     */
    @DecimalMin(value = "0", message = "成绩不能小于0")
    @DecimalMax(value = "100", message = "成绩不能大于100")
    private Double score;

    /**
     * 学期
     */
    private String semester;

    /**
     * 考试日期
     */
    private Date examDate;

    /**
     * 备注
     */
    private String remark;

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
