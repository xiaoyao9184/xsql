package com.xy.xsql.tsql.builder.chain.queries.hints;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.queries.hints.TableHint;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * TableHintBuilder
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class TableHintBuilder<ParentBuilder>
        extends CodeTreeBuilder<TableHintBuilder<ParentBuilder>,ParentBuilder,TableHint> {

    public TableHintBuilder() {
        super(new TableHint());
    }

    public TableHintBuilder(TableHint tableHint) {
        super(tableHint);
    }


    public TableHintBuilder<ParentBuilder> withNOEXPAND(){
        target.setUseNOEXPAND(true);
        return this;
    }

    public TableHintBuilder<ParentBuilder> withType(TableHint.Type type){
        target.setType(type);
        return this;
    }

    public TableHintBuilder<ParentBuilder> withIndexValue(String... indexValues){
        target.setIndex_value(
                Arrays.stream(indexValues)
                        .map(StringConstant::new)
                        .collect(Collectors.toList()));
        return this;
    }

    public TableHintBuilder<ParentBuilder> withPercent(String... indexColumnNames){
        target.setIndex_column_name(
                Arrays.stream(indexColumnNames)
                        .map(StringConstant::new)
                        .collect(Collectors.toList()));
        return this;
    }

    public TableHintBuilder<ParentBuilder> withInteger(Integer integer){
        target.setInteger(integer);
        return this;
    }



    /**
     * Quick in
     * @return
     */
    public static TableHintIndexBuilder<Void> INDEX(){
        return new TableHintIndexBuilder<>();
    }


    /*
    Quick build
     */

    /**
     * Quick build
     * @return
     */
    public static TableHintBuilder NOEXPAND(){
        return new TableHintBuilder<Void>()
                .withNOEXPAND();
    }

    /**
     * Quick build
     * By default an element will NOT enable ONE index_value mode(increase equal(=) symbol),
     * If you want to enable ONE index_value mode(add equal symbol(=)), you can pass a 'null' parameter at position 2
     * like this {@code INDEX('IX_Employee_ManagerID',null)}
     * Or use {@link #INDEX} like this {@code INDEX().$EQUAL('IX_Employee_ManagerID')}
     * @return
     */
    public static TableHint INDEX(String... indexValues){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.INDEX);
        tableHint.setIndex_value(
                Arrays.stream(indexValues)
                        .filter(Objects::nonNull)
                        .map(StringConstant::new)
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
     * @return
     */
    public static TableHint FORCESEEK(String index_value, String... indexColumnNames){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.FORCESEEK);
        tableHint.setIndex_value(
                Collections.singletonList(new StringConstant(index_value)));
        tableHint.setIndex_column_name(
                Arrays.stream(indexColumnNames)
                        .map(StringConstant::new)
                        .collect(Collectors.toList()));
        return tableHint;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHint FORCESCAN(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.FORCESCAN);
        return tableHint;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHint FORCESEEK(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.FORCESEEK);
        return tableHint;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHint HOLDLOCK(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.HOLDLOCK);
        return tableHint;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHint NOLOCK(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.NOLOCK);
        return tableHint;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHint NOWAIT(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.NOWAIT);
        return tableHint;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHint PAGLOCK(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.PAGLOCK);
        return tableHint;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHint READCOMMITTED(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.READCOMMITTED);
        return tableHint;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHint READCOMMITTEDLOCK(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.READCOMMITTEDLOCK);
        return tableHint;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHint READPAST(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.READPAST);
        return tableHint;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHint READUNCOMMITTED(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.READUNCOMMITTED);
        return tableHint;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHint REPEATABLEREAD(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.REPEATABLEREAD);
        return tableHint;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHint ROWLOCK(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.ROWLOCK);
        return tableHint;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHint SERIALIZABLE(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.SERIALIZABLE);
        return tableHint;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHint SNAPSHOT(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.SNAPSHOT);
        return tableHint;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHint SPATIAL_WINDOW_MAX_CELLS(Integer integer){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.SPATIAL_WINDOW_MAX_CELLS);
        tableHint.setInteger(integer);
        return tableHint;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHint TABLOCK(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.TABLOCK);
        return tableHint;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHint TABLOCKX(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.TABLOCKX);
        return tableHint;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHint UPDLOCK(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.UPDLOCK);
        return tableHint;
    }

    /**
     * Quick build
     * @return
     */
    public static TableHint XLOCK(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.XLOCK);
        return tableHint;
    }



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

        public TableHintIndexBuilder<ParentBuilder> withIndex_value(String... indexValues){
            target.setIndex_value(
                    Arrays.stream(indexValues)
                            .filter(Objects::nonNull)
                            .map(StringConstant::new)
                            .collect(Collectors.toList()));
            return this;
        }

        public TableHintIndexBuilder<ParentBuilder> withUseOneIndexValue(){
            target.setUseOneIndexValue(true);
            return this;
        }

        public TableHintIndexBuilder<ParentBuilder> $EQUAL(String indexValue){
            return withUseOneIndexValue()
                    .withIndex_value(indexValue);
        }

        public TableHint $(String... indexValues){
            return withIndex_value(indexValues)
                    .build();
        }

    }

}
