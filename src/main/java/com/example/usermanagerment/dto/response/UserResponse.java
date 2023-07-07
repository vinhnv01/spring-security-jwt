package com.example.usermanagerment.dto.response;

import com.example.usermanagerment.entity.User;
import com.example.usermanagerment.util.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Nguyễn Vinh
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponse {

    private String id;

    private String fullName;

    private String email;

    private String phoneNumber;

    private Long dateOfBirth;

    private Boolean gender;

    private String address;

    private Roles role;

    public UserResponse(User user) {
        this.id = user.getId();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.dateOfBirth = user.getDateOfBirth();
        this.gender = user.getGender();
        this.address = user.getAddress();
        this.role = user.getRole();
    }
}
