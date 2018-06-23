package validator;

public class ReturnLine extends Singleline {
    @Override
    public boolean isValid(ScopeObj scopeObj) {
        return scopeObj.isFunction();
    }
}
