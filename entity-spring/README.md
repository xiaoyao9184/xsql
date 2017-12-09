# 关于JPA的标注支持情况

描述表定义的注解

- @Id
- @Column
- @Transient


描述类型的注解

- @Temporal
- @Enumerated
- @Lob 


描述写入映射

- @GeneratedValue
- @Transient

描述查询映射

- @GeneratedValue
- @Basic
- @Transient

| 注解 | 支持 | 原因 |
| ----- | ----- | ----- |
| @Entity | 支持 | |
| @Table | 支持 | |
|
| @Id | 支持 | |
| @Column | 支持 | |
| @ColumnResult | 应支持 | |
|
| @GeneratedValue | 应支持 | |
| @UniqueConstraint| 应支持 | |
| 
| @Embeddable | 不支持 | 内部实体结构与表结构不一致，与本设计冲突 |
| @Embedded | 不支持 | 同上 |
| @EmbeddedId | 不支持 | 同上 |
|
| @Inheritance | 不支持 | 继承策略涉及到单表对多实体，与本设计冲突 |
| @DiscriminatorColumn | 不支持 | 同上 |
| @DiscriminatorValue | 不支持 | 同上 |
|
| @Enumerated | 不支持 | 表字段类型应该通过JAVA类型获取 |
| @Temporal | 不支持 | 表字段类型应该通过JAVA类型获取 |
| @Lob | 不支持 | 大字段存储应通过字段长度确定 |
| @Basic | 不支持 | 延迟加载破坏实体完整性 |
|
| @Transient | 不支持 | 非表字段不应该出现在实体中 |