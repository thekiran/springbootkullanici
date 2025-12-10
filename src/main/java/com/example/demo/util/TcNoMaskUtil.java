package com.example.demo.util;

public class TcNoMaskUtil {

    public static String mask(String tc) {

        // Null veya hatalı uzunluk kontrolü
        if (tc == null || tc.length() != 11) {
            return tc; // geçersizse maskelenmeden döner
        }

        String start = tc.substring(0, 3);  // İlk 3 hane
        String end = tc.substring(9, 11);   // Son 2 hane

        String stars = "*".repeat(11 - start.length() - end.length());

        return start + stars + end;
    }
}
