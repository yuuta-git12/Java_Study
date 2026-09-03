package org.example;

public class Main_t2_q19 {
    public static void main(String[] args){
        StringBuilder s1 = new StringBuilder("Hey").append("!!");
        String s2 = "Hello";
        Main_t2_q19 m = new Main_t2_q19();
        System.out.println(m.method(s1.toString(),s2));
        System.out.println(m.method(s1,s2));
        System.out.println(m.method(s2,s1));
    }

    String method(StringBuilder sb, String s) {       // (A)
        String tmp = sb.toString();
        return toShortLetter(tmp.startsWith("H")
                && s.startsWith("H"));
    }
    String method(String s, StringBuilder sb) {       // (B)
        String tmp = sb.toString();
        return toShortLetter(s.contains("!")
                && tmp.contains("!"));
    }
    String method(Object s1, Object s2) {             // (C)
        return toShortLetter(s1.toString().length()
                == s2.toString().length());
    }
    private String toShortLetter(boolean b) {         // (D)
        return b ? "T" : "F";
    }

}
