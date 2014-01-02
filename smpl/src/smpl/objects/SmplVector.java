package smpl.objects;

import java.util.*;
import smpl.lang.*;


public class SmplVector extends SmplLiteral {
    ArrayList content;

    public SmplVector() {
	content = new ArrayList();
    }
    
    public SmplVector(ArrayList as) {
	content = as;
    }

    public void addi(ArrayList as) {
	for (int i = 0; i < as.size(); i++)
	    content.add(as.get(i));
    }
    
    public void addi(Object l) {
	    content.add(l);
    }

    public SmplNum size() {
	return new SmplNum.Int(content.size());
    }

    public Object get(int i) {
	return content.get(i);
    }

    public Object get(SmplNum i)
	throws SmplException
    {
	int v = -1;
	try {
	    v = i.intVal();
	} catch (ClassCastException cce) {
	    throw new SmplWrongTypeException("Trying to access vector with " +
					     "non-numerical value is not allowed.");
	} catch (SmplWrongTypeException swte) {
	    throw new SmplWrongTypeException("Trying to access vector with " +
					     "non-numerical value is not allowed.");
	}
	return content.get(v);
    }

    public boolean isVec() {
	return true;
    }

    public String toString() {
	String result = "[: ";
	for (int i = 0; i < content.size() - 1; i++)
	    result += content.get(i) + ", ";
	try {
	    result += content.get(content.size() - 1);
	} catch (IndexOutOfBoundsException ioobe) {}
	result += " :]";
	
	return result;
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

    public String stringVal() throws SmplWrongTypeException {
	throw new SmplWrongTypeException("String", "nuthin");
    }
    
    public String charVal() throws SmplWrongTypeException {
	throw new SmplWrongTypeException("Char", "nuthin");
    }

    public Object visit(Visitor v,Object arg) throws SmplException{return null;}
    public int getChildCount(){return 0;}
    public ASTNode getChild(int i){return null;}

    
}
