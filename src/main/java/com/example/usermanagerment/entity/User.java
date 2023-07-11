package com.example.usermanagerment.entity;

import com.example.usermanagerment.dto.request.CreateUserRequest;
import com.example.usermanagerment.dto.request.UpdateUserRequest;
import com.example.usermanagerment.util.Roles;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Nguyễn Vinh
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_of_birth")
    private Long dateOfBirth;

    @Column(name = "gender")
    private Boolean gender;

    @Column(name = "address")
    private String address;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Roles role;

    public User(CreateUserRequest req) {
        this.fullName = req.getFullName();
        this.email = req.getEmail();
        this.password = req.getPassword();
        this.phoneNumber = req.getPhoneNumber();
        this.dateOfBirth = convertDateToLong(req.getDateOfBirth());
        this.gender = req.getGender();
        this.address = req.getAddress();
        this.role = req.getRole();
    }

    public User(UpdateUserRequest req) {
        this.id = req.getId();
        this.fullName = req.getFullName();
        this.email = req.getEmail();
        this.password = req.getPassword();
        this.phoneNumber = req.getPhoneNumber();
        this.dateOfBirth = convertDateToLong(req.getDateOfBirth());
        this.gender = req.getGender();
        this.address = req.getAddress();
        this.role = req.getRole();
    }

    private Long convertDateToLong(String date) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-YYYY");
        long milliseconds = 0;
        try {
            Date d = format.parse(date);
            milliseconds = d.getTime();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return milliseconds;
    }
}
