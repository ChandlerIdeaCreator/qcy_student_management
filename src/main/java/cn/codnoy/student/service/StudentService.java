package cn.codnoy.student.service;

import cn.codnoy.student.dto.StudentDTO;
import cn.codnoy.student.dto.StudentQueryDTO;
import cn.codnoy.student.entity.Student;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 学生服务接口
 * 继承自MyBatis-Plus的IService接口，提供学生相关的业务操作方法
 */
public interface StudentService extends IService<Student> {

    /**
     * 查询所有学生列表
     *
     * @return 学生列表
     */
    List<Student> listAll();

    /**
     * 分页查询学生列表
     *
     * @param query 学生查询条件DTO
     * @return 学生分页结果
     */
    IPage<Student> listStudents(StudentQueryDTO query);

    /**
     * 根据ID获取学生信息
     *
     * @param id 学生ID
     * @return 学生实体对象
     */
    Student getStudentById(Long id);

    /**
     * 保存学生信息
     *
     * @param studentDTO 学生数据传输对象
     * @return 是否保存成功
     */
    boolean saveStudent(StudentDTO studentDTO);

    /**
     * 更新学生信息
     *
     * @param id 学生ID
     * @param studentDTO 学生数据传输对象
     * @return 是否更新成功
     */
    boolean updateStudent(Long id, StudentDTO studentDTO);

    /**
     * 删除学生信息
     *
     * @param id 学生ID
     * @return 是否删除成功
     */
    boolean deleteStudent(Long id);
}
