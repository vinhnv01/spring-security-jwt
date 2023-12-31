package com.example.usermanagerment.controller;

import com.example.usermanagerment.dto.request.FilterUserRequest;
import com.example.usermanagerment.dto.request.UpdateUserRequest;
import com.example.usermanagerment.dto.response.UserResponse;
import com.example.usermanagerment.entity.User;
import com.example.usermanagerment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<UserResponse>> view() throws Exception {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<UserResponse>> page(@RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 2);
        return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable("id") String id,
                                       @RequestBody UpdateUserRequest req) {
        req.setId(id);
        return ResponseEntity.ok(userService.update(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getOneById(@PathVariable("id") String id) {
        return ResponseEntity.ok(userService.getOneById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
        return ResponseEntity.ok(userService.delete(id));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<UserResponse>> filter(final FilterUserRequest req) {
        return new ResponseEntity<>(userService.filter(req), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserResponse>> search(String keyword) {
        return new ResponseEntity<>(userService.search(keyword), HttpStatus.OK);
    }


}
