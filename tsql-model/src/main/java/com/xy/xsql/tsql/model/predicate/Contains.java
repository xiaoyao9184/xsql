package com.xy.xsql.tsql.model.predicate;

import com.xy.xsql.tsql.model.datatype.StringConstant;
import com.xy.xsql.tsql.model.element.ColumnName;

import java.util.List;

/**
 * CONTAINS
 ( { column | * } , '<contains_search_condition>' )
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class Contains implements Predicate {

    /*
     {
        column_name | ( column_list )
      | *
      | PROPERTY ( { column_name }, 'property_name' )
     }
     */
    private ColumnName columnName;
    private List<ColumnName> columnList;
    private boolean useAllColumn;
    //TODO PROPERTY
    private ColumnName propertyColumnName;
    private StringConstant propertyName;

    //TODO contains_search_condition
    private StringConstant containsSearchCondition;

    public ColumnName getColumnName() {
        return columnName;
    }

    public void setColumnName(ColumnName columnName) {
        this.columnName = columnName;
    }

    public List<ColumnName> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<ColumnName> columnList) {
        this.columnList = columnList;
    }

    public boolean isUseAllColumn() {
        return useAllColumn;
    }

    public void setUseAllColumn(boolean useAllColumn) {
        this.useAllColumn = useAllColumn;
    }

    public ColumnName getPropertyColumnName() {
        return propertyColumnName;
    }

    public void setPropertyColumnName(ColumnName propertyColumnName) {
        this.propertyColumnName = propertyColumnName;
    }

    public StringConstant getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(StringConstant propertyName) {
        this.propertyName = propertyName;
    }

    public StringConstant getContainsSearchCondition() {
        return containsSearchCondition;
    }

    public void setContainsSearchCondition(StringConstant containsSearchCondition) {
        this.containsSearchCondition = containsSearchCondition;
    }

}
