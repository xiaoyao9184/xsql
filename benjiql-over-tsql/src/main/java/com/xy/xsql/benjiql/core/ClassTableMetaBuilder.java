package com.xy.xsql.benjiql.core;

import com.xy.xsql.benjiql.util.Conventions;
import com.xy.xsql.core.builder.BaseBuilder;
import com.xy.xsql.core.mapper.SourceTargetMapper;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.TableName;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by xiaoyao9184 on 2017/7/27.
 */
public class ClassTableMetaBuilder
        implements BaseBuilder<Class,ClassTableMetaBuilder> {

    private Class clazz;
    private SourceTargetMapper<Method,ColumnName> methodColumnNameMapper;
    private TableName tableName;



    @Override
    public ClassTableMetaBuilder build(Class aClass) {
        this.clazz = aClass;
        buildTable();
        buildColumn();
        return this;
    }

    public ClassTableMetaBuilder buildTable(){
        String name = Conventions.toDbName(this.clazz);
        TableName tableName = new TableName();
        tableName.setTableOrViewName(name);
        this.tableName = tableName;
        return this;
    }

    public ClassTableMetaBuilder buildColumn(){
        this.methodColumnNameMapper = new SourceTargetMapper<>();
        Stream.of(this.clazz.getMethods())
                .forEach(method -> {
                    String name = Conventions.toDbName(method.getName());
                    ColumnName columnName = new ColumnName();
                    columnName.setTable(this.tableName);
                    columnName.setName(name);
                    this.methodColumnNameMapper.map(method,columnName);
                });
        return this;
    }




    public TableName getTableName() {
        return tableName;
    }

    public ColumnName getColumnName(Method method) {
        return methodColumnNameMapper.getByLeft(method);
    }

    public List<ColumnName> getColumnNames() {
        return new ArrayList<>(methodColumnNameMapper.getRight());
    }
}
