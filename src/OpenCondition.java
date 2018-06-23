public class OpenCondition implements RawLine {
    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public boolean isOpen() {
        return true;
    }

    @Override
    public Segment transfom() {
        //todo
        return null;
    }
}
