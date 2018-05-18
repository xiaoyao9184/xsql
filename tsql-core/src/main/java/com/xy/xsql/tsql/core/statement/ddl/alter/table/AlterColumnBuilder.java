package com.xy.xsql.tsql.core.statement.ddl.alter.table;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.datatypes.DataType;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.table.collation.Collate;
import com.xy.xsql.tsql.model.statements.alter.table.AlterColumn;

/**
 * Created by xiaoyao9184 on 2017/9/16.
 */
public class AlterColumnBuilder<ParentBuilder>
        extends CodeTreeBuilder<AlterColumnBuilder<ParentBuilder>,ParentBuilder,AlterColumn> {

    public AlterColumnBuilder(AlterColumn target) {
        super(target);
    }

    public AlterColumnBuilder() {
        super(new AlterColumn());
    }

    public AlterColumnBuilder<ParentBuilder> withColumnName(String name){
        target.setColumnName(name);
        return this;
    }

    public AlterColumnBuilder<ParentBuilder> withTypeName(DataType dataType){
        target.setTypeName(dataType);
        return this;
    }

    public AlterColumnBuilder<ParentBuilder> withCollationName(Collate collationName){
        target.setCollationName(collationName);
        return this;
    }

    public AlterColumnBuilder<ParentBuilder> withUseNull(Boolean useNull){
        target.setUseNull(useNull);
        return this;
    }

    public AlterColumnBuilder<ParentBuilder> withUseSparse(Boolean useSparse){
        target.setUseSparse(useSparse);
        return this;
    }

    public AlterColumnBuilder<ParentBuilder> withUseAdd(Boolean useAdd){
        target.setUseAdd(useAdd);
        return this;
    }

    public AlterColumnBuilder<ParentBuilder> withUseRowGuidCol(boolean useRowGuidCol){
        target.setUseAdd(useRowGuidCol);
        return this;
    }

    public AlterColumnBuilder<ParentBuilder> withUsePersisted(boolean usePersisted){
        target.setUsePersisted(usePersisted);
        return this;
    }

    public AlterColumnBuilder<ParentBuilder> withUseNotForReplication(boolean useNotForReplication){
        target.setUseNotForReplication(useNotForReplication);
        return this;
    }

    public AlterColumnBuilder<ParentBuilder> withUseHidden(boolean useHidden){
        target.setUseHidden(useHidden);
        return this;
    }

    public AlterColumnBuilder<ParentBuilder> withUseMasked(boolean useMasked){
        target.setUseMasked(useMasked);
        return this;
    }

    public AlterColumnBuilder<ParentBuilder> withMaskFunction(StringConstant maskFunction){
        target.setMaskFunction(maskFunction);
        return this;
    }

    public AlterColumnBuilder<ParentBuilder> withUseOnline(boolean useOnline){
        target.setUseOnline(useOnline);
        return this;
    }

    public AlterColumnBuilder<ParentBuilder> withUseCheck(Boolean useCheck){
        target.setUseCheck(useCheck);
        return this;
    }


    /*
    Quick
     */

    public AlterColumnBuilder<ParentBuilder> $(String columnName){
        return withColumnName(columnName);
    }

    public AlterColumnBuilder<ParentBuilder> $(DataType dataType){
        return withTypeName(dataType);
    }

    public AlterColumnBuilder<ParentBuilder> $COLLATE(Collate collationName){
        return withCollationName(collationName);
    }

    public AlterColumnBuilder<ParentBuilder> $NULL(){
        return withUseNull(true);
    }

    public AlterColumnBuilder<ParentBuilder> $NOT_NULL(){
        return withUseNull(false);
    }

    public AlterColumnBuilder<ParentBuilder> $SPARSE(){
        return withUseSparse(true);
    }

    public AlterColumnBuilder<ParentBuilder> $ADD(){
        return withUseAdd(true);
    }

    public AlterColumnBuilder<ParentBuilder> $DROP(){
        return withUseAdd(false);
    }


    public AlterColumnBuilder<ParentBuilder> $ROWGUIDCOL(){
        return withUseRowGuidCol(true);
    }

    public AlterColumnBuilder<ParentBuilder> $PERSISTED(){
        return withUsePersisted(true);
    }

    public AlterColumnBuilder<ParentBuilder> $NOT_FOR_REPLICATION(){
        return withUseNotForReplication(true);
    }

    public AlterColumnBuilder<ParentBuilder> $HIDDEN(){
        return withUseHidden(true);
    }

    public AlterColumnBuilder<ParentBuilder> $MASKED(){
        return withUseMasked(true);
    }

    public AlterColumnBuilder<ParentBuilder> $WITH_FUNCTION(String maskFunction){
        return withMaskFunction(new StringConstant(maskFunction).withQuote());
    }

    public AlterColumnBuilder<ParentBuilder> $WITH_ONLINE_ON(){
        return withUseOnline(true);
    }

    public AlterColumnBuilder<ParentBuilder> $WITH_ONLINE_OFF(){
        return withUseOnline(false);
    }

    public AlterColumnBuilder<ParentBuilder> $WITH_CHECK(){
        return withUseCheck(true);
    }

    public AlterColumnBuilder<ParentBuilder> $WITH_NOCHECK(){
        return withUseCheck(false);
    }



}
