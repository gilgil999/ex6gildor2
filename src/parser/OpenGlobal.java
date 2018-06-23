package parser;

import validator.CodeSegment;

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
