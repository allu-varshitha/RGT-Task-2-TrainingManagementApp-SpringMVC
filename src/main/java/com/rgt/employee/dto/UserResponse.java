package com.rgt.employee.dto;

import com.rgt.employee.enums.Role;
import lombok.Data;

@Data
public class UserResponse {
    private Long userId;
    private String userName;
    private Role role;
}
