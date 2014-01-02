/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import smpl.Visitor;

public class ASTIdExp extends ASTNode{

    public ASTIdExp(String str){
        super(str);
    }
    
    @Override
    public <S, T> T visit(Visitor<S, T> visitor, S state) {
        return visitor.visitID(this, state);
    }
}
