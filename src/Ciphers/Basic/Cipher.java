package Ciphers.Basic;

public abstract class Cipher {

    public abstract byte[] encrypt(byte[] input);

    public abstract byte[] decrypt(byte[] input);
}
