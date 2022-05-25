package MacAndElgamal;


import java.math.BigInteger;


public class Point {
    private BigInteger x;
    private BigInteger y;

    Point() {
        x = BigInteger.ZERO;
        y = BigInteger.ZERO;
    }

    public Point(long x, long y) {
        this.x = BigInteger.valueOf(x);
        this.y = BigInteger.valueOf(y);
    }

    Point(BigInteger x, BigInteger y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object p) {
        if (p instanceof Point) {
            Point pointP = (Point) p;
            return pointP.x.equals(this.x) && pointP.y.equals(this.y);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        return String.format("(%s, %s)", x.toString(), y.toString());
    }

	public BigInteger getY() {
		return y;
	}
	public BigInteger getX() {
		return x;
	}
}
