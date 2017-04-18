package com.schonherz.project.producer;

import java.util.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * Created by darvasr on 2016.09.11..
 */
public class LoggerProducer {

    @Produces
    public Logger loggerProducer(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().toString());
    }
}
