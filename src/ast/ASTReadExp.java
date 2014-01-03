/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import lang.SMPLVisitor;

public class ASTReadExp extends ASTNode {
    
    public ASTReadExp(){
        super("Read");
    }
    
    public <S, T> T visit(SMPLVisitor<S, T> visitor, S state){
        return visitor.visitRead(this, state);
    }
}
