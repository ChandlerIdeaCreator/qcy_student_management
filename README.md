# 项目介绍

## 常见问题
- 错误：DELETE FROM classes	Error Code: 1175. You are using safe update mode and you tried to update a table without a WHERE that uses a KEY column.  To disable safe mode, toggle the option in Preferences -> SQL Editor and reconnect.	0.051 sec
- 解决方案
```SQL
-- 执行前先禁用安全模式
SET SQL_SAFE_UPDATES = 0;

-- 执行您的DELETE语句
DELETE FROM classes WHERE 1=1;

-- 执行完再恢复安全模式
SET SQL_SAFE_UPDATES = 1;
```