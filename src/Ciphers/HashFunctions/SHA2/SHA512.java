package Ciphers.HashFunctions.SHA2;

import Ciphers.HashFunctions.SHA;
import Ciphers.Utils.BitUtil;

public class SHA512 extends SHA {

    protected static final int LENGTH = 64;

    public SHA512() {
        super.LENGTH = LENGTH;
    }

    public static int getLength() {
        return LENGTH;
    }

    @Override
    public byte[] process(byte[] input) {

        final long[] K = new long[]{
                0x428A2F98D728AE22L, 0x7137449123EF65CDL, 0xB5C0FBCFEC4D3B2FL, 0xE9B5DBA58189DBBCL, 0x3956C25BF348B538L,
                0x59F111F1B605D019L, 0x923F82A4AF194F9BL, 0xAB1C5ED5DA6D8118L, 0xD807AA98A3030242L, 0x12835B0145706FBEL,
                0x243185BE4EE4B28CL, 0x550C7DC3D5FFB4E2L, 0x72BE5D74F27B896FL, 0x80DEB1FE3B1696B1L, 0x9BDC06A725C71235L,
                0xC19BF174CF692694L, 0xE49B69C19EF14AD2L, 0xEFBE4786384F25E3L, 0x0FC19DC68B8CD5B5L, 0x240CA1CC77AC9C65L,
                0x2DE92C6F592B0275L, 0x4A7484AA6EA6E483L, 0x5CB0A9DCBD41FBD4L, 0x76F988DA831153B5L, 0x983E5152EE66DFABL,
                0xA831C66D2DB43210L, 0xB00327C898FB213FL, 0xBF597FC7BEEF0EE4L, 0xC6E00BF33DA88FC2L, 0xD5A79147930AA725L,
                0x06CA6351E003826FL, 0x142929670A0E6E70L, 0x27B70A8546D22FFCL, 0x2E1B21385C26C926L, 0x4D2C6DFC5AC42AEDL,
                0x53380D139D95B3DFL, 0x650A73548BAF63DEL, 0x766A0ABB3C77B2A8L, 0x81C2C92E47EDAEE6L, 0x92722C851482353BL,
                0xA2BFE8A14CF10364L, 0xA81A664BBC423001L, 0xC24B8B70D0F89791L, 0xC76C51A30654BE30L, 0xD192E819D6EF5218L,
                0xD69906245565A910L, 0xF40E35855771202AL, 0x106AA07032BBD1B8L, 0x19A4C116B8D2D0C8L, 0x1E376C085141AB53L,
                0x2748774CDF8EEB99L, 0x34B0BCB5E19B48A8L, 0x391C0CB3C5C95A63L, 0x4ED8AA4AE3418ACBL, 0x5B9CCA4F7763E373L,
                0x682E6FF3D6B2B8A3L, 0x748F82EE5DEFB2FCL, 0x78A5636F43172F60L, 0x84C87814A1F0AB72L, 0x8CC702081A6439ECL,
                0x90BEFFFA23631E28L, 0xA4506CEBDE82BDE9L, 0xBEF9A3F7B2C67915L, 0xC67178F2E372532BL, 0xCA273ECEEA26619CL,
                0xD186B8C721C0C207L, 0xEADA7DD6CDE0EB1EL, 0xF57D4F7FEE6ED178L, 0x06F067AA72176FBAL, 0x0A637DC5A2C898A6L,
                0x113F9804BEF90DAEL, 0x1B710B35131C471BL, 0x28DB77F523047D84L, 0x32CAAB7B40C72493L, 0x3C9EBE0A15C9BEBCL,
                0x431D67C49C100D4CL, 0x4CC5D4BECB3E42B6L, 0x597F299CFC657E2AL, 0x5FCB6FAB3AD6FAECL, 0x6C44198C4A475817L
        };

        long h0 = 0x6A09E667F3BCC908L;
        long h1 = 0xBB67AE8584CAA73BL;
        long h2 = 0x3C6EF372FE94F82BL;
        long h3 = 0xA54FF53A5F1D36F1L;
        long h4 = 0x510E527FADE682D1L;
        long h5 = 0x9B05688C2B3E6C1FL;
        long h6 = 0x1F83D9ABFB41BD6BL;
        long h7 = 0x5BE0CD19137E2179L;
        byte[] input_prepared = padding_process(input);
        for (int i = 0; i < input_prepared.length; i += 128) {

            long[] words_64bit = new long[80];
            byte[] chunk = new byte[128];
            System.arraycopy(input_prepared, i, chunk, 0, chunk.length);

            long[] chunk_L = BitUtil.ByteArrays.byteArrayToLongArray(chunk);
            System.arraycopy(chunk_L, 0, words_64bit, 0, chunk_L.length);

            long s0, s1;
            byte i_num = 80;

            for (int j = 16; j < i_num; j++) {
                s0 = (BitUtil.Rotation.rotateR(words_64bit[j - 15], 1)) ^ (BitUtil.Rotation.rotateR(words_64bit[j - 15], 8)) ^ (words_64bit[j - 15] >>> 7);
                s1 = (BitUtil.Rotation.rotateR(words_64bit[j - 2], 19)) ^ (BitUtil.Rotation.rotateR(words_64bit[j - 2], 61)) ^ (words_64bit[j - 2] >>> 6);

                words_64bit[j] = words_64bit[j - 16] + s0 + words_64bit[j - 7] + s1;
            }

            long a = h0;
            long b = h1;
            long c = h2;
            long d = h3;
            long e = h4;
            long f = h5;
            long g = h6;
            long h = h7;

            for (int q = 0; q < i_num; q++) {

                long S0 = (BitUtil.Rotation.rotateR(a, 28)) ^ (BitUtil.Rotation.rotateR(a, 34)) ^ (BitUtil.Rotation.rotateR(a, 39));
                long S1 = (BitUtil.Rotation.rotateR(e, 14)) ^ (BitUtil.Rotation.rotateR(e, 18)) ^ (BitUtil.Rotation.rotateR(e, 41));

                long ch = (e & f) ^ ((~e) & g);
                long maj = (a & b) ^ (a & c) ^ (b & c);


                long temp1 = h + S1 + ch + K[q] + words_64bit[q];
                long temp2 = S0 + maj;

                h = g;
                g = f;
                f = e;
                e = d + temp1;
                d = c;
                c = b;
                b = a;
                a = temp1 + temp2;
            }

            h0 = h0 + a;
            h1 = h1 + b;
            h2 = h2 + c;
            h3 = h3 + d;
            h4 = h4 + e;
            h5 = h5 + f;
            h6 = h6 + g;
            h7 = h7 + h;


        }


        byte[] hash = BitUtil.ByteArrays.longArrayToByteArray(
                new long[]{
                        h0,
                        h1,
                        h2,
                        h3,
                        h4,
                        h5,
                        h6,
                        h7
                });
        return hash;
    }


}
