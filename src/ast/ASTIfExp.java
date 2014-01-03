/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import lang.SMPLVisitor;


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
    public <S, T> T visit(SMPLVisitor<S, T> visitor, S state){
        return visitor.visitIfExp(this, state);
    }
}
