package com.userfront.service;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.userfront.dao.RoleDao;
import com.userfront.dao.UserDao;
import com.userfront.domain.User;
import com.userfront.domain.security.UserRole;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private AccountService accountService;
	
	public void save(User user) {
        userDao.save(user);
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
    
    public boolean checkUserExists(String username, String email){
        if (checkUsernameExists(username) || checkEmailExists(username)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkUsernameExists(String username) {
        if (null != findByUsername(username)) {
            return true;
        }

        return false;
    }
    
    public boolean checkEmailExists(String email) {
        if (null != findByEmail(email)) {
            return true;
        }

        return false;
    }
    
    public User createUser(User user, Set<UserRole> userRoles) {
        System.out.println("entree create");
    	User localUser = userDao.findByUsername(user.getUsername());
    	System.out.println("ligne 75");
        if (localUser != null) {
        	System.out.println("ligne 77");
        	LOG.info("User with username {} already exist. Nothing will be done. ", user.getUsername());
        } else {
            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);
            System.out.println("ligne 82");

            for (UserRole ur : userRoles) {
                roleDao.save(ur.getRole());
            }
            System.out.println("ligne 87");

            user.getUserRoles().addAll(userRoles);
            System.out.println("ligne 90");

            user.setPrimaryAccount(accountService.createPrimaryAccount());
            System.out.println("ligne 93");
            user.setSavingsAccount(accountService.createSavingsAccount());
            System.out.println("ligne 95");

            localUser = userDao.save(user);
            System.out.println("ligne 98");
        }

        return localUser;
    }
    
    public User saveUser (User user) {
        return userDao.save(user);
    }
    
    public List<User> findUserList() {
        return userDao.findAll();
    }
    
    public void enableUser (String username) {
        User user = findByUsername(username);
        user.setEnabled(true);
        userDao.save(user);
    }

    public void disableUser (String username) {
    	System.out.println("entree disableUser : " + username);
    	User user = findByUsername(username);
    	System.out.println("121 : " + username);
        user.setEnabled(false);
        System.out.println(user.isEnabled());
        userDao.save(user);
        System.out.println(username + " is disabled.");
    }
	
}
