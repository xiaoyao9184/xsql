package com.xy.xsql.tsql.core.statement.ddl;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.tsql.model.statements.create.CreateView;
import com.xy.xsql.tsql.model.queries.Select;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.reverse;
import static com.xy.xsql.core.ListBuilder.setter;

/**
 * Created by xiaoyao9184 on 2017/8/3.
 */
public class CreateViewBuilder extends CodeBuilder<CreateView> {

    public CreateViewBuilder(CreateView tar) {
        super(tar);
    }

    public CreateViewBuilder() {
        super(new CreateView());
    }

    public CreateViewBuilder withAlter(){
        target.setUseAlter(true);
        return this;
    }

    public CreateViewBuilder withSchemaName(String schemaName){
        target.setSchemaName(schemaName);
        return this;
    }

    public CreateViewBuilder withViewName(String viewName){
        target.setViewName(viewName);
        return this;
    }

    public CreateViewBuilder withColumn(List<String> columns){
        target.setColumn(columns);
        return this;
    }

    public CreateViewBuilder withViewAttribute(List<CreateView.ViewAttribute> viewAttributes){
        target.setViewAttributes(viewAttributes);
        return this;
    }

    public CreateViewBuilder withSelectStatement(Select selectStatement){
        target.setSelectStatement(selectStatement);
        return this;
    }

    public CreateViewBuilder withCheckOption(){
        target.setUseCheckOption(true);
        return this;
    }



    public CreateViewBuilder $Or_Alter_View(String schemaName, String viewName) {
        return withAlter()
                .withSchemaName(schemaName)
                .withViewName(viewName);
    }

    public CreateViewBuilder $View(String schemaName, String viewName) {
        return withSchemaName(schemaName)
                .withViewName(viewName);
    }

    public CreateViewBuilder $(String... columns) {
        initAdd(Stream.of(columns)
                .collect(Collectors.toList()),
                this.target::getColumn,
                this.target::setColumn);
        return this;
    }

    public CreateViewBuilder $With(CreateView.ViewAttribute... viewAttribute) {
        initAdd(Stream.of(viewAttribute)
                        .collect(Collectors.toList()),
                this.target::getViewAttributes,
                this.target::setViewAttributes);
        return this;
    }

    public CreateViewBuilder $As(Select selectStatement) {
        return withSelectStatement(selectStatement);
    }

    public CreateViewBuilder $With_Check_Option() {
        return withCheckOption();
    }


    /*
    Quick build
     */

    /**
     * Quick build Create View
     * @param selectStatement select statement
     * @param schema_view the last is view name
     * @return CreateView
     */
    public static CreateView CREATE_VIEW(Select selectStatement, String... schema_view){
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
    public static CreateView CREATE_OR_ALTER_VIEW(Select selectStatement, String... schema_view){
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


    public static CreateView.ViewAttribute ENCRYPTION() {
        return CreateView.ViewAttribute.ENCRYPTION;
    }

    public static CreateView.ViewAttribute SCHEMABINDING() {
        return CreateView.ViewAttribute.SCHEMABINDING;
    }

    public static CreateView.ViewAttribute VIEW_METADATA() {
        return CreateView.ViewAttribute.VIEW_METADATA;
    }
}
