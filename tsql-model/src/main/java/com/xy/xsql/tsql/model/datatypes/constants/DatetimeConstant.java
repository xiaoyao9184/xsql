package com.xy.xsql.tsql.model.datatypes.constants;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * datetime constants
 * Created by xiaoyao9184 on 2018/5/22.
 */
public class DatetimeConstant implements Constant {

    private ZonedDateTime dateTime;
    private DateTimeFormatter formatter;

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }

    public void setFormatter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public String toString(){
        if(formatter == null){
            formatter = DateTimeFormatter.BASIC_ISO_DATE;
        }
        if(formatter == null){
            formatter = DateTimeFormatter.BASIC_ISO_DATE;
        }
        return dateTime.format(formatter);
    }

}
