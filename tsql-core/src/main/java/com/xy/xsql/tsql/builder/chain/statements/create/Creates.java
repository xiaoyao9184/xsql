package com.xy.xsql.tsql.builder.chain.statements.create;

import com.xy.xsql.tsql.builder.chain.statements.create.table.SimpleCreateTableBuilder;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnDefinition;
import com.xy.xsql.tsql.model.queries.Select;
import com.xy.xsql.tsql.model.statements.create.CreateDataBase;
import com.xy.xsql.tsql.model.statements.create.CreateView;
import com.xy.xsql.tsql.model.statements.create.table.SimpleCreateTable;

import java.util.List;

import static com.xy.xsql.core.ListBuilder.reverse;
import static com.xy.xsql.core.ListBuilder.setter;

/**
 * Create Factory
 * Created by xiaoyao9184 on 2018/5/24.
 */
@SuppressWarnings("unused")
public interface Creates {

    /**
     * Quick in
     * @return THIS
     */
    static SimpleCreateTableBuilder $CreateTable(){
        return new SimpleCreateTableBuilder();
    }

    /**
     * Quick build Create Table
     * @param tableName multipart table name
     * @param columnDefinitions the last is view name
     * @return SimpleCreateTable
     */
    static SimpleCreateTable $CreateTable(TableName tableName, ColumnDefinition... columnDefinitions){
        return new SimpleCreateTableBuilder()
                .$(tableName)
                .$(columnDefinitions)
                .build();
    }
    
    /**
     * Quick build Create DataBase
     * @param name the last is new name
     * @return CreateDataBase
     */
    static CreateDataBase $CreateDatabase(String name){
        return new CreateDataBaseBuilder()
                .withDBName(name)
                .build();
    }

    /**
     * Quick build Create View
     * @param selectStatement select statement
     * @param schema_view the last is view name
     * @return CreateView
     */
    static CreateView $CreateView(Select selectStatement, String... schema_view){
        List<String> listReversedOrder = reverse(schema_view);

        CreateView createView = new CreateView();
        //noinspection unchecked
        setter(listReversedOrder,
                createView::setViewName,
                createView::setSchemaName);

        return new CreateViewBuilder(createView)
                .withSelectStatement(selectStatement)
                .build();
    }

    /**
     * Quick build Create View
     * @param selectStatement select statement
     * @param schema_view the last is view name
     * @return CreateView
     */
    static CreateView $CreateOrAlterView(Select selectStatement, String... schema_view){
        List<String> listReversedOrder = reverse(schema_view);

        CreateView createView = new CreateView();
        //noinspection unchecked
        setter(listReversedOrder,
                createView::setSchemaName,
                createView::setViewName);

        return new CreateViewBuilder(createView)
                .withAlter()
                .withSelectStatement(selectStatement)
                .build();
    }

    /**
     * Quick build
     * @return ViewAttribute
     */
    static CreateView.ViewAttribute $Encryption() {
        return CreateView.ViewAttribute.ENCRYPTION;
    }

    /**
     * Quick build
     * @return ViewAttribute
     */
    static CreateView.ViewAttribute $Schemabinding() {
        return CreateView.ViewAttribute.SCHEMABINDING;
    }

    /**
     * Quick build
     * @return ViewAttribute
     */
    static CreateView.ViewAttribute $ViewMetadata() {
        return CreateView.ViewAttribute.VIEW_METADATA;
    }
}
