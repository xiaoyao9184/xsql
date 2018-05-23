package com.xy.xsql.tsql.model.datatypes;

import com.xy.xsql.tsql.model.datatypes.precision_scale_length.Length;
import com.xy.xsql.tsql.model.datatypes.precision_scale_length.MaxLength;
import com.xy.xsql.tsql.model.datatypes.precision_scale_length.Precision;
import com.xy.xsql.tsql.model.datatypes.precision_scale_length.Scale;

/**
 * Created by xiaoyao9184 on 2018/5/18.
 */
public interface DataType {

    /**
     * named for data_type
     * @return name
     */
    String name();

    /**
     * internal keyword for data_type name
     */
    enum Keywords {

        bit,

        date,
        datetime,
        datetime2,
        datetimeoffset,
        smalldatetime,
        time,

        hierarchyid,

        decimal,
        numeric,
        float$("float"),
        real,
        int$("int"),
        bigin,
        smallint,
        tinyint,
        money,
        smallmoney,

        rowversion,

        binary,
        varbinary,
        char$("char"),
        varchar,
        nchar,
        nvarchar,
        text,
        ntext,
        image,

        geography,
        geometry,

        sql_variant,
        table,
        uniqueidentifier,
        xml,

        max;

        private String string;

        Keywords(){
            this.string = this.name();
        }
        Keywords(String string){
            this.string = string;
        }

        @Override
        public String toString(){
            return string;
        }
    }

    /**
     * use keyword for data_type's name
     */
    interface KeywordNamed extends DataType {

        @Override
        default String name() {
            return keyword().toString();
        }

        Keywords keyword();


        static KeywordNamed keyword_type(Keywords keywords){
            return () -> keywords;
        }
    }


    abstract class SimpleDataType
            implements DataType {

    }

    abstract class SpatialDataType
            implements DataType {

    }

    abstract class FractionalSecondsPrecisionDataType
            implements DataType,Precision {

        public FractionalSecondsPrecisionDataType(){}

        public FractionalSecondsPrecisionDataType(Integer precision) {
            this.precision = precision;
        }

        protected Integer precision;

        public void precision(Integer precision) {
            this.precision = precision;
        }

        @Override
        public int precision() {
            return precision;
        }

        @Override
        public boolean precisionUsed() {
            return precision != null && precision > 0;
        }
    }

    abstract class FractionalSecondsScaleDataType
            implements DataType,Scale {

        public FractionalSecondsScaleDataType(){}

        public FractionalSecondsScaleDataType(Integer scale) {
            this.scale = scale;
        }

        protected Integer scale;

        public void scale(Integer scale) {
            this.scale = scale;
        }

        @Override
        public int scale() {
            return scale;
        }

        @Override
        public boolean scaleUsed() {
            return scale != null && scale >= 0;
        }
    }

    abstract class PrecisionScaleDataType
            implements DataType,Precision,Scale {

        public PrecisionScaleDataType(){}

        public PrecisionScaleDataType(Integer precision, Integer scale) {
            this.precision = precision;
            this.scale = scale;
        }

        protected Integer precision;
        protected Integer scale;

        public void precision(Integer precision) {
            this.precision = precision;
        }

        public void scale(Integer scale) {
            this.scale = scale;
        }

        @Override
        public int precision() {
            return precision;
        }

        @Override
        public int scale() {
            return scale;
        }

        @Override
        public boolean precisionUsed() {
            return precision != null && precision > 0;
        }

        @Override
        public boolean scaleUsed() {
            return scale != null && scale >= 0;
        }
    }

    abstract class FixedLengthDataType
            implements DataType,Length {

        public FixedLengthDataType(){}

        public FixedLengthDataType(Integer length) {
            this.length = length;
        }

        protected Integer length;

        public void length(Integer length) {
            this.length = length;
        }

        @Override
        public int length() {
            return length;
        }

        @Override
        public boolean lengthUsed() {
            return length != null && length >= 0;
        }
    }

    abstract class MaxFixedLengthDataType
            implements DataType,Length,MaxLength {

        public MaxFixedLengthDataType(){}

        public MaxFixedLengthDataType(Integer length) {
            this.length = length;
        }

        protected Integer length;
        protected boolean max;

        public void length(Integer length) {
            this.length = length;
        }

        public void max(boolean max) {
            this.max = max;
        }

        @Override
        public int length() {
            return length;
        }

        @Override
        public boolean lengthUsed() {
            return !maxUsed() && length != null && length >= 0;
        }

        @Override
        public boolean maxUsed() {
            return max;
        }
    }

}
