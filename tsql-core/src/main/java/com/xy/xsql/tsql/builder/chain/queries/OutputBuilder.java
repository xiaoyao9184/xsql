package com.xy.xsql.tsql.builder.chain.queries;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.queries.Output;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
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
@SuppressWarnings({"WeakerAccess", "UnusedReturnValue", "unused"})
public class OutputBuilder<ParentBuilder>
        extends CodeTreeBuilder<OutputBuilder<ParentBuilder>,ParentBuilder,Output> {

    public OutputBuilder() {
        super(new Output());
    }

    public OutputBuilder(Output output) {
        super(output);
    }


    /**
     * set
     * @param dmlSelectList DmlSelect
     * @return THIS
     */
    public OutputBuilder<ParentBuilder> withDmlSelect(List<Output.DmlSelect> dmlSelectList){
        this.target.setDmlSelectList(dmlSelectList);
        return this;
    }

    /**
     * in
     * @return DmlSelectBuilder
     */
    public DmlSelectBuilder<OutputBuilder<ParentBuilder>> withDmlSelect(){
        return new DmlSelectBuilder<OutputBuilder<ParentBuilder>>
                (initNew(Output.DmlSelect::new,
                        this.target::getDmlSelectList,
                        this.target::setDmlSelectList))
                .in(this);
    }

    /**
     * set
     * @param tableVariable table variable
     * @return THIS
     */
    public OutputBuilder<ParentBuilder> withTableVariable(String tableVariable){
        this.target.setTableVariable(new LocalVariable(tableVariable));
        return this;
    }

    /**
     * set
     * @param outputTable TableName
     * @return THIS
     */
    public OutputBuilder<ParentBuilder> withTableName(TableName outputTable){
        this.target.setOutputTable(outputTable);
        return this;
    }

    /**
     * set
     * @param columnNames ColumnName
     * @return THIS
     */
    public OutputBuilder<ParentBuilder> withColumnName(ColumnName... columnNames){
        if(CheckUtil.isNullOrEmpty(columnNames)){
            return this;
        }
        initAdd(Arrays.asList(columnNames),
                target::getColumnList,
                target::setColumnList);
        return this;
    }

    /**
     * set
     * @param columnNameList ColumnName
     * @return THIS
     */
    public OutputBuilder<ParentBuilder> withColumnName(List<ColumnName> columnNameList){
        if(CheckUtil.isNullOrEmpty(columnNameList)){
            return this;
        }
        initAdd(columnNameList,
                target::getColumnList,
                target::setColumnList);
        return this;
    }

    /**
     * set
     * @param dmlSelectList DmlSelect
     * @return THIS
     */
    public OutputBuilder<ParentBuilder> withOutputDmlSelect(List<Output.DmlSelect> dmlSelectList){
        this.target.setOutputDmlSelectList(dmlSelectList);
        return this;
    }

    /**
     * in
     * @return DmlSelectBuilder
     */
    public DmlSelectBuilder<OutputBuilder<ParentBuilder>> withOutputDmlSelect(){
        return new DmlSelectBuilder<OutputBuilder<ParentBuilder>>
                (initNew(Output.DmlSelect::new,
                        this.target::getOutputDmlSelectList,
                        this.target::setOutputDmlSelectList))
                .in(this);
    }




    /*
    Quick set
     */

    /**
     * Quick set DmlSelect
     * @param names name
     * @return THIS
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
     * @param columnNames ColumnName
     * @return THIS
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

    /**
     * set
     * @param columnNames ColumnName
     * @return THIS
     */
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
     * @param expressions ScalarExpression
     * @return THIS
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
     * @param names name
     * @return THIS
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
     * @param names name
     * @return THIS
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
     * @param tableName TableName
     * @param columnNames ColumnName
     * @return THIS
     */
    public OutputBuilder<ParentBuilder> $Into(TableName tableName, ColumnName... columnNames){
        return withTableName(tableName)
                .withColumnName(columnNames);
    }

    /**
     * Quick set tableVariable
     * @param tableVariable table variable
     * @return THIS
     */
    public OutputBuilder<ParentBuilder> $Into(String tableVariable, ColumnName... columnNames){
        return withTableVariable(tableVariable)
                .withColumnName(columnNames);
    }

    /**
     * int
     * @return DmlSelectBuilder
     */
    public DmlSelectBuilder<OutputBuilder<ParentBuilder>> $Output(){
        return new DmlSelectBuilder<OutputBuilder<ParentBuilder>>
                (initNew(Output.DmlSelect::new,
                        target::getOutputDmlSelectList,
                        target::setOutputDmlSelectList))
                .in(this);
    }

    /**
     * Quick set OutputDmlSelect
     * @param names name
     * @return THIS
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
     * @param columnNames ColumnName
     * @return THIS
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

    /**
     * set
     * @param columnNames ColumnName
     * @return THIS
     */
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
     * @param expressions ScalarExpression
     * @return THIS
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
     * @param names name
     * @return THIS
     */
    public OutputBuilder<ParentBuilder> $OutputInserted(String... names){
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
     * @param names name
     * @return THIS
     */
    public OutputBuilder<ParentBuilder> $OutputDeleted(String... names){
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


    /**
     * DmlSelectListBuilder
     * @param <ParentBuilder>
     */
    public static class DmlSelectListBuilder<ParentBuilder>
            extends CodeTreeBuilder<DmlSelectListBuilder<ParentBuilder>,ParentBuilder,List<Output.DmlSelect>> {

        public DmlSelectListBuilder() {
            super(new ArrayList<>());
        }

        public DmlSelectListBuilder(List<Output.DmlSelect> dmlSelects) {
            super(dmlSelects);
        }

        /**
         * in
         * @return DmlSelectBuilder
         */
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

        /**
         * set
         * @param columnName ColumnName
         * @return THIS
         */
        public DmlSelectBuilder<ParentBuilder> withColumnName(Output.ColumnName columnName){
            target.setColumnName(columnName);
            return this;
        }

        /**
         * in
         * @return ColumnNameBuilder
         */
        public ColumnNameBuilder<DmlSelectBuilder<ParentBuilder>> withColumnName(){
            return new ColumnNameBuilder<DmlSelectBuilder<ParentBuilder>>
                    (set(Output.ColumnName::new,
                            this.target::setColumnName))
                    .in(this);
        }

        /**
         * set
         * @param scalarExpression ScalarExpression
         * @return THIS
         */
        public DmlSelectBuilder<ParentBuilder> withScalarExpression(ScalarExpression scalarExpression){
            this.target.setScalarExpression(scalarExpression);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public DmlSelectBuilder<ParentBuilder> withAs(){
            this.target.setUseAs(true);
            return this;
        }

        /**
         * set
         * @param columnAliasIdentifier column alias identifier
         * @return THIS
         */
        public DmlSelectBuilder<ParentBuilder> withColumnAliasIdentifier(String columnAliasIdentifier){
            this.target.setColumnAliasIdentifier(columnAliasIdentifier);
            return this;
        }



        /*
        Quick
         */

        /**
         * Quick set
         * @param columnName column name
         * @return THIS
         */
        public DmlSelectBuilder<ParentBuilder> $Inserted(String columnName) {
            return withColumnName()
                    .withInserted()
                    .withColumnName(columnName)
                    .and();
        }

        /**
         * Quick set
         * @param columnName column name
         * @return THIS
         */
        public DmlSelectBuilder<ParentBuilder> $Deleted(String columnName) {
            return withColumnName()
                    .withDeleted()
                    .withColumnName(columnName)
                    .and();
        }

        /**
         * Quick back
         * @param columnAliasIdentifier column alias identifier
         * @return PARENT
         */
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

        /**
         * set
         * @return THIS
         */
        public ColumnNameBuilder<ParentBuilder> withDeleted(){
            this.target.setUseDeleted(true);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public ColumnNameBuilder<ParentBuilder> withInserted(){
            this.target.setUseInserted(true);
            return this;
        }

        /**
         * set
         * @param fromTableName from table name
         * @return THIS
         */
        public ColumnNameBuilder<ParentBuilder> withFromTableName(String fromTableName){
            this.target.setFromTableName(fromTableName);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public ColumnNameBuilder<ParentBuilder> withAll(){
            this.target.setUseAll(true);
            return this;
        }

        /**
         * set
         * @param columnName column name
         * @return THIS
         */
        public ColumnNameBuilder<ParentBuilder> withColumnName(String columnName){
            this.target.setColumnName(columnName);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public ColumnNameBuilder<ParentBuilder> with$action(){
            this.target.set$action(true);
            return this;
        }
    }




    /*
    Quick into
     */

    /**
     * Quick in ColumnNameFactory
     * @return ColumnNameBuilder
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
     * @param name name
     * @return ColumnName
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
     * @return ColumnName
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
     * @param name name
     * @return ColumnName
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
     * @return ColumnName
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
     * @return ColumnName
     */
    public static Output.ColumnName c_$action(){
        Output.ColumnName c = new Output.ColumnName();
        c.set$action(true);
        return new ColumnNameBuilder<Output.ColumnName>
                (c)
                .build();
    }

}
