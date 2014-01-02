/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import java.util.ArrayList;
import smpl.Visitor;

public class ASTVectorListExp extends ASTNode {
    public ASTVectorListExp( ArrayList<ASTNode> list ){
        super("VectorList", list);
    }
    
    public ArrayList<ASTNode> getValues(){
        return children;
    }
    
    @Override
    public <S, T> T visit(Visitor<S, T> visitor, S state) {
        return visitor.visitVectorListExp(this, state);
    }
}
