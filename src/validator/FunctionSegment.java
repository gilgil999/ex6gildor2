package validator;

import parser.TypeOneException;

public class FunctionSegment extends CodeSegment {

    private FunctionObj thisfunc;

    public FunctionSegment(FunctionObj funcobj) {
        this.thisfunc = funcobj;
    }

    public FunctionObj getThisfunc() {
        return thisfunc;
    }

    /**
     * return whether this function segment is valid of not, it preforms validity checks on all of its children,
     * in addition, it will only return true if the last line is a return line
     *
     * @param scopeObj the current scope to check by
     * @return
     * @Override
     */

    public boolean isValid(ScopeObj scopeObj) throws TypeOneException {
        if (scopeObj.isFunction())
            throw new TypeOneException();

        if (paramsHaveDuplicates())
            throw new TypeOneException();
        ScopeObj myscope = new ScopeObj(scopeObj);
        myscope.update(this.thisfunc.getParameters());
        myscope.setFunction(true);//updates the
        for (Checkable line : this.children) {
            if (!line.isValid(myscope))
                return false;
        }

        //check if there is a return statement
        if (!(this.children.get(this.children.size() - 1) instanceof ReturnLine)) {
            return false;
        }

        return true;

    }

    private boolean paramsHaveDuplicates() {
        int len = thisfunc.getParameters().size();
        String[] names = new String[len];

        for (int i = 0; i < len; i++) {
            names[i] = thisfunc.getParameters().get(i).getName();
        }
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (names[i].equals(names[j]))
                    return true;
            }
        }
        return false;
    }
}
