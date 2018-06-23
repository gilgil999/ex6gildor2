public class CloseLine implements RawLine {
    @Override
    public boolean isClosed() {
        return true;
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public Segment transfom() {
        //todo
        return null;
    }
}
