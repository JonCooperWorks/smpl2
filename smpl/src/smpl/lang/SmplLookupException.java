package smpl.lang;

public class SmplLookupException extends SmplException {
    String id;

    public SmplLookupException(String name) {
	super("variable " + name + " is undefined.");
	id = name;
    }
    
    public String getID() {
	return id;
    }
}
