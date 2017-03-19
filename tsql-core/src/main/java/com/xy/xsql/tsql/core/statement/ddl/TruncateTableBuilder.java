package com.xy.xsql.tsql.core.statement.ddl;

import com.xy.xsql.core.builder.BaseBuilder;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.statement.ddl.TruncateTable;

import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.tsql.core.element.TableNameBuilder.t;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 */
public class TruncateTableBuilder implements BaseBuilder<Void,TruncateTable> {


    private TruncateTable truncateTable = new TruncateTable();

    public TruncateTableBuilder withTableName(TableName tableName){
        truncateTable.setTableName(tableName);
        return this;
    }

    public TruncateTableBuilder withNumber(Integer number){
        initAdd(
                e_pn(number),
                truncateTable::getPartitionsList,
                truncateTable::setPartitionsList
        );
        return this;
    }

    public TruncateTableBuilder withRange(Integer range,Integer range2){
        initAdd(
                e_range(range,range2),
                truncateTable::getPartitionsList,
                truncateTable::setPartitionsList
        );
        return this;
    }


    public TruncateTable build() {
        return truncateTable;
    }

    @Override
    public TruncateTable build(Void aVoid) {
        return build();
    }



    public static TruncateTable TRUNCATE_TABLE(TableName tableName){
        return new TruncateTableBuilder().withTableName(tableName).build();
    }


    public static TruncateTable.PartitionNumberExpression e_pn(Number number){
        return new TruncateTable.PartitionNumberExpression(number);
    }

    public static TruncateTable.Range e_range(Number number,Number number2){
        return new TruncateTable.Range(e_pn(number),e_pn(number2));
    }
}
