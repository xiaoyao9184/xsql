package com.xy.xsql.tsql.core.element.table;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.core.element.constraint.PrimaryUniques;
import com.xy.xsql.tsql.core.element.index.IndexOptionBuilder;
import com.xy.xsql.tsql.core.element.index.PartitionBuilder;
import com.xy.xsql.tsql.model.datatypes.table.constraint.PrimaryUnique;
import com.xy.xsql.tsql.model.datatypes.table.index.IndexOption;
import com.xy.xsql.tsql.model.datatypes.table.index.Partition;
import com.xy.xsql.tsql.model.datatypes.table.table.TableIndex;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * Use
 * in --Disk-Based CREATE TABLE Syntax
 * Created by xiaoyao9184 on 2017/8/18.
 * @param <ParentBuilder>
 */
public class TableIndexBuilder<ParentBuilder>
        extends CodeTreeBuilder<TableIndexBuilder<ParentBuilder>,ParentBuilder,TableIndex> {

    public TableIndexBuilder(TableIndex tableIndex) {
        super(tableIndex);
    }

    public TableIndexBuilder() {
        super(new TableIndex());
    }

    public TableIndexBuilder<ParentBuilder> withIndexName(String indexName) {
        target.setIndexName(indexName);
        return this;
    }

    public TableIndexBuilder<ParentBuilder> withClustered() {
        target.setUseClustered(true);
        return this;
    }

    public TableIndexBuilder<ParentBuilder> withNonClustered() {
        target.setUseNonClustered(true);
        return this;
    }

    public TableIndexBuilder<ParentBuilder> withColumnstore() {
        target.setUseColumnStore(true);
        return this;
    }

    public TableIndexBuilder<ParentBuilder> withColumnNameList(List<PrimaryUnique.Column> columnNameList) {
        target.setColumnNameList(columnNameList);
        return this;
    }

    public TableIndexBuilder<ParentBuilder> withIndexOptionList(List<IndexOption> indexOptionList) {
        target.setIndexOptionList(indexOptionList);
        return this;
    }

    public TableIndexBuilder<ParentBuilder> withPartition(Partition partition) {
        target.setPartition(partition);
        return this;
    }

    public TableIndexBuilder<ParentBuilder> withFileStreamPartition(Partition partition) {
        target.setFileStreamPartition(partition);
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
    public TableIndexBuilder<ParentBuilder> $INDEX(String indexName) {
        return withIndexName(indexName);
    }

    /**
     * Quick set
     * @return THIS
     */
    public TableIndexBuilder<ParentBuilder> $CLUSTERED() {
        return withClustered();
    }

    /**
     * Quick set
     * @return THIS
     */
    public TableIndexBuilder<ParentBuilder> $NONCLUSTERED() {
        return withNonClustered();
    }

    /**
     * Quick set
     * @param columnNames columnNames
     * @return THIS
     */
    public TableIndexBuilder<ParentBuilder> $(String... columnNames) {
        List<PrimaryUnique.Column> columnNameList = Stream.of(columnNames)
                .map(columnName -> {
                    PrimaryUnique.Column c = new PrimaryUnique.Column();
                    c.setColumn(columnName);
                    return c;
                })
                .collect(Collectors.toList());

        return withColumnNameList(columnNameList);
    }

    /**
     * Quick into
     * @param columnName columnName
     * @return PrimaryUniqueColumnBuilder
     */
    public PrimaryUniques.PrimaryUniqueColumnBuilder<TableIndexBuilder<ParentBuilder>> $(String columnName) {
        return new PrimaryUniques.PrimaryUniqueColumnBuilder<TableIndexBuilder<ParentBuilder>>
                (initNew(PrimaryUnique.Column::new,
                        target::getColumnNameList,
                        target::setColumnNameList))
                .in(this)
                .withName(columnName);
    }

    /**
     * Quick set
     * @return THIS
     */
    public TableIndexBuilder<ParentBuilder> $COLUMNSTORE() {
        return withColumnstore();
    }

    /**
     * Quick set
     * @param columnNames columnNames
     * @return THIS
     */
    public TableIndexBuilder<ParentBuilder> $COLUMNSTORE(String... columnNames) {
        List<PrimaryUnique.Column> columnNameList = Stream.of(columnNames)
                .map(columnName -> {
                    PrimaryUnique.Column c = new PrimaryUnique.Column();
                    c.setColumn(columnName);
                    return c;
                })
                .collect(Collectors.toList());

        return withColumnstore()
                .withColumnNameList(columnNameList);
    }

    /**
     * Quick into
     * @return IndexOptionBuilder
     */
    public IndexOptionBuilder<TableIndexBuilder<ParentBuilder>> $With(){
        return new IndexOptionBuilder<TableIndexBuilder<ParentBuilder>>
                (initNew(IndexOption::new,
                        target::getIndexOptionList,
                        target::setIndexOptionList))
                .in(this);
    }

    /**
     * Quick into
     * @return PartitionBuilder
     */
    public PartitionBuilder<TableIndexBuilder<ParentBuilder>> $On(){
        return new PartitionBuilder<TableIndexBuilder<ParentBuilder>>
                (initSet(Partition::new,
                        target::getPartition,
                        target::setPartition))
                .in(this);
    }

    /**
     * Quick into
     * @return PartitionBuilder
     */
    public PartitionBuilder<TableIndexBuilder<ParentBuilder>> $FileStream_On(){
        return new PartitionBuilder<TableIndexBuilder<ParentBuilder>>
                (initSet(Partition::new,
                        target::getFileStreamPartition,
                        target::setFileStreamPartition))
                .in(this);
    }

}
