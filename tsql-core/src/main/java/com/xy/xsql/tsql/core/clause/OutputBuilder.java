package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.clause.Output;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.elements.expressions.ScalarExpression;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.util.CheckUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.xy.xsql.core.FiledBuilder.set;
import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * OutputBuilder
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
                        this.target::getDmlSelectList,
                        this.target::setDmlSelectList))
                .in(this);
    }

    public OutputBuilder<ParentBuilder> withTableVariable(String tableVariable){
        this.target.setTableVariable(new LocalVariable(tableVariable));
        return this;
    }

    public OutputBuilder<ParentBuilder> withTableName(TableName outputTable){
        this.target.setOutputTable(outputTable);
        return this;
    }

    public OutputBuilder<ParentBuilder> withColumnName(ColumnName... columnNames){
        if(CheckUtil.isNullOrEmpty(columnNames)){
            return this;
        }
        initAdd(Arrays.asList(columnNames),
                target::getColumnList,
                target::setColumnList);
        return this;
    }

    public DmlSelectBuilder<OutputBuilder<ParentBuilder>> withOutputDmlSelect(){
        return new DmlSelectBuilder<OutputBuilder<ParentBuilder>>
                (initNew(Output.DmlSelect::new,
                        this.target::getOutputDmlSelectList,
                        this.target::setOutputDmlSelectList))
                .in(this);
    }

    public OutputBuilder<ParentBuilder> withDmlSelect(List<Output.DmlSelect> dmlSelectList){
        this.target.setDmlSelectList(dmlSelectList);
        return this;
    }

    public OutputBuilder<ParentBuilder> withColumnName(List<ColumnName> columnNameList){
        if(CheckUtil.isNullOrEmpty(columnNameList)){
            return this;
        }
        initAdd(columnNameList,
                target::getColumnList,
                target::setColumnList);
        return this;
    }

    public OutputBuilder<ParentBuilder> withOutputDmlSelect(List<Output.DmlSelect> dmlSelectList){
        this.target.setOutputDmlSelectList(dmlSelectList);
        return this;
    }



    /*
    Quick set
     */

    /**
     * Quick set DmlSelect
     * @param names
     * @return
     */
    @Deprecated
    public OutputBuilder<ParentBuilder> $(String... names){
        if(CheckUtil.isNullOrEmpty(names)){
            return this;
        }
        List<Output.DmlSelect> list = Arrays.stream(names)
                .map((Output.ColumnName::new))
                .map(Output.DmlSelect::new)
                .collect(Collectors.toList());
        initAdd(list,
                this.target::getDmlSelectList,
                this.target::setDmlSelectList);
        return this;
    }

    /**
     * Quick set DmlSelect
     * @param columnNames
     * @return
     */
    public OutputBuilder<ParentBuilder> $(Output.ColumnName... columnNames){
        if(CheckUtil.isNullOrEmpty(columnNames)){
            return this;
        }
        List<Output.DmlSelect> list = Arrays.stream(columnNames)
                .map(Output.DmlSelect::new)
                .collect(Collectors.toList());
        initAdd(list,
                this.target::getDmlSelectList,
                this.target::setDmlSelectList);
        return this;
    }

    @SuppressWarnings("Duplicates")
    public OutputBuilder<ParentBuilder> $(ColumnName... columnNames) {
        if(CheckUtil.isNullOrEmpty(columnNames)){
            return this;
        }
        List<Output.DmlSelect> list = Arrays.stream(columnNames)
                .map(columnName -> {
                    Output.ColumnName c = new Output.ColumnName();
                    c.setColumnName(columnName.getName());
                    if(columnName.getTable() != null){
                        c.setFromTableName(columnName.getTable().getFullName());
                    }
                    return c;
                })
                .map(Output.DmlSelect::new)
                .collect(Collectors.toList());
        initAdd(list,
                this.target::getDmlSelectList,
                this.target::setDmlSelectList);
        return this;
    }


    /**
     * Quick set DmlSelect
     * TODO maybe use Expression replace ColumnName and GroupExpression
     * @param expressions
     * @return
     */
    public OutputBuilder<ParentBuilder> $(ScalarExpression... expressions){
        if(CheckUtil.isNullOrEmpty(expressions)){
            return this;
        }
        List<Output.DmlSelect> list = Arrays.stream(expressions)
                .map(Output.DmlSelect::new)
                .collect(Collectors.toList());
        initAdd(list,
                this.target::getDmlSelectList,
                this.target::setDmlSelectList);
        return this;
    }

    /**
     * Quick set DmlSelect
     * @param names
     * @return
     */
    public OutputBuilder<ParentBuilder> $Inserted(String... names){
        if(CheckUtil.isNullOrEmpty(names)){
            return this;
        }
        List<Output.DmlSelect> list = Arrays.stream(names)
                .map((name -> {
                    Output.ColumnName columnName = new Output.ColumnName(name);
                    columnName.setUseInserted(true);
                    return columnName;
                }))
                .map(Output.DmlSelect::new)
                .collect(Collectors.toList());
        initAdd(list,
                this.target::getDmlSelectList,
                this.target::setDmlSelectList);
        return this;
    }

    /**
     * Quick set DmlSelect
     * @param names
     * @return
     */
    public OutputBuilder<ParentBuilder> $Deleted(String... names){
        if(CheckUtil.isNullOrEmpty(names)){
            return this;
        }
        List<Output.DmlSelect> list = Arrays.stream(names)
                .map((name -> {
                    Output.ColumnName columnName = new Output.ColumnName(name);
                    columnName.setUseDeleted(true);
                    return columnName;
                }))
                .map(Output.DmlSelect::new)
                .collect(Collectors.toList());
        initAdd(list,
                this.target::getDmlSelectList,
                this.target::setDmlSelectList);
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

    public DmlSelectBuilder<OutputBuilder<ParentBuilder>> $Output(){
        return new DmlSelectBuilder<OutputBuilder<ParentBuilder>>
                (initNew(Output.DmlSelect::new,
                        target::getOutputDmlSelectList,
                        target::setOutputDmlSelectList))
                .in(this);
    }

    /**
     * Quick set OutputDmlSelect
     * @param names
     * @return
     */
    public OutputBuilder<ParentBuilder> $Output(String... names){
        if(CheckUtil.isNullOrEmpty(names)){
            return this;
        }
        List<Output.DmlSelect> list = Arrays.stream(names)
                .map((Output.ColumnName::new))
                .map(Output.DmlSelect::new)
                .collect(Collectors.toList());
        initAdd(list,
                this.target::getOutputDmlSelectList,
                this.target::setOutputDmlSelectList);
        return this;
    }

    /**
     * Quick set OutputDmlSelect
     * @param columnNames
     * @return
     */
    public OutputBuilder<ParentBuilder> $Output(Output.ColumnName... columnNames){
        if(CheckUtil.isNullOrEmpty(columnNames)){
            return this;
        }
        List<Output.DmlSelect> list = Arrays.stream(columnNames)
                .map(Output.DmlSelect::new)
                .collect(Collectors.toList());
        initAdd(list,
                this.target::getOutputDmlSelectList,
                this.target::setOutputDmlSelectList);
        return this;
    }

    @SuppressWarnings("Duplicates")
    public OutputBuilder<ParentBuilder> $Output(ColumnName... columnNames) {
        if(CheckUtil.isNullOrEmpty(columnNames)){
            return this;
        }
        List<Output.DmlSelect> list = Arrays.stream(columnNames)
                .map(columnName -> {
                    Output.ColumnName c = new Output.ColumnName();
                    c.setColumnName(columnName.getName());
                    if(columnName.getTable() != null){
                        c.setFromTableName(columnName.getTable().getFullName());
                    }
                    return c;
                })
                .map(Output.DmlSelect::new)
                .collect(Collectors.toList());
        initAdd(list,
                this.target::getOutputDmlSelectList,
                this.target::setOutputDmlSelectList);
        return this;
    }

    /**
     * Quick set OutputDmlSelect
     * @param expressions
     * @return
     */
    public OutputBuilder<ParentBuilder> $Output(ScalarExpression... expressions){
        if(CheckUtil.isNullOrEmpty(expressions)){
            return this;
        }
        List<Output.DmlSelect> list = Arrays.stream(expressions)
                .map(Output.DmlSelect::new)
                .collect(Collectors.toList());
        initAdd(list,
                this.target::getOutputDmlSelectList,
                this.target::setOutputDmlSelectList);
        return this;
    }

    /**
     * Quick set OutputDmlSelect
     * @param names
     * @return
     */
    public OutputBuilder<ParentBuilder> $Output_Inserted(String... names){
        if(CheckUtil.isNullOrEmpty(names)){
            return this;
        }
        List<Output.DmlSelect> list = Arrays.stream(names)
                .map((name -> {
                    Output.ColumnName columnName = new Output.ColumnName(name);
                    columnName.setUseInserted(true);
                    return columnName;
                }))
                .map(Output.DmlSelect::new)
                .collect(Collectors.toList());
        initAdd(list,
                this.target::getOutputDmlSelectList,
                this.target::setOutputDmlSelectList);
        return this;
    }

    /**
     * Quick set OutputDmlSelect
     * @param names
     * @return
     */
    public OutputBuilder<ParentBuilder> $Output_Deleted(String... names){
        if(CheckUtil.isNullOrEmpty(names)){
            return this;
        }
        List<Output.DmlSelect> list = Arrays.stream(names)
                .map((name -> {
                    Output.ColumnName columnName = new Output.ColumnName(name);
                    columnName.setUseDeleted(true);
                    return columnName;
                }))
                .map(Output.DmlSelect::new)
                .collect(Collectors.toList());
        initAdd(list,
                this.target::getOutputDmlSelectList,
                this.target::setOutputDmlSelectList);
        return this;
    }



    public static class DmlSelectListBuilder<ParentBuilder>
            extends CodeTreeBuilder<DmlSelectListBuilder<ParentBuilder>,ParentBuilder,List<Output.DmlSelect>> {

        public DmlSelectListBuilder() {
            super(new ArrayList<>());
        }

        public DmlSelectListBuilder(List<Output.DmlSelect> dmlSelects) {
            super(dmlSelects);
        }

        public DmlSelectBuilder<DmlSelectListBuilder<ParentBuilder>> withItem(){
            return new DmlSelectBuilder<DmlSelectListBuilder<ParentBuilder>>
                    ()
                    .enter(this, dmlSelect -> this.target.add(dmlSelect));
        }

    }

    /**
     * DmlSelectBuilder
     * @param <ParentBuilder>
     */
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
                            this.target::setColumnName))
                    .in(this);
        }

        public DmlSelectBuilder<ParentBuilder> withColumnName(Output.ColumnName columnName){
            target.setColumnName(columnName);
            return this;
        }

        public DmlSelectBuilder<ParentBuilder> withScalarExpression(ScalarExpression scalarExpression){
            this.target.setScalarExpression(scalarExpression);
            return this;
        }

        public DmlSelectBuilder<ParentBuilder> withAs(){
            this.target.setUseAs(true);
            return this;
        }

        public DmlSelectBuilder<ParentBuilder> withColumnAliasIdentifier(String columnAliasIdentifier){
            this.target.setColumnAliasIdentifier(columnAliasIdentifier);
            return this;
        }


        public DmlSelectBuilder<ParentBuilder> $Inserted(String columnName) {
            return withColumnName()
                    .withInserted()
                    .withColumnName(columnName)
                    .and();
        }

        public DmlSelectBuilder<ParentBuilder> $Deleted(String columnName) {
            return withColumnName()
                    .withDeleted()
                    .withColumnName(columnName)
                    .and();
        }


        public ParentBuilder $As(String columnAliasIdentifier){
            return withColumnAliasIdentifier(columnAliasIdentifier)
                    .and();
        }
    }

    /**
     * ColumnNameFactory
     * @param <ParentBuilder>
     */
    public static class ColumnNameBuilder<ParentBuilder>
            extends CodeTreeBuilder<ColumnNameBuilder<ParentBuilder>,ParentBuilder,Output.ColumnName> {

        public ColumnNameBuilder(Output.ColumnName tar) {
            super(tar);
        }

        public ColumnNameBuilder<ParentBuilder> withDeleted(){
            this.target.setUseDeleted(true);
            return this;
        }

        public ColumnNameBuilder<ParentBuilder> withInserted(){
            this.target.setUseInserted(true);
            return this;
        }

        public ColumnNameBuilder<ParentBuilder> withFromTableName(String fromTableName){
            this.target.setFromTableName(fromTableName);
            return this;
        }

        public ColumnNameBuilder<ParentBuilder> withAll(){
            this.target.setUseAll(true);
            return this;
        }

        public ColumnNameBuilder<ParentBuilder> withColumnName(String columnName){
            this.target.setColumnName(columnName);
            return this;
        }

        public ColumnNameBuilder<ParentBuilder> with$action(){
            this.target.set$action(true);
            return this;
        }
    }




    /*
    Quick into
     */

    /**
     * Quick into ColumnNameFactory
     * @return
     */
    public static ColumnNameBuilder<Output.ColumnName> column_name(){
        return new ColumnNameBuilder<>
                (new Output.ColumnName());
    }




    /*
    Quick build
     */

    /**
     * Quick build ColumnName
     * with inserted flag and name
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
     * Quick build ColumnName
     * with inserted flag
     * @return
     */
    public static Output.ColumnName c_inserted(){
        return new ColumnNameBuilder<Output.ColumnName>
                (new Output.ColumnName())
                .withInserted()
                .withAll()
                .build();
    }

    /**
     * Quick build ColumnName
     * with inserted flag and name
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
     * Quick build ColumnName
     * with inserted flag
     * @return
     */
    public static Output.ColumnName c_deleted(){
        return new ColumnNameBuilder<Output.ColumnName>
                (new Output.ColumnName())
                .withDeleted()
                .withAll()
                .build();
    }

    /**
     * Quick build ColumnName
     * inserted flag $action
     * @return
     */
    public static Output.ColumnName c_$action(){
        Output.ColumnName c = new Output.ColumnName();
        c.set$action(true);
        return new ColumnNameBuilder<Output.ColumnName>
                (c)
                .build();
    }

}
