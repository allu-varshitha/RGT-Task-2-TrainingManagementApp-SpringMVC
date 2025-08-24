package com.rgt.employee.dto;

import com.rgt.employee.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank(message = "User name cannot be blank")
    private String userName;

    @NotNull(message = "Role is required")
    private Role role;
}
