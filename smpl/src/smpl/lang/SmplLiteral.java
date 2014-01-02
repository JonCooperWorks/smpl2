package smpl.lang;

public abstract class SmplLiteral extends ASTNode {

    public static final SmplLiteral NIL = new SmplPair.Nil();

    public static final SmplBool TRUE = new SmplBool(true);

    public static final SmplBool FALSE = new SmplBool(false);

    protected SmplLiteral() {}

    

    

    public boolean isPair() {
	return false;
    }

    public boolean isList() {
	return false;
    }

    public boolean isNil() {
	return false;
    }

    public boolean isVec() {
	return false;
    }

    public boolean isTup() {
	return false;
    }

    public boolean isBool() {
	return false;
    }

    public boolean isNum() {
	return false;
    }

    public boolean isInt() {
	return false;
    }

    public boolean isHex() {
	return false;
    }

    public boolean isDec() {
	return false;
    }

    public boolean isBin() {
	return false;
    }

    public boolean isDouble() {
	return false;
    }

    public boolean isString() {
	return false;
    }

    public boolean isChar() {
	return false;
    }

    public boolean isVar() {
	return false;
    }

    public boolean isProc() {
	return false;
    }

   public boolean isTrue() {
	return false;
    }

    public boolean isFalse() {
	return false;
    }

    public abstract int intVal() throws SmplWrongTypeException;

    public abstract  double doubleVal() throws SmplWrongTypeException;

    public abstract  SmplNum numVal() throws SmplWrongTypeException; 

      
    
    public abstract  String stringVal() throws SmplWrongTypeException;

    public abstract  String charVal() throws SmplWrongTypeException;

    public SmplBool eq(SmplLiteral v2) throws SmplException {
	if ((this == v2) ||
	    (isTrue() && v2.isTrue()) ||
	    (isFalse() && v2.isFalse()))
	    return TRUE;
	else
	    return FALSE;
    }

    public abstract SmplBool ne(SmplLiteral l) throws SmplException;
    public abstract SmplBool gt(SmplLiteral l) throws SmplException;
    public abstract SmplBool ge(SmplLiteral l) throws SmplException;
    public abstract SmplBool lt(SmplLiteral l) throws SmplException;
    public abstract SmplBool le(SmplLiteral l) throws SmplException;
    public abstract String toString();


   
    public abstract SmplLiteral add(SmplLiteral n) throws SmplWrongTypeException;
    public abstract SmplLiteral sub(SmplLiteral n) throws SmplWrongTypeException;
    public abstract SmplLiteral mul(SmplLiteral n) throws SmplWrongTypeException;
    public abstract SmplLiteral pow(SmplLiteral n) throws SmplWrongTypeException;
    public abstract SmplLiteral div(SmplLiteral n) throws SmplWrongTypeException;
    public abstract SmplLiteral mod(SmplLiteral n) throws SmplWrongTypeException;
    public abstract SmplLiteral bnot() throws SmplWrongTypeException;
    public abstract SmplLiteral bor(SmplLiteral n) throws SmplWrongTypeException;
    public abstract SmplLiteral band(SmplLiteral n) throws SmplWrongTypeException;
    public abstract SmplLiteral neg();
    
}
