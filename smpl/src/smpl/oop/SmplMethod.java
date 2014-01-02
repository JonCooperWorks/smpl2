package smpl.oop;
/*
 * SmplMethod.java
 */



import java.util.ArrayList;
import smpl.lang.SmplException;
import smpl.lang.SmplExp;
import smpl.lang.Visitor;
import smpl.sys.SmplEnvironment;
// import smpl.sys.SMPLEnvironment;


public class SmplMethod extends SmplExp{

    private String name;		// name of this method
    private ArrayList parameters;	// parameters in this method definition
    private SmplEnvironment localEnv = new SmplEnvironment();
    private ArrayList<SmplExp> MethBody;
    private String boundedClass;
    
    
    public SmplMethod(ArrayList varList, ArrayList body){
        
        
        this.parameters = varList;
        this.MethBody = body;
        System.out.println("Method Constructor");
    }

    public void setParameters(ArrayList<String> parameters) {
        this.parameters = parameters;
    }

    public void setOwner(SmplClass owner) {
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMethBody(ArrayList<SmplExp> MethBody) {
        this.MethBody = MethBody;
    }

    public void setLocalEnv(SmplEnvironment localEnv) {
        this.localEnv = localEnv;
    }

    public SmplClass getOwner() {
        return owner;
    }

    public ArrayList<SmplExp> getMethBody() {
        return MethBody;
    }

    public SmplEnvironment getLocalEnv() {
        return localEnv;
    }

    public void setBoundedClass(String boundedClass) {
        this.boundedClass = boundedClass;
    }

    public ArrayList getParameters() {
        return parameters;
    }

    public String getBoundedClass() {
        return boundedClass;
    }

    
//    SmplExp body;		// body of this method
	//private SmplSequence body;
//     SMPLEnvironment closingEnv;
	private SmplClass owner;

    /*
    public SmplMethod(String id, ArrayList params, SmplExp bodyExp) {
	this(id,(String[]) params.toArray(new String[0]),bodyExp);
    }

    public SmplMethod(String id, String[] params, SmplExp bodyExp) {
	name = id;
	parameters = params;
	body = bodyExp;
    }*/
        
        /*

	public SmplMethod(String id, ArrayList params, SmplSequence bodySeq) {
		this(id,(String[]) params.toArray(new String[0]),bodySeq);
    }

    public SmplMethod(String id, String[] params, SmplSequence bodySeq) {
	name = id;
	parameters = params;
	body = bodySeq;}
*/
    public final String getName() {
	return name;
    }
/*
    public final String[] getParameters() {
	return parameters;
    }
 */
/*
    public final SmplExp getBody() {
	return body;
    }
*/
/*
  	public final SmplSequence getBody() {
		return body;
    }
*/
//     public void setEnvironment(SMPLEnvironment env){
// 		closingEnv = env;
//     }
//
//     public SMPLEnvironment getEnvironment(){
// 		return closingEnv;
//     }

   public Object visit(Visitor v, Object arg) throws SmplException {
	return v.visitSmplMethod(this, arg);
    }

//     public String toString() {
// 	String result = "fun " + name + " [";
// 	if (parameters.length != 0) {
// 	    for (int i = 0; i < parameters.length - 1; i++) {
// 		result = result + parameters[i] + ", ";
// 	    }
// 	    result = result + parameters[parameters.length - 1] ;
// 	}
//
// 	result = result + "] = " + body.toString() + "\nend\n";
// 	return result;
//     }

    public String toString() {
        
        return "Method" + this.name;
    }

}
