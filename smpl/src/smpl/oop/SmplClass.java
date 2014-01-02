package smpl.oop;

import java.util.HashMap;
import smpl.lang.*;

import java.util.ArrayList;

import smpl.sys.SmplEnvironment;



public class SmplClass extends SmplExp{
    
    //SVG shared environment;
    //Instanve environment
    
    private SmplEnvironment sharedEnv = new SmplEnvironment();
    private SmplEnvironment publicEnv = new SmplEnvironment();
    private SmplEnvironment protectedEnv = new SmplEnvironment();
    
    private SmplClass parent;
    private String myParent;
    
    private ArrayList<Object> temp;
    private HashMap tempMap = new HashMap();
    private String identifier;
    private SmplExp body;
    private ArrayList<SmplExp> classBody;

    public String getMyParent() {
        return myParent;
    }

    public ArrayList<Object> getTemp() {
        return temp;
    }

    public void setMyParent(String myParent) {
        this.myParent = myParent;
    }

    public void setTemp(ArrayList<Object> temp) {
        this.temp = temp;
    }
    

    public SmplClass(ArrayList body, String var){
        
        this.classBody = body ;
        this.myParent = var;
        
        
    }
    
    public SmplClass(ArrayList body){
      //  System.out.println("In Class Constructor");
        this.classBody = body ;
        
        
        
    }

    public HashMap getTempMap() {
        return tempMap;
    }

    public void setTempMap(HashMap tempMap) {
        this.tempMap = tempMap;
    }
    

    
    /**
     *	Constructor for an SmplClass which extends another class
     */
    public SmplClass(String id,SmplClass p,SmplExp bod){
        identifier = id;
        parent = p;
        body = bod;
    }
    
    /**
     *	Constructor for an SmplClass
     */
    public SmplClass(String id,SmplExp bod){
        this(id,null,bod);
    }
    
    
    /**
     *	Environment which this class extends
     */
        /*
        public void setClosingEnvironment(SmplEnvironment env){
                globalEnv = env;
        }
         */
    /**
     *	REturns the "global" environment for this class
     */
    public SmplEnvironment getPublicEnv(){
        return publicEnv;
    }
    
    public SmplEnvironment getSharedEnv(){
        return sharedEnv;
    }
    
    public SmplEnvironment getProtectedEnv(){
		return protectedEnv;
	}

    public void setSharedEnv(SmplEnvironment sharedEnv) {
        this.sharedEnv = sharedEnv;
    }

    public void setPublicItems(ArrayList<Object> publicItems) {
        this.temp = publicItems;
    }

    public void setPublicEnv(SmplEnvironment publicEnv) {
        this.publicEnv = publicEnv;
    }

    public void setProtectedEnv(SmplEnvironment protectedEnv) {
        this.protectedEnv = protectedEnv;
    }

    public void setParent(SmplClass parent) {
        this.parent = parent;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setClassBody(ArrayList<SmplExp> classBody) {
        this.classBody = classBody;
    }

    public void setBody(SmplExp body) {
        this.body = body;
    }

    public ArrayList<Object> getPublicItems() {
        return temp;
    }

    public SmplClass getParent() {
        return parent;
    }

    public String getIdentifier() {
        return identifier;
    }

    public ArrayList<SmplExp> getClassBody() {
        return classBody;
    }

    public SmplExp getBody() {
        return body;
    }

    
// 	/**
// 	  *	Constructor for an SmplClass which extends another class
// 	  */
// 	public SmplClass(SmplClass p, Object genv,SmplFunctionDictionary funDict){
// 		parent = p;
// 		globalEnv = genv;
// 		sharedEnv = new SmplEnvironment((SmplEnvironment)genv);
// 		temp = new ArrayList();
// 		methods = funDict;
// 	}
//
// 	/**
// 	  *	Constructor for an SmplClass with no parent class
// 	  */
// 	public SmplClass(Object genv,SmplFunctionDictionary funDict){
// 		this (null,genv,funDict);
// 	}
//
// 	/**
// 	  *	Populates public list
// 	  */
// 	public void addPublicItem(Object item){
// 		temp.add(item);
// 	}
//
// 	/**
// 	  *	Returns true if the given item is a public member
// 	  */
// 	public boolean isPublic(Object item){
// 		return temp.contains(item);
// 	}
    
    
    public Object visit(Visitor v, Object arg) throws
            SmplException {
        return v.visitSmplClass(this, arg); }
    
    public String toString() {
        
        
        return "Class "+ this.identifier;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}