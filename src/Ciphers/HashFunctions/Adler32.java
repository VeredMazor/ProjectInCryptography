package Ciphers.HashFunctions;

import Ciphers.Basic.HashFunction;
import Ciphers.Utils.BitUtil;

public class Adler32 extends HashFunction {
    @Override
    public byte[] process(byte[] input) {
        short A = 0x1;
        short B = 0x0;
        for (byte value:input) {
            A=(short) ((A + value) % 0xfff1);
            B=(short) ((B + A) % 0xfff1);
        }
        return BitUtil.ByteArrays.shortArrayToByteArray(new short[]{B,A});
    }

}
