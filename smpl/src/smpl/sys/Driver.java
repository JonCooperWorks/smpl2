package smpl.sys;

import smpl.lang.*;
import java.io.*;
import java_cup.runtime.*;

public class Driver {
    SystemSettings settings;
    Visitor visitor;
    private static SmplEnvironment dict=new SmplEnvironment();

    public Driver() {
	settings = new SystemSettings();
    } // Driver constructor

    public Driver(String[] args) {
	settings = new SystemSettings(args);
    }

    public SystemSettings getSettings() {
	return settings;
    }

    public Visitor getVisitor() {
	return visitor;
    }

    public Object run() {
	Class visitorClass = settings.getVisitorClass();
	Object result = null;
	try {
	    // instantiate and initialise the visitor
	    visitor = (Visitor) visitorClass.newInstance();
	    visitor.init();

	    // process the files on the command line
	    String[] files = settings.getFileNames();
	    // for each file
	    for (int i = 0; i < files.length; i++) {
		// setup a parser for each
		try {
		    result = parseEvalReturn(new FileInputStream(files[i]), visitor);
		    visitor.display(result);
		} catch (FileNotFoundException fnfe) {
		    System.out.println("Could not find file " + files[i]);
		}
	    }
	} catch (InstantiationException ie) {
	    System.out.println("Unable to instantiate " + visitorClass);
	    System.exit(1);
	} catch (IllegalAccessException iae) {
	    System.out.println("Unable to instantiate " + visitorClass +
			       "without any arguments to constructor.");
	    System.exit(1);

	}
	return result;
    }

    public static Object parseEvalReturn(InputStream is, Visitor visitor) {

	SmplParser parser;
	SmplProgram program;
    Object obj2=new Object();

	try {
	    parser = new SmplParser(new SmplLexer(is));
        Symbol obj = parser.parse();
        program = (SmplProgram) obj.value;
	    if (program != null)
        {
            try {
                    //is.close();
                    obj2=visitor.visitProgram(program, dict);
                    
                }
            catch (SmplException le) {
                System.out.println("Runtime Error: " + le.getMessage());
                le.printStackTrace();
            }
        }
		} catch (Exception e) {
	    System.out.println("Syntax Error: " + e.getMessage());
	    e.printStackTrace();
	}
	return obj2;//throws SpmlException;		// if something went wrong
    }

    public static void main(String[] args) {
	Driver driver = new Driver(args);
	System.out.println(driver.run());
    }
} // Driver
