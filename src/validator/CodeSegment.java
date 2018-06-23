package validator;
import java.util.*;


public abstract class CodeSegment implements Checkable{

    protected ArrayList<Checkable> children;
    protected CodeSegment parent;

    public CodeSegment(ArrayList<Checkable> children) {
        this.children = children;
    }

    public CodeSegment() {
        this.children = new ArrayList<Checkable>();

    }

    public CodeSegment getParent() {
        return parent;
    }

    public void addLine(Checkable line){
        this.children.add(line);
    }


    public void setParent(CodeSegment parent) {
        this.parent = parent;
    }
}
