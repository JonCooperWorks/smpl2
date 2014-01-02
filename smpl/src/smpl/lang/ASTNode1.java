package smpl.lang;

/**
 * Class to represent AST nodes that have one child.  These nodes
 * typically represent a special form that has one sub expression.
 * For example, the commands FORWARD, BACK, LEFT and RIGHT fall into
 * this category.
 */
public abstract class ASTNode1 extends ASTNode {

    ASTNode[] children;

    public ASTNode1(ASTNode c) {
	children = new ASTNode[]{c};
    }

    public ASTNode getChild() {
	return children[0];
    }

    public ASTNode getChild(int i){return children[i];}

    public int getChildCount(){return children.length;}
}
