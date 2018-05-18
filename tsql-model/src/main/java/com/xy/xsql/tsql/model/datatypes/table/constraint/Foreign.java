package com.xy.xsql.tsql.model.datatypes.table.constraint;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/8/7.
 */
public class Foreign implements Constraint {
    private boolean useForeignKey;
    //REFERENCES [ schema_name . ] referenced_table_name [ ( ref_column [ ,...n ] ) ]
    private String schemaName;
    private String referencedTableName;
    private List<String> refColumns;
    //[ ON DELETE { NO ACTION | CASCADE | SET NULL | SET DEFAULT } ]
    private OnType onDelete;
    //[ ON UPDATE { NO ACTION | CASCADE | SET NULL | SET DEFAULT } ]
    private OnType onUpdate;
    //[ NOT FOR REPLICATION ]
    private boolean useNotForReplication;

    //Table
    //( column [ ,...n ] )
    private List<String> columns;

    public boolean isUseForeignKey() {
        return useForeignKey;
    }

    public void setUseForeignKey(boolean useForeignKey) {
        this.useForeignKey = useForeignKey;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public String getReferencedTableName() {
        return referencedTableName;
    }

    public void setReferencedTableName(String referencedTableName) {
        this.referencedTableName = referencedTableName;
    }

    public List<String> getRefColumns() {
        return refColumns;
    }

    public void setRefColumns(List<String> refColumns) {
        this.refColumns = refColumns;
    }

    public OnType getOnDelete() {
        return onDelete;
    }

    public void setOnDelete(OnType onDelete) {
        this.onDelete = onDelete;
    }

    public OnType getOnUpdate() {
        return onUpdate;
    }

    public void setOnUpdate(OnType onUpdate) {
        this.onUpdate = onUpdate;
    }

    public boolean isUseNotForReplication() {
        return useNotForReplication;
    }

    public void setUseNotForReplication(boolean useNotForReplication) {
        this.useNotForReplication = useNotForReplication;
    }

    public enum OnType{
        NO_ACTION("NO ACTION"),
        CASCADE(),
        SET_NULL("SET NULL"),
        SET_DEFAULT("SET DEFAULT");

        private String key;
        OnType(){
            this.key = this.name();
        }
        OnType(String key){
            this.key = key;
        }

        @Override
        public String toString() {
            return this.key;
        }
    }
}
