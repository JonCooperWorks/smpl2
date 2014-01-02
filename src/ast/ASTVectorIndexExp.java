/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import smpl.Visitor;


public class ASTVectorIndexExp extends ASTNode{
    
    public ASTVectorIndexExp(ASTNode e, ASTNode index){
        super("VectorIndex", e, index);
    }
    
    public ASTNode getExp(){
        return children.get(0);
    }
    
    public ASTNode getIndex(){
        return children.get(1);
    }

    @Override
    public <S, T> T visit(Visitor<S, T> visitor, S state) {
        return visitor.visitVectorIndexExp(this, state);
    }
}
