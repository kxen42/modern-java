package org.fotm.duh;

import java.time.LocalDate;

public class TextBlocks {

    public static void main(String[] args) {
        closingQuotesInCharge();
        incidentalWhitespece();
        closingQuotesOnSameLineAsLast();
        fillingInPlaceHolders();
    }

    /**
     * When closing quotes are left most, that determines the location of the first char on the output
     * (i.e. it determines the indentation).
     */
    public static void closingQuotesInCharge() {
        System.out.println(" ----- closingQuotesInCharge");
        // If the closing quotes were to the right of the last blah, the location of the last blah
        // would determine the indent.
        var str = """
                 blah
                  blah
               blah
            """;
        System.out.println("|");
        System.out.print(str);
        System.out.println("|");
        str = """
                 blah
                  blah
               blah
""";
        System.out.println("|");
        System.out.print(str);
        System.out.println("|");
    }

    /**
     * Incidental whitespace is determined by the left most line. The left most
     * line starts at the first position of the printed output. The locations of the closing """
     * doesn't affect indent if it isn't the left most line.
     */
    static void incidentalWhitespece() {
        System.out.println(" ----- incidentalWhitespece");

        String html = """
        <html>
            <body>
                <p>Hello World.</p>
            </body>
        </html>
                  """;
        System.out.println("|");
        System.out.print(html);
        System.out.println("|");
        System.out.println("Indent TextBlock");
        System.out.println(html.indent(4));
        System.out.println("-------------------------");
        System.out.println("|");
        System.out.println("""
                This
             is
                    stuff                   
                               """);
        System.out.println("|");
    }

    public static void closingQuotesOnSameLineAsLast() {
        System.out.println(" ----- closingQuotesOnSameLineAsLast");
        var str = """
            Apfel
            Braun
            Gift""";

        System.out.println("|");
        System.out.println(str);
        System.out.println("|");
        System.out.println("-------------------------");
        System.out.println("|");
        System.out.println(str.indent(8));
        System.out.println("|");
    }

    public static void fillingInPlaceHolders() {
        System.out.println("TextBlocks.fillingInPlaceHolders - ");
        String sql = """
            SELECT * FROM customer WHERE
            name = '%s'
            age = %d
            dob = %s
            """.indent(4)
               .formatted("Fred", 36, LocalDate.of(1960, 11, 12));
        System.out.println(sql);
        sql = """
            SELECT * FROM employee
            WHERE
            %s
            """;
        var whereClause = """
    firstname = '%s'
    lastname = '%s'
    salary = %,.2f
    hire_date = %s
""".indent(4)  // <-- opt out of incidental whitespace stripping
               .formatted("Wilma", "Flintstone", 162_000.95, LocalDate.of(2010, 5, 1));
        sql = sql.formatted(whereClause);

        System.out.println(sql);
    }
}
