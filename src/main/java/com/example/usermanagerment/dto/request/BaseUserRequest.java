package com.example.usermanagerment.dto.request;

import com.example.usermanagerment.util.Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Nguyễn Vinh
 */
@Setter
@Getter
public abstract class BaseUserRequest {

    private String idUserCurrent;

    @NotBlank(message = "Không để trống")
    private String fullName;

    @Email (message = "Email sai ")
    @NotBlank(message = "Không để trống")
    private String email;

    private String password;

    @Pattern(regexp = "^0[0-9]{9}$", message = "Số điện thoại không hợp lệ")
    private String phoneNumber;

    private String dateOfBirth;

    private Boolean gender;

    @NotBlank(message = "Không để trống")
    private String address;

    private Roles role;

}
