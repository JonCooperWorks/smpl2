/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import java.util.ArrayList;
import lang.SMPLVisitor;


public class ASTListExp extends ASTNode {
    
    public ASTListExp(ASTNode...e){
        super("List", e);
    }
    
    public ASTListExp(ArrayList<ASTNode> e){
        super("List", e);
    }
    
    public ArrayList<ASTNode> getValues(){
        return children;
    }
    
    @Override
    public <S, T> T visit(SMPLVisitor<S, T> visitor, S state) {
        return visitor.visitListExp(this, state);
    }
}
