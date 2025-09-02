package cn.codnoy.student.service.impl;

import cn.codnoy.student.dto.StudentDTO;
import cn.codnoy.student.dto.StudentQueryDTO;
import cn.codnoy.student.entity.Student;
import cn.codnoy.student.mapper.StudentMapper;
import cn.codnoy.student.service.ClassService;
import cn.codnoy.student.service.StudentService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

import java.util.Date;
import java.util.List;

/**
 * 学生服务实现类
 * 提供学生相关的业务逻辑处理
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {//代码复用的模板

    @Autowired
    private ClassService classService;

    /**
     * 查询所有学生列表
     *
     * @return 学生列表
     */
    @Override
    public List<Student> listAll() {
        return baseMapper.selectList(null);
    }

    /**
     * 根据ID获取学生信息
     *
     * @param id 学生ID
     * @return 学生实体对象
     */
    @Override
    public Student getStudentById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 分页查询学生列表
     *
     * @param query 学生查询条件DTO
     * @return 学生分页结果
     */
    @Override
    public IPage<Student> listStudents(StudentQueryDTO query) {
        Page<Student> page = new Page<>(query.getPageNum(), query.getPageSize());
        return baseMapper.selectByCondition(page, query);
    }

    /**
     * 保存学生信息
     *
     * @param studentDTO 学生数据传输对象
     * @return 是否保存成功
     */
    @Override
    public boolean saveStudent(StudentDTO studentDTO) {
        Student student = new Student();
        BeanUtils.copyProperties(studentDTO, student);
        student.setEnrollmentDate(LocalDate.now()); // 设置入学日期为当前时间
        boolean result = this.save(student);

        // 增加班级人数
        if (result && studentDTO.getClassId() != null) {
            classService.updateStudentCount(studentDTO.getClassId(), 1);
        }
        return result;
    }

    /**
     * 更新学生信息
     *
     * @param id 学生ID
     * @param studentDTO 学生数据传输对象
     * @return 是否更新成功
     */
    @Override
    public boolean updateStudent(Long id, StudentDTO studentDTO) {
        Student oldStudent = this.getById(id);
        if (oldStudent == null) {
            return false;
        }

        Student student = new Student();
        BeanUtils.copyProperties(studentDTO, student);
        student.setId(id);
        boolean result = this.updateById(student);

        // 处理班级人数变化
        if (result) {
            String oldClassId = oldStudent.getClassId();
            String newClassId = studentDTO.getClassId();

            if (oldClassId != null && !oldClassId.equals(newClassId)) {
                // 原班级人数减1
                classService.updateStudentCount(oldClassId, -1);
                // 新班级人数加1
                if (newClassId != null) {
                    classService.updateStudentCount(newClassId, 1);
                }
            }
        }
        return result;
    }

    /**
     * 删除学生信息
     *
     * @param id 学生ID
     * @return 是否删除成功
     */
    @Override
    @Transactional
    public boolean deleteStudent(Long id) {
        Student student = this.getById(id);
        if (student == null) {
            return false;
        }

        boolean result = this.removeById(id);

        // 减少班级人数
        if (result && student.getClassId() != null) {
            classService.updateStudentCount(student.getClassId(), -1);
        }
        return result;
    }
}
