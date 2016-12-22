package com.xy.xsql.orm.data.sql.clause.hints;

import com.xy.xsql.orm.data.sql.Element;

import java.util.List;

/**
 * https://msdn.microsoft.com/en-us/library/ms187373.aspx
 *
 * <table_hint>
 *
 * Created by xiaoyao9184 on 2016/12/22.
 */
public class TableHint {
    private boolean useNOEXPAND;
    private Type type;
    private boolean useOneIndexValue;
    private List<String> index_value;
    private List<String> index_column_name;
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

    public List<String> getIndex_value() {
        return index_value;
    }

    public void setIndex_value(List<String> index_value) {
        this.index_value = index_value;
    }

    public List<String> getIndex_column_name() {
        return index_column_name;
    }

    public void setIndex_column_name(List<String> index_column_name) {
        this.index_column_name = index_column_name;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
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
