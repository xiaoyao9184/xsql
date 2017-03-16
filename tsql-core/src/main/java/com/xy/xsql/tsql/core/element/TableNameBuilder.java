package com.xy.xsql.tsql.core.element;

import com.xy.xsql.tsql.model.element.TableName;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static com.xy.xsql.core.ListBuilder.setter;

/**
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class TableNameBuilder {

    public static TableName t(String name){
        return new TableName(name);
    }

    @Deprecated
    public static TableName t(String schemaName,String name){
        TableName tableName = new TableName(name);
        tableName.setSchemaName(schemaName);
        return tableName;
    }

    public static TableName t(String... name){
        Iterator<String> reversedStream = Stream
                .of(name)
                .collect(Collectors.toCollection(LinkedList::new))
                .descendingIterator();

        List<String> listReversedOrder = StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(reversedStream,
                        Spliterator.ORDERED), false).collect(
                Collectors.toList());

        TableName tableName = new TableName();
        setter(listReversedOrder,
                tableName::setTableOrViewName,
                tableName::setSchemaName,
                tableName::setDatabaseName,
                tableName::setDatabaseName);

        return tableName;
    }
}
