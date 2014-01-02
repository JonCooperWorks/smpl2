package smpl.lang;

import java.math.BigInteger;

public abstract class SmplNum extends SmplLiteral {

    public static final int HEX = 16;
    public static final int DEC = 10;
    public static final int BIN = 2;

    protected SmplNum(){}

    public boolean isNum() {
	return true;
    }

    public boolean isInt() {
	return false;
    }

    public boolean isDouble() {
	return false;
    }

    public SmplNum numVal() {
	return this;
    }

    public SmplBool eq(SmplLiteral n) throws SmplException {
	if (n.isNum())
	    if (doubleVal() == n.doubleVal())
		return TRUE;
	return FALSE;
    }

    public SmplBool ne(SmplLiteral n) throws SmplException {
	if (n.isNum())
	    if (doubleVal() != n.doubleVal())
		return TRUE;
	return FALSE;
    }

    public SmplBool lt(SmplLiteral n) throws SmplException {
	if (n.isNum())
	    if (doubleVal() < n.doubleVal())
		return TRUE;
	return FALSE;
    }

    public SmplBool gt(SmplLiteral n) throws SmplException {
	if (n.isNum())
	    if (doubleVal() > n.doubleVal())
		return TRUE;
	return FALSE;
    }

    public SmplBool le(SmplLiteral n) throws SmplException {
	if (n.isNum())
	    if (doubleVal() <= n.doubleVal())
		return TRUE;
	return FALSE;
    }

    public SmplBool ge(SmplLiteral n) throws SmplException {
	if (n.isNum())
	    if (doubleVal() >= n.doubleVal())
		return TRUE;
	return FALSE;
    }
    
    public abstract SmplLiteral add(SmplLiteral n) throws SmplWrongTypeException;
    public abstract SmplLiteral sub(SmplLiteral n) throws SmplWrongTypeException;
    public abstract SmplLiteral mul(SmplLiteral n) throws SmplWrongTypeException;
    public abstract SmplLiteral pow(SmplLiteral n) throws SmplWrongTypeException;
    public abstract SmplLiteral div(SmplLiteral n) throws SmplWrongTypeException;
    public abstract SmplLiteral mod(SmplLiteral n) throws SmplWrongTypeException;
    public abstract SmplLiteral bnot() throws SmplWrongTypeException;
    public abstract SmplLiteral bor(SmplLiteral n) throws SmplWrongTypeException;
    public abstract SmplLiteral band(SmplLiteral n) throws SmplWrongTypeException;
    public abstract SmplLiteral neg();

    public abstract String toString();

   

    /* Internal subclasses */

    public static class Int extends SmplNum {
	int val;
	int base;
// 	BigInteger big;

	public Int(int v) {
	    val = v;
	    base = 10;
	}

	public Int(int v, int b) {
	    val = v;
	    base = b;
	}

// 	public Int(BigInteger bi) {
// 	    BigInteger b = new BigInteger(Integer.MAX_VALUE);
// 	    if (bi.compareTo(new BigInteger(Integer.MAX_VALUE)) == 1)
// 		big = bi;
// 	    else
// 		val = bi.intValue();
// 	}

	public boolean isInt() {
	    return true;
	}

	public boolean isHex() {
	    return (base == SmplNum.HEX);
	}

	public boolean isDec() {
	    return (base == SmplNum.DEC);
	}

	public boolean isBin() {
	    return (base == SmplNum.BIN);
	}

	public double doubleVal() {
	    return (double) val;
	}

	public int intVal() {
	    return val;
	}

	public SmplLiteral add(SmplLiteral n) throws SmplWrongTypeException {
	    if (n.isInt())
		return new SmplNum.Int(val + n.intVal(), base);
	    else if (n.isDouble())
		return new SmplNum.Double(val + n.doubleVal());
	    
	    else
		throw new SmplWrongTypeException("number or point", "something else",
						 "Addition not supported for non-number '"
						 + n + "'\n");
	}

	public SmplLiteral sub(SmplLiteral n) throws SmplWrongTypeException {
	    if (n.isInt())
		return new SmplNum.Int(val - n.intVal(), base);
	    else if (n.isDouble())
		return new SmplNum.Double(val - n.doubleVal());
	    else
		throw new SmplWrongTypeException("number", "something else",
						 "Subtraction not supported for non-number '"
						 + n + "'\n");
	}

	public SmplLiteral mul(SmplLiteral n) throws SmplWrongTypeException {
	    if (n.isInt())
		return new SmplNum.Int(val * n.intVal(), base);
	    else if (n.isDouble())
		return new SmplNum.Double(val * n.doubleVal());
	    
	    else
		throw new SmplWrongTypeException("number or point", "something else",
						 "Multiplication not supported for non-number '"
						 + n + "'\n");
	}

	public SmplLiteral pow(SmplLiteral n) throws SmplWrongTypeException {
	    if (n.isInt())
		return new SmplNum.Int((int)Math.pow(val, n.intVal()), base);
	    else if (n.isDouble())
		return new SmplNum.Double(Math.pow(val, n.doubleVal()));
	    else
		throw new SmplWrongTypeException("number", "something else",
						 "Exponentiation not supported for non-number '"
						 + n + "'\n");
	}

	public SmplLiteral mod(SmplLiteral n) throws SmplWrongTypeException {
	    if (n.isInt())
		return new SmplNum.Int(val % n.intVal(), base);
	    else if (n.isDouble())
		return new SmplNum.Double(val % n.doubleVal());
	    else 
		throw new SmplWrongTypeException("number", "something else",
						 "Modulo not supported for non-number '"
						 + n + "'\n");
	}

	public SmplLiteral div(SmplLiteral n) throws SmplWrongTypeException {
	    if (n.isInt()) {
		int argVal = n.intVal();
		int result = val / argVal;
		if (result * argVal == val)
		    return new SmplNum.Int(result, base);
		else
		    return new SmplNum.Double(val / n.doubleVal());
	    } else if (n.isDouble())
		return new SmplNum.Double(val / n.doubleVal());
	    else
		throw new SmplWrongTypeException("number", "something else",
						 "Division not supported for non-number '"
						 + n + "'\n");
	}

	public SmplLiteral bnot() throws SmplWrongTypeException {
	    return new SmplNum.Int((int) ~val, base);
	}

	public SmplLiteral bor(SmplLiteral n) throws SmplWrongTypeException {
	    if (n.isInt())
		return new SmplNum.Int(val | n.intVal(), base);
	    /*
	    else if (n.isPoint())
	    return new SmplPoint(val | n.getX(), val | n.getY());
	    
	    */
	    	else
		throw new SmplWrongTypeException("integer or point", "something else",
						 "Bitwise Or not supported for non-integer '"
						 + n + "'\n");
	}
	
	public SmplLiteral band(SmplLiteral n) throws SmplWrongTypeException {
	    if (n.isInt())
		return new SmplNum.Int(val & n.intVal(), base);
	   /*
	    else if (n.isPoint())
	    return new SmplPoint(val & n.getX(), val & n.getY());
	    */
	    else
		throw new SmplWrongTypeException("number or point", "something else",
						 "Bitwise And not supported for non-integer '"
						 + n + "'\n");
	}

	public SmplLiteral neg() {
	    return new SmplNum.Int(-val, base);
	}

	public String toString() {
	    String pre = (isHex() ? "#x" : isBin() ? "#b" : "");
	    
	    return (val < 0 ? "-" : "" ) + pre + Integer.toString(Math.abs(val), base);
	}
    public int getChildCount(){return 0;}
    public ASTNode getChild(int i){return null;}

    }

    public static class Double extends SmplNum {
	double val;

	public Double(double v) {
	    val = v;
	}

	public boolean isDouble() {
	    return true;
	}

	public double doubleVal() {
	    return val;
	}

	public int intVal() {
	    return (int) val;
	}

	public SmplLiteral add(SmplLiteral n) throws SmplWrongTypeException {
	    if (n.isNum())
		return new SmplNum.Double(val + n.doubleVal());
/*	    
	    else if (n.isPoint())
	  return new SmplPoint(intVal() + n.getX(), intVal() + n.getY());
	   */
	    else
		throw new SmplWrongTypeException("number or point", "something else",
						 "Addition not supported for non-number '"
						 + n + "'\n");
	}
	
	public SmplLiteral sub(SmplLiteral n) throws SmplWrongTypeException {
	    if (n.isNum())
		return new SmplNum.Double(val - n.doubleVal());
	    else
		throw new SmplWrongTypeException("number", "something else",
						 "Subtraction not supported for non-number '"
						 + n + "'\n");
	}
	
	public SmplLiteral mul(SmplLiteral n) throws SmplWrongTypeException {
	    if (n.isNum())
		return new SmplNum.Double(val * n.doubleVal());
	    /*
	    else if (n.isPoint())
	    return new SmplPoint(intVal() * n.getX(), intVal() * n.getY());
	    */
	    else
		throw new SmplWrongTypeException("number or point", "something else",
						 "Multiplication not supported for non-number '"
						 + n + "'\n");
	}
	
	public SmplLiteral div(SmplLiteral n) throws SmplWrongTypeException {
	    if (n.isNum())
		return new SmplNum.Double(val / n.doubleVal());
	    else 
		throw new SmplWrongTypeException("number", "something else",
						 "Division not supported for non-number '"
						 + n + "'\n");
	}
	
	public SmplLiteral mod(SmplLiteral n) throws SmplWrongTypeException {
	    if (n.isNum())
		return new SmplNum.Double(val % n.doubleVal());
	    else 
		throw new SmplWrongTypeException("number", "something else",
						 "Modulo not supported for non-number '"
						 + n + "'\n");
	}

	public SmplLiteral pow(SmplLiteral n) throws SmplWrongTypeException {
	    if (n.isNum())
		return new SmplNum.Double(Math.pow(val, n.doubleVal()));
	    else
		throw new SmplWrongTypeException("number", "something else",
						 "Exponentiation not supported for non-number '"
						 + n + "'\n");
	}

	public SmplLiteral bnot() throws SmplWrongTypeException {
	    throw new SmplWrongTypeException("bitwise operation not (~) " +
					     "is not allowed for " +
					     "floating point numbers");
	}

	public SmplLiteral bor(SmplLiteral n) throws SmplWrongTypeException {
	    throw new SmplWrongTypeException("bitwise operation or (|) " +
					     "is not allowed " +
					     "for floating point numbers");
	}

	public SmplLiteral band(SmplLiteral n) throws SmplWrongTypeException {
	    throw new SmplWrongTypeException("bitwise operation and (&) " +
					     "is not allowed for " +
					     "floating point numbers");
	}

	public SmplLiteral neg() {
	    return new SmplNum.Double(-val);
	}

	public String toString() {
	    return java.lang.Double.toString(val);
	}
    public int getChildCount(){return 0;}
    public ASTNode getChild(int i){return null;}

    }

    public String stringVal() throws SmplWrongTypeException {
	throw new SmplWrongTypeException("String", "nuthin");
    }
    
    public String charVal() throws SmplWrongTypeException {
	throw new SmplWrongTypeException("Char", "nuthin");
    }

    public Object visit(Visitor v, Object info) throws SmplException{return null;}

    
}
