package smpl.lang;

import smpl.oop.*;



/**
 * Visitor.java
 *
 */

public interface Visitor {

    public void init();		// called before any nodes are visited
    public void display(Object val); // display the result of a computation

    public Object visitProgram(SmplProgram prog, Object info)
	throws SmplException;

    public Object visitSequence(SmplSeq seq, Object arg)
	throws SmplException;

    public Object visitSmplShared(SmplShared share, Object arg)
    throws SmplException;

    public Object visitCmdPair(CmdPair cmd, Object arg)
	throws SmplException;

    public Object visitCmdList(SmplList cmd, Object arg)
	throws SmplException;

    public Object visitCmdCar(SmplCar cmd, Object arg)
	throws SmplException;

    public Object visitCmdCdr(SmplCdr cmd, Object arg)
	throws SmplException;

    public Object visitCmdVec(SmplCVector cmd, Object arg)
	throws SmplException;

    public Object visitExpSubVec(SmplSubVec cmd, Object arg)
	throws SmplException;

    public Object visitCmdVecAcc(SmplVecAcc cmd, Object arg)
	throws SmplException;

    public Object visitCmdSize(SmplSize cmd, Object arg)
	throws SmplException;

    public Object visitIsPair(SmplIsPair cmd, Object arg)
	throws SmplException;

    public Object visitIsEqv(SmplIsEqv cmd, Object arg)
	throws SmplException;

    public Object visitCmdSubstr(SmplSubstr cmd, Object arg)
	throws SmplException;

    public Object visitSmplProcCall(SmplProcCall cmd, Object arg)
	throws SmplException;

    public Object visitProcDef(SmplProcDef cmd, Object arg)
	throws SmplException;

    public Object visitCmdDef(SmplDef cmd, Object arg)
	throws SmplException;

    public Object visitCmdPrint(SmplPrint cmd, Object arg)
	throws SmplException;

    public Object visitLet(SmplLet cmd, Object arg)
	throws SmplException;

    public Object visitIf(SmplIf cmd, Object arg)
	throws SmplException;

    public Object visitCase(SmplCase cmd, Object arg)
	throws SmplException;

    public Object visitCmdRead(SmplRead cmd, Object arg)
	throws SmplException;

    public Object visitRep(CmdRep cmd, Object arg)
	throws SmplException;

    public Object visitFor(SmplForExp cmd, Object arg)
	throws SmplException;

    public Object visitWhile(SmplWhile cmd, Object arg)
	throws SmplException;

    public Object visitBreak(SmplBreak cmd, Object arg)
	throws SmplException;

    public Object visitExpression(ASTNode exp, Object arg)
	throws SmplException;

    public Object visitExpSeq(ExpSeq exp, Object arg)
	throws SmplException;

    public Object visitLetSeq(LetSeq exp, Object arg)
	throws SmplException;

    public Object visitCaseSeq(SmplCaseSeq exp, Object arg)
	throws SmplException;

    public Object visitExpLet(ExpLet exp, Object arg)
	throws SmplException;

    public Object visitExpCase(ExpCase exp, Object arg)
	throws SmplException;

    public Object visitExpAdd(SmplAdd exp, Object arg)
	throws SmplException;

    public Object visitExpSub(SmplMinus exp, Object arg)
	throws SmplException;

    public Object visitExpMul(SmplMul exp, Object arg)
	throws SmplException;

    public Object visitExpDiv(SmplDiv exp, Object arg)
	throws SmplException;

    public Object visitExpMod(SmplMod exp, Object arg)
	throws SmplException;

    public Object visitExpPow(SmplExpo exp, Object arg)
	throws SmplException;

    public Object visitExpNeg(SmplNeg exp, Object arg)
	throws SmplException;

    public Object visitExpCat(SmplConcat exp, Object arg)
	throws SmplException;

    public Object visitExpBAnd(SmplBitAnd exp, Object arg)
	throws SmplException;

    public Object visitExpBOr(SmplBitOr exp, Object arg)
	throws SmplException;

    public Object visitExpBNot(SmplBitNot exp, Object arg)
	throws SmplException;

    public Object visitExpAnd(SmplLogAnd exp, Object arg)
	throws SmplException;

    public Object visitExpOr(SmplOr exp, Object arg)
	throws SmplException;

    public Object visitExpNot(SmplNot exp, Object arg)
	throws SmplException;

    public Object visitExpGT(SmplGThan exp, Object arg)
	throws SmplException;

    public Object visitExpGE(SmplGEqual exp, Object arg)
	throws SmplException;

    public Object visitExpLT(SmplLThan exp, Object arg)
	throws SmplException;

    public Object visitExpLE(SmplLEqual exp, Object arg)
	throws SmplException;

    public Object visitExpEQ(SmplEqual exp, Object arg)
	throws SmplException;

    public Object visitExpNE(SmplNotEqual exp, Object arg)
	throws SmplException;

    public Object visitExpVar(SmplVar var, Object arg)
	throws SmplException;
    
    public Object visitExpLit(SmplLit lit, Object arg)
 	throws SmplException;
    
    public Object visitSmplClass(SmplClass sClass, Object arg)
 	throws SmplException;
    public Object visitSmplMethod(SmplMethod method, Object arg)
 	throws SmplException;
    
    public Object visitSmplNew(SmplNew newClass, Object arg)
 	throws SmplException;
    
    public Object visitSmplExport(SmplExport smplExport, Object arg)
 	throws SmplException;
    
    public Object visitSmplMethodCall(SmplMethodCall smplMethodCall, Object arg)
 	throws SmplException;
 	
 	public Object visitTryBlock(TryBlock tBlock, Object arg)
 	throws SmplException;
    
    public Object visitSmplThread(SmplThread sThread, Object arg)
 	throws SmplException;  
 	
 	public Object visitSmplThreadCall(SmplThreadCall sThreadCall, Object arg)
 	throws SmplException;
        
    public Object visitCreateWindow(CreateWindow window, Object arg)
 	throws SmplException;
        
      
        
    public Object visitSmplSleep(SmplSleep sleep, Object arg)
 	throws SmplException;    
        
       

    public Object visitSmplConstr(SmplConstr smplConstr, Object arg)
    throws SmplException;
        
 	
    
}// Visitor
