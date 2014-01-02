package smpl.lang;

import java.util.*;

/**
 * SmplTuple class
 *
 * Like a vector, this tuple uses an arraylist to store expressions.
 */
public class SmplTuple extends SmplLiteral {
    ArrayList content;

    public SmplTuple() {
	content = new ArrayList();
    }
    
    public SmplTuple(ArrayList as) {
	content = as;
    }

    public void add(Object l) {
	content.add(l);
    }
    
    public int size() {
	return content.size();
    }

    public Object get(int i) {
	return (Object) content.get(i);
    }

    public ArrayList getContents() {
	return content;
    }

    public boolean isTup() {
	return true;
    }

    public String toString() {
	String result = "(";
	for (int i = 0; i < content.size() - 1; i++)
	    result += content.get(i) + ", ";
	try {
	    result += content.get(content.size() - 1);
	} catch (IndexOutOfBoundsException ioobe) {}
	result += ")";
	
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


    public Object visit(Visitor v, Object info) throws SmplException{return null;}
    public int getChildCount(){return 0;}
    public ASTNode getChild(int i){return null;}
}
