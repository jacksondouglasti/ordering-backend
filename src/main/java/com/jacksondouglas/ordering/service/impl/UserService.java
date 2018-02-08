package com.jacksondouglas.ordering.service.impl;

import com.jacksondouglas.ordering.security.UserSS;
import com.jacksondouglas.ordering.service.exception.AuthorizationException;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {

    public static UserSS authenticated() {
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new AuthorizationException("Access denied");
        }
    }
}
