/*
 * SmplClassObjectObject.java
 *
 * Created on December 27, 2006, 8:49 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package smpl.objects;

import java.util.HashMap;
import smpl.lang.*;

import java.util.ArrayList;

import smpl.sys.SmplEnvironment;


/**
 *
 * @author Administrator
 */


public class SmplClassObject extends SmplExp{
    
    //SVG shared environment;
    //Instanve environment
    
    private SmplEnvironment sharedEnv = new SmplEnvironment();
    private SmplEnvironment publicEnv = sharedEnv.spawn();
    private SmplEnvironment protectedEnv = publicEnv.spawn();
    
    private SmplClassObject parent;
    private String myParent;
    
    private ArrayList<Object> temp;
    private HashMap tempMap = new HashMap();
    private String identifier;
    private SmplExp body;
    private ArrayList<SmplExp> classBody;
    
    
    public SmplClassObject(ArrayList body, String var){
            this.classBody = body ;
        this.myParent = var;
   }
    
    public SmplClassObject(ArrayList body){
      //  System.out.println("In Class Constructor");
        this.classBody = body ;
    }
    
    /**
     *	Constructor for an SmplClassObject which extends another class
     */
    public SmplClassObject(String id,SmplClassObject p,SmplExp bod){
        identifier = id;
        parent = p;
        body = bod;
    }
    
    /**
     *	Constructor for an SmplClassObject
     */
    public SmplClassObject(String id,SmplExp bod){
        this(id,null,bod);
    }

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
    
    public HashMap getTempMap() {
        return tempMap;
    }

    public void setTempMap(HashMap tempMap) {
        this.tempMap = tempMap;
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

    public void setParent(SmplClassObject parent) {
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

    public SmplClassObject getParent() {
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
    public Object visit(Visitor v,Object arg) throws SmplException{return null;}


    
// 	/**
// 	  *	Constructor for an SmplClassObject which extends another class
// 	  */
// 	public SmplClassObject(SmplClassObject p, Object genv,SmplFunctionDictionary funDict){
// 		parent = p;
// 		globalEnv = genv;
// 		sharedEnv = new SmplEnvironment((SmplEnvironment)genv);
// 		temp = new ArrayList();
// 		methods = funDict;
// 	}
//
// 	/**
// 	  *	Constructor for an SmplClassObject with no parent class
// 	  */
// 	public SmplClassObject(Object genv,SmplFunctionDictionary funDict){
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
    
    public String toString() {
        return "Class "+ this.identifier;
    }    
}

