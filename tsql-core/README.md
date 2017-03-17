
# Agreement

1. Not to have full of MSDN
2. Block the array Builder
3. Array-only syntax uses the Item method (for example, the With statement)



# 约定

1. 不与MSDN完全一致
2. 屏蔽数组Builder，含有数组的语句（例如With语句<common_table_expression>的column_name）
3. 仅含数组的语法使用Item方法（例如With语句<common_table_expression>）
4. 提供静态方法用于快速构建一些简单元素，见下表








# 快速构建方法列表

| 方法前缀 | 构建目标 |
| ----- | ----- |
| e | Expression
| p | Predicate
| t | TableName
| c | ColumnName
| _ | DataType