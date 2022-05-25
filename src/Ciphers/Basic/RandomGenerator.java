package Ciphers.Basic;

public interface RandomGenerator {
    void setSeed(byte[] seed);

    byte[] nextBytes(byte[] in);

    void reset();

}
