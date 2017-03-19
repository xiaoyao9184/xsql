package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.clause.Output;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.variable.LocalVariable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        this.tar.setTableVariable(new LocalVariable(tableVariable));
        return this;
    }

    public OutputBuilder<ParentBuilder> withTableName(TableName outputTable){
        this.tar.setOutputTable(outputTable);
        return this;
    }

    public OutputBuilder<ParentBuilder> withColumnName(ColumnName... columnNames){
        initAdd(Arrays.asList(columnNames),
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


    /**
     * Quick set DmlSelect
     * @param names
     * @return
     */
    @Deprecated
    public OutputBuilder<ParentBuilder> $(String... names){
        List<Output.DmlSelect> list = Arrays.stream(names)
                .map((Output.ColumnName::new))
                .map(Output.DmlSelect::new)
                .collect(Collectors.toList());
        initAdd(list,
                this.tar::getDmlSelectList,
                this.tar::setDmlSelectList);
        return this;
    }

    /**
     * Quick set DmlSelect
     * @param columnNames
     * @return
     */
    public OutputBuilder<ParentBuilder> $(Output.ColumnName... columnNames){
        List<Output.DmlSelect> list = Arrays.stream(columnNames)
                .map(Output.DmlSelect::new)
                .collect(Collectors.toList());
        initAdd(list,
                this.tar::getDmlSelectList,
                this.tar::setDmlSelectList);
        return this;
    }

    /**
     * Quick set DmlSelect
     * TODO maybe use Expression replace ColumnName and GroupExpression
     * @param expressions
     * @return
     */
    public OutputBuilder<ParentBuilder> $(Expression... expressions){
        List<Output.DmlSelect> list = Arrays.stream(expressions)
                .map(Output.DmlSelect::new)
                .collect(Collectors.toList());
        initAdd(list,
                this.tar::getDmlSelectList,
                this.tar::setDmlSelectList);
        return this;
    }

    /**
     * Quick set DmlSelect
     * @param names
     * @return
     */
    public OutputBuilder<ParentBuilder> $Inserted(String... names){
        List<Output.DmlSelect> list = Arrays.stream(names)
                .map((name -> {
                    Output.ColumnName columnName = new Output.ColumnName(name);
                    columnName.setUseInserted(true);
                    return columnName;
                }))
                .map(Output.DmlSelect::new)
                .collect(Collectors.toList());
        initAdd(list,
                this.tar::getDmlSelectList,
                this.tar::setDmlSelectList);
        return this;
    }

    /**
     * Quick set DmlSelect
     * @param names
     * @return
     */
    public OutputBuilder<ParentBuilder> $Deleted(String... names){
        List<Output.DmlSelect> list = Arrays.stream(names)
                .map((name -> {
                    Output.ColumnName columnName = new Output.ColumnName(name);
                    columnName.setUseDeleted(true);
                    return columnName;
                }))
                .map(Output.DmlSelect::new)
                .collect(Collectors.toList());
        initAdd(list,
                this.tar::getDmlSelectList,
                this.tar::setDmlSelectList);
        return this;
    }

    /**
     * Quick set tableName
     * @param tableName
     * @param columnNames
     * @return
     */
    public OutputBuilder<ParentBuilder> $Into(TableName tableName, ColumnName... columnNames){
        return withTableName(tableName)
                .withColumnName(columnNames);
    }

    /**
     * Quick set tableVariable
     * @param tableVariable
     * @return
     */
    public OutputBuilder<ParentBuilder> $Into(String tableVariable, ColumnName... columnNames){
        return withTableVariable(tableVariable)
                .withColumnName(columnNames);
    }

    /**
     * Quick set OutputDmlSelect
     * @param names
     * @return
     */
    public OutputBuilder<ParentBuilder> $Output(String... names){
        List<Output.DmlSelect> list = Arrays.stream(names)
                .map((Output.ColumnName::new))
                .map(Output.DmlSelect::new)
                .collect(Collectors.toList());
        initAdd(list,
                this.tar::getOutputDmlSelectList,
                this.tar::setOutputDmlSelectList);
        return this;
    }

    /**
     * Quick set OutputDmlSelect
     * @param columnNames
     * @return
     */
    public OutputBuilder<ParentBuilder> $Output(Output.ColumnName... columnNames){
        List<Output.DmlSelect> list = Arrays.stream(columnNames)
                .map(Output.DmlSelect::new)
                .collect(Collectors.toList());
        initAdd(list,
                this.tar::getDmlSelectList,
                this.tar::setDmlSelectList);
        return this;
    }

    /**
     * Quick set OutputDmlSelect
     * @param expressions
     * @return
     */
    public OutputBuilder<ParentBuilder> $Output(Expression... expressions){
        List<Output.DmlSelect> list = Arrays.stream(expressions)
                .map(Output.DmlSelect::new)
                .collect(Collectors.toList());
        initAdd(list,
                this.tar::getDmlSelectList,
                this.tar::setDmlSelectList);
        return this;
    }

    /**
     * Quick set DmlSelect
     * @param names
     * @return
     */
    public OutputBuilder<ParentBuilder> $Output_Inserted(String... names){
        List<Output.DmlSelect> list = Arrays.stream(names)
                .map((name -> {
                    Output.ColumnName columnName = new Output.ColumnName(name);
                    columnName.setUseInserted(true);
                    return columnName;
                }))
                .map(Output.DmlSelect::new)
                .collect(Collectors.toList());
        initAdd(list,
                this.tar::getDmlSelectList,
                this.tar::setDmlSelectList);
        return this;
    }

    /**
     * Quick set DmlSelect
     * @param names
     * @return
     */
    public OutputBuilder<ParentBuilder> $Output_Deleted(String... names){
        List<Output.DmlSelect> list = Arrays.stream(names)
                .map((name -> {
                    Output.ColumnName columnName = new Output.ColumnName(name);
                    columnName.setUseDeleted(true);
                    return columnName;
                }))
                .map(Output.DmlSelect::new)
                .collect(Collectors.toList());
        initAdd(list,
                this.tar::getDmlSelectList,
                this.tar::setDmlSelectList);
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
    public static Output.ColumnName c_inserted(String name){
        return new ColumnNameBuilder<Output.ColumnName>
                (new Output.ColumnName(name))
                .withInserted()
                .build();
    }

    /**
     * with all
     * @return
     */
    public static Output.ColumnName c_inserted(){
        return new ColumnNameBuilder<Output.ColumnName>
                (new Output.ColumnName())
                .withInserted()
                .build();
    }

    /**
     * with name
     * @param name
     * @return
     */
    public static Output.ColumnName c_deleted(String name){
        return new ColumnNameBuilder<Output.ColumnName>
                (new Output.ColumnName(name))
                .withDeleted()
                .build();
    }

    /**
     * with all
     * @return
     */
    public static Output.ColumnName c_deleted(){
        return new ColumnNameBuilder<Output.ColumnName>
                (new Output.ColumnName())
                .withDeleted()
                .build();
    }

    /**
     *
     * @return
     */
    public static Expression c_$action(){
        return new ColumnNameBuilder<Output.ColumnName>
                (new Output.ColumnName("$action"))
                .build();
    }

}
