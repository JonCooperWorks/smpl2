package smpl.lang;

import java.util.*;

/**
 * ExpSeq class
 *
 * This is the AST representation for tuple of SMPL expressions
 */
public class ExpSeq extends SmplSeq {
    
    ArrayList list ; 
    public ExpSeq(ArrayList as) {
        
	super(as);
        this.list = as;
        
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitExpSeq(this, arg);
    }

    public String toString() {
	String result = "( ";
	if (getChildCount() > 0 )
	    result += getChild(0);
	for (int i = 1; i < getChildCount(); i++)
	    result += ", " + getChild(i);
	result += " )";
	return result;
    }

    public ArrayList getList() {
        return list;
    }

}
