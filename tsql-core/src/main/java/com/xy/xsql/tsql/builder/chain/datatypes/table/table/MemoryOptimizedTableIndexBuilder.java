package com.xy.xsql.tsql.builder.chain.datatypes.table.table;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.constraint.PrimaryUniques;
import com.xy.xsql.tsql.builder.chain.datatypes.table.index.PartitionBuilder;
import com.xy.xsql.tsql.model.datatypes.table.constraint.PrimaryUnique;
import com.xy.xsql.tsql.model.datatypes.table.index.Partition;
import com.xy.xsql.tsql.model.datatypes.table.table.TableIndex;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * MemoryOptimizedTableIndexBuilder
 * Use
 * in --Memory-Based CREATE TABLE Syntax
 * Created by xiaoyao9184 on 2017/9/1.
 * @param <ParentBuilder>
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class MemoryOptimizedTableIndexBuilder<ParentBuilder>
        extends CodeTreeBuilder<MemoryOptimizedTableIndexBuilder<ParentBuilder>,ParentBuilder,TableIndex> {

    public MemoryOptimizedTableIndexBuilder(TableIndex tableIndex) {
        super(tableIndex);
    }

    public MemoryOptimizedTableIndexBuilder() {
        super(new TableIndex());
    }

    /**
     * set
     * @param indexName index name
     * @return THIS
     */
    public MemoryOptimizedTableIndexBuilder<ParentBuilder> withIndexName(String indexName) {
        target.setIndexName(indexName);
        return this;
    }

    /**
     * set
     * @return THIS
     */
    public MemoryOptimizedTableIndexBuilder<ParentBuilder> withNonClustered() {
        target.setUseNonClustered(true);
        return this;
    }

    /**
     * set
     * @return THIS
     */
    public MemoryOptimizedTableIndexBuilder<ParentBuilder> withClusteredColumnstore() {
        target.setUseClustered(true);
        target.setUseColumnStore(true);
        return this;
    }

    /**
     * set
     * @return THIS
     */
    public MemoryOptimizedTableIndexBuilder<ParentBuilder> withHash() {
        target.setUseHash(true);
        return this;
    }

    /**
     * SET
     * @param columnNameList Column
     * @return THIS
     */
    public MemoryOptimizedTableIndexBuilder<ParentBuilder> withColumnNameList(List<PrimaryUnique.Column> columnNameList) {
        target.setColumnNameList(columnNameList);
        return this;
    }

    /**
     * set
     * @param bucketCount bucket count
     * @return THIS
     */
    public MemoryOptimizedTableIndexBuilder<ParentBuilder> withBucketCount(Integer bucketCount) {
        target.setBucketCount(bucketCount);
        return this;
    }

    /**
     * set
     * @param delay delay
     * @return THIS
     */
    public MemoryOptimizedTableIndexBuilder<ParentBuilder> withCompressionDelay(Integer delay) {
        target.setDelay(delay);
        return this;
    }

    /**
     * set
     * @param partition Partition
     * @return THIS
     */
    public MemoryOptimizedTableIndexBuilder<ParentBuilder> withPartition(Partition partition) {
        target.setPartition(partition);
        return this;
    }




    /*
    Quick
     */

    /**
     * Quick set
     * @param indexName indexName
     * @return THIS
     */
    public MemoryOptimizedTableIndexBuilder<ParentBuilder> $Index(String indexName) {
        return withIndexName(indexName);
    }

    /**
     * Quick set
     * @return THIS
     */
    public MemoryOptimizedTableIndexBuilder<ParentBuilder> $Nonclustered(){
        return withNonClustered();
    }

    /**
     * Quick set
     * @param columns columns
     * @return THIS
     */
    public MemoryOptimizedTableIndexBuilder $Hash(String... columns){
        initAdd(Stream.of(columns)
                        .map(PrimaryUnique.Column::new)
                        .collect(Collectors.toList()),
                target::getColumnNameList,
                target::setColumnNameList);
        return withHash();
    }

    /**
     * Quick set
     * @param columns columns
     * @return THIS
     */
    public MemoryOptimizedTableIndexBuilder $(String... columns){
        initAdd(Stream.of(columns)
                        .map(PrimaryUnique.Column::new)
                        .collect(Collectors.toList()),
                target::getColumnNameList,
                target::setColumnNameList);
        return this;
    }

    /**
     * Quick in
     * @return PrimaryUniqueColumnBuilder
     */
    public PrimaryUniques.PrimaryUniqueColumnBuilder<MemoryOptimizedTableIndexBuilder> $(String columnName){
        return new PrimaryUniques.PrimaryUniqueColumnBuilder<MemoryOptimizedTableIndexBuilder>
                (initNew(PrimaryUnique.Column::new,
                        target::getColumnNameList,
                        target::setColumnNameList))
                .in(this)
                .withName(columnName);
    }

    /**
     * Quick set
     * @param bucketCount bucket count
     * @return THIS
     */
    public MemoryOptimizedTableIndexBuilder<ParentBuilder> $WithBucketCount(Integer bucketCount){
        return withBucketCount(bucketCount);
    }

    /**
     * Quick set
     * @param delay delay
     * @return THIS
     */
    public MemoryOptimizedTableIndexBuilder<ParentBuilder> $WithCompressionDelay(Integer delay){
        return withCompressionDelay(delay);
    }

    /**
     * TODO only filegroup_name | default
     * Quick set
     * @return THIS
     */
    public PartitionBuilder<MemoryOptimizedTableIndexBuilder<ParentBuilder>> $On(){
        return new PartitionBuilder<MemoryOptimizedTableIndexBuilder<ParentBuilder>>
                (initSet(Partition::new,
                        target::getPartition,
                        target::setPartition))
                .in(this);
    }
}
