package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.core.CodeTreeBuilder;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.clause.Output;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.TableName;
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

    public OutputBuilder<ParentBuilder> withColumnName(ColumnName columnName){
        initAdd(columnName,
                tar::getColumnList,
                tar::setColumnList);
        return this;
    }

    public DmlSelectBuilder<OutputBuilder<ParentBuilder>> withOutputDmlSelect(){
        return new DmlSelectBuilder<OutputBuilder<ParentBuilder>>
                (initNew(Output.DmlSelect::new,
                        this.tar::getOutputDmlSelectList,
                        this.tar::setOutputDmlSelectList))
                .in(this);
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

        public DmlSelectBuilder<ParentBuilder> withColumnName(Output.ColumnName columnName){
            tar.setColumnName(columnName);
            return this;
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


    /**
     * with name
     * @param name
     * @return
     */
    public static Output.ColumnName inserted_c(String name){
        return new ColumnNameBuilder<Output.ColumnName>(new Output.ColumnName(name))
                .withInserted().build();
    }

    /**
     * with all
     * @return
     */
    public static Output.ColumnName inserted_c(){
        return new ColumnNameBuilder<Output.ColumnName>(new Output.ColumnName())
                .withInserted().build();
    }

    /**
     * with name
     * @param name
     * @return
     */
    public static Output.ColumnName deleted_c(String name){
        return new ColumnNameBuilder<Output.ColumnName>(new Output.ColumnName(name))
                .withDeleted().build();
    }

    /**
     * with all
     * @return
     */
    public static Output.ColumnName deleted_c(){
        return new ColumnNameBuilder<Output.ColumnName>(new Output.ColumnName())
                .withDeleted().build();
    }
}
