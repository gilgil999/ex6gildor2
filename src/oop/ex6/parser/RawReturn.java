package oop.ex6.parser;

import oop.ex6.validator.Checkable;
import oop.ex6.validator.ReturnLine;

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
