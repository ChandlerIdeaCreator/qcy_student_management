package cn.codnoy.student.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

/**
 * 学生数据传输对象
 * 用于学生信息的创建和更新操作
 */
@Data
public class StudentDTO {
    /**
     * 学号，不能为空
     */
    @NotBlank(message = "学号不能为空")
    private String studentId;

    /**
     * 姓名，不能为空，长度必须在2-20个字符之间
     */
    @NotBlank(message = "姓名不能为空")
    @Size(min = 2, max = 20, message = "姓名长度必须在2-20个字符之间")
    private String name;

    /**
     * 性别，只能是"男"或"女"
     */
    @Pattern(regexp = "^[男女]$", message = "性别只能是男或女")
    private String gender;

    /**
     * 出生日期，必须是过去的时间
     */
    @Past(message = "出生日期必须是过去的时间")
    private Date birthDate;

    /**
     * 班级ID，不能为空
     */
    @NotBlank(message = "班级ID不能为空")
    private String classId;

    /**
     * 邮箱，必须符合邮箱格式
     */
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 手机号，必须符合手机号格式
     */
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;
}
