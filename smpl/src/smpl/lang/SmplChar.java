package smpl.lang;

public class SmplChar extends SmplStr {
    String val;
    String rep;

    public static final SmplChar NEWLINE =  new SmplChar("\n", "#\\nl");
    public static final SmplChar SPACE =  new SmplChar(" ", "#\\sp");
    public static final SmplChar TAB =  new SmplChar("\t", "#\\tb");
    public static final SmplChar CRETURN =  new SmplChar("\r", "#\\cr");
    public static final SmplChar FORMFEED =  new SmplChar("\f", "\\ff");

    public boolean isChar() {
	return true;
    }

    public String charVal() {
	return val;
    }

    public SmplChar(String chr, String r) {
	super(chr);
	val = chr;
	rep = r;
    }

    public SmplChar(String chr) {
	super(chr);
	val = "\\u"+ chr;
	rep = "#\\"+ chr;
    }

    public String toString() {
	return val;
    }
}
