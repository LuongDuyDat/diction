package com.example.diction.Entry;


import java.util.ArrayList;

public class approximateWord {
    public static ArrayList<String> approximate(String s) {
        ArrayList<String> listwords = new ArrayList<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            listwords.add(s.substring(0, i) + "_" + s.substring(i, n));
            listwords.add(s.substring(0, i) + s.substring(i + 1, n));
            listwords.add(s.substring(0, i) + "_" + s.substring(i + 1, n));
        }
        return listwords;
    }
}
