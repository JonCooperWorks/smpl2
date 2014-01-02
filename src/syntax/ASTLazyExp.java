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
public class ASTLazyExp extends ASTNode {
    
    public ASTLazyExp(ASTNode exp){
        super("Lazy", exp);
    }
    
    public ASTNode getExp(){
        return children.get(0);
    }
    
    @Override
    public <S, T> T visit(Visitor<S, T> visitor, S state) {
        return visitor.visitLazyExp(this, state);
    }
}
