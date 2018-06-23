package parser;

import validator.Checkable;
import validator.CodeSegment;

public interface RawLine {
    public boolean isClosed();
    public boolean isOpen();
    public Checkable transfom();

}
