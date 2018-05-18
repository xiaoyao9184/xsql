package com.xy.xsql.tsql.model.statements.alter.table;

import com.xy.xsql.tsql.model.datatypes.table.column.DataType;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.table.collation.Collate;

/**
 * Created by xiaoyao9184 on 2017/9/17
 */
public class AlterColumn implements Item {
    private String columnName;

    private DataType typeName;
    private Collate collationName;
    private Boolean useNull;
    private boolean useSparse;

    private Boolean useAdd;
    private boolean useRowGuidCol;
    private boolean usePersisted;
    private boolean useNotForReplication;
    private boolean useHidden;
    private boolean useMasked;
    private StringConstant maskFunction;

    private Boolean useOnline;
    private Boolean useCheck;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public DataType getTypeName() {
        return typeName;
    }

    public void setTypeName(DataType typeName) {
        this.typeName = typeName;
    }

    public Collate getCollationName() {
        return collationName;
    }

    public void setCollationName(Collate collationName) {
        this.collationName = collationName;
    }

    public Boolean getUseNull() {
        return useNull;
    }

    public void setUseNull(Boolean useNull) {
        this.useNull = useNull;
    }

    public boolean isUseSparse() {
        return useSparse;
    }

    public void setUseSparse(boolean useSparse) {
        this.useSparse = useSparse;
    }

    public Boolean getUseAdd() {
        return useAdd;
    }

    public void setUseAdd(Boolean useAdd) {
        this.useAdd = useAdd;
    }

    public boolean isUseRowGuidCol() {
        return useRowGuidCol;
    }

    public void setUseRowGuidCol(boolean useRowGuidCol) {
        this.useRowGuidCol = useRowGuidCol;
    }

    public boolean isUsePersisted() {
        return usePersisted;
    }

    public void setUsePersisted(boolean usePersisted) {
        this.usePersisted = usePersisted;
    }

    public boolean isUseNotForReplication() {
        return useNotForReplication;
    }

    public void setUseNotForReplication(boolean useNotForReplication) {
        this.useNotForReplication = useNotForReplication;
    }

    public boolean isUseHidden() {
        return useHidden;
    }

    public void setUseHidden(boolean useHidden) {
        this.useHidden = useHidden;
    }

    public boolean isUseMasked() {
        return useMasked;
    }

    public void setUseMasked(boolean useMasked) {
        this.useMasked = useMasked;
    }

    public StringConstant getMaskFunction() {
        return maskFunction;
    }

    public void setMaskFunction(StringConstant maskFunction) {
        this.maskFunction = maskFunction;
    }

    public Boolean getUseOnline() {
        return useOnline;
    }

    public void setUseOnline(Boolean useOnline) {
        this.useOnline = useOnline;
    }

    public Boolean getUseCheck() {
        return useCheck;
    }

    public void setUseCheck(Boolean useCheck) {
        this.useCheck = useCheck;
    }
}
