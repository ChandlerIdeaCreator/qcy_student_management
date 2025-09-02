-- 创建数据库并指定编码
CREATE DATABASE IF NOT EXISTS school_management
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_unicode_ci;
USE school_management;   //

-- 班级表(classes) - 存储班级信息
DROP TABLE IF EXISTS classes;
CREATE TABLE classes (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    class_id VARCHAR(20) COMMENT '班级编号',
    class_name VARCHAR(50) NOT NULL COMMENT '班级名称',
    grade INT NOT NULL COMMENT '年级，如1表示一年级',
    head_teacher VARCHAR(50) COMMENT '班主任',
    classroom VARCHAR(20) COMMENT '固定教室',
    student_count INT DEFAULT 0 COMMENT '班级人数',
    deleted TINYINT DEFAULT 0 COMMENT '删除标识：0-未删除，1-已删除',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_class_id (class_id),
    KEY idx_class_name (class_name),
    KEY idx_grade (grade)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT = '班级信息表';

-- 学生表(students) - 存储学生基本信息
DROP TABLE IF EXISTS students;
CREATE TABLE students (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    student_id VARCHAR(20) COMMENT '学号',
    name VARCHAR(50) NOT NULL COMMENT '学生姓名',
    gender CHAR(1) NOT NULL COMMENT '性别：男/女',
    birth_date DATE COMMENT '出生日期',
    class_id VARCHAR(20) COMMENT '班级编号',
    enrollment_date DATE NOT NULL COMMENT '入学日期',
    address TEXT COMMENT '家庭住址',
    phone VARCHAR(15) COMMENT '联系电话',
    email VARCHAR(50) COMMENT '电子邮箱',
    id_card VARCHAR(18) COMMENT '身份证号',
    status TINYINT DEFAULT 1 COMMENT '学生状态：1-在读，2-休学，3-退学，4-毕业',
    deleted TINYINT DEFAULT 0 COMMENT '删除标识：0-未删除，1-已删除',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_student_id (student_id),      //no repeat
    KEY idx_name (name),
    KEY idx_class_id (class_id),
    KEY idx_status (status),
    KEY idx_enrollment_date (enrollment_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT = '学生信息表';

-- 教师表(teachers) - 存储教师信息
DROP TABLE IF EXISTS teachers;
CREATE TABLE teachers (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    teacher_id VARCHAR(20) COMMENT '教师工号',
    name VARCHAR(50) NOT NULL COMMENT '教师姓名',
    gender CHAR(1) COMMENT '性别：男/女',
    birth_date DATE COMMENT '出生日期',
    title VARCHAR(20) COMMENT '职称',
    department VARCHAR(50) COMMENT '所属院系',
    hire_date DATE COMMENT '入职日期',
    phone VARCHAR(15) COMMENT '联系电话',
    email VARCHAR(50) COMMENT '电子邮箱',
    deleted TINYINT DEFAULT 0 COMMENT '删除标识：0-未删除，1-已删除',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_teacher_id (teacher_id),
    KEY idx_name (name),
    KEY idx_department (department),
    KEY idx_title (title)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT = '教师信息表';

-- 课程表(courses) - 存储课程信息
DROP TABLE IF EXISTS courses;
CREATE TABLE courses (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    course_id VARCHAR(20) COMMENT '课程编号',
    course_name VARCHAR(100) NOT NULL COMMENT '课程名称',
    credit DECIMAL(3,1) NOT NULL COMMENT '学分',
    teacher_id VARCHAR(20) COMMENT '授课教师ID',
    class_hours INT COMMENT '课时',
    course_type VARCHAR(20) COMMENT '课程类型：必修/选修',
    description TEXT COMMENT '课程描述',
    deleted TINYINT DEFAULT 0 COMMENT '删除标识：0-未删除，1-已删除',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_course_id (course_id),
    KEY idx_course_name (course_name),
    KEY idx_teacher_id (teacher_id),
    KEY idx_course_type (course_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT = '课程信息表';

-- 教师授课安排(teacher_assignments) - 存储教师授课安排信息
DROP TABLE IF EXISTS teacher_assignments;
CREATE TABLE teacher_assignments (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    teacher_id VARCHAR(20) COMMENT '教师ID',
    course_id VARCHAR(20) DEFAULT NULL COMMENT '课程ID',
    semester VARCHAR(30) COMMENT '学期，如2023-2024-1',
    classroom VARCHAR(30) COMMENT '上课教室',
    schedule VARCHAR(30) COMMENT '上课时间安排',
    deleted TINYINT DEFAULT 0 COMMENT '删除标识：0-未删除，1-已删除',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    KEY idx_teacher_id (teacher_id),
    KEY idx_course_id (course_id),
    KEY idx_semester (semester)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT = '教师授课安排表';

-- 成绩表(scores) - 存储学生成绩
DROP TABLE IF EXISTS scores;
CREATE TABLE scores (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    student_id VARCHAR(20) NOT NULL COMMENT '学生ID',
    course_id VARCHAR(20) NOT NULL COMMENT '课程ID',
    score DECIMAL(5,2) COMMENT '成绩',
    credit_earned DECIMAL(3,1) COMMENT '获得学分',
    semester VARCHAR(20) NOT NULL COMMENT '学期，如2023-2024-1',
    exam_date DATE COMMENT '考试日期',
    remark VARCHAR(100) COMMENT '备注',
    deleted TINYINT DEFAULT 0 COMMENT '删除标识：0-未删除，1-已删除',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_student_course_semester (student_id, course_id, semester),
    KEY idx_student_id (student_id),
    KEY idx_course_id (course_id),
    KEY idx_semester (semester),
    KEY idx_score (score)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT = '成绩信息表';
