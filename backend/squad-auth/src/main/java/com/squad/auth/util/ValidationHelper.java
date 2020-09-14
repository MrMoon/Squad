package com.squad.auth.util;

import org.jetbrains.annotations.NotNull;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationHelper {

    public static boolean validateEmailFormat(String email) {
        return Pattern.compile("^(.+)@(.+)$").matcher(email).matches();
    }

    public static boolean hasOnlyAlphanumericCharacters(String text) {
        return Pattern.compile("^[a-zA-Z0-9- ]+$").matcher(text).matches();
    }

    public static @NotNull Date convertStringToDate(String date , String format) throws ParseException {
        java.util.Date utilDate = new SimpleDateFormat(format).parse(date);
        return new Date(utilDate.getTime());
    }

    public static String convertDateToString(java.util.Date date , String format) {
        return date != null ? new SimpleDateFormat(format).format(date) : null;
    }

    public static boolean isValidDate(String date , String format) {
        boolean valid = false;
        try {
            LocalDate.parse(date , DateTimeFormatter.ofPattern(format));
            valid = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valid;
    }

    public static boolean isStateValid(String state) {
        Matcher matcher = Pattern.compile("AL|AK|AR|AZ|CA|CO|CT|DC|DE|FL|GA|HI|IA|ID|IL|IN|KS|KY|LA|MA|MD|ME|MI|MN|MO|MS|MT|NC|ND|NE|NH|NJ|NM|NV|NY|OH|OK|OR|PA|RI|SC|SD|TN|TX|UT|VA|VT|WA|WI|WV|WY|al|ak|ar|az|ca|co|ct|dc|de|fl|ga|hi|ia|id|il|in|ks|ky|la|ma|md|me|mi|mn|mo|ms|mt|nc|nd|ne|nh|nj|nm|nv|ny|oh|ok|or|pa|ri|sc|sd|tn|tx|ut|va|vt|wa|wi|wv|wy").matcher(state);
        return matcher.matches();
    }

    public static boolean hasSqllInjectionCharacters(String text) {
        return Pattern.compile("^[^\\'\\{\\}\\\\\\;\\$]*$").matcher(text).matches();
    }

}
