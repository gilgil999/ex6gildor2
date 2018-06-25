package parser;
import java.util.ArrayList;
import validator.CodeSegment;
import validator.ConditionSegment;
import validator.VarInstance;

public class OpenCondition implements RawLine {
	private ArrayList<String> functionsNames = new ArrayList<String>();

	public void addName(String name) {
		this.functionsNames.add(name);
	}
    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public boolean isOpen() {
        return true;
    }

    @Override
    public CodeSegment transfom() {
        //todo
        ArrayList<VarInstance> vars = new ArrayList<VarInstance>();
        for(String var : functionsNames){
            vars.add(new VarInstance(var, MainParser.varType.UNKNOWN));
        }
        return new ConditionSegment(vars);
    }
}
