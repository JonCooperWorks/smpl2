/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package syntax;

import smpl.Visitor;

/**
 *
 * @author jean-paul
 */
public class ASTSequence extends ASTNode{
    public ASTSequence() {
        super("seq");
    }
    
    public ASTSequence(ASTNode... exp) {
        super("seq", exp);
    }
    
    public ASTSequence add(ASTNode exp) {
        children.add(exp);
        return this;
    }
    
    public ASTNode getStatement(int i) {
        return getChild(i);
    }
    
    public Iterable<ASTNode> statements() {
        return children;
//        return children.iterator();
    }
    
    @Override
    public String toString() {
        String result = "";
        for (ASTNode stmt : children) {
            result += stmt.toString() + "\n";
        }
        return result;
    }
    
    @Override
    public <S, T> T visit(Visitor<S, T> visitor, S state){
        return visitor.visitSequence(this, state);
    }
}
