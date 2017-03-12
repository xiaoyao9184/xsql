package com.xy.xsql.orm.data.sql.element.predicate;

import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.element.datatype.StringConstant;
import com.xy.xsql.orm.data.sql.element.info.Column;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class Contains implements Element {

    /*
     {
        column_name | ( column_list )
      | *
      | PROPERTY ( { column_name }, 'property_name' )
     }
     */
    private Column columnName;
    private List<Column> columnList;
    private boolean useAllColumn;
    //TODO

    //TODO
    private StringConstant containsSearchCondition;

    public Column getColumnName() {
        return columnName;
    }

    public void setColumnName(Column columnName) {
        this.columnName = columnName;
    }

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }

    public boolean isUseAllColumn() {
        return useAllColumn;
    }

    public void setUseAllColumn(boolean useAllColumn) {
        this.useAllColumn = useAllColumn;
    }

    public StringConstant getContainsSearchCondition() {
        return containsSearchCondition;
    }

    public void setContainsSearchCondition(StringConstant containsSearchCondition) {
        this.containsSearchCondition = containsSearchCondition;
    }
}
