package com.xy.xsql.tsql.model.functions.cryptographic;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class HashBytes
        implements CryptographicFunction, Function.InternalFunction {
    private Algorithm algorithm;
    private Expression input;
    private LocalVariable inputVariable;

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public Expression getInput() {
        return input;
    }

    public void setInput(Expression input) {
        this.input = input;
    }

    public LocalVariable getInputVariable() {
        return inputVariable;
    }

    public void setInputVariable(LocalVariable inputVariable) {
        this.inputVariable = inputVariable;
    }

    public enum Algorithm {
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
