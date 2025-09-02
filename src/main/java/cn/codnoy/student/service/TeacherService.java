package cn.codnoy.student.service;

import cn.codnoy.student.dto.TeacherDTO;
import cn.codnoy.student.dto.TeacherQueryDTO;
import cn.codnoy.student.entity.Teacher;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 教师服务接口
 * 继承自MyBatis-Plus的IService接口，提供教师相关的业务操作方法
 */
public interface TeacherService extends IService<Teacher> {

    /**
     * 分页查询教师列表
     *
     * @param query 教师查询条件DTO
     * @return 教师分页结果
     */
    IPage<Teacher> listTeachers(TeacherQueryDTO query);

    /**
     * 根据ID获取教师信息
     *
     * @param id 教师ID
     * @return 教师实体对象
     */
    Teacher getTeacherById(Long id);

    /**
     * 保存教师信息
     *
     * @param teacherDTO 教师数据传输对象
     * @return 是否保存成功
     */
    boolean saveTeacher(TeacherDTO teacherDTO);

    /**
     * 更新教师信息
     *
     * @param id 教师ID
     * @param teacherDTO 教师数据传输对象
     * @return 是否更新成功
     */
    boolean updateTeacher(Long id, TeacherDTO teacherDTO);

    /**
     * 删除教师信息
     *
     * @param id 教师ID
     * @return 是否删除成功
     */
    boolean deleteTeacher(Long id);
}
