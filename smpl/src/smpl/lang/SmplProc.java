package smpl.lang;

import smpl.sys.*;
import java.util.*;

/**
 * SmplProc class
 *
 * procedures are first-class citizens of SMPL. they are therefore
 * extended from SmplLiteral.
 */
public class SmplProc extends SmplLiteral {
    String[] args = {};
    String listArg = "";
    ArrayList bindings = new ArrayList();
    ArrayList values = new ArrayList();
    ASTNode body;

    SmplProc(String[] as, ASTNode b, SmplEnvironment d) {
	args = as;
	body = b;
	recordUnbound(as, b, d, bindings, values);
// 	try {
// 	    System.err.println(args + ", " + listArg + ", " + bindings + ", " + values + ", " + body);
// 	} catch (Exception e) {}
    }
    
    SmplProc(String a, ASTNode b, SmplEnvironment d) {
	listArg = a;
	body = b;
	recordUnbound(a, b, d, bindings, values);
// 	try {
// 	    System.err.println(args + ", " + listArg + ", " + bindings + ", " + values + ", " + body);
// 	} catch (Exception e) {}
    }
    
    SmplProc(String[] as, String a, ASTNode b, SmplEnvironment d) {
	args = as;
	listArg = a;
	body = b;
	recordUnbound(as, a, b, d, bindings, values);
// 	try {
// 	    System.err.println(args + ", " + listArg + ", " + bindings + ", " + values + ", " + body);
// 	} catch (Exception e) {}
    }

    public String[] getArgs() {
	return args;
    }
	
    public String getListArg() {
	return listArg;
    }

    public ASTNode getBody() {
	return body;
    }

    public ArrayList getExtraArgs() {
	return bindings;
    }

    public ArrayList getExtraVals() {
	return values;
    }

    // method to check if a string is in an array of strings
    public static boolean member(String[] as, String s) {
	for (int i = 0; i < as.length; i ++)
    	{
            if (s.compareTo(as[i]) == 0)
            {
                return true;
            }
        }
	return false;
    }
    
    public static boolean member(ArrayList as, String s) {
	for (int i = 0; i < as.size(); i ++)
    {
	    String n = (String) as.get(i);
	    if (s.compareTo(n) == 0)
            return true;
	}
	return false;
    }
    
    public static void recordUnbound(String[] as, ASTNode b, SmplEnvironment d, ArrayList bs, ArrayList vs) {
	try{
	    SmplVar v = (SmplVar) b;
	    String n = v.getVar();
	    SmplLiteral l;
	    if (!member(as, n) && !member(bs, n)) {
		try {
		    l = d.get(n);
		    bs.add(n);
		    vs.add(l);
		} catch (SmplException se) {}
	    }
	} catch (ClassCastException cce) {
	    try {
		SmplProcCall c = (SmplProcCall) b;
		recordUnbound(as, c.getChild(1), d, bs, vs);
	    } catch (ClassCastException cce1) {
		try {
		    SmplDef c = (SmplDef) b;
		    recordUnbound(as, c.getChild(1), d, bs, vs);
		} catch (ClassCastException cce2) {
		    for (int i = 0; i < b.getChildCount(); i++) {
			recordUnbound(as, b.getChild(i), d, bs, vs);
		    }
		} catch (Exception e2) {}
	    } catch (Exception e1) {}
	} catch (Exception e) {}
    }
    
    public static void recordUnbound(String s, ASTNode b, SmplEnvironment d, ArrayList bs, ArrayList vs) {
	try{
	    SmplVar v = (SmplVar) b;
	    String n = v.getVar();
	    SmplLiteral l;
	    if (!n.equals(s)) {
		try {
		    l = d.get(n);
		    bs.add(n);
		    vs.add(l);
		} catch (SmplException se) {}
	    }
	} catch (ClassCastException cce) {
	    try {
		SmplProcCall c = (SmplProcCall) b;
		recordUnbound(s, b.getChild(1), d, bs, vs);
	    } catch (ClassCastException cce1) {
		try {
		    SmplDef c = (SmplDef) b;
		    recordUnbound(s, b.getChild(1), d, bs, vs);
		} catch (ClassCastException cce2) {
		    for (int i = 0; i < b.getChildCount(); i++) {
			recordUnbound(s, b.getChild(i), d, bs, vs);
		    }
		} catch (Exception e2) {}
	    } catch (Exception e1) {}
	} catch (Exception e) {}
    }

    public static void recordUnbound(String[] as, String s, ASTNode b, SmplEnvironment d, ArrayList bs, ArrayList vs) {
	String[] as2 = new String[as.length + 1];
	int i = 0;
	for (; i < as.length; i++)
	    as2[i] = as[i];
	as2[i] = s;
	recordUnbound(as2, b, d, bs, vs);
    }

    public String toString() {
	String result = "proc (";
	if (args.length > 0) result += args[0];
	for (int i = 1; i < args.length; i++)
	    result +=args[i];
	result += (listArg != "") ? " . " + listArg : "";
	if(args.length >= 0) result += ")\n ";
	result += body;
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
