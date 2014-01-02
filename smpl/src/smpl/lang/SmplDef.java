package smpl.lang;

/**
 * CmdDef class
 *
 * AST node representation of a variable binding, (or multiple
 * variable binding)
 */
public class SmplDef extends ASTNode2 {
    boolean def = false;

    public SmplDef(ASTNode e1,  ASTNode e2) {
	super(e1, e2);
    }

    public SmplDef(ASTNode e1,  ASTNode e2, boolean d) {
	super(e1, e2);
	def = d;
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitCmdDef(this, arg);
    }

    public String toString() {
        if (def)
        {
            return "def " + getChild(0) + " " + getChild(1);
        }
        else
        {
            return getChild(0) + " := " + getChild(1);
        }
	
    }
}
