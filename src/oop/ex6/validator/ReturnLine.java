package oop.ex6.validator;

public class ReturnLine extends Singleline {
    /**
     * this class represents a return line;
     *
     * @param scopeObj
     * @return
     */
    @Override
    public void isValid(ScopeObj scopeObj) throws TypeOneException {
        if(!scopeObj.isFunction())
            throw new SyntaxException("return line not inside a fucntion");
    }
}
