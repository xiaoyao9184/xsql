package com.xy.xsql.benjiql.core;

import com.xy.xsql.benjiql.ddl.JoinTables;
import com.xy.xsql.benjiql.util.Conventions;
import com.xy.xsql.core.builder.BaseBuilder;
import com.xy.xsql.core.mapper.SourceTargetMapper;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.TableName;

import java.lang.reflect.Method;
import java.util.stream.Stream;

/**
 * Created by xiaoyao9184 on 2017/7/27.
 */
public class RelationshipClassTableMetaBuilder
        implements BaseBuilder<JoinTables,RelationshipClassTableMetaBuilder> {

    private JoinTables joinTables;
    private TableName tableName;
    private SourceTargetMapper<Method,ColumnName> leftMethodColumnNameMapper;
    private SourceTargetMapper<Method,ColumnName> rightMethodColumnNameMapper;

    @Override
    public RelationshipClassTableMetaBuilder build(JoinTables joinTables) {
        this.joinTables = joinTables;
        buildTable();
        buildColumn();
        return this;
    }

    public RelationshipClassTableMetaBuilder buildTable(){
        String name = joinTables.isInverted() ?
                Conventions.toDbName(joinTables.getRightTable(), joinTables.getLeftTable()) :
                Conventions.toDbName(joinTables.getLeftTable(), joinTables.getRightTable());

        TableName tableName = new TableName();
        tableName.setTableOrViewName(name);
        this.tableName = tableName;
        return this;
    }

    public RelationshipClassTableMetaBuilder buildColumn(){
        this.leftMethodColumnNameMapper = new SourceTargetMapper<>();
        Stream.of(this.joinTables.getLeftTable().getMethods())
                .forEach(method -> {
                    String name = Conventions.toDbName(this.joinTables.leftTable.getSimpleName()) +
                            "_" +
                             Conventions.toDbName(method.getName());
                    ColumnName columnName = new ColumnName();
                    columnName.setTable(this.tableName);
                    columnName.setName(name);
                    this.leftMethodColumnNameMapper.map(method,columnName);
                });
        this.rightMethodColumnNameMapper = new SourceTargetMapper<>();
        Stream.of(this.joinTables.getRightTable().getMethods())
                .forEach(method -> {
                    String name = Conventions.toDbName(this.joinTables.rightTable.getSimpleName()) +
                            "_" +
                            Conventions.toDbName(method.getName());
                    ColumnName columnName = new ColumnName();
                    columnName.setTable(this.tableName);
                    columnName.setName(name);
                    this.rightMethodColumnNameMapper.map(method,columnName);
                });
        return this;
    }


    public TableName getTableName() {
        return tableName;
    }

    public ColumnName getColumnName(Method method) {
        if(method.getDeclaringClass().equals(this.joinTables.getLeftTable())){
            return leftMethodColumnNameMapper.getByLeft(method);
        }else{
            return rightMethodColumnNameMapper.getByLeft(method);
        }
    }

}
