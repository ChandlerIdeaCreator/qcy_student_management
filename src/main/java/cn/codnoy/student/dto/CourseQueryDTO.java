package cn.codnoy.student.dto;

import lombok.Data;

/**
 * 课程查询数据传输对象
 * 用于封装课程信息查询的条件参数
 */
@Data
public class CourseQueryDTO {
    /**
     * 课程编号，用于按课程编号查询
     */
    private String courseId;    // 课程编号

    /**
     * 课程名称，用于按课程名称查询
     */
    private String courseName;    // 课程名称

    /**
     * 页码，分页查询的当前页数，默认为第1页
     */
    private Integer pageNum = 1;

    /**
     * 每页大小，分页查询每页显示的记录数，默认为10条
     */
    private Integer pageSize = 10;
}
