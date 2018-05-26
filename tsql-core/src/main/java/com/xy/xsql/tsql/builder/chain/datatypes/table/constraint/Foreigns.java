package com.xy.xsql.tsql.builder.chain.datatypes.table.constraint;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.core.builder.parent.ParentHoldLazyConfigBuilder;
import com.xy.xsql.core.lambda.Getter;
import com.xy.xsql.tsql.model.datatypes.table.constraint.Foreign;

import java.util.Collections;
import java.util.List;

import static com.xy.xsql.core.handler.list.ListHandler.list;

/**
 * Foreign Builders
 * Created by xiaoyao9184 on 2017/8/17.
 */
@SuppressWarnings({"WeakerAccess","unused"})
public class Foreigns {


    /**
     * ColumnForeignBuilder
     * @param <ParentBuilder>
     */
    public static class ColumnForeignBuilder<ParentBuilder>
            extends ParentHoldBuilder<ColumnForeignBuilder<ParentBuilder>,ParentBuilder,Foreign> {

        public ColumnForeignBuilder() {
            super(new Foreign());
        }

        public ColumnForeignBuilder(Foreign target) {
            super(target);
        }

        /**
         * set
         * @param useForeignKey foreign key
         * @return THIS
         */
        public ColumnForeignBuilder<ParentBuilder> withForeignKey(boolean useForeignKey){
            target.setUseForeignKey(useForeignKey);
            return this;
        }

        /**
         * set
         * @param schemaName schema name
         * @return THIS
         */
        public ColumnForeignBuilder<ParentBuilder> withSchemaName(String schemaName){
            target.setSchemaName(schemaName);
            return this;
        }

        /**
         * set
         * @param referencedTableName referenced table name
         * @return THIS
         */
        public ColumnForeignBuilder<ParentBuilder> withReferencedTableName(String referencedTableName){
            target.setReferencedTableName(referencedTableName);
            return this;
        }

        /**
         * set
         * @param refColumn ref column
         * @return THIS
         */
        public ColumnForeignBuilder<ParentBuilder> withRefColumn(String refColumn){
            target.setRefColumns(Collections.singletonList(refColumn));
            return this;
        }

        /**
         * set
         * @param onType OnType
         * @return THIS
         */
        public ColumnForeignBuilder<ParentBuilder> withOnDelete(Foreign.OnType onType){
            target.setOnDelete(onType);
            return this;
        }

        /**
         * set
         * @param onType OnType
         * @return THIS
         */
        public ColumnForeignBuilder<ParentBuilder> withOnUpdate(Foreign.OnType onType){
            target.setOnUpdate(onType);
            return this;
        }

        /**
         * set
         * @param useNotForReplication not for replication
         * @return THIS
         */
        public ColumnForeignBuilder<ParentBuilder> withUseNotForReplication(boolean useNotForReplication){
            target.setUseNotForReplication(useNotForReplication);
            return this;
        }




        /*
        Quick
         */

        /**
         * Quick set
         * @return THIS
         */
        public ColumnForeignBuilder<ParentBuilder> $ForeignKey(){
            return withForeignKey(true);
        }

        /**
         * Quick inout set referencedTableName
         * @return THIS
         */
        public ColumnForeignBuilder<ParentBuilder> $References(String referencedTableName, String refColumn){
            return withReferencedTableName(referencedTableName)
                    .withRefColumn(refColumn);
        }

        /**
         * Quick inout set schemaName,referencedTableName
         * @return THIS
         */
        public ColumnForeignBuilder<ParentBuilder> $References(String schemaName, String referencedTableName, String refColumn){
            return withSchemaName(schemaName)
                    .withReferencedTableName(referencedTableName)
                    .withRefColumn(refColumn);
        }

        /**
         * Quick in
         * @return OnTypeBuilder
         */
        public OnTypeBuilder<ColumnForeignBuilder> $OnDelete(){
            return new OnTypeBuilder<ColumnForeignBuilder>()
                    .enter(this, Getter.empty(), onType -> target.setOnDelete(onType));
        }

        /**
         * Quick in
         * @return OnTypeBuilder
         */
        public OnTypeBuilder<ColumnForeignBuilder> $OnUpdate(){
            return new OnTypeBuilder<ColumnForeignBuilder>()
                    .enter(this, Getter.empty(), onType -> target.setOnUpdate(onType));
        }

        /**
         * Quick set useNotForReplication
         * @return THIS
         */
        public ColumnForeignBuilder $NotForReplication(){
            return withUseNotForReplication(true);
        }

    }


    /**
     * ComputedColumnForeignBuilder
     * @param <ParentBuilder>
     */
    public static class ComputedColumnForeignBuilder<ParentBuilder>
            extends ParentHoldBuilder<ColumnForeignBuilder<ParentBuilder>,ParentBuilder,Foreign> {

        public ComputedColumnForeignBuilder() {
            super(new Foreign());
        }

        public ComputedColumnForeignBuilder(Foreign target) {
            super(target);
        }

        /**
         * set
         * @param useForeignKey foreign key
         * @return THIS
         */
        public ComputedColumnForeignBuilder<ParentBuilder> withForeignKey(boolean useForeignKey){
            target.setUseForeignKey(useForeignKey);
            return this;
        }

        /**
         * set
         * @param referencedTableName referenced table name
         * @return THIS
         */
        public ComputedColumnForeignBuilder<ParentBuilder> withReferencedTableName(String referencedTableName){
            target.setReferencedTableName(referencedTableName);
            return this;
        }

        /**
         * set
         * @param refColumn ref column
         * @return THIS
         */
        public ComputedColumnForeignBuilder<ParentBuilder> withRefColumn(String refColumn){
            target.setRefColumns(Collections.singletonList(refColumn));
            return this;
        }

        /**
         * set
         * @param onType OnType
         * @return THIS
         */
        public ComputedColumnForeignBuilder<ParentBuilder> withOnDelete(Foreign.OnType onType){
            target.setOnDelete(onType);
            return this;
        }

        /**
         * set
         * @param onType OnType
         * @return THIS
         */
        public ComputedColumnForeignBuilder<ParentBuilder> withOnUpdate(Foreign.OnType onType){
            target.setOnUpdate(onType);
            return this;
        }

        /**
         * set
         * @param useNotForReplication not for replication
         * @return THIS
         */
        public ComputedColumnForeignBuilder<ParentBuilder> withUseNotForReplication(boolean useNotForReplication){
            target.setUseNotForReplication(useNotForReplication);
            return this;
        }




        /*
        Quick
         */

        /**
         * Quick set
         * @return THIS
         */
        public ComputedColumnForeignBuilder<ParentBuilder> $ForeignKey(){
            return withForeignKey(true);
        }

        /**
         * Quick inout set referencedTableName
         * @return THIS
         */
        public ComputedColumnForeignBuilder $References(String referencedTableName, String refColumn){
            return withReferencedTableName(referencedTableName)
                    .withRefColumn(refColumn);
        }

        /**
         * Quick in
         * @return OnTypeBuilder
         */
        public OnTypeBuilder<ComputedColumnForeignBuilder> $OnDelete(){
            return new OnTypeBuilder<ComputedColumnForeignBuilder>()
                    .enter(this, Getter.empty(), onType -> target.setOnDelete(onType));
        }

        /**
         * Quick in
         * @return OnTypeBuilder
         */
        public OnTypeBuilder<ComputedColumnForeignBuilder> $OnUpdate(){
            return new OnTypeBuilder<ComputedColumnForeignBuilder>()
                    .enter(this, Getter.empty(), onType -> target.setOnUpdate(onType));
        }

        /**
         * Quick set useNotForReplication
         * @return THIS
         */
        public ComputedColumnForeignBuilder $NotForReplication(){
            return withUseNotForReplication(true);
        }

    }


    /**
     * ReferencesColumnForeignBuilder
     * Use
     * in column_constraint
     * on --Memory optimized CREATE TABLE Syntax
     * @param <ParentBuilder>
     */
    public static class ReferencesColumnForeignBuilder<ParentBuilder>
            extends ParentHoldBuilder<ReferencesColumnForeignBuilder<ParentBuilder>,ParentBuilder,Foreign> {

        public ReferencesColumnForeignBuilder() {
            super(new Foreign());
        }

        public ReferencesColumnForeignBuilder(Foreign target) {
            super(target);
        }

        /**
         * set
         * @param useForeignKey foreign key
         * @return THIS
         */
        public ReferencesColumnForeignBuilder<ParentBuilder> withForeignKey(boolean useForeignKey){
            target.setUseForeignKey(useForeignKey);
            return this;
        }

        /**
         * set
         * @param schemaName schema name
         * @return THIS
         */
        public ReferencesColumnForeignBuilder<ParentBuilder> withSchemaName(String schemaName){
            target.setSchemaName(schemaName);
            return this;
        }

        /**
         * set
         * @param referencedTableName referenced table name
         * @return THIS
         */
        public ReferencesColumnForeignBuilder<ParentBuilder> withReferencedTableName(String referencedTableName){
            target.setReferencedTableName(referencedTableName);
            return this;
        }

        /**
         * set
         * @param refColumn ref column
         * @return THIS
         */
        public ReferencesColumnForeignBuilder<ParentBuilder> withRefColumn(String refColumn){
            target.setRefColumns(Collections.singletonList(refColumn));
            return this;
        }




        /*
        Quick
         */

        /**
         * Quick set
         * @return THIS
         */
        public ReferencesColumnForeignBuilder<ParentBuilder> $ForeignKey(){
            return withForeignKey(true);
        }

        /**
         * Quick inout set referencedTableName
         * @return THIS
         */
        public ReferencesColumnForeignBuilder $References(String referencedTableName, String refColumn){
            return withReferencedTableName(referencedTableName)
                    .withRefColumn(refColumn);
        }

    }



    /**
     * TableForeignBuilder
     * @param <ParentBuilder>
     */
    public static class TableForeignBuilder<ParentBuilder>
            extends ParentHoldBuilder<TableForeignBuilder<ParentBuilder>,ParentBuilder,Foreign> {

        public TableForeignBuilder() {
            super(new Foreign());
        }

        public TableForeignBuilder(Foreign target) {
            super(target);
        }

        /**
         * set
         * @param columns column
         * @return THIS
         */
        public TableForeignBuilder<ParentBuilder> withColumns(List<String> columns){
            target.setColumns(columns);
            return this;
        }

        /**
         * set
         * @param referencedTableName referenced table name
         * @return THIS
         */
        public TableForeignBuilder<ParentBuilder> withReferencedTableName(String referencedTableName){
            target.setReferencedTableName(referencedTableName);
            return this;
        }

        /**
         * set
         * @param refColumns ref column
         * @return THIS
         */
        public TableForeignBuilder<ParentBuilder> withRefColumn(List<String> refColumns){
            target.setRefColumns(refColumns);
            return this;
        }

        /**
         * set
         * @param onType OnType
         * @return THIS
         */
        public TableForeignBuilder<ParentBuilder> withOnDelete(Foreign.OnType onType){
            target.setOnDelete(onType);
            return this;
        }

        /**
         * set
         * @param onType OnType
         * @return THIS
         */
        public TableForeignBuilder<ParentBuilder> withOnUpdate(Foreign.OnType onType){
            target.setOnUpdate(onType);
            return this;
        }

        /**
         * set
         * @param useNotForReplication not for replication
         * @return THIS
         */
        public TableForeignBuilder<ParentBuilder> withUseNotForReplication(boolean useNotForReplication){
            target.setUseNotForReplication(useNotForReplication);
            return this;
        }

        /*
        Quick
         */

        /**
         * Quick set columns
         * @param columns column
         * @return THIS
         */
        public TableForeignBuilder $(String... columns){
            list(target::getColumns, target::setColumns).addAll(columns);
            return this;
        }




        /*
        Quick
         */

        /**
         * Quick inout set referencedTableName, refColumn
         * @return THIS
         */
        public TableForeignBuilder $References(String referencedTableName, String... refColumns){
            list(target::getRefColumns, target::setRefColumns).addAll(refColumns);
            return withReferencedTableName(referencedTableName);
        }

        /**
         * Quick in
         * @return OnTypeBuilder
         */
        public OnTypeBuilder<TableForeignBuilder> $OnDelete(){
            return new OnTypeBuilder<TableForeignBuilder>()
                    .enter(this, Getter.empty(), onType -> target.setOnDelete(onType));
        }

        /**
         * Quick in
         * @return OnTypeBuilder
         */
        public OnTypeBuilder<TableForeignBuilder> $OnUpdate(){
            return new OnTypeBuilder<TableForeignBuilder>()
                    .enter(this, Getter.empty(), onType -> target.setOnUpdate(onType));
        }

        /**
         * Quick set useNotForReplication
         * @return THIS
         */
        public TableForeignBuilder $NotForReplication(){
            return withUseNotForReplication(true);
        }

    }

    /**
     * Use
     * in table_constraint
     * on --Memory optimized CREATE TABLE Syntax
     * @param <ParentBuilder>
     */
    public static class ReferencesTableForeignBuilder<ParentBuilder>
            extends ParentHoldBuilder<ReferencesTableForeignBuilder<ParentBuilder>,ParentBuilder,Foreign> {

        public ReferencesTableForeignBuilder() {
            super(new Foreign());
        }

        public ReferencesTableForeignBuilder(Foreign target) {
            super(target);
        }

        /**
         * set
         * @param columns column
         * @return THIS
         */
        public ReferencesTableForeignBuilder<ParentBuilder> withColumns(List<String> columns){
            target.setColumns(columns);
            return this;
        }

        /**
         * set
         * @param schemaName schema name
         * @return THIS
         */
        public ReferencesTableForeignBuilder<ParentBuilder> withSchemaName(String schemaName){
            target.setSchemaName(schemaName);
            return this;
        }

        /**
         * set
         * @param referencedTableName referenced table name
         * @return THIS
         */
        public ReferencesTableForeignBuilder<ParentBuilder> withReferencedTableName(String referencedTableName){
            target.setReferencedTableName(referencedTableName);
            return this;
        }

        /**
         * set
         * @param refColumn ref column
         * @return THIS
         */
        public ReferencesTableForeignBuilder<ParentBuilder> withRefColumn(String refColumn){
            target.setRefColumns(Collections.singletonList(refColumn));
            return this;
        }




        /*
        Quick
         */

        /**
         * Quick set columns
         * @param columns columns
         * @return THIS
         */
        public ReferencesTableForeignBuilder $(String... columns){
            list(target::getColumns, target::setColumns).addAll(columns);
            return this;
        }

        /**
         * Quick inout set referencedTableName
         * @return THIS
         */
        public ReferencesTableForeignBuilder $References(String referencedTableName, String refColumn){
            return withReferencedTableName(referencedTableName)
                    .withRefColumn(refColumn);
        }

    }

    /**
     * Abstract OnTypeBuilder
     * @param <ParentBuilder>
     */
    public static class OnTypeBuilder<ParentBuilder>
            extends ParentHoldLazyConfigBuilder<OnTypeBuilder<ParentBuilder>,ParentBuilder,Foreign.OnType> {

        public OnTypeBuilder() {}

        /**
         * Quick set
         * @return PARENT
         */
        public ParentBuilder $NoAction(){
            target = Foreign.OnType.NO_ACTION;
            return this.and();
        }

        /**
         * Quick set
         * @return PARENT
         */
        public ParentBuilder $Cascade(){
            target = Foreign.OnType.CASCADE;
            return this.and();
        }

        /**
         * Quick set
         * @return PARENT
         */
        public ParentBuilder $SetNull(){
            target = Foreign.OnType.SET_NULL;
            return this.and();
        }

        /**
         * Quick set
         * @return PARENT
         */
        public ParentBuilder $SetDefault(){
            target = Foreign.OnType.SET_DEFAULT;
            return this.and();
        }

    }

}
