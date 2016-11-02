package common;

/**
 * Created by Rick on 11/1/2016.
 */
public class Tuple<A, B> {
    public A _1;
    public B _2;
    public Tuple(A left, B right) {
        _1 = left;
        _2 = right;
    }

    public static <C, D> Tuple<C, D> of(C left, D right) {
        return new Tuple<>(left, right);
    }

    @Override
    public String toString() {
        return "<" + _1 + ", " + _2 +">";
    }
}

