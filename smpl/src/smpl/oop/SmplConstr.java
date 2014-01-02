/*
 * SmplConstr.java
 *
 * Created on December 18, 2006, 2:50 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package smpl.oop;

import smpl.lang.SmplException;
import smpl.lang.*;
import smpl.lang.Visitor;

/**
 *
 * @author dephilio
 */
public class SmplConstr extends ASTNode2{
    
    /** Creates a new instance of SmplConstr */
    public SmplConstr(ASTNode args, ASTNode body) {
        super(args,body);
    }
    
   public String toString() {
        return "";
    }
   
   public Object visit(Visitor v,Object arg) throws SmplException{
        return v.visitSmplConstr(this,arg);
   }
    
}
