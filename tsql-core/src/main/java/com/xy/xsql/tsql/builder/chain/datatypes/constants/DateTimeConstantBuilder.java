package com.xy.xsql.tsql.builder.chain.datatypes.constants;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.tsql.model.datatypes.constants.DatetimeConstant;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by xiaoyao9184 on 2018/5/22.
 */
public class DateTimeConstantBuilder extends CodeBuilder<DatetimeConstant> {

    public DateTimeConstantBuilder() {
        super(new DatetimeConstant());
    }

    public DateTimeConstantBuilder(DatetimeConstant tar) {
        super(tar);
    }

    public DateTimeConstantBuilder withDateTime() {
        target.setDateTime(ZonedDateTime.now());
        return this;
    }

    public DateTimeConstantBuilder withDateTime(ZonedDateTime dateTime) {
        target.setDateTime(dateTime);
        return this;
    }

    public DateTimeConstantBuilder withDateTime(Date dateTime) {
        target.setDateTime(dateTime.toInstant().atZone(ZoneId.systemDefault()));
        return this;
    }

    public DateTimeConstantBuilder withFormatter() {
        target.setFormatter(DateTimeFormatter.BASIC_ISO_DATE);
        return this;
    }

    public DateTimeConstantBuilder withFormatter(DateTimeFormatter formatter) {
        target.setFormatter(formatter);
        return this;
    }

}
