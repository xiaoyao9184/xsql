package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.core.CodeTreeBuilder;
import com.xy.xsql.tsql.model.clause.With;

import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class WithBuilder<ParentBuilder>
        extends CodeTreeBuilder<WithBuilder<ParentBuilder>,ParentBuilder,With> {

    public WithBuilder() {
        super(new With());
    }

    public WithBuilder(With with) {
        super(with);
    }

    public CommonTableExpressionBuilder<WithBuilder<ParentBuilder>> withItem(){
        return new CommonTableExpressionBuilder<WithBuilder<ParentBuilder>>
                (initNew(With.CommonTableExpression::new,
                        this.tar::getCommonTableExpressionList,
                        this.tar::setCommonTableExpressionList))
                .in(this);
    }

    public static class CommonTableExpressionBuilder<ParentBuilder>
        extends CodeTreeBuilder<CommonTableExpressionBuilder<ParentBuilder>,ParentBuilder,With.CommonTableExpression> {

        public CommonTableExpressionBuilder() {
            super(new With.CommonTableExpression());
        }

        public CommonTableExpressionBuilder(With.CommonTableExpression commonTableExpression) {
            super(commonTableExpression);
        }


        public CommonTableExpressionBuilder<ParentBuilder> withExpressionName(String expressionName){
            this.tar.setExpressionName(expressionName);
            return this;
        }

        public CommonTableExpressionBuilder<ParentBuilder> withColumnName(String columnName){
            initAdd(columnName,
                     this.tar::getColumnName,
                    this.tar::setColumnName);
            return this;
        }

//        public CommonTableExpressionBuilder<ParentBuilder> withCteQueryDefinition(Sentence cteQueryDefinition){
//            this.tar.setCteQueryDefinition(cteQueryDefinition);
//            return this;
//        }

    }

}
