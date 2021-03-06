package klx.parser;

import java.util.Arrays;

public class ParseError extends RuntimeException {
    public ParseError(Token loc, String message) {
        super(error(loc, message));
    }

    public static void expected(String expected, Token found) throws ParseError {
        throw new ParseError(found, "expected " + expected + ", found '" + found.text + "'");
    }

    public static void expected(String[] expected, Token found) throws ParseError {
        throw new ParseError(found,
                "expected " + Arrays.toString(expected) + ", found '" + found.text + "'");
    }

    public static void atError(String processing, Token found) throws ParseError {
        throw new ParseError(
                found,
                " found '" + found.text + "' while processing: " + processing
        );
    }

    public static String error(Token loc, String message) {
        return loc.getLocation() + ": " + message;
    }
}
