/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import java.util.ArrayList;
import smpl.Visitor;

public class ASTCaseStmtExp extends ASTNode {
    
    public ASTCaseStmtExp(ArrayList<ASTNode> caseExps){
        super("Case Statement", caseExps);
    }
    
    public ArrayList<ASTNode> getCaseExpressions(){
        return children;
    }
    
    public <S, T> T visit(Visitor<S, T> visitor, S state) {
        return visitor.visitCaseStmtExp(this, state);
    }
}
