package Ciphers.HashFunctions;

import Ciphers.Basic.HashFunction;
import Ciphers.Utils.BitUtil;

public abstract class SHA extends HashFunction {
    protected byte[] padding_process(byte[] input) {
        int l_orig = input.length;
        int l = l_orig << 3;
        int k = 2;
        while ((l + k) % 1024 != 896) k++;
        l += k + 128;
        byte[] prepared = new byte[l >> 3];
        System.arraycopy(input, 0, prepared, 0, l_orig);
        prepared[l_orig] = (byte) 0b10000000;
        byte[] coping = BitUtil.ByteArrays.longToByteArray(l_orig << 3);
        System.arraycopy(coping, 0, prepared, prepared.length - coping.length, coping.length);
        return prepared;
    }
}
