package com.xy.xsql.tsql.converter.datatypes;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.converter.datatypes.table.table.TableTypeDefinitionConverter;
import com.xy.xsql.tsql.model.datatypes.DataType;
import com.xy.xsql.tsql.model.datatypes.Xml;
import com.xy.xsql.tsql.model.datatypes.table.table.TableTypeDefinition;
import com.xy.xsql.tsql.model.elements.Other;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class DataTypeConverter
        implements ModelMetaBlockConverter<DataType> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,DataType>()
                    .name("data_type")
                    .description("data type")
                    .czse(c -> c instanceof DataType.SimpleDataType)
                        .ref(SimpleDataTypeConverter.class)
                        .scope(c -> c)
                        .and()
                    .czse(c -> c instanceof DataType.SpatialDataType)
                        .ref(SimpleDataTypeConverter.class)
                        .scope(c -> c)
                        .and()
                    .czse(c -> c instanceof DataType.FractionalSecondsPrecisionDataType)
                        .ref(FractionalSecondsPrecisionDataTypeConverter.class)
                        .scope(c -> c)
                        .and()
                    .czse(c -> c instanceof DataType.FractionalSecondsScaleDataType)
                        .ref(FractionalSecondsScaleDataTypeConverter.class)
                        .scope(c -> c)
                        .and()
                    .czse(c -> c instanceof DataType.PrecisionScaleDataType)
                        .ref(PrecisionScaleDataTypeConverter.class)
                        .scope(c -> c)
                        .and()
                    .czse(c -> c instanceof DataType.FixedLengthDataType)
                        .ref(FixedLengthDataTypeConverter.class)
                        .scope(c -> c)
                        .and()
                    .czse(c -> c instanceof DataType.MaxFixedLengthDataType)
                        .ref(MaxFixedLengthDataTypeConverter.class)
                        .scope(c -> c)
                        .and()
                    .czse(c -> c instanceof Xml)
                        .ref(XmlConverter.class)
                        .scope(c -> c)
                        .and()
                    .czse(c -> c instanceof TableTypeDefinition)
                        .ref(TableTypeDefinitionConverter.class)
                        .scope(c -> c)
                        .and()
                    .czse(c -> true)
                        //TODO father class loop, must find subclass Converter
                        .scope(c -> { throw new RuntimeException("scope loop!"); })
                        .and()
                    .sub_format_empty_delimiter()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

    public static class SimpleDataTypeConverter
            implements ModelMetaBlockConverter<DataType.SimpleDataType> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,DataType.SimpleDataType>()
                        .description("type")
                        .sub()
                            .description("type name")
                            .scope(d -> d.name())
                            .and()
                        .sub_format_empty_delimiter()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class FractionalSecondsPrecisionDataTypeConverter
            implements ModelMetaBlockConverter<DataType.FractionalSecondsPrecisionDataType> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,DataType.FractionalSecondsPrecisionDataType>()
                        .description("type[ (fractional seconds precision) ]")
                        .sub()
                            .description("type name")
                            .scope(d -> d.name())
                            .and()
                        .sub()
                            .optional(d -> !d.precisionUsed())
                            .sub_keyword(Other.GROUP_START)
                            .sub()
                                .scope(d -> d.precision())
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .sub_format_empty_delimiter()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class FractionalSecondsScaleDataTypeConverter
            implements ModelMetaBlockConverter<DataType.FractionalSecondsScaleDataType> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,DataType.FractionalSecondsScaleDataType>()
                        .description("type[ (fractional seconds scale) ]")
                        .sub()
                            .description("type name")
                            .scope(d -> d.name())
                            .and()
                        .sub()
                            .optional(d -> !d.scaleUsed())
                            .sub_keyword(Other.GROUP_START)
                            .sub()
                                .scope(d -> d.scale())
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .sub_format_empty_delimiter()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class PrecisionScaleDataTypeConverter
            implements ModelMetaBlockConverter<DataType.PrecisionScaleDataType> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,DataType.PrecisionScaleDataType>()
                        .description("type[ (p[ ,s] )]")
                        .sub()
                            .description("type name")
                            .scope(d -> d.name())
                            .and()
                        .sub()
                            .optional(d -> !d.precisionUsed())
                            .sub_keyword(Other.GROUP_START)
                            .sub()
                                .scope(d -> d.precision())
                                .and()
                            .sub()
                                .optional(d -> !d.scaleUsed())
                                .sub_keyword(Other.DELIMITER)
                                .sub()
                                    .scope(d -> d.scale())
                                    .and()
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .sub_format_empty_delimiter()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class FixedLengthDataTypeConverter
            implements ModelMetaBlockConverter<DataType.FixedLengthDataType> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,DataType.FixedLengthDataType>()
                        .description("type[ ( n ) ]")
                        .sub()
                            .description("type name")
                            .scope(d -> d.name())
                            .and()
                        .sub()
                            .optional(d -> !d.lengthUsed())
                            .sub_keyword(Other.GROUP_START)
                            .sub()
                                .scope(d -> d.length())
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .sub_format_empty_delimiter()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class MaxFixedLengthDataTypeConverter
            implements ModelMetaBlockConverter<DataType.MaxFixedLengthDataType> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,DataType.MaxFixedLengthDataType>()
                        .description("type[ ( n | max) ]")
                        .sub()
                            .description("type name")
                            .scope(d -> d.name())
                            .and()
                        .sub()
                            .optional(d -> !d.lengthUsed() && !d.maxUsed())
                            .sub_keyword(Other.GROUP_START)
                            .sub()
                                .czse(d -> d.lengthUsed())
                                    .scope(d -> d.length())
                                    .and()
                                .czse(d -> d.maxUsed())
                                    .keyword(DataType.Keywords.max)
                                    .and()
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .sub_format_empty_delimiter()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

}
