package smpl.lang;

public class SmplWrongTypeException extends SmplException {

    String problem = "";
    String expectedType = "";
    String actualType = "";

    public SmplWrongTypeException(String msg) {
	super(msg);
    }

    public SmplWrongTypeException(String expected, String actual) {
	super("expecting : " + expected + ", but given : " + actual + "\n");
	expectedType = expected;
	actualType = actual;
    }

    public SmplWrongTypeException(String expected, String actual, String problem) {
	super(problem + ": expecting : " + expected + ", but given : " + actual + "\n");
	expectedType = expected;
	actualType = actual;
    }

    public String getActual() {
	return actualType;
    }

    public String getExpected() {
	return expectedType;
    }
}
