package oop.ex6.validator;

import oop.ex6.parser.TypeOneException;

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
//        return scopeObj.isFunction();
            throw new TypeOneException();
    }
}
