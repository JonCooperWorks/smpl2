package smpl.lang;

import java.util.*;

/**
 * CmdList
 *
 * Abstract Syntax Tree node for creation of an SMPL list, which is really a chain of pairs
 *
 */
public class SmplList extends ExpSeq {
    

    public SmplList(ArrayList as) {
	super(as);
    }
    

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitCmdList(this, arg);
    }

    public String toString() {
	String result = "[";
	for (int i = 0; i < getChildCount() - 1; i++)
	    result += getChild(i) + ", ";
	try {
	    result += getChild(getChildCount() - 1);
	} catch (ArrayIndexOutOfBoundsException aioobe) {}
	result += "]";
	return result;
    }
}
