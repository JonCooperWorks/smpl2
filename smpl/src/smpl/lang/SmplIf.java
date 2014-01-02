package smpl.lang;

/**
 * Abstract Syntax Tree node for Smpl IF and IFELSE commands.
 *
 * 
 */
public class SmplIf extends ASTNodeX {

    public SmplIf(ASTNode e1, ASTNode e2, ASTNode e3) {
	super(new ASTNode[]{e1, e2, e3});
	
    }
    public SmplIf(ASTNode e1, ASTNode e2){
      super(new ASTNode[]{e1, e2});
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitIf(this, arg);
    }

    public String toString() {
	return "if " + getChild(0) + "\nthen \n" +
	    getChild(1) + "\n" +
	    ((getChild(2) != null) ? ("\nelse \n" +
				       getChild(2)) : "") + "\n";
    }
}
