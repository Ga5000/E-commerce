package com.ga5000.api.ecommerce.utils;

import com.ga5000.api.ecommerce.domain.user.User;
import com.ga5000.api.ecommerce.domain.user.utils.Role;

public class AuthUtil {
    public static boolean IsAdmin(User user) {
        return user.getRole().equals(Role.ADMIN);
    }
}
