
# Agreement

1. Not to have full of MSDN
2. Block the array Builder
3. Array-only syntax uses the Item method (for example, the With statement)



# 约定

1. 不与MSDN完全一致
2. 屏蔽数组Builder，含有数组的语句（例如With语句<common_table_expression>的column_name）
3. 仅含数组的语法使用Item方法（例如With语句<common_table_expression>）
4. 由枚举组成的语法，应将枚举抽象为接口（例如Option语句中的<query_option>）
5. 提供静态方法用于快速构建一些简单元素，见下表








# 快速构建方法列表

分为：
- 静态方法
- 实例方法

## 静态方法

| 方法前缀 | 构建目标 |
| ----- | ----- |
| e | Expression
| p | Predicate
| s | Update.SetItem
| t | TableName
| c | ColumnName
| _ | DataType


### Expression

通过一下方法即可创建Expression

| Method | Expression |
| ----- | ----- |
| e | UnknownExpression
| e_null | KeywordExpression
| e_default | KeywordExpression
| e_string | StringConstant
| e_n_string | StringConstant
| e_string_uuid | StringConstant
| e_bin | BinaryConstant
| e_bin_uuid | BinaryConstant
| e_number | NumberConstant
| e_money | NumberConstant
| e_variable | Variable
| e_subquery | GroupExpression
| e_at_time_zone | AtTimeZone
| e_case | Case
| e_coalesce | Coalesce
| e_nullif | NullIf
|
| e_rv* | RowValueExpression
| e_{binary Operator} | GroupExpression


## 实例方法

| 方法前缀 | 方法后缀 | 构建目标 |
| ----- | ----- |
| with | | 设置本级属性
| $ | | 同with方法
| $_ | | 设置子级属性（通常会连带初始化本级属性）
| $__ | | 设置孙级属性（通常会连带初始化本级、子级属性）
| _ |  | 设置本级实现，并进入（通常本级为抽象）
|  | _ | 

组合型
| 方法前缀 | 方法后缀 | 构建目标 |
| ----- | ----- |
| $ | _ | 设置本级属性，并返回上级（with，and）
| _ | $ | 
| $ | _ | 
| _ | $ | 



**例如** _TableTypeDefinition 语句_
```java
TableTypeDefinition quick = new TableTypeDefinitionBuilder<Void>()
    .withColumnDefinition()
        .withColumnName(c("EmpID"))
        .withDataType(_int())
        .and()
    .$_(
            c_int("OldVacationHours"),
            c_int("NewVacationHours"),
            c_datetime("ModifiedDate"))
    .$_PrimaryKey(c("EmpID"))
    .$_Unique(c("ModifiedDate"))
    .build();

```