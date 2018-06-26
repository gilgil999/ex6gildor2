package parser;

import validator.Checkable;
import validator.CodeSegment;

public interface RawLine {
    /**
     * this interface captures all of the lines after the initial processing, every line that will be read will be
     * converted to a rawline. a rawline have three functions,
     * @return
     */
    /**
     * for every instace created, is closed will return true only if it is a closing line
     * @return
     */
    public boolean isClosed();

    /**
     * for every instace created, is open will return true only if it is an opening line of a new segment in the code
     * @return
     */
    public boolean isOpen();

    /**
     * the transform method wil return the corresponding checkable object to match the rawline object
     * @return
     */
    public Checkable transfom();

}
