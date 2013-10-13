/*
 * Copyright 2002-2006,2009 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.makersoft.mvc.json.util;

import java.util.HashSet;
import java.util.Set;


/**
 * Utility class for text parsing.
 *
 * @author Jason Carreira
 * @author Rainer Hermanns
 * @author tm_jee
 *
 * @version $Date: 2012-02-23 09:40:53 +0100 (Thu, 23 Feb 2012) $ $Id: TextParseUtil.java 1292705 2012-02-23 08:40:53Z lukaszlenart $
 */
public class TextParseUtil {

    /**
     * Returns a set from comma delimted Strings.
     * @param s The String to parse.
     * @return A set from comma delimted Strings.
     */
    public static Set<String> commaDelimitedStringToSet(String s) {
        Set<String> set = new HashSet<String>();
        String[] split = s.split(",");
        for (String aSplit : split) {
            String trimmed = aSplit.trim();
            if (trimmed.length() > 0)
                set.add(trimmed);
        }
        return set;
    }

}
