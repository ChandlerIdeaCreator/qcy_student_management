package cn.codnoy.student.vo;

import lombok.Data;

/**
 * 学生成绩统计视图对象
 * 用于展示某个学生的各科成绩详情
 */
@Data
public class StudentScoreVO {
    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 课程编号
     */
    private String courseCode;

    /**
     * 学分
     */
    private Double credit;

    /**
     * 成绩
     */
    private Double score;

    /**
     * 学期
     */
    private String semester;

    /**
     * 教师姓名
     */
    private String teacherName;
}
