package com.rgt.employee.model;

import com.rgt.employee.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity //marks a class as a JPA entity.
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id //primaary key 
    @GeneratedValue(strategy = GenerationType.IDENTITY)// auto increment of ids
    private Long userId;
    private String userName;

    @Enumerated(EnumType.STRING)
    private Role role;
}
