package com.xy.xsql.tsql.builder.chain.queries.hints;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.Constants;
import com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions;
import com.xy.xsql.tsql.model.queries.hints.TableHint;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e;

/**
 * TableHintBuilder
 * Created by xiaoyao9184 on 2016/12/28.
 */
@SuppressWarnings({"WeakerAccess","unused"})
public class TableHintBuilder<ParentBuilder>
        extends CodeTreeBuilder<TableHintBuilder<ParentBuilder>,ParentBuilder,TableHint> {

    public TableHintBuilder() {
        super(new TableHint());
    }

    public TableHintBuilder(TableHint tableHint) {
        super(tableHint);
    }


    /**
     * set
     * @return THIS
     */
    public TableHintBuilder<ParentBuilder> withNOEXPAND(){
        target.setUseNOEXPAND(true);
        return this;
    }

    /**
     * set
     * @param type Type
     * @return THIS
     */
    public TableHintBuilder<ParentBuilder> withType(TableHint.Type type){
        target.setType(type);
        return this;
    }

    /**
     * set
     * @param indexValues index value
     * @return THIS
     */
    public TableHintBuilder<ParentBuilder> withIndexValue(String... indexValues){
        target.setIndex_value(
                Arrays.stream(indexValues)
                        .map(Constants::c_string)
                        .collect(Collectors.toList()));
        return this;
    }

    /**
     * set
     * @param indexColumnNames index column name
     * @return THIS
     */
    public TableHintBuilder<ParentBuilder> withPercent(String... indexColumnNames){
        target.setIndex_column_name(
                Arrays.stream(indexColumnNames)
                        .map(Constants::c_string)
                        .collect(Collectors.toList()));
        return this;
    }

    /**
     * set
     * @param integer integer
     * @return THIS
     */
    public TableHintBuilder<ParentBuilder> withInteger(Integer integer){
        target.setInteger(integer);
        return this;
    }




    /*
    Quick build
    Quick in
     */

    /**
     * Quick in
     * @return TableHintIndexBuilder
     */
    public static TableHintIndexBuilder<Void> $Index(){
        return new TableHintIndexBuilder<>();
    }

    /**
     * Quick in
     * @return TableHintIndexBuilder
     */
    public static TableHintBuilder $Noexpand(){
        return new TableHintBuilder<Void>()
                .withNOEXPAND();
    }

    /**
     * Quick build
     * By default an element will NOT enable ONE index_value mode(increase equal(=) symbol),
     * If you want to enable ONE index_value mode(add equal symbol(=)), you can pass a 'null' parameter at position 2
     * like this {@code INDEX('IX_Employee_ManagerID',null)}
     * Or use {@link #$Index} like this {@code INDEX().$EQUAL('IX_Employee_ManagerID')}
     * @return TableHint
     */
    public static TableHint $Index(String... indexValues){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.INDEX);
        tableHint.setIndex_value(
                Arrays.stream(indexValues)
                        .filter(Objects::nonNull)
                        .map(Expressions::e)
                        .collect(Collectors.toList()));
        if(tableHint.getIndex_value().size() == 1){
            if(indexValues.length != 1){
                tableHint.setUseOneIndexValue(true);
            }
        }

        return tableHint;
    }

    /**
     * Quick build
     * @return TableHint
     */
    public static TableHint $Forceseek(String index_value, String... indexColumnNames){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.FORCESEEK);
        tableHint.setIndex_value(
                Collections.singletonList(e(index_value)));
        tableHint.setIndex_column_name(
                Arrays.stream(indexColumnNames)
                        .map(Expressions::e)
                        .collect(Collectors.toList()));
        return tableHint;
    }

    /**
     * Quick build
     * @return TableHint
     */
    public static TableHint $Forcescan(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.FORCESCAN);
        return tableHint;
    }

    /**
     * Quick build
     * @return TableHint
     */
    public static TableHint $Forceseek(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.FORCESEEK);
        return tableHint;
    }

    /**
     * Quick build
     * @return TableHint
     */
    public static TableHint $Holdlock(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.HOLDLOCK);
        return tableHint;
    }

    /**
     * Quick build
     * @return TableHint
     */
    public static TableHint $Nolock(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.NOLOCK);
        return tableHint;
    }

    /**
     * Quick build
     * @return TableHint
     */
    public static TableHint $Nowait(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.NOWAIT);
        return tableHint;
    }

    /**
     * Quick build
     * @return TableHint
     */
    public static TableHint $Paglock(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.PAGLOCK);
        return tableHint;
    }

    /**
     * Quick build
     * @return TableHint
     */
    public static TableHint $Readcommitted(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.READCOMMITTED);
        return tableHint;
    }

    /**
     * Quick build
     * @return TableHint
     */
    public static TableHint $Readcommittedlock(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.READCOMMITTEDLOCK);
        return tableHint;
    }

    /**
     * Quick build
     * @return TableHint
     */
    public static TableHint $Readpast(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.READPAST);
        return tableHint;
    }

    /**
     * Quick build
     * @return TableHint
     */
    public static TableHint $Readuncommitted(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.READUNCOMMITTED);
        return tableHint;
    }

    /**
     * Quick build
     * @return TableHint
     */
    public static TableHint $Repeatableread(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.REPEATABLEREAD);
        return tableHint;
    }

    /**
     * Quick build
     * @return TableHint
     */
    public static TableHint $Rowlock(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.ROWLOCK);
        return tableHint;
    }

    /**
     * Quick build
     * @return TableHint
     */
    public static TableHint $Serializable(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.SERIALIZABLE);
        return tableHint;
    }

    /**
     * Quick build
     * @return TableHint
     */
    public static TableHint $Snapshot(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.SNAPSHOT);
        return tableHint;
    }

    /**
     * Quick build
     * @return TableHint
     */
    public static TableHint $SpatialWindowMaxCells(Integer integer){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.SPATIAL_WINDOW_MAX_CELLS);
        tableHint.setInteger(integer);
        return tableHint;
    }

    /**
     * Quick build
     * @return TableHint
     */
    public static TableHint $Tablock(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.TABLOCK);
        return tableHint;
    }

    /**
     * Quick build
     * @return TableHint
     */
    public static TableHint $Tablockx(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.TABLOCKX);
        return tableHint;
    }

    /**
     * Quick build
     * @return TableHint
     */
    public static TableHint $Updlock(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.UPDLOCK);
        return tableHint;
    }

    /**
     * Quick build
     * @return TableHint
     */
    public static TableHint $Xlock(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.XLOCK);
        return tableHint;
    }


    /**
     * TableHintIndexBuilder
     * @param <ParentBuilder>
     */
    public static class TableHintIndexBuilder<ParentBuilder>
            extends CodeTreeBuilder<TableHintIndexBuilder<ParentBuilder>,ParentBuilder,TableHint> {

        public TableHintIndexBuilder() {
            super(new TableHint());
            target.setType(TableHint.Type.INDEX);
        }

        public TableHintIndexBuilder(TableHint tableHint) {
            super(tableHint);
            target.setType(TableHint.Type.INDEX);
        }

        /**
         * set
         * @param indexValues index value
         * @return THIS
         */
        public TableHintIndexBuilder<ParentBuilder> withIndex_value(String... indexValues){
            target.setIndex_value(
                    Arrays.stream(indexValues)
                            .filter(Objects::nonNull)
                            .map(Expressions::e)
                            .collect(Collectors.toList()));
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public TableHintIndexBuilder<ParentBuilder> withUseOneIndexValue(){
            target.setUseOneIndexValue(true);
            return this;
        }

        /**
         * set
         * @param indexValue index value
         * @return THIS
         */
        public TableHintIndexBuilder<ParentBuilder> $Equal(String indexValue){
            return withUseOneIndexValue()
                    .withIndex_value(indexValue);
        }




        /*
        Quick
         */

        /**
         * Quick build
         * @param indexValues index value
         * @return TableHint
         */
        public TableHint $(String... indexValues){
            return withIndex_value(indexValues)
                    .build();
        }

    }

}
