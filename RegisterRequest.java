package com.project.BTS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String phone;
    
}