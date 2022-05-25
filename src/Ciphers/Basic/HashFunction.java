package Ciphers.Basic;

public abstract class HashFunction {
    protected static int LENGTH = 0;

    public static int getLength() {
        return LENGTH;
    }

    public abstract byte[] process(byte[] input);
}
