package com.xy.xsql.tsql.builder.chain.datatypes.constants;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.tsql.model.datatypes.constants.MoneyConstant;

import java.util.Currency;
import java.util.Locale;

import static com.xy.xsql.tsql.builder.chain.datatypes.constants.NumberConstantBuilder.isNegative;

/**
 * Created by xiaoyao9184 on 2018/5/22.
 */
public class MoneyConstantBuilder extends CodeBuilder<MoneyConstant> {

    public MoneyConstantBuilder() {
        super(new MoneyConstant());
    }

    public MoneyConstantBuilder(MoneyConstant tar) {
        super(tar);
    }

    public MoneyConstantBuilder withSymbol() {
        target.setSymbol(Currency.getInstance(Locale.getDefault()).getSymbol());
        return this;
    }

    public MoneyConstantBuilder withSymbol(String symbol) {
        target.setSymbol(symbol);
        return this;
    }

    public MoneyConstantBuilder withNumber(Number number) {
        target.setNumber(number);
        return this;
    }

    public MoneyConstantBuilder withNegative(boolean useNegative) {
        target.setUseNegative(useNegative);
        return this;
    }

    public MoneyConstantBuilder withPositive(boolean usePositive) {
        target.setUsePositive(usePositive);
        return this;
    }

    public MoneyConstantBuilder withNegative() {
        boolean negative = isNegative(target.getNumber());
        target.setUseNegative(negative);
        return this;
    }

    public MoneyConstantBuilder withPositive() {
        boolean negative = isNegative(target.getNumber());
        target.setUsePositive(!negative);
        return this;
    }

    public MoneyConstantBuilder withNegativePositive() {
        boolean negative = isNegative(target.getNumber());
        target.setUseNegative(negative);
        target.setUsePositive(!negative);
        return this;
    }

}
