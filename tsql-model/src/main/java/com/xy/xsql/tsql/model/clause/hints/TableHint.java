package com.xy.xsql.tsql.model.clause.hints;

import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.Clause;
import com.xy.xsql.tsql.model.datatype.StringConstant;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.operator.Comparison;
import com.xy.xsql.tsql.util.ListBlockBuilder;

import java.util.List;

/**
 * https://msdn.microsoft.com/en-us/library/ms187373.aspx
 *
 * <table_hint>
 *
 * Created by xiaoyao9184 on 2016/12/22.
 */
public class TableHint implements Clause {
    private boolean useNOEXPAND;
    private Type type;
    private boolean useOneIndexValue;
    private List<StringConstant> index_value;
    private List<StringConstant> index_column_name;
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

    public List<StringConstant> getIndex_value() {
        return index_value;
    }

    public void setIndex_value(List<StringConstant> index_value) {
        this.index_value = index_value;
    }

    public List<StringConstant> getIndex_column_name() {
        return index_column_name;
    }

    public void setIndex_column_name(List<StringConstant> index_column_name) {
        this.index_column_name = index_column_name;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    @Override
    public List<Block> toBlockList() {
        ListBlockBuilder b = new ListBlockBuilder()
                .append(type);

        if(useNOEXPAND){
            b.append(Keywords.Key.NOEXPAND);
        }
        b.append(type);
        switch (type){
            case INDEX:
                if(useOneIndexValue){
                    b.append(Other.GROUP_START)
                            .append(index_value)
                            .append(Other.GROUP_END);
                }else{
                    b.append(Comparison.EQUAL)
                            .append(index_value.get(0));
                }
                break;
            case FORCESEEK:
                if(index_value.size() > 0){
                    b.append(Other.GROUP_START)
                            .append(index_value)
                            .append(Other.GROUP_START)
                            .append(index_column_name,Other.DELIMITER)
                            .append(Other.GROUP_END)
                            .append(Other.GROUP_END);
                }
                break;
            case SPATIAL_WINDOW_MAX_CELLS:
                b.append(Comparison.EQUAL)
                        .append(integer);
                break;
        }

        return b.build();
    }


    public enum Type implements Block {
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
