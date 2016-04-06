package org.yagna.samples.jwt.service;

import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by asish on 4/4/16.
 */
@Service
public class UserService implements UserDetailsService {

    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();
    private final HashMap<String, User> userMap = new HashMap<String, User>();

    public final User loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userMap.get(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        detailsChecker.check(user);
        return user;
    }

    public void addUser(User user) {
        userMap.put(user.getUsername(), user);
    }
}
