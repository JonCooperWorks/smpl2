/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package syntax;

import smpl.Visitor;

/**
 *
 * @author Michelle
 */
public class ASTReadExp extends ASTNode {
    
    public ASTReadExp(){
        super("Read");
    }
    
    public <S, T> T visit(Visitor<S, T> visitor, S state){
        return visitor.visitRead(this, state);
    }
}
