package Ciphers.PrimitiveCiphers;

import Ciphers.Basic.Cipher;
import Ciphers.Utils.BitUtil;

public class Vernam extends Cipher {
    private byte[] key;
    String vernam_key = "Key len must equal to data len!";
    public Vernam(){}
    public Vernam(byte[] key){this.key = key;}
    public void setKey(byte[] key){this.key = key;}
    @Override
    public byte[] encrypt(byte[] input) {
        if(key.length!=input.length) throw new VernamException(vernam_key);
        return BitUtil.Operation.XOR(input,key);
    }

    @Override
    public byte[] decrypt(byte[] input) {
        if(key.length!=input.length) throw new VernamException(vernam_key);
        return BitUtil.Operation.XOR(input,key);
    }
   private class VernamException extends IllegalArgumentException{
        VernamException(String e){super(e);}
    }
}
