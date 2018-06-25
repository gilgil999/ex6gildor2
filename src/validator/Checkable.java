package validator;

import parser.TypeOneException;

public interface Checkable {
    boolean isValid(ScopeObj scopeObj) throws TypeOneException;

}
