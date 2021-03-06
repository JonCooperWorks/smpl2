/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import lang.SMPLVisitor;


public class ASTEqualExp extends ASTNode{
    
    public ASTEqualExp(ASTNode e1, ASTNode e2){
        super("Equal", e1, e2);
    }
    
    public ASTNode getExp1(){
        return children.get(0);
    }
    
    public ASTNode getExp2(){
        return children.get(1);
    }

    @Override
    public <S, T> T visit(SMPLVisitor<S, T> visitor, S state) {
        return visitor.visitEqualExp(this, state);
    }
}
