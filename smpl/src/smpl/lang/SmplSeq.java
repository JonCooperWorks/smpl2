package smpl.lang;

import java.util.*;

/**
 * SmplSeq class
 *
 * this is the parent class for all SMPL sequences (and there seem to
 * be a few). It has it's generic visit method, so that classes which
 * extend it can decide to use this default, or define their own.
 */
public class SmplSeq extends ASTNodeX{

    /**
     * decided to keep the static arraylist-to-array converter written
     * by Dr.Coore for the Logo Project
     */
    protected static ASTNode[] list2array(ArrayList as) {
	ASTNode[] es = new ASTNode[as.size()];
	for (int i = 0; i < as.size(); i++) {
	    es[i] = (ASTNode) as.get(i);
	}
	return es;
    }

    public SmplSeq(ArrayList seq) {
	super(list2array(seq));
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitSequence(this, arg);
    }

    public String toString() {
	String result = "{\n";
	for (int i = 0; i < getChildCount(); i++) {
	    result += getChild(i) + ";\n";
	}
	result += "}\n";
	return result;
    }
}

