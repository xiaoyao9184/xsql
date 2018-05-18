package com.xy.xsql.tsql.builder.chain.statements.alter.table;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.table.index.IndexOption;
import com.xy.xsql.tsql.model.statements.alter.table.LowPriorityLockWait;
import com.xy.xsql.tsql.model.statements.alter.table.SinglePartitionRebuildOption;

import static com.xy.xsql.core.FiledBuilder.initSet;

/**
 * Created by xiaoyao9184 on 2017/9/16.
 */
public class SinglePartitionRebuildOptionBuilder<ParentBuilder>
        extends CodeTreeBuilder<SinglePartitionRebuildOptionBuilder<ParentBuilder>,ParentBuilder,SinglePartitionRebuildOption> {

    public SinglePartitionRebuildOptionBuilder(SinglePartitionRebuildOption target) {
        super(target);
    }

    public SinglePartitionRebuildOptionBuilder<ParentBuilder> withUseSortInTempDb(boolean useSortInTempDb){
        target.setUseSortInTempDb(useSortInTempDb);
        return this;
    }

    public SinglePartitionRebuildOptionBuilder<ParentBuilder> withMaxDegreeOfParallelism(NumberConstant maxDegreeOfParallelism){
        target.setMaxDegreeOfParallelism(maxDegreeOfParallelism);
        return this;
    }

    public SinglePartitionRebuildOptionBuilder<ParentBuilder> withDataCompression(IndexOption.DataCompression dataCompression){
        target.setDataCompression(dataCompression);
        return this;
    }

    public SinglePartitionRebuildOptionBuilder<ParentBuilder> withUseOnline(boolean useOnline){
        target.setUseOnline(useOnline);
        return this;
    }

    public SinglePartitionRebuildOptionBuilder<ParentBuilder> withLowPriorityLockWait(LowPriorityLockWait lowPriorityLockWait){
        target.setLowPriorityLockWait(lowPriorityLockWait);
        return this;
    }

    /*
    Quick
     */

    public SinglePartitionRebuildOptionBuilder<ParentBuilder> $SORT_IN_TEMPDB_$ON(){
        return withUseOnline(true);
    }

    public SinglePartitionRebuildOptionBuilder<ParentBuilder> $SORT_IN_TEMPDB_$OFF(){
        return withUseOnline(false);
    }

    public SinglePartitionRebuildOptionBuilder<ParentBuilder> $MAXDOP(Integer maxDegreeOfParallelism){
        return withMaxDegreeOfParallelism(
                new NumberConstant(maxDegreeOfParallelism)
                        .withUnsigned()
                        .withInteger());
    }

    public SinglePartitionRebuildOptionBuilder<ParentBuilder> $DATA_COMPRESSION_$NONE(){
        return withDataCompression(IndexOption.DataCompression.NONE);
    }

    public SinglePartitionRebuildOptionBuilder<ParentBuilder> $DATA_COMPRESSION_$ROW(){
        return withDataCompression(IndexOption.DataCompression.ROW);
    }

    public SinglePartitionRebuildOptionBuilder<ParentBuilder> $DATA_COMPRESSION_$PAGE(){
        return withDataCompression(IndexOption.DataCompression.PAGE);
    }

    public SinglePartitionRebuildOptionBuilder<ParentBuilder> $DATA_COMPRESSION_$COLUMNSTORE(){
        return withDataCompression(IndexOption.DataCompression.COLUMNSTORE);
    }

    public SinglePartitionRebuildOptionBuilder<ParentBuilder> $DATA_COMPRESSION_$COLUMNSTORE_ARCHIVE(){
        return withDataCompression(IndexOption.DataCompression.COLUMNSTORE_ARCHIVE);
    }

    public SinglePartitionRebuildOptionBuilder<ParentBuilder> $ONLINE_$ON(){
        return withUseOnline(true);
    }

    public LowPriorityLockWaitBuilder<SinglePartitionRebuildOptionBuilder<ParentBuilder>> $ONLINE_$ON_$(){
        return new LowPriorityLockWaitBuilder<SinglePartitionRebuildOptionBuilder<ParentBuilder>>
                (initSet(LowPriorityLockWait::new,
                        target::getLowPriorityLockWait,
                        target::setLowPriorityLockWait))
                .in(this);
    }

    public SinglePartitionRebuildOptionBuilder<ParentBuilder> $ONLINE_$OFF(){
        return withUseOnline(false);
    }

}
