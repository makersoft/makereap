/*
 * @(#)LoggerUtils.java 2013-1-21 下午6:03:56
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.log;

/**
 * Logging utility methods
 */
public class LoggerUtils {

    /**
     * Formats messages using parameters. For example, the call:
     * 
     * <pre>
     * format("foo #1", "bob");
     * </pre>
     * 
     * will return:
     * <pre>
     * foo bob
     * </pre>
     * 
     * @param msg The message
     * @param args A list of arguments.  A maximum of 10 are supported.
     * @return The formatted string
     */
    public static String format(String msg, Object... args) {
        if (msg != null && msg.length() > 0 && msg.indexOf('#') > -1) {
            StringBuilder sb = new StringBuilder();
            boolean isArg = false;
            for (int x = 0; x < msg.length(); x++) {
                char c = msg.charAt(x);
                if (isArg) {
                    isArg = false;
                    if (Character.isDigit(c)) {
                        int val = Character.getNumericValue(c);
                        if (val >= 0 && val < args.length) {
                            sb.append(args[val]);
                            continue;
                        }
                    }
                    sb.append('#');
                }
                if (c == '#') {
                    isArg = true;
                    continue;
                }
                sb.append(c);
            }
            
            if (isArg) {
                sb.append('#');
            }
            return sb.toString();
        }
        return msg;
        
    }

}