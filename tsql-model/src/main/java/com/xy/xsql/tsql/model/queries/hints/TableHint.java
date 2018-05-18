package com.xy.xsql.tsql.model.queries.hints;

import com.xy.xsql.tsql.model.queries.Clause;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;

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

    public enum Type {
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
