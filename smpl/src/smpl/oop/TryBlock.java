package smpl.oop;

import java.util.*;
import smpl.lang.*;


public class TryBlock extends SmplExp{
	
	ArrayList tryStat;
	ArrayList catStat;
	
	
	public TryBlock(ArrayList tryStat, ArrayList catStat){
		
		
	this.tryStat = tryStat;
	this.catStat = catStat;
		
		
		
		
	}
	
	public ArrayList getTryStat(){
		
		
		
		return this.tryStat;
	}
	
	public ArrayList getCatStat(){
		
		
		
		return this.catStat;	
	}
	
	
	
	public Object visit(Visitor v , Object arg) throws SmplException{
		
		
		return v.visitTryBlock(this,arg);
		
	}
	
	public String toString(){
		
		
		return "";
	}
	
}