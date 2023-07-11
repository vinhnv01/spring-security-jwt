package com.example.usermanagerment.repository;

import com.example.usermanagerment.dto.request.FilterUserRequest;
import com.example.usermanagerment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nguyễn Vinh
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT u FROM User u WHERE u.email =:email")
    User findByEmail(@Param("email") String email);

    @Query(value = """
            SELECT *
            FROM users u
            WHERE 
                (COALESCE (:#{#req.phoneNumber} ,'') = '' OR phone_number ILIKE CONCAT('%', :#{#req.phoneNumber}, '%'))
            AND 
                (COALESCE (:#{#req.email} ,'') = ''  OR email ILIKE CONCAT('%', :#{#req.email}, '%'))
            AND 
                (COALESCE (:#{#req.address} ,'') = ''  OR address ILIKE CONCAT('%', :#{#req.address}, '%'))
            AND 
                (COALESCE (:#{#req.gender} ,'') = ''  OR gender =  CAST(:#{#req.gender} AS BOOLEAN))
            AND 
                (COALESCE (:#{#req.role} ,'') = ''  OR role =  (:#{#req.role} ))
            """, nativeQuery = true)
    List<User> filterUser(@Param("req") FilterUserRequest request);


    @Query(value = """
            SELECT *
            FROM users u
            WHERE 
                (COALESCE(:keyword, '') = '' OR 
                u.phone_number ILIKE CONCAT('%', :keyword, '%') OR 
                u.email ILIKE CONCAT('%', :keyword, '%') OR 
                u.address ILIKE CONCAT('%', :keyword, '%') OR 
                role ILIKE CONCAT('%', :keyword, '%'))
            """, nativeQuery = true)
    List<User> searchUser(@Param("keyword") String keyword);

}
