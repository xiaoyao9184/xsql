# Transact-SQL

## Reserved Keywords

Only SQLServer 

## Syntax Conventions

dont need

## BACKUP and RESTORE Statements

TODO

## Built-in Functions

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
## Cursors

## DDL


| 语句 | 支持程度 |
| ----- | ----- |
| ALTER |
| CREATE |
| DISABLE TRIGGER
| DROP |
| ENABLE TRIGGER |
| TRUNCATE TABLE |
| UPDATE STATISTICS |
| RENAME | ALL |

## DML

| 语句 | 支持程度 |
| ----- | ----- |
| BULK INSERT | ALL |
| SELECT |
| DELETE |
| UPDATE |
| INSERT |
| UPDATETEXT | NOT |
| MERGE |
| WRITETEXT | NOT |
| READTEXT | NOT |


| Clause | 支持程度 |
| ----- | ----- |
| FROM |
| Hints | Query,Table,Join |
| OPTION Clause | ALL |
| OUTPUT Clause | ALL |
| Search Condition | ALL |
| Table Value Constructor | ALL |
| TOP | ALL |
| WHERE | ALL |
| WITH common_table_expression | ALL |


| Other | 支持程度 |
| ----- | ----- |
| EXPLAIN
| Subqueries | use SELECT |

## Data Types

Only SQL Server system data type, user-defined type not support
| Name | Support |
| ----- | ----- |
| Constants | String,Number |
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

## Expressions
| Type | Support |
| ----- | ----- |
| constant | String,Number |
| scalar_function  |  |
| [ table_name. ] column | NOT table_name alias |
| variable | YES |
| ( expression ) | |
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
## RAISERROR
## Security Statements 
## Service Broker Statements
## SET Statements
## SQL Server Utilities Statements
## System Stored Functions
## System Stored Procedures
## System Tables
## Transaction Statements
## Variables

Yea

## XML Statements
