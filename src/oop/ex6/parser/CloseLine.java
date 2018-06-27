package oop.ex6.parser;

import oop.ex6.validator.CodeSegment;

public class CloseLine implements RawLine {
    /**
     * this class is a representation of a line with a closing bracket
     *
     */


    @Override
    public boolean isClosed() {
        return true;
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public CodeSegment transfom() {
        return null;
    }
}
