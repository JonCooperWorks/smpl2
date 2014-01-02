package smpl.sys;

import smpl.lang.*;
import java.util.*;

/** Dictionary class to support runtime bookkeeping needed by the
    interpreter. */
public class SmplEnvironment {
    SmplEnvironment parent = null;
    Hashtable tbl;

    /**
     * a dictionary may either be initialized with or without a
     * parent.
     */
    public SmplEnvironment() {
	super();
	tbl = new Hashtable();
    }

    public void setParent(SmplEnvironment parent) {
        this.parent = parent;
    }

    protected SmplEnvironment(SmplEnvironment p) {
	parent = p;
	tbl = new Hashtable();
    }
    
    /**
     * Return the value associated with the given name in this
     * dictionary.  Returns null if there is no such association.
     */
    public SmplLiteral get(String name) throws SmplLookupException {
	SmplLiteral result = (SmplLiteral)tbl.get(name);
	if (result == null)
	    if (parent != null)
		return parent.get(name);
	    else
		throw new SmplLookupException(name);
	else 
	    return result;
    }

     public Object getOther(String name) throws SmplLookupException {
	Object result = tbl.get(name);
	if (result == null)
	    if (parent != null)
		return parent.get(name);
	    else
		throw new SmplLookupException(name);
	else 
	    return result;
    }
    public void put(String var, Object value) {
	tbl.put(var, value);
    }
    /** Binds the given name to the given value in this dictionary. */
    public void put(String var, SmplLiteral value) {
	tbl.put(var, value);
    }

    /**
     * update the value in the dictionary, wherever it was initially
     * stored. this is so that values that are not supposed to be
     * local to any limited scope can be updated from within. this is
     * something of a combination between get() and put()
     */
    public void update(String var, SmplLiteral val) {
	// first, check to see that it's defined at all
	// if it isn't put it in the local scope
	try {
	    get(var);
// 	System.err.println("found " + var + " and it's value is  ... " + get(var));

	} catch (SmplLookupException sle) {
	    put(var, val);
	}
	// otherwise, find where it's defined, and update it there
	SmplLiteral result = (SmplLiteral)tbl.get(var);
	if (result != null)
	    put(var, val);
	else if (parent != null)
	    parent.update(var, val);
    }

    /** Return the underlying set of bindings as an abstract Map.
	(For internal use only).
    */
    protected Map getMap() {
	return tbl;
    }

    /** Return a new Dictionary that contains the bindings of both
	this dictionary and the argument dictionary.  Where conflicting
	bindings occur, the binding in the argument dictionary takes
	precedence.
    */
    public SmplEnvironment merge(SmplEnvironment d) {
	SmplEnvironment result = new SmplEnvironment(this);
	result.getMap().putAll(d.getMap());
	return result;
    }

    /**
     * return a new dictionary, with this as the parent
     */
    public SmplEnvironment spawn() {
	return new SmplEnvironment(this);
    }

   
    
    public SmplEnvironment smplZip(Vector names, Vector values) {
	SmplEnvironment result = new SmplEnvironment(this);
	for(int i = 0; i < names.size(); i++)
	    result.put((String) names.elementAt(i),
		       (SmplLiteral) values.elementAt(i));
	return result;
    }

    
    public SmplEnvironment append(String name, SmplLiteral val) {
	SmplEnvironment result = new SmplEnvironment(this);
	result.put(name, val);
	return result;
    }
}
