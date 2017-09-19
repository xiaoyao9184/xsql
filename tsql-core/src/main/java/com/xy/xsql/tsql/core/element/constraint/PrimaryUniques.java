package com.xy.xsql.tsql.core.element.constraint;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.core.element.index.PartitionBuilder;
import com.xy.xsql.tsql.model.element.constraint.PrimaryUnique;
import com.xy.xsql.tsql.model.element.index.IndexOption;
import com.xy.xsql.tsql.model.element.index.Partition;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * Created by xiaoyao9184 on 2017/8/17.
 */
public class PrimaryUniques {


    /**
     *
     * @param <ParentBuilder>
     */
    public static class ColumnPrimaryUniqueBuilder<ParentBuilder>
            extends CodeTreeBuilder<ColumnPrimaryUniqueBuilder<ParentBuilder>,ParentBuilder,PrimaryUnique> {

        public ColumnPrimaryUniqueBuilder(PrimaryUnique tar) {
            super(tar);
        }

        public ColumnPrimaryUniqueBuilder() {
            super(new PrimaryUnique());
        }


        public ColumnPrimaryUniqueBuilder<ParentBuilder> withUsePrimaryKey(boolean usePrimaryKey){
            target.setUsePrimaryKey(usePrimaryKey);
            return this;
        }

        public ColumnPrimaryUniqueBuilder<ParentBuilder> withUseClustered(boolean useClustered){
            target.setUseClustered(useClustered);
            return this;
        }

        public ColumnPrimaryUniqueBuilder<ParentBuilder> withFillfactor(String fillfactor){
            target.setFillfactor(fillfactor);
            return this;
        }

        public ColumnPrimaryUniqueBuilder<ParentBuilder> withIndexOptions(List<IndexOption> indexOptions){
            target.setIndexOptions(indexOptions);
            return this;
        }

        public ColumnPrimaryUniqueBuilder<ParentBuilder> withOn(Partition on){
            target.setOn(on);
            return this;
        }

        /*
        Quick
         */

        /**
         * Quick inout set useClustered
         * @return THIS
         */
        public ColumnPrimaryUniqueBuilder<ParentBuilder> $CLUSTERED(){
            return withUseClustered(true);
        }


        /**
         * Quick inout set useClustered
         * @return THIS
         */
        public ColumnPrimaryUniqueBuilder<ParentBuilder> $NONCLUSTERED(){
            return withUseClustered(false);
        }

        /**
         * Quick inout set fillfactor
         * @param fillfactor fillfactor
         * @return THIS
         */
        public ColumnPrimaryUniqueBuilder<ParentBuilder> $WITH_FILLFACTOR(String fillfactor){
            return withFillfactor(fillfactor);
        }

//    /**
//     * Quick in
//     * @return
//     */
//    public PrimaryUniques $WITH(){
//        target.setLogicalExpression(logicalExpression);
//        return this.and();
//    }

        public PartitionBuilder<ColumnPrimaryUniqueBuilder> $On(){
            return new PartitionBuilder<ColumnPrimaryUniqueBuilder>
                    (initSet(Partition::new,
                            target::getOn,
                            target::setOn))
                    .in(this);
        }
    }


    /**
     *
     * @param <ParentBuilder>
     */
    public static class TablePrimaryUniqueBuilder<ParentBuilder>
            extends CodeTreeBuilder<TablePrimaryUniqueBuilder<ParentBuilder>,ParentBuilder,PrimaryUnique> {

        public TablePrimaryUniqueBuilder(PrimaryUnique tar) {
            super(tar);
        }


        public TablePrimaryUniqueBuilder<ParentBuilder> withUsePrimaryKey(boolean usePrimaryKey){
            target.setUsePrimaryKey(usePrimaryKey);
            return this;
        }

        public TablePrimaryUniqueBuilder<ParentBuilder> withUseClustered(boolean useClustered){
            target.setUseClustered(useClustered);
            return this;
        }

        public TablePrimaryUniqueBuilder<ParentBuilder> withColumn(List<PrimaryUnique.Column> columns){
            target.setColumns(columns);
            return this;
        }

        public PrimaryUniqueColumnBuilder<TablePrimaryUniqueBuilder> withColumn(){
            return new PrimaryUniqueColumnBuilder<TablePrimaryUniqueBuilder>
                    (initNew(PrimaryUnique.Column::new,
                            target::getColumns,
                            target::setColumns))
                    .in(this);
        }

        public TablePrimaryUniqueBuilder<ParentBuilder> withFillfactor(String fillfactor){
            target.setFillfactor(fillfactor);
            return this;
        }

        public TablePrimaryUniqueBuilder<ParentBuilder> withIndexOptions(List<IndexOption> indexOptions){
            target.setIndexOptions(indexOptions);
            return this;
        }

        public TablePrimaryUniqueBuilder<ParentBuilder> withOn(Partition on){
            target.setOn(on);
            return this;
        }

        /*
        Quick
         */

        /**
         * Quick inout set useClustered
         * @return THIS
         */
        public TablePrimaryUniqueBuilder $CLUSTERED(){
            return withUseClustered(true);
        }


        /**
         * Quick set useClustered
         * @return THIS
         */
        public TablePrimaryUniqueBuilder $NONCLUSTERED(){
            return withUseClustered(false);
        }

        /**
         * Quick set
         * @return THIS
         */
        public PrimaryUniqueColumnBuilder<TablePrimaryUniqueBuilder> $(){
            return withColumn();
        }

        /**
         * Quick set
         * @param column column
         * @return THIS
         */
        public TablePrimaryUniqueBuilder $(String column){
            return withColumn()
                    .withName(column)
                    .and();
        }

        /**
         * Quick inout set fillfactor
         * @param fillfactor fillfactor
         * @return THIS
         */
        public TablePrimaryUniqueBuilder $WITH_FILLFACTOR(String fillfactor){
            return withFillfactor(fillfactor);
        }

//    /**
//     * Quick in
//     * @return
//     */
//    public TablePrimaryUniqueBuilder $WITH(){
//        target.setLogicalExpression(logicalExpression);
//        return this.and();
//    }

        public PartitionBuilder<TablePrimaryUniqueBuilder> $On(){
            return new PartitionBuilder<TablePrimaryUniqueBuilder>
                    (initSet(Partition::new,
                            target::getOn,
                            target::setOn))
                    .in(this);
        }

    }


    /**
     * Use
     * in column_constraint --Memory optimized CREATE TABLE Syntax
     * @param <ParentBuilder>
     */
    public static class HashBucketCountColumnPrimaryUniqueBuilder<ParentBuilder>
            extends CodeTreeBuilder<HashBucketCountColumnPrimaryUniqueBuilder<ParentBuilder>,ParentBuilder,PrimaryUnique> {

        public HashBucketCountColumnPrimaryUniqueBuilder(PrimaryUnique tar) {
            super(tar);
        }


        public HashBucketCountColumnPrimaryUniqueBuilder<ParentBuilder> withUsePrimaryKey(boolean usePrimaryKey){
            target.setUsePrimaryKey(usePrimaryKey);
            return this;
        }

        public HashBucketCountColumnPrimaryUniqueBuilder<ParentBuilder> withUseClustered(){
            target.setUseClustered(false);
            return this;
        }

        public HashBucketCountColumnPrimaryUniqueBuilder<ParentBuilder> withBucketCount(Integer bucketCount){
            target.setBucketCount(bucketCount);
            return this;
        }

        /*
        Quick
         */

        /**
         * Quick set useClustered
         * @return THIS
         */
        public HashBucketCountColumnPrimaryUniqueBuilder<ParentBuilder> $NONCLUSTERED(){
            return withUseClustered();
        }

        /**
         * Quick set bucketCount
         * @param bucketCount bucketCount
         * @return THIS
         */
        public HashBucketCountColumnPrimaryUniqueBuilder<ParentBuilder> $HASH_WITH_BUCKET_COUNT(Integer bucketCount){
            return withUseClustered()
                    .withBucketCount(bucketCount);
        }

    }


    /**
     * Use
     * in --Memory optimized CREATE TABLE Syntax
     * @param <ParentBuilder>
     */
    public static class HashBucketCountTablePrimaryUniqueBuilder<ParentBuilder>
            extends CodeTreeBuilder<HashBucketCountTablePrimaryUniqueBuilder<ParentBuilder>,ParentBuilder,PrimaryUnique> {

        public HashBucketCountTablePrimaryUniqueBuilder(PrimaryUnique tar) {
            super(tar);
        }


        public HashBucketCountTablePrimaryUniqueBuilder<ParentBuilder> withUsePrimaryKey(boolean usePrimaryKey){
            target.setUsePrimaryKey(usePrimaryKey);
            return this;
        }

        public HashBucketCountTablePrimaryUniqueBuilder<ParentBuilder> withUseClustered(){
            target.setUseClustered(false);
            return this;
        }

        public HashBucketCountTablePrimaryUniqueBuilder<ParentBuilder> withColumn(List<PrimaryUnique.Column> columns){
            target.setColumns(columns);
            return this;
        }

        public PrimaryUniqueColumnBuilder<HashBucketCountTablePrimaryUniqueBuilder> withColumn(){
            return new PrimaryUniqueColumnBuilder<HashBucketCountTablePrimaryUniqueBuilder>
                    (initNew(PrimaryUnique.Column::new,
                            target::getColumns,
                            target::setColumns))
                    .in(this);
        }

        public HashBucketCountTablePrimaryUniqueBuilder<ParentBuilder> withBucketCount(Integer bucketCount){
            target.setBucketCount(bucketCount);
            return this;
        }

        /*
        Quick
         */

        /**
         * Quick set useClustered
         * @return THIS
         */
        public HashBucketCountTablePrimaryUniqueBuilder $NONCLUSTERED(){
            return withUseClustered();
        }

        /**
         * Quick into
         * @return PrimaryUniqueColumnBuilder
         */
        public PrimaryUniqueColumnBuilder<HashBucketCountTablePrimaryUniqueBuilder> $(){
            return withColumn();
        }

        /**
         * Quick set column
         * @param column column
         * @return THIS
         */
        public HashBucketCountTablePrimaryUniqueBuilder $(String column){
            return withColumn()
                    .withName(column)
                    .and();
        }

        /**
         * Quick set columns
         * @param columns columns
         * @return THIS
         */
        public HashBucketCountTablePrimaryUniqueBuilder $HASH(String... columns){
            initAdd(Stream.of(columns)
                            .map(PrimaryUnique.Column::new)
                            .collect(Collectors.toList()),
                    target::getColumns,
                    target::setColumns);
            return this;
        }

        /**
         * Quick set bucketCount
         * @param bucketCount bucketCount
         * @return THIS
         */
        public HashBucketCountTablePrimaryUniqueBuilder<ParentBuilder> $WITH_BUCKET_COUNT(Integer bucketCount){
            return withUseClustered()
                    .withBucketCount(bucketCount);
        }

    }


    /**
     * Use
     * in
     * @param <ParentBuilder>
     */
    public static class PrimaryUniqueColumnBuilder<ParentBuilder>
            extends CodeTreeBuilder<PrimaryUniqueColumnBuilder<ParentBuilder>,ParentBuilder,PrimaryUnique.Column> {

        public PrimaryUniqueColumnBuilder(PrimaryUnique.Column column) {
            super(column);
        }

        public PrimaryUniqueColumnBuilder<ParentBuilder> withName(String column){
            target.setColumn(column);
            return this;
        }

        public PrimaryUniqueColumnBuilder<ParentBuilder> withUseAsc(boolean useAsc){
            target.setUseAsc(useAsc);
            return this;
        }

        /*
        Quick
         */

        /**
         * Quick set
         * @param column column
         * @return THIS
         */
        public PrimaryUniqueColumnBuilder<ParentBuilder> $(String column){
            return withName(column);
        }

        /**
         * Quick set
         * @return ParentBuilder
         */
        public ParentBuilder $Asc(){
            return withUseAsc(true)
                    .and();
        }

        /**
         * Quick set
         * @return ParentBuilder
         */
        public ParentBuilder $Desc(){
            return withUseAsc(false)
                    .and();
        }

    }

}
