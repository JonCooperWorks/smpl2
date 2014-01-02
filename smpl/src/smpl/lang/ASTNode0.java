package smpl.lang;


/**
 * Class to represent AST nodes that do not have any children.  These
 * nodes would typically represent some kind of stand-alone command
 * (e.g. HOME) or an immediate value, such as a number or a string.
 * So they are allowed to store constant data from the program to be
 * used later by the Logo interpreter.
 */
public abstract class ASTNode0 extends ASTNode {

    SmplLit value;

    public ASTNode0() {
	value = null;
    }

    public ASTNode0(SmplLit v) {
	value = v;
    }

    public SmplLit getValue() {
	return value;
    }

    public int getChildCount(){return 0;}
    public ASTNode getChild(int i){return null;}
}
