/*
 * SmplNew.java
 *
 * Created on December 18, 2006, 2:20 AM
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
public class SmplNew extends SmplExp{
    
    String className;
    ArrayList param;
    /** Creates a new instance of SmplNew */
    public SmplNew(String className, ArrayList param) {
        
        this.className = className;
        this.param = param;
        
    }

    public String toString() {
    
    return "New Class";
    }

    public Object visit(Visitor v, Object arg) throws SmplException {
        
        return v.visitSmplNew(this,arg);
    }

    public String getClassName() {
        return className;
    }

    public ArrayList getParam() {
        return param;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setParam(ArrayList param) {
        this.param = param;
    }
    

}
