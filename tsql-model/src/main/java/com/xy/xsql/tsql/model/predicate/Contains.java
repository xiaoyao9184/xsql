package com.xy.xsql.tsql.model.predicate;


import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.datatype.StringConstant;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.util.ListBlockBuilder;

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
    //TODO

    //TODO
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

    public StringConstant getContainsSearchCondition() {
        return containsSearchCondition;
    }

    public void setContainsSearchCondition(StringConstant containsSearchCondition) {
        this.containsSearchCondition = containsSearchCondition;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public List<Block> toBlockList() {
        ListBlockBuilder b = new ListBlockBuilder()
                .append(Keywords.CONTAINS);

        if(columnName != null){
            b.append(columnName);
        }else if(columnList != null){
            b.append(Other.GROUP_START)
                    .append(columnList)
                    .append(Other.GROUP_END);
        }else if(useAllColumn){
            b.append(new ColumnName());
        }
        b.append(Other.DELIMITER)
                .append(containsSearchCondition);

        return b.build();
    }
}
