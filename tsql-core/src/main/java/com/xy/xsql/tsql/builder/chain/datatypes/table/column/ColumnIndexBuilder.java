package com.xy.xsql.tsql.builder.chain.datatypes.table.column;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.index.IndexOptionBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.index.PartitionBuilder;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnIndex;
import com.xy.xsql.tsql.model.datatypes.table.index.IndexOption;
import com.xy.xsql.tsql.model.datatypes.table.index.Partition;

import java.util.List;

import static com.xy.xsql.core.handler.list.ListHandler.list;
import static com.xy.xsql.core.handler.object.GetterSetterObjectHandler.object;

/**
 * ColumnIndexBuilder
 * Use
 * in --Disk-Based CREATE TABLE Syntax
 * Created by xiaoyao9184 on 2017/8/18.
 * @param <ParentBuilder>
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class ColumnIndexBuilder<ParentBuilder>
        extends ParentHoldBuilder<ColumnIndexBuilder<ParentBuilder>,ParentBuilder,ColumnIndex> {

    public ColumnIndexBuilder() {
        super(new ColumnIndex());
    }

    public ColumnIndexBuilder(ColumnIndex target) {
        super(target);
    }

    /**
     * set
     * @param indexName index name
     * @return THIS
     */
    public ColumnIndexBuilder<ParentBuilder> withIndexName(String indexName) {
        target.setIndexName(indexName);
        return this;
    }

    /**
     * set
     * @param useClustered clustered
     * @return THIS
     */
    public ColumnIndexBuilder<ParentBuilder> withUseClustered(Boolean useClustered) {
        target.setUseClustered(useClustered);
        return this;
    }

    /**
     * set
     * @param indexOptionList IndexOption
     * @return THIS
     */
    public ColumnIndexBuilder<ParentBuilder> withIndexOptionList(List<IndexOption> indexOptionList) {
        target.setIndexOptionList(indexOptionList);
        return this;
    }

    /**
     * set
     * @param partition Partition
     * @return THIS
     */
    public ColumnIndexBuilder<ParentBuilder> withPartition(Partition partition) {
        target.setPartition(partition);
        return this;
    }

    /**
     * TODO not same
     * set
     * @param fileStreamPartition file stream partition
     * @return THIS
     */
    public ColumnIndexBuilder<ParentBuilder> withFileStreamPartition(Partition fileStreamPartition) {
        target.setFileStreamPartition(fileStreamPartition);
        return this;
    }




    /*
    Quick
     */

    /**
     * Quick set
     * @param indexName index name
     * @return THIS
     */
    public ColumnIndexBuilder<ParentBuilder> $Index(String indexName){
        return withIndexName(indexName);
    }

    /**
     * Quick set
     * @return THIS
     */
    public ColumnIndexBuilder<ParentBuilder> $Clustered(){
        return withUseClustered(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public ColumnIndexBuilder<ParentBuilder> $Nonclustered(){
        return withUseClustered(false);
    }

    /**
     * Quick in
     * @return IndexOptionBuilder
     */
    public IndexOptionBuilder<ColumnIndexBuilder<ParentBuilder>> $With(){
        return new IndexOptionBuilder<ColumnIndexBuilder<ParentBuilder>>
                (list(target::getIndexOptionList, target::setIndexOptionList)
                        .addNew(IndexOption::new))
                .in(this);
    }

    /**
     * Quick in
     * @return PartitionBuilder
     */
    public PartitionBuilder<ColumnIndexBuilder<ParentBuilder>> $On(){
        return new PartitionBuilder<ColumnIndexBuilder<ParentBuilder>>
                (object(target::getPartition, target::setPartition)
                        .init(Partition::new))
                .in(this);
    }

    /**
     * Quick in
     * @return PartitionBuilder
     */
    public PartitionBuilder<ColumnIndexBuilder<ParentBuilder>> $FilestreamOn(){
        return new PartitionBuilder<ColumnIndexBuilder<ParentBuilder>>
                (object(target::getFileStreamPartition, target::setFileStreamPartition)
                        .init(Partition::new))
                .in(this);
    }

}
