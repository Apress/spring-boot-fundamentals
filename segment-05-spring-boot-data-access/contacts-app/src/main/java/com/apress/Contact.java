package com.apress;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Contact {

    @Id
    private String email;
    private String name;
    private String phone;
}
