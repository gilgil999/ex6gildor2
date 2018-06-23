package validator;

public class FunctionSegment extends CodeSegment {
    @Override
    public boolean isValid(ScopeObj scopeObj) {
        ScopeObj myscope= scopeObj.
        for (Checkable line : this.children){
            if(!line.isValid(scopeObj))
                return false;
        }

        //check if

        return true;

    }
}
