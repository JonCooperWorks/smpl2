package smpl.oop;

import java.util.*;
import smpl.lang.*;

public class SmplThread extends SmplExp{
	
	String var;
	ArrayList body;
	
	public SmplThread(String var, ArrayList body){
		
		
		this.var = var;
		this.body = body;
		
		
		
	}
	
	public String getName(){
		
		
		return var;
	}
	
	public ArrayList getBody(){
		
		
		return body;
	}
	public String toString(){
		
		
		return "";
	}
	
	public Object visit(Visitor v , Object arg) throws SmplException{
		
		
		
		
		return v.visitSmplThread(this,arg);
	}
}