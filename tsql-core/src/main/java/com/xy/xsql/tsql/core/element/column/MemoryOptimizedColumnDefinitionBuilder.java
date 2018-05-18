package com.xy.xsql.tsql.core.element.column;

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
import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * Use
 * in --Memory optimized CREATE TABLE Syntax
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class MemoryOptimizedColumnDefinitionBuilder<ParentBuilder>
        extends CodeTreeBuilder<MemoryOptimizedColumnDefinitionBuilder<ParentBuilder>,ParentBuilder,ColumnDefinition> {

    public MemoryOptimizedColumnDefinitionBuilder(ColumnDefinition tar) {
        super(tar);
    }

    public MemoryOptimizedColumnDefinitionBuilder() {
        super(new ColumnDefinition());
    }

    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> withColumnName(ColumnName columnName) {
        target.setTable(columnName.getTable());
        target.setName(columnName.getName());
        return this;
    }

    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> withDataType(DataType dataType) {
        target.setDataType(dataType);
        return this;
    }

    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> withCollate(Collate collationName) {
        target.setCollationName(collationName);
        return this;
    }

    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> withUseGeneratedAlwaysStart(boolean useGeneratedAlwaysStart) {
        target.setUseGeneratedAlwaysStart(useGeneratedAlwaysStart);
        return this;
    }

    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> withUseGeneratedAlwaysEnd(boolean useGeneratedAlwaysEnd) {
        target.setUseGeneratedAlwaysEnd(useGeneratedAlwaysEnd);
        return this;
    }

    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> withUseHidden(boolean useHidden) {
        target.setUseHidden(useHidden);
        return this;
    }

    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> withUseNull(boolean useNull) {
        target.setUseNull(useNull);
        return this;
    }

    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> withUseNotNull(boolean useNotNull) {
        target.setUseNotNull(useNotNull);
        return this;
    }

    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> withConstraintName(String constraintName) {
        target.setConstraintName(constraintName);
        return this;
    }

    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> withMemoryOptimizedConstantExpression(Expression memoryOptimizedConstantExpression) {
        target.setConstantExpression(memoryOptimizedConstantExpression);
        return this;
    }

    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> withIdentity() {
        target.setSeed(1);
        target.setIncrement(1);
        return this;
    }

    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> withColumnConstraint(ColumnConstraint columnConstraint) {
        target.setColumnConstraint(Collections.singletonList(columnConstraint));
        return this;
    }

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
    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> $COLLATE(Collate collationName){
        return withCollate(collationName);
    }

    /**
     * Quick set
     * @return THIS
     */
    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> $GENERATED_ALWAYS_AS_ROW_START(){
        return withUseGeneratedAlwaysStart(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> $GENERATED_ALWAYS_AS_ROW_END(){
        return withUseGeneratedAlwaysEnd(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> $HIDDEN(){
        return withUseHidden(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> $NULL(){
        return withUseNull(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> $NOT_NULL(){
        return withUseNotNull(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> $DEFAULT(Expression memoryOptimizedConstantExpression){
        return withMemoryOptimizedConstantExpression(memoryOptimizedConstantExpression);
    }

    /**
     * Quick set
     * @return THIS
     */
    public MemoryOptimizedColumnDefinitionBuilder<ParentBuilder> $IDENTITY(){
        return withIdentity();
    }

    /**
     * Quick in
     * @param constraintName constraintName
     * @return MemoryOptimizedColumnConstraintBuilder
     */
    public MemoryOptimizedColumnConstraintBuilder<MemoryOptimizedColumnDefinitionBuilder<ParentBuilder>> $CONSTRAINT(String constraintName){
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
    public HashBucketCountColumnIndexBuilder<MemoryOptimizedColumnDefinitionBuilder<ParentBuilder>> $INDEX(String indexName){
        return new HashBucketCountColumnIndexBuilder<MemoryOptimizedColumnDefinitionBuilder<ParentBuilder>>
                (initSet(ColumnIndex::new,
                        target::getColumnIndex,
                        target::setColumnIndex))
                .in(this)
                .withIndexName(indexName);
    }
}
