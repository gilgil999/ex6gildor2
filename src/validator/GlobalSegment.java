package validator;
import java.util.*;


public class GlobalSegment extends CodeSegment {


    public GlobalSegment() {
        super();

    }

    @Override



    public boolean isValid(ScopeObj scopeObj) {
        //no need for deepcopying the scopeobj
        for (Checkable line : this.children){
            if(!line.isValid(scopeObj))
                return false;
        }
        return true;
    }
}
