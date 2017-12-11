package com.example.demo.listener;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Component
public class WrongPassListener {

    private static final int MAX_NUMBER_OF_USER_LOGIN_ATTEMPTS = 3;
    private UserRepository userRepository;

    @Autowired
    public WrongPassListener(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventListener
    public void saveFailedLoginAttempt(AuthenticationFailureBadCredentialsEvent badCredentialsEvent) {

        String userEmail = badCredentialsEvent
                .getAuthentication()
                .getPrincipal().toString();
        User userByEmail = userRepository.findByEmail(userEmail);

        if(isInDB(userByEmail)) {
            validateUserAttempts(userByEmail);
            userRepository.save(userByEmail);
        }
    }

    private boolean isInDB(User userByEmail) {
        return userByEmail != null;
    }

    private void validateUserAttempts(User userByEmail) {
        int loginAttempts = userByEmail.getLoginAttempts();

        userByEmail.setLoginAttempts(loginAttempts++);
        if (loginAttempts > MAX_NUMBER_OF_USER_LOGIN_ATTEMPTS) {
            userByEmail.setLocked(true);
        }

        userByEmail.setLoginAttempts(loginAttempts);
    }
}
