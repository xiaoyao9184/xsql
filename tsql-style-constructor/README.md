
# 约定

语句拆分成多个部分（子语句），每个部分（子语句）一般由一个关键字开始。
构建整个语句需要对每个部分（子语句）单独构建。
部分（子语句）可能由一个关键字开始，也可能由参数开始。
部分（子语句）可省略，部分（子语句）可重复

1. 使用静态方法生产语句构造器

    > `<TOP>`
    https://docs.microsoft.com/en-us/sql/t-sql/queries/top-transact-sql?view=sql-server-2017
    > ```
    > TOP (expression) [PERCENT]  
    >    [ WITH TIES ]
    > ```
    > 1个必填参数 2个可选参数，排列组合后的方法：
    > 1. TOP TOP(Expression countExpression)
    > 2. TOP TOP(Expression countExpression, PERCENT percent)
    > 3. TOP TOP(Expression countExpression, WITH_TIES with_ties)
    > 4. TOP TOP(Expression countExpression, PERCENT percent, WITH_TIES with_ties)
    
2. 语句中的子部分通过，也通过静态方法生产部分构造器

    > `<DELETE>`
    > ```java
    > static DELETE DELETE(
    >             TOP top,
    >             DELETE.FROM_ from_,
    >             String table_alias,
    >             DELETE.WITH with,
    >             OUTPUT output,
    >             FROM from,
    >             WHERE where,
    >             OPTION option)
    > ```
    > 对于DELETE语句构造，需要调用此方法传入TOP部分（子语句）构造器，
    > 我们通过复用`<TOP>`构造器的静态方法直接获取
    > ```java
    > builder = DELETE(
    >             TOP(5),
    >             ...
    >             )
    > ```
3. 对于部分（子语句）可省略则通过增加重载静态方法

    > `<TOP>`
    > 1个必填参数 2个可选参数，排列组合后的方法：
    > 1. TOP TOP(Expression countExpression)
    > 2. TOP TOP(Expression countExpression, PERCENT percent)
    > 3. TOP TOP(Expression countExpression, WITH_TIES with_ties)
    > 4. TOP TOP(Expression countExpression, PERCENT percent, WITH_TIES with_ties)
    
4. 对于可重复的部分（子语句），通过在部分（子语句）构造器中加入`$$`方法（无关键字）或关键字方法以便支持多个
实际上部分（子语句）构造器就是集合构造器

    > `<OUTPUT>`
    https://docs.microsoft.com/en-us/sql/t-sql/queries/output-clause-transact-sql?view=sql-server-2017
    > 
    > OUTPUT <dml_select_list>
    >
    > ```java
    > OUTPUT(
    >     $action()
    >     .INSERTED("ProductID")
    >     .INSERTED("LocationID")
    >     .INSERTED("Quantity",AS("NewQty"))
    >     .DELETED("Quantity",AS("PreviousQty"))
    > )
    > ```
    
    > `<VALUES>`
    https://docs.microsoft.com/en-us/sql/t-sql/queries/table-value-constructor-transact-sql?view=sql-server-2017
    > 
    > VALUES ( <row value expression list> ) [ ,...n ]  
    >
    > ```java
    > VALUES(
    >     e_number(1),e_number(2)
    > ).$$(
    >     e_number(3),e_number(4)
    > ).$$(
    >     e_number(5),e_number(6)
    > ).$$(
    >     e_number(7),e_number(8)
    > ).$$(
    >     e_number(9),e_number(10)
    > )
    > ```
