# xsql


# License

Apache License 2.0


# Do What

just ORM


# How Use

Now in development, don't use in a real project



# 4

Class --------------> SqlData --------------> SqlString --------------> Object

## Class --------------> SqlData

SqlDataBuilder.build
    Annotation                 Element List
    Code
        
## SqlData --------------> SqlString

SqlDialectBuilder.build
         + TypeMapper + ??

## SqlString --------------> Object

    JDBC + RowMapper
