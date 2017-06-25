package com.xy.xsql.tsql.model.datatype;

import com.xy.xsql.tsql.model.expression.Expression;

/**
 * bit constants
 * integer constants
 * decimal constants
 * float and real constants
 * money constants
 * Created by xiaoyao9184 on 2016/11/13.
 */
public class NumberConstant implements Expression {

    private boolean flagMoney;
    private Number number;


    public NumberConstant(Number number){
        this.number = number;
    }


    public boolean isFlagMoney() {
        return flagMoney;
    }

    public void setFlagMoney(boolean flagMoney) {
        this.flagMoney = flagMoney;
    }

    public Number getNumber() {
        return number;
    }

    public void setNumber(Number number) {
        this.number = number;
    }


    public NumberConstant withMoney(){
        this.flagMoney = true;
        return this;
    }

    @Override
    public String toString(){
        if(flagMoney){
            return "$" + number.toString();
        }
        return number.toString();
    }
}
