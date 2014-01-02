package smpl.oop;

import java.util.*;
import smpl.lang.*;

public class SmplThreadCall extends SmplExp{
	
	String var;
        Object tO ;
        SmplThreadCall thr = this;
	
	public SmplThreadCall(String var){
		
		
		this.var = var;
		
		
		
		
	}
	
	public String getName(){
		
		
		return this.var;
	}
	
	public String toString(){
		
		
		return "";
	}
	
	public Object visit(final Visitor v ,final Object arg) throws SmplException{
		
		/*
		Thread t = new Thread(){
                    
                    public void run(){
                try {
                     
                     
                    tO = v.visitSmplThreadCall(thr,arg); 
                } catch (SmplException ex) {
                    ex.printStackTrace();
                } 
                    }
                    
                };
                t.start();
		
		return tO;
                 **/
            return v.visitSmplThreadCall(this,arg); 
	}
}