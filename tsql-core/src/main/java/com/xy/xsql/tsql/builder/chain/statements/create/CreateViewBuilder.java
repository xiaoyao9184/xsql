package com.xy.xsql.tsql.builder.chain.statements.create;

import com.xy.xsql.core.builder.simple.CodeBuilder;
import com.xy.xsql.tsql.model.queries.Select;
import com.xy.xsql.tsql.model.statements.create.CreateView;

import java.util.List;

import static com.xy.xsql.core.handler.list.ListHandler.list;

/**
 * CreateViewBuilder
 * Created by xiaoyao9184 on 2017/8/3.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class CreateViewBuilder extends CodeBuilder<CreateView> {

    public CreateViewBuilder(CreateView target) {
        super(target);
    }

    public CreateViewBuilder() {
        super(new CreateView());
    }

    /**
     * set
     * @return THIS
     */
    public CreateViewBuilder withAlter(){
        target.setUseAlter(true);
        return this;
    }

    /**
     * set
     * @param schemaName schema name
     * @return THIS
     */
    public CreateViewBuilder withSchemaName(String schemaName){
        target.setSchemaName(schemaName);
        return this;
    }

    /**
     * set
     * @param viewName view name
     * @return THIS
     */
    public CreateViewBuilder withViewName(String viewName){
        target.setViewName(viewName);
        return this;
    }

    /**
     * set
     * @param columns column
     * @return THIS
     */
    public CreateViewBuilder withColumn(List<String> columns){
        target.setColumn(columns);
        return this;
    }

    /**
     * set
     * @param viewAttributes ViewAttribute
     * @return THIS
     */
    public CreateViewBuilder withViewAttribute(List<CreateView.ViewAttribute> viewAttributes){
        target.setViewAttributes(viewAttributes);
        return this;
    }

    /**
     * set
     * @param selectStatement select statement
     * @return THIS
     */
    public CreateViewBuilder withSelectStatement(Select selectStatement){
        target.setSelectStatement(selectStatement);
        return this;
    }

    /**
     * set
     * @return THIS
     */
    public CreateViewBuilder withCheckOption(){
        target.setUseCheckOption(true);
        return this;
    }




    /*
    Quick
     */

    /**
     * Quick set
     * @param schemaName schema name
     * @param viewName view name
     * @return THIS
     */
    public CreateViewBuilder $OrAlterView(String schemaName, String viewName) {
        return withAlter()
                .withSchemaName(schemaName)
                .withViewName(viewName);
    }

    /**
     * Quick set
     * @param schemaName schema name
     * @param viewName view name
     * @return THIS
     */
    public CreateViewBuilder $View(String schemaName, String viewName) {
        return withSchemaName(schemaName)
                .withViewName(viewName);
    }

    /**
     * Quick set
     * @param columns column
     * @return THIS
     */
    public CreateViewBuilder $(String... columns) {
        list(target::getColumn, target::setColumn)
                .addAll(columns);
        return this;
    }

    /**
     * Quick set
     * @param viewAttribute ViewAttribute
     * @return THIS
     */
    public CreateViewBuilder $With(CreateView.ViewAttribute... viewAttribute) {
        list(target::getViewAttributes, target::setViewAttributes)
                .addAll(viewAttribute);
        return this;
    }

    /**
     * set
     * @param selectStatement select statement
     * @return THIS
     */
    public CreateViewBuilder $As(Select selectStatement) {
        return withSelectStatement(selectStatement);
    }

    /**
     * set
     * @return THIS
     */
    public CreateViewBuilder $WithCheckOption() {
        return withCheckOption();
    }

}
