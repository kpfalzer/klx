package klx.parser.acceptor;

import klx.parser.Parser;
import klx.parser.Token;
import klx.parser.Token.EType;

import java.util.LinkedList;
import java.util.List;

import static java.util.Objects.isNull;
import static klx.Util.addToList;
import static klx.Util.toArray;

public class Repetition extends Acceptor {
    private Repetition(Acceptor acceptor, boolean zeroOrMore) {
        __acc = acceptor;
        __zeroOrMore = zeroOrMore;
    }

    public static Repetition zeroOrMore(Acceptor acceptor) {
        return new Repetition(acceptor, true);
    }

    public static Repetition oneOrMore(Acceptor acceptor) {
        return new Repetition(acceptor, false);
    }

    public static Object[] zeroOrMoreSemiColon(Parser parser) {
        List<Object> semis = new LinkedList<>();
        Token tok;
        while (true) {
            tok = parser.peek();
            if (tok.type != EType.SEMICOLON) break;
            semis = addToList(parser.accept(), semis);
        }
        return toArray(semis);
    }
    private final Acceptor __acc;
    private final boolean __zeroOrMore;

    @Override
    public Object[] accept(Parser parser, Predicate predicate) {
        _init(parser);
        List<Object> accepted = __zeroOrMore ? new LinkedList<>() : null;
        while (true) {
            Object[] acc = __acc.accept(parser, predicate);
            if (isNull(acc)) break;
            accepted = addToList(acc, accepted);
        }
        if (isNull(accepted) && !__zeroOrMore) {
            return _fail();
        }
        return toArray(accepted);
    }

}
