package com.example.usermanagerment.service.impl;


import com.example.usermanagerment.dto.request.CreateUserRequest;
import com.example.usermanagerment.dto.request.FilterUserRequest;
import com.example.usermanagerment.dto.request.UpdateUserRequest;
import com.example.usermanagerment.dto.response.UserResponse;
import com.example.usermanagerment.entity.User;
import com.example.usermanagerment.repository.UserRepository;
import com.example.usermanagerment.service.UserService;
import com.example.usermanagerment.util.Roles;
import com.example.usermanagerment.util.error.NotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Nguyễn Vinh
 */
@Service
@Validated
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public List<UserResponse> getAll() {
        List<UserResponse> responses = new ArrayList<>();
        List<User> list = userRepository.findAll();
        for (User user : list) {
            responses.add(new UserResponse(user));
        }
        return responses;
    }


    @Override
    public Page<UserResponse> findAll(Pageable pageable) {
        List<UserResponse> responses = new ArrayList<>();
        Page<User> list = userRepository.findAll(pageable);
        for (User user : list.toList()) {
            responses.add(new UserResponse(user));
        }
        return new PageImpl<>(responses, pageable, list.getTotalElements());
    }

    @Override
    public User create(@Valid CreateUserRequest req) {

        System.out.println(req.getIdUserCurrent());
        Optional<User> optional = userRepository.findById(req.getIdUserCurrent());
        if (optional.get().getRole() != Roles.MENTOR) {
            throw new NotFoundException("Không có quyền");
        }
        // check email tồn tại
        User chekcEmail = userRepository.findByEmail(req.getEmail());
        if (chekcEmail != null) {
            throw new RuntimeException();
        }
        User add = new User(req);
        add.setPassword(new BCryptPasswordEncoder().encode(add.getPassword()));
        return userRepository.save(add);
    }

    @Override
    public User update(@Valid UpdateUserRequest req) {
        Optional<User> optional = userRepository.findById(req.getId());
        if (!optional.isPresent()) {
            throw new NotFoundException("Không tìm thấy user");
        }
        if (!optional.get().getId().equals(req.getIdUserCurrent())) {
            throw new NotFoundException("Không có quyền");
        }
        User update = new User(req);
        update.setPassword(passwordEncoder.encode(update.getPassword()));
        return userRepository.save(update);
    }

    @Override
    public User getOneById(String id) {
        Optional<User> optional = userRepository.findById(id);
        if (!optional.isPresent()) {
            throw new NotFoundException("Không tìm thấy user");
        }
        return optional.get();
    }

    @Override
    public Boolean delete(String id) {
        Optional<User> optional = userRepository.findById(id);
        if (!optional.isPresent()) {
            throw new NotFoundException("Không tìm thấy user");
        }
        userRepository.delete(optional.get());
        return true;
    }

    @Override
    public List<UserResponse> filter(FilterUserRequest req) {

        List<UserResponse> responses = new ArrayList<>();
        List<User> list = userRepository.filterUser(req);
        for (User user : list) {
            responses.add(new UserResponse(user));
        }
        return responses;
    }

    @Override
    public List<UserResponse> search(String keyword) {
        List<UserResponse> responses = new ArrayList<>();
        List<User> list = userRepository.searchUser(keyword);
        for (User user : list) {
            responses.add(new UserResponse(user));
        }
        return responses;
    }
}
