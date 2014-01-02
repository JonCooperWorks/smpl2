/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package syntax;

import java.util.ArrayList;
import smpl.Visitor;

/**
 *
 * @author Michelle
 */
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
