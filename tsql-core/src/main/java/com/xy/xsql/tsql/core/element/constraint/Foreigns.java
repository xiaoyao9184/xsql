package com.xy.xsql.tsql.core.element.constraint;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.datatypes.table.constraint.Foreign;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.xy.xsql.core.ListBuilder.initAdd;

/**
 * Created by xiaoyao9184 on 2017/8/17.
 */
public class Foreigns {


    /**
     *
     * @param <ParentBuilder>
     */
    public static class ColumnForeignBuilder<ParentBuilder>
            extends CodeTreeBuilder<ColumnForeignBuilder<ParentBuilder>,ParentBuilder,Foreign> {

        public ColumnForeignBuilder(Foreign tar) {
            super(tar);
        }

        public ColumnForeignBuilder<ParentBuilder> withForeignKey(boolean useForeignKey){
            target.setUseForeignKey(useForeignKey);
            return this;
        }

        public ColumnForeignBuilder<ParentBuilder> withSchemaName(String schemaName){
            target.setSchemaName(schemaName);
            return this;
        }

        public ColumnForeignBuilder<ParentBuilder> withReferencedTableName(String referencedTableName){
            target.setReferencedTableName(referencedTableName);
            return this;
        }

        public ColumnForeignBuilder<ParentBuilder> withRefColumn(String refColumn){
            target.setRefColumns(Collections.singletonList(refColumn));
            return this;
        }

        public ColumnForeignBuilder<ParentBuilder> withOnDelete(Foreign.OnType onType){
            target.setOnDelete(onType);
            return this;
        }

        public ColumnForeignBuilder<ParentBuilder> withOnUpdate(Foreign.OnType onType){
            target.setOnUpdate(onType);
            return this;
        }

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
        public ColumnForeignBuilder<ParentBuilder> $FOREIGN_KEY(){
            return withForeignKey(true);
        }

        /**
         * Quick inout set referencedTableName
         * @return THIS
         */
        public ColumnForeignBuilder<ParentBuilder> $REFERENCES(String referencedTableName, String refColumn){
            return withReferencedTableName(referencedTableName)
                    .withRefColumn(refColumn);
        }

        /**
         * Quick inout set schemaName,referencedTableName
         * @return THIS
         */
        public ColumnForeignBuilder<ParentBuilder> $REFERENCES(String schemaName, String referencedTableName, String refColumn){
            return withSchemaName(schemaName)
                    .withReferencedTableName(referencedTableName)
                    .withRefColumn(refColumn);
        }

        /**
         * Quick into
         * @return OnTypeBuilder
         */
        public OnTypeBuilder<ColumnForeignBuilder> $ON_DELETE(){
            return new OnTypeBuilder<ColumnForeignBuilder>
                    ()
                    .enter(this, onType -> target.setOnDelete(onType));
        }

        /**
         * Quick into
         * @return OnTypeBuilder
         */
        public OnTypeBuilder<ColumnForeignBuilder> $ON_UPDATE(){
            return new OnTypeBuilder<ColumnForeignBuilder>
                    ()
                    .enter(this, onType -> target.setOnUpdate(onType));
        }

        /**
         * Quick set useNotForReplication
         * @return THIS
         */
        public ColumnForeignBuilder $NOT_FOR_REPLICATION(){
            return withUseNotForReplication(true);
        }

    }


    /**
     *
     * @param <ParentBuilder>
     */
    public static class ComputedColumnForeignBuilder<ParentBuilder>
            extends CodeTreeBuilder<ColumnForeignBuilder<ParentBuilder>,ParentBuilder,Foreign> {

        public ComputedColumnForeignBuilder(Foreign tar) {
            super(tar);
        }

        public ComputedColumnForeignBuilder<ParentBuilder> withForeignKey(boolean useForeignKey){
            target.setUseForeignKey(useForeignKey);
            return this;
        }

        public ComputedColumnForeignBuilder<ParentBuilder> withReferencedTableName(String referencedTableName){
            target.setReferencedTableName(referencedTableName);
            return this;
        }

        public ComputedColumnForeignBuilder<ParentBuilder> withRefColumn(String refColumn){
            target.setRefColumns(Collections.singletonList(refColumn));
            return this;
        }

        public ComputedColumnForeignBuilder<ParentBuilder> withOnDelete(Foreign.OnType onType){
            target.setOnDelete(onType);
            return this;
        }

        public ComputedColumnForeignBuilder<ParentBuilder> withOnUpdate(Foreign.OnType onType){
            target.setOnUpdate(onType);
            return this;
        }

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
        public ComputedColumnForeignBuilder<ParentBuilder> $FOREIGN_KEY(){
            return withForeignKey(true);
        }

        /**
         * Quick inout set referencedTableName
         * @return THIS
         */
        public ComputedColumnForeignBuilder $REFERENCES(String referencedTableName, String refColumn){
            return withReferencedTableName(referencedTableName)
                    .withRefColumn(refColumn);
        }

        /**
         * Quick into
         * @return OnTypeBuilder
         */
        public OnTypeBuilder<ComputedColumnForeignBuilder> $ON_DELETE(){
            return new OnTypeBuilder<ComputedColumnForeignBuilder>
                    ()
                    .enter(this, onType -> target.setOnDelete(onType));
        }

        /**
         * Quick into
         * @return OnTypeBuilder
         */
        public OnTypeBuilder<ComputedColumnForeignBuilder> $ON_UPDATE(){
            return new OnTypeBuilder<ComputedColumnForeignBuilder>
                    ()
                    .enter(this, onType -> target.setOnUpdate(onType));
        }

        /**
         * Quick set useNotForReplication
         * @return THIS
         */
        public ComputedColumnForeignBuilder $NOT_FOR_REPLICATION(){
            return withUseNotForReplication(true);
        }

    }


    /**
     * Use
     * in column_constraint
     * on --Memory optimized CREATE TABLE Syntax
     * @param <ParentBuilder>
     */
    public static class ReferencesColumnForeignBuilder<ParentBuilder>
            extends CodeTreeBuilder<ReferencesColumnForeignBuilder<ParentBuilder>,ParentBuilder,Foreign> {

        public ReferencesColumnForeignBuilder(Foreign tar) {
            super(tar);
        }


        public ReferencesColumnForeignBuilder<ParentBuilder> withForeignKey(boolean useForeignKey){
            target.setUseForeignKey(useForeignKey);
            return this;
        }

        public ReferencesColumnForeignBuilder<ParentBuilder> withSchemaName(String schemaName){
            target.setSchemaName(schemaName);
            return this;
        }

        public ReferencesColumnForeignBuilder<ParentBuilder> withReferencedTableName(String referencedTableName){
            target.setReferencedTableName(referencedTableName);
            return this;
        }

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
        public ReferencesColumnForeignBuilder<ParentBuilder> $FOREIGN_KEY(){
            return withForeignKey(true);
        }

        /**
         * Quick inout set referencedTableName
         * @return THIS
         */
        public ReferencesColumnForeignBuilder $REFERENCES(String referencedTableName, String refColumn){
            return withReferencedTableName(referencedTableName)
                    .withRefColumn(refColumn);
        }

    }



    /**
     *
     * @param <ParentBuilder>
     */
    public static class TableForeignBuilder<ParentBuilder>
            extends CodeTreeBuilder<TableForeignBuilder<ParentBuilder>,ParentBuilder,Foreign> {

        public TableForeignBuilder(Foreign tar) {
            super(tar);
        }


        public TableForeignBuilder<ParentBuilder> withColumns(List<String> columns){
            target.setColumns(columns);
            return this;
        }

        public TableForeignBuilder<ParentBuilder> withReferencedTableName(String referencedTableName){
            target.setReferencedTableName(referencedTableName);
            return this;
        }

        public TableForeignBuilder<ParentBuilder> withRefColumn(List<String> refColumns){
            target.setRefColumns(refColumns);
            return this;
        }

        public TableForeignBuilder<ParentBuilder> withOnDelete(Foreign.OnType onType){
            target.setOnDelete(onType);
            return this;
        }

        public TableForeignBuilder<ParentBuilder> withOnUpdate(Foreign.OnType onType){
            target.setOnUpdate(onType);
            return this;
        }

        public TableForeignBuilder<ParentBuilder> withUseNotForReplication(boolean useNotForReplication){
            target.setUseNotForReplication(useNotForReplication);
            return this;
        }

        /*
        Quick
         */

        /**
         * Quick set columns
         * @param columns
         * @return THIS
         */
        public TableForeignBuilder $(String... columns){
            initAdd(Arrays.asList(columns),
                    target::getColumns,
                    target::setColumns);
            return this;
        }

        /**
         * Quick inout set referencedTableName, refColumn
         * @return THIS
         */
        public TableForeignBuilder $REFERENCES(String referencedTableName, String... refColumns){
            initAdd(Arrays.asList(refColumns),
                    target::getRefColumns,
                    target::setRefColumns);
            return withReferencedTableName(referencedTableName);
        }

        /**
         * Quick into
         * @return OnTypeBuilder
         */
        public OnTypeBuilder<TableForeignBuilder> $ON_DELETE(){
            return new OnTypeBuilder<TableForeignBuilder>
                    ()
                    .enter(this, onType -> target.setOnDelete(onType));
        }

        /**
         * Quick into
         * @return OnTypeBuilder
         */
        public OnTypeBuilder<TableForeignBuilder> $ON_UPDATE(){
            return new OnTypeBuilder<TableForeignBuilder>
                    ()
                    .enter(this, onType -> target.setOnUpdate(onType));
        }

        /**
         * Quick set useNotForReplication
         * @return THIS
         */
        public TableForeignBuilder $NOT_FOR_REPLICATION(){
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
            extends CodeTreeBuilder<ReferencesTableForeignBuilder<ParentBuilder>,ParentBuilder,Foreign> {

        public ReferencesTableForeignBuilder(Foreign tar) {
            super(tar);
        }


        public ReferencesTableForeignBuilder<ParentBuilder> withColumns(List<String> columns){
            target.setColumns(columns);
            return this;
        }

        public ReferencesTableForeignBuilder<ParentBuilder> withSchemaName(String schemaName){
            target.setSchemaName(schemaName);
            return this;
        }

        public ReferencesTableForeignBuilder<ParentBuilder> withReferencedTableName(String referencedTableName){
            target.setReferencedTableName(referencedTableName);
            return this;
        }

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
            initAdd(Arrays.asList(columns),
                    target::getColumns,
                    target::setColumns);
            return this;
        }

        /**
         * Quick inout set referencedTableName
         * @return THIS
         */
        public ReferencesTableForeignBuilder $REFERENCES(String referencedTableName, String refColumn){
            return withReferencedTableName(referencedTableName)
                    .withRefColumn(refColumn);
        }

    }

    /**
     *
     * @param <ParentBuilder>
     */
    public static class OnTypeBuilder<ParentBuilder>
            extends CodeTreeBuilder<OnTypeBuilder<ParentBuilder>,ParentBuilder,Foreign.OnType> {

        public OnTypeBuilder(Foreign.OnType onType) {
            super(onType);
        }

        public OnTypeBuilder() {
            super(null);
        }

        public ParentBuilder $NO_ACTION(){
            target = Foreign.OnType.NO_ACTION;
            return this.back();
        }

        public ParentBuilder $CASCADE(){
            target = Foreign.OnType.CASCADE;
            return this.back();
        }

        public ParentBuilder $SET_NULL(){
            target = Foreign.OnType.SET_NULL;
            return this.back();
        }

        public ParentBuilder $SET_DEFAULT(){
            target = Foreign.OnType.SET_DEFAULT;
            return this.back();
        }

    }

}
