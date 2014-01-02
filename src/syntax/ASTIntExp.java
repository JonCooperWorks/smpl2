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
public class ASTIntExp extends ASTNmExp{

    int value;
    
    public ASTIntExp(int param){
        super("int");
        value = param;
    }

    public int getValue() {
        return value;
    }
    
    @Override
    public <S, T> T visit(Visitor<S, T> visitor, S state) {
        return visitor.visitMakeInt(this, state);
    }
}
