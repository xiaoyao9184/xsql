package com.xy.xsql.tsql.builder.chain.statements.alter.table;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.table.index.IndexOption;
import com.xy.xsql.tsql.model.statements.alter.table.LowPriorityLockWait;
import com.xy.xsql.tsql.model.statements.alter.table.SinglePartitionRebuildOption;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_unsigned_integer;

/**
 * SinglePartitionRebuildOptionBuilder
 * Created by xiaoyao9184 on 2017/9/16.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class SinglePartitionRebuildOptionBuilder<ParentBuilder>
        extends CodeTreeBuilder<SinglePartitionRebuildOptionBuilder<ParentBuilder>,ParentBuilder,SinglePartitionRebuildOption> {

    public SinglePartitionRebuildOptionBuilder(SinglePartitionRebuildOption target) {
        super(target);
    }

    /**
     * set
     * @param useSortInTempDb sort in temp db
     * @return THIS
     */
    public SinglePartitionRebuildOptionBuilder<ParentBuilder> withUseSortInTempDb(boolean useSortInTempDb){
        target.setUseSortInTempDb(useSortInTempDb);
        return this;
    }

    /**
     * set
     * @param maxDegreeOfParallelism max degree of parallelism
     * @return THIS
     */
    public SinglePartitionRebuildOptionBuilder<ParentBuilder> withMaxDegreeOfParallelism(NumberConstant maxDegreeOfParallelism){
        target.setMaxDegreeOfParallelism(maxDegreeOfParallelism);
        return this;
    }

    /**
     * set
     * @param dataCompression DataCompression
     * @return THIS
     */
    public SinglePartitionRebuildOptionBuilder<ParentBuilder> withDataCompression(IndexOption.DataCompression dataCompression){
        target.setDataCompression(dataCompression);
        return this;
    }

    /**
     * set
     * @param useOnline online
     * @return THIS
     */
    public SinglePartitionRebuildOptionBuilder<ParentBuilder> withUseOnline(boolean useOnline){
        target.setUseOnline(useOnline);
        return this;
    }

    /**
     * set
     * @param lowPriorityLockWait LowPriorityLockWait
     * @return THIS
     */
    public SinglePartitionRebuildOptionBuilder<ParentBuilder> withLowPriorityLockWait(LowPriorityLockWait lowPriorityLockWait){
        target.setLowPriorityLockWait(lowPriorityLockWait);
        return this;
    }




    /*
    Quick
     */

    /**
     * Quick set
     * @return THIS
     */
    public SinglePartitionRebuildOptionBuilder<ParentBuilder> $SortInTempdb$On(){
        return withUseOnline(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public SinglePartitionRebuildOptionBuilder<ParentBuilder> $SortInTempdb$Off(){
        return withUseOnline(false);
    }

    /**
     * Quick set
     * @param maxDegreeOfParallelism max degree of parallelism
     * @return THIS
     */
    public SinglePartitionRebuildOptionBuilder<ParentBuilder> $Maxdop(Integer maxDegreeOfParallelism){
        return withMaxDegreeOfParallelism(
                c_unsigned_integer(maxDegreeOfParallelism));
    }

    /**
     * Quick set
     * @return THIS
     */
    public SinglePartitionRebuildOptionBuilder<ParentBuilder> $DataCompression$None(){
        return withDataCompression(IndexOption.DataCompression.NONE);
    }

    /**
     * Quick set
     * @return THIS
     */
    public SinglePartitionRebuildOptionBuilder<ParentBuilder> $DataCompression$Row(){
        return withDataCompression(IndexOption.DataCompression.ROW);
    }

    /**
     * Quick set
     * @return THIS
     */
    public SinglePartitionRebuildOptionBuilder<ParentBuilder> $DataCompression$Page(){
        return withDataCompression(IndexOption.DataCompression.PAGE);
    }

    /**
     * Quick set
     * @return THIS
     */
    public SinglePartitionRebuildOptionBuilder<ParentBuilder> $DataCompression$Columnstore(){
        return withDataCompression(IndexOption.DataCompression.COLUMNSTORE);
    }

    /**
     * Quick set
     * @return THIS
     */
    public SinglePartitionRebuildOptionBuilder<ParentBuilder> $DataCompression$ColumnstoreArchive(){
        return withDataCompression(IndexOption.DataCompression.COLUMNSTORE_ARCHIVE);
    }

    /**
     * Quick set
     * @return THIS
     */
    public SinglePartitionRebuildOptionBuilder<ParentBuilder> $Online$On(){
        return withUseOnline(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public LowPriorityLockWaitBuilder<SinglePartitionRebuildOptionBuilder<ParentBuilder>> $Online$On$(){
        return new LowPriorityLockWaitBuilder<SinglePartitionRebuildOptionBuilder<ParentBuilder>>
                (initSet(LowPriorityLockWait::new,
                        target::getLowPriorityLockWait,
                        target::setLowPriorityLockWait))
                .in(this);
    }

    /**
     * Quick set
     * @return THIS
     */
    public SinglePartitionRebuildOptionBuilder<ParentBuilder> $Online$Off(){
        return withUseOnline(false);
    }

}
