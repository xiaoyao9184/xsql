package com.xy.xsql.tsql.style.constructor.builder.queries.select;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.tsql.builder.chain.queries.select.ForBuilder;
import com.xy.xsql.tsql.model.queries.select.For;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public class b_FOR extends ForBuilder<b_FOR> {

    public b_FOR() {
        this.in(this);
    }


    public static class k_BROWSE {}

    public static class b_XML extends XmlBuilder<b_XML> {

        public b_XML() {
            this.in(this);
        }


        public static class b_RAW extends CodeBuilder<String> {
            public b_RAW(String s) {
                super(s);
            }
        }

        public static class k_BINARY_BASE64 {}

        public static class k_TYPE {}

        public static class k_XMLDATA {}

        public static class b_XMLSCHEMA extends CodeBuilder<String> {
            public b_XMLSCHEMA(String s) {
                super(s);
            }
        }

        public static class k_ELEMENTS {}

        public static class k_XSINIL {}

        public static class k_ABSENT {}

        public static class k_EXPLICIT {}

    }

    public static class b_JSON extends JsonBuilder<b_JSON> {

        public b_JSON() {
            this.in(this);
        }


        public static class k_INCLUDE_NULL_VALUES {}

        public static class k_WITHOUT_ARRAY_WRAPPER {}
    }

    public static class k_AUTO {}

    public static class b_PATH extends CodeBuilder<String> {
        public b_PATH(String s) {
            super(s);
        }
    }

    public static class b_ROOT extends CodeBuilder<String> {
        public b_ROOT(String s) {
            super(s);
        }
    }

}
