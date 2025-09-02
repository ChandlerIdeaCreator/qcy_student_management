package cn.codnoy.student.dto;

import lombok.Data;

/**
 * 教师查询数据传输对象
 * 用于封装教师信息查询的条件参数
 */
@Data
public class TeacherQueryDTO {
    /**
     * 教师姓名，用于按姓名查询
     */
    private String name;

    /**
     * 性别，用于按性别查询
     */
    private String gender;

    /**
     * 所属院系，用于按院系查询
     */
    private String department;

    /**
     * 职称，用于按职称查询
     */
    private String title;

    /**
     * 页码，分页查询的当前页数，默认为第1页
     */
    private Integer pageNum = 1;

    /**
     * 每页大小，分页查询每页显示的记录数，默认为10条
     */
    private Integer pageSize = 10;
}
