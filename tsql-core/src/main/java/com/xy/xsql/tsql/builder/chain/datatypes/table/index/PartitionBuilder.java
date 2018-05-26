package com.xy.xsql.tsql.builder.chain.datatypes.table.index;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.model.datatypes.table.index.Partition;

/**
 * PartitionBuilder
 * Created by xiaoyao9184 on 2017/8/17.
 * @param <ParentBuilder>
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class PartitionBuilder<ParentBuilder>
        extends ParentHoldBuilder<PartitionBuilder<ParentBuilder>,ParentBuilder,Partition> {

    public PartitionBuilder() {
        super(new Partition());
    }

    public PartitionBuilder(Partition target) {
        super(target);
    }

    /**
     * set
     * @param partitionSchemeName partition scheme name
     * @return THIS
     */
    public PartitionBuilder<ParentBuilder> withSchemeName(String partitionSchemeName){
        target.setSchemeName(partitionSchemeName);
        return this;
    }

    /**
     * set
     * @param partitionColumnName partition column name
     * @return THIS
     */
    public PartitionBuilder<ParentBuilder> withColumnName(String partitionColumnName){
        target.setColumnName(partitionColumnName);
        return this;
    }

    /**
     * set
     * @param filegroupName filegroup name
     * @return THIS
     */
    public PartitionBuilder<ParentBuilder> withFilegroupName(String filegroupName){
        target.setFilegroupName(filegroupName);
        return this;
    }

    /**
     * set
     * @param useDefault default
     * @return THIS
     */
    public PartitionBuilder<ParentBuilder> withUseDefault(boolean useDefault){
        target.setUseDefault(useDefault);
        return this;
    }




    /*
    Quick
     */

    /**
     * Quick inout set partitionSchemeName, partitionColumnName
     * @return PARENT
     */
    public ParentBuilder $Partition(String partitionSchemeName,String partitionColumnName){
        return withSchemeName(partitionSchemeName)
                .withColumnName(partitionColumnName)
                .and();
    }

    /**
     * Quick inout set filegroupName
     * @return PARENT
     */
    public ParentBuilder $FilegroupName(String filegroupName){
        return withFilegroupName(filegroupName)
                .and();
    }

    /**
     * Quick inout set filegroupName
     * @return PARENT
     */
    public ParentBuilder $Default(String filegroupName){
        return withFilegroupName(filegroupName)
                .and();
    }

}
