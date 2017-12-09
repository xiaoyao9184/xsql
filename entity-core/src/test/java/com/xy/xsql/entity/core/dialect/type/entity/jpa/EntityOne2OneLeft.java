package com.xy.xsql.entity.core.dialect.type.entity.jpa;

import javax.persistence.*;

/**
 * Created by xiaoyao9184 on 2017/10/1
 */
@Entity
@Table(name = "entity_one_2_one_left")
public class EntityOne2OneLeft {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @OneToOne(targetEntity = EntityOne2OneRight.class)
    @Column(name = "right_id")
    private String rightId;

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

    public String getRightId() {
        return rightId;
    }

    public void setRightId(String rightId) {
        this.rightId = rightId;
    }
}
