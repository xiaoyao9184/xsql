package com.xy.xsql.tsql.builder.chain.datatypes.constants;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;

/**
 * Created by xiaoyao9184 on 2018/5/22.
 */
public class NumberConstantBuilder extends CodeBuilder<NumberConstant> {

    public NumberConstantBuilder() {
        super(new NumberConstant());
    }

    public NumberConstantBuilder(NumberConstant tar) {
        super(tar);
    }

    public NumberConstantBuilder withNumber(Number number) {
        target.setNumber(number);
        return this;
    }

    public NumberConstantBuilder withNegative(boolean useNegative) {
        target.setUseNegative(useNegative);
        return this;
    }

    public NumberConstantBuilder withPositive(boolean usePositive) {
        target.setUsePositive(usePositive);
        return this;
    }

    public NumberConstantBuilder withNegative() {
        boolean negative = isNegative(target.getNumber());
        target.setUseNegative(negative);
        return this;
    }

    public NumberConstantBuilder withPositive() {
        boolean negative = isNegative(target.getNumber());
        target.setUsePositive(!negative);
        return this;
    }

    public NumberConstantBuilder withNegativePositive() {
        boolean negative = isNegative(target.getNumber());
        target.setUseNegative(negative);
        target.setUsePositive(!negative);
        return this;
    }

    public static boolean isNegative(Number number) {
        boolean negative;
        if(number instanceof Byte){
            negative = number.byteValue() < 0;
        }else if(number instanceof Integer){
            negative = number.intValue() < 0;
        }else if(number instanceof Short){
            negative = number.shortValue() < 0;
        }else if(number instanceof Long){
            negative = number.longValue() < 0;
        }else if(number instanceof Float){
            negative = number.floatValue() < 0;
        }else if(number instanceof Double){
            negative = number.doubleValue() < 0;
        }else{
            negative = number.doubleValue() < 0;
        }
        return negative;
    }

}
