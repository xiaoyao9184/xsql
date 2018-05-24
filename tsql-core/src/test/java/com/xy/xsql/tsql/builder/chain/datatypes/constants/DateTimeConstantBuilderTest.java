package com.xy.xsql.tsql.builder.chain.datatypes.constants;

import com.xy.xsql.tsql.model.datatypes.constants.DatetimeConstant;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_datetime;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/22.
 */
public class DateTimeConstantBuilderTest {

    public DatetimeConstant example1 = c_datetime(
            LocalDate.parse("December 5, 1985",
                    DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.US))
                    .atStartOfDay()
                    .atZone(ZoneId.systemDefault()),
            DateTimeFormatter.ofPattern("MMMM d, yyyy",Locale.US)
    );
    public DatetimeConstant example2 = c_datetime(
            LocalDate.parse("5 December, 1985",
                    DateTimeFormatter.ofPattern("d MMMM, yyyy", Locale.US)
            ).atStartOfDay().atZone(ZoneId.systemDefault()),
            DateTimeFormatter.ofPattern("d MMMM, yyyy",Locale.US)
    );
    public DatetimeConstant example3 = c_datetime(
            LocalDate.parse("851205",
                    DateTimeFormatter.ofPattern("yyMMdd"))
                    .atStartOfDay().atZone(ZoneId.systemDefault()),
            "yyMMdd"
    );
    public DatetimeConstant example4 = c_datetime(
            LocalDate.parse("12/5/98",
                    DateTimeFormatter.ofPattern("M/d/y"))
                    .atStartOfDay().atZone(ZoneId.systemDefault()),
            "M/d/y"
    );
    public DatetimeConstant example5 = c_datetime(
            LocalTime.parse("14:30:24",
                    DateTimeFormatter.ofPattern("HH:mm:ss"))
                    .atDate(LocalDate.MAX).atZone(ZoneId.systemDefault()),
            "HH:mm:ss"
    );
    public DatetimeConstant example6 = c_datetime(
            LocalTime.parse("04:24 PM",
                    DateTimeFormatter.ofPattern("hh:mm a",Locale.US))
                    .atDate(LocalDate.MAX).atZone(ZoneId.systemDefault()),
            DateTimeFormatter.ofPattern("hh:mm a",Locale.US)
    );

    /**
     *
     */
    @Test
    public void testExample(){
        assertEquals(
                example1.getDateTime().format(example1.getFormatter()),
                "December 5, 1985");
        assertEquals(
                example2.getDateTime().format(example2.getFormatter()),
                "5 December, 1985");
        assertEquals(
                example3.getDateTime().format(example3.getFormatter()),
                "851205");
        assertEquals(
                example4.getDateTime().format(example4.getFormatter()),
                "12/5/98");
        assertEquals(
                example5.getDateTime().format(example5.getFormatter()),
                "14:30:24");
        assertEquals(
                example6.getDateTime().format(example6.getFormatter()),
                "04:24 PM");
    }

}