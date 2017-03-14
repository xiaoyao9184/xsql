package com.xy.xsql.orm.core.sql.clause;

import com.xy.xsql.orm.core.CodeTreeBuilder;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.tsql.model.clause.Output;
import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.orm.data.sql.element.info.TableName;
import com.xy.xsql.tsql.model.variable.VariableString;

import static com.xy.xsql.core.FiledBuilder.set;
import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class OutputBuilder<ParentBuilder>
        extends CodeTreeBuilder<OutputBuilder<ParentBuilder>,ParentBuilder,Output> {

    public OutputBuilder() {
        super(new Output());
    }

    public OutputBuilder(Output output) {
        super(output);
    }


    public DmlSelectBuilder<OutputBuilder<ParentBuilder>> withDmlSelect(){
        return new DmlSelectBuilder<OutputBuilder<ParentBuilder>>
                (initNew(Output.DmlSelect::new,
                        this.tar::getDmlSelectList,
                        this.tar::setDmlSelectList))
                .in(this);
    }

    public OutputBuilder<ParentBuilder> withTableVariable(String tableVariable){
        this.tar.setTableVariable(new VariableString(tableVariable));
        return this;
    }

    public OutputBuilder<ParentBuilder> withTableName(TableName outputTable){
        this.tar.setOutputTable(outputTable);
        return this;
    }

    public OutputBuilder<ParentBuilder> withColumnName(Column columnName){
        initAdd(columnName,
                tar::getColumnList,
                tar::setColumnList);
        return this;
    }



    public static class DmlSelectBuilder<ParentBuilder>
            extends CodeTreeBuilder<DmlSelectBuilder<ParentBuilder>,ParentBuilder,Output.DmlSelect> {

        public DmlSelectBuilder() {
            super(new Output.DmlSelect());
        }

        public DmlSelectBuilder(Output.DmlSelect dmlSelect) {
            super(dmlSelect);
        }


        public ColumnNameBuilder<DmlSelectBuilder<ParentBuilder>> withColumnName(){
            return new ColumnNameBuilder<DmlSelectBuilder<ParentBuilder>>
                    (set(Output.ColumnName::new,
                            this.tar::setColumnName))
                    .in(this);
        }

        public DmlSelectBuilder<ParentBuilder> withScalarExpression(Expression scalarExpression){
            this.tar.setScalarExpression(scalarExpression);
            return this;
        }

        public DmlSelectBuilder<ParentBuilder> withAs(){
            this.tar.setUseAs(true);
            return this;
        }

        public DmlSelectBuilder<ParentBuilder> withColumnAliasIdentifier(String columnAliasIdentifier){
            this.tar.setColumnAliasIdentifier(columnAliasIdentifier);
            return this;
        }

    }



    public static class ColumnNameBuilder<ParentBuilder>
            extends CodeTreeBuilder<ColumnNameBuilder<ParentBuilder>,ParentBuilder,Output.ColumnName> {

        public ColumnNameBuilder(Output.ColumnName tar) {
            super(tar);
        }

        public ColumnNameBuilder<ParentBuilder> withDeleted(){
            this.tar.setUseDeleted(true);
            return this;
        }

        public ColumnNameBuilder<ParentBuilder> withInserted(){
            this.tar.setUseInserted(true);
            return this;
        }

        public ColumnNameBuilder<ParentBuilder> withFromTableName(String fromTableName){
            this.tar.setFromTableName(fromTableName);
            return this;
        }

        public ColumnNameBuilder<ParentBuilder> withAll(){
            this.tar.setUseAll(true);
            return this;
        }

        public ColumnNameBuilder<ParentBuilder> withColumnName(String columnName){
            this.tar.setColumnName(columnName);
            return this;
        }

        public ColumnNameBuilder<ParentBuilder> with$action(){
            this.tar.set$action(true);
            return this;
        }
    }

    public static ColumnNameBuilder<Output.ColumnName> column_name(){
        return new ColumnNameBuilder<Output.ColumnName>(new Output.ColumnName());
    }
}
