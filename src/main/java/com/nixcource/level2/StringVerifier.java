package com.nixcource.level2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class StringVerifier {
    private final Vector<Character> delimiters;
    private String data;

    public StringVerifier(String data) {
        this.data = data;
        this.delimiters = new Vector<>();
        for (char delimiter : "{}[]()".toCharArray()) {
            this.delimiters.add(delimiter);
        }
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private boolean isSymmetric(ArrayList<Character> data) {
        HashMap<Character, Character> tempDelimiters = new HashMap<>();
        tempDelimiters.put('{', '}');
        tempDelimiters.put('(', ')');
        tempDelimiters.put('[', ']');

        for (int i = 0; i < data.size() / 2; ++i) {
            if (tempDelimiters.get(data.get(i)) != data.get(data.size() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public boolean isValid() {
        ArrayList<Character> tempSymbols = new ArrayList<>();

        for (int i = 0; i < this.data.length(); ++i) {
            if (delimiters.contains(this.data.charAt(i))) {
                tempSymbols.add(this.data.charAt(i));
            }
        }

        if (tempSymbols.isEmpty()) {
            return true;
        } else if (tempSymbols.size() % 2 == 0) {
            return isSymmetric(tempSymbols);
        }

        return false;
    }
}
