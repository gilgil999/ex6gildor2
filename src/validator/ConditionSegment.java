package validator;

public class ConditionSegment extends CodeSegment {
    @Override
    public boolean isValid(ScopeObj scopeObj) {
        ScopeObj myscope= new ScopeObj(scopeObj);
        for (Checkable line : this.children){
            if(!line.isValid(myscope))
                return false;
        }

        //check if there is a return statement
        if(!(this.children.get(this.children.size()-1) instanceof ReturnLine)){
            return false;
        }

        return true;

    }
}
