package com.xy.xsql.tsql.builder.chain;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;

import java.util.Comparator;

/**
 * Created by xiaoyao9184 on 2018/5/22.
 */
public class NumberConstantComparator implements Comparator<NumberConstant> {
    @Override
    public int compare(NumberConstant o1, NumberConstant o2) {
        if(o1.isUseNegative() == o2.isUseNegative()
                && o1.isUsePositive() == o2.isUsePositive()
                && o1.getNumber().equals(o2.getNumber())){
            return 0;
        }
        return -1;
    }
}
