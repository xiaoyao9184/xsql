package com.xy.xsql.tsql.core.statement.ddl;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.statement.ddl.TruncateTable;

import static com.xy.xsql.core.ListBuilder.initAdd;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 */
public class TruncateTableBuilder extends CodeBuilder<TruncateTable> {

    public TruncateTableBuilder(TruncateTable tar) {
        super(tar);
    }

    public TruncateTableBuilder() {
        super(new TruncateTable());
    }

    public TruncateTableBuilder withTableName(TableName tableName){
        target.setTableName(tableName);
        return this;
    }

    public TruncateTableBuilder withNumber(Integer number){
        initAdd(
                e_pn(number),
                target::getPartitionsList,
                target::setPartitionsList
        );
        return this;
    }

    public TruncateTableBuilder withRange(Integer range,Integer range2){
        initAdd(
                e_range(range,range2),
                target::getPartitionsList,
                target::setPartitionsList
        );
        return this;
    }




    /*
    Quick build
     */

    /**
     * Quick build TruncateTable
     * @param tableName
     * @return
     */
    public static TruncateTable TRUNCATE_TABLE(TableName tableName){
        return new TruncateTableBuilder().withTableName(tableName).build();
    }

    /**
     * Quick build PartitionNumberExpression
     * @param number
     * @return
     */
    public static TruncateTable.PartitionNumberExpression e_pn(Number number){
        return new TruncateTable.PartitionNumberExpression(number);
    }

    /**
     * Quick build Range
     * @param number
     * @param number2
     * @return
     */
    public static TruncateTable.Range e_range(Number number,Number number2){
        return new TruncateTable.Range(e_pn(number),e_pn(number2));
    }
}
