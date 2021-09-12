package com.codingtest.message.auth;

import java.util.Optional;

public interface ApplicationUserDao {
    Optional<ApplicationUserDetails> fetchAccountByUsername(String username);
}
