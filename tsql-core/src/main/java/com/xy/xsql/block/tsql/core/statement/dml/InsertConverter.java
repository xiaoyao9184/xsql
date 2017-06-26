package com.xy.xsql.block.tsql.core.statement.dml;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.model.Block;
import com.xy.xsql.block.tsql.core.clause.OutputConverter;
import com.xy.xsql.block.tsql.core.clause.TableValueConstructorConverter;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.Output;
import com.xy.xsql.tsql.model.clause.TableValueConstructor;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.statement.dml.Insert;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class InsertConverter
        implements BlockConverter<Insert> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,Insert> builder =
            new ReferenceBlockBuilder<Void,Insert>()
                    .overall("INSERT")
                    .sub("WITH <common_table_expression> [ ,...n ]")
                        .optional(d -> d.getWith() == null)
                        .data(Insert::getWith)
                        .and()
                    .sub_keyword(Keywords.INSERT)
                    .sub("TOP ( expression ) [ PERCENT ]")
                        .optional(d -> d.getTop() == null)
                        .data(Insert::getTop)
                        .and()
                    .sub("INTO")
                        .optional(d -> !d.isUseInto())
                        .keyword(Keywords.INTO)
                        .and()
                    .sub()
                        .required()
                        .czse(d -> d.getTableName() != null, "<object>")
                            .data(Insert::getTableName)
                            .and()
    //                    .czse_meta("rowset_function_limited")
                        .and()
                    .sub()
                        .optional(d -> d.getColumns() == null)
                        .sub_keyword(Other.GROUP_START)
                        .sub("column_list")
                            .data(Insert::getColumns)
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .and()
                    .sub("OUTPUT Clause")
                        .optional(d -> d.getOutput() == null)
                        .ref(OutputConverter.class)
                        .data(Insert::getOutput)
                        .and()
                    .sub()
                        .required()
                        //TODO donot use ref
                        .czse(d -> d.getValues() != null, "VALUES ( { DEFAULT | NULL | expression } [ ,...n ] ) [ ,...n     ]")
                            .optional(d -> d.getValues() == null)
                            .ref(TableValueConstructorConverter.class)
                            .data(Insert::getValues)
                            .and()
    //                    .czse_meta("derived_table")
    //                    .czse_meta("execute_statement")
    //                    .czse_meta("<dml_table_source>")
                        .czse(Insert::isUseDefaultValues,"DEFAULT VALUES")
                            .optional(Insert::isUseDefaultValues)
                            .sub_keyword(Keywords.DEFAULT)
                            .sub_keyword(Keywords.VALUES)
                            .and()
                        .headFootTakeLine()
                        .subTakeLine()
                        .and()
                    .subTakeLine();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public Block convert(Insert insert) {
        return builder
                .data(insert)
                .build();
    }

}
