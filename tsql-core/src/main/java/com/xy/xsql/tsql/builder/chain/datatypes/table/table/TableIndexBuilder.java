package com.xy.xsql.tsql.builder.chain.datatypes.table.table;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.constraint.PrimaryUniques;
import com.xy.xsql.tsql.builder.chain.datatypes.table.index.IndexOptionBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.index.PartitionBuilder;
import com.xy.xsql.tsql.model.datatypes.table.constraint.PrimaryUnique;
import com.xy.xsql.tsql.model.datatypes.table.index.IndexOption;
import com.xy.xsql.tsql.model.datatypes.table.index.Partition;
import com.xy.xsql.tsql.model.datatypes.table.table.TableIndex;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.xy.xsql.core.handler.list.ListHandler.list;
import static com.xy.xsql.core.handler.object.GetterSetterObjectHandler.object;

/**
 * TableIndexBuilder
 * Use
 * in --Disk-Based CREATE TABLE Syntax
 * Created by xiaoyao9184 on 2017/8/18.
 * @param <ParentBuilder>
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class TableIndexBuilder<ParentBuilder>
        extends ParentHoldBuilder<TableIndexBuilder<ParentBuilder>,ParentBuilder,TableIndex> {

    public TableIndexBuilder() {
        super(new TableIndex());
    }

    public TableIndexBuilder(TableIndex target) {
        super(target);
    }

    /**
     * set
     * @param indexName index name
     * @return THIS
     */
    public TableIndexBuilder<ParentBuilder> withIndexName(String indexName) {
        target.setIndexName(indexName);
        return this;
    }

    /**
     * set
     * @return THIS
     */
    public TableIndexBuilder<ParentBuilder> withClustered() {
        target.setUseClustered(true);
        return this;
    }

    /**
     * set
     * @return THIS
     */
    public TableIndexBuilder<ParentBuilder> withNonClustered() {
        target.setUseNonClustered(true);
        return this;
    }

    /**
     * set
     * @return THIS
     */
    public TableIndexBuilder<ParentBuilder> withColumnstore() {
        target.setUseColumnStore(true);
        return this;
    }

    /**
     * set
     * @param columnNameList Column
     * @return THIS
     */
    public TableIndexBuilder<ParentBuilder> withColumnNameList(List<PrimaryUnique.Column> columnNameList) {
        target.setColumnNameList(columnNameList);
        return this;
    }

    /**
     * set
     * @param indexOptionList IndexOption
     * @return THIS
     */
    public TableIndexBuilder<ParentBuilder> withIndexOptionList(List<IndexOption> indexOptionList) {
        target.setIndexOptionList(indexOptionList);
        return this;
    }

    /**
     * SET
     * @param partition Partition
     * @return THIS
     */
    public TableIndexBuilder<ParentBuilder> withPartition(Partition partition) {
        target.setPartition(partition);
        return this;
    }

    /**
     * set
     * @param partition Partition
     * @return THIS
     */
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
    public TableIndexBuilder<ParentBuilder> $Index(String indexName) {
        return withIndexName(indexName);
    }

    /**
     * Quick set
     * @return THIS
     */
    public TableIndexBuilder<ParentBuilder> $Clustered() {
        return withClustered();
    }

    /**
     * Quick set
     * @return THIS
     */
    public TableIndexBuilder<ParentBuilder> $Nonclustered() {
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
     * Quick in
     * @param columnName columnName
     * @return PrimaryUniqueColumnBuilder
     */
    public PrimaryUniques.PrimaryUniqueColumnBuilder<TableIndexBuilder<ParentBuilder>> $(String columnName) {
        return new PrimaryUniques.PrimaryUniqueColumnBuilder<TableIndexBuilder<ParentBuilder>>
                (list(target::getColumnNameList, target::setColumnNameList)
                        .addNew(PrimaryUnique.Column::new))
                .in(this)
                .withName(columnName);
    }

    /**
     * Quick set
     * @return THIS
     */
    public TableIndexBuilder<ParentBuilder> $Columnstore() {
        return withColumnstore();
    }

    /**
     * Quick set
     * @param columnNames columnNames
     * @return THIS
     */
    public TableIndexBuilder<ParentBuilder> $Columnstore(String... columnNames) {
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
     * Quick in
     * @return IndexOptionBuilder
     */
    public IndexOptionBuilder<TableIndexBuilder<ParentBuilder>> $With(){
        return new IndexOptionBuilder<TableIndexBuilder<ParentBuilder>>
                (list(target::getIndexOptionList, target::setIndexOptionList)
                        .addNew(IndexOption::new))
                .in(this);
    }

    /**
     * Quick in
     * @return PartitionBuilder
     */
    public PartitionBuilder<TableIndexBuilder<ParentBuilder>> $On(){
        return new PartitionBuilder<TableIndexBuilder<ParentBuilder>>
                (object(target::getPartition, target::setPartition)
                        .init(Partition::new))
                .in(this);
    }

    /**
     * Quick in
     * @return PartitionBuilder
     */
    public PartitionBuilder<TableIndexBuilder<ParentBuilder>> $FileStreamOn(){
        return new PartitionBuilder<TableIndexBuilder<ParentBuilder>>
                (object(target::getFileStreamPartition, target::setFileStreamPartition)
                        .init(Partition::new))
                .in(this);
    }

}
