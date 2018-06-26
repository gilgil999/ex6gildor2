package parser;
import java.util.ArrayList;
import validator.CodeSegment;
import validator.ConditionSegment;
import validator.VarInstance;

public class OpenCondition implements RawLine {
    /**
     * this class is a representation of the first line in an if\while block
     * as one, it contatins the variables appearing inside the condition
     */
	private ArrayList<String> varnames = new ArrayList<String>();

	public void addName(String name) {
		this.varnames.add(name);
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
        for(String var : varnames){
            vars.add(new VarInstance(var, MainParser.varType.UNKNOWN));
        }
        return new ConditionSegment(vars);
    }
}
