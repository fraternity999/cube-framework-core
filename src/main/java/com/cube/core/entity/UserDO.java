package com.cube.core.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "USER")
public class UserDO {

    @Id
    private String id;

    private String name;
}
