package org.javaacademy.private_party.entity;

import lombok.Data;

@Data
public class Guest {
    private Integer id;
    private String email;
    private String phone;
    private String name;
}
