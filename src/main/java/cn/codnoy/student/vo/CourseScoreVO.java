package cn.codnoy.student.vo;

import lombok.Data;

/**
 * 课程成绩统计视图对象
 * 用于展示某门课程中所有学生的成绩信息及统计
 */
@Data
public class CourseScoreVO {
    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 学号
     */
    private String studentId;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 成绩
     */
    private Double score;

    /**
     * 成绩等级(A/B/C/D/F)
     */
    private String gradeLevel;
}
