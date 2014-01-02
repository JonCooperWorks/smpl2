package smpl.lang;


public class SmplStr extends SmplLiteral {
    String lit;

    public SmplStr(String s) {
	lit = s;
    } // constructor

    // return the string contained in this object
    public String stringVal() {
	return lit;
    }
    
    public boolean isString() {
	return true;
    }

    public SmplBool eq(SmplLiteral l) throws SmplException {
	if (l.isString())
	    if (lit == l.stringVal())
		return TRUE;
	return FALSE;
    }

    public SmplBool ne(SmplLiteral l) throws SmplException {
	if (l.isString())
	    if (lit != l.stringVal())
            return TRUE;
	return FALSE;
    }

    public SmplStr substr(int a, int b) {
	if (b > lit.length())
	    return substr(a, b - 1);
	else if (b < a)
	    return new SmplStr("");
	else
	    return new SmplStr(lit.substring(a, b));
    }

    public SmplStr substr(SmplNum a, SmplNum b)
	throws SmplException
    {
	int x = -1;
	int y = -1;
	try {
	    x = a.intVal();
	    y = b.intVal();
	} catch (ClassCastException cce) {
	    throw new SmplWrongTypeException("Trying to access vector with " +
					     "non-numerical value is not allowed.");
	} catch (SmplWrongTypeException swte) {
	    throw new SmplWrongTypeException("Trying to access vector with " +
					     "non-numerical value is not allowed.");
	}
	return substr(x, y);
    }

    public SmplBool lt(SmplLiteral v2) throws SmplException {
	throw new Error("Strings only support equality compatisons, '<' will not work");
    }

    public SmplBool gt(SmplLiteral v2) throws SmplException {
	throw new Error("Strings only support equality compatisons, '>' will not work");
    }

    public SmplBool le(SmplLiteral v2) throws SmplException {
	throw new Error("Strings only support equality compatisons, '<=' will not work");
    }

    public SmplBool ge(SmplLiteral v2) throws SmplException {
	throw new Error("Strings only support equality compatisons, '>=' will not work");
    }

    // you can only add strings (ie. cannot sub, mul, etc.)
    public SmplLiteral add(SmplLiteral l) {
	return new SmplStr(lit + l.toString());
    }
    
    public SmplLiteral sub(SmplLiteral l) throws SmplWrongTypeException {
	throw new Error("Strings do not support subtraction");
    }

    public SmplLiteral mul(SmplLiteral l) throws SmplWrongTypeException {
	throw new Error("Strings do not support multiplication");
    }

    public SmplLiteral pow(SmplLiteral l) throws SmplWrongTypeException {
	throw new Error("Strings do not support exponentiation");
    }

    public SmplLiteral div(SmplLiteral l) throws SmplWrongTypeException {
	throw new Error("Strings do not support division");
    }

    public SmplLiteral mod(SmplLiteral l) throws SmplWrongTypeException {
	throw new Error("Strings do not support modulo");
    }

    public String toString() {
	return lit ;
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

    public int intVal() throws SmplWrongTypeException {
	throw new SmplWrongTypeException("Integer", "nuthin");
    }

    public double doubleVal() throws SmplWrongTypeException {
	throw new SmplWrongTypeException("Double", "nuthin");
    }

    public SmplNum numVal() throws SmplWrongTypeException {
	throw new SmplWrongTypeException("Number", "nuthin");
    }

    public String charVal() throws SmplWrongTypeException {
	throw new SmplWrongTypeException("Char", "nuthin");
    }

    public Object visit(Visitor v, Object info) throws SmplException{return null;}
    public int getChildCount(){return 0;}
    public ASTNode getChild(int i){return null;}
} // SmplStr
