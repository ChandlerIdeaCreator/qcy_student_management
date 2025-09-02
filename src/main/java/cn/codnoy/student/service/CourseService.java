package cn.codnoy.student.service;

import cn.codnoy.student.dto.CourseDTO;
import cn.codnoy.student.dto.CourseQueryDTO;
import cn.codnoy.student.entity.Course;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 课程服务接口
 * 定义了课程相关的业务操作方法
 */
public interface CourseService {

    /**
     * 分页查询课程列表
     *
     * @param query 课程查询条件DTO
     * @return 课程分页结果
     */
    IPage<Course> listCourses(CourseQueryDTO query);

    /**
     * 根据ID获取课程信息
     *
     * @param id 课程ID
     * @return 课程实体对象
     */
    Course getCourseById(Long id);

    /**
     * 保存课程信息
     *
     * @param courseDTO 课程数据传输对象
     * @return 是否保存成功
     */
    boolean saveCourse(CourseDTO courseDTO);

    /**
     * 更新课程信息
     *
     * @param id 课程ID
     * @param courseDTO 课程数据传输对象
     * @return 是否更新成功
     */
    boolean updateCourse(Long id, CourseDTO courseDTO);

    /**
     * 删除课程信息
     *
     * @param id 课程ID
     * @return 是否删除成功
     */
    boolean deleteCourse(Long id);
}
