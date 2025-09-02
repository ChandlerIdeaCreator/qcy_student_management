package cn.codnoy.student.dto;

import lombok.Data;

/**
 * 成绩查询数据传输对象
 * 用于封装成绩查询的条件参数
 */
@Data
public class ScoreQueryDTO {
    /**
     * 学生ID，用于按学生查询成绩
     */
    private Long studentId;

    /**
     * 课程ID，用于按课程查询成绩
     */
    private Long courseId;

    /**
     * 教师ID，用于按教师查询成绩
     */
    private Long teacherId;

    /**
     * 学期，用于按学期查询成绩
     */
    private String semester;

    /**
     * 页码，分页查询的当前页数，默认为第1页
     */
    private Integer pageNum = 1;

    /**
     * 每页大小，分页查询每页显示的记录数，默认为10条
     */
    private Integer pageSize = 10;
}
