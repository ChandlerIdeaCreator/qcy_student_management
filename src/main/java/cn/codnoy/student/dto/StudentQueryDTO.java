package cn.codnoy.student.dto;

import lombok.Data;

/**
 * 学生查询数据传输对象
 * 用于封装学生信息查询的条件参数
 */
@Data
public class StudentQueryDTO {
    /**
     * 学生姓名，用于按姓名查询
     */
    private String name;

    /**
     * 性别，用于按性别查询
     */
    private String gender;

    /**
     * 班级ID，用于按班级查询
     */
    private String classId;

    /**
     * 学号，用于按学号查询
     */
    private String studentId;

    /**
     * 页码，分页查询的当前页数，默认为第1页
     */
    private Integer pageNum = 1;

    /**
     * 每页大小，分页查询每页显示的记录数，默认为10条
     */
    private Integer pageSize = 10;
}
