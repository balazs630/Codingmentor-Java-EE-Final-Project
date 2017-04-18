package com.schonherz.project.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Janos Harsanyi <hajani003@gmail.com>
 */
public class PasswordUtil {

    public static final int DEFAULT_WORK_FACTOR = 12;

    public static final String WORK_FACTOR_PARAM_NAME = "work-factor";
    
    public static String hashPassword(String password, int workFactor) {
        return BCrypt.hashpw(password, BCrypt.gensalt(workFactor));
    }

    public static boolean checkPassword(String plaintext, String hashed) {
        return BCrypt.checkpw(plaintext, hashed);
    }
}
