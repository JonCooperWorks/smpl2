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
public class ASTNmModExp extends ASTNmExp{
    public ASTNmModExp(ASTNode i1, ASTNode i2){
        super("%", i1, i2);
    }
    
    @Override
    public <S, T> T visit(Visitor<S, T> visitor, S state) {
        return visitor.visitNmMod(this, state);
    }
}
