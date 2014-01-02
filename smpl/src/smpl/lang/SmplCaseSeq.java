package smpl.lang;

import java.util.*;

/**
 * CaseSeq class
 *
 * This is the AST representation for tuple of SMPL expressions
 */
public class SmplCaseSeq extends SmplSeq {
    
    public SmplCaseSeq(ArrayList as) {
	super(as);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitCaseSeq(this, arg);
    }

    public String toString() {
	String result = "";
	if (getChildCount() > 0 )
	    result += getChild(0);
	for (int i = 1; i < getChildCount(); i++)
	    result += ", " + getChild(i);
	return result;
    }
}
