/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import lang.SMPLVisitor;

public class ASTPairExp extends ASTNode{
    
    public ASTPairExp(ASTNode e1, ASTNode e2){
        super("Pair", e1, e2);
    }
    
    public ASTNode getExp1(){
        return children.get(0);
    }
    
    public ASTNode getExp2(){
        return children.get(1);
    }
    
    @Override
    public <S, T> T visit(SMPLVisitor<S, T> visitor, S state) {
        return visitor.visitPairExp(this, state);
    }
}
