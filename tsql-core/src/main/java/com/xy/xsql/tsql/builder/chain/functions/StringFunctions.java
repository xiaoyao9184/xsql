package com.xy.xsql.tsql.builder.chain.functions;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.string.*;
import com.xy.xsql.tsql.model.queries.select.OrderBy;

import java.util.Arrays;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public interface StringFunctions {

    static Ascii f_ascii(Expression characterExpression){
        Ascii f = new Ascii();
        f.setCharacterExpression(characterExpression);
        return f;
    }
    static Char f_char(Expression integerExpression){
        Char f = new Char();
        f.setIntegerExpression(integerExpression);
        return f;
    }
    static CharIndex f_charindex(Expression expressionToFind, Expression expressionToSearch){
        CharIndex f = new CharIndex();
        f.setExpressionToFind(expressionToFind);
        f.setExpressionToSearch(expressionToSearch);
        return f;
    }
    static CharIndex f_charindex(Expression expressionToFind, Expression expressionToSearch, Expression startLocation){
        CharIndex f = new CharIndex();
        f.setExpressionToFind(expressionToFind);
        f.setExpressionToSearch(expressionToSearch);
        f.setStartLocation(startLocation);
        return f;
    }

    static Concat f_concat(Expression... stringValue){
        Concat f = new Concat();
        f.setStringValueList(Arrays.asList(stringValue));
        return f;
    }
    static Concat_Ws f_concat_ws(Expression separator, Expression... argument){
        Concat_Ws f = new Concat_Ws();
        f.setSeparator(separator);
        f.setArgumentList(Arrays.asList(argument));
        return f;
    }
    static Difference f_difference(Expression characterExpression, Expression characterExpression3){
        Difference f = new Difference();
        f.setCharacterExpression(characterExpression);
        f.setCharacterExpression2(characterExpression3);
        return f;
    }
    static Format f_format(
            Expression value,
            Expression format,
            Expression culture){
        Format f = new Format();
        f.setValue(value);
        f.setFormat(format);
        f.setCulture(culture);
        return f;
    }
    static Format f_format(
            Expression value,
            Expression format){
        Format f = new Format();
        f.setValue(value);
        f.setFormat(format);
        return f;
    }
    static Left f_left(
            Expression characterExpression,
            Expression integerExpression){
        Left f = new Left();
        f.setCharacterExpression(characterExpression);
        f.setIntegerExpression(integerExpression);
        return f;
    }
    static Len f_len(
            Expression stringExpression){
        Len f = new Len();
        f.setStringExpression(stringExpression);
        return f;
    }
    static Lower f_lower(
            Expression characterExpression){
        Lower f = new Lower();
        f.setCharacterExpression(characterExpression);
        return f;
    }
    static LTrim f_ltrim(
            Expression characterExpression){
        LTrim f = new LTrim();
        f.setCharacterExpression(characterExpression);
        return f;
    }
    static NChar f_nchar(
            Expression integerExpression){
        NChar f = new NChar();
        f.setIntegerExpression(integerExpression);
        return f;
    }
    static PatIndex f_patindex(
            Expression pattern,
            Expression expression){
        PatIndex f = new PatIndex();
        f.setPattern(pattern);
        f.setExpression(expression);
        return f;
    }
    static QuoteName f_quotename(
            Expression characterString,
            Expression quoteCharacter){
        QuoteName f = new QuoteName();
        f.setCharacterString(characterString);
        f.setQuoteCharacter(quoteCharacter);
        return f;
    }
    static QuoteName f_quotename(
            Expression characterString){
        QuoteName f = new QuoteName();
        f.setCharacterString(characterString);
        return f;
    }
    static Replace f_replace(
            Expression stringExpression,
            Expression stringPattern,
            Expression stringReplacement){
        Replace f = new Replace();
        f.setStringExpression(stringExpression);
        f.setStringPattern(stringPattern);
        f.setStringReplacement(stringReplacement);
        return f;
    }
    static Replicate f_replicate(
            Expression stringExpression,
            Expression integerExpression){
        Replicate f = new Replicate();
        f.setStringExpression(stringExpression);
        f.setIntegerExpression(integerExpression);
        return f;
    }
    static Reverse f_reverse(
            Expression stringExpression){
        Reverse f = new Reverse();
        f.setStringExpression(stringExpression);
        return f;
    }
    static Right f_right(
            Expression characterExpression,
            Expression integerExpression){
        Right f = new Right();
        f.setCharacterExpression(characterExpression);
        f.setIntegerExpression(integerExpression);
        return f;
    }
    static RTrim f_rtrim(
            Expression characterExpression){
        RTrim f = new RTrim();
        f.setCharacterExpression(characterExpression);
        return f;
    }
    static Soundex f_soundex(
            Expression characterExpression){
        Soundex f = new Soundex();
        f.setCharacterExpression(characterExpression);
        return f;
    }
    static Space f_space(
            Expression integerExpression){
        Space f = new Space();
        f.setIntegerExpression(integerExpression);
        return f;
    }
    static Str f_str(
            Expression floatExpression){
        Str f = new Str();
        f.setFloatExpression(floatExpression);
        return f;
    }
    static Str f_str(
            Expression floatExpression,
            Integer length){
        Str f = new Str();
        f.setFloatExpression(floatExpression);
        f.setLength(length);
        return f;
    }
    static Str f_str(
            Expression floatExpression,
            Integer length,
            Integer decimal){
        Str f = new Str();
        f.setFloatExpression(floatExpression);
        f.setLength(length);
        f.setDecimal(decimal);
        return f;
    }
    static String_Agg f_string_agg(
            Expression expression,
            Expression separator){
        String_Agg f = new String_Agg();
        f.setExpression(expression);
        f.setSeparator(separator);
        return f;
    }
    static String_Agg f_string_agg(
            Expression expression,
            Expression separator,
            OrderBy.Item orderBy){
        String_Agg f = new String_Agg();
        f.setExpression(expression);
        f.setSeparator(separator);
        f.setOrderBy(orderBy);
        return f;
    }
    static String_Escape f_string_escape(
            Expression text,
            String_Escape.Type type){
        String_Escape f = new String_Escape();
        f.setText(text);
        f.setType(type);
        return f;
    }
    static String_Split f_string_split(
            Expression string,
            Expression separator){
        String_Split f = new String_Split();
        f.setString(string);
        f.setSeparator(separator);
        return f;
    }
    static Stuff f_stuff(
            Expression characterExpression,
            Expression start,
            Expression length,
            Expression replaceWithExpression){
        Stuff f = new Stuff();
        f.setCharacterExpression(characterExpression);
        f.setStart(start);
        f.setLength(length);
        f.setReplaceWithExpression(replaceWithExpression);
        return f;
    }
    static SubString f_substring(
            Expression expression,
            Expression start,
            Expression length){
        SubString f = new SubString();
        f.setExpression(expression);
        f.setStart(start);
        f.setLength(length);
        return f;
    }
    static Translate f_translate(
            Expression inputString,
            Expression characters,
            Expression translations){
        Translate f = new Translate();
        f.setInputString(inputString);
        f.setCharacters(characters);
        f.setTranslations(translations);
        return f;
    }
    static Trim f_trim(
            Expression characters,
            Expression string){
        Trim f = new Trim();
        f.setCharacters(characters);
        f.setString(string);
        return f;
    }
    static Trim f_trim(
            Expression string){
        Trim f = new Trim();
        f.setString(string);
        return f;
    }
    static Unicode f_unicode(
            Expression ncharacterExpression){
        Unicode f = new Unicode();
        f.setNcharacterExpression(ncharacterExpression);
        return f;
    }
    static Upper f_upper(
            Expression characterExpression){
        Upper f = new Upper();
        f.setCharacterExpression(characterExpression);
        return f;
    }
}
