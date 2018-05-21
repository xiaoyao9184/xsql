package com.xy.xsql.tsql.converter;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumConverterUtil {


    public static String getSyntaxString(Class<? extends Enum> enumClass){
        return Stream.of(enumClass.getEnumConstants())
                .map(Enum::toString)
                .collect(Collectors.joining(" | "));
    }
}
