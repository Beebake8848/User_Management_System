package com.bibek.service;

import com.bibek.model.UserDtls;

public interface UserService {
    public UserDtls createUser(UserDtls user);

    public boolean checkEmail(String email);

}
