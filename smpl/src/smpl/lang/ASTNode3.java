package smpl.lang;

/**
 * Class to represent AST nodes that have three children.  These nodes
 * typically represent a special form that has three sub expressions.
 * For example, the command IF, IFELSE fall into this category.
 */
public abstract class ASTNode3 extends ASTNode {

    ASTNode[] children;
    

    public ASTNode3(ASTNode c1, ASTNode c2, ASTNode c3) {
	children = new ASTNode[]{c1, c2, c3};
	
    }

    public ASTNode getChild(int i) {
	return children[i];
    }

    public int getChildCount(){return children.length;}

    
}
