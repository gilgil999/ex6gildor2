package parser;
import java.util.ArrayList;
import validator.CodeSegment;

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
        return null;
    }
}
