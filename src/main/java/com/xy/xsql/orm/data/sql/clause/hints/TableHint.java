package com.xy.xsql.orm.data.sql.clause.hints;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.ElementList;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.UnknownString;

import java.util.ArrayList;
import java.util.List;

/**
 * https://msdn.microsoft.com/en-us/library/ms187373.aspx
 *
 * <table_hint>
 *
 * Created by xiaoyao9184 on 2016/12/22.
 */
public class TableHint implements ElementList{
    private boolean useNOEXPAND;
    private Type type;
    private boolean useOneIndexValue;
    private List<UnknownString> index_value;
    private List<UnknownString> index_column_name;
    private Integer integer;

    public boolean isUseNOEXPAND() {
        return useNOEXPAND;
    }

    public void setUseNOEXPAND(boolean useNOEXPAND) {
        this.useNOEXPAND = useNOEXPAND;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isUseOneIndexValue() {
        return useOneIndexValue;
    }

    public void setUseOneIndexValue(boolean useOneIndexValue) {
        this.useOneIndexValue = useOneIndexValue;
    }

    public List<UnknownString> getIndex_value() {
        return index_value;
    }

    public void setIndex_value(List<UnknownString> index_value) {
        this.index_value = index_value;
    }

    public List<UnknownString> getIndex_column_name() {
        return index_column_name;
    }

    public void setIndex_column_name(List<UnknownString> index_column_name) {
        this.index_column_name = index_column_name;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    @Override
    public List<Element> toElementList() {
        ListElementBuilder b = new ListElementBuilder()
                .append(type);

        if(useNOEXPAND){
            b.append(GrammarEnum.NOEXPAND);
        }
        b.append(type);
        switch (type){
            case INDEX:
                if(useOneIndexValue){
                    b.append(OtherEnum.GROUP_START)
                            .append(index_value)
                            .append(OtherEnum.GROUP_END);
                }else{
                    b.append(OperatorEnum.EQUAL)
                            .append(index_value.get(0));
                }
                break;
            case FORCESEEK:
                if(index_value.size() > 0){
                    b.append(OtherEnum.GROUP_START)
                            .append(index_value)
                            .append(OtherEnum.GROUP_START)
                            .append(index_column_name,OtherEnum.DELIMITER)
                            .append(OtherEnum.GROUP_END)
                            .append(OtherEnum.GROUP_END);
                }
                break;
            case SPATIAL_WINDOW_MAX_CELLS:
                b.append(OperatorEnum.EQUAL)
                        .append(integer);
                break;
        }

        return b.build();
    }


    public enum Type implements Element {
        INDEX,
        FORCESCAN,
        FORCESEEK,
        HOLDLOCK,
        NOLOCK,
        NOWAIT,
        PAGLOCK,
        READCOMMITTED,
        READCOMMITTEDLOCK,
        READPAST,
        READUNCOMMITTED,
        REPEATABLEREAD,
        ROWLOCK,
        SERIALIZABLE,
        SNAPSHOT,
        SPATIAL_WINDOW_MAX_CELLS,
        TABLOCK,
        TABLOCKX,
        UPDLOCK,
        XLOCK;

        @Override
        public String toString() {
            return this.name();
        }
    }
}
