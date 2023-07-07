package com.example.usermanagerment.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Nguyễn Vinh
 */
@Setter
@Getter
public class UpdateUserRequest extends BaseUserRequest {

    private String id;

}
