package Ciphers.Utils;


public final class BitUtil {

    private static final int BYTE_BITSIZE = 8;
    private static final int SHORT_BITSIZE = 16;
    private static final int INT_BITSIZE = 32;
    private static final int LONG_BITSIZE = 64;

    public static class Rotation {
        public static long rotateR(long i, long bits) {
            return ((i >>> bits) | (i << (LONG_BITSIZE - bits)));
        }

        public static long rotateL(long i, long bits) {
            return ((i << bits) | (i >>> (LONG_BITSIZE - bits)));
        }

        public static int rotateR(int i, int bits) {
            return ((i >>> bits) | (i << (INT_BITSIZE - bits)));
        }

        public static int rotateL(int i, int bits) {
            return ((i << bits) | (i >>> (INT_BITSIZE - bits)));
        }


        public static short rotateR(short i, int bits) {
            return (short) ((i >>> bits) | (i << (SHORT_BITSIZE - bits)));
        }

        public static short rotateL(short i, int bits) {
            return (short) ((i << bits) | (i >>> (SHORT_BITSIZE - bits)));
        }

        public static byte rotateR(byte i, int bits) {
            return (byte) ((i >>> bits) | (i << (BYTE_BITSIZE - bits)));

        }

        public static byte rotateL(byte i, int bits) {
            return (byte) ((i << bits) | (i >>> (BYTE_BITSIZE - bits)));
        }

        public static byte[] rotate128R(byte[] i, int bits) {
            long[] i_64 = ByteArrays.byteArrayToLongArray(i);
            long i_64_0_c = i_64[0];
            i_64[0] >>>= bits;
            i_64[0] |= i_64[1] << (LONG_BITSIZE - bits);
            i_64[1] >>>= bits;
            i_64[1] |= i_64_0_c << (LONG_BITSIZE - bits);
            return ByteArrays.longArrayToByteArray(i_64);
        }

        public static byte[] rotate128L(byte[] i, int bits) {
            long[] i_64 = ByteArrays.byteArrayToLongArray(i);
            long i_64_0_c = i_64[0];
            i_64[0] <<= bits;
            i_64[0] |= i_64[1] >>> (LONG_BITSIZE - bits);
            i_64[1] <<= bits;
            i_64[1] |= i_64_0_c >>> (LONG_BITSIZE - bits);
            return ByteArrays.longArrayToByteArray(i_64);
        }


    }

    public static class ByteArrays {
        public static long byteArrayToLong(byte[] b) {

            long l = b[0] & 0xff;
            l = (l << 8) ^ (long) b[1] & 0xff;
            l = (l << 8) ^ (long) b[2] & 0xff;
            l = (l << 8) ^ (long) b[3] & 0xff;
            l = (l << 8) ^ (long) b[4] & 0xff;
            l = (l << 8) ^ (long) b[5] & 0xff;
            l = (l << 8) ^ (long) b[6] & 0xff;
            l = (l << 8) ^ (long) b[7] & 0xff;

            return l;
        }


        public static byte[] longToByteArray(long l) {
            return new byte[]{
                    (byte) (l >> 56),
                    (byte) (l >> 48),
                    (byte) (l >> 40),
                    (byte) (l >> 32),
                    (byte) (l >> 24),
                    (byte) (l >> 16),
                    (byte) (l >> 8),
                    (byte) l
            };
        }

        public static long[] byteArrayToLongArray(byte[] b) {
            long[] l = new long[b.length >> 3];


            for (int i = 0, j = 0; i < b.length; i += 8, j++) {
                byte[] chunk = new byte[8];
                System.arraycopy(b, i, chunk, 0, chunk.length);
                l[j] = byteArrayToLong(chunk);

            }
            return l;
        }

        public static byte[] longArrayToByteArray(long[] l) {
            byte[] b = new byte[l.length << 3];
            int padding_t = 0;
            for (int i = 0; i < l.length; i++) {
                byte[] chunk = longToByteArray(l[i]);
                System.arraycopy(chunk, 0, b, i + padding_t, chunk.length);
                padding_t += chunk.length - 1;

            }
            return b;
        }

        public static int byteArrayToInt(byte[] b) {
            int i = b[0] & 0xff;
            i = (i << 8) ^ (int) b[1] & 0xff;
            i = (i << 8) ^ (int) b[2] & 0xff;
            i = (i << 8) ^ (int) b[3] & 0xff;

            return i;
        }


        public static byte[] intToByteArray(int i) {
            return new byte[]{
                    (byte) (i >> 24),
                    (byte) (i >> 16),
                    (byte) (i >> 8),
                    (byte) i
            };
        }


        public static int[] byteArrayToIntArray(byte[] b) {
            int[] i_32 = new int[b.length >> 2];


            for (int i = 0, j = 0; i < b.length; i += 4, j++) {
                byte[] chunk = new byte[4];
                System.arraycopy(b, i, chunk, 0, chunk.length);
                i_32[j] = byteArrayToInt(chunk);

            }
            return i_32;
        }

        public static byte[] intArrayToByteArray(int[] i_32) {
            byte[] b = new byte[i_32.length << 2];
            int padding_t = 0;
            for (int i = 0; i < i_32.length; i++) {
                byte[] chunk = intToByteArray(i_32[i]);
                System.arraycopy(chunk, 0, b, i + padding_t, chunk.length);
                padding_t += chunk.length - 1;

            }
            return b;
        }


        public static short byteArrayToShort(byte[] b) {
            short s = (short) (b[0] & 0xff);
            s = (short) ((s << 8) ^ (int) b[1] & 0xff);
            return s;
        }


        public static byte[] shortToByteArray(short i) {
            return new byte[]{
                    (byte) (i >> 8),
                    (byte) i
            };
        }


        public static short[] byteArrayToShortArray(byte[] b) {
            short[] s = new short[b.length >> 1];


            for (int i = 0, j = 0; i < b.length; i += 2, j++) {
                byte[] chunk = new byte[2];
                System.arraycopy(b, i, chunk, 0, chunk.length);
                s[j] = byteArrayToShort(chunk);

            }
            return s;
        }


        public static byte[] shortArrayToByteArray(short[] s) {
            byte[] b = new byte[s.length << 1];
            int padding_t = 0;
            for (int i = 0; i < s.length; i++) {
                byte[] chunk = shortToByteArray(s[i]);
                System.arraycopy(chunk, 0, b, i + padding_t, chunk.length);
                padding_t += chunk.length - 1;

            }
            return b;
        }
    }

    public static class Print {
        public static void printHex(byte... input) {
            java.io.PrintStream stream = new java.io.PrintStream(System.out);
            for (byte b : input) {
                stream.printf("%x", b);
            }
            stream.print("\n");
        }


        public static void printHex(short... input) {
            java.io.PrintStream stream = new java.io.PrintStream(System.out);
            for (short b : input) {
                stream.printf("%04x", b);
            }
            stream.print("\n");
        }


        public static void printHex(int... input) {
            java.io.PrintStream stream = new java.io.PrintStream(System.out);
            for (int b : input) {
                stream.printf("%04x", b);
            }
            stream.print("\n");
        }

        public static void printHex(long... input) {
            java.io.PrintStream stream = new java.io.PrintStream(System.out);
            for (long b : input) {
                stream.printf("%x", b);
            }
            stream.print("\n");
        }

        public static void printHex(byte[] input, boolean padding) {
            java.io.PrintStream stream = new java.io.PrintStream(System.out);
            for (byte b : input) {
                stream.printf("%x ", b);
            }
            stream.print("\n");
        }


        public static void printHex(short[] input, boolean padding) {
            java.io.PrintStream stream = new java.io.PrintStream(System.out);
            for (short b : input) {
                stream.printf("%04x ", b);
            }
            stream.print("\n");
        }


        public static void printHex(int[] input, boolean padding) {
            java.io.PrintStream stream = new java.io.PrintStream(System.out);
            for (int b : input) {
                stream.printf("%04x ", b);
            }
            stream.print("\n");
        }

        public static void printHex(long[] input, boolean padding) {
            java.io.PrintStream stream = new java.io.PrintStream(System.out);
            for (long b : input) {
                stream.printf("%x ", b);
            }
            stream.print("\n");
        }


        public static void printBinary(byte... input) {
            java.io.PrintStream stream = new java.io.PrintStream(System.out);
            for (byte b : input) {
                printHex(Binary.byteToBinary(b));
            }
            stream.print("\n");
        }


        public static void printBinary(short... input) {
            java.io.PrintStream stream = new java.io.PrintStream(System.out);
            for (short b : input) {
                printHex(Binary.shortToBinary(b));
            }
            stream.print("\n");
        }


        public static void printBinary(int... input) {
            java.io.PrintStream stream = new java.io.PrintStream(System.out);
            for (int b : input) {
                printHex(Binary.intToBinary(b));
            }
            stream.print("\n");
        }

        public static void printBinary(long... input) {
            java.io.PrintStream stream = new java.io.PrintStream(System.out);
            for (long b : input) {
                printHex(Binary.longToBinary(b));
            }
            stream.print("\n");
        }
    }

    public static class Fission {
        public static byte[][] splitTo4bytes(byte[] input) {
            byte[][] retq = new byte[input.length >> 2][4];
            for (int i = 0, j = 0; i < input.length; i += 4, j++) {
                if (i + 4 > input.length) break;
                System.arraycopy(input, i, retq[j], 0, 4);
            }
            return retq;
        }

        public static byte[][] splitTo8bytes(byte[] input) {
            byte[][] retq = new byte[input.length >> 3][8];
            for (int i = 0, j = 0; i < input.length; i += 8, j++) {
                if (i + 8 > input.length) break;
                System.arraycopy(input, i, retq[j], 0, 8);
            }
            return retq;
        }

        public static byte[][] splitTo16bytes(byte[] input) {
            byte[][] retq = new byte[input.length >> 4][16];
            for (int i = 0, j = 0; i < input.length; i += 16, j++) {
                if (i + 16 > input.length) break;
                System.arraycopy(input, i, retq[j], 0, 16);
            }
            return retq;
        }

        public static byte[] splitBy4bits(byte[] input) {
            byte[] input_4bit = new byte[input.length << 1];
            for (int i = 0, j = 0; i < input.length; i++, j++) {
                input_4bit[j] = (byte) ((input[i] >> 4) & 0b1111);
                input_4bit[j + 1] = (byte) ((input[i] & 0b1111));
                j++;
            }
            return input_4bit;
        }

        public static byte[] concatBy4bit(byte[] input_4bit) {
            byte[] input = new byte[input_4bit.length >> 1];
            for (int i = 0, j = 0; i < input_4bit.length; i++, j++) {
                input[j] = (byte) ((input_4bit[i] << 4) | input_4bit[i + 1]);
                i++;
            }
            return input;
        }
    }

    public static class Operation {
        public static byte[] XOR(byte[] c, byte[] b) {
            byte[] a = c.clone();
            for (int i = 0; i < a.length; i++) {
                a[i] ^= b[i];
            }
            return a;
        }

        public static byte[] OR(byte[] c, byte[] b) {
            byte[] a = c.clone();
            for (int i = 0; i < a.length; i++) {
                a[i] |= b[i];
            }
            return a;
        }


        public static byte[] AND(byte[] c, byte[] b) {
            byte[] a = c.clone();
            for (int i = 0; i < a.length; i++) {
                a[i] &= b[i];
            }
            return a;
        }


        public static byte[] NOT(byte[] c) {
            byte[] a = c.clone();
            for (int i = 0; i < a.length; i++) {
                a[i] = (byte) ~a[i];
            }
            return a;
        }

        public static byte[] NAND(byte[] c, byte[] b) {
            return NOT(AND(c, b));
        }

        public static byte[] NOR(byte[] c, byte[] b) {
            return NOT(OR(c, b));
        }

        public static byte[] XNOR(byte[] c, byte[] b) {
            return NOT(XOR(c, b));
        }

    }

    public static class Binary {
        public static byte[] toBinaryOctets(byte b) {
            return new byte[]{
                    (byte) ((b & 0b10000000) >> 7),
                    (byte) ((b & 0b01000000) >> 6),
                    (byte) ((b & 0b00100000) >> 5),
                    (byte) ((b & 0b00010000) >> 4),
                    (byte) ((b & 0b00001000) >> 3),
                    (byte) ((b & 0b00000100) >> 2),
                    (byte) ((b & 0b00000010) >> 1),
                    (byte) ((b & 0b00000001) >> 0)
            };
        }

        public static byte fromBinaryOctets(byte[] b) {
            return (byte) (
                    (b[7] << 0) |
                            (b[6] << 1) |
                            (b[5] << 2) |
                            (b[4] << 3) |
                            (b[3] << 4) |
                            (b[2] << 5) |
                            (b[1] << 6) |
                            (b[0] << 7));
        }

        public static byte[] longToBinary(long l) {
            long mask = 0x8000000000000000L;
            byte[] ret = new byte[64];
            for (int i = 63, j = 0; i >= 0; i--, j++) {
                ret[j] = (byte) (((l & mask) >>> i));
                mask >>>= 1;
            }
            return ret;
        }


        public static long binaryToLong(byte[] bin) {
            long l = 0;
            byte shift = 63;
            int f = 0;
            while (bin[f] != 1 && f != bin.length-1) f++;
            if(f==0 && bin[0]!=1) return 0;
            byte[] bin_clear = new byte[bin.length - f];
            System.arraycopy(bin, f, bin_clear, 0, bin.length - f);
            bin = bin_clear;
            for (byte b : bin) {
                l |= ((long) (b) << shift--);
            }
            l = Rotation.rotateL(l,bin.length);
            return l;

        }

        public static byte[] intToBinary(int l) {
            int mask = 0x80000000;
            byte[] ret = new byte[32];
            for (int i = 31, j = 0; i >= 0; i--, j++) {
                ret[j] = (byte) (((l & mask) >>> i));
                mask >>>= 1;
            }
            return ret;
        }

        public static int binatyToInt(byte[] bin){
            int i = 0;
            byte shift = 31;
            int f = 0;
            while (bin[f] != 1 && f != bin.length-1) f++;
            if(f==0 && bin[0]!=1) return 0;
            byte[] bin_clear = new byte[bin.length - f];
            System.arraycopy(bin, f, bin_clear, 0, bin.length - f);
            bin = bin_clear;
            for (byte b : bin) {
                i |= ((int) (b) << shift--);
            }
            i = Rotation.rotateL(i,bin.length);
            return i;
        }

        public static byte[] shortToBinary(short s) {
            int mask = 0x8000;
            byte[] ret = new byte[16];
            for (int i = 15, j = 0; i >= 0; i--, j++) {
                ret[j] = (byte) (((s & mask) >>> i));
                mask >>>= 1;
            }
            return ret;
        }

        public static short binaryToShort(byte[] bin){
            short s = 0;
            byte shift = 15;
            int f = 0;
            while (bin[f] != 1 && f != bin.length-1) f++;
            if(f==0 && bin[0] != 1) return 0;
            if(bin[0] != 1) f--;
            byte[] bin_clear = new byte[bin.length - f];
            System.arraycopy(bin, f, bin_clear, 0, bin.length - f);
            bin = bin_clear;
            for (byte b : bin) {
                s |= ((short) (b) << shift--);
            }
            s = Rotation.rotateL(s,bin.length);
            return s;
        }

        public static byte[] byteToBinary(byte b) {
            int mask = 0x80;
            byte[] ret = new byte[8];
            for (int i = 7, j = 0; i >= 0; i--, j++) {
                ret[j] = (byte) (((b & mask) >>> i));
                mask >>>= 1;
            }
            return ret;
        }

        public static byte binaryToByte(byte[] bin){
            byte b = 0;
            byte shift = 7;
            int f = 0;
            while (bin[f] != 1 && f != bin.length-1) f++;
            if(f==0 && bin[0] != 1) return 0;
            else if(bin[0] == 1) f =0;
            byte[] bin_clear = new byte[bin.length - f];
            System.arraycopy(bin, f, bin_clear, 0, bin.length - f);
            bin = bin_clear;
            for (byte b_i : bin) {
                b |= ((b_i) << shift--);
            }

            b = Rotation.rotateL(b,bin.length);
            return b;
        }

    }

    public static class Extend {
        public static int extendToSize(int variable, int size) {
            if (variable % size == 0) return variable;
            return (variable - (variable % size) + size);
        }

        public static int narrowToSize(int variable, int size){
            return extendToSize(variable,size) - size >= 0 ?extendToSize(variable,size) - size:0 ;
        }
    }


}
