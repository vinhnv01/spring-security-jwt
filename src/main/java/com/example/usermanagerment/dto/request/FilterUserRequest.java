package com.example.usermanagerment.dto.request;

import com.example.usermanagerment.util.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FilterUserRequest {

    private String phoneNumber;

    private String gender;

    private String address;

    private String role;

    private String email;
}
