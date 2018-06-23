package parser;

import validator.CodeSegment;

//probably will never be used

public class OpenGlobal implements RawLine {
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
        return null;
    }
}
