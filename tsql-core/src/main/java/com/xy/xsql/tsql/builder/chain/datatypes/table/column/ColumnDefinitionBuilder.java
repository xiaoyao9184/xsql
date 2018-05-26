package com.xy.xsql.tsql.builder.chain.datatypes.table.column;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.constraint.CheckBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.constraint.Foreigns;
import com.xy.xsql.tsql.builder.chain.datatypes.table.constraint.PrimaryUniques;
import com.xy.xsql.tsql.model.datatypes.DataType;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.collation.Collate;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnConstraint;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnDefinition;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnIndex;
import com.xy.xsql.tsql.model.elements.expressions.Expression;

import java.util.List;

import static com.xy.xsql.core.handler.list.ListHandler.list;
import static com.xy.xsql.core.handler.object.GetterSetterObjectHandler.object;

/**
 * ColumnDefinitionBuilder
 * Use
 * in --Disk-Based CREATE TABLE Syntax
 * Created by xiaoyao9184 on 2017/3/12.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class ColumnDefinitionBuilder<ParentBuilder>
        extends ParentHoldBuilder<ColumnDefinitionBuilder<ParentBuilder>,ParentBuilder,ColumnDefinition> {

    public ColumnDefinitionBuilder() {
        super(new ColumnDefinition());
    }

    public ColumnDefinitionBuilder(ColumnDefinition target) {
        super(target);
    }

    /**
     * set
     * @param columnName ColumnName
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> withColumnName(ColumnName columnName) {
        target.setTable(columnName.getTable());
        target.setName(columnName.getName());
        return this;
    }

    /**
     * set
     * @param dataType DataType
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> withDataType(DataType dataType) {
        target.setDataType(dataType);
        return this;
    }

    /**
     * set
     * @param useFilestream filestream
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> withUseFilestream(boolean useFilestream) {
        target.setUseFilestream(useFilestream);
        return this;
    }

    /**
     * set
     * @param collationName collation name
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> withCollate(Collate collationName) {
        target.setCollationName(collationName);
        return this;
    }

    /**
     * set
     * @param useSparse sparse
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> withUseSparse(boolean useSparse) {
        target.setUseSparse(useSparse);
        return this;
    }

    /**
     * set
     * @param maskFunction mask function
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> withMaskFunction(StringConstant maskFunction) {
        target.setMaskFunction(maskFunction);
        return this;
    }

    /**
     * set
     * @param constraintName constraint name
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> withConstraintName(String constraintName) {
        target.setConstraintName(constraintName);
        return this;
    }

    /**
     * set
     * @param constantExpression Expression
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> withConstantExpression(Expression constantExpression) {
        target.setConstantExpression(constantExpression);
        return this;
    }

    /**
     * set
     * @param seed seed
     * @param increment increment
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> withIdentity(Integer seed, Integer increment) {
        target.setSeed(seed);
        target.setIncrement(increment);
        return this;
    }

    /**
     * set
     * @param useNotForReplication not for replication
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> withUseNotForReplication(boolean useNotForReplication) {
        target.setUseNotForReplication(useNotForReplication);
        return this;
    }

    /**
     * set
     * @param useGeneratedAlwaysStart generated always start
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> withUseGeneratedAlwaysStart(boolean useGeneratedAlwaysStart) {
        target.setUseGeneratedAlwaysStart(useGeneratedAlwaysStart);
        return this;
    }

    /**
     * set
     * @param useGeneratedAlwaysEnd generated always end
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> withUseGeneratedAlwaysEnd(boolean useGeneratedAlwaysEnd) {
        target.setUseGeneratedAlwaysEnd(useGeneratedAlwaysEnd);
        return this;
    }

    /**
     * set
     * @param useHidden hidden
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> withUseHidden(boolean useHidden) {
        target.setUseHidden(useHidden);
        return this;
    }

    /**
     * set
     * @param useNull null
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> withUseNull(boolean useNull) {
        target.setUseNull(useNull);
        return this;
    }

    /**
     * set
     * @param useNotNull not null
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> withUseNotNull(boolean useNotNull) {
        target.setUseNotNull(useNotNull);
        return this;
    }

    /**
     * set
     * @param useRowGuidCol row guid col
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> withUseRowGuidCol(boolean useRowGuidCol) {
        target.setUseRowGuidCol(useRowGuidCol);
        return this;
    }

    /**
     * set
     * @param columnEncryptionKey column encryption key
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> withColumnEncryptionKey(String columnEncryptionKey) {
        target.setColumnEncryptionKey(columnEncryptionKey);
        return this;
    }

    /**
     * set
     * @param randomizedEncryptionType randomized encryption type
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> withRandomizedEncryptionType(boolean randomizedEncryptionType) {
        target.setRandomizedEncryptionType(randomizedEncryptionType);
        return this;
    }

    /**
     * set
     * @param algorithm algorithm
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> withAlgorithm(StringConstant algorithm) {
        target.setAlgorithm(algorithm);
        return this;
    }

    /**
     * set
     * @param columnConstraintList ColumnConstraint
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> withColumnConstraint(List<ColumnConstraint> columnConstraintList) {
        target.setColumnConstraint(columnConstraintList);
        return this;
    }

    /**
     * set
     * @param columnIndex ColumnIndex
     * @return THIS
     */
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
    public ColumnDefinitionBuilder<ParentBuilder> $Filestream(){
        return withUseFilestream(true);
    }

    /**
     * Quick set
     * @param collationName collationName
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> $Collate(Collate collationName){
        return withCollate(collationName);
    }

    /**
     * Quick set
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> $Sparse(){
        return withUseSparse(true);
    }

    /**
     * Quick set
     * @param maskFunction maskFunction
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> $MaskedWithFunction(StringConstant maskFunction){
        return withMaskFunction(maskFunction);
    }

    /**
     * TODO handle repeat
     * @see #$$Constraint(String)
     * Quick set
     * @param constraintName constraint name
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> $Constraint(String constraintName){
        return withConstraintName(constraintName);
    }

    /**
     * Quick set
     * @param constantExpression Expression
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> $Default(Expression constantExpression){
        return withConstantExpression(constantExpression);
    }

    /**
     * Quick set
     * @param seed seed
     * @param increment increment
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> $Identity(Integer seed, Integer increment){
        return withIdentity(seed, increment);
    }

    /**
     * Quick set
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> $NotForReplication(){
        return withUseNotForReplication(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> $GeneratedAlwaysAsRowStart(){
        return withUseGeneratedAlwaysStart(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> $GeneratedAlwaysAsRowEnd(){
        return withUseGeneratedAlwaysEnd(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> $Hidden(){
        return withUseHidden(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> $Null(){
        return withUseNull(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> $NotNull(){
        return withUseNotNull(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> $Rowguidcol(){
        return withUseRowGuidCol(true);
    }

    /**
     * Quick set
     * @param columnEncryptionKey columnEncryptionKey
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> $ColumnEncryptionKey(String columnEncryptionKey){
        return withColumnEncryptionKey(columnEncryptionKey);
    }

    /**
     * Quick set
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> $EncryptionTypeDeterministic(){
        return withRandomizedEncryptionType(false);
    }

    /**
     * Quick set
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> $EncryptionTypeRandomized(){
        return withRandomizedEncryptionType(true);
    }

    /**
     * Quick set
     * @param algorithm algorithm
     * @return THIS
     */
    public ColumnDefinitionBuilder<ParentBuilder> $Algorithm(StringConstant algorithm){
        return withAlgorithm(algorithm);
    }

    /**
     * Quick in
     * @param constraintName constraint name
     * @return ColumnConstraintBuilder
     */
    public ColumnConstraintBuilder<ColumnDefinitionBuilder<ParentBuilder>> $$Constraint(String constraintName){
        return new ColumnConstraintBuilder<ColumnDefinitionBuilder<ParentBuilder>>
                (list(target::getColumnConstraint, target::setColumnConstraint)
                        .addNew(ColumnConstraint::new))
                .in(this)
                .withConstraintName(constraintName);
    }
    
    /**
     * Quick in
     * @return ColumnPrimaryUniqueBuilder
     */
    public PrimaryUniques.ColumnPrimaryUniqueBuilder<ColumnDefinitionBuilder<ParentBuilder>> $PrimaryKey(){
        return $$Constraint(null)
                .$PrimaryKey();
    }

    /**
     * Quick in
     * @return ColumnPrimaryUniqueBuilder
     */
    public PrimaryUniques.ColumnPrimaryUniqueBuilder<ColumnDefinitionBuilder<ParentBuilder>> $Unique(){
        return $$Constraint(null)
                .$Unique();
    }

    /**
     * Quick in
     * @return ColumnForeignBuilder
     */
    public Foreigns.ColumnForeignBuilder<ColumnDefinitionBuilder<ParentBuilder>> $ForeignKey(){
        return $$Constraint(null)
                .$ForeignKey()
                .withForeignKey(true);
    }

    /**
     * Quick inout set referencedTableName
     * @return ColumnForeignBuilder
     */
    public Foreigns.ColumnForeignBuilder<ColumnDefinitionBuilder<ParentBuilder>> $References(
            String referencedTableName, String refColumn){
        return $$Constraint(null)
                .$ForeignKey()
                .withReferencedTableName(referencedTableName)
                .withRefColumn(refColumn);
    }

    /**
     * Quick inout set schemaName,referencedTableName
     * @return ColumnForeignBuilder
     */
    public Foreigns.ColumnForeignBuilder<ColumnDefinitionBuilder<ParentBuilder>> $References(
            String schemaName, String referencedTableName, String refColumn){
        return $$Constraint(null)
                .$ForeignKey()
                .withSchemaName(schemaName)
                .withReferencedTableName(referencedTableName)
                .withRefColumn(refColumn);
    }

    /**
     * Quick in
     * @return CheckBuilder
     */
    public CheckBuilder<ColumnDefinitionBuilder<ParentBuilder>> $Check(){
        return $$Constraint(null)
                .$Check();
    }

    /**
     * Quick in
     * @return ColumnIndexBuilder
     */
    public ColumnIndexBuilder<ColumnDefinitionBuilder<ParentBuilder>> $Index(){
        return new ColumnIndexBuilder<ColumnDefinitionBuilder<ParentBuilder>>
                (object(target::getColumnIndex, target::setColumnIndex)
                        .init(ColumnIndex::new))
                .in(this);
    }
}
