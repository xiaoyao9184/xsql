package com.xy.xsql.tsql.core.statement;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.tsql.core.clause.OutputBuilder;
import com.xy.xsql.tsql.core.clause.TableValueConstructorBuilder;
import com.xy.xsql.tsql.core.clause.TopBuilder;
import com.xy.xsql.tsql.core.clause.WithBuilder;
import com.xy.xsql.tsql.model.clause.Output;
import com.xy.xsql.tsql.model.clause.TableValueConstructor;
import com.xy.xsql.tsql.model.clause.Top;
import com.xy.xsql.tsql.model.clause.With;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.statement.dml.Insert;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.ListBuilder.initAdd;

/**
 * Created by xiaoyao9184 on 2017/1/9.
 */
public class InsertBuilder extends CodeBuilder<Insert> {

    public InsertBuilder(Insert tar) {
        super(tar);
    }

    public InsertBuilder(){
        super(new Insert());
    }

    /**
     *
     * @return
     */
    public WithBuilder<InsertBuilder> withWith(){
        return new WithBuilder<InsertBuilder>
                (initSet(With::new,
                        tar::getWith,
                        tar::setWith))
                .in(this);
    }

    /**
     *
     * @return
     */
    public TopBuilder<InsertBuilder> withTop(){
        return new TopBuilder<InsertBuilder>
                (initSet(Top::new,
                        tar::getTop,
                        tar::setTop))
                .in(this);
    }

    /**
     *
     * @param useInto
     * @return
     */
    public InsertBuilder withInto(boolean useInto){
        tar.setUseInto(useInto);
        return this;
    }

    /**
     *
     * @param tableName
     * @return
     */
    public InsertBuilder withTableName(TableName tableName){
        tar.setTableName(tableName);
        return this;
    }

    /**
     *
     * @param tableName
     * @return
     */
    public InsertBuilder withTableName(String tableName){
        tar.setTableName(new TableName(tableName));
        return this;
    }

    /**
     *
     * @return
     */
    public InsertBuilder withColumn(ColumnName columnName){
        initAdd(columnName,
                tar::getColumns,
                tar::setColumns);
        return this;
    }

    /**
     *
     * @return
     */
    public OutputBuilder<InsertBuilder> withOutput() {
        return new OutputBuilder<InsertBuilder>
                (initSet(Output::new,
                        tar::getOutput,
                        tar::setOutput))
                .in(this);
    }

    /**
     *
     * @return
     */
    public TableValueConstructorBuilder<InsertBuilder> withValues(){
        return new TableValueConstructorBuilder<InsertBuilder>
                (initSet(TableValueConstructor::new,
                        tar::getValues,
                        tar::setValues))
                .in(this);
    }


//    /**
//     *
//     * @param <Done>
//     */
//    public static class ColumnListBuilder<Done>
//            extends SubBuilder<ColumnListBuilder<Done>,Void,Done> {
//
//        private List<Column> columnList;
//
//        public ColumnListBuilder(List<Column> columnList) {
//            this.columnList = columnList;
//        }
//
//        public ColumnBuilder<ColumnListBuilder<Done>> withItem(){
//            Column column = new Column();
//            if(this.columnList == null){
//                this.columnList = new ArrayList<>();
//            }
//            this.columnList.add(column);
//            return new ColumnBuilder<ColumnListBuilder<Done>>(column)
//                    .in(this);
//        }
//    }
//
//    /**
//     *
//     * @param <Done>
//     */
//    public static class ColumnBuilder<Done>
//            extends SubBuilder<ColumnBuilder<Done>,Void,Done> {
//
//        private Column column;
//
//        public ColumnBuilder(Column column) {
//            this.column = column;
//        }
//
//        public ColumnBuilder<Done> withName(String name){
//            this.column.setName(name);
//            return this;
//        }
//    }


//    /**
//     *
//     * @param <Done>
//     */
//    public class ValueGroupListBuilder<Done>
//            extends SubBuilder<ValueGroupListBuilder<Done>,Void,Done> {
//
//        private GroupList<Insert.Value> valueGroupList;
//
//        public ValueGroupListBuilder(GroupList<Insert.Value> valueGroupList) {
//            this.valueGroupList = valueGroupList;
//        }
//
//        public ValueListBuilder<ValueGroupListBuilder<Done>> withGroupItem(){
//            List<Insert.Value> valueList = new ArrayList<>();
//            if(this.valueGroupList == null){
//                this.valueGroupList = new GroupList<>();
//            }
//            this.valueGroupList.add(valueList);
//            return new ValueListBuilder<ValueGroupListBuilder<Done>>(valueList)
//                    .in(this);
//        }
//    }
//
//    /**
//     *
//     * @param <Done>
//     */
//    public static class ValueListBuilder<Done>
//            extends SubBuilder<ValueListBuilder<Done>,Void,Done> {
//
//        private List<Insert.Value> valueList;
//
//        public ValueListBuilder(List<Insert.Value> valueList) {
//            this.valueList = valueList;
//        }
//
//        public ValueBuilder<ValueListBuilder<Done>> withItem(){
//            Insert.Value value = new Insert.Value();
//            if(this.valueList == null){
//                this.valueList = new ArrayList<>();
//            }
//            this.valueList.add(value);
//            return new ValueBuilder<ValueListBuilder<Done>>(value)
//                    .in(this);
//        }
//    }
//
//    /**
//     *
//     * @param <Done>
//     */
//    public static class ValueBuilder<Done>
//            extends SubBuilder<ValueBuilder<Done>,Void,Done> {
//
//        private Insert.Value value;
//
//        public ValueBuilder(Insert.Value value) {
//            this.value = value;
//        }
//
//        public ValueBuilder<Done> withExpression(Expression expression){
//            this.value.setExpression(expression);
//            return this;
//        }
//
//        public ValueBuilder<Done> withNull(Boolean useNull){
//            this.value.setUseNull(useNull);
//            return this;
//        }
//    }
}
