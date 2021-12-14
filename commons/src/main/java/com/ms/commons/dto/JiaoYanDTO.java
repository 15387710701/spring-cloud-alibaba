package com.ms.commons.dto;

import com.ms.commons.validation.IsPhoneNumber;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class JiaoYanDTO {
    @NotBlank
    private String username;
    @Size(min = 4, max = 10)
    private String password;
    @IsPhoneNumber
    private String phone;
    @Email
    private String email;
}
