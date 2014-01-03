/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import lang.SMPLVisitor;

public class ASTListConcatExp extends ASTNmExp{

    public ASTListConcatExp(ASTNode e1, ASTNode e2){
        super("@", e1, e2);
    }
    
    public ASTNode getVector1(){
        return children.get(0);
    }
    
    public ASTNode getVector2(){
        return children.get(1);
    }
    
    @Override
    public <S, T> T visit(SMPLVisitor<S, T> visitor, S state) {
        return visitor.visitListConcat(this, state);
    }
}
