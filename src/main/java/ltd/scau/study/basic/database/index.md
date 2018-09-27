# 索引
### 对表一列或多列值进行排序，提高查找性能

MySQL
```
create index idx_birth on students(birthday);
explain select * from students s where birthday between unix_timestamp('1997-01-07') * 1000 and unix_timestamp('1997-01-08') * 1000;
explain select from_unixtime(birthday/1000) from students where stu_id='201525050420';
drop index idx_birth on students;
```
类型
* BTree
  * 存储列值，可以覆盖索引
* Hash
  * 不存储列值
  
全文索引
* 不存储列值

覆盖索引
* 要求索引存储列值
* 查询的字段均为索引字段
  * stu_id 为索引字段
  * 查询的stu_name不是索引，未覆盖，需要回表：explain select stu_id, stu_name from students where stu_id like '%250504%';
  * 查询stu_id，只查索引不用回表：explain select stu_id from students where stu_id like '%250504%';
  * birthday也是索引字段，不用回表：explain select stu_id, birthday from students where stu_id like '%250504%';




正排索引
* 以文档ID查找文档

倒排索引
* 关键词映射到文档 keyword -> document

索引失效
* 使用 or 连接的条件有非索引列（使用 or 利用索引，要所有条件列都是索引列）
* 对字符串 like 时，"%str%"会失效，"str%"可以使用索引（索引就是个排序的结构，在字符串按照字典序，可以理解）（解决方案：覆盖索引）
* 索引不存储null值
* 不对索引列做任何操作，比如 left(str, 1), i + 5

