package com.userfront.service;

import java.util.List;
import java.util.Set;

import com.userfront.domain.User;
import com.userfront.domain.security.UserRole;

public interface UserService {
	
	User findByUsername(String username);

    User findByEmail(String email);
    
    List<User> findUserList();

    boolean checkUserExists(String username, String email);

    boolean checkUsernameExists(String username);

    boolean checkEmailExists(String email);
    
    User createUser(User user, Set<UserRole> userRoles);
    
    void save (User user);
    
    User saveUser (User user); 
    
    void enableUser (String username);

    void disableUser (String username);
    
}
