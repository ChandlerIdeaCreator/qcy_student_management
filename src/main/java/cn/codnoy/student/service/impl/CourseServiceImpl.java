package cn.codnoy.student.service.impl;

import cn.codnoy.student.dto.CourseDTO;
import cn.codnoy.student.dto.CourseQueryDTO;
import cn.codnoy.student.entity.Course;
import cn.codnoy.student.mapper.CourseMapper;
import cn.codnoy.student.service.CourseService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * 课程服务实现类
 * 提供课程相关的业务逻辑处理
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    /**
     * 分页查询课程列表
     *
     * @param query 课程查询条件DTO
     * @return 课程分页结果
     */
    @Override
    public IPage<Course> listCourses(CourseQueryDTO query) {
        Page<Course> page = new Page<>(query.getPageNum(), query.getPageSize());

        LambdaQueryWrapper<Course> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(query.getCourseName()), Course::getCourseName, query.getCourseName())
                .like(StringUtils.hasText(query.getCourseId()), Course::getCourseId, query.getCourseId())
                .orderByAsc(Course::getCourseId);

        return baseMapper.selectPage(page, wrapper);
    }

    /**
     * 根据ID获取课程信息
     *
     * @param id 课程ID
     * @return 课程实体对象
     */
    @Override
    public Course getCourseById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 保存课程信息
     *
     * @param courseDTO 课程数据传输对象
     * @return 是否保存成功
     */
    @Override
    @Transactional
    public boolean saveCourse(CourseDTO courseDTO) {
        // 检查课程编号是否已存在
        if (lambdaQuery().eq(Course::getCourseId, courseDTO.getCourseId()).count() > 0) {
            throw new RuntimeException("课程编号已存在");
        }

        Course course = new Course();
        BeanUtils.copyProperties(courseDTO, course);
        return this.save(course);
    }

    /**
     * 更新课程信息
     *
     * @param id 课程ID
     * @param courseDTO 课程数据传输对象
     * @return 是否更新成功
     */
    @Override
    @Transactional
    public boolean updateCourse(Long id, CourseDTO courseDTO) {
        Course existingCourse = getById(id);
        if (existingCourse == null) {
            throw new RuntimeException("课程不存在");
        }

        // 检查课程编号是否被其他课程使用
        if (lambdaQuery()
                .eq(Course::getCourseId, courseDTO.getCourseId())
                .ne(Course::getId, id)
                .count() > 0) {
            throw new RuntimeException("课程编号已被其他课程使用");
        }

        Course course = new Course();
        BeanUtils.copyProperties(courseDTO, course);
        course.setId(id);

        return this.updateById(course);
    }

    /**
     * 删除课程信息
     *
     * @param id 课程ID
     * @return 是否删除成功
     */
    @Override
    @Transactional
    public boolean deleteCourse(Long id) {
        Course course = getById(id);
        if (course == null) {
            throw new RuntimeException("课程不存在");
        }

        // 检查是否有授课安排关联此课程
        if (baseMapper.checkIfHasTeachingAssignments(id) > 0) {
            throw new RuntimeException("该课程已有授课安排，无法删除");
        }

        // 检查是否有成绩记录关联此课程
        if (baseMapper.checkIfHasScores(id) > 0) {
            throw new RuntimeException("该课程已有成绩记录，无法删除");
        }

        return this.removeById(id);
    }
}
