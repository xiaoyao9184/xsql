package com.xy.xsql.entity.core.dialect.type.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by xiaoyao9184 on 2017/9/23
 */
@Entity
@Table(name = "entity_006")
public class Entity006 {

    @Id
    @Column(name = "e_id")
    private String id;

    @Column(name = "e_name")
    private String name;

    private String fullPath;

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

    @Column(name = "e_full_path")
    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }
}
