package cn.codnoy.student.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * 教师实体类
 * 用于表示学校的教师基本信息
 */
@Data
@TableName("teachers")
public class Teacher {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 教师工号
     */
    private String teacherId;

    /**
     * 教师姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 出生日期
     */
    private Date birthDate;

    /**
     * 职称
     */
    private String title;

    /**
     * 所属院系
     */
    private String department;

    /**
     * 入职日期
     */
    private Date hireDate;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 电子邮箱
     */
    private String email;

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
