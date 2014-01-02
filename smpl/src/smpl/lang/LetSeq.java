package smpl.lang;

import java.util.*;

/**
 * LetSeq class
 *
 * This is the AST representation for tuple of SMPL expressions
 */
public class LetSeq extends SmplSeq {
    
    public LetSeq(ArrayList as) {
	super(as);
    }

//     public Object visit(Visitor v, Object arg) throws SmplException {
// 	return v.visitLetSeq(this, arg);
//     }

    public String toString() {
	String result = "";
	if (getChildCount() > 0 )
	    result += getChild(0);
	for (int i = 1; i < getChildCount(); i++)
	    result += ", " + getChild(i);
	return result;
    }
}
