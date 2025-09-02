package cn.codnoy.student.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * 班级实体类
 * 用于表示学校中的班级信息
 */
@Data
@TableName("classes")
public class Class {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 班级编号
     */
    private String classId;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 年级
     */
    private Integer grade;

    /**
     * 班主任
     */
    private String headTeacher;

    /**
     * 教室位置
     */
    private String classroom;

    /**
     * 学生人数
     */
    private Integer studentCount;

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
