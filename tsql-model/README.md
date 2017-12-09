# Transact-SQL

The chapters of this article are from the 
[MSDN](https://msdn.microsoft.com/en-us/library/bb510741.aspx) 
`Transact-SQL Reference` directory


## Reserved Keywords

Only SQLServer 

## Syntax Conventions

don't need

## BACKUP and RESTORE Statements

to be evaluated

## Built-in Functions

later

## COLLATE 
| Clause | Support |
| ----- | ----- |
| COLLATE | YES |

| Type | Support |
| ----- | ----- |
| SQL Server Collation Name | NO |
| Windows Collation Name | NO |
| Collation Precedence | NOT |

## Control-of-Flow Language

to be evaluated

## Cursors

to be evaluated

## DDL


| 语句 | 支持程度 |
| ----- | ----- |
| ALTER |
| CREATE |
| DISABLE TRIGGER
| DROP |
| ENABLE TRIGGER |
| TRUNCATE TABLE | ALL |
| UPDATE STATISTICS |
| RENAME | ALL |

## DML

| Statement | Support |
| ----- | ----- |
| BULK INSERT | ALL |
| SELECT | NOT XMLNAMESPACES, column_position 
| DELETE | NOT rowset_function_limited
| UPDATE | NOT rowset_function_limited, udt_column_name, WRITE
| INSERT | NOT rowset_function_limited, execute_statement, <dml_table_source>  
| UPDATETEXT | _ |
| MERGE | ALL |
| WRITETEXT | _ |
| READTEXT | _ |


| Clause | Support |
| ----- | ----- |
| FROM | ONLY table_or_view_name, derived_table, <joined_table>
| Hints | Query,Table,Join |
| OPTION Clause | ALL |
| OUTPUT Clause | ALL |
| Search Condition | ALL |
| Table Value Constructor | ALL |
| TOP | ALL |
| WHERE | ALL |
| WITH common_table_expression | ALL |

| SELECT Clause | Support |
| ----- | ----- |
| SELECT | NOT $IDENTITY, $ROWGUID, udt_column_name |
| FOR | ALL |
| GROUP BY | NOT For backward |
| HAVING | ALL |
| INTO | YES, but model not use |
| ORDER BY | NOT COLLATE collation_name |
| OVER | ALL |


| Other | Support |
| ----- | ----- |
| EXPLAIN | _ |
| Subqueries | use SELECT |

## Data Types

Only SQL Server system data type, user-defined type not support
| Name | Support |
| ----- | ----- |
| Constants | String,Number,Binary |
| bit | YES |
| cursor | CANT |
| Date and Time Types | YES |
| hierarchyid | YES |
| Numeric Types | YES |
| rowversion | YES |
| String and Binary Types | NOT max |
| Spatial Types
| sql_variant |
| table
| uniqueidentifier | YES
| xml


## EXECUTE 

to be evaluated

## Expressions
| Type | Support |
| ----- | ----- |
| constant | String,Number |
| scalar_function  |  |
| [ table_name. ] column | NOT table_name name |
| variable | YES |
| ( expression ) | YES |
| ( scalar_subquery ) | SELECT |
| { unary_operator } expression |
| expression { binary_operator } expression | YES |
| ranking_windowed_function
| aggregate_windowed_function 

| Type | Support |
| ----- | ----- |
| AT TIME ZONE | YES |
| CASE | YES |
| Coalesce | YES |
| NullIf | YES |
 
## Language Elements

| Other | Support |
| ----- | ----- |
| -- (Comment) | OK |
| Slash Star Comment | OK |
| CREATE DIAGNOSTICS SESSION | NOT |
| NULL and UNKNOWN | NOT |
| Transactions |
| USE |

## Management Commands

to be evaluated

## Operators

| Operator Type | Support |
| ----- | ----- |
| Arithmetic | YES |
| Assignment | YES |
| Bitwise | YES |
| Comparison | YES |
| Compound | YES |
| Logical | YES |
| Scope_Resolution | YES |
| Set | YES |
| String | +, += |
| Unary | YES |

`Operator Precedence` is not support

## Predicates

| Name | Support |
| ----- | ----- |
| Between | YES |
| Comparison | YES |
| ComparisonSubQuery | YES |
| Contains | YES |
| Exists | YES |
| FreeText | YES |
| In | YES |
| IsNull | YES |
| Like | YES |

## PRINT
to be evaluated
## RAISERROR
to be evaluated
## Security Statements 
to be evaluated
## Service Broker Statements
to be evaluated
## SET Statements
to be evaluated
## SQL Server Utilities Statements
to be evaluated
## System Stored Functions
to be evaluated
## System Stored Procedures
to be evaluated
## System Tables
to be evaluated
## Transaction Statements
to be evaluated

## Variables

Only @local_variable,
@cursor_variable NOT,
@table_variable_name MAYBE later

| Name | Support |
| ----- | ----- |
| Declare | Only local_variable |
| Select | YES |
| Set | Only simple local_variable |

## XML Statements

to be evaluated
