package validator;
import parser.TypeOneException;

import java.util.*;


public class GlobalSegment extends CodeSegment {


    public GlobalSegment() {
        super();

    }

    @Override
    public boolean isValid(ScopeObj scopeObj) throws TypeOneException {
        rearrangeFunctions(scopeObj);
        //no need for deepcopying the scopeobj
        for (Checkable line : this.children){
            if(!line.isValid(scopeObj))
                return false;
        }
        return true;
    }

    /**
     * this function goes over all of the children of
     * @param scopeObj
     */
    private void rearrangeFunctions(ScopeObj scopeObj) {
        ArrayList<FunctionSegment> myfuncs = new ArrayList<FunctionSegment>();
        for(Checkable line : this.children){
            if(line instanceof FunctionSegment){
                scopeObj.addFundtion(((FunctionSegment)line).getThisfunc());
                myfuncs.add((FunctionSegment)line);
                this.children.remove(line);
            }
        }
        this.children.addAll(myfuncs);


    }
}
