package com.xy.xsql.tsql.model.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class HashBytes
        implements CryptographicFunction, Function.InternalFunction {
    private Algorithm algorithm;
    private StringConstant input;
    private LocalVariable inputVariable;

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public StringConstant getInput() {
        return input;
    }

    public void setInput(StringConstant input) {
        this.input = input;
    }

    public LocalVariable getInputVariable() {
        return inputVariable;
    }

    public void setInputVariable(LocalVariable inputVariable) {
        this.inputVariable = inputVariable;
    }

    private enum Algorithm {
        MD2,
        MD4,
        MD5,
        SHA,
        SHA1,
        SHA2_256,
        SHA2_512;

        private String string;

        Algorithm(){
            this.string = "'" + this.name() + "'";
        }

        @Override
        public String toString(){
            return string;
        }
    }
}
