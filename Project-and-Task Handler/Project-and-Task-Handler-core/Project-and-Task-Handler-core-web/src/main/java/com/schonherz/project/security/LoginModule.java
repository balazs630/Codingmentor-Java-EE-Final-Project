package com.schonherz.project.security;

import com.schonherz.project.util.PasswordUtil;
import org.jboss.security.auth.spi.DatabaseServerLoginModule;

/**
 *
 * @author Janos Harsanyi <hajani003@gmail.com>
 */
public class LoginModule extends DatabaseServerLoginModule {

    @Override
    protected boolean validatePassword(String inputPassword, String expectedPassword) {
        if (inputPassword == null || expectedPassword == null) {
            return false;
        }
        return PasswordUtil.checkPassword(inputPassword, expectedPassword);
    }

}
