package com.xy.xsql.tsql.core.clause.hints;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.clause.hints.TableHint;
import com.xy.xsql.tsql.model.datatype.StringConstant;

import java.util.Arrays;
import java.util.Collections;
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
     * @return
     */
    public static TableHint INDEX(String... indexValues){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.INDEX);
        tableHint.setIndex_value(
                Arrays.stream(indexValues)
                        .map(StringConstant::new)
                        .collect(Collectors.toList()));
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

}
