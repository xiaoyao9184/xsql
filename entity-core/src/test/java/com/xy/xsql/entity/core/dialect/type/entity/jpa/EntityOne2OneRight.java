package com.xy.xsql.entity.core.dialect.type.entity.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by xiaoyao9184 on 2017/10/1
 */
@Entity
@Table(name = "entity_one_2_one_right")
public class EntityOne2OneRight {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
