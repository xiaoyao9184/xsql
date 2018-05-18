package com.xy.xsql.tsql.builder.chain.datatypes.table.index;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.datatypes.table.index.Partition;

/**
 * Created by xiaoyao9184 on 2017/8/17.
 * @param <ParentBuilder>
 */
public class PartitionBuilder<ParentBuilder>
        extends CodeTreeBuilder<PartitionBuilder<ParentBuilder>,ParentBuilder,Partition> {

    public PartitionBuilder(Partition tar) {
        super(tar);
    }

    public PartitionBuilder<ParentBuilder> withSchemeName(String partitionSchemeName){
        target.setSchemeName(partitionSchemeName);
        return this;
    }

    public PartitionBuilder<ParentBuilder> withColumnName(String partitionColumnName){
        target.setColumnName(partitionColumnName);
        return this;
    }

    public PartitionBuilder<ParentBuilder> withFilegroupName(String filegroupName){
        target.setFilegroupName(filegroupName);
        return this;
    }

    public PartitionBuilder<ParentBuilder> withUseDefault(boolean useDefault){
        target.setUseDefault(useDefault);
        return this;
    }

    /**
     * Quick inout set partitionSchemeName, partitionColumnName
     * @return ParentBuilder
     */
    public ParentBuilder $Partition(String partitionSchemeName,String partitionColumnName){
        return withSchemeName(partitionSchemeName)
                .withColumnName(partitionColumnName)
                .and();
    }

    /**
     * Quick inout set filegroupName
     * @return ParentBuilder
     */
    public ParentBuilder $FilegroupName(String filegroupName){
        return withFilegroupName(filegroupName)
                .and();
    }

    /**
     * Quick inout set filegroupName
     * @return ParentBuilder
     */
    public ParentBuilder $Default(String filegroupName){
        return withFilegroupName(filegroupName)
                .and();
    }

}
