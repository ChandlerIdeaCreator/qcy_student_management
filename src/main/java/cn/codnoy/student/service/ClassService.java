package cn.codnoy.student.service;

import cn.codnoy.student.dto.ClassDTO;
import cn.codnoy.student.dto.ClassQueryDTO;
import cn.codnoy.student.entity.Class;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 班级服务接口
 * 继承自IService<Class>，提供班级相关的业务操作方法
 */
public interface ClassService extends IService<Class> {

    /**
     * 分页查询班级列表
     *
     * @param query 班级查询条件DTO
     * @return 班级分页结果
     */
    IPage<Class> listClasses(ClassQueryDTO query);

    /**
     * 根据ID获取班级信息
     *
     * @param id 班级ID
     * @return 班级实体对象
     */
    Class getClassById(Long id);

    /**
     * 保存班级信息
     *
     * @param classDTO 班级数据传输对象
     * @return 是否保存成功
     */
    boolean saveClass(ClassDTO classDTO);

    /**
     * 更新班级信息
     *
     * @param id 班级ID
     * @param classDTO 班级数据传输对象
     * @return 是否更新成功
     */
    boolean updateClass(Long id, ClassDTO classDTO);

    /**
     * 删除班级信息
     *
     * @param id 班级ID
     * @return 是否删除成功
     */
    boolean deleteClass(Long id);

    /**
     * 更新班级学生数量
     *
     * @param classId 班级ID
     * @param count 学生数量
     * @return 是否更新成功
     */
    boolean updateStudentCount(String classId, int count);
}
