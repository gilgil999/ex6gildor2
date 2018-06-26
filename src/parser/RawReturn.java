package parser;

import validator.Checkable;
import validator.ReturnLine;

public class RawReturn implements RawLine {
    /**
     * this class is rawline representation of  a return line
     * @return
     */
    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public Checkable transfom() {
        return new ReturnLine();
    }
}
