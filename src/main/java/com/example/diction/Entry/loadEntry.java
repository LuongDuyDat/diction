package com.example.diction.Entry;


import com.example.diction.API.GoogleTranslateAPI;
import com.example.diction.Exception.GoogleTranslateException;
import com.example.diction.Exception.InvalidWord;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Locale;

public class loadEntry {

    private static JDBCConnection databases;
    private static GoogleTranslateAPI translation;

    public loadEntry() {
        databases = new JDBCConnection();
        translation = new GoogleTranslateAPI();
    }

    public void insertWord(String word, String description) {
        try {
            Connection con = databases.getConnection();
            Statement st = con.createStatement();
            word = word.toLowerCase();
            String sql = ("INSERT INTO av (word, description, html) VALUES (\"" + word + "\",\"" + description + "\",\"" + description + "\");");
            System.out.println(sql);
            st.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String deleteWord(String word) {
        try {
            Connection con = databases.getConnection();
            Statement st = con.createStatement();
            word = word.toLowerCase();
            String sql = ("DELETE FROM av WHERE word = \"" + word + "\";");
            if (!st.execute(sql)) {
                throw new InvalidWord("Khong tim duoc tu trong tu dien");
            }
            else {
                return "Hop le";
            }
        } catch (SQLException e) {
            return e.getMessage();
        } catch (InvalidWord i) {
            return i.getMessage();
        }
    }

    public String updateWord(String word, String description) {
        try {
            Connection con = databases.getConnection();
            Statement st = con.createStatement();
            word = word.toLowerCase();
            String sql = ("UPDATE av SET description = \"" + description + "\", html = \"" +
                           description + "\" WHERE word = \"" + word + "\";");
            if (!st.execute(sql)) {
                throw new InvalidWord("Khong tim duoc tu trong tu dien");
            }
            else {
                return "Hop le";
            }
        } catch (SQLException e) {
            return e.getMessage();
        } catch (InvalidWord i) {
            return i.getMessage();
        }
    }

    public word searchWord(String s) {
        String translated = null;
        s.trim();
        try {
            Connection con = databases.getConnection();
            Statement st = con.createStatement();
            s = s.toLowerCase();
            String sql = ("SELECT * FROM av WHERE word = \"" + s + "\";");
            ResultSet rs = st.executeQuery(sql);
            if (!rs.next()) {
                translated = translation.translate("en", "vi", s);
                throw new GoogleTranslateException("Use Google Translation");
            }
            word Word = new word(rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getString(4), rs.getString(5));
            return Word;
        } catch (SQLException e) {
            return new word(e.getMessage());
        } catch (GoogleTranslateException g) {
            return new word(s, translated, g.getMessage());
        } catch (IOException i) {
            return new word(i.getMessage());
        }
    }

    public ArrayList<String> approxiamteSearch(String s) {
        ArrayList<String> candidates = approximateWord.approximate(s);
        ArrayList<String> result = new ArrayList<>();
        try {
            Connection con = databases.getConnection();
            Statement st = con.createStatement();
            s = s.toLowerCase();
            String sql = ("SELECT * FROM av WHERE ");
            for (int i = 0; i < candidates.size() - 1; i++) {
                sql += "\"word\" LIKE \"%" + candidates.get(i) + "%\"" + "OR";
            }
            sql += "\"word\" LIKE \"%" + candidates.get(candidates.size() - 1) + "%\";";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                result.add(rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public ArrayList<String> hintSearch(String s) {
        try {
            s.trim();
            Connection con = databases.getConnection();
            Statement st = con.createStatement();
            s = s.toLowerCase();
            String sql = ("SELECT * FROM av WHERE word LIKE \"" + s + "%\"ORDER BY word LIMIT 100;");
            ResultSet rs = st.executeQuery(sql);
            if (!rs.next()) {
                ArrayList<String> listword = this.approxiamteSearch(s);
                if (listword.size() == 0) {
                    throw new InvalidWord("The dictionary is not have this word and the approximate of it");
                }
                else {
                    return listword;
                }
            }
            ArrayList<String> listword = new ArrayList<>();
            while (rs.next()) {
                String Word = rs.getString(2);
                listword.add(Word);
            }
            return listword;
        } catch (SQLException e) {
            ArrayList<String> listword = new ArrayList<>();
            listword.add(e.getMessage());
            return listword;
        } catch (InvalidWord i) {
            ArrayList<String> listword = new ArrayList<>();
            listword.add(i.getMessage());
            return listword;
        }
    }
}
