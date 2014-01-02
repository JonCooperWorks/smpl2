/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package syntax;

import smpl.Visitor;

/**
 *
 * @author Michelle
 */
public class ASTIfExp extends ASTNode{
    
    public ASTIfExp(ASTNode cond, ASTNode exp, ASTNode elseExp ){
        super("if", cond, exp, elseExp);
    }
    
    public ASTNode getCondition(){
        return getChild(0);
    }
    
    public ASTNode getExpression(){
        return getChild(1);
    }
    
    public ASTNode getElseExpression(){
        return getChild(2);
    }
    
    @Override
    public <S, T> T visit(Visitor<S, T> visitor, S state){
        return visitor.visitIfExp(this, state);
    }
}
