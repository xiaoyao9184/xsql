package com.xy.xsql.tsql.core.statement.ddl.alter.table;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.core.element.index.IndexOptionBuilder;
import com.xy.xsql.tsql.model.element.index.IndexOption;
import com.xy.xsql.tsql.model.statement.ddl.alter.table.Rebuild;
import com.xy.xsql.tsql.model.statement.ddl.alter.table.SinglePartitionRebuildOption;

import java.util.Arrays;
import java.util.List;

import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * Created by xiaoyao9184 on 2017/9/16.
 */
public class RebuildBuilder<ParentBuilder>
        extends CodeTreeBuilder<RebuildBuilder<ParentBuilder>,ParentBuilder,Rebuild> {

    public RebuildBuilder(Rebuild target) {
        super(target);
    }

    public RebuildBuilder<ParentBuilder> withUseAll(boolean useAll){
        target.setUseAll(useAll);
        return this;
    }

    public RebuildBuilder<ParentBuilder> withRebuildOptions(List<IndexOption> rebuildOptions){
        target.setRebuildOptions(rebuildOptions);
        return this;
    }

    public RebuildBuilder<ParentBuilder> withRebuildOptions(IndexOption... rebuildOptions){
        target.setRebuildOptions(Arrays.asList(rebuildOptions));
        return this;
    }

    public RebuildBuilder<ParentBuilder> withPartitionNumber(Integer partitionNumber){
        target.setPartitionNumber(partitionNumber);
        return this;
    }

    public RebuildBuilder<ParentBuilder> withSinglePartitionRebuildOptions(List<SinglePartitionRebuildOption> singlePartitionRebuildOptions){
        target.setSinglePartitionRebuildOptions(singlePartitionRebuildOptions);
        return this;
    }

    public RebuildBuilder<ParentBuilder> withSinglePartitionRebuildOptions(SinglePartitionRebuildOption... singlePartitionRebuildOptions){
        target.setSinglePartitionRebuildOptions(Arrays.asList(singlePartitionRebuildOptions));
        return this;
    }

    /*
    Quick
     */

    public PartitionBuilder<ParentBuilder> $PARTITION_$ALL(){
        return new PartitionBuilder<ParentBuilder>
                (target)
                .in(out())
                .withUseAll(true);
    }

    public SinglePartitionBuilder<ParentBuilder> $PARTITION(Integer partitionNumber){
        return new SinglePartitionBuilder<ParentBuilder>
                (target)
                .in(out())
                .withPartitionNumber(partitionNumber);
    }


    public class PartitionBuilder<ParentBuilder>
            extends CodeTreeBuilder<PartitionBuilder<ParentBuilder>,ParentBuilder,Rebuild> {

        public PartitionBuilder(Rebuild target) {
            super(target);
        }


        public PartitionBuilder<ParentBuilder> withUseAll(boolean useAll){
            target.setUseAll(useAll);
            return this;
        }

        public PartitionBuilder<ParentBuilder> withRebuildOptions(List<IndexOption> rebuildOptions){
            target.setRebuildOptions(rebuildOptions);
            return this;
        }

        public PartitionBuilder<ParentBuilder> withRebuildOptions(IndexOption... rebuildOptions){
            target.setRebuildOptions(Arrays.asList(rebuildOptions));
            return this;
        }

        /*
        Quick
         */

        public IndexOptionBuilder<PartitionBuilder<ParentBuilder>> $WITH(){
            return new IndexOptionBuilder<PartitionBuilder<ParentBuilder>>
                    (initNew(IndexOption::new,
                            target::getRebuildOptions,
                            target::setRebuildOptions))
                    .in(this);
        }

    }


    public class SinglePartitionBuilder<ParentBuilder>
            extends CodeTreeBuilder<SinglePartitionBuilder<ParentBuilder>,ParentBuilder,Rebuild> {

        public SinglePartitionBuilder(Rebuild target) {
            super(target);
        }


        public SinglePartitionBuilder<ParentBuilder> withPartitionNumber(Integer partitionNumber){
            target.setPartitionNumber(partitionNumber);
            return this;
        }

        public SinglePartitionBuilder<ParentBuilder> withSinglePartitionRebuildOptions(List<SinglePartitionRebuildOption> singlePartitionRebuildOptions){
            target.setSinglePartitionRebuildOptions(singlePartitionRebuildOptions);
            return this;
        }

        public SinglePartitionBuilder<ParentBuilder> withSinglePartitionRebuildOptions(SinglePartitionRebuildOption... singlePartitionRebuildOptions){
            target.setSinglePartitionRebuildOptions(Arrays.asList(singlePartitionRebuildOptions));
            return this;
        }

        /*
        Quick
         */

        public SinglePartitionRebuildOptionBuilder<SinglePartitionBuilder<ParentBuilder>> $WITH(){
            return new SinglePartitionRebuildOptionBuilder<SinglePartitionBuilder<ParentBuilder>>
                    (initNew(SinglePartitionRebuildOption::new,
                            target::getSinglePartitionRebuildOptions,
                            target::setSinglePartitionRebuildOptions))
                    .in(this);
        }

    }

}
