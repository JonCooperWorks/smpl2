package smpl.lang;

import java.util.*;

/**
 * ExpSubvec class
 *
 * This is the AST representation for an item in an SMPL vector. the
 * item can either be an ordinary expression, or a special kind of
 * vector initialization, consisting of two expressions.
 */
public class SmplSubVec extends ASTNodeX {
    
    public SmplSubVec(ASTNode[] e) {
	super(e);
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitExpSubVec(this, arg);
    }

    public String toString() {
	String result = getChild(0).toString();
	if (getChildCount() > 1)
	    result += " : " + getChild(1);
	return result;
    }
}
