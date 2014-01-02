package smpl.lang;

import java.util.*;

/**
 * CmdVector class
 *
 * This is the AST representation for an SMPL vector. a vector is
 * actually a sequence of SMPL expressions
 */
public class SmplCVector extends SmplSeq {
    
    public SmplCVector(ArrayList as) {
	super(as);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitCmdVec(this, arg);
    }

    public String toString() {
	String result = "[: ";
	if (getChildCount() > 0 )
	    result += getChild(0);
	for (int i = 1; i < getChildCount(); i++)
	    result += ", " + getChild(i);
	result += " :]";
	return result;
    }
}
