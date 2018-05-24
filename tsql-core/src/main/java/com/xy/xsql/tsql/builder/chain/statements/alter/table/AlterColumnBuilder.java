package com.xy.xsql.tsql.builder.chain.statements.alter.table;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.datatypes.DataType;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.table.collation.Collate;
import com.xy.xsql.tsql.model.statements.alter.table.AlterColumn;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;

/**
 * AlterColumnBuilder
 * Created by xiaoyao9184 on 2017/9/16.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class AlterColumnBuilder<ParentBuilder>
        extends CodeTreeBuilder<AlterColumnBuilder<ParentBuilder>,ParentBuilder,AlterColumn> {

    public AlterColumnBuilder(AlterColumn target) {
        super(target);
    }

    public AlterColumnBuilder() {
        super(new AlterColumn());
    }

    /**
     * set
     * @param name name
     * @return THIS
     */
    public AlterColumnBuilder<ParentBuilder> withColumnName(String name){
        target.setColumnName(name);
        return this;
    }

    /**
     * set
     * @param dataType DataType
     * @return THIS
     */
    public AlterColumnBuilder<ParentBuilder> withTypeName(DataType dataType){
        target.setDataType(dataType);
        return this;
    }

    /**
     * set
     * @param collationName Collate
     * @return THIS
     */
    public AlterColumnBuilder<ParentBuilder> withCollationName(Collate collationName){
        target.setCollationName(collationName);
        return this;
    }

    /**
     * set
     * @param useNull null
     * @return THIS
     */
    public AlterColumnBuilder<ParentBuilder> withUseNull(Boolean useNull){
        target.setUseNull(useNull);
        return this;
    }

    /**
     * set
     * @param useSparse sparse
     * @return THIS
     */
    public AlterColumnBuilder<ParentBuilder> withUseSparse(Boolean useSparse){
        target.setUseSparse(useSparse);
        return this;
    }

    /**
     * set
     * @param useAdd add
     * @return THIS
     */
    public AlterColumnBuilder<ParentBuilder> withUseAdd(Boolean useAdd){
        target.setUseAdd(useAdd);
        return this;
    }

    /**
     * set
     * @param useRowGuidCol row guid col
     * @return THIS
     */
    public AlterColumnBuilder<ParentBuilder> withUseRowGuidCol(boolean useRowGuidCol){
        target.setUseAdd(useRowGuidCol);
        return this;
    }

    /**
     * set
     * @param usePersisted persisted
     * @return THIS
     */
    public AlterColumnBuilder<ParentBuilder> withUsePersisted(boolean usePersisted){
        target.setUsePersisted(usePersisted);
        return this;
    }

    /**
     * set
     * @param useNotForReplication not for replication
     * @return THIS
     */
    public AlterColumnBuilder<ParentBuilder> withUseNotForReplication(boolean useNotForReplication){
        target.setUseNotForReplication(useNotForReplication);
        return this;
    }

    /**
     * set
     * @param useHidden hidden
     * @return THIS
     */
    public AlterColumnBuilder<ParentBuilder> withUseHidden(boolean useHidden){
        target.setUseHidden(useHidden);
        return this;
    }

    /**
     * set
     * @param useMasked masked
     * @return THIS
     */
    public AlterColumnBuilder<ParentBuilder> withUseMasked(boolean useMasked){
        target.setUseMasked(useMasked);
        return this;
    }

    /**
     * set
     * @param maskFunction mask function
     * @return THIS
     */
    public AlterColumnBuilder<ParentBuilder> withMaskFunction(StringConstant maskFunction){
        target.setMaskFunction(maskFunction);
        return this;
    }

    /**
     * set
     * @param useOnline online
     * @return THIS
     */
    public AlterColumnBuilder<ParentBuilder> withUseOnline(boolean useOnline){
        target.setUseOnline(useOnline);
        return this;
    }

    /**
     * set
     * @param useCheck check
     * @return THIS
     */
    public AlterColumnBuilder<ParentBuilder> withUseCheck(Boolean useCheck){
        target.setUseCheck(useCheck);
        return this;
    }




    /*
    Quick
     */

    /**
     * Quick set
     * @param columnName column name
     * @return THIS
     */
    public AlterColumnBuilder<ParentBuilder> $(String columnName){
        return withColumnName(columnName);
    }

    /**
     * Quick set
     * @param dataType DataType
     * @return THIS
     */
    public AlterColumnBuilder<ParentBuilder> $(DataType dataType){
        return withTypeName(dataType);
    }

    /**
     * Quick set
     * @param collationName Collate
     * @return THIS
     */
    public AlterColumnBuilder<ParentBuilder> $Collate(Collate collationName){
        return withCollationName(collationName);
    }

    /**
     * Quick set
     * @return THIS
     */
    public AlterColumnBuilder<ParentBuilder> $Null(){
        return withUseNull(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public AlterColumnBuilder<ParentBuilder> $NotNull(){
        return withUseNull(false);
    }

    /**
     * Quick set
     * @return THIS
     */
    public AlterColumnBuilder<ParentBuilder> $Sparse(){
        return withUseSparse(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public AlterColumnBuilder<ParentBuilder> $Add(){
        return withUseAdd(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public AlterColumnBuilder<ParentBuilder> $Drop(){
        return withUseAdd(false);
    }

    /**
     * Quick set
     * @return THIS
     */
    public AlterColumnBuilder<ParentBuilder> $Rowguidcol(){
        return withUseRowGuidCol(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public AlterColumnBuilder<ParentBuilder> $Persisted(){
        return withUsePersisted(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public AlterColumnBuilder<ParentBuilder> $NotForReplication(){
        return withUseNotForReplication(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public AlterColumnBuilder<ParentBuilder> $Hidden(){
        return withUseHidden(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public AlterColumnBuilder<ParentBuilder> $Masked(){
        return withUseMasked(true);
    }

    /**
     * set
     * @param maskFunction mask function
     * @return THIS
     */
    public AlterColumnBuilder<ParentBuilder> $WithFunction(String maskFunction){
        return withMaskFunction(c_string(maskFunction));
    }

    /**
     * Quick set
     * @return THIS
     */
    public AlterColumnBuilder<ParentBuilder> $WithOnlineOn(){
        return withUseOnline(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public AlterColumnBuilder<ParentBuilder> $WithOnlineOff(){
        return withUseOnline(false);
    }

    /**
     * Quick set
     * @return THIS
     */
    public AlterColumnBuilder<ParentBuilder> $WithCheck(){
        return withUseCheck(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public AlterColumnBuilder<ParentBuilder> $WithNocheck(){
        return withUseCheck(false);
    }

}
