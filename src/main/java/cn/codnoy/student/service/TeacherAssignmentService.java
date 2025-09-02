package cn.codnoy.student.service;

import cn.codnoy.student.dto.TeacherAssignmentDTO;
import cn.codnoy.student.dto.TeacherAssignmentQueryDTO;
import cn.codnoy.student.entity.TeacherAssignment;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 教师任课服务接口
 * 提供教师任课相关的业务操作方法
 */
public interface TeacherAssignmentService {

    /**
     * 分页查询教师任课列表
     *
     * @param query 教师任课查询条件DTO
     * @return 教师任课分页结果
     */
    IPage<TeacherAssignment> listTeachingAssignments(TeacherAssignmentQueryDTO query);

    /**
     * 根据ID获取教师任课信息
     *
     * @param id 教师任课ID
     * @return 教师任课实体对象
     */
    TeacherAssignment getTeachingAssignmentById(Long id);

    /**
     * 保存教师任课信息
     *
     * @param assignmentDTO 教师任课数据传输对象
     * @return 是否保存成功
     */
    boolean saveTeachingAssignment(TeacherAssignmentDTO assignmentDTO);

    /**
     * 更新教师任课信息
     *
     * @param id 教师任课ID
     * @param assignmentDTO 教师任课数据传输对象
     * @return 是否更新成功
     */
    boolean updateTeachingAssignment(Long id, TeacherAssignmentDTO assignmentDTO);

    /**
     * 删除教师任课信息
     *
     * @param id 教师任课ID
     * @return 是否删除成功
     */
    boolean deleteTeachingAssignment(Long id);
}
