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
public class ASTBoAndExp extends ASTBoExp{

    public ASTBoAndExp(ASTNode b1, ASTNode b2){
        super("and", b1, b2);
    }
    
    @Override
    public <S, T> T visit(Visitor<S, T> visitor, S state) {
        return visitor.visitBoolAnd(this, state);
    }
    
}
