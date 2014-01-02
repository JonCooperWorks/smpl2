package smpl.sys;

import smpl.lang.*;

public class SystemSettings {
    private Class visitorClass;	// name of Visitor implementation
    private int fileCount; // number of files supplied on command line
    private String[] fileNames;	// names of files supplied on command line

    public SystemSettings() {
	super();
	visitorClass = smpl.lang.SmplEvaluator.class;
	fileCount = 0;
	fileNames = new String[0];
    } // SystemSettings constructor

    public SystemSettings(String[] cmdLineArgs) {
	// parse command line
	this();
	parseCommandLine(cmdLineArgs);
    }

    protected void parseCommandLine(String[] args) {
	fileNames = new String[args.length];
	for (int i = 0; i < args.length; i++) {
	    String arg = args[i];
	    if (arg.equals("-v")) {
		visitorClass = findClass(args[i+1], visitorClass);
		i++;
	    } else {
		fileNames[fileCount] = args[i];
		fileCount++;
	    }
	}
    }

    public static Class findClass(String name, Class defaultClass) {
	try {
	    return Class.forName(name);
	} catch (ClassNotFoundException cnfe) {
	    System.err.println("Sorry, I could not locate a class " +
			       "named " + name);
	    return defaultClass;
	}
    }

    public Class getVisitorClass() {
	return visitorClass;
    }

    /** @return the number of files given on the command line */
    public int getFileCount() {
	return fileCount;
    }

    /** Return the names of the files passed on the command line as an
     * array of strings.*/
    public String[] getFileNames() {
	String[] result = new String[fileCount];
	for (int i = 0; i < fileCount; i++) {
	    result[i] = fileNames[i];
	}
	return result;
    }

} // SystemSettings
