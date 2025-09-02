package cn.codnoy.student.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.Date;
import java.time.LocalDateTime;
/**
 * 学生实体类
 * 用于表示学校中的学生基本信息
 */
@Data
@TableName("students")
public class Student {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 学号
     */
    private String studentId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 出生日期
     */
    private LocalDate birthDate;

    /**
     * 班级ID
     */
    private String classId;

    /**
     * 入学日期
     */
    private LocalDate enrollmentDate;

    /**
     * 地址
     */
    private String address;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    @TableField(insertStrategy = FieldStrategy.NOT_NULL)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(insertStrategy = FieldStrategy.NOT_NULL, updateStrategy = FieldStrategy.NOT_NULL)
    private LocalDateTime updateTime;

    /**
     * 逻辑删除标志(0:未删除, 1:已删除)
     */
    @TableLogic
    private Integer deleted;
}
