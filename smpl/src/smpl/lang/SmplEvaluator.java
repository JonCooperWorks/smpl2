package smpl.lang;

//import smpl.database.*;
import smpl.objects.*;
import smpl.objects.SmplVector;
import smpl.oop.*;
import smpl.sys.*;
//import smpl.gui.*;
import java.util.*;
//import javax.swing.*;



public class SmplEvaluator implements Visitor {
    
    CreateWindow win;
    ASTNode stmts;
    ArrayList thrList ;
    SmplEvaluator smplEval = this;
    SmplThreadCall tCall = null;;
    Object tObject= null;;
    String methName;
    ArrayList paramList;
    protected SmplEnvironment dict;

    private SmplClass myGlobalClass;

    private SmplEnvironment methEnv;
    
    
    public void init(){
        dict = new SmplEnvironment();
    }
    
    public void display(Object val){} // displays the result of a computation
    
    public Object visitProgram(SmplProgram prog, Object info)
    throws SmplException {
        SmplSeq b = prog.getBody();
        info=b.visit(this, info);
        return info;
    }
    
    
    public Object visitSequence(SmplSeq seq, Object arg)
    throws SmplException {
        Object result = null;
        ASTNode e;
        for (int i = 0; i < seq.getChildCount(); i++) {
            e =  seq.getChild(i);
            try {
                result = e.visit(this, arg);
            } catch (SmplEvalException see) {
                throw new SmplEvalException(see.getMessage(), e);
            }
        }
        return result;
    }
    
    public Object visitCmdPair(CmdPair cmd, Object arg)
    throws SmplException {
        SmplLiteral e1, e2 = null;
        try {
            e1 = (SmplLiteral) cmd.getChild(0).visit(this, arg);
            e2 = (SmplLiteral) cmd.getChild(1).visit(this, arg);
        } catch (ClassCastException cce) {
            throw new SmplWrongTypeException("literal value", cce.getMessage(),
                    "Error performing operation: " + cmd);
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation: " +
                    cmd + "type mismatch:" + swte.getMessage(), cmd);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage(), cmd);
        }
        SmplPair result = new SmplPair.Nil();
        try {
            result = new SmplPair(e1, (SmplPair)e2);
        } catch (ClassCastException cce) {
            result = new SmplPair(e1, e2);
        }
        return result;
    }
    
    public Object visitCmdList(SmplList cmd, Object arg)
    throws SmplException {
        SmplPair result = new SmplPair.Nil();
        SmplLiteral e1 = null;
        int c = cmd.getChildCount();
        for (int i = 1; i <= c; i++) {
            try {
                e1 = (SmplLiteral) cmd.getChild(c - i).visit(this, arg);
            } catch (ClassCastException cce) {
                throw new SmplWrongTypeException("literal value", cce.getMessage(),
                        "Error performing operation: " + cmd);
            } catch (SmplWrongTypeException swte) {
                throw new SmplEvalException("Error performing operation: " +
                        cmd + "type mismatch: " + swte.getMessage(), cmd);
            } catch (SmplEvalException see) {
                throw new SmplEvalException(see.getMessage(), cmd);
            }
            result = new SmplPair(e1, result);
        }
        
        return result;
    }
    
    public Object visitCmdCar(SmplCar cmd, Object arg)
    throws SmplException {
        SmplPair e = null;
        try {
            e = (SmplPair) cmd.getChild(0).visit(this, arg);
        } catch (ClassCastException cce) {
            throw new SmplWrongTypeException("pair", cce.getMessage(),
                    "Error performing operation: " + cmd);
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation: " +
                    cmd + "type mismatch:" + swte.getMessage(), cmd);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage(), cmd);
        }
        
        return e.car();
    }
    
    public Object visitCmdCdr(SmplCdr cmd, Object arg)
    throws SmplException {
        SmplPair e = null;
        try {
            e = (SmplPair) cmd.getChild(1).visit(this, arg);
        } catch (ClassCastException cce) {
            throw new SmplWrongTypeException("pair", cce.getMessage(),
                    "Error performing operation: " + cmd);
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation: " +
                    cmd + "type mismatch: " + swte.getMessage(), cmd);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage(), cmd);
        }
        
        return e.cdr();
    }
    
    public Object visitCmdVec(SmplCVector cmd, Object arg)
    throws SmplException {
        
        SmplVector result = new SmplVector();
        Object item = null;
        ASTNode e;
        for (int i = 0; i < cmd.getChildCount(); i++) {
            e =  cmd.getChild(i);
            try {
                item = e.visit(this, arg);
                
                result.addi((ArrayList) item);
            } catch (ClassCastException cce) {
                try {
                    result.addi((SmplLiteral) item);
                    
                } catch (ClassCastException cce2) {
                    throw new SmplWrongTypeException("literal value", cce2.getMessage(),
                            "Error performing operation: " + cmd);
                }
            } catch (SmplEvalException see) {
                throw new SmplEvalException(see.getMessage(), e);
            }
        }
        
        return result;
    }
    
    public Object visitExpSubVec(SmplSubVec cmd, Object arg)
    throws SmplException {
        SmplNum e1 = null;
        SmplProc e2 = null;
        ArrayList result = new ArrayList();
        SmplEnvironment d = (SmplEnvironment) arg;
        
        try {
            e1 = (SmplNum) cmd.getChild(0).visit(this, arg);
            e2 = (SmplProc) cmd.getChild(1).visit(this, arg);
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation : " +
                    cmd + "type mismatch :" + swte.getMessage(), cmd);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage(), cmd);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            return e1;
        }
        
        String var = "";
        var = e2.getListArg();
        if (var.equals("")) {
            String[] v = e2.getArgs();
            var = v[0];
        }
        if (var.equals(""))
            throw new SmplEvalException("Error in Subvector function. " +"Function has no variable", cmd);
        
        for (int i = 0; i < e1.intVal(); i++)
        {
            SmplEnvironment d2 = d.append(var, new SmplNum.Int(i));
            result.add(e2.getBody().visit(this, d2));
        }
        
        return result;
    }
    
    public Object visitCmdVecAcc(SmplVecAcc cmd, Object arg)
    throws SmplException {
        SmplVector v = null;
        SmplNum n = null;
        try {
            try {
                
                v = (SmplVector) cmd.getChild(0).visit(this, arg);
            } catch (ClassCastException cce1) {
                throw new SmplWrongTypeException("vector", cce1.getMessage(),
                        "Error performing operation: " + cmd);
            }
            try {
                n = (SmplNum) cmd.getChild(1).visit(this, arg);
            } catch (ClassCastException cce2) {
                throw new SmplWrongTypeException("number", cce2.getMessage(),
                        "Error performing operation: " + cmd);
            }
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation : " +
                    cmd + "type mismatch: " + swte.getMessage(), cmd);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage(), cmd);
        }
        
        return v.get(n);
    }
    
    public Object visitCmdSize(SmplSize cmd, Object arg)
    throws SmplException {
        SmplVector v = null;
        try {
            v = (SmplVector) cmd.getChild(0).visit(this, arg);
        } catch (ClassCastException cce) {
            throw new SmplWrongTypeException("vector", cce.getMessage(),
                    "Error performing operation: " + cmd);
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation: " +
                    cmd + "type mismatch:" + swte.getMessage(), cmd);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage(), cmd);
        }
        
        return v.size();
    }
    
    public Object visitIsPair(SmplIsPair cmd, Object arg)
    throws SmplException {
        SmplPair p = null;
        try {
            p = (SmplPair) cmd.getChild(0).visit(this, arg);
        } catch (ClassCastException cce) {
            return new SmplLit(false);
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation: " +
                    cmd + "type mismatch:" + swte.getMessage(), cmd);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage(), cmd);
        }
        
        return new SmplLit(p.isPair());
    }
    
    public Object visitIsEqv(SmplIsEqv cmd, Object arg)
    throws SmplException {
        SmplBool result = new SmplBool(false);
        SmplLiteral e1 = null;
        SmplLiteral e2 = null;
        
        try {
            e1 = (SmplLiteral) cmd.getChild(0).visit(this, arg);
            e2 = (SmplLiteral) cmd.getChild(1).visit(this, arg);
        } catch (ClassCastException cce) {
            throw new SmplWrongTypeException("literal value", cce.getMessage(),
                    "Error performing operation: " + cmd);
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation: " +
                    cmd + "type mismatch:" + swte.getMessage(), cmd);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage(), cmd);
        }
        result=e1.eq(e2);
        return result;
        
    }
    
    public Object visitCmdSubstr(SmplSubstr cmd, Object arg)
    throws SmplException {
        SmplStr s = new SmplStr("");
        SmplNum e1, e2 = null;
        
        try {
            try {
                s = (SmplStr) cmd.getChild(0).visit(this, arg);
            } catch (ClassCastException cce1) {
                throw new SmplWrongTypeException("string", cce1.getMessage(),
                        "Error performing operation: " + cmd);
            }
            try {
                e1 = (SmplNum) cmd.getChild(1).visit(this, arg);
                e2 = (SmplNum) cmd.getChild(2).visit(this, arg);
            } catch (ClassCastException cce2) {
                throw new SmplWrongTypeException("number", cce2.getMessage(),
                        "Error performing operation: " + cmd);
            }
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage(), cmd);
        }
        
        return s.substr(e1, e2);
    }
    
    public Object visitSmplProcCall(SmplProcCall cmd, Object arg)
    throws SmplException {
        
        if (cmd.call) {
            SmplProcCall c;
            SmplPair p;
            ASTNode f =  cmd.getChild(0);
            try {
                p = (SmplPair) (cmd.getChild(1).visit(this, arg));
            } catch (ClassCastException cce1) {
                throw new SmplWrongTypeException("list or list variable", cce1.getMessage(),
                        "Error calling variable arity function: " + cmd);
            }
            ArrayList p1 = p.toLitArray();
            c = new SmplProcCall(f, new ExpSeq(p1));
            return c.visit(this, arg);
        }
        
        SmplEnvironment d = (SmplEnvironment) arg;
        SmplEnvironment d2;
        SmplVar v = null;
        SmplProc p = null;
        int l1, l2;
        String[] ls = {};
        String s, s2 = "";
        Object result;
        SmplProcDef pd = null;
        try {
            pd = (SmplProcDef) cmd.getChild(0);
            p = (SmplProc) pd.visit(this, arg);
        } catch (ClassCastException cce) {
            try {
                v = (SmplVar) cmd.getChild(0);
                s2 = v.getVar();
            } catch (ClassCastException cce1) {
                throw new SmplWrongTypeException("variable", cce1.getMessage(),
                        "Error calling function: " + cmd);
            }
            try {
                p = (SmplProc) d.get(s2);
            } catch (ClassCastException cce2) {
                throw new SmplWrongTypeException("function", cce2.getMessage(),
                        "Variable " + s2 +
                        " is not defined as a function: " + cmd);
            } catch (SmplLookupException sle) {
                methName = s2;
                paramList = ((ExpSeq)cmd.getChild(1)).getList();
                smpl.utils.Util.displayMsg("Call to undefined procedure::Trying to Recover.....");
                smpl.utils.Util.displayMsg("Recovered..........");
                //throw new SmplEvalException("Call to undefined procedure: " + s2);
                return getMethod();
            }
        }
        ls = p.getArgs();
        s = p.getListArg();
        
        l1 = ls.length;
        l2 = ((ASTNode) cmd.getChild(1)).getChildCount();
        
        if (s == "" && l1 != l2)
            throw new SmplEvalException("Procedure " + s2 + " must be called with exactly " +
                    (l1 == 0 ? "no" : ("" + l1)) + " argument" + (l1 == 1 ? "" : "s") + ". ",
                    cmd);
        
        if (s != "" && l1 > l2)
            throw new SmplEvalException("Procedure " + s2 + " must be called with at least " +
                    (l1 == 0 ? "no" : ("" + l1)) + " argument" + (l1 == 1 ? "" : "s") + ". ",
                    cmd);
        ExpSeq e = (ExpSeq) cmd.getChild(1);
        SmplPair pr = new SmplPair.Nil();
        SmplTuple t = null;
        ArrayList as = new ArrayList();
        try {
            pr = (SmplPair) e.visit(this, arg);
        } catch (ClassCastException cce) {
            try {
                t = (SmplTuple) e.visit(this, arg);
            } catch (SmplEvalException see) {
                throw new SmplEvalException("Error calling procedure " + s2 + "\n");
            }
            as = t.getContents();
            while (as.size() > l1)
                pr = new SmplPair((SmplLiteral) as.remove(as.size() - 1), pr);
        }
        Vector v1 = new Vector(l1 + 1);
        Vector v2 = new Vector(l1 + 1);
        
        ArrayList bs = new ArrayList();
        ArrayList vs = new ArrayList();
        
        try {
            bs = p.getExtraArgs();
            vs = p.getExtraVals();
        } catch (NullPointerException npe) {}
        
        for (int i = 0; i < l1; i++) {
            v1.add(ls[i]);
            v2.add(as.get(i));
        }
        
        for (int i = 0; i < bs.size(); i++) {
            v1.add(bs.get(i));
            v2.add(vs.get(i));
        }
        
        if (s != "" && (pr.size()).intVal() > 0) {
            v1.add(s);
            v2.add(pr);
        }
        d2 = d.smplZip(v1, v2);
        try {
            result = p.getBody().visit(this, d2);
        } catch (SmplEvalException see2) {
            throw new SmplEvalException("Error calling procedure " + v2 + "\n");
        }
        return result;
    }
    
    public Object visitProcDef(SmplProcDef cmd, Object arg)
    throws SmplException {
        String s = "";
        String[] ls = {};
        ASTNode e = null;
        SmplTuple t = new SmplTuple();
        ASTNode b = null;
        SmplEnvironment d = (SmplEnvironment) arg;
        
        
        
        if (cmd.getChildCount() == 2) {
            try {
                s = ((SmplVar) cmd.getChild(0)).getVar();
            } catch (ClassCastException cce1) {
                throw new SmplWrongTypeException("variable", cce1.getMessage(),
                        "Error defining procedure : " + cmd);
            }
            b =  cmd.getChild(1);
            return new SmplProc(s, b, d);
        } else if  (cmd.getChildCount() == 3) {
            try {
                e = (ExpSeq) cmd.getChild(0);
                for (int i = 0; i < e.getChildCount(); i++)
                    t.add((SmplVar) e.getChild(i));
            } catch (ClassCastException cce2) {
                throw new SmplWrongTypeException("argument list", cce2.getMessage(),
                        "Error defining procedure : " + cmd);
            }
            ls = new String[t.size()];
            for (int i = 0; i < t.size(); i++) {
                try {
                    ls[i] = ((SmplVar) t.get(i)).getVar();
                } catch (ClassCastException cce3) {
                    throw new SmplWrongTypeException("variable", cce3.getMessage(),
                            "Error defining procedure \n" +
                            "(arguments must all be variables) : " + cmd);
                }
            }
            s = "";
            try {
                e = (SmplVar) cmd.getChild(1);
            } catch (NullPointerException npe) {
            } catch (ClassCastException cce4) {
                if (e != null)
                    throw new SmplWrongTypeException("variable", cce4.getMessage(),
                            "Error defining procedure \n" +
                            "(arguments must all be variables) : " + cmd);
            }
            if (e != null)
                s = ((SmplVar)e).getVar();
            
            if (s == "")
                return new SmplProc(ls, cmd.getChild(2), d);
            else
                return new SmplProc(ls, s, (ASTNode)cmd.getChild(2), d);
            
        }
        throw new SmplEvalException("System error: Procedure definition failed.\n");
    }
    
    public Object visitCmdDef(SmplDef cmd, Object arg) throws SmplException {
        SmplVar v = null;
        SmplLiteral l = null;
        SmplTuple t = new SmplTuple();
        ExpSeq e = null;
        ASTNode b = cmd.getChild(1);
        SmplEnvironment d = (SmplEnvironment) arg;
        ASTNode result = null;
        ASTNode myClass = (ASTNode)cmd.getChild(1).visit(this,arg);
        //smpl.utils.Util.displayMsg("I AM:" + myClass.toString());
        if(myClass instanceof SmplClass){
            //smpl.utils.Util.displayMsg("In if");
            String var = ((SmplVar)cmd.getChild(0)).getVar();
            d.put(var,cmd.getChild(1));
            SmplLit lit =  new SmplLit(var);
            result =  myClass;
            smpl.utils.Util.displayMsg("Class "+var + " Defined");
            
        }else if(myClass instanceof SmplMethod){
            smpl.utils.Util.displayMsg("In if for Method");
            String var = ((SmplVar)cmd.getChild(0)).getVar();
            SmplMethod meth  = (SmplMethod) myClass;
            meth.setName(var);
            //SmplLit lit =  new SmplLit(var);
            result =  meth;
            smpl.utils.Util.displayMsg("Method "+var + " Defined");
            
        }else if(myClass instanceof CreateWindow){
            
            String var = ((SmplVar)cmd.getChild(0)).getVar();
            d.put(var,cmd.getChild(1));
            SmplLit lit =  new SmplLit(var);
            result =  myClass;
            smpl.utils.Util.displayMsg("Window "+var + " Defined");
            
            
            
        }/* else if(myClass instanceof DbaseConnect){
            
            String var = ((SmplVar)cmd.getLeftChild()).getVar();
            d.put(var,cmd.getRightChild());
            SmplLit lit =  new SmplLit(var);
            result = (SmplExp) myClass;
            
        }*/else if(myClass instanceof SmplLit){
            
            
            String var = ((SmplVar)cmd.getChild(0)).getVar();
            d.put(var,cmd.getChild(1));
            SmplLit lit =  new SmplLit(var);
            result =  myClass;
            
        }
        
        
        
        else{
            
            
            
            
            try {
                l = (SmplLiteral) b.visit(this, arg);
                result =  l;
            } catch (ClassCastException cce1) {
                throw new SmplWrongTypeException("literal value", cce1.getMessage(),
                        "Error Assigning value(s) : " + cmd);
            }
            try {
                e = (ExpSeq) cmd.getChild(0);
            } catch (ClassCastException cce2) {
                try {
                    v = (SmplVar) cmd.getChild(0);
                    d.update(v.getVar(), l);
                    return l;
                } catch (ClassCastException cce3) {
                    throw new SmplWrongTypeException("variable", cce3.getMessage(),
                            "Error defining procedure : " + cmd);
                }
            }
            
            for (int i = 0; i < e.getChildCount(); i++) {
                try {
                    v = ((SmplVar) e.getChild(i));
                    d.update(v.getVar(), l);
                } catch (ClassCastException cce4) {
                    throw new SmplWrongTypeException("variable", cce4.getMessage(),
                            "Error assigning variables \n" +
                            "(all expressions given must be variables) : " + cmd);
                }
            }
            
        }
        return result;
    }
    
    public Object visitCmdPrint(SmplPrint cmd, Object arg)
    throws SmplException {
        Object e = null;
        try {
            e = cmd.getChild().visit(this, arg);
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation : " +
                    cmd + "type mismatch :" + swte.getMessage(), cmd);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage(), cmd);
        }
        
        System.out.print(e.toString() + ((cmd.nl) ? "\n" : ""));
        
        return e;
    }
    
    public Object visitLet(SmplLet cmd, Object arg)
    throws SmplException {
        SmplEnvironment d =  ((SmplEnvironment) arg).spawn();
        cmd.getChild(0).visit(this, d);
        return (cmd.getChild(1).visit(this, d));
    }
    
    public Object visitIf(SmplIf cmd, Object arg)
    throws SmplException {
        ASTNode e =  cmd.getChild(0);
        ASTNode s1, s2 = null;
        SmplBool test = null;
        Object result = null;
        
        
        s1 =  cmd.getChild(1);
        try {
            s2 =  cmd.getChild(2);
        } catch (ArrayIndexOutOfBoundsException aioobe) {}
        
        try {
            test = (SmplBool) e.visit(this, arg);
        } catch (ClassCastException cce) {
            throw new SmplWrongTypeException("boolean", cce.getMessage(),
                    "Error defining procedure : " + cmd);
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation : " +
                    cmd + "type mismatch :" + swte.getMessage(), cmd);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage());
        }
        
        if (test.isTrue())
            try {
                result = s1.visit(this, arg);
            } catch (SmplEvalException see) {
                throw new SmplEvalException(see.getMessage());
            } else if (s2 != null) {
            try {
                result = s2.visit(this, arg);
            } catch (SmplEvalException see) {
                throw new SmplEvalException(see.getMessage());
            }
            }
        
        return result;
        
    }
    
    public Object visitCase(SmplCase cmd, Object arg)
    throws SmplException {
        
        Object result = null;
        try {
            result = cmd.getChild().visit(this, arg);
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation : " +
                    cmd + "type mismatch :" + swte.getMessage(), cmd);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage(), cmd);
        }
        return result;
    }
    
    public Object visitCmdRead(SmplRead cmd, Object arg)
    throws SmplException {
        return null;
    }
    
    public Object visitRep(CmdRep cmd, Object arg)
    throws SmplException {
        ASTNode e1 = cmd.getChild(0);
        ASTNode e2 = cmd.getChild(1);
        SmplNum n = null;
        Object result = null;
        try {
            n = (SmplNum) e1.visit(this, arg);
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation : " +
                    cmd + "type mismatch :" + swte.getMessage(), cmd);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage(), cmd);
        } catch (SmplStopException sse) {
            
            for (int i = 0; i < n.intVal(); i++)
                try {
                    result = e2.visit(this, arg);
                } catch (SmplStopException sse1) {
                    return result;
                } catch (SmplEvalException see2) {
                    throw new SmplEvalException(see2.getMessage());
                }
        }
        return result;
    }
    
    public Object visitFor(SmplForExp cmd, Object arg)
    throws SmplException {
        ASTNode e1, e2, e3, e4;
        e1 =  cmd.getChild(0);
        e2 =  cmd.getChild(1);
        e3 =  cmd.getChild(2);
        e4 =  cmd.getChild(3);
        boolean b = false;
        Object result = null;
        
        try {
            e1.visit(this, arg);
            //smpl.utils.Util.displayMsg(t);
            while (true) {
                result = e2.visit(this, arg);
                try {
                    b = ((SmplBool) result).isTrue();
                } catch (ClassCastException cce) {
                    try {
                        b = ((SmplBool) ((SmplLit) result).getVal()).isTrue();
                    } catch (ClassCastException cce2) {
                        throw new SmplWrongTypeException("boolean", cce2.getMessage(),
                                "Error defining procedure : " + cmd);
                    }
                }
                if (b){
                    
                    result = e4.visit(this, arg);
                    e3.visit(this, arg);
                    
                }else{
                    
                    break;
                }
                
                
            }
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation : " +
                    cmd + "type mismatch :" + swte.getMessage(), cmd);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage(), cmd);
        } catch (SmplStopException sse) {
            return result;
        }
        return result;
    }
    
    public Object visitWhile(SmplWhile cmd, Object arg)
    throws SmplException {
        ASTNode e1, e2;
        e1 =  cmd.getChild(0);
        e2 =  cmd.getChild(1);
        boolean b = false;
        Object result = null;
        
        try {
            while (true) {
                result = e1.visit(this, arg);
                try {
                    b = ((SmplBool) result).isTrue();
                } catch (ClassCastException cce) {
                    try {
                        b = ((SmplBool) ((SmplLit) result).getVal()).isTrue();
                    } catch (ClassCastException cce2) {
                        throw new SmplWrongTypeException("boolean", cce2.getMessage(),
                                "Error defining procedure : " + cmd);
                    }
                }
                if (b){
                    
                    result = e2.visit(this, arg);
                }else{
                    
                    break;
                }
                
                
            }
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation : " +
                    cmd + "type mismatch :" + swte.getMessage(), cmd);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage(), cmd);
        } catch (SmplStopException sse) {
            return result;
        }
        return result;
    }
    
    public Object visitBreak(SmplBreak cmd, Object arg)
    throws SmplException {
        throw new SmplStopException();
    }
    
    public Object visitExpression(ASTNode exp, Object arg)
    throws SmplException {
        return visitExpLit((SmplLit) exp, arg);
    }
    
    public Object visitExpSeq(ExpSeq seq, Object arg)
    throws SmplException {
        SmplTuple t = new SmplTuple();
        ASTNode e = null;
        Object r = null;
        
        for (int i = 0; i < seq.getChildCount(); i++) {
            e =  seq.getChild(i);
            try {
                r = e.visit(this, arg);
                t.add(r);
            } catch (SmplEvalException see) {
                throw new SmplEvalException(see.getMessage(), see.getExp());
            }
        }
        return t;
    }
    
    public Object visitLetSeq(LetSeq exp, Object arg)
    throws SmplException {
        return null;
    }
    
    public Object visitCaseSeq(SmplCaseSeq seq, Object arg)
    throws SmplException {
        Object result = new SmplBool(false);
        for (int i = 0; i < seq.getChildCount(); i++) {
            try {
                result = seq.getChild(i).visit(this, arg);
                break;
            } catch (SmplWrongTypeException swte) {
                throw new SmplEvalException("Error performing operation : " +
                        seq + "type mismatch :" + swte.getMessage(), seq);
            } catch (SmplEvalException see) {
                throw new SmplEvalException(see.getMessage(), seq);
            } catch (SmplBadCaseException sbce) {
                continue;
            }
        }
        
        return result;
    }
    
    public Object visitExpLet(ExpLet exp, Object arg)
    throws SmplException {
        SmplVar v = null;
        SmplLiteral l = null;
        ExpSeq e = null;
        ASTNode b = exp.getChild(1);
        
        SmplEnvironment d = (SmplEnvironment) arg;
        
        try {
            l = (SmplLiteral) b.visit(this, arg);
        } catch (ClassCastException cce1) {
            throw new SmplWrongTypeException("literal value", cce1.getMessage(),
                    "Error Assigning value(s) : " + exp);
        }
        try {
            v = (SmplVar) exp.getChild(0);
            d.put(v.getVar(), l);
        } catch (ClassCastException cce2) {
            throw new SmplWrongTypeException("variable", cce2.getMessage(),
                    "Error defining procedure : " + exp);
        }
        return l;
    }
    
    public Object visitExpCase(ExpCase exp, Object arg)
    throws SmplException {
        ASTNode e1 =  exp.getChild(0);
        ASTNode e2 =  exp.getChild(1);
        
        SmplBool test = null;
        Object result = null;
        
        try {
            test = (SmplBool) e1.visit(this, arg);
        } catch (ClassCastException cce) {
            throw new SmplWrongTypeException("boolean", cce.getMessage(),
                    "Error defining procedure : " + exp);
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation : " +
                    exp + "type mismatch :" + swte.getMessage(), exp);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage());
        }
        if (!test.isTrue())
            throw new SmplBadCaseException();
        
        try {
            result = e2.visit(this, arg);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage());
        }
        return result;
    }
    
    public Object visitExpAdd(SmplAdd exp, Object arg)
    throws SmplException {
        SmplLiteral e1 = null;
        SmplLiteral e2 = null;
        
        try {
            e1 = (SmplLiteral) exp.getChild(0).visit(this, arg);
            e2 = (SmplLiteral) exp.getChild(1).visit(this, arg);
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation : " +
                    exp + "type mismatch :" + swte.getMessage(), exp);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage(), exp);
        }
        
        return (e1.add(e2));
    }
    
    public Object visitExpSub(SmplMinus exp, Object arg)
    throws SmplException {
        SmplLiteral e1 = null;
        SmplLiteral e2 = null;
        
        try {
            e1 = (SmplLiteral) exp.getChild(0).visit(this, arg);
            e2 = (SmplLiteral) exp.getChild(1).visit(this, arg);
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation : " +
                    exp + "type mismatch :" + swte.getMessage(), exp);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage(), exp);
        }
        
        return (e1.sub(e2));
    }
    
    public Object visitExpMul(SmplMul exp, Object arg)
    throws SmplException {
        SmplLiteral e1 = null;
        SmplLiteral e2 = null;
        
        
        
        try {
            e1 = (SmplLiteral) exp.getChild(0).visit(this, arg);
            e2 = (SmplLiteral) exp.getChild(1).visit(this, arg);
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation : " +
                    exp + "type mismatch :" + swte.getMessage(), exp);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage(), exp);
        }
        
        return (e1.mul(e2));
    }
    
    public Object visitExpDiv(SmplDiv exp, Object arg)
    throws SmplException {
        SmplLiteral e1 = null;
        SmplLiteral e2 = null;
        
        try {
            e1 = (SmplLiteral) exp.getChild(0).visit(this, arg);
            e2 = (SmplLiteral) exp.getChild(1).visit(this, arg);
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation : " +
                    exp + "type mismatch :" + swte.getMessage(), exp);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage(), exp);
        }
        
        return (e1.div(e2));
    }
    
    public Object visitExpMod(SmplMod exp, Object arg)
    throws SmplException {
        SmplLiteral e1 = null;
        SmplLiteral e2 = null;
        
        try {
            e1 = (SmplLiteral) exp.getChild(0).visit(this, arg);
            e2 = (SmplLiteral) exp.getChild(1).visit(this, arg);
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation : " +
                    exp + "type mismatch :" + swte.getMessage(), exp);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage(), exp);
        }
        
        return (e1.mod(e2));
    }
    
    public Object visitExpPow(SmplExpo exp, Object arg)
    throws SmplException {
        SmplLiteral e1 = null;
        SmplLiteral e2 = null;
        
        try {
            e1 = (SmplLiteral) exp.getChild(0).visit(this, arg);
            e2 = (SmplLiteral) exp.getChild(1).visit(this, arg);
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation : " +
                    exp + "type mismatch :" + swte.getMessage(), exp);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage(), exp);
        }
        
        return (e1.pow(e2));
    }
    
    public Object visitExpNeg(SmplNeg exp, Object arg)
    throws SmplException {
        SmplLiteral e1 = null;
        
        try {
            e1 = (SmplLiteral) exp.getChild(0).visit(this, arg);
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation : " +
                    exp + "type mismatch :" + swte.getMessage(), exp);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage(), exp);
        }
        
        return (e1.neg());
    }
    
    public Object visitExpCat(SmplConcat exp, Object arg)
    throws SmplException {
        return null;
    }
    
    public Object visitExpBAnd(SmplBitAnd exp, Object arg)
    throws SmplException {
        SmplLiteral e1 = null;
        SmplLiteral e2 = null;
        
        try {
            e1 = (SmplLiteral) exp.getChild(0).visit(this, arg);
            e2 = (SmplLiteral) exp.getChild(1).visit(this, arg);
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation : " +
                    exp + "type mismatch :" + swte.getMessage(), exp);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage(), exp);
        }
        
        return (e1.band(e2));
    }
    
    public Object visitExpBOr(SmplBitOr exp, Object arg)
    throws SmplException {
        SmplLiteral e1 = null;
        SmplLiteral e2 = null;
        
        try {
            e1 = (SmplLiteral) exp.getChild(0).visit(this, arg);
            e2 = (SmplLiteral) exp.getChild(1).visit(this, arg);
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation : " +
                    exp + "type mismatch :" + swte.getMessage(), exp);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage(), exp);
        }
        
        return (e1.bor(e2));
    }
    
    public Object visitExpBNot(SmplBitNot exp, Object arg)
    throws SmplException {
        SmplLiteral e1 = (SmplLiteral) new SmplLit(0).getVal();
        
        try {
            e1 = (SmplLiteral) exp.getChild(0).visit(this, arg);
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation : " +
                    exp + "type mismatch :" + swte.getMessage(), exp);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage(), exp);
        }
        
        return (e1.bnot());
    }
    
    public Object visitExpAnd(SmplLogAnd exp, Object arg)
    throws SmplException {
        SmplBool e1 = null;
        SmplBool e2 = null;
        
        try {
            e1 = (SmplBool) exp.getChild(0).visit(this, arg);
            e2 = (SmplBool) exp.getChild(1).visit(this, arg);
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation : " +
                    exp + "type mismatch :" + swte.getMessage(), exp);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage(), exp);
        }
        
        return (e1.and(e2));
    }
    
    public Object visitExpOr(SmplOr exp, Object arg)
    throws SmplException {
        SmplBool e1 = null;
        SmplBool e2 = null;
        
        try {
            e1 = (SmplBool) exp.getChild(0).visit(this, arg);
            e2 = (SmplBool) exp.getChild(1).visit(this, arg);
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation : " +
                    exp + "type mismatch :" + swte.getMessage(), exp);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage(), exp);
        }
        
        return (e1.or(e2));
    }
    
    public Object visitExpNot(SmplNot exp, Object arg)
    throws SmplException {
        SmplBool e1 = (SmplBool) new SmplLit(false).getVal();
        
        try {
            e1 = (SmplBool) exp.getChild(0).visit(this, arg);
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation : " +
                    exp + "type mismatch :" + swte.getMessage(), exp);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage(), exp);
        }
        
        return (e1.not());
    }
    
    public Object visitExpGT(SmplGThan exp, Object arg)
    throws SmplException {
        SmplLiteral e1 = null;
        SmplLiteral e2 = null;
        
        try {
            e1 = (SmplLiteral) exp.getChild(0).visit(this, arg);
            e2 = (SmplLiteral) exp.getChild(1).visit(this, arg);
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation : " +
                    exp + "type mismatch :" + swte.getMessage(), exp);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage(), exp);
        }
        
        return (e1.gt(e2));
    }
    
    public Object visitExpGE(SmplGEqual exp, Object arg)
    throws SmplException {
        SmplLiteral e1 = null;
        SmplLiteral e2 = null;
        
        try {
            e1 = (SmplLiteral) exp.getChild(0).visit(this, arg);
            e2 = (SmplLiteral) exp.getChild(1).visit(this, arg);
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation : " +
                    exp + "type mismatch :" + swte.getMessage(), exp);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage(), exp);
        }
        
        return (e1.ge(e2));
    }
    
    public Object visitExpLT(SmplLThan exp, Object arg)
    throws SmplException {
        SmplLiteral e1 = null;
        SmplLiteral e2 = null;
        
        try {
            e1 = (SmplLiteral) exp.getChild(0).visit(this, arg);
            e2 = (SmplLiteral) exp.getChild(1).visit(this, arg);
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation : " +
                    exp + "type mismatch :" + swte.getMessage(), exp);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage(), exp);
        }
        
        return (e1.lt(e2));
    }
    
    public Object visitExpLE(SmplLEqual exp, Object arg)
    throws SmplException {
        SmplLiteral e1 = null;
        SmplLiteral e2 = null;
        
        try {
            e1 = (SmplLiteral) exp.getChild(0).visit(this, arg);
            e2 = (SmplLiteral) exp.getChild(1).visit(this, arg);
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation : " +
                    exp + "type mismatch :" + swte.getMessage(), exp);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage(), exp);
        }
        
        return (e1.le(e2));
    }
    
    public Object visitExpEQ(SmplEqual exp, Object arg)
    throws SmplException {
        SmplLiteral e1 = null;
        SmplLiteral e2 = null;
        
        try {
            e1 = (SmplLiteral) exp.getChild(0).visit(this, arg);
            e2 = (SmplLiteral) exp.getChild(1).visit(this, arg);
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation : " +
                    exp + "type mismatch :" + swte.getMessage(), exp);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage(), exp);
        }
        
        return (e1.eq(e2));
    }
    
    public Object visitExpNE(SmplNotEqual exp, Object arg)
    throws SmplException {
        SmplLiteral e1 = null;
        SmplLiteral e2 = null;
        
        try {
            e1 = (SmplLiteral) exp.getChild(0).visit(this, arg);
            e2 = (SmplLiteral) exp.getChild(1).visit(this, arg);
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation : " +
                    exp + "type mismatch :" + swte.getMessage(), exp);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage(), exp);
        }
        
        return (e1.ne(e2));
    }
    
    public Object visitExpVar(SmplVar var, Object arg)
    throws SmplException {
        SmplEnvironment d = (SmplEnvironment) arg;
        ASTNode l = null;
        
        try {
            l = (ASTNode) d.getOther(var.getVar());
        } catch (SmplLookupException sle) {
            throw new SmplEvalException(sle.getMessage(), var);
        }
        return l;
    }
    
    public Object visitExpLit(SmplLit lit, Object arg)
    throws SmplException {
        return lit.getVal();
    }
    
    public Object visitSmplClass(SmplClass sClass, Object arg) throws SmplException {
        //prepare shard enviroment for subsequent instantiations of this class.
        if(sClass.getMyParent() == null){
            
            
            
        }else{
            
            SmplEnvironment global = (SmplEnvironment) arg;
            SmplClass parentClass  =  (SmplClass) global.getOther(sClass.getMyParent());
            sClass.getPublicEnv().setParent(parentClass.getPublicEnv());
            
            
            
        }
        ArrayList body= sClass.getClassBody();
        SmplEnvironment sharedEnv = sClass.getSharedEnv();
        for (int i=0; i < body.size();i++){
            //SmplShared list = (SmplShared)((SmplExp)body.get(i)).getLeftChild();
//            smpl.utils.Util.displayMsg("List :" + list);
            
            
            smpl.utils.Util.displayMsg("Going through each statement of the Class");
            smpl.utils.Util.displayMsg("Body at " + i + " " + ((ArrayList)body.get(i)).get(i) );
            if (((ArrayList)body.get(i)).get(i) instanceof SmplShared){
                smpl.utils.Util.displayMsg("Found instanec of SMPLShared");
                ASTNode shar = (ASTNode)   ((SmplShared)((ArrayList)body.get(i)).get(i)).visit(this,arg)  ; ///((SmplExp)body.get(i)).visit(this,arg);
                ArrayList sharedStmts = ((SmplShared)((ArrayList)body.get(i)).get(i)).getStmts();//((SmplShared) body.get(i)).getStmts();
                smpl.utils.Util.displayMsg("# elements in shared body: " + sharedStmts.size());
                
                for (int k=0; k<sharedStmts.size(); k++){
                    smpl.utils.Util.displayMsg("Going through each statement in the shared command. " + k);
                    
                    ASTNode exp = (ASTNode) ((ASTNode) sharedStmts.get(k)).visit(this,arg);
                    
                    //    sharedEnv.put(lit.getVar(),sClass.getTempMap().get(lit.getVar()));
                    
                    
                    if(exp instanceof  SmplMethod){
                        
                        SmplMethod meth = (SmplMethod) exp;
                        if(meth == null){
                            
                            smpl.utils.Util.displayMsg("Method is null");
                            
                        }
                        sharedEnv.put(meth.getName(),meth);
                        smpl.utils.Util.displayMsg("Method In Env: "+ meth.getName());
                        
                        
                    }else if(exp instanceof SmplLiteral){
                        
                        SmplLiteral lit = (SmplLiteral) exp;
                        sharedEnv.put(lit.stringVal(),lit);
                        
                    }
                    
                    
                    
                }
                
            }
            sClass.setSharedEnv(sharedEnv);
        }
        
        return sClass;
    }
    
    public Object visitSmplMethod(SmplMethod method, Object arg) throws SmplException {
        
        
        return method;
    }
    
    public Object visitSmplNew(SmplNew newClass, Object arg) throws SmplException {
        
        SmplEnvironment globalEnv = (SmplEnvironment) arg;
        String className = newClass.getClassName();
        
        
        
        SmplClass sClass = (SmplClass) globalEnv.getOther(className);
        
        if(sClass.getMyParent() != null){
            
            (new SmplNew(sClass.getMyParent(),new ArrayList())).visit(this,arg);
            
        }
        ArrayList list = sClass.getClassBody();
        smpl.utils.Util.displayMsg("Outer List: " + list.size());
        //Please Change This Number YOU ARE COOKING IT!!!!!!!
        ArrayList innerList =  (ArrayList)list.get(0);
        smpl.utils.Util.displayMsg(innerList.size());
        ArrayList<SmplExport> se = new ArrayList<SmplExport>();
        for(int i = 0 ; i< innerList.size(); i++){
            
            
            if(((ASTNode)innerList.get(i)) instanceof SmplDef){
                
                SmplDef def = (SmplDef) ((ASTNode)innerList.get(i));
                SmplVar var  = (SmplVar) def.getChild(0);
                //SmplLiteral lit  = (SmplLiteral) def.getRightChild();
                
                if(def.getChild(0).visit(this,arg)instanceof SmplLiteral){
                    
                smpl.utils.Util.displayMsg("MY VAR " + var.getVar());    
                       sClass.getTempMap().put(var.getVar(),def.getChild(1).visit(this,arg));
                }
                
            }
            
            ASTNode exp = (ASTNode) ((ASTNode)innerList.get(i)).visit(this,arg);
            
            if(exp instanceof  SmplMethod){
                
                SmplMethod meth = (SmplMethod) exp;
                if(meth == null){
                    
                    smpl.utils.Util.displayMsg("Method is null");
                    
                }
                sClass.getTempMap().put(meth.getName(),meth);
                smpl.utils.Util.displayMsg("Method In Env: "+ meth.getName());
                
                
            }else if(exp instanceof SmplLiteral){
             //   smpl.utils.Util.displayMsg("IN VAL");
               // SmplLiteral lit = (SmplLiteral) exp;
                //smpl.utils.Util.displayMsg("VAL" +lit.toString());
        //        sClass.getTempMap().put(lit.stringVal(),lit);
                smpl.utils.Util.displayMsg("Found Literal");
                
            }else if(exp instanceof SmplShared){
                smpl.utils.Util.displayMsg("In NewClas visitor Method- Instance of Shard Object");
                
                
                
            }else if(exp instanceof SmplConstrObject){
                
                SmplConstrObject conOb =  (SmplConstrObject) exp;
                
                ArrayList body = conOb .getBody();
                ArrayList params = conOb.getParams();
                if(params.size() == newClass.getParam().size()){
                    smpl.utils.Util.displayMsg("In IF");
                    SmplClass classObj = (SmplClass)globalEnv.getOther(newClass.getClassName());
                    SmplEnvironment env = classObj.getSharedEnv().spawn();
                    
                    for(int q = 0 ; q< params.size(); q++){
                        
                        
                        ASTNode  numLit = (ASTNode) ((ASTNode)newClass.getParam().get(q)).visit(this,arg);
                        SmplVar lit = (SmplVar) (params.get(q));
                        smpl.utils.Util.displayMsg("LIT:" + lit.getVar());
                        env.put(lit.getVar(),numLit);
                        
                        
                    }
                    
                    for(int z = 0 ; z< body.size() ;z++ ){
                        
                        //SmplClass classObj = (SmplClass)globalEnv.getOther(newClass.getClassName());
                        
                        
                        ASTNode expression = (ASTNode) ((ASTNode)body.get(i)).visit(this,env);
                        
                    }
                    
                }else{
                    
                    throw new SmplException("Parameters not equal");
                }
                
                smpl.utils.Util.displayMsg("Found Constructor");
                //SmplConstr con = (SmplConstr) exp;
                //sClass.getPublicEnv().put("con",con);
                
                
            }else if(exp instanceof SmplClass ){
                
                SmplClass c = (SmplClass) exp;
                sClass.getTemp().add(c);
                
                
            }else if(exp instanceof SmplExport){
                SmplExport ex = (SmplExport)exp;
                se.add(ex);
                smpl.utils.Util.displayMsg("add export statement to arraylist SE");
                
                
            }
            
            
            
        }
        
        //run export statments
        if (se.isEmpty()){
            smpl.utils.Util.displayMsg("No variables to export");
        }else{
            //For each exportobject do this
            smpl.utils.Util.displayMsg("Export varibales to public environment of this class");
            for (int i=0; i<se.size();i++){
                
                ArrayList pList  = (se.get(i)).getParams();
                
                for(int k = 0 ; k< pList.size() ; k++){
                    
                    SmplVar lit = (SmplVar) pList.get(k);
                    smpl.utils.Util.displayMsg(lit.getVar());
                    if((sClass.getTempMap().get(lit.getVar())) == null){
                        
                        
                        smpl.utils.Util.displayMsg("Value is null");
                    }else{
                        smpl.utils.Util.displayMsg("Assigning "+ lit.getVar());
                        sClass.getPublicEnv().put(lit.getVar(),sClass.getTempMap().get(lit.getVar()));
                    
                        sClass.getTempMap().remove(lit.getVar());
                    }
                    
                    
                    
                    
                }
                
            }
            
            
            
        }
        
        Iterator iter = sClass.getTempMap().keySet().iterator();
        
        while(iter.hasNext()){
            
            
            
            String  keys = (String)iter.next();
            smpl.utils.Util.displayMsg("Method" + keys +"protected: ");
            sClass.getProtectedEnv().put(keys,sClass.getTempMap().get(keys));
            
           /*
            if(exp instanceof SmplMethod){
            
                SmplMethod meth = (SmplMethod) exp;
                sClass.getProtectedEnv().put(meth.getName(),meth);
            
            
            }else if(exp instanceof SmplLiteral){
            
                SmplLiteral lit = (SmplLiteral) exp;
                sClass.getProtectedEnv().put(lit.strVal(),lit);
            
            
            }
            
            */
            
        }
        
        
        return sClass;
        
    }
    
    public Object visitSmplExport(SmplExport smplExport, Object arg) throws SmplException {
        
        
        return smplExport;
    }
    
    public Object visitSmplMethodCall(SmplMethodCall smc, Object arg) throws SmplException {
        
        smpl.utils.Util.displayMsg("Doiong Method CAlll!!!!!!!!!!!!!!!1");
      
        ASTNode refClass = smc.getClassObject();
        // SmplNew newClass= (SmplNew)refClass.visit(this,arg);
        // SmplClass sClass= (SmplClass) newClass.visit(this,arg);
        
        
        ASTNode sExp = (ASTNode)refClass.visit(this,arg);
        smpl.utils.Util.displayMsg(sExp);
        SmplClass sClass= null;
        ASTNode stmt=null;
        SmplMethod meth = null;
        
        if(sExp instanceof SmplNew){
            
            sClass =(SmplClass) sExp.visit(this,arg);
            myGlobalClass = sClass;
            smpl.utils.Util.displayMsg("In NEW");
            
            
            
        }
        smpl.utils.Util.displayMsg("In NEW CLASS");
        //   sClass = (SmplClass) sExp;
        
       methEnv = (SmplEnvironment) arg;

        try{
         meth = (SmplMethod) sClass.getPublicEnv().getOther(smc.getMethodName());
        }catch(Exception e){
            
        if(sClass == null){
            
            smpl.utils.Util.displayMsg("Sclass Nuill");
            
        }
            if(meth == null){
            
           
            if(sClass.getMyParent() != null){
               
                
                SmplEnvironment global = (SmplEnvironment) arg;
                SmplClass parentClass  =  (SmplClass) global.getOther(sClass.getMyParent());
                //sClass.getPublicEnv().setParent(parentClass.getPublicEnv());
                
            
                try{
                    
                meth =  (SmplMethod)parentClass.getPublicEnv().getOther(smc.getMethodName());    
                }catch(Exception ef){
                    
                    try{
                        
                        meth =  (SmplMethod)parentClass.getSharedEnv().getOther(smc.getMethodName());    
                    }catch(Exception v){
                        
                        smpl.utils.Util.displayMsg("Cant find method in parent class");
                    }
                }
                
                
            }else{
                
                throw new SmplException("Method Cannot be found");
                
            }
             
            
        }
            
        }
        
        SmplLiteral stmtLit =null;
        if (smc.getParams().size()==meth.getParameters().size()){
            
            for (int i=0; i< smc.getParams().size(); i++){
                meth.getLocalEnv().put(((SmplVar)meth.getParameters().get(i)).getVar(), ((ASTNode)smc.getParams().get(i)).visit(this,arg));
                
            }
            meth.getLocalEnv().setParent((SmplEnvironment)arg);
            for (int k=0; k< meth.getMethBody().size(); k++){
                smpl.utils.Util.displayMsg("Evaulating Method Body ");
               try {
               
                    /*
                    if(((SmplExp) meth.getMethBody().get(k)).visit(this, meth.getLocalEnv()) instanceof SmplProcCall){
                        
                        
                        smpl.utils.Util.displayMsg("WEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
                    }else if(((SmplExp) meth.getMethBody().get(k)).visit(this, meth.getLocalEnv()) instanceof SmplProc){
                        
                        
                        smpl.utils.Util.displayMsg("WEEEEEEEEEEEEEEEEEEEEEEEEEEEEE2");
                    }else if(((SmplExp) meth.getMethBody().get(k)).visit(this, meth.getLocalEnv()) instanceof SmplProcDef){
                        
                        
                        smpl.utils.Util.displayMsg("WEEEEEEEEEEEEEEEEEEEEEEEEEEEEE3");
                        
                    }
        */
                //   try{ 
                   
                   
                   stmt = (ASTNode) ((ASTNode) meth.getMethBody().get(k)).visit(this, meth.getLocalEnv());
                   }catch(Exception r){
                       
                  //     SmplEnvironment env = new SmplEnvironment();
                       
                       try{
                           smpl.utils.Util.displayMsg("Cant find it in method Env Looking in Class Protected Env");
                       stmt = (ASTNode) ((ASTNode) meth.getMethBody().get(k)).visit(this, sClass.getProtectedEnv());
                       }catch(Exception re){

                           try{
                               smpl.utils.Util.displayMsg("Cant find it in Protected Env Looking in Class Public Env");
                               stmt = (ASTNode) ((ASTNode) meth.getMethBody().get(k)).visit(this, sClass.getPublicEnv());
                           }catch(Exception e){
                              e.printStackTrace(); 
                               try{
                                      smpl.utils.Util.displayMsg("Cant find it in Public Env Looking in Class Shared Env");
                                     stmt = (ASTNode) ((ASTNode) meth.getMethBody().get(k)).visit(this, sClass.getSharedEnv());
                               }catch(Exception d){
                                   smpl.utils.Util.displayMsg("Just Cant Find It"); 
//                                   try{
//                                       
//                                  stmt = (SmplExp) ((SmplExp) meth.getMethBody().get(k)).visit(this, meth.getLocalEnv());                 
//                                   }catch(Exception fgh){
//                                       
//                                      
//                                   }
                                   
                                      
                               }
                           }
                           
                       }
                   }
            //    } catch (SmplException ex) {
            /*
                    smpl.utils.Util.displayMsg("BODY SIZE" + meth.getMethBody().size());
                    int skip = k;
                    smpl.utils.Util.displayMsg("I FOUND "  + methName );
                    SmplMethod protectMethod = (SmplMethod) sClass.getProtectedEnv().getOther(methName);
                    for(int i = 0; i <protectMethod.getParameters().size(); i++){
                        
                        protectMethod.getLocalEnv().put(((SmplVar)protectMethod.getParameters().get(i)).getVar(),((SmplExp)paramList.get(i)).visit(this,arg));
                        
                        
                    }
                    for(int j =0 ;j< protectMethod.getMethBody().size();j++){
                        
                        
                        
                        protectMethod.getLocalEnv().setParent(sClass.getProtectedEnv());
                      ((SmplExp)protectMethod.getMethBody().get(j)).visit(this,protectMethod.getLocalEnv());
                      
                      
                        
                    }
            
             */        
             //   }
            
                
              
                
            }
            
            return stmt;
            
        } else{
            smpl.utils.Util.displayMsg("Parameter sizes NOT EQUAL");
        }
        
        
        
        
        return stmt;
    }
    
    
    public Object visitTryBlock(TryBlock tBlock, Object arg)throws SmplException {
        
        ASTNode exp = null;
        
        try{
            
            
            ArrayList tList = tBlock.getTryStat();
            
            for(int i = 0 ;i< tList.size() ; i++){
                
                
                exp   = (ASTNode)	((ASTNode)tList.get(i)).visit(this,arg);
                
                
            }
            
        }catch(Exception e){
            
            
            ArrayList cList = tBlock.getCatStat();
            
            for(int i = 0 ; i< cList.size() ; i++){
                
                
                exp   =  (ASTNode)((ASTNode)cList.get(i)).visit(this,arg);
                
                
            }
            
        }
        
        
        return exp;
        
    }
    
    public Object visitSmplThread(SmplThread sThread, Object arg)	throws SmplException{
        
        
        SmplEnvironment env = (SmplEnvironment) arg;
        String name = sThread.getName();
        env.put(name,sThread);
        
        
        return sThread;
    }
    
    public Object visitSmplThreadCall( SmplThreadCall sThreadCall,  Object arg)	throws SmplException{
        
        final SmplThreadCall   tCall = sThreadCall;
        final Object   tObject = arg;
        Thread thread = new Thread(){
            int i = 0;
            public void run(){
                
                int nameInc = 0;
                nameInc++;
                SmplEnvironment env = (SmplEnvironment) tObject;
                SmplThread exp =null;
                try {
                    exp = (SmplThread) env.getOther(tCall.getName());
                } catch (SmplLookupException ex) {
                    ex.printStackTrace();
                }
                
                thrList = exp.getBody();
                String name = exp.getName();
                smpl.utils.Util.displayMsg(name+nameInc);
                for(  i =0 ; i< thrList.size(); i++){
                    
                    try{
                        
                        try {
                            
                            
                            stmts = (ASTNode) (((ASTNode)thrList.get(i)).visit(new SmplEvaluator(),tObject));
                        } catch (SmplException ex) {
                            ex.printStackTrace();
                        }
                        
                        
                        
                    }catch(Exception e){
                        
                        
                        
                    }
                    
                }
                
                
                
            /*
             
                 Thread u = new Thread(){
             
                            public void run(){
                for(  i =0 ; i< thrList.size(); i++){
             
                    try{
             
                                try {
             
             
                                    stmts = (SmplExp) (((SmplExp)thrList.get(i)).visit(new SmplEvaluator(),tObject));
                                } catch (SmplException ex) {
                                    ex.printStackTrace();
                                }
             
             
             
                    }catch(Exception e){
             
             
             
                    }
             
                }
             
                    }
                        };
                        u.start();
             */
                
                
            }
            
            
        };
        
        thread.start();
        
        
        
        
        
        
        
        return new SmplLit();
    }
    
   /* public Object visitDisplayPopUp(DisplayPopUp popUp, Object arg) throws SmplException{
        
        String title = popUp.getTitle();
        SmplExp info  = popUp.getInfo();
        
        //JOptionPane.showMessageDialog(null,info);
        
        //return popUp;
        
        
        
        Object e = null;
        try {
            e = info.visit(this, arg);
        } catch (SmplWrongTypeException swte) {
            throw new SmplEvalException("Error performing operation : " +
                    info + "type mismatch :" + swte.getMessage(), popUp);
        } catch (SmplEvalException see) {
            throw new SmplEvalException(see.getMessage(), popUp);
        }
        
        //System.out.print(e.toString() + ((cmd.nl) ? "\n" : ""));
        String i = e.toString() ;
        JOptionPane.showMessageDialog(null, i ,title,JOptionPane.INFORMATION_MESSAGE);
        return e;
    }*/
    
    public Object visitCreateWindow(CreateWindow window, Object arg) throws SmplException {
        
        
        return window;
    }
    
    /*public Object visitDisplayWindow(DisplayWindow dWindow, Object arg) throws SmplException {
        
        
        SmplEnvironment env = (SmplEnvironment) arg;
        String name = dWindow.getName();
        SmplExp exp = (SmplExp) env.getOther(name);
        
        if(exp instanceof CreateWindow){
            
            win = (CreateWindow) exp;
            //JFrame frame = new JFrame(win.getTitle());
            Thread u  = new Thread(){
                
                
                public void run(){
                    
                    win.getFrame().setTitle(win.getTitle());
                    win.getFrame().setSize(400,400);
                    win.getFrame().setVisible(true);
                    
                }
            };
            u.start();
            
            
            
            
        }else{
            
            smpl.utils.Util.displayMsg("Error Displaying Window");
        }
        return new SmplLit();
    }*/
    
/*    public Object visitAddTextArea(AddTextArea textArea, Object arg) throws SmplException {
        
        SmplEnvironment env = (SmplEnvironment) arg;
        env.put(textArea.getName(),textArea);
        String win = textArea.getWindow();
        CreateWindow window = (CreateWindow) env.getOther(win);
        window.getFrame().add(textArea.getPane());
        
        return textArea;
    }*/
    
    public Object visitSmplSleep(SmplSleep sleep, Object arg) throws SmplException {
        Integer val = sleep.getValue();
        try {
            
            Thread.sleep(val);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return sleep;
    }
    
   /* public Object visitSmplAppend(SmplAppend append, Object arg) throws SmplException {
        
        SmplEnvironment env = (SmplEnvironment) arg;
        AddTextArea area = (AddTextArea)env.getOther(append.getAppTextArea());
        area.getTextArea().append(append.getExp().visit(this,arg).toString());
        return append;
        
    }*/
    
/*    public Object visitSmplDatabaseConnect(SmplDatabaseConnect conn, Object arg) throws SmplException {
        
        //  vendor,database,host,username,password,port
        
        
        SmplExp[] children = conn.getChildren();
        
        SmplExp vendor = (SmplExp) children[0].visit(this,arg);
        SmplExp database = (SmplExp) children[1].visit(this,arg);
        SmplExp host = (SmplExp) children[2].visit(this,arg);
        SmplExp username = (SmplExp) children[3].visit(this,arg);
        SmplExp password = (SmplExp) children[4].visit(this,arg);
        SmplExp port = (SmplExp) children[5].visit(this,arg);
        smpl.utils.Util.displayMsg(vendor);
        smpl.utils.Util.displayMsg(database);
        smpl.utils.Util.displayMsg(host);
        smpl.utils.Util.displayMsg(username);
        smpl.utils.Util.displayMsg(password);
        smpl.utils.Util.displayMsg(port);
        
        DbaseConnect connection = new DbaseConnect(vendor.toString(),database.toString(),host.toString(),username.toString(),password.toString(),port.toString());
        connection.connect();
        conn.setConnection(connection);
        return connection;
        
    }*/
    
/*    public Object visitSmplDatabaseExec(SmplDatabaseExec exec, Object arg) throws SmplException {
        
        
        SmplDatabaseConnect exp = (SmplDatabaseConnect)exec.getLeftChild().visit(this,arg);
        DbaseConnect leftExp = exp.getConnection();
        SmplExp rightExp = (SmplExp)  exec.getRightChild().visit(this,arg);
        
        int numRows = leftExp.execute(rightExp.toString());
        
        return new SmplLit(numRows).getVal();
        
    }*/
    
   /* public Object visitSmplDatabaseResult(SmplDatabaseResult conn, Object arg) throws SmplException {
        
        SmplExp[] children = conn.children;
        SmplLit lit= null;
        SmplDatabaseConnect connect = (SmplDatabaseConnect) children[0].visit(this,arg);
        DbaseConnect connection = connect.getConnection();
        SmplNum row = (SmplNum) children[1].visit(this,arg);
        SmplNum column = (SmplNum) children[2].visit(this,arg);
        Object res =  connection.result(new Integer(row.toString()),new Integer(column.toString()));
        
        try{
            
            String resStr = (String) res;
            lit = new SmplLit(resStr);
        }catch(Exception e){
            
            
            try{
                
                Integer resStr = (Integer) res;
                lit = new SmplLit(resStr);
                
            }catch(Exception eff){
                
                try{
                    
                    
                    int resStr = (new Integer(res.toString())).intValue() ;
                    lit = new SmplLit(resStr);
                }catch(Exception ef){
                    
                    
                    lit = new SmplLit();
                    
                }
                
                
            }
        }
        
        
        
        return lit.getVal();
    }*/
    
    public Object visitSmplShared(SmplShared ssh, Object arg) throws SmplException {
        smpl.utils.Util.displayMsg("Vistor  emthod of Shared ");
        return ssh;
    }
    
    public Object visitSmplConstr(SmplConstr smplConstr, Object arg) throws SmplException {
        
        smpl.utils.Util.displayMsg("Visited Connstr");
        ExpSeq params = (ExpSeq)smplConstr.getChild(0);
        ExpSeq body     = (ExpSeq)smplConstr.getChild(1);
        ArrayList pa = params.getList();
        ArrayList bod = body.getList();
        return new SmplConstrObject(pa,bod);
        
    }

    private ASTNode getMethod() throws SmplException {
        ASTNode lit = null;
      //    smpl.utils.Util.displayMsg("BODY SIZE" + meth.getMethBody().size());
                  //  int skip = k;
                    smpl.utils.Util.displayMsg("I FOUND "  + methName );
        SmplMethod protectMethod = null;
          if(myGlobalClass.getMyParent() != null){
               
                
                
                SmplClass parentClass  =  (SmplClass) methEnv.getOther(myGlobalClass.getMyParent());
                //sClass.getPublicEnv().setParent(parentClass.getPublicEnv());
                try{
                protectMethod =  (SmplMethod)parentClass.getProtectedEnv().getOther(methName);
                }catch(Exception e){
                    try{
                    protectMethod =  (SmplMethod)parentClass.getPublicEnv().getOther(methName);
                    }catch(Exception r){
                        
                        protectMethod =  (SmplMethod)parentClass.getSharedEnv().getOther(methName);
                        
                    }
                }
                
                
                
            }else{
            
        try {
            protectMethod = (SmplMethod) myGlobalClass.getProtectedEnv().getOther(methName);
        } catch (SmplLookupException ex) {

           throw new SmplException("Procedure of Method Not Found");
        }
            }
                    for(int i = 0; i <protectMethod.getParameters().size(); i++){
            try {
                
                protectMethod.getLocalEnv().put(((SmplVar)protectMethod.getParameters().get(i)).getVar(),((ASTNode)paramList.get(i)).visit(this,methEnv));
            } catch (SmplException ex) {
                ex.printStackTrace();
            }
                        
                        
                    }
                    for(int j =0 ;j< protectMethod.getMethBody().size();j++){
                        
                        
                        
                        protectMethod.getLocalEnv().setParent(myGlobalClass.getProtectedEnv());
            try {
                lit  = (ASTNode) ((ASTNode)protectMethod.getMethBody().get(j)).visit(this,protectMethod.getLocalEnv());
            } catch (SmplException ex) {
                ex.printStackTrace();
            }
                      
                      
                        
                    }
        
                
            if(lit instanceof SmplNum){
            
            
            smpl.utils.Util.displayMsg("Num");
            /*
            try {
                //return new SmplLit(((SmplNum.) ((SmplNum)lit).visit(this,methEnv)).val);
            } catch (SmplException ex) {
                ex.printStackTrace();
            }
             */
            }
        /*
        try {
         //   String val = ((SmplLiteral)lit.visit(this,methEnv)).stringVal();
       //    smpl.utils.Util.displayMsg(val);
        } catch (SmplException ex) {
            ex.printStackTrace();
        }
         */
        /*
        try {
            return new SmplLit((SmplNum.Int) lit.visit(this,methEnv));
        } catch (SmplException ex) {
            ex.printStackTrace();
        }
         */
        return lit;
        
    }
}





