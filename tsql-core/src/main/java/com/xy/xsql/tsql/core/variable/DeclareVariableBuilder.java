package com.xy.xsql.tsql.core.variable;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.datatype.DataType;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.variable.DeclareVariable;
import com.xy.xsql.tsql.model.variable.LocalVariable;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.ListBuilder.initNew;
import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.e_variable;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 */
public class DeclareVariableBuilder<ParentBuilder>
        extends CodeTreeBuilder<DeclareVariableBuilder<ParentBuilder>,ParentBuilder,DeclareVariable> {


    public DeclareVariableBuilder(DeclareVariable tar) {
        super(tar);
    }

    public DeclareVariableBuilder() {
        super(new DeclareVariable());
    }

    public DeclareVariableItemBuilder<DeclareVariableBuilder<ParentBuilder>> withItem(){
        return new DeclareVariableItemBuilder<DeclareVariableBuilder<ParentBuilder>>
                (initNew(DeclareVariable.Item::new,
                        target::getItems,
                        target::setItems))
                .in(this);
    }

    public DeclareVariableBuilder<ParentBuilder> withTable(String tableVariableName){
        initSet(LocalVariable::new,
                        target::getTableVariableName,
                        target::setTableVariableName);
        return this;
    }

//    public TableTypeDefinitionBuilder<DeclareVariableBuilder<ParentBuilder>> withTable(String tableVariableName){
//        initSet(LocalVariable::new,
//                target::getTableVariableName,
//                target::setTableVariableName);
//        return this;
//    }


    public class DeclareVariableItemBuilder<ParentBuilder>
            extends CodeTreeBuilder<DeclareVariableItemBuilder<ParentBuilder>,ParentBuilder,DeclareVariable.Item> {

        public DeclareVariableItemBuilder(DeclareVariable.Item tar) {
            super(tar);
        }

        public DeclareVariableItemBuilder<ParentBuilder> withLocalVariable(String variable){
            target.setLocalVariable(e_variable(variable));
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
    }

}
