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

    public boolean insertWord(String word, String description) {
        try {
            Connection con = databases.getConnection();
            Statement st = con.createStatement();
            word = word.toLowerCase();
            String sql = ("INSERT INTO av (word, description, html) VALUES (\"" + word + "\",\"" + description + "\",\"" + description + "\");");
            if (this.searchWord(word).getException() == "false") {
                return false;
            }
            st.execute(sql);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteWord(String word) {
        try {
            Connection con = databases.getConnection();
            Statement st = con.createStatement();
            word = word.toLowerCase();
            String sql = ("DELETE FROM av WHERE word = \"" + word + "\";");
            if (this.searchWord(word).getException() != "false") {
                throw new InvalidWord("Khong tim duoc tu trong tu dien");
            }
            st.execute(sql);
            return true;
        } catch (SQLException e) {
            return false;
        } catch (InvalidWord i) {
            return false;
        }
    }

    public boolean updateWord(String word, String description) {
        try {
            Connection con = databases.getConnection();
            Statement st = con.createStatement();
            word = word.toLowerCase();
            String sql = ("UPDATE av SET description = \"" + description + "\", html = \"" +
                           description + "\" WHERE word = \"" + word + "\";");
            if (this.searchWord(word).getException() != "false") {
                throw new InvalidWord("Khong tim duoc tu trong tu dien");
            }
            st.execute(sql);
            return true;
        } catch (SQLException e) {
            return false;
        } catch (InvalidWord i) {
            return false;
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
                throw new InvalidWord("Khong tim duoc tu trong tu dien");
            }
            word Word = new word(rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getString(4), rs.getString(5));
            rs.close();
            return Word;
        } catch (SQLException e) {
            return new word(e.getMessage());
        } catch (InvalidWord iv) {
            return new word(iv.getMessage());
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
            rs.close();
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
            ArrayList<String> listword = new ArrayList<>();
            while (rs.next()) {
                String Word = rs.getString(2);
                listword.add(Word);
            }

            if (listword.size() == 0) {
                rs.close();
                listword = this.approxiamteSearch(s);
                if (listword.size() == 0) {
                    throw new InvalidWord("The dictionary is not have this word and the approximate of it");
                }
                else {
                    return listword;
                }
            }
            rs.close();
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
