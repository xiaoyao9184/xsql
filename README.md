# xsql


# License

Apache License 2.0


# Do What

just ORM


# How Use

Now in development, don't use in a real project



# 4

| T                | Data           | String             | Object     |
|:--------------:|:--------------:|:------------------:|:----------:|
| Class            | Entity Template    | Entity Agreement Sql Collection  | Class Object |
| Code             | Sentence SqlData  | SqlString             | Object       |

## Class

### Class -> Entity Template

Use `AnnotationEntityDataBuilder` to build element collection `SqlEntityData`.

### Entity Template -> Entity Agreement Sql

Use `DialectEntitySqlBuilder` to build sql string, maybe use `TypeMapper`

You can implements `DialectEntitySqlBuilder` to handle **Statement Group-level** sql **dialect** 

### SqlString -> Object

Use **JDBC** & **RowMapper**

### Class -> SqlString

The **Class** to **String** quick way is use `AnnotationEntitySqlBuilder`,
this use also use `AnnotationEntityTemplateBuilder` to handle **Class** **Data** conversion,
and use `EntitySqlBuilder` to handle **Data** **String** conversion.

If you don't want handle sql **dialect** in `EntitySqlBuilder`,
you can also transfer handle to `SentenceEntitySqlBuilder`, 
to achieve this use `AnnotationEntitySqlBuilder` with config setting **EntityDialectSqlBuildConfig** to `SentenceDialectEntitySqlBuilder` 
    
    
    
## Code

### Code -> SqlData

Use `CodeSentenceDataBuilder` to build element collection `ElementsSentence`.

### SqlData -> SqlString

Use `DialectSentenceSqlBuilder` to build sql string, maybe use `TypeMapper`

You can implements `DialectEntitySqlBuilder` to handle **Statement-level** sql **dialect** 

### SqlString -> Object

Use **JDBC** & **RowMapper**
    
    
    
