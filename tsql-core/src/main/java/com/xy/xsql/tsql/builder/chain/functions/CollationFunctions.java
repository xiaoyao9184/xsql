package com.xy.xsql.tsql.builder.chain.functions;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.collation.CollationProperty;
import com.xy.xsql.tsql.model.functions.collation.Tertiary_Weights;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public interface CollationFunctions {

    static CollationProperty f_collationproperty(
            String collation_name,
            String property
    ){
        CollationProperty f = new CollationProperty();
        f.setCollationName(collation_name);
        f.setProperty(property);
        return f;
    }
    static Tertiary_Weights f_tertiary_weights(
            Expression non_Unicode_character_string_expression
    ){
        Tertiary_Weights f = new Tertiary_Weights();
        f.setNonUnicodeCharacterStringExpression(non_Unicode_character_string_expression );
        return f;
    }
}
