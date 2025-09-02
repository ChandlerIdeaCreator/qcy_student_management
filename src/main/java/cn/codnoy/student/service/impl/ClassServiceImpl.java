package cn.codnoy.student.service.impl;

import cn.codnoy.student.dto.ClassDTO;
import cn.codnoy.student.dto.ClassQueryDTO;
import cn.codnoy.student.entity.Class;
import cn.codnoy.student.entity.Teacher;
import cn.codnoy.student.mapper.ClassMapper;
import cn.codnoy.student.service.ClassService;
import cn.codnoy.student.service.TeacherService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 班级服务实现类
 * 提供班级相关的业务逻辑处理
 */
@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements ClassService {

    @Autowired
    private TeacherService teacherService;

    /**
     * 根据ID获取班级信息
     *
     * @param id 班级ID
     * @return 班级实体对象
     */
    @Override
    public Class getClassById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 分页查询班级列表
     *
     * @param query 班级查询条件DTO
     * @return 班级分页结果
     */
    @Override
    public IPage<Class> listClasses(ClassQueryDTO query) {
        Page<Class> page = new Page<>(query.getPageNum(), query.getPageSize());
        return baseMapper.selectByCondition(page, query);
    }

    /**
     * 保存班级信息
     *
     * @param classDTO 班级数据传输对象
     * @return 是否保存成功
     */
    @Override
    @Transactional
    public boolean saveClass(ClassDTO classDTO) {
        // 检查班主任是否存在
        if (classDTO.getHeadTeacher() != null) {
            Teacher teacher = teacherService.lambdaQuery()
                    .eq(Teacher::getName, classDTO.getHeadTeacher())
                    .one();
            if (teacher == null) {
                throw new RuntimeException("指定的班主任不存在");
            }
        }

        Class clazz = new Class();
        BeanUtils.copyProperties(classDTO, clazz);
        clazz.setStudentCount(0); // 新建班级学生数默认为0
        return this.save(clazz);
    }

    /**
     * 更新班级信息
     *
     * @param id 班级ID
     * @param classDTO 班级数据传输对象
     * @return 是否更新成功
     */
    @Override
    @Transactional
    public boolean updateClass(Long id, ClassDTO classDTO) {
        // 检查班主任是否存在
        if (classDTO.getHeadTeacher() != null) {
            Teacher teacher = teacherService.lambdaQuery()
                    .eq(Teacher::getName, classDTO.getHeadTeacher())
                    .one();
            if (teacher == null) {
                throw new RuntimeException("指定的班主任不存在");
            }
        }

        Class clazz = this.getById(id);
        if (clazz == null) {
            return false;
        }
        BeanUtils.copyProperties(classDTO, clazz);
        return this.updateById(clazz);
    }

    /**
     * 删除班级信息
     *
     * @param id 班级ID
     * @return 是否删除成功
     */
    @Override
    @Transactional
    public boolean deleteClass(Long id) {
        // 检查班级是否有学生
        Class clazz = this.getById(id);
        if (clazz != null && clazz.getStudentCount() > 0) {
            throw new RuntimeException("班级中还有学生，不能删除");
        }
        return this.removeById(id);
    }

    /**
     * 更新班级学生人数
     *
     * @param classId 班级ID
     * @param count 变更数量（正数增加，负数减少）
     * @return 是否更新成功
     */
    @Override
    @Transactional
    public boolean updateStudentCount(String classId, int count) {
        return baseMapper.updateStudentCount(classId, count) > 0;
    }
}
