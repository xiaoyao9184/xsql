package com.xy.xsql.tsql.core.element.column;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.core.element.constraint.CheckBuilder;
import com.xy.xsql.tsql.core.element.constraint.Foreigns;
import com.xy.xsql.tsql.core.element.constraint.PrimaryUniques;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.element.collation.Collate;
import com.xy.xsql.tsql.model.element.column.ColumnConstraint;
import com.xy.xsql.tsql.model.element.column.ColumnDefinition;
import com.xy.xsql.tsql.model.datatypes.DataType;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.column.ColumnIndex;
import com.xy.xsql.tsql.model.expression.Expression;

import java.util.List;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * Use
 * in --Disk-Based CREATE TABLE Syntax
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class ColumnDefinitionBuilder<ParentBuilder>
        extends CodeTreeBuilder<ColumnDefinitionBuilder<ParentBuilder>,ParentBuilder,ColumnDefinition> {

    public ColumnDefinitionBuilder(ColumnDefinition tar) {
        super(tar);
    }

    public ColumnDefinitionBuilder() {
        super(new ColumnDefinition());
    }

    public ColumnDefinitionBuilder<ParentBuilder> withColumnName(ColumnName columnName) {
        target.setTable(columnName.getTable());
        target.setName(columnName.getName());
        return this;
    }

    public ColumnDefinitionBuilder<ParentBuilder> withDataType(DataType dataType) {
        target.setDataType(dataType);
        return this;
    }

    public ColumnDefinitionBuilder<ParentBuilder> withUseFilestream(boolean useFilestream) {
        target.setUseFilestream(useFilestream);
        return this;
    }

    public ColumnDefinitionBuilder<ParentBuilder> withCollate(Collate collationName) {
        target.setCollationName(collationName);
        return this;
    }

    public ColumnDefinitionBuilder<ParentBuilder> withUseSparse(boolean useSparse) {
        target.setUseSparse(useSparse);
        return this;
    }

    public ColumnDefinitionBuilder<ParentBuilder> withMaskFunction(StringConstant maskFunction) {
        target.setMaskFunction(maskFunction);
        return this;
    }

    public ColumnDefinitionBuilder<ParentBuilder> withConstraintName(String constraintName) {
        target.setConstraintName(constraintName);
        return this;
    }

    public ColumnDefinitionBuilder<ParentBuilder> withConstantExpression(Expression constantExpression) {
        target.setConstantExpression(constantExpression);
        return this;
    }

    public ColumnDefinitionBuilder<ParentBuilder> withIdentity(Integer seed, Integer increment) {
        target.setSeed(seed);
        target.setIncrement(increment);
        return this;
    }

    public ColumnDefinitionBuilder<ParentBuilder> withUseNotForReplication(boolean useNotForReplication) {
        target.setUseNotForReplication(useNotForReplication);
        return this;
    }

    public ColumnDefinitionBuilder<ParentBuilder> withUseGeneratedAlwaysStart(boolean useGeneratedAlwaysStart) {
        target.setUseGeneratedAlwaysStart(useGeneratedAlwaysStart);
        return this;
    }

    public ColumnDefinitionBuilder<ParentBuilder> withUseGeneratedAlwaysEnd(boolean useGeneratedAlwaysEnd) {
        target.setUseGeneratedAlwaysEnd(useGeneratedAlwaysEnd);
        return this;
    }

    public ColumnDefinitionBuilder<ParentBuilder> withUseHidden(boolean useHidden) {
        target.setUseHidden(useHidden);
        return this;
    }

    public ColumnDefinitionBuilder<ParentBuilder> withUseNull(boolean useNull) {
        target.setUseNull(useNull);
        return this;
    }

    public ColumnDefinitionBuilder<ParentBuilder> withUseNotNull(boolean useNotNull) {
        target.setUseNotNull(useNotNull);
        return this;
    }

    public ColumnDefinitionBuilder<ParentBuilder> withUseRowGuidCol(boolean useRowGuidCol) {
        target.setUseRowGuidCol(useRowGuidCol);
        return this;
    }

    public ColumnDefinitionBuilder<ParentBuilder> withColumnEncryptionKey(String columnEncryptionKey) {
        target.setColumnEncryptionKey(columnEncryptionKey);
        return this;
    }

    public ColumnDefinitionBuilder<ParentBuilder> withRandomizedEncryptionType(boolean randomizedEncryptionType) {
        target.setRandomizedEncryptionType(randomizedEncryptionType);
        return this;
    }

    public ColumnDefinitionBuilder<ParentBuilder> withAlgorithm(StringConstant algorithm) {
        target.setAlgorithm(algorithm);
        return this;
    }

    public ColumnDefinitionBuilder<ParentBuilder> withColumnConstraint(List<ColumnConstraint> columnConstraintList) {
        target.setColumnConstraint(columnConstraintList);
        return this;
    }

    public ColumnDefinitionBuilder<ParentBuilder> withColumnIndex(ColumnIndex columnIndex) {
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
    public ColumnDefinitionBuilder<ParentBuilder> $(DataType dataType){
        return withDataType(dataType);
    }

    /**
     * Quick set
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> $FILESTREAM(){
        return withUseFilestream(true);
    }

    /**
     * Quick set
     * @param collationName collationName
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> $COLLATE(Collate collationName){
        return withCollate(collationName);
    }

    /**
     * Quick set
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> $SPARSE(){
        return withUseSparse(true);
    }

    /**
     * Quick set
     * @param maskFunction maskFunction
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> $MASKED_WITH_FUNCTION(StringConstant maskFunction){
        return withMaskFunction(maskFunction);
    }

    /**
     * Quick set
     * @param constraintName constraintName
     * @return THIS
     */
    //TODO
    public ColumnDefinitionBuilder<ParentBuilder> $CONSTRAINT(String constraintName){
        return withConstraintName(constraintName);
    }

    /**
     * Quick set
     * @param constantExpression constantExpression
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> $DEFAULT(Expression constantExpression){
        return withConstantExpression(constantExpression);
    }

    /**
     * Quick set
     * @param seed seed
     * @param increment increment
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> $IDENTITY(Integer seed, Integer increment){
        return withIdentity(seed, increment);
    }

    /**
     * Quick set
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> $NOT_FOR_REPLICATION(){
        return withUseNotForReplication(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> $GENERATED_ALWAYS_AS_ROW_START(){
        return withUseGeneratedAlwaysStart(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> $GENERATED_ALWAYS_AS_ROW_END(){
        return withUseGeneratedAlwaysEnd(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> $HIDDEN(){
        return withUseHidden(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> $NULL(){
        return withUseNull(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> $NOT_NULL(){
        return withUseNotNull(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> $ROWGUIDCOL(){
        return withUseRowGuidCol(true);
    }

    /**
     * Quick set
     * @param columnEncryptionKey columnEncryptionKey
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> $COLUMN_ENCRYPTION_KEY(String columnEncryptionKey){
        return withColumnEncryptionKey(columnEncryptionKey);
    }

    /**
     * Quick set
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> $ENCRYPTION_TYPE_DETERMINISTIC(){
        return withRandomizedEncryptionType(false);
    }

    /**
     * Quick set
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> $ENCRYPTION_TYPE_RANDOMIZED(){
        return withRandomizedEncryptionType(true);
    }

    /**
     * Quick set
     * @param algorithm algorithm
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> $ALGORITHM(StringConstant algorithm){
        return withAlgorithm(algorithm);
    }

    /**
     * Quick into
     * @param constraintName constraintName
     * @return ColumnConstraintBuilder
     */
    public ColumnConstraintBuilder<ColumnDefinitionBuilder<ParentBuilder>> $$Constraint(String constraintName){
        return new ColumnConstraintBuilder<ColumnDefinitionBuilder<ParentBuilder>>
                (initNew(ColumnConstraint::new,
                        target::getColumnConstraint,
                        target::setColumnConstraint))
                .in(this)
                .withConstraintName(constraintName);
    }
    
    /**
     * Quick into
     * @return ColumnPrimaryUniqueBuilder
     */
    public PrimaryUniques.ColumnPrimaryUniqueBuilder<ColumnDefinitionBuilder<ParentBuilder>> $PRIMARY_KEY(){
        return $$Constraint(null)
                .$PRIMARY_KEY();
    }

    /**
     * Quick into
     * @return ColumnPrimaryUniqueBuilder
     */
    public PrimaryUniques.ColumnPrimaryUniqueBuilder<ColumnDefinitionBuilder<ParentBuilder>> $UNIQUE(){
        return $$Constraint(null)
                .$UNIQUE();
    }

    /**
     * Quick into
     * @return ColumnForeignBuilder
     */
    public Foreigns.ColumnForeignBuilder<ColumnDefinitionBuilder<ParentBuilder>> $FOREIGN_KEY(){
        return $$Constraint(null)
                .$FOREIGN_KEY()
                .withForeignKey(true);
    }

    /**
     * Quick inout set referencedTableName
     * @return ColumnForeignBuilder
     */
    public Foreigns.ColumnForeignBuilder<ColumnDefinitionBuilder<ParentBuilder>> $REFERENCES(String referencedTableName, String refColumn){
        return $$Constraint(null)
                .$FOREIGN_KEY()
                .withReferencedTableName(referencedTableName)
                .withRefColumn(refColumn);
    }

    /**
     * Quick inout set schemaName,referencedTableName
     * @return ColumnForeignBuilder
     */
    public Foreigns.ColumnForeignBuilder<ColumnDefinitionBuilder<ParentBuilder>> $REFERENCES(String schemaName, String referencedTableName, String refColumn){
        return $$Constraint(null)
                .$FOREIGN_KEY()
                .withSchemaName(schemaName)
                .withReferencedTableName(referencedTableName)
                .withRefColumn(refColumn);
    }

    /**
     * Quick into
     * @return CheckBuilder
     */
    public CheckBuilder<ColumnDefinitionBuilder<ParentBuilder>> $CHECK(){
        return $$Constraint(null)
                .$CHECK();
    }

    /**
     * Quick into
     * @return ColumnIndexBuilder
     */
    public ColumnIndexBuilder<ColumnDefinitionBuilder<ParentBuilder>> $INDEX(){
        return new ColumnIndexBuilder<ColumnDefinitionBuilder<ParentBuilder>>
                (initSet(ColumnIndex::new,
                        target::getColumnIndex,
                        target::setColumnIndex))
                .in(this);
    }
}
