-- 插入社团数据
INSERT INTO clubs (club_id, name, category, description, founded_date, advisor_id, meeting_time, meeting_place, member_count, status)
VALUES
    ('CLUB001', '计算机协会', '学术', '计算机技术学习与交流社团', '2022-03-15', 'T001', '每周三 18:00-20:00', '逸夫楼502', 45, 1),
    ('CLUB002', '篮球社', '体育', '篮球爱好者社团', '2021-09-10', 'T003', '每周一、周五 16:00-18:00', '东区篮球场', 30, 1),
    ('CLUB003', '摄影协会', '艺术', '摄影技术与艺术交流', '2023-01-20', 'T002', '每周二 17:00-19:00', '艺术学院305', 25, 1);

-- 插入社团成员数据
INSERT INTO club_members (club_id, student_id, position, join_date, is_active)
VALUES
    ('CLUB001', '20230001', '社长', '2022-09-01', 1),
    ('CLUB001', '20230002', '副社长', '2022-09-01', 1),
    ('CLUB001', '20230003', '成员', '2023-03-10', 1),
    ('CLUB002', '20230004', '社长', '2023-02-15', 1),
    ('CLUB002', '20230001', '成员', '2023-03-05', 1);

-- 插入社团活动数据
INSERT INTO club_activities (club_id, title, activity_date, location, description, participant_count, status)
VALUES
    ('CLUB001', '编程马拉松', '2023-04-15 09:00:00', '计算机学院实验室', '24小时编程挑战赛', 30, 2),
    ('CLUB002', '校园篮球联赛', '2023-05-20 14:00:00', '东区篮球场', '春季校园篮球比赛', 50, 1),
    ('CLUB001', '人工智能讲座', '2023-06-10 15:00:00', '学术报告厅', '邀请业界专家讲座', 80, 1);