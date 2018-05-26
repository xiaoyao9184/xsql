package com.xy.xsql.tsql.builder.chain.statements;

import com.xy.xsql.core.builder.simple.CodeBuilder;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.statements.TruncateTable;

import static com.xy.xsql.core.handler.list.ListHandler.list;

/**
 * TruncateTableBuilder
 * Created by xiaoyao9184 on 2017/3/16.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class TruncateTableBuilder extends CodeBuilder<TruncateTable> {

    public TruncateTableBuilder(TruncateTable target) {
        super(target);
    }

    public TruncateTableBuilder() {
        super(new TruncateTable());
    }

    /**
     * set
     * @param tableName TableName
     * @return THIS
     */
    public TruncateTableBuilder withTableName(TableName tableName){
        target.setTableName(tableName);
        return this;
    }

    /**
     * set
     * @param number number
     * @return THIS
     */
    public TruncateTableBuilder withNumber(Integer number){
        list(target::getPartitionsList, target::setPartitionsList)
                .add(e_pn(number));
        return this;
    }

    /**
     * set
     * @param range range
     * @param range2 range
     * @return THIS
     */
    public TruncateTableBuilder withRange(Integer range,Integer range2){
        list(target::getPartitionsList, target::setPartitionsList)
                .add(e_range(range,range2));
        return this;
    }




    /*
    Quick
     */

    /**
     * Quick build PartitionNumberExpression
     * @param number number
     * @return PartitionNumberExpression
     */
    public static TruncateTable.PartitionNumberExpression e_pn(Number number){
        return new TruncateTable.PartitionNumberExpression(number);
    }

    /**
     * Quick build Range
     * @param number range
     * @param number2 range
     * @return Range
     */
    public static TruncateTable.Range e_range(Number number,Number number2){
        return new TruncateTable.Range(e_pn(number),e_pn(number2));
    }
}
