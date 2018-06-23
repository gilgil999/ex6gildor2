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
    public Segment transfom() {
        return null;
    }
}
