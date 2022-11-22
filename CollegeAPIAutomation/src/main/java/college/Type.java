package college;

public enum Type {
    PRIVATE,
    GOVERNMENT,
    EMPTY("");

    final String empty;
    Type(String name) { this.empty = name; }
    Type() { this(null); }
    @Override
    public String toString() {
        return empty == null ? super.toString() : empty;
    }

}
