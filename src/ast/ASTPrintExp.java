/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import lang.SMPLVisitor;

public class ASTPrintExp extends ASTNode {
    
    private boolean _bPrintLn;
    
    public ASTPrintExp( ASTNode exp, boolean bPrintln ){
        super("print", exp);
        _bPrintLn = bPrintln;
    }
    
    public ASTNode getExpression(){
        return getChild(0);
    }
    
    public boolean PrintLine(){
        return _bPrintLn;
    }
            
    public <S, T> T visit(SMPLVisitor<S, T> visitor, S state){
        return visitor.visitPrint(this, state);
    }
}
