/*
 * CreateWindow.java
 *
 * Created on December 20, 2006, 4:45 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package smpl.oop;

import javax.swing.JFrame;
import smpl.lang.SmplException;
import smpl.lang.SmplExp;
import smpl.lang.Visitor;

/**
 *
 * @author dephilio
 */
public class CreateWindow extends SmplExp {
    
    String title;
    JFrame frame = new JFrame();
    /** Creates a new instance of CreateWindow */
    public CreateWindow(String title) {
        
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toString() {
        
        return "";
    }

    public Object visit(Visitor v , Object arg) throws SmplException {
        
        
        return v.visitCreateWindow(this,arg);
        
    }

    public JFrame getFrame() {
        return frame;
    }


}
