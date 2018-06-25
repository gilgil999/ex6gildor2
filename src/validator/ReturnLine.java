package validator;

public class ReturnLine extends Singleline {
    /**
     * this class represents a return line;
     *
     * @param scopeObj
     * @return
     */
    @Override
    public boolean isValid(ScopeObj scopeObj) {
        return scopeObj.isFunction();
    }
}
