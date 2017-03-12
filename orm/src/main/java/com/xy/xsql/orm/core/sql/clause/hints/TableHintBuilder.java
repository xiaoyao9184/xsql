package com.xy.xsql.orm.core.sql.clause.hints;

import com.xy.xsql.orm.core.CodeTreeBuilder;
import com.xy.xsql.orm.data.sql.clause.hints.TableHint;
import com.xy.xsql.orm.data.sql.element.UnknownString;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
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
        tar.setUseNOEXPAND(true);
        return this;
    }

    public TableHintBuilder<ParentBuilder> withType(TableHint.Type type){
        tar.setType(type);
        return this;
    }

    public TableHintBuilder<ParentBuilder> withIndexValue(String... index_value){
        tar.setIndex_value(
                Arrays.stream(index_value)
                        .map(UnknownString::new)
                        .collect(Collectors.toList()));
        return this;
    }

    public TableHintBuilder<ParentBuilder> withPercent(String... index_column_name){
        tar.setIndex_column_name(
                Arrays.stream(index_column_name)
                        .map(UnknownString::new)
                        .collect(Collectors.toList()));
        return this;
    }

    public TableHintBuilder<ParentBuilder> withInteger(Integer integer){
        tar.setInteger(integer);
        return this;
    }

    

    public static TableHintBuilder NOEXPAND(){
        return new TableHintBuilder<Void>()
                .withNOEXPAND();
    }

    public static TableHint INDEX(String... index_value){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.INDEX);
        tableHint.setIndex_value(
                Arrays.stream(index_value)
                        .map(UnknownString::new)
                        .collect(Collectors.toList()));
        return tableHint;
    }

    public static TableHint FORCESEEK(String index_value, String... index_column_name){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.FORCESEEK);
        tableHint.setIndex_value(
                Collections.singletonList(new UnknownString(index_value)));
        tableHint.setIndex_column_name(
                Arrays.stream(index_column_name)
                        .map(UnknownString::new)
                        .collect(Collectors.toList()));
        return tableHint;
    }

    public static TableHint FORCESCAN(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.FORCESCAN);
        return tableHint;
    }

    public static TableHint FORCESEEK(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.FORCESEEK);
        return tableHint;
    }

    public static TableHint HOLDLOCK(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.HOLDLOCK);
        return tableHint;
    }

    public static TableHint NOLOCK(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.NOLOCK);
        return tableHint;
    }

    public static TableHint NOWAIT(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.NOWAIT);
        return tableHint;
    }

    public static TableHint PAGLOCK(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.PAGLOCK);
        return tableHint;
    }

    public static TableHint READCOMMITTED(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.READCOMMITTED);
        return tableHint;
    }

    public static TableHint READCOMMITTEDLOCK(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.READCOMMITTEDLOCK);
        return tableHint;
    }

    public static TableHint READPAST(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.READPAST);
        return tableHint;
    }

    public static TableHint READUNCOMMITTED(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.READUNCOMMITTED);
        return tableHint;
    }

    public static TableHint REPEATABLEREAD(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.REPEATABLEREAD);
        return tableHint;
    }

    public static TableHint ROWLOCK(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.ROWLOCK);
        return tableHint;
    }

    public static TableHint SERIALIZABLE(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.SERIALIZABLE);
        return tableHint;
    }

    public static TableHint SNAPSHOT(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.SNAPSHOT);
        return tableHint;
    }

    public static TableHint SPATIAL_WINDOW_MAX_CELLS(Integer integer){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.SPATIAL_WINDOW_MAX_CELLS);
        tableHint.setInteger(integer);
        return tableHint;
    }

    public static TableHint TABLOCK(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.TABLOCK);
        return tableHint;
    }

    public static TableHint TABLOCKX(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.TABLOCKX);
        return tableHint;
    }

    public static TableHint UPDLOCK(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.UPDLOCK);
        return tableHint;
    }

    public static TableHint XLOCK(){
        TableHint tableHint = new TableHint();
        tableHint.setType(TableHint.Type.XLOCK);
        return tableHint;
    }

}
