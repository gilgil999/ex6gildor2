package parser;
import java.util.ArrayList;
import validator.VarObj;
import validator.CodeSegment;
import validator.FunctionObj;
import validator.FunctionSegment;

public class OpenFunction implements RawLine {
    /**
     * this class represents the first line of a function deceleration, it holds the value of the function name and
     * parameters
     */
    private FunctionObj thisfunc;
    public OpenFunction(FunctionObj functionObj){
    	thisfunc = functionObj;
	}


    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public boolean isOpen() {
        return true;
    }

    @Override
    public CodeSegment transfom() {
        return new FunctionSegment(thisfunc);
    }
}
