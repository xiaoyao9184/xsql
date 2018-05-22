package com.xy.xsql.tsql.builder.chain.datatypes;

import com.xy.xsql.tsql.builder.chain.datatypes.constants.*;
import com.xy.xsql.tsql.model.datatypes.constants.*;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

/**
 * Created by xiaoyao9184 on 2018/5/22.
 */
public interface Constants {


    /**
     * string constant
     * @param string string
     * @return StringConstant
     */
    static StringConstant c_string(String string){
        return new StringConstantBuilder()
                .withString(string)
                .withUseNQuote(false)
                .build();
    }

    /**
     * Unicode string constant
     * @param string string
     * @return StringConstant
     */
    static StringConstant c_n_string(String string){
        return new StringConstantBuilder()
                .withString(string)
                .withUseNQuote(true)
                .build();
    }

    /**
     * Binary constant
     * @param bin Binary arrays
     * @return BinaryConstant
     */
    static BinaryConstant c_bin(byte[] bin){
        return new BinaryConstantBuilder()
                .withData(bin)
                .build();
    }

    /**
     * Binary constant
     * @param uuid Binary for uuid
     * @return BinaryConstant
     */
    static BinaryConstant c_bin(UUID uuid){
        return new BinaryConstantBuilder()
                .withData(uuid)
                .build();
    }

    /**
     * bit constant
     * @param flag false 0 true 1
     * @return NumberConstant
     */
    static BitConstant c_bit(boolean flag){
        return new BitConstantBuilder()
                .withByte(flag)
                .build();
    }

    /**
     * datetime constant
     * @return DatetimeConstant
     */
    static DatetimeConstant c_datetime(){
        return new DateTimeConstantBuilder()
                .withDateTime(ZonedDateTime.now())
                .withFormatter(DateTimeFormatter.ISO_DATE_TIME)
                .build();
    }

    /**
     * datetime constant
     * @param dateTime dateTime
     * @return DatetimeConstant
     */
    static DatetimeConstant c_datetime(ZonedDateTime dateTime){
        return new DateTimeConstantBuilder()
                .withDateTime(dateTime)
                .withFormatter(DateTimeFormatter.ISO_DATE_TIME)
                .build();
    }

    /**
     * datetime constant
     * @param dateTime dateTime
     * @param formatter formatter
     * @return DatetimeConstant
     */
    static DatetimeConstant c_datetime(ZonedDateTime dateTime, DateTimeFormatter formatter){
        return new DateTimeConstantBuilder()
                .withDateTime(dateTime)
                .withFormatter(formatter)
                .build();
    }

    /**
     * datetime constant
     * @param date date
     * @return DatetimeConstant
     */
    static DatetimeConstant c_datetime(Date date){
        return new DateTimeConstantBuilder()
                .withDateTime(date)
                .withFormatter(DateTimeFormatter.ISO_DATE_TIME)
                .build();
    }

    /**
     * datetime constant
     * @param format format
     * @return DatetimeConstant
     */
    static DatetimeConstant c_datetime(String format){
        return new DateTimeConstantBuilder()
                .withDateTime(ZonedDateTime.now())
                .withFormatter(DateTimeFormatter.ofPattern(format))
                .build();
    }

    /**
     * datetime constant
     * @param dateTime dateTime
     * @param format format
     * @return DatetimeConstant
     */
    static DatetimeConstant c_datetime(ZonedDateTime dateTime, String format){
        return new DateTimeConstantBuilder()
                .withDateTime(dateTime)
                .withFormatter(DateTimeFormatter.ofPattern(format))
                .build();
    }

    /**
     * datetime constant
     * @param date date
     * @param format format
     * @return DatetimeConstant
     */
    static DatetimeConstant c_datetime(Date date, String format){
        return new DateTimeConstantBuilder()
                .withDateTime(date)
                .withFormatter(DateTimeFormatter.ofPattern(format))
                .build();
    }

    /**
     * number constant
     * @param number
     * @return NumberConstant
     */
    static NumberConstant c_number(Number number){
        return new NumberConstantBuilder()
                .withNumber(number)
                .withNegative()
                .build();
    }

    /**
     * number constant
     * @param number
     * @return NumberConstant
     */
    static NumberConstant c_negative_number(Number number){
        return new NumberConstantBuilder()
                .withNumber(number)
                .withNegative(true)
                .build();
    }

    /**
     * number constant
     * @param number
     * @return NumberConstant
     */
    static NumberConstant c_positive_number(Number number){
        return new NumberConstantBuilder()
                .withNumber(number)
                .withPositive(true)
                .build();
    }

    /**
     * number constant
     * @param number
     * @return NumberConstant
     */
    static NumberConstant c_unsigned_integer(Integer number){
        return new NumberConstantBuilder()
                .withNumber(Math.abs(number))
                .build();
    }

    /**
     * number constant
     * @param number
     * @return NumberConstant
     */
    static NumberConstant c_unsigned_long(Long number){
        return new NumberConstantBuilder()
                .withNumber(Math.abs(number))
                .build();
    }

    /**
     * number constant
     * @param number
     * @return NumberConstant
     */
    static NumberConstant c_unsigned_double(Double number){
        return new NumberConstantBuilder()
                .withNumber(Math.abs(number))
                .build();
    }

    /**
     * number constant
     * @param number
     * @return NumberConstant
     */
    static NumberConstant c_unsigned_float(Float number){
        return new NumberConstantBuilder()
                .withNumber(Math.abs(number))
                .build();
    }

    /**
     * money constant
     * @param number
     * @return MoneyConstant
     */
    static MoneyConstant c_money(Number number){
        return new MoneyConstantBuilder()
                .withNumber(number)
                .withSymbol()
                .withNegative()
                .build();
    }

    /**
     * money constant
     * @param number
     * @return MoneyConstant
     */
    static MoneyConstant c_negative_money(Number number){
        return new MoneyConstantBuilder()
                .withNumber(number)
                .withSymbol()
                .withNegative(true)
                .build();
    }

    /**
     * money constant
     * @param number
     * @return MoneyConstant
     */
    static MoneyConstant c_positive_money(Number number){
        return new MoneyConstantBuilder()
                .withNumber(number)
                .withSymbol()
                .withPositive(true)
                .build();
    }

    /**
     * money constant
     * @param symbol
     * @param number
     * @return MoneyConstant
     */
    static MoneyConstant c_money(String symbol, Number number){
        return new MoneyConstantBuilder()
                .withNumber(number)
                .withSymbol(symbol)
                .withNegative()
                .build();
    }

    /**
     * money constant
     * @param symbol
     * @param number
     * @return MoneyConstant
     */
    static MoneyConstant c_negative_money(String symbol, Number number){
        return new MoneyConstantBuilder()
                .withNumber(number)
                .withSymbol(symbol)
                .withNegative(true)
                .build();
    }

    /**
     * money constant
     * @param symbol
     * @param number
     * @return MoneyConstant
     */
    static MoneyConstant c_positive_money(String symbol, Number number){
        return new MoneyConstantBuilder()
                .withNumber(number)
                .withSymbol(symbol)
                .withPositive(true)
                .build();
    }

    /**
     * uniqueidentifier constant
     * @param uuid uuid
     * @return UniqueidentifierConstant
     */
    static UniqueidentifierConstant c_uniqueidentifier(UUID uuid){
        return new UniqueidentifierConstantBuilder()
                .withUUID(uuid)
                .withUseBinaryFormat()
                .build();
    }

    /**
     * uniqueidentifier constant
     * @param uuid uuid
     * @return UniqueidentifierConstant
     */
    static UniqueidentifierConstant c_uniqueidentifier(String uuid){
        return new UniqueidentifierConstantBuilder()
                .withUUID(uuid)
                .build();
    }

}
