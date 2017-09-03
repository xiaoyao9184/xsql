package com.xy.xsql.block.tsql.core.element.constraint;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.element.constraint.Check;
import com.xy.xsql.tsql.model.element.constraint.PrimaryUnique;
import com.xy.xsql.tsql.model.element.table.TableConstraint;

/**
 * Created by xiaoyao9184 on 2017/9/3.
 */
public class PrimaryUniqueConverters {

    public static class SimplePrimaryUniqueConverter
            implements ModelMetaBlockConverter<PrimaryUnique> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,PrimaryUnique>()
                        .sub()
                            .description("primary key/unique")
                            .syntax_required()
                            .czse(PrimaryUnique::isUsePrimaryKey)
                                .sub_keyword(Keywords.PRIMARY)
                                .sub_keyword(Keywords.KEY)
                                .and()
                            .czse_keyword(d -> !d.isUsePrimaryKey(), Keywords.UNIQUE)
                            .and()
                        .sub_keyword(Other.GROUP_START)
                        .sub("column_name")
                            .list()
                            .scope(PrimaryUnique::getColumns)
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }
}
