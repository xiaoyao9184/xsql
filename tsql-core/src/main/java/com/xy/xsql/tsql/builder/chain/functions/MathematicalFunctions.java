package com.xy.xsql.tsql.builder.chain.functions;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.mathematical.*;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public interface MathematicalFunctions {


    static Abs f_abs(
            Expression numericExpression){
        Abs f = new Abs();
        f.setNumericExpression(numericExpression);
        return f;
    }
    static ACos f_acos(
            Expression floatExpression){
        ACos f = new ACos();
        f.setFloatExpression(floatExpression);
        return f;
    }
    static ASin f_asin(
            Expression floatExpression){
        ASin f = new ASin();
        f.setFloatExpression(floatExpression);
        return f;
    }
    static ATan f_atan(
            Expression floatExpression){
        ATan f = new ATan();
        f.setFloatExpression(floatExpression);
        return f;
    }
    static ATn2 f_atn2(
            Expression floatExpression,
            Expression floatExpression2){
        ATn2 f = new ATn2();
        f.setFloatExpression(floatExpression);
        f.setFloatExpression2(floatExpression2);
        return f;
    }
    static Ceiling f_ceiling(
            Expression numericExpression){
        Ceiling f = new Ceiling();
        f.setNumericExpression(numericExpression);
        return f;
    }
    static Cos f_cos(
            Expression floatExpression){
        Cos f = new Cos();
        f.setFloatExpression(floatExpression);
        return f;
    }
    static Cot f_cot(
            Expression floatExpression){
        Cot f = new Cot();
        f.setFloatExpression(floatExpression);
        return f;
    }
    static Degrees f_degrees(
            Expression numericExpression){
        Degrees f = new Degrees();
        f.setNumericExpression(numericExpression);
        return f;
    }
    static Exp f_exp(
            Expression floatExpression){
        Exp f = new Exp();
        f.setFloatExpression(floatExpression);
        return f;
    }
    static Floor f_floor(
            Expression numericExpression){
        Floor f = new Floor();
        f.setNumericExpression(numericExpression);
        return f;
    }
    static Log f_log(
            Expression floatExpression,
            Integer base){
        Log f = new Log();
        f.setFloatExpression(floatExpression);
        f.setBase(base);
        return f;
    }
    static Log f_log(
            Expression floatExpression){
        Log f = new Log();
        f.setFloatExpression(floatExpression);
        return f;
    }
    static Log10 f_log10(
            Expression floatExpression){
        Log10 f = new Log10();
        f.setFloatExpression(floatExpression);
        return f;
    }
    static Pi f_pi(){
        return new Pi();
    }
    static Power f_power(
            Expression floatExpression,
            Expression y){
        Power f = new Power();
        f.setFloatExpression(floatExpression);
        f.setY(y);
        return f;
    }
    static Radians f_radians(
            Expression numericExpression){
        Radians f = new Radians();
        f.setNumericExpression(numericExpression);
        return f;
    }
    static Rand f_rand(
            Expression seed){
        Rand f = new Rand();
        f.setSeed(seed);
        return f;
    }
    static Rand f_rand(){
        Rand f = new Rand();
        return f;
    }
    static Round f_round(
            Expression numericExpression,
            Expression length,
            Function function){
        Round f = new Round();
        f.setNumericExpression(numericExpression);
        f.setLength(length);
        f.setFunction(function);
        return f;
    }
    static Round f_round(
            Expression numericExpression,
            Expression length){
        Round f = new Round();
        f.setNumericExpression(numericExpression);
        f.setLength(length);
        return f;
    }
    static Sign f_sign(
            Expression numericExpression){
        Sign f = new Sign();
        f.setNumericExpression(numericExpression);
        return f;
    }
    static Sin f_sin(
            Expression floatExpression){
        Sin f = new Sin();
        f.setFloatExpression(floatExpression);
        return f;
    }
    static Sqrt f_sqrt(
            Expression floatExpression){
        Sqrt f = new Sqrt();
        f.setFloatExpression(floatExpression);
        return f;
    }
    static Square f_square(
            Expression floatExpression){
        Square f = new Square();
        f.setFloatExpression(floatExpression);
        return f;
    }
    static Tan f_tan(
            Expression floatExpression){
        Tan f = new Tan();
        f.setFloatExpression(floatExpression);
        return f;
    }

}
