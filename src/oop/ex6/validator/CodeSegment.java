package oop.ex6.validator;

import java.util.*;


public abstract class CodeSegment implements Checkable {

    /**
     * this class represents all the codesegments that contain other lines.
     * its member variables are a list of checkable objects and a parent codesegment
     */
    protected ArrayList<Checkable> children;
    protected CodeSegment parent;


    public CodeSegment() {
        this.children = new ArrayList<Checkable>();
    }

    public CodeSegment getParent() {
        return parent;
    }

    public void addLine(Checkable line) {
        this.children.add(line);
    }


    public void setParent(CodeSegment parent) {
        this.parent = parent;
    }

}
