/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import smpl.Visitor;


public class ASTReadIntExp extends ASTNode {
    
    public ASTReadIntExp(){
        super("ReadInt");
    }
    
    public <S, T> T visit(Visitor<S, T> visitor, S state){
        return visitor.visitReadInt(this, state);
    }
}
