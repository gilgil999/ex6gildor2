package validator;

public class VariableLine extends Singleline {


    private boolean isFinal;
    private VarOperation[] operations;

    public VariableLine(boolean isFinal, VarOperation[] operations) {
        this.isFinal = isFinal;
        this.operations = operations;
    }

    @Override
    public boolean isValid(ScopeObj scopeObj) {


        return false;
    }
}
