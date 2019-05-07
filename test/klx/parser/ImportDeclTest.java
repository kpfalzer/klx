package klx.parser;

import org.junit.Test;

import static java.util.Objects.requireNonNull;
import static klx.Util.invariant;

public class ImportDeclTest {

    private static ImportDecl parse(String s) {
        Scanner scanner = Scanner.getStringScanner(s);
        Object ptree = Parser.parse(scanner, ImportDecl.class);
        return (ImportDecl) ptree;
    }

    @Test
    public void parse() {
        {
            String s = "import foo.bar";
            ImportDecl pdecl = parse(s);
            invariant(1 == pdecl.packageName().name().length, "expected 1 names");
            invariant(1 == pdecl.items().length, "expected 1 item");
        }
        {
            String s = "import foo.bar.*";
            ImportDecl pdecl = parse(s);
            requireNonNull(pdecl);
            invariant(2 == pdecl.packageName().name().length, "expected 2 names");
            invariant(1 == pdecl.items().length, "expected 1 item");
        }
    }
}