/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import lang.SMPLVisitor;


public class ASTSubstrExp extends ASTNmExp{

    public ASTSubstrExp(ASTNode e1, ASTNode e2, ASTNode e3){
        super("substr", e1, e2, e3);
    }
    
    public ASTNode getString(){
        return children.get(0);
    }
    
    public ASTNode getStart(){
        return children.get(1);
    }
    
    public ASTNode getEnd(){
        return children.get(2);
    }
            
    @Override
    public <S, T> T visit(SMPLVisitor<S, T> visitor, S state) {
        return visitor.visitStringExp(this, state);
    }
}
