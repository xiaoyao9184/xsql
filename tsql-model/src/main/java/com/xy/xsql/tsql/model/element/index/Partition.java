package com.xy.xsql.tsql.model.element.index;

/**
 * Created by xiaoyao9184 on 2017/8/16.
 */
public class Partition {

    //[ ON { partition_scheme_name (column_name )
    //      | filegroup_name
    //      | default
    //     }
    //]
    private String partitionSchemeName;
    private String columnName;
    private String filegroupName;
    private boolean useDefault;

    public String getPartitionSchemeName() {
        return partitionSchemeName;
    }

    public void setPartitionSchemeName(String partitionSchemeName) {
        this.partitionSchemeName = partitionSchemeName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getFilegroupName() {
        return filegroupName;
    }

    public void setFilegroupName(String filegroupName) {
        this.filegroupName = filegroupName;
    }

    public boolean isUseDefault() {
        return useDefault;
    }

    public void setUseDefault(boolean useDefault) {
        this.useDefault = useDefault;
    }
}
