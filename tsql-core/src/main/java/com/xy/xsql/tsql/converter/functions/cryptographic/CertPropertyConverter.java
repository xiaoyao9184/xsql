package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.cryptographic.AsymKeyProperty;
import com.xy.xsql.tsql.model.functions.cryptographic.CertProperty;

import static com.xy.xsql.tsql.converter.EnumConverterUtil.getSyntaxString;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class CertPropertyConverter
        implements ModelMetaBlockConverter<CertProperty> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,CertProperty>()
                    .overall("CertProperty")
                    .sub_keyword(Function.Keywords.CertProperty)
                    .sub_keyword(Other.GROUP_START)
                    .sub("Cert_ID")
                        .scope(d -> d.getCertId())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("'<PropertyName>'")
                        .scope(d -> d.getProperties())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


    public static class PropertiesConverter
            implements ModelMetaBlockConverter<CertProperty.Properties> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,CertProperty.Properties>()
                        .overall("PropertyName")
                        .sub(getSyntaxString(CertProperty.Properties.class))
                            .scope(d -> d.toString())
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }
}
