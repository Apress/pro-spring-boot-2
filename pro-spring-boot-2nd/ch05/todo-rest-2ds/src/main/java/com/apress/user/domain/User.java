package com.apress.user.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Data
@Entity
public class User {

    @Id
    private String email;
    private String password;
    private String name;
    private String role;
    private Boolean active;
}
