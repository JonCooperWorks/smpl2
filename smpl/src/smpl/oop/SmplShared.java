/*
 * SmplShared.java
 *
 * Created on December 18, 2006, 2:49 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package smpl.oop;

import java.util.ArrayList;
import smpl.lang.SmplException;
import smpl.lang.SmplExp;
import smpl.lang.Visitor;

/**
 *
 * @author dephilio
 */
public class SmplShared  extends SmplExp {
    
    ArrayList stmts;
    
    /** Creates a new instance of SmplShared */
    public SmplShared(ArrayList stmts) {
        this.stmts = stmts;
    }

    public ArrayList getStmts() {
        return stmts;
    }
    
   public Object visit(Visitor v, Object arg) throws SmplException {
        
        return v.visitSmplShared(this,arg);
    }
    
    public String toString() {
        return "This is the Shared Method";
    }
    
}
