package com.xy.xsql.tsql.builder.chain.datatypes.table.constraint;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.index.PartitionBuilder;
import com.xy.xsql.tsql.model.datatypes.table.constraint.PrimaryUnique;
import com.xy.xsql.tsql.model.datatypes.table.index.IndexOption;
import com.xy.xsql.tsql.model.datatypes.table.index.Partition;

import java.util.List;
import java.util.stream.Stream;

import static com.xy.xsql.core.handler.list.ListHandler.list;
import static com.xy.xsql.core.handler.object.GetterSetterObjectHandler.object;

/**
 * PrimaryUnique Builders
 * Created by xiaoyao9184 on 2017/8/17.
 */
@SuppressWarnings({"unused","WeakerAccess"})
public class PrimaryUniques {


    /**
     * ColumnPrimaryUniqueBuilder
     * @param <ParentBuilder>
     */
    public static class ColumnPrimaryUniqueBuilder<ParentBuilder>
            extends ParentHoldBuilder<ColumnPrimaryUniqueBuilder<ParentBuilder>,ParentBuilder,PrimaryUnique> {

        public ColumnPrimaryUniqueBuilder() {
            super(new PrimaryUnique());
        }

        public ColumnPrimaryUniqueBuilder(PrimaryUnique target) {
            super(target);
        }

        /**
         * set
         * @param usePrimaryKey primary key
         * @return THIS
         */
        public ColumnPrimaryUniqueBuilder<ParentBuilder> withUsePrimaryKey(boolean usePrimaryKey){
            target.setUsePrimaryKey(usePrimaryKey);
            return this;
        }

        /**
         * set
         * @param useClustered clustered
         * @return THIS
         */
        public ColumnPrimaryUniqueBuilder<ParentBuilder> withUseClustered(boolean useClustered){
            target.setUseClustered(useClustered);
            return this;
        }

        /**
         * set
         * @param fillfactor fill factor
         * @return THIS
         */
        public ColumnPrimaryUniqueBuilder<ParentBuilder> withFillfactor(String fillfactor){
            target.setFillfactor(fillfactor);
            return this;
        }

        /**
         * set
         * @param indexOptions IndexOption
         * @return THIS
         */
        public ColumnPrimaryUniqueBuilder<ParentBuilder> withIndexOptions(List<IndexOption> indexOptions){
            target.setIndexOptions(indexOptions);
            return this;
        }

        /**
         * set
         * @param on Partition
         * @return THIS
         */
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
        public ColumnPrimaryUniqueBuilder<ParentBuilder> $Clustered(){
            return withUseClustered(true);
        }


        /**
         * Quick inout set useClustered
         * @return THIS
         */
        public ColumnPrimaryUniqueBuilder<ParentBuilder> $Nonclustered(){
            return withUseClustered(false);
        }

        /**
         * Quick inout set fillfactor
         * @param fillfactor fillfactor
         * @return THIS
         */
        public ColumnPrimaryUniqueBuilder<ParentBuilder> $WithFillfactor(String fillfactor){
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

        /**
         * Quick in
         * @return PartitionBuilder
         */
        public PartitionBuilder<ColumnPrimaryUniqueBuilder> $On(){
            return new PartitionBuilder<ColumnPrimaryUniqueBuilder>
                    (object(target::getOn, target::setOn)
                            .init(Partition::new))
                    .in(this);
        }
    }


    /**
     * TablePrimaryUniqueBuilder
     * @param <ParentBuilder>
     */
    public static class TablePrimaryUniqueBuilder<ParentBuilder>
            extends ParentHoldBuilder<TablePrimaryUniqueBuilder<ParentBuilder>,ParentBuilder,PrimaryUnique> {

        public TablePrimaryUniqueBuilder() {
            super(new PrimaryUnique());
        }

        public TablePrimaryUniqueBuilder(PrimaryUnique target) {
            super(target);
        }

        /**
         * set
         * @param usePrimaryKey primary key
         * @return THIS
         */
        public TablePrimaryUniqueBuilder<ParentBuilder> withUsePrimaryKey(boolean usePrimaryKey){
            target.setUsePrimaryKey(usePrimaryKey);
            return this;
        }

        /**
         * set
         * @param useClustered clustered
         * @return THIS
         */
        public TablePrimaryUniqueBuilder<ParentBuilder> withUseClustered(boolean useClustered){
            target.setUseClustered(useClustered);
            return this;
        }

        /**
         * set
         * @param columns Column
         * @return THIS
         */
        public TablePrimaryUniqueBuilder<ParentBuilder> withColumn(List<PrimaryUnique.Column> columns){
            target.setColumns(columns);
            return this;
        }

        /**
         * in
         * @return PrimaryUniqueColumnBuilder
         */
        public PrimaryUniqueColumnBuilder<TablePrimaryUniqueBuilder> withColumn(){
            return new PrimaryUniqueColumnBuilder<TablePrimaryUniqueBuilder>
                    (list(target::getColumns, target::setColumns)
                            .addNew(PrimaryUnique.Column::new))
                    .in(this);
        }

        /**
         * set
         * @param fillfactor fill factor
         * @return THIS
         */
        public TablePrimaryUniqueBuilder<ParentBuilder> withFillfactor(String fillfactor){
            target.setFillfactor(fillfactor);
            return this;
        }

        /**
         * set
         * @param indexOptions IndexOption
         * @return THIS
         */
        public TablePrimaryUniqueBuilder<ParentBuilder> withIndexOptions(List<IndexOption> indexOptions){
            target.setIndexOptions(indexOptions);
            return this;
        }

        /**
         * set
         * @param on Partition
         * @return THIS
         */
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
        public TablePrimaryUniqueBuilder $Clustered(){
            return withUseClustered(true);
        }


        /**
         * Quick set useClustered
         * @return THIS
         */
        public TablePrimaryUniqueBuilder $Nonclustered(){
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
        public TablePrimaryUniqueBuilder $WithFillfactor(String fillfactor){
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

        /**
         * Quick in
         * @return PartitionBuilder
         */
        public PartitionBuilder<TablePrimaryUniqueBuilder> $On(){
            return new PartitionBuilder<TablePrimaryUniqueBuilder>
                    (object(target::getOn, target::setOn)
                            .init(Partition::new))
                    .in(this);
        }

    }


    /**
     * HashBucketCountColumnPrimaryUniqueBuilder
     * Use
     * in column_constraint --Memory optimized CREATE TABLE Syntax
     * @param <ParentBuilder>
     */
    public static class HashBucketCountColumnPrimaryUniqueBuilder<ParentBuilder>
            extends ParentHoldBuilder<HashBucketCountColumnPrimaryUniqueBuilder<ParentBuilder>,ParentBuilder,PrimaryUnique> {

        public HashBucketCountColumnPrimaryUniqueBuilder() {
            super(new PrimaryUnique());
        }

        public HashBucketCountColumnPrimaryUniqueBuilder(PrimaryUnique target) {
            super(target);
        }

        /**
         * set
         * @param usePrimaryKey primary key
         * @return THIS
         */
        public HashBucketCountColumnPrimaryUniqueBuilder<ParentBuilder> withUsePrimaryKey(boolean usePrimaryKey){
            target.setUsePrimaryKey(usePrimaryKey);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public HashBucketCountColumnPrimaryUniqueBuilder<ParentBuilder> withUseClustered(){
            target.setUseClustered(false);
            return this;
        }

        /**
         * set
         * @param bucketCount bucket count
         * @return THIS
         */
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
        public HashBucketCountColumnPrimaryUniqueBuilder<ParentBuilder> $Nonclustered(){
            return withUseClustered();
        }

        /**
         * Quick set bucketCount
         * @param bucketCount bucket count
         * @return THIS
         */
        public HashBucketCountColumnPrimaryUniqueBuilder<ParentBuilder> $HashWithBucketCount(Integer bucketCount){
            return withUseClustered()
                    .withBucketCount(bucketCount);
        }

    }


    /**
     * HashBucketCountTablePrimaryUniqueBuilder
     * Use
     * in --Memory optimized CREATE TABLE Syntax
     * @param <ParentBuilder>
     */
    public static class HashBucketCountTablePrimaryUniqueBuilder<ParentBuilder>
            extends ParentHoldBuilder<HashBucketCountTablePrimaryUniqueBuilder<ParentBuilder>,ParentBuilder,PrimaryUnique> {

        public HashBucketCountTablePrimaryUniqueBuilder() {
            super(new PrimaryUnique());
        }

        public HashBucketCountTablePrimaryUniqueBuilder(PrimaryUnique target) {
            super(target);
        }

        /**
         * set
         * @param usePrimaryKey primary key
         * @return THIS
         */
        public HashBucketCountTablePrimaryUniqueBuilder<ParentBuilder> withUsePrimaryKey(boolean usePrimaryKey){
            target.setUsePrimaryKey(usePrimaryKey);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public HashBucketCountTablePrimaryUniqueBuilder<ParentBuilder> withUseClustered(){
            target.setUseClustered(false);
            return this;
        }

        /**
         * set
         * @param columns Column
         * @return THIS
         */
        public HashBucketCountTablePrimaryUniqueBuilder<ParentBuilder> withColumn(List<PrimaryUnique.Column> columns){
            target.setColumns(columns);
            return this;
        }

        /**
         * in
         * @return PrimaryUniqueColumnBuilder
         */
        public PrimaryUniqueColumnBuilder<HashBucketCountTablePrimaryUniqueBuilder> withColumn(){
            return new PrimaryUniqueColumnBuilder<HashBucketCountTablePrimaryUniqueBuilder>
                    (list(target::getColumns, target::setColumns)
                            .addNew(PrimaryUnique.Column::new))
                    .in(this);
        }

        /**
         * set
         * @param bucketCount bucket count
         * @return THIS
         */
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
         * Quick in
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
        public HashBucketCountTablePrimaryUniqueBuilder $Hash(String... columns){
            list(target::getColumns, target::setColumns)
                    .addAll(Stream.of(columns).map(PrimaryUnique.Column::new));
            return this;
        }

        /**
         * Quick set bucketCount
         * @param bucketCount bucket count
         * @return THIS
         */
        public HashBucketCountTablePrimaryUniqueBuilder<ParentBuilder> $WithBucketCount(Integer bucketCount){
            return withUseClustered()
                    .withBucketCount(bucketCount);
        }

    }


    /**
     * PrimaryUniqueColumnBuilder
     * Use
     * in
     * @param <ParentBuilder>
     */
    public static class PrimaryUniqueColumnBuilder<ParentBuilder>
            extends ParentHoldBuilder<PrimaryUniqueColumnBuilder<ParentBuilder>,ParentBuilder,PrimaryUnique.Column> {

        public PrimaryUniqueColumnBuilder() {
            super(new PrimaryUnique.Column());
        }

        public PrimaryUniqueColumnBuilder(PrimaryUnique.Column target) {
            super(target);
        }

        /**
         * set
         * @param column column
         * @return THIS
         */
        public PrimaryUniqueColumnBuilder<ParentBuilder> withName(String column){
            target.setColumn(column);
            return this;
        }

        /**
         * set
         * @param useAsc asc
         * @return THIS
         */
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
         * @return PARENT
         */
        public ParentBuilder $Asc(){
            return withUseAsc(true)
                    .and();
        }

        /**
         * Quick set
         * @return PARENT
         */
        public ParentBuilder $Desc(){
            return withUseAsc(false)
                    .and();
        }

    }

}
