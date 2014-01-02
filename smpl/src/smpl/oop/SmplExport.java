/*
 * SmplExport.java
 *
 * Created on December 18, 2006, 2:50 AM
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
public class SmplExport  extends SmplExp{
    
    
    ArrayList params;
    /** Creates a new instance of SmplExport */
    public SmplExport(ArrayList param) {
        
        
        this.params = param;
        
        
    }

    public void setParams(ArrayList params) {
        this.params = params;
    }

    public ArrayList getParams() {
        return params;
    }

    public String toString() {
        
        return "";
    }
     public Object visit(Visitor v, Object arg) throws
            SmplException {
        return v.visitSmplExport(this, arg); }
}
