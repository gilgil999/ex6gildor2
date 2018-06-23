package validator;
import java.util.*;


public abstract class CodeSegment implements Checkable{

    protected ArrayList<Checkable> children;

    public CodeSegment(ArrayList<Checkable> children) {
        this.children = children;
    }

    public CodeSegment() {
        this.children = new ArrayList<Checkable>();

    }

    public void AddLline(Checkable line){
        this.children.add(line);
    }


}
