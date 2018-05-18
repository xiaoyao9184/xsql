package com.xy.xsql.block.tsql.core.expression;

import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.datatypes.constants.Constant;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.elements.expressions.BinaryExpression;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.expressions.GroupExpression;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ExpressionConverter
        implements ModelMetaBlockConverter<Expression> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Expression>()
                    .description("expression")
                    .czse(d -> d instanceof Constant,"constant")
                        .scope(Object::toString)
                        .and()
//                    .czse(d -> d instanceof ,"scalar_function")
//                        .and()
                    .czse(d -> d instanceof ColumnName,"column")
                        .scope(Object::toString)
                        .and()
                    .czse(d -> d instanceof LocalVariable,"variable")
                        .scope(Object::toString)
                        .and()
                    .czse_ref(d -> d instanceof GroupExpression,GroupExpressionConverter.ExpressionConverter.meta)
//                    .czse(d -> d instanceof ,"{ unary_operator } expression")
//                        .and()
                    .czse_ref(d -> d instanceof BinaryExpression,BinaryExpressionConverter.meta)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
