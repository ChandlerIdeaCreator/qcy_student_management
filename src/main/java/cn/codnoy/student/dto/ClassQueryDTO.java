package cn.codnoy.student.dto;

import lombok.Data;

/**
 * 班级查询数据传输对象
 * 用于封装班级信息查询的条件参数
 */
@Data
public class ClassQueryDTO {
    /**
     * 班级名称，用于模糊查询
     */
    private String className;

    /**
     * 年级，用于按年级查询
     */
    private Integer grade;

    /**
     * 班主任，用于按班主任查询
     */
    private String headTeacher;

    /**
     * 页码，分页查询的当前页数，默认为第1页
     */
    private Integer pageNum = 1;

    /**
     * 每页大小，分页查询每页显示的记录数，默认为10条
     */
    private Integer pageSize = 10;
}
