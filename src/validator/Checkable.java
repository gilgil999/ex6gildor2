package validator;

import parser.TypeOneException;

public interface Checkable {
    /**
     * this interface is the interface of all of the objects in our project that can be checked to see if they are
     * logically valid. it contains a single method called isValid that takes as a parameter a ScopeObj
     *
     * @param scopeObj
     * @return
     * @throws TypeOneException
     */
    void isValid(ScopeObj scopeObj) throws TypeOneException;

}
