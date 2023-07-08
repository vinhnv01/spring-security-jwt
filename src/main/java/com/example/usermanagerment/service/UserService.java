package com.example.usermanagerment.service;


import com.example.usermanagerment.dto.request.CreateUserRequest;
import com.example.usermanagerment.dto.request.FilterUserRequest;
import com.example.usermanagerment.dto.request.UpdateUserRequest;
import com.example.usermanagerment.dto.response.UserResponse;
import com.example.usermanagerment.entity.User;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Nguyễn Vinh
 */
public interface UserService {

    List<UserResponse> getAll();

    Page<UserResponse> findAll(Pageable pageable);

    User create(@Valid final CreateUserRequest req);

    User update(@Valid final UpdateUserRequest req);

    User getOneById(String id);

    Boolean delete(String id);

    // Bô xung
    // filter theo phone number , gender , address, role , email
    // search theo  phone number , gender , address, role , email

    List<UserResponse> filter(FilterUserRequest req);
    List<UserResponse> search(String keyword);

}
