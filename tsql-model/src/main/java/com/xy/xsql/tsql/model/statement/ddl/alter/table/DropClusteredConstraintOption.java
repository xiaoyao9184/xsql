package com.xy.xsql.tsql.model.statement.ddl.alter.table;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatype.OnOff;
import com.xy.xsql.tsql.model.element.index.Partition;

/**
 * Created by xiaoyao9184 on 2017/9/17
 */
public class DropClusteredConstraintOption {

    private NumberConstant maxDegreeOfParallelism;
    private OnOff online;
    private Partition moveTo;


    public DropClusteredConstraintOption(){

    }

    public NumberConstant getMaxDegreeOfParallelism() {
        return maxDegreeOfParallelism;
    }

    public void setMaxDegreeOfParallelism(NumberConstant maxDegreeOfParallelism) {
        this.maxDegreeOfParallelism = maxDegreeOfParallelism;
    }

    public OnOff getOnline() {
        return online;
    }

    public void setOnline(OnOff online) {
        this.online = online;
    }

    public Partition getMoveTo() {
        return moveTo;
    }

    public void setMoveTo(Partition moveTo) {
        this.moveTo = moveTo;
    }
}
