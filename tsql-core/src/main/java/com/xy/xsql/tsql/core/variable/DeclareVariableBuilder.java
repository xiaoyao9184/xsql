package com.xy.xsql.tsql.core.variable;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.core.datatype.TableTypeDefinitionBuilder;
import com.xy.xsql.tsql.model.datatypes.DataType;
import com.xy.xsql.tsql.model.datatypes.TableTypeDefinition;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.variable.DeclareVariable;
import com.xy.xsql.tsql.model.variable.LocalVariable;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.ListBuilder.initNew;
import static com.xy.xsql.tsql.core.expression.Expressions.e_variable;

/**
 * DeclareVariableBuilder
 * Created by xiaoyao9184 on 2017/3/16.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class DeclareVariableBuilder<ParentBuilder>
        extends CodeTreeBuilder<DeclareVariableBuilder<ParentBuilder>,ParentBuilder,DeclareVariable> {

    public DeclareVariableBuilder() {
        super(new DeclareVariable());
    }

    public DeclareVariableBuilder(DeclareVariable tar) {
        super(tar);
    }

    /**
     * in
     * @return DeclareVariableItemBuilder
     */
    public DeclareVariableItemBuilder<DeclareVariableBuilder<ParentBuilder>> withItem(){
        return new DeclareVariableItemBuilder<DeclareVariableBuilder<ParentBuilder>>
                (initNew(DeclareVariable.Item::new,
                        target::getItems,
                        target::setItems))
                .in(this);
    }

    /**
     * set
     * @param tableVariableName tableVariableName
     * @return THIS
     */
    public DeclareVariableBuilder<ParentBuilder> withTableVariableName(LocalVariable tableVariableName){
        target.setTableVariableName(tableVariableName);
        return this;
    }

    /**
     * set
     * @param useAs As
     * @return THIS
     */
    public DeclareVariableBuilder<ParentBuilder> withAs(boolean useAs){
        target.setUseAs(useAs);
        return this;
    }

    /**
     * set
     * @param tableTypeDefinition table_type_definition
     * @return THIS
     */
    public DeclareVariableBuilder<ParentBuilder> withTable(TableTypeDefinition tableTypeDefinition){
        target.setTableTypeDefinition(tableTypeDefinition);
        return this;
    }


    /*
    Quick
     */

    public static DeclareVariableBuilder<Void> DECLARE(){
        return new DeclareVariableBuilder<>();
    }

    /**
     * Quick in
     * @return DeclareVariableItemBuilder
     */
    public DeclareVariableItemBuilder<DeclareVariableBuilder<ParentBuilder>> $(){
        return withItem();
    }

    /**
     * Quick in
     * @param local_cursor_table local_variable or cursor_variable_name or table_variable_name
     * @return TransformBuilder
     */
    public TransformBuilder<DeclareVariableBuilder<ParentBuilder>> $(String local_cursor_table) {
        return new TransformBuilder<DeclareVariableBuilder<ParentBuilder>>
                (target)
                .in(this)
                .withLocalVariable(e_variable(local_cursor_table));
    }




    /**
     * DeclareVariableItemBuilder
     * @param <ParentBuilder>
     */
    @SuppressWarnings({"WeakerAccess", "SameParameterValue", "TypeParameterHidesVisibleType", "unused"})
    public class DeclareVariableItemBuilder<ParentBuilder>
            extends CodeTreeBuilder<DeclareVariableItemBuilder<ParentBuilder>,ParentBuilder,DeclareVariable.Item> {

        public DeclareVariableItemBuilder(DeclareVariable.Item tar) {
            super(tar);
        }

        public DeclareVariableItemBuilder() {
            super(new DeclareVariable.Item());
        }

        public DeclareVariableItemBuilder<ParentBuilder> withLocalVariable(LocalVariable local_cursor){
            target.setLocalVariable(local_cursor);
            return this;
        }

        public DeclareVariableItemBuilder<ParentBuilder> withAs(boolean useAs) {
            target.setUseAs(useAs);
            return this;
        }

        public DeclareVariableItemBuilder<ParentBuilder> withAs() {
            target.setUseAs(true);
            return this;
        }

        public DeclareVariableItemBuilder<ParentBuilder> withValue(Expression value){
            target.setValue(value);
            return this;
        }

        public DeclareVariableItemBuilder<ParentBuilder> withDateType(DataType dateType){
            target.setDataType(dateType);
            return this;
        }

        public DeclareVariableItemBuilder<ParentBuilder> withCursor(boolean useCursor){
            target.setUseCursor(useCursor);
            return this;
        }

        /*
        Quick
         */


        /**
         * Quick set
         * @param local_cursor local_variable or cursor_variable_name
         * @return THIS
         */
        public DeclareVariableItemBuilder<ParentBuilder> $(String local_cursor) {
            return withValue(e_variable(local_cursor));
        }

        /**
         * Quick set
         * @return THIS
         */
        public DeclareVariableItemBuilder<ParentBuilder> $As() {
            return withAs(true);
        }

        /**
         * Quick set
         * @param dataType DataType
         * @return THIS
         */
        public DeclareVariableItemBuilder<ParentBuilder> $(DataType dataType) {
            return withDateType(dataType);
        }

        /**
         * Quick end
         * @param value value
         * @return PARENT
         */
        public ParentBuilder $(Expression value){
            return withValue(value)
                    .and();
        }

        /**
         * Quick end
         * @return PARENT
         */
        public ParentBuilder $CURSOR(){
            return withCursor(true)
                    .and();
        }

    }

    /**
     * TransformBuilder
     * @param <ParentBuilder>
     */
    @SuppressWarnings({"WeakerAccess", "SameParameterValue", "TypeParameterHidesVisibleType", "unused"})
    public class TransformBuilder<ParentBuilder>
            extends CodeTreeBuilder<TransformBuilder<ParentBuilder>,ParentBuilder,DeclareVariable> {

        public TransformBuilder(DeclareVariable declareVariable) {
            super(declareVariable);
        }

        private LocalVariable localVariable;
        private boolean useAs;

        /**
         * set
         * @param localVariable local_variable or cursor_variable_name or table_variable_name
         * @return THIS
         */
        public TransformBuilder<ParentBuilder> withLocalVariable(LocalVariable localVariable){
            this.localVariable = localVariable;
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public TransformBuilder<ParentBuilder> withAs(){
            this.useAs = true;
            return this;
        }

        /**
         * transform
         * @param dataType DataType
         * @return DeclareVariableItemBuilder
         */
        public DeclareVariableItemBuilder<ParentBuilder> withDataType(DataType dataType) {
            return new DeclareVariableItemBuilder<ParentBuilder>
                    (initNew(DeclareVariable.Item::new,
                            target::getItems,
                            target::setItems))
                    .in(and())
                    .withLocalVariable(this.localVariable)
                    .withAs(this.useAs)
                    .withDateType(dataType);
        }

        /**
         * transform
         * @return DeclareVariableItemBuilder
         */
        public DeclareVariableItemBuilder<ParentBuilder> withCursor() {
            return new DeclareVariableItemBuilder<ParentBuilder>
                    (initNew(DeclareVariable.Item::new,
                            target::getItems,
                            target::setItems))
                    .in(and())
                    .withLocalVariable(this.localVariable)
                    .withAs(this.useAs)
                    .withCursor(true);
        }

        /**
         * transform
         * @param tableTypeDefinition TableTypeDefinition
         * @return PARENT
         */
        public ParentBuilder withTable(TableTypeDefinition tableTypeDefinition) {
            target.setTableVariableName(this.localVariable);
            target.setUseAs(this.useAs);
            target.setTableTypeDefinition(tableTypeDefinition);
            return and();
        }


        /*
        Quick
         */

        /**
         * Quick set
         * @return THIS
         */
        public TransformBuilder<ParentBuilder> $As() {
            return withAs();
        }

        /**
         * Quick transform
         * @param dataType DataType
         * @return DeclareVariableItemBuilder
         */
        public DeclareVariableItemBuilder<ParentBuilder> $(DataType dataType) {
            return withDataType(dataType);
        }

        /**
         * Quick transform/end
         * @return PARENT
         */
        public ParentBuilder $Cursor() {
            return withCursor()
                    .and();
        }

        /**
         * Quick transform/set
         * @return PARENT
         */
        public ParentBuilder $Table(TableTypeDefinition tableTypeDefinition) {
            return withTable(tableTypeDefinition);
        }

        /**
         * Quick transform
         * @return TableTypeDefinitionBuilder
         */
        public TableTypeDefinitionBuilder<ParentBuilder> $Table() {
            withTable(initSet(TableTypeDefinition::new,
                    target::getTableTypeDefinition,
                    target::setTableTypeDefinition));
            return new TableTypeDefinitionBuilder<ParentBuilder>
                    (target.getTableTypeDefinition())
                    .in(and());
        }
    }
}
