package smpl.lang;

public abstract class ASTNode {
    public abstract Object visit(Visitor v, Object info) throws SmplException;
    public abstract String toString();
    public abstract ASTNode getChild(int i);
    public abstract int getChildCount();
}