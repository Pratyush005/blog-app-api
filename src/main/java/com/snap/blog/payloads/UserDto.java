package com.snap.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer userId;

    @NotBlank
    @Size(min = 4, message = "User name must be 4 character !!")
    private String userName;

    @Email(message = "Email address is not valid!!!")
    private String email;

    @NotBlank
    @Size(min = 3, max = 10, message = "Passowrd must be min 3 character and maximum 10 characters")
    private String password;

    @NotBlank
    private String about;
    
}
