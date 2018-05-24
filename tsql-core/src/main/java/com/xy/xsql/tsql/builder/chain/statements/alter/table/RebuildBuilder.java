package com.xy.xsql.tsql.builder.chain.statements.alter.table;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.index.IndexOptionBuilder;
import com.xy.xsql.tsql.model.datatypes.table.index.IndexOption;
import com.xy.xsql.tsql.model.statements.alter.table.Rebuild;
import com.xy.xsql.tsql.model.statements.alter.table.SinglePartitionRebuildOption;

import java.util.Arrays;
import java.util.List;

import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * RebuildBuilder
 * Created by xiaoyao9184 on 2017/9/16.
 */
@SuppressWarnings({"WeakerAccess", "unused","TypeParameterHidesVisibleType"})
public class RebuildBuilder<ParentBuilder>
        extends CodeTreeBuilder<RebuildBuilder<ParentBuilder>,ParentBuilder,Rebuild> {

    public RebuildBuilder(Rebuild target) {
        super(target);
    }

    /**
     * set
     * @param useAll all
     * @return THIS
     */
    public RebuildBuilder<ParentBuilder> withUseAll(boolean useAll){
        target.setUseAll(useAll);
        return this;
    }

    /**
     * set
     * @param rebuildOptions IndexOption
     * @return THIS
     */
    public RebuildBuilder<ParentBuilder> withRebuildOptions(List<IndexOption> rebuildOptions){
        target.setRebuildOptions(rebuildOptions);
        return this;
    }

    /**
     * set
     * @param rebuildOptions IndexOption
     * @return THIS
     */
    public RebuildBuilder<ParentBuilder> withRebuildOptions(IndexOption... rebuildOptions){
        target.setRebuildOptions(Arrays.asList(rebuildOptions));
        return this;
    }

    /**
     * set
     * @param partitionNumber partition number
     * @return THIS
     */
    public RebuildBuilder<ParentBuilder> withPartitionNumber(Integer partitionNumber){
        target.setPartitionNumber(partitionNumber);
        return this;
    }

    /**
     * set
     * @param singlePartitionRebuildOptions SinglePartitionRebuildOption
     * @return THIS
     */
    public RebuildBuilder<ParentBuilder> withSinglePartitionRebuildOptions(List<SinglePartitionRebuildOption> singlePartitionRebuildOptions){
        target.setSinglePartitionRebuildOptions(singlePartitionRebuildOptions);
        return this;
    }

    /**
     * set
     * @param singlePartitionRebuildOptions SinglePartitionRebuildOption
     * @return THIS
     */
    public RebuildBuilder<ParentBuilder> withSinglePartitionRebuildOptions(SinglePartitionRebuildOption... singlePartitionRebuildOptions){
        target.setSinglePartitionRebuildOptions(Arrays.asList(singlePartitionRebuildOptions));
        return this;
    }




    /*
    Quick
     */

    /**
     * Quick set
     * @return THIS
     */
    public PartitionBuilder<ParentBuilder> $Partition$All(){
        return new PartitionBuilder<ParentBuilder>
                (target)
                .in(out())
                .withUseAll(true);
    }

    /**
     * Quick set
     * @param partitionNumber partition number
     * @return THIS
     */
    public SinglePartitionBuilder<ParentBuilder> $Partition(Integer partitionNumber){
        return new SinglePartitionBuilder<ParentBuilder>
                (target)
                .in(out())
                .withPartitionNumber(partitionNumber);
    }

    /**
     * PartitionBuilder
     * @param <ParentBuilder>
     */
    public class PartitionBuilder<ParentBuilder>
            extends CodeTreeBuilder<PartitionBuilder<ParentBuilder>,ParentBuilder,Rebuild> {

        public PartitionBuilder(Rebuild target) {
            super(target);
        }

        /**
         * set
         * @param useAll all
         * @return THIS
         */
        public PartitionBuilder<ParentBuilder> withUseAll(boolean useAll){
            target.setUseAll(useAll);
            return this;
        }

        /**
         * set
         * @param rebuildOptions IndexOption
         * @return THIS
         */
        public PartitionBuilder<ParentBuilder> withRebuildOptions(List<IndexOption> rebuildOptions){
            target.setRebuildOptions(rebuildOptions);
            return this;
        }

        /**
         * set
         * @param rebuildOptions IndexOption
         * @return THIS
         */
        public PartitionBuilder<ParentBuilder> withRebuildOptions(IndexOption... rebuildOptions){
            target.setRebuildOptions(Arrays.asList(rebuildOptions));
            return this;
        }




        /*
        Quick
         */

        /**
         * Quick in
         * @return IndexOptionBuilder
         */
        public IndexOptionBuilder<PartitionBuilder<ParentBuilder>> $With(){
            return new IndexOptionBuilder<PartitionBuilder<ParentBuilder>>
                    (initNew(IndexOption::new,
                            target::getRebuildOptions,
                            target::setRebuildOptions))
                    .in(this);
        }

    }

    /**
     * SinglePartitionBuilder
     * @param <ParentBuilder>
     */
    public class SinglePartitionBuilder<ParentBuilder>
            extends CodeTreeBuilder<SinglePartitionBuilder<ParentBuilder>,ParentBuilder,Rebuild> {

        public SinglePartitionBuilder(Rebuild target) {
            super(target);
        }

        /**
         * set
         * @param partitionNumber partition number
         * @return THIS
         */
        public SinglePartitionBuilder<ParentBuilder> withPartitionNumber(Integer partitionNumber){
            target.setPartitionNumber(partitionNumber);
            return this;
        }

        /**
         * set
         * @param singlePartitionRebuildOptions SinglePartitionRebuildOption
         * @return THIS
         */
        public SinglePartitionBuilder<ParentBuilder> withSinglePartitionRebuildOptions(List<SinglePartitionRebuildOption> singlePartitionRebuildOptions){
            target.setSinglePartitionRebuildOptions(singlePartitionRebuildOptions);
            return this;
        }

        /**
         * set
         * @param singlePartitionRebuildOptions SinglePartitionRebuildOption
         * @return THIS
         */
        public SinglePartitionBuilder<ParentBuilder> withSinglePartitionRebuildOptions(SinglePartitionRebuildOption... singlePartitionRebuildOptions){
            target.setSinglePartitionRebuildOptions(Arrays.asList(singlePartitionRebuildOptions));
            return this;
        }




        /*
        Quick
         */

        /**
         * Quick in
         * @return SinglePartitionRebuildOptionBuilder
         */
        public SinglePartitionRebuildOptionBuilder<SinglePartitionBuilder<ParentBuilder>> $With(){
            return new SinglePartitionRebuildOptionBuilder<SinglePartitionBuilder<ParentBuilder>>
                    (initNew(SinglePartitionRebuildOption::new,
                            target::getSinglePartitionRebuildOptions,
                            target::setSinglePartitionRebuildOptions))
                    .in(this);
        }

    }

}
