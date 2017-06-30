/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author khang
 */
public class ValidationAndNormalizingText {

    public static String removeUnnecessaryBlank(String input) {
        return input.trim().replaceAll("\\s+", " ");
    }

    public static String removeAllBlank(String input) {
        return input.trim().replaceAll("\\s+", "");
    }

    public static String normalFormName(String input) {
        input = removeUnnecessaryBlank(input);
        String temp[] = input.split(" ");
        input = "";
        for (int i = 0; i < temp.length; i++) {
            input += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
            if (i < temp.length - 1) {
                input += " ";
            }
        }
        return input;
    }

    public static String normalFormStringAfterDot(String input) {
        String output = "";
        input = removeUnnecessaryBlank(input);
        input = String.valueOf(input.charAt(0)).toUpperCase() + input.substring(1);

        input = input.replaceAll("\\.\\s+", "\\.").replaceAll("\\s+\\.", "\\.");
        output += input.charAt(0);
        for (int i = 1; i < input.length(); i++) {

            if (input.charAt(i - 1) == '.' && Character.isAlphabetic(input.charAt(i))) {
                output += " " + Character.toUpperCase(input.charAt(i));
            } else {
                output += input.charAt(i);
            }
        }
        return output;
    }

    public static String validAndNomalPhoneNumber(String phoneNum, String splitChar) throws Exception {
        phoneNum = removeAllBlank(phoneNum);
        String out = "";
        System.out.println(phoneNum);
        if (!phoneNum.matches("[0-9]+")) {
            throw new Exception("Phone number must be contain digit only");
        }

        if (phoneNum.length() < 10 || phoneNum.length() > 15) {
            throw new Exception("Phone number length must be from 10 and 15 digits");
        }

        if (!(phoneNum.startsWith("0") || phoneNum.startsWith("84"))) {
            throw new Exception("Phone number invalid, must be start with 0 or 84");
        }

        if (phoneNum.startsWith("84") && phoneNum.length() < 11) {
            throw new Exception("Phone number invalid,length of phone number start with 84 must be from 11 digits");
        }
        out += phoneNum.subSequence(0, 4).toString();
        int count = 1;
        for (int i = 0; i < (phoneNum.length() - 4) / 3; i++) {
            out += splitChar + phoneNum.subSequence(4 + i * 3, 4 + i * 3 + 3);
            count++;
        }
        if (phoneNum.length() - count * 3 - 1 != 0)
            out += splitChar + phoneNum.subSequence(count * 3 + 1, count * 3 + 1 + (phoneNum.length() - 4) % 3);
        return out;
    }

    public static String validationEmail(String email) {
        final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        email = removeAllBlank(email);
        if (email.matches(EMAIL_REGEX)) {
            return email;
        } else {
            return "";
        }
    }

    public static String validationDate(String date, String format) {
        try {
            new SimpleDateFormat(format).parse(date);
            return date;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return format;
        }
    }

    public static void main(String[] args) {
        System.out.println(validationDate(" 21 /09 / 1992 ","dd/MM/yyyy"));
    }

}
