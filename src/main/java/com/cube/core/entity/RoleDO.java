package com.cube.core.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROLE")
@Data
public class RoleDO {

    @Id
    private String id;

    private String name;

    private String code;
}
