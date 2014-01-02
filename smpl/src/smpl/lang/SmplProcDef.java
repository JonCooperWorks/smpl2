package smpl.lang;

/**
 * CmdProcDef class
 *
 * AST node representing a procedure definition. procedures can be
 * defined in three ways: with a fixed number of arguments, with a
 * minimum number of arguments, or with a any number of arguments,
 * (where they are all composed into a list and passed as a single
 * argument (basically SMPL version of argc[])
 */
public class SmplProcDef extends ASTNodeX {

    public SmplProcDef(ASTNode[] nodes) {
	super(nodes);
    }

    

    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitProcDef(this, arg);
    }

    public String toString() {
	String result = "proc";
	if (getChildCount() == 3) {
	    result += "(" + getChild(0);
	  /* if(!getChild(1).equals(null))
       {
            String v = getChild(1).toString();
            if (!v.equals(""))
            {
                result += " . "+v;
            }
       }*/
        
        
        result += ")\n" + getChild(2);
        
	} else if(getChildCount() == 2)
    {
        result += " " + getChild(0) + "\n" +
		getChild(1);
    }
	
	return result;
    }
}
