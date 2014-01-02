package smpl.lang;

public class SmplBool extends SmplLiteral {
    boolean val;

    protected SmplBool() {}	// no unspecified SmplBool's allowed

    public SmplBool(boolean v) {
	val = v;
    }
    
    public boolean isBool() {
	return true;
    }

    public boolean isTrue() {
	return val;
    }

    public boolean isFalse() {
	return !val;
    }

    public SmplBool eq(SmplLiteral v2) {
	if (v2.isBool())
	    if (val == v2.isTrue())
		return TRUE;
	return FALSE;
    }

    public SmplBool and(SmplBool v) {
	if (val && v.isTrue())
	    return TRUE;
	else
	    return FALSE;
    }

    public SmplBool or(SmplBool v) {
	if (val || v.isTrue())
	    return TRUE;
	else
	    return FALSE;
    }

    public SmplBool not() {
	if (isTrue())
	    return FALSE;
	else
	    return TRUE;
    }

    public String toString() {
	return (val ? "#t" : "#f");
    }

    public  SmplLiteral add(SmplLiteral n) throws SmplWrongTypeException {
	return null;
    }
    public  SmplLiteral sub(SmplLiteral n) throws SmplWrongTypeException {
	return null;
    };
    public  SmplLiteral mul(SmplLiteral n) throws SmplWrongTypeException {
	return null;
    }
    public  SmplLiteral pow(SmplLiteral n) throws SmplWrongTypeException {
	return null;
    }
    public  SmplLiteral div(SmplLiteral n) throws SmplWrongTypeException {
	return null;
    }
    public  SmplLiteral mod(SmplLiteral n) throws SmplWrongTypeException {
	return null;
    }
    public  SmplLiteral bnot() throws SmplWrongTypeException {
	return null;
    }
    public  SmplLiteral bor(SmplLiteral n) throws SmplWrongTypeException {
	return null;
    }
    public  SmplLiteral band(SmplLiteral n) throws SmplWrongTypeException {
	return null;
    }
    public SmplLiteral neg() {
	return null;
    }
    public int getX() {
	return 0;
    }
    
    public int getY() {
	return 0;
    }

    public SmplBool ne(SmplLiteral l) throws SmplException {
	return null;
    }
    public SmplBool gt(SmplLiteral l) throws SmplException {
	return null;
    }
    public SmplBool ge(SmplLiteral l) throws SmplException {
	return null;
    };
    public SmplBool lt(SmplLiteral l) throws SmplException {
	return null;
    }
    public SmplBool le(SmplLiteral l) throws SmplException {
	return null;
    }
    
    public int intVal() throws SmplWrongTypeException {
	throw new SmplWrongTypeException("Integer", "nuthin");
    }

    public double doubleVal() throws SmplWrongTypeException {
	throw new SmplWrongTypeException("Double", "nuthin");
    }

    public SmplNum numVal() throws SmplWrongTypeException {
	throw new SmplWrongTypeException("Number", "nuthin");
    }

    public String stringVal() throws SmplWrongTypeException {
	throw new SmplWrongTypeException("String", "nuthin");
    }
    
    public String charVal() throws SmplWrongTypeException {
	throw new SmplWrongTypeException("Char", "nuthin");
    }
    public Object visit(Visitor v, Object info) throws SmplException{return null;}
    public int getChildCount(){return 0;}
    public ASTNode getChild(int i){return null;}

   
}

