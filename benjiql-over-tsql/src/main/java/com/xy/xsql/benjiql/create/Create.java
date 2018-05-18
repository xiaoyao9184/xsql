package com.xy.xsql.benjiql.create;

import com.xy.xsql.benjiql.core.ClassTableMetaBuilder;
import com.xy.xsql.benjiql.core.ProxyObjectMethodRecording;
import com.xy.xsql.benjiql.ddl.JoinTables;
import com.xy.xsql.benjiql.util.Conventions;
import com.xy.xsql.block.core.BlockManager;
import com.xy.xsql.tsql.model.datatypes.DataType;
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

import static com.xy.xsql.tsql.core.datatype.DataTypes._user_defined;

public class Create<T> {
    private final Class<T> cls;

    private final Set<FieldNameType> fieldNames = new LinkedHashSet<>();
    private final ProxyObjectMethodRecording<T> recorder;
    private ClassTableMetaBuilder builder;

    public Create(Class<T> cls) {
        this.cls = cls;
        this.recorder = ProxyObjectMethodRecording.create(cls);
    }

    public static <T> Create<T> create(Class<T> cls) {
        return new Create<T>(cls);
    }

    public static <T,U> CreateRelationship<T,U> create(JoinTables<T,U> joinTables) {
        return new CreateRelationship<T, U>(joinTables.leftTable, joinTables.rightTable);
    }

    public <U extends Serializable> Create<T> field(Function<T,U> getter) {
        getter.apply(recorder.getObject());
        ColumnName columnName = buildClassTable().getColumnName(recorder.getMethod());
        fieldNames.add(new FieldNameType(columnName.getName(), recorder.getMethod().getReturnType()));
        return this;
    }


    private ClassTableMetaBuilder buildClassTable(){
        if(builder == null){
            builder = new ClassTableMetaBuilder()
                    .build(this.cls);
        }
        return builder;
    }


    public String toSql() {
        SimpleCreateTable create = buildCreateTable();
        return BlockManager.INSTANCE.print(create);
    }

    private SimpleCreateTable buildCreateTable(){
        TableName tableName = new TableName();
        tableName.setTableOrViewName(Conventions.toDbName(cls.getSimpleName()));

        List<ColumnDefinition> columnDefinitionList = buildColumnDefinitionList();

        SimpleCreateTable createTable = new SimpleCreateTable();
        createTable.setTableName(tableName);
        createTable.setColumnDefinitionList(columnDefinitionList);

        return createTable;
    }

    @SuppressWarnings("Duplicates")
    private List<ColumnDefinition> buildColumnDefinitionList(){
        return fieldNames.stream()
                .map(fieldNameType -> {
                    ColumnDefinition columnDefinition = new ColumnDefinition();
                    columnDefinition.setName(fieldNameType.fieldName);
                    DataType dataType = _user_defined(Conventions.toDbType(fieldNameType.type));
                    columnDefinition.setDataType(dataType);
                    return columnDefinition;
                })
                .collect(Collectors.toList());
    }


    static class FieldNameType<T> {
        String fieldName;
        Class<T> type;

        FieldNameType(String fieldName, Class<T> type) {
            this.fieldName = fieldName;
            this.type = type;
        }

        @Override
        public String toString() {
            return Conventions.toDbName(fieldName) + " " + Conventions.toDbType(type);
        }
    }
}
