package parser;

import validator.CodeSegment;

public interface RawLine {
    public boolean isClosed();
    public boolean isOpen();
    public CodeSegment transfom();

}
