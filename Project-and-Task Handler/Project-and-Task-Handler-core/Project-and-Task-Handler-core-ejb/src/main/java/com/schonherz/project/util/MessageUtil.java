package com.schonherz.project.util;

import java.util.Objects;
import javax.interceptor.InvocationContext;

/**
 * Created by darvasr on 2016.09.17..
 */
public class MessageUtil {

    public static String createLogMessage(InvocationContext context) {
        StringBuilder message = new StringBuilder();
        Object[] parameters = context.getParameters();
        if (parameters != null) {
            for (Object parameter : parameters) {
                message.append(Objects.toString(parameter)).append(" ");
            }
        }
        return message.toString();
    }
}
