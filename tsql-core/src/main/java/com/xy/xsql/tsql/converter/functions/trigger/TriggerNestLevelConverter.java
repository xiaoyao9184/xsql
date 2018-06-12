package com.xy.xsql.tsql.converter.functions.trigger;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.trigger.Trigger_NestLevel;
import com.xy.xsql.tsql.model.functions.trigger.Update;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class TriggerNestLevelConverter
        implements ModelMetaBlockConverter<Trigger_NestLevel> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Trigger_NestLevel>()
                    .overall("TRIGGER_NESTLEVEL")
                    .sub_keyword(Function.Keywords.TRIGGER_NESTLEVEL)
                    .sub_keyword(Other.GROUP_START)
                    .sub()
                        .optional(d -> d.getObjectId() == null
                                && d.getTriggerType() == null
                                && d.getTriggerEventCategory() == null)
                        .sub("object_id")
                            .optional(d -> d.getObjectId() == null)
                            .scope(d -> d.getObjectId())
                            .and()
                        .sub_keyword(Other.DELIMITER)
                        .sub("'trigger_type'")
                            .optional(d -> d.getTriggerType() == null)
                            .scope(d -> d.getTriggerType())
                            .and()
                        .sub_keyword(Other.DELIMITER)
                        .sub("'trigger_event_category'")
                            .optional(d -> d.getTriggerEventCategory() == null)
                            .scope(d -> d.getTriggerEventCategory())
                            .and()
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
