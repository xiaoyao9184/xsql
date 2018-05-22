package com.xy.xsql.tsql.builder.chain.datatypes.constants;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;

/**
 * Created by xiaoyao9184 on 2018/5/22.
 */
public class StringConstantBuilder extends CodeBuilder<StringConstant> {

    public StringConstantBuilder() {
        super(new StringConstant());
    }

    public StringConstantBuilder(StringConstant tar) {
        super(tar);
    }

    public StringConstantBuilder withString(String string) {
        target.setString(string);
        return this;
    }

    public StringConstantBuilder withUseNQuote(boolean useNQuote) {
        target.setUseNQuote(useNQuote);
        return this;
    }

}
