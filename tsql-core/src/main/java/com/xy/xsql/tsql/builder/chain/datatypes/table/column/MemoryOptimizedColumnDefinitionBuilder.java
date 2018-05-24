package com.xy.xsql.tsql.builder.chain.datatypes.table.column;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.datatypes.DataType;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.collation.Collate;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnConstraint;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnDefinition;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnIndex;
import com.xy.xsql.tsql.model.elements.expressions.Expression;

import java.util.Collections;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * MemoryOptimizedColumnDefinitionBuilder
 * Use
 * in --Memory optimized CREATE TABLE Syntax
 * Created by xiaoyao9184 on 2017/3/12.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class MemoryOptimizedColumnDefinitionBuilder<ParentBuilder>
        extends CodeTreeBuilder<MemoryOptimizedColumnDefinitionBuilder<ParentBuilder>,ParentBuilder,ColumnDefinition> {

    public MemoryOptimizedColumnDefinitionBuilder(ColumnDefinition tar) {
        super(tar);
    }

    public MemoryOptimizedColumnDefinitionBuilder() {
        super(new ColumnDefinition());
    }

    /**
     * set
     * @param columnName ColumnName
     * @return THIS
     */
    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> withColumnName(ColumnName columnName) {
        target.setTable(columnName.getTable());
        target.setName(columnName.getName());
        return this;
    }

    /**
     * set
     * @param dataType DataType
     * @return THIS
     */
    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> withDataType(DataType dataType) {
        target.setDataType(dataType);
        return this;
    }

    /**
     * set
     * @param collationName collation name
     * @return THIS
     */
    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> withCollate(Collate collationName) {
        target.setCollationName(collationName);
        return this;
    }

    /**
     * set
     * @param useGeneratedAlwaysStart generated always start
     * @return THIS
     */
    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> withUseGeneratedAlwaysStart(boolean useGeneratedAlwaysStart) {
        target.setUseGeneratedAlwaysStart(useGeneratedAlwaysStart);
        return this;
    }

    /**
     * set
     * @param useGeneratedAlwaysEnd generated always end
     * @return THIS
     */
    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> withUseGeneratedAlwaysEnd(boolean useGeneratedAlwaysEnd) {
        target.setUseGeneratedAlwaysEnd(useGeneratedAlwaysEnd);
        return this;
    }

    /**
     * set
     * @param useHidden hidden
     * @return THIS
     */
    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> withUseHidden(boolean useHidden) {
        target.setUseHidden(useHidden);
        return this;
    }

    /**
     * set
     * @param useNull null
     * @return THIS
     */
    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> withUseNull(boolean useNull) {
        target.setUseNull(useNull);
        return this;
    }

    /**
     * set
     * @param useNotNull not null
     * @return THIS
     */
    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> withUseNotNull(boolean useNotNull) {
        target.setUseNotNull(useNotNull);
        return this;
    }

    /**
     * set
     * @param constraintName constraint name
     * @return THIS
     */
    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> withConstraintName(String constraintName) {
        target.setConstraintName(constraintName);
        return this;
    }

    /**
     * set
     * @param memoryOptimizedConstantExpression Expression
     * @return THIS
     */
    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> withMemoryOptimizedConstantExpression(Expression memoryOptimizedConstantExpression) {
        target.setConstantExpression(memoryOptimizedConstantExpression);
        return this;
    }

    /**
     * set
     * @return THIS
     */
    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> withIdentity() {
        target.setSeed(1);
        target.setIncrement(1);
        return this;
    }

    /**
     * set
     * @param columnConstraint ColumnConstraint
     * @return THIS
     */
    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> withColumnConstraint(ColumnConstraint columnConstraint) {
        target.setColumnConstraint(Collections.singletonList(columnConstraint));
        return this;
    }

    /**
     * set
     * @param columnIndex ColumnIndex
     * @return THIS
     */
    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> withColumnIndex(ColumnIndex columnIndex) {
        target.setColumnIndex(columnIndex);
        return this;
    }




    /*
    Quick
     */

    /**
     * Quick set
     * @param dataType dataType
     * @return THIS
     */
    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> $(DataType dataType){
        return withDataType(dataType);
    }

    /**
     * Quick set
     * @param collationName collationName
     * @return THIS
     */
    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> $Collate(Collate collationName){
        return withCollate(collationName);
    }

    /**
     * Quick set
     * @return THIS
     */
    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> $GeneratedAlwaysAsRowStart(){
        return withUseGeneratedAlwaysStart(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> $GeneratedAlwaysAsRowEnd(){
        return withUseGeneratedAlwaysEnd(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> $Hidden(){
        return withUseHidden(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> $Null(){
        return withUseNull(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> $NotNull(){
        return withUseNotNull(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> $Default(Expression memoryOptimizedConstantExpression){
        return withMemoryOptimizedConstantExpression(memoryOptimizedConstantExpression);
    }

    /**
     * Quick set
     * @return THIS
     */
    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> $Identity(){
        return withIdentity();
    }

    /**
     * Quick in
     * @param constraintName constraint name
     * @return MemoryOptimizedColumnConstraintBuilder
     */
    public MemoryOptimizedColumnConstraintBuilder<MemoryOptimizedColumnDefinitionBuilder<ParentBuilder>> $Constraint(
            String constraintName){
        return new MemoryOptimizedColumnConstraintBuilder<MemoryOptimizedColumnDefinitionBuilder<ParentBuilder>>
                (initNew(ColumnConstraint::new,
                        target::getColumnConstraint,
                        target::setColumnConstraint))
                .in(this)
                .withConstraintName(constraintName);
    }

    /**
     * Quick in
     * @param indexName indexName
     * @return ColumnIndexBuilder
     */
    public HashBucketCountColumnIndexBuilder<MemoryOptimizedColumnDefinitionBuilder<ParentBuilder>> $Index(
            String indexName){
        return new HashBucketCountColumnIndexBuilder<MemoryOptimizedColumnDefinitionBuilder<ParentBuilder>>
                (initSet(ColumnIndex::new,
                        target::getColumnIndex,
                        target::setColumnIndex))
                .in(this)
                .withIndexName(indexName);
    }
}
