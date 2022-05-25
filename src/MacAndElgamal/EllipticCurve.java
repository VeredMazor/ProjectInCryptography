package MacAndElgamal;



import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class EllipticCurve {
    private static final BigInteger MINUS_ONE = BigInteger.valueOf(-1);
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private static final BigInteger THREE = BigInteger.valueOf(3);

    private BigInteger a;
    private BigInteger b;
    private BigInteger p;

    EllipticCurve() {
        this.a = BigInteger.valueOf(1);
        this.b = BigInteger.valueOf(6);
        this.p = BigInteger.valueOf(11);
    }

    public EllipticCurve(long a, long b, long p) {
        this.a = BigInteger.valueOf(a);
        this.b = BigInteger.valueOf(b);
        this.p = BigInteger.valueOf(p);
    }

    public Point addPoint(Point p1, Point p2) {
        BigInteger gradient = this.gradient(p1, p2);

        BigInteger x = gradient.multiply(gradient).subtract(p1.getX()).subtract(p2.getX());
        x = x.mod(this.p);

        BigInteger y = gradient.multiply(p1.getX().subtract(x)).subtract(p1.getY());
        y = y.mod(this.p);

        return new Point(x, y);
    }

    public Point negatePoint(Point p) {
        BigInteger x = p.getX();
        BigInteger y = p.getY().negate().mod(this.p);

        return new Point(x, y);
    }

    public Point minusPoint(Point p1, Point p2) {
        return addPoint(p1, negatePoint(p2));
    }

    public Point doublePoint(Point p) {
        if (!p.getY().equals(BigInteger.ZERO)) {
            BigInteger gradient = gradient(p);

            BigInteger x = gradient.multiply(gradient).subtract(p.getX().multiply(TWO));
            x = x.mod(this.p);

            BigInteger y = gradient.multiply(p.getX().subtract(x)).subtract(p.getY());
            y = y.mod(this.p);

            return new Point(x, y);
        }
        return p;
    }

    public Point multiplyPoint(Point p1, long n) {
        if (n <= 0)
            return null;

        if (n == 1) {
            return p1;
        } else if (n % 2 == 1) {
            return addPoint(p1, multiplyPoint(p1, n-1));
        } else {
            return multiplyPoint(doublePoint(p1), n/2);
        }
    }

    private BigInteger gradient(Point p) {
        BigInteger n1 = p.getX().pow(2).multiply(THREE).add(this.a);
        BigInteger n2 = p.getY().multiply(TWO);

        BigInteger g = n1.multiply(n2.modInverse(this.p));
        return g.mod(this.p);
    }

    private BigInteger gradient(Point p1, Point p2) {
        BigInteger n1 = p2.getY().subtract(p1.getY());
        BigInteger n2 = p2.getX().subtract(p1.getX());

        BigInteger g = n1.multiply(n2.modInverse(this.p));
        return g.mod(this.p);
    }

    public BigInteger computeY(BigInteger x) {
        for (BigInteger i = BigInteger.ONE; i.compareTo(this.p) < 0; i = i.add(BigInteger.ONE)) {
            BigInteger m1 = x.pow(3).add(x.multiply(this.a)).add(this.b).mod(this.p);
            BigInteger res = i.pow(2).subtract(m1).mod(this.p);

            if (res.equals(BigInteger.ZERO) && x.gcd(i).equals(BigInteger.ONE)) {
                return i;
            }
        }

        return MINUS_ONE;
    }

    public boolean isPointInCurve(Point p) {
//        if ()
        return true;
    }

    public Point messageToPoint(BigInteger msg, BigInteger k) {
        BigInteger m = msg.multiply(k);
        for (BigInteger i = BigInteger.ONE; i.compareTo(k) < 0; i = i.add(BigInteger.ONE)) {
            BigInteger x = m.add(i);
            BigInteger y = this.computeY(x);
            if (!y.equals(MINUS_ONE)) {
                return new Point(x, y);
            }
        }

        return new Point(MINUS_ONE, MINUS_ONE);
    }

    public BigInteger pointToMessage(Point p, BigInteger k) {
        return p.getX().subtract(BigInteger.ONE).divide(k);
    }

    public List<Point> generateListPoint() {
        List<Point> result = new ArrayList<>();

        for (BigInteger x = BigInteger.ONE; x.compareTo(p) < 0; x = x.add(BigInteger.ONE)) {
            BigInteger y = this.computeY(x);
            if (!y.equals(MINUS_ONE)) {
                result.add(new Point(x, y));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Generate list point, for basePoint
        EllipticCurve ec = new EllipticCurve(-1, 188, 65537);

        for (Point p1: ec.generateListPoint()) {
            //System.out.print(p1);
        }
    }
}
