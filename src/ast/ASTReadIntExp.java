/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import lang.SMPLVisitor;


public class ASTReadIntExp extends ASTNode {
    
    public ASTReadIntExp(){
        super("ReadInt");
    }
    
    public <S, T> T visit(SMPLVisitor<S, T> visitor, S state){
        return visitor.visitReadInt(this, state);
    }
}
