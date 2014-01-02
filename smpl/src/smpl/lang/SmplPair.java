package smpl.lang;

import java.util.*;

/**
 * Abstract Syntax Tree node for creation of an SMPL pair
 *
 */
public class SmplPair extends SmplLiteral {
    SmplLiteral left;
    SmplPair right;
    
    public SmplPair() {}

    public SmplPair(SmplLiteral e) {
	super();
	left = e;
	right = new SmplPair.Nil();
    }

    public SmplPair(SmplLiteral l, SmplLiteral e) {
	super();
	left = l;
	right = new SmplPair(e);
    }

    public SmplPair(SmplLiteral e, SmplPair p) {
	super();
	left = e;
	right = p;
    }

    public boolean isPair() {
	return true;
    }

    public boolean isList() {
	return (intSize() > 2);
    }

    public SmplLiteral car() {
	return left;
    }

    public SmplPair cdr() {
	return right;
    }

    public SmplNum size() {
	return new SmplNum.Int(intSize());
    }

    private int intSize() {
	if (isNil())
	    return 0;
	return 1 + ((SmplPair)right).intSize();
   }

    public SmplBool eq(SmplPair pr) throws SmplException {
	    if (left.eq(pr.car()) == TRUE)
		if (right.eq(pr.cdr()) == TRUE)
		    return TRUE;
// 	} catch (SmplException se) {
// 	    throw new "Error equating lists/pairs: " + se.getMessage();
// 	}
	return FALSE;
    }

//     convert this data structure to an array (for printing purposes only)
    public ArrayList toArray() {
	if (isNil())
	    return new ArrayList();
	ArrayList result = new ArrayList(intSize());
	result.add(left);
	return right.toArray(result);
    }

    public ArrayList toArray(ArrayList as) {
	if (isNil())
	    return as;
	as.add(left);
	return right.toArray(as);
    }
    
    public ArrayList toLitArray() {
	if (isNil())
	    return new ArrayList();
	ArrayList result = new ArrayList(intSize());
	result.add(new SmplLit(left));
	return right.toLitArray(result);
    }

    public ArrayList toLitArray(ArrayList as) {
	if (isNil())
	    return as;
	as.add(new SmplLit(left));
	return right.toLitArray(as);
    }
    
    public String toString() {
	if (isNil())
    {return "#e";}
	if (intSize() == 2)
    {return "(" + left + " . " + right.car() + ")";}
	return toArray().toString();
    }

    // make nil a subclass of SmplPair
    public static class Nil extends SmplPair {

	public Nil() {
	    super();
	    left = null;
	    right = null;
	}
	
	public boolean isNil() {
	    return true;
	}

	public SmplBool eq(SmplLiteral l) {
	    if (l.isNil())
		return TRUE;
	    else
		return FALSE;
	}
	
	public SmplBool eq(SmplLit l) {
	    if (l.getVal().isNil())
		return TRUE;
	    else
		return FALSE;
	}
	
	public String toString() {
	    return "#e";
	}
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
    
    
    public ASTNode getChild(int i){return null;}
    public int getChildCount(){return 0;};
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
}
