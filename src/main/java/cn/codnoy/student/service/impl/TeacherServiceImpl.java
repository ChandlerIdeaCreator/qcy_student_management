package cn.codnoy.student.service.impl;

import cn.codnoy.student.dto.TeacherDTO;
import cn.codnoy.student.dto.TeacherQueryDTO;
import cn.codnoy.student.entity.Teacher;
import cn.codnoy.student.mapper.TeacherMapper;
import cn.codnoy.student.service.TeacherService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 教师服务实现类
 * 提供教师相关的业务逻辑处理
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    /**
     * 根据ID获取教师信息
     *
     * @param id 教师ID
     * @return 教师实体对象
     */
    @Override
    public Teacher getTeacherById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 分页查询教师列表
     *
     * @param query 教师查询条件DTO
     * @return 教师分页结果
     */
    @Override
    public IPage<Teacher> listTeachers(TeacherQueryDTO query) {
        Page<Teacher> page = new Page<>(query.getPageNum(), query.getPageSize());
        return baseMapper.selectByCondition(page, query);
    }

    /**
     * 保存教师信息
     *
     * @param teacherDTO 教师数据传输对象
     * @return 是否保存成功
     */
    @Override
    @Transactional
    public boolean saveTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacherDTO, teacher);
        if (teacher.getHireDate() == null) {
            teacher.setHireDate(new Date()); // 默认入职日期为当前时间
        }
        return this.save(teacher);
    }

    /**
     * 更新教师信息
     *
     * @param id 教师ID
     * @param teacherDTO 教师数据传输对象
     * @return 是否更新成功
     */
    @Override
    @Transactional
    public boolean updateTeacher(Long id, TeacherDTO teacherDTO) {
        Teacher teacher = this.getById(id);
        if (teacher == null) {
            return false;
        }
        BeanUtils.copyProperties(teacherDTO, teacher);
        return this.updateById(teacher);
    }

    /**
     * 删除教师信息
     *
     * @param id 教师ID
     * @return 是否删除成功
     */
    @Override
    @Transactional
    public boolean deleteTeacher(Long id) {
        // 检查教师是否担任班主任或教授课程
        Teacher teacher = this.getById(id);
        if (teacher != null) {
            // TODO: 实际项目中需要检查教师关联关系
        }
        return this.removeById(id);
    }
}
