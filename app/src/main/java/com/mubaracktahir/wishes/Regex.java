package com.mubaracktahir.wishes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    static Pattern pt;
    static String regrex;
    static boolean isItValidEmail(String str){
        boolean match = false;
        regrex ="^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{3,3}$";
        pt = Pattern.compile(regrex);

        Matcher matcher = pt.matcher(str);
        if (matcher.matches())
            match = true;
        return match;
    }
}
