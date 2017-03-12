package com.xy.xsql.orm.data.sql.element.predicate;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.datatype.StringConstant;
import com.xy.xsql.orm.data.sql.element.info.AnyColumn;
import com.xy.xsql.orm.data.sql.element.info.Column;

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

    @SuppressWarnings("Duplicates")
    @Override
    public List<Element> toElementList() {
        ListElementBuilder b = new ListElementBuilder()
                .append(GrammarEnum.CONTAINS);

        if(columnName != null){
            b.append(columnName);
        }else if(columnList != null){
            b.append(OtherEnum.GROUP_START)
                    .append(columnList)
                    .append(OtherEnum.GROUP_END);
        }else if(useAllColumn){
            b.append(new AnyColumn());
        }
        b.append(OtherEnum.DELIMITER)
                .append(containsSearchCondition);

        return b.build();
    }
}
