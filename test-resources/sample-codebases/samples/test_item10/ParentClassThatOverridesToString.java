package app.test.samples;

public class ParentClassThatOverridesToString {

    private final int b;

    public ParentClassThatOverridesToString(int b) {
        this.b = b;
    }

    public int getB() {
        return b;
    }

    @Override
    public String toString() {
        return "app.test.samples.ParentClassThatOverridesToString{" +
                "b=" + b +
                '}';
    }

}
