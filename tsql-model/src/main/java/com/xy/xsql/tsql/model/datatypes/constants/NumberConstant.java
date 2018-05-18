package com.xy.xsql.tsql.model.datatypes.constants;

import com.xy.xsql.tsql.model.elements.expressions.Expression;

/**
 * bit constants
 * integer constants
 * decimal constants
 * float and real constants
 * money constants
 * Created by xiaoyao9184 on 2016/11/13.
 */
public class NumberConstant implements Constant, Expression {

    private boolean flagMoney;
    private boolean unsigned;
    private boolean integer;
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

    public boolean isUnsigned() {
        return unsigned;
    }

    public void setUnsigned(boolean unsigned) {
        this.unsigned = unsigned;
    }

    public boolean isInteger() {
        return integer;
    }

    public void setInteger(boolean integer) {
        this.integer = integer;
    }


    public NumberConstant withMoney(){
        this.flagMoney = true;
        return this;
    }

    public NumberConstant withUnsigned(){
        this.unsigned = true;
        return this;
    }

    public NumberConstant withInteger(){
        this.integer = true;
        return this;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        if(flagMoney){
            sb.append("$");
        }
        if(integer){
            number = number.intValue();
        }

        if(unsigned){
            if(number instanceof Byte){
                number = Math.abs(number.byteValue());
            }else if(number instanceof Integer){
                number = Math.abs(number.intValue());
            }else if(number instanceof Short){
                number = Math.abs(number.shortValue());
            }else if(number instanceof Long){
                number = Math.abs(number.longValue());
            }else if(number instanceof Float){
                number = Math.abs(number.floatValue());
            }else if(number instanceof Double){
                number = Math.abs(number.doubleValue());
            }
        }


//        if(number instanceof Float ||
//                number instanceof Double){
//            DecimalFormat df = new DecimalFormat("0.00");
//            sb.append(df.format(number));
//        }else{
//            sb.append(number);
//        }
        sb.append(number);

        return sb.toString();
    }

}
