-- 1. 社团表基本结构 (clubs)

CREATE TABLE clubs (
    club_id VARCHAR(20) PRIMARY KEY COMMENT '社团编号',
    name VARCHAR(100) NOT NULL COMMENT '社团名称',
    category VARCHAR(50) NOT NULL COMMENT '社团类别(学术/艺术/体育/公益等)',
    description TEXT COMMENT '社团描述',
    founded_date DATE NOT NULL COMMENT '成立日期',
    advisor_id VARCHAR(20) COMMENT '指导老师工号',
    meeting_time VARCHAR(100) COMMENT '常规活动时间',
    meeting_place VARCHAR(100) COMMENT '常规活动地点',
    member_count INT DEFAULT 0 COMMENT '成员人数',
    status TINYINT DEFAULT 1 COMMENT '状态(1-活跃 2-暂停 3-已解散)',
    logo_url VARCHAR(255) COMMENT '社团LogoURL',
    wechat_group VARCHAR(100) COMMENT '微信群号',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (advisor_id) REFERENCES teachers(teacher_id) ON DELETE SET NULL
) COMMENT '社团基本信息表';


-- 2. 社团成员表 (club_members)

CREATE TABLE club_members (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '记录ID',
    club_id VARCHAR(20) NOT NULL COMMENT '社团编号',
    student_id VARCHAR(20) NOT NULL COMMENT '学号',
    position VARCHAR(50) COMMENT '职位(社长/副社长/普通成员等)',
    join_date DATE NOT NULL COMMENT '加入日期',
    is_active TINYINT(1) DEFAULT 1 COMMENT '是否活跃成员(1-是 0-否)',
    contribution TEXT COMMENT '主要贡献',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (club_id) REFERENCES clubs(club_id) ON DELETE CASCADE,
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    UNIQUE KEY (club_id, student_id) COMMENT '避免重复加入'
) COMMENT '社团成员关系表';

-- 3. 社团活动表 (club_activities)

CREATE TABLE club_activities (
    activity_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '活动ID',
    club_id VARCHAR(20) NOT NULL COMMENT '社团编号',
    title VARCHAR(200) NOT NULL COMMENT '活动标题',
    activity_date DATETIME NOT NULL COMMENT '活动时间',
    location VARCHAR(100) NOT NULL COMMENT '活动地点',
    description TEXT COMMENT '活动描述',
    participant_count INT DEFAULT 0 COMMENT '参与人数',
    budget DECIMAL(10,2) COMMENT '活动预算',
    status TINYINT DEFAULT 1 COMMENT '状态(1-计划中 2-已举办 3-已取消)',
    photos_url TEXT COMMENT '活动照片URL(JSON数组)',
    created_by VARCHAR(20) COMMENT '创建人(学号或工号)',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (club_id) REFERENCES clubs(club_id) ON DELETE CASCADE
) COMMENT '社团活动记录表';


-- 4. 社团申请记录表 (club_applications)

CREATE TABLE club_applications (
    application_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '申请ID',
    club_id VARCHAR(20) NOT NULL COMMENT '社团编号',
    student_id VARCHAR(20) NOT NULL COMMENT '申请人学号',
    application_date DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '申请时间',
    motivation TEXT COMMENT '申请动机',
    skills TEXT COMMENT '相关技能',
    status TINYINT DEFAULT 0 COMMENT '状态(0-待审核 1-已通过 2-已拒绝)',
    processed_by VARCHAR(20) COMMENT '处理人(学号或工号)',
    processed_at DATETIME COMMENT '处理时间',
    feedback TEXT COMMENT '审核反馈',
    FOREIGN KEY (club_id) REFERENCES clubs(club_id) ON DELETE CASCADE,
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE
) COMMENT '社团加入申请表';


-- 5. 社团财务记录表 (club_finances)

CREATE TABLE club_finances (
    record_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '记录ID',
    club_id VARCHAR(20) NOT NULL COMMENT '社团编号',
    transaction_type TINYINT NOT NULL COMMENT '类型(1-收入 2-支出)',
    amount DECIMAL(10,2) NOT NULL COMMENT '金额',
    transaction_date DATE NOT NULL COMMENT '交易日期',
    description VARCHAR(255) NOT NULL COMMENT '用途描述',
    category VARCHAR(50) COMMENT '收支类别',
    proof_url VARCHAR(255) COMMENT '凭证URL',
    recorded_by VARCHAR(20) NOT NULL COMMENT '记录人(学号或工号)',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    FOREIGN KEY (club_id) REFERENCES clubs(club_id) ON DELETE CASCADE
) COMMENT '社团财务记录表';
