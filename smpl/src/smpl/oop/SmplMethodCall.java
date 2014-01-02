package smpl.oop;

import smpl.lang.*;
import java.util.*;

/**
 * Representation for method invokation.
 *
 * 
 */
public class SmplMethodCall extends ASTNode3 {

    String methodName;		// name of method being invoked
    ASTNode classObject;
    ArrayList params;

    //SMPLExp [] arguments;	// expressions passed as arguments
    //SMPLExp exp;
    
    //SMPLEnvironment curEnv;
    //SmplClass owner;
    
    public SmplMethodCall(ASTNode classObject, String methodName,ArrayList params) {
        super(classObject, new SmplVar(methodName), new SmplSeq(params));
        this.classObject = classObject;
        this.methodName= methodName;
        this.params= params;

//this(id,(SMPLExp[])args.toArray(new SMPLExp[0]),o);
        

// 	name = id;
// 	arguments = (SMPLExp[])args.toArray(new SMPLExp[0]);
    }

    public void setParams(ArrayList params) {
        this.params = params;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setClassObject(SmplExp classObject) {
        this.classObject = classObject;
    }

    public ArrayList getParams() {
        return params;
    }

    public String getMethodName() {
        return methodName;
    }

    public ASTNode getClassObject() {
        return classObject;
    }

    /*
    public SMPLMethodCall(String id,ArrayList args,SmplClass o) {
	this(id,(SMPLExp[])args.toArray(new SMPLExp[0]),o);

// 	name = id;
// 	arguments = (SMPLExp[])args.toArray(new SMPLExp[0]);
    }

    public SMPLMethodCall(String id, SMPLExp[] args,SmplClass o) {
	name = id;
	arguments = args;
	owner = o;
    }

*/
/*public String getName(){return name;}


    /* Return the list of arguments supplied to this method. */
/*    public SMPLExp[] getArgs() {
	return arguments;
    }

    public void setEnvironment(SMPLEnvironment env){
		curEnv = env;
    }

    public SMPLEnvironment getEnvironment(){
		return curEnv;
    }
*/
    /**
     * Call the visitSMPMe method within <code>v</code> on this
     * method definition representation and the given argument.
     *
     * @param v a <code>Visitor</code> value
     * @param arg the data to be passed to this method's components
     * @return the result of visiting this method
     */
    public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitSmplMethodCall(this, arg);
    }

    public String toString() {
    return "Method Call Class";
    }
}

