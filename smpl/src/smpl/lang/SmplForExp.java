package smpl.lang;

/**
 * CmdFor class
 *
 * AST node representation of an SMPL implementation of typical for loops
 */
public class SmplForExp extends ASTNodeX {
    


    public SmplForExp(ASTNode e1, ASTNode e2, ASTNode e3, ASTNode e4) {
        super(new ASTNode[]{e1, e2, e3, e4});
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitFor(this, arg);
    }

    public String toString() {
	return "for (" + getChild(0) + ";" +
	    getChild(1) + ";" +
	    getChild(2) + ")\n" + getChild(3);
    }
}
