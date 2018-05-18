package com.xy.xsql.tsql.model.predicate;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.element.ColumnName;

import java.util.List;

/**
 * FREETEXT ( { column | * } , 'freetext_string' )
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class FreeText implements Predicate {

    //{ column_name | (column_list) | * }
    private ColumnName columnName;
    private List<ColumnName> columnList;
    private boolean useAllColumn;

    //'freetext_string'
    private StringConstant freetextString;

    //TODO [ , LANGUAGE language_term ]


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

    public StringConstant getFreetextString() {
        return freetextString;
    }

    public void setFreetextString(StringConstant freetextString) {
        this.freetextString = freetextString;
    }

}
