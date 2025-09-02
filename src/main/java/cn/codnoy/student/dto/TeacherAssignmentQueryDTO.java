package cn.codnoy.student.dto;

import lombok.Data;

/**
 * 教学任务分配数据传输对象
 * 用于封装教师信息查询的条件参数
 */
@Data
public class TeacherAssignmentQueryDTO {
    /**
     * 教师ID，不能为空
     */
    private Long teacherId;

    /**
     * 课程ID，不能为空
     */
    private Long courseId;

    /**
     * 学期，不能为空，格式应为YYYY-YYYY-1或YYYY-YYYY-2
     */
    private String semester;

    /**
     * 教室，不能为空
     */
    private String classroom;

    /**
     * 上课时间安排，不能为空
     */
    private String schedule;

    /**
     * 页码，分页查询的当前页数，默认为第1页
     */
    private Integer pageNum = 1;

    /**
     * 每页大小，分页查询每页显示的记录数，默认为10条
     */
    private Integer pageSize = 10;
}
