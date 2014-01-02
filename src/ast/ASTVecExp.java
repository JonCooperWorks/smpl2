/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import smpl.Visitor;


public class ASTVecExp extends ASTNode{

    public ASTVecExp( ASTNode size, ASTNode func ) {
        super("Vector Exp", size, func);
    }
    
    public ASTNode getSize(){
        return children.get(0);
    }
    
    public ASTNode getFunction(){
        return children.get(1);
    }
    
    @Override
    public <S, T> T visit(Visitor<S, T> visitor, S state) {
        return visitor.visitVecExp(this, state);
    }
}
