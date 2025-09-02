package cn.codnoy.student.service.impl;

import cn.codnoy.student.dto.TeacherAssignmentDTO;
import cn.codnoy.student.dto.TeacherAssignmentQueryDTO;
import cn.codnoy.student.entity.TeacherAssignment;
import cn.codnoy.student.mapper.TeacherAssignmentMapper;
import cn.codnoy.student.service.TeacherAssignmentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * 教师授课安排服务实现类
 * 提供教师授课安排相关的业务逻辑处理
 */
@Service
public class TeacherAssignmentServiceImpl extends ServiceImpl<TeacherAssignmentMapper, TeacherAssignment>
        implements TeacherAssignmentService {

    /**
     * 分页查询授课安排列表
     *
     * @param query 授课安排查询条件DTO
     * @return 授课安排分页结果
     */
    @Override
    public IPage<TeacherAssignment> listTeachingAssignments(TeacherAssignmentQueryDTO query) {
        Page<TeacherAssignment> page = new Page<>(query.getPageNum(), query.getPageSize());

        LambdaQueryWrapper<TeacherAssignment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(query.getTeacherId() != null, TeacherAssignment::getTeacherId, query.getTeacherId())
                .eq(query.getCourseId() != null, TeacherAssignment::getCourseId, query.getCourseId())
                .eq(StringUtils.hasText(query.getSemester()), TeacherAssignment::getSemester, query.getSemester())
                .orderByAsc(TeacherAssignment::getSemester)
                .orderByAsc(TeacherAssignment::getClassroom);

        return baseMapper.selectPage(page, wrapper);
    }

    /**
     * 根据ID获取授课安排信息
     *
     * @param id 授课安排ID
     * @return 授课安排实体对象
     */
    @Override
    public TeacherAssignment getTeachingAssignmentById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 保存授课安排信息
     *
     * @param assignmentDTO 授课安排数据传输对象
     * @return 是否保存成功
     */
    @Override
    @Transactional
    public boolean saveTeachingAssignment(TeacherAssignmentDTO assignmentDTO) {
        // 检查教师是否已存在同一时间段的授课安排
        if (baseMapper.checkTeacherScheduleConflict(
                assignmentDTO.getTeacherId(),
                assignmentDTO.getSemester(),
                assignmentDTO.getSchedule(),
                null) > 0) {
            throw new RuntimeException("教师在该时间段已有其他授课安排");
        }

        // 检查教室是否已被占用
        if (baseMapper.checkClassroomConflict(
                assignmentDTO.getClassroom(),
                assignmentDTO.getSemester(),
                assignmentDTO.getSchedule(),
                null) > 0) {
            throw new RuntimeException("教室在该时间段已被占用");
        }

        TeacherAssignment assignment = new TeacherAssignment();
        BeanUtils.copyProperties(assignmentDTO, assignment);

        return this.save(assignment);
    }

    /**
     * 更新授课安排信息
     *
     * @param id 授课安排ID
     * @param assignmentDTO 授课安排数据传输对象
     * @return 是否更新成功
     */
    @Override
    @Transactional
    public boolean updateTeachingAssignment(Long id, TeacherAssignmentDTO assignmentDTO) {
        TeacherAssignment existingAssignment = getById(id);
        if (existingAssignment == null) {
            throw new RuntimeException("授课安排不存在");
        }

        // 检查教师是否已存在同一时间段的授课安排（排除当前记录）
        if (baseMapper.checkTeacherScheduleConflict(
                assignmentDTO.getTeacherId(),
                assignmentDTO.getSemester(),
                assignmentDTO.getSchedule(),
                id) > 0) {
            throw new RuntimeException("教师在该时间段已有其他授课安排");
        }

        // 检查教室是否已被占用（排除当前记录）
        if (baseMapper.checkClassroomConflict(
                assignmentDTO.getClassroom(),
                assignmentDTO.getSemester(),
                assignmentDTO.getSchedule(),
                id) > 0) {
            throw new RuntimeException("教室在该时间段已被占用");
        }

        TeacherAssignment assignment = new TeacherAssignment();
        BeanUtils.copyProperties(assignmentDTO, assignment);
        assignment.setId(id);

        return this.updateById(assignment);
    }

    /**
     * 删除授课安排信息
     *
     * @param id 授课安排ID
     * @return 是否删除成功
     */
    @Override
    @Transactional
    public boolean deleteTeachingAssignment(Long id) {
        TeacherAssignment assignment = getById(id);
        if (assignment == null) {
            throw new RuntimeException("授课安排不存在");
        }

        // 检查是否已有学生成绩关联此授课安排
        if (baseMapper.checkIfHasScores(id) > 0) {
            throw new RuntimeException("该授课安排已有学生成绩记录，无法删除");
        }

        return this.removeById(id);
    }
}
