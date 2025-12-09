package com.example.demo.util;

public class PhoneMaskUtil {

    public static String mask(String number) {

        if (number == null || number.length() < 10) {
            return number;
        }

        String trCode = "+90 ";

        String start = number.substring(0, 4); // 0532
        String end = number.substring(number.length() - 4); // 4567

        int middleLength = number.length() - start.length() - end.length();
        String stars = "*".repeat(middleLength);

        return trCode + start + stars + end;
    }
}
