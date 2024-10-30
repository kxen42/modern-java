package org.fotm.java17.misc;

public class TextBlocks {

    public static void main(String[] args) {
        String sName = "Sean Kennedy";
        String tbName = """
                Sean Kennedy""";
        String tbName2 = """
                Sean Kennedy
                """;

        // gotta use parens because + has higher precedence
        System.out.println("sName.equals(tbName) should be true: " + (sName.equals(tbName)));
        System.out.println("sName == tbName should be true: " + (sName == tbName));
        System.out.println("sName.length() == tbName.length should be true: " +( sName.length() == tbName.length()));

        // gotta use parens because + has higher precedence
        System.out.println("sName.equals(tbName2) should be false: " + (sName.equals(tbName2)));
        System.out.println("sName == tbName2 should be false: " + (sName == tbName2));
        System.out.println("sName.length() == tbName2.length should be false: " +( sName.length() == tbName2.length()));

        System.out.println("tbName.indexOf(\"K\") should be 5: "  + tbName.indexOf("K"));
        System.out.println("tbName.substring(5) should be \"Kennedy\": "  + tbName.substring(5));

        // should have newline
        System.out.println("|" + tbName2 + "|");

        String stuart = """
                "Look what I can do!"
                """;
        System.out.println("Embed to quotes " + stuart);

        // aligning the opening and closing braces of JSON, and the terminating operator gets rid ot the
        // incidental spaces that would make the JSON ugly when you print
        String prettyJson = """
                    {
                        "ouch": "boo",
                        "x": 42
                    }
                    """;
        System.out.println(prettyJson);
    }
}
