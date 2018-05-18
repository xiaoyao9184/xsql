package com.xy.xsql.benjiql.create;

import com.xy.xsql.benjiql.core.ProxyObjectMethodRecording;
import com.xy.xsql.benjiql.core.RelationshipClassTableMetaBuilder;
import com.xy.xsql.benjiql.ddl.JoinTables;
import com.xy.xsql.benjiql.util.Conventions;
import com.xy.xsql.block.core.BlockManager;
import com.xy.xsql.tsql.model.datatypes.table.column.DataType;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnDefinition;
import com.xy.xsql.tsql.model.statements.create.table.SimpleCreateTable;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.column.DataTypes._user_defined;

public class CreateRelationship<T,U> {
    private final Class<T> left;
    private final Class<U> right;

    //lazy
    private final Set<Create.FieldNameType> leftFieldNames = new LinkedHashSet<>();
    private final Set<Create.FieldNameType> rightFieldNames = new LinkedHashSet<>();
    private final ProxyObjectMethodRecording<T> leftRecorder;
    private final ProxyObjectMethodRecording<U> rightRecorder;
    private final JoinTables<T,U> joinTables;
    private RelationshipClassTableMetaBuilder builder;

    public CreateRelationship(Class<T> leftCls, Class<U> rightCls) {
        this.left = leftCls;
        this.right = rightCls;
        this.leftRecorder = ProxyObjectMethodRecording.create(leftCls);
        this.rightRecorder = ProxyObjectMethodRecording.create(rightCls);
        this.joinTables = new JoinTables<>(this.left, this.right);
    }

    public <V extends Serializable> CreateRelationship<T,U> fieldLeft(Function<T,V> getter) {
        getter.apply(leftRecorder.getObject());
        ColumnName columnName = buildRelationshipTable().getColumnName(leftRecorder.getMethod());
        leftFieldNames.add(new Create.FieldNameType(Conventions.toDbName(left.getSimpleName()) + "_" + columnName.getName(), leftRecorder.getMethod().getReturnType()));
        return this;
    }

    public <V extends Serializable> CreateRelationship<T,U> fieldRight(Function<U,V> getter) {
        getter.apply(rightRecorder.getObject());
        ColumnName columnName = buildRelationshipTable().getColumnName(rightRecorder.getMethod());
        rightFieldNames.add(new Create.FieldNameType(Conventions.toDbName(right.getSimpleName()) + "_" + columnName.getName(), rightRecorder.getMethod().getReturnType()));
        return this;
    }


    private RelationshipClassTableMetaBuilder buildRelationshipTable(){
        if(builder == null){
            builder = new RelationshipClassTableMetaBuilder()
                    .build(this.joinTables);
        }
        return builder;
    }


    public String toSql() {
        SimpleCreateTable create = buildCreateTable();
        return BlockManager.INSTANCE.print(create);
    }

    private SimpleCreateTable buildCreateTable(){
        TableName tableName = new TableName();
        tableName.setTableOrViewName(Conventions.toDbName(left, right));

        List<ColumnDefinition> columnDefinitionList = buildColumnDefinitionList();

        SimpleCreateTable createTable = new SimpleCreateTable();
        createTable.setTableName(tableName);
        createTable.setColumnDefinitionList(columnDefinitionList);

        return createTable;
    }

    @SuppressWarnings("Duplicates")
    private List<ColumnDefinition> buildColumnDefinitionList(){
        return Stream.concat(leftFieldNames.stream(),rightFieldNames.stream())
                .map(fieldNameType -> {
                    ColumnDefinition columnDefinition = new ColumnDefinition();
                    columnDefinition.setName(fieldNameType.fieldName);
                    DataType dataType = _user_defined(Conventions.toDbType(fieldNameType.type));
                    columnDefinition.setDataType(dataType);
                    return columnDefinition;
                })
                .collect(Collectors.toList());
    }

}
