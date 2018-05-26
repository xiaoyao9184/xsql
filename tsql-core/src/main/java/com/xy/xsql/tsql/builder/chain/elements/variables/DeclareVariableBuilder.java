package com.xy.xsql.tsql.builder.chain.elements.variables;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.table.TableTypeDefinitionBuilder;
import com.xy.xsql.tsql.model.datatypes.DataType;
import com.xy.xsql.tsql.model.datatypes.table.table.TableTypeDefinition;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.variables.DeclareVariable;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;

import static com.xy.xsql.core.handler.list.ListHandler.list;
import static com.xy.xsql.core.handler.object.GetterSetterObjectHandler.object;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;

/**
 * DeclareVariableBuilder
 * Created by xiaoyao9184 on 2017/3/16.
 */
@SuppressWarnings({"WeakerAccess", "SameParameterValue", "TypeParameterHidesVisibleType", "unused"})
public class DeclareVariableBuilder<ParentBuilder>
        extends ParentHoldBuilder<DeclareVariableBuilder<ParentBuilder>,ParentBuilder,DeclareVariable> {

    public DeclareVariableBuilder() {
        super(new DeclareVariable());
    }

    public DeclareVariableBuilder(DeclareVariable target) {
        super(target);
    }

    /**
     * in
     * @return DeclareVariableItemBuilder
     */
    public DeclareVariableItemBuilder<DeclareVariableBuilder<ParentBuilder>> withItem(){
        return new DeclareVariableItemBuilder<DeclareVariableBuilder<ParentBuilder>>
                (list(target::getItems, target::setItems)
                        .addNew(DeclareVariable.Item::new))
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

    /**
     * in
     * @return TableTypeDefinitionBuilder
     */
    public TableTypeDefinitionBuilder<DeclareVariableBuilder<ParentBuilder>> withTable(){
        return new TableTypeDefinitionBuilder<DeclareVariableBuilder<ParentBuilder>>
                (object(target::getTableTypeDefinition, target::setTableTypeDefinition)
                        .init(TableTypeDefinition::new))
                .in(this);
    }




    /*
    Quick
     */

    public static DeclareVariableBuilder<Void> $Declare(){
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
    public class DeclareVariableItemBuilder<ParentBuilder>
            extends ParentHoldBuilder<DeclareVariableItemBuilder<ParentBuilder>,ParentBuilder,DeclareVariable.Item> {

        public DeclareVariableItemBuilder(DeclareVariable.Item target) {
            super(target);
        }

        public DeclareVariableItemBuilder() {
            super(new DeclareVariable.Item());
        }

        /**
         * set
         * @param local_cursor LocalVariable
         * @return THIS
         */
        public DeclareVariableItemBuilder<ParentBuilder> withLocalVariable(LocalVariable local_cursor){
            target.setLocalVariable(local_cursor);
            return this;
        }

        /**
         * set
         * @param useAs as
         * @return THIS
         */
        public DeclareVariableItemBuilder<ParentBuilder> withAs(boolean useAs) {
            target.setUseAs(useAs);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public DeclareVariableItemBuilder<ParentBuilder> withAs() {
            target.setUseAs(true);
            return this;
        }

        /**
         * set
         * @param value Expression
         * @return THIS
         */
        public DeclareVariableItemBuilder<ParentBuilder> withValue(Expression value){
            target.setValue(value);
            return this;
        }

        /**
         * set
         * @param dateType DateType
         * @return THIS
         */
        public DeclareVariableItemBuilder<ParentBuilder> withDateType(DataType dateType){
            target.setDataType(dateType);
            return this;
        }

        /**
         * set
         * @param useCursor cursor
         * @return THIS
         */
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
        public ParentBuilder $Cursor(){
            return withCursor(true)
                    .and();
        }

    }

    /**
     * TransformBuilder
     * @param <ParentBuilder>
     */
    public class TransformBuilder<ParentBuilder>
            extends ParentHoldBuilder<TransformBuilder<ParentBuilder>,ParentBuilder,DeclareVariable> {

        public TransformBuilder() {
            super(new DeclareVariable());
        }

        public TransformBuilder(DeclareVariable target) {
            super(target);
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
                    (list(target::getItems, target::setItems)
                            .addNew(DeclareVariable.Item::new))
                    .in(this.and())
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
                    (list(target::getItems, target::setItems)
                            .addNew(DeclareVariable.Item::new))
                    .in(this.and())
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
            withTable(object(target::getTableTypeDefinition, target::setTableTypeDefinition)
                    .init(TableTypeDefinition::new));
            return new TableTypeDefinitionBuilder<ParentBuilder>
                    (target.getTableTypeDefinition())
                    .in(this.and());
        }
    }
}
