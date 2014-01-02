package smpl.lang;

import smpl.objects.SmplVector;
/**
 * SmplLit.java
 *
 * Abstract Syntax Tree node for an Smpl Literal
 *
 * This is more or less a wrapper for literal smpl values
 */

public class SmplLit extends ASTNode {
    SmplLiteral lit;

    // nil
    public SmplLit() {
	lit = new SmplPair.Nil();
    }

    // integers
    public SmplLit(int i) {
	lit = (SmplLiteral) new SmplNum.Int(i);
    }
    
    public SmplLit(Integer i) {
	lit = (SmplLiteral) new SmplNum.Int(i.intValue());
    }
    
    public SmplLit(Integer i, int b) {
	lit = (SmplLiteral) new SmplNum.Int(i.intValue(), b);
    }
    
    public SmplLit(SmplNum.Int i) {
	lit = (SmplLiteral) i;
    }
    
    // doubles
    public SmplLit(double d) {
	lit = (SmplLiteral) new SmplNum.Double(d);
    }
    
    public SmplLit(Double d) {
	lit = (SmplLiteral) new SmplNum.Double(d.doubleValue());
    }
    
    public SmplLit(SmplNum.Double d) {
	lit = (SmplLiteral) d;
    }
    
    // strings
    public SmplLit(String s) {
	lit = (SmplLiteral) new SmplStr(s);
    }
    
    public SmplLit(SmplStr s) {
	lit = (SmplLiteral) s;
    }

    public SmplLit(SmplChar c) {
	lit = (SmplLiteral) c;
    }

    // booleans
    public SmplLit(boolean b) {
	lit = (SmplLiteral) new SmplBool(b);
    }

    public SmplLit(SmplBool b) {
	lit = (SmplLiteral) b;
    }

    // lists 'n pairs 'n stuff
    public SmplLit(SmplPair p) {
	lit = (SmplLiteral) p;
    }

    public SmplLit(SmplVector v) {
	lit = (SmplLiteral) v;
    }

    // SmplLiteral
    public SmplLit(SmplLiteral v) {
	lit = v;
    }
    
    public SmplLiteral getVal() {
	return lit;
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitExpLit(this, arg);
    }
    public int getChildCount(){return 0;}
    public ASTNode getChild(int i){return null;}

    public String toString() {
	return lit.toString();
    }
}
