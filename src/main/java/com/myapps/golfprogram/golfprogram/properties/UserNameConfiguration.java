/*
 * File designed to read from a file in the classpath userconfig.properties.
 * Stored in this property is the username stored in the config file. 
 */
package com.myapps.golfprogram.golfprogram.properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 *
 * @author Me
 */
@Configuration
@PropertySource(name = "userconfig", value = "classpath:userconfig.properties")
public class UserNameConfiguration
{
    public static String getValueFromConfigFile()
    {
        String username = StringUtils.EMPTY;
        final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        try
        {
            ctx.register(UserNameConfiguration.class);
            ctx.refresh();
            final Environment env = ctx.getEnvironment();
            username = env.getProperty("name");

        }
        finally
        {
            ctx.close();
        }
        return username;
    }

}