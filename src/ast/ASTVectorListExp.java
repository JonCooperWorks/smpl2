/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import java.util.ArrayList;
import lang.SMPLVisitor;

public class ASTVectorListExp extends ASTNode {
    public ASTVectorListExp( ArrayList<ASTNode> list ){
        super("VectorList", list);
    }
    
    public ArrayList<ASTNode> getValues(){
        return children;
    }
    
    @Override
    public <S, T> T visit(SMPLVisitor<S, T> visitor, S state) {
        return visitor.visitVectorListExp(this, state);
    }
}
