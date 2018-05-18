package com.xy.xsql.tsql.builder.chain.datatypes.table.column;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.index.IndexOptionBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.index.PartitionBuilder;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnIndex;
import com.xy.xsql.tsql.model.datatypes.table.index.IndexOption;
import com.xy.xsql.tsql.model.datatypes.table.index.Partition;

import java.util.List;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * Use
 * in --Disk-Based CREATE TABLE Syntax
 * Created by xiaoyao9184 on 2017/8/18.
 * @param <ParentBuilder>
 */
public class ColumnIndexBuilder<ParentBuilder>
        extends CodeTreeBuilder<ColumnIndexBuilder<ParentBuilder>,ParentBuilder,ColumnIndex> {

    public ColumnIndexBuilder(ColumnIndex columnIndex) {
        super(columnIndex);
    }

    public ColumnIndexBuilder() {
        super(new ColumnIndex());
    }

    public ColumnIndexBuilder<ParentBuilder> withIndexName(String indexName) {
        target.setIndexName(indexName);
        return this;
    }

    public ColumnIndexBuilder<ParentBuilder> withUseClustered(Boolean useClustered) {
        target.setUseClustered(useClustered);
        return this;
    }

    public ColumnIndexBuilder<ParentBuilder> withIndexOptionList(List<IndexOption> indexOptionList) {
        target.setIndexOptionList(indexOptionList);
        return this;
    }

    public ColumnIndexBuilder<ParentBuilder> withPartition(Partition partition) {
        target.setPartition(partition);
        return this;
    }

    //TODO not same
    public ColumnIndexBuilder<ParentBuilder> withFileStreamPartition(Partition fileStreamPartition) {
        target.setFileStreamPartition(fileStreamPartition);
        return this;
    }


    /*
    Quick
     */


    public ColumnIndexBuilder<ParentBuilder> $INDEX(String indexName){
        return withIndexName(indexName);
    }

    public ColumnIndexBuilder<ParentBuilder> $CLUSTERED(){
        return withUseClustered(true);
    }

    public ColumnIndexBuilder<ParentBuilder> $NONCLUSTERED(){
        return withUseClustered(false);
    }

    public IndexOptionBuilder<ColumnIndexBuilder<ParentBuilder>> $WITH(){
        return new IndexOptionBuilder<ColumnIndexBuilder<ParentBuilder>>
                (initNew(
                        IndexOption::new,
                        target::getIndexOptionList,
                        target::setIndexOptionList))
                .in(this);
    }

    public PartitionBuilder<ColumnIndexBuilder<ParentBuilder>> $ON(){
        return new PartitionBuilder<ColumnIndexBuilder<ParentBuilder>>
                (initSet(Partition::new,
                        target::getPartition,
                        target::setPartition))
                .in(this);
    }

    public PartitionBuilder<ColumnIndexBuilder<ParentBuilder>> $FILESTREAM_ON(){
        return new PartitionBuilder<ColumnIndexBuilder<ParentBuilder>>
                (initSet(Partition::new,
                        target::getFileStreamPartition,
                        target::setFileStreamPartition))
                .in(this);
    }

}
