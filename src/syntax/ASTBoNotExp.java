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
public class ASTBoNotExp extends ASTBoExp{

    public ASTBoNotExp(ASTNode b1){
        super("not", b1);
    }
    
    @Override
    public <S, T> T visit(Visitor<S, T> visitor, S state) {
        return visitor.visitBoolNot(this, state);
    }
    
}
