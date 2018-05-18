package com.xy.xsql.tsql.model.statements.create;

import com.xy.xsql.tsql.model.statements.Statement;
import com.xy.xsql.tsql.model.queries.Select;

import java.util.List;

/**
 * https://docs.microsoft.com/en-us/sql/t-sql/statements/create-view-transact-sql
 *
 *
 * Created by xiaoyao9184 on 2017/8/3.
 */
public class CreateView implements Statement {

    private boolean useAlter;
    private String schemaName;
    private String viewName;
    private List<String> column;

    //[ WITH <view_attribute> [ ,...n ] ]
    private List<ViewAttribute> viewAttributes;
    private Select selectStatement;

    //[ WITH CHECK OPTION ]
    private boolean useCheckOption;


    public boolean isUseAlter() {
        return useAlter;
    }

    public void setUseAlter(boolean useAlter) {
        this.useAlter = useAlter;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public List<String> getColumn() {
        return column;
    }

    public void setColumn(List<String> column) {
        this.column = column;
    }

    public List<ViewAttribute> getViewAttributes() {
        return viewAttributes;
    }

    public void setViewAttributes(List<ViewAttribute> viewAttributes) {
        this.viewAttributes = viewAttributes;
    }

    public Select getSelectStatement() {
        return selectStatement;
    }

    public void setSelectStatement(Select selectStatement) {
        this.selectStatement = selectStatement;
    }

    public boolean isUseCheckOption() {
        return useCheckOption;
    }

    public void setUseCheckOption(boolean useCheckOption) {
        this.useCheckOption = useCheckOption;
    }

    public enum ViewAttribute {
        ENCRYPTION,
        SCHEMABINDING,
        VIEW_METADATA
    }
}
