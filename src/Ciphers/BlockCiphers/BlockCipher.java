package Ciphers.BlockCiphers;

import Ciphers.Basic.Cipher;
import Ciphers.Utils.BitUtil;
import Ciphers.Utils.MathUtil;


public abstract class BlockCipher extends Cipher {
    public static final byte ECB = 0;
    public static final byte CBC = 1;
    public static final byte OFB = 2;
    public static final byte CFB = 3;
    public static final byte CTR = 4;
    int KeySize = 0;
    int[] PossibleKeySizes;
    int IV_Size = 0;
    byte MODE_SELECTED = 0;
    BlockCipherAlgorithm algorithm;

    protected BlockCipher(){

    }


    public static BlockCipher getInstance(BlockCipher blockCipher) {
        return blockCipher;
    }

    public static BlockCipher getInstance(BlockCipher blockCipher, byte mode) {
        blockCipher.setMode(mode);
        return blockCipher;
    }

    public static BlockCipher getInstance(BlockCipher blockCipher, byte mode, byte[] key) {
        blockCipher.setMode(mode);
        blockCipher.setKey(key);
        return blockCipher;
    }

    public static BlockCipher getInstance(BlockCipher blockCipher, byte mode, byte[] key, byte[] iv) {
        blockCipher.setMode(mode);
        blockCipher.setKey(key);
        blockCipher.setIV(iv);
        return blockCipher;
    }


    @Override
    public byte[] encrypt(byte[] plain) {
        if (plain == null || plain.length == 0) throw new BlockCipherException(BlockCipherException.DATA_NULL);
        switch (this.MODE_SELECTED) {
            case BlockCipher.CBC:
                if (this.algorithm.IV == null) throw new BlockCipherException(BlockCipherException.IV_NULL);
                return algorithm.encryptInCBC(plain);
            case BlockCipher.OFB:
                if (this.algorithm.IV == null) throw new BlockCipherException(BlockCipherException.IV_NULL);
                return algorithm.encryptInOFB(plain);
            case BlockCipher.CFB:
                if (this.algorithm.IV == null) throw new BlockCipherException(BlockCipherException.IV_NULL);
                return algorithm.encryptInCFB(plain);
            case BlockCipher.CTR:
                if (this.algorithm.IV == null) throw new BlockCipherException(BlockCipherException.IV_NULL);
                return algorithm.encryptInCTR(plain);
            default: {
                if (plain.length % algorithm.blocksize != 0)
                    throw new BlockCipherException(BlockCipherException.DATA_LEN, algorithm.blocksize);
                return algorithm.encryptInECB(plain);
            }
        }
    }


    @Override
    public byte[] decrypt(byte[] ciph) {
        if (ciph == null || ciph.length == 0) throw new BlockCipherException(BlockCipherException.DATA_NULL);
        switch (this.MODE_SELECTED) {
            case BlockCipher.CBC:
                if (this.algorithm.IV == null) throw new BlockCipherException(BlockCipherException.IV_NULL);
                return algorithm.decryptInCBC(ciph);
            case BlockCipher.OFB:
                if (this.algorithm.IV == null) throw new BlockCipherException(BlockCipherException.IV_NULL);
                return algorithm.decryptInOFB(ciph);
            case BlockCipher.CFB:
                if (this.algorithm.IV == null) throw new BlockCipherException(BlockCipherException.IV_NULL);
                return algorithm.decryptInCFB(ciph);
            case BlockCipher.CTR:
                if (this.algorithm.IV == null) throw new BlockCipherException(BlockCipherException.IV_NULL);
                return algorithm.decryptInCTR(ciph);
            default: {
                if ((ciph.length % algorithm.blocksize != 0)) {
                    throw new BlockCipherException(BlockCipherException.DATA_LEN, algorithm.blocksize);
                }

                return algorithm.decryptInECB(ciph);
            }
        }
    }

    public byte[] MAC(byte[] input) {
        throw new BlockCipherException(BlockCipherException.NOT_SUPPORTED, "MAC");
    }

    public abstract void setKey(byte[] key);


    public void reset() {
        algorithm = null;
        this.MODE_SELECTED = 0;

    }

    public void setIV(byte[] IV) {
        if (IV == null || IV.length != getIV_Size())
            throw new BlockCipherException(BlockCipherException.IV_LEN, getIV_Size() * 8, getIV_Size());
        algorithm.IV = IV;
    }


    public void setMode(byte mode) {
        MODE_SELECTED = (mode > 4 || mode < 0) ? 0 : mode;
    }


    public int getKeySize() {
        return this.KeySize;
    }

    public int[] getPossibleKeySize() {
        return this.PossibleKeySizes;
    }

    public int getIV_Size() {
        return this.IV_Size;
    }

    protected final class BlockCipherException extends IllegalArgumentException {
        final static String KEY_LEN = "Key length must be %d bit (%d bytes)!";
        final static String KEY_LEN_MANY = "Key length must be %s bit (%s bytes)!";
        final static String IV_LEN = "IV length must be %d bit (%d bytes)!";
        final static String DATA_LEN = "Data length must be multiple of %d!";
        final static String DATA_NULL = "Data length must be >0!";
        final static String IV_NULL = "Initialization Vector is not set!";
        final static String NOT_SUPPORTED = "%s is not supported by this algorithm!";

        BlockCipherException() {
            super();
        }


        BlockCipherException(String message) {
            super(message);
        }

        BlockCipherException(String message, Object... args) {
            super(String.format(message, args));
        }

        BlockCipherException(String message, String name) {
            super(String.format(message, name));
        }
    }

    protected abstract class BlockCipherAlgorithm {

        byte[] IV = null;
        byte blocksize = 8;

        BlockCipherAlgorithm() {

        }

        BlockCipherAlgorithm(byte[] key) {

        }

        abstract byte[] encryptInECB(byte[] input);

        abstract byte[] decryptInECB(byte[] input);


        byte[] encryptInCBC(byte[] input) {
            byte last_len = (byte) (input.length % blocksize == 0 ? 0 : BitUtil.Extend.extendToSize(input.length, blocksize) - input.length);
            byte[] input_extended = new byte[input.length % blocksize == 0 ? input.length + blocksize : BitUtil.Extend.extendToSize(input.length, blocksize)];
            System.arraycopy(input, 0, input_extended, 0, input.length);
            for (int i = input.length; i < input_extended.length; i++) input_extended[i] = last_len;
            byte[] STATE = this.IV.clone();
            byte[] encrypted = new byte[input_extended.length];

            for (int i = 0; i < input_extended.length; i += blocksize) {
                byte[] chunck = new byte[blocksize];
                System.arraycopy(input_extended, i, chunck, 0, blocksize);
                chunck = encryptInECB(BitUtil.Operation.XOR(STATE, chunck));
                STATE = chunck.clone();
                System.arraycopy(chunck, 0, encrypted, i, blocksize);
            }


            return encrypted;


        }

        byte[] decryptInCBC(byte[] input) {
            byte[] STATE = this.IV.clone();
            byte[] decrypted_extended = new byte[input.length];
            for (int i = 0; i <= input.length - blocksize; i += blocksize) {
                byte[] chunck = new byte[blocksize];
                System.arraycopy(input, i, chunck, 0, chunck.length);
                byte[] STATE_m = chunck.clone();
                chunck = BitUtil.Operation.XOR(STATE, decryptInECB(chunck));
                STATE = STATE_m;
                System.arraycopy(chunck, 0, decrypted_extended, i, blocksize);
            }

            byte len = decrypted_extended[decrypted_extended.length - 1];
            if (len == 0) len = blocksize;
            byte[] decrypted = new byte[decrypted_extended.length - len];
            System.arraycopy(decrypted_extended, 0, decrypted, 0, decrypted.length);
            return decrypted;
        }

        byte[] encryptInOFB(byte[] input) {
            byte[] gamma = new byte[BitUtil.Extend.extendToSize(input.length, blocksize)];
            byte[] STATE = this.IV.clone();
            for (int i = 0; i < gamma.length; i += blocksize) {
                STATE = encryptInECB(STATE);
                System.arraycopy(STATE, 0, gamma, i, blocksize);
            }

            return BitUtil.Operation.XOR(input, gamma);

        }

        byte[] decryptInOFB(byte[] input) {
            byte[] gamma = new byte[BitUtil.Extend.extendToSize(input.length, blocksize)];
            byte[] STATE = this.IV.clone();
            for (int i = 0; i < gamma.length; i += blocksize) {
                STATE = encryptInECB(STATE);
                System.arraycopy(STATE, 0, gamma, i, blocksize);
            }

            return BitUtil.Operation.XOR(input, gamma);


        }

        byte[] encryptInCFB(byte[] input) {
            int len = BitUtil.Extend.extendToSize(input.length, blocksize);
            byte[] extended = new byte[len];

            System.arraycopy(input, 0, extended, 0, input.length);
            byte[] STATE = this.IV.clone();

            for (int i = 0; i < extended.length; i += blocksize) {
                STATE = encryptInECB(STATE);
                byte[] chunck = new byte[blocksize];
                System.arraycopy(extended, i, chunck, 0, blocksize);
                chunck = BitUtil.Operation.XOR(chunck, STATE);
                System.arraycopy(chunck, 0, extended, i, blocksize);
                STATE = chunck;

            }

            byte[] encrypted = new byte[input.length];

            System.arraycopy(extended, 0, encrypted, 0, encrypted.length);
            return encrypted;
        }

        byte[] decryptInCFB(byte[] input) {
            int len = BitUtil.Extend.extendToSize(input.length, blocksize);
            byte[] extended = new byte[len];
            System.arraycopy(input, 0, extended, 0, input.length);

            byte[] decrypted = new byte[input.length];

            byte[] STATE = this.IV.clone();
            for (int i = 0; i < input.length; i += blocksize) {
                STATE = encryptInECB(STATE);
                byte[] chunck = new byte[blocksize];
                System.arraycopy(extended, i, chunck, 0, blocksize);
                System.arraycopy(BitUtil.Operation.XOR(chunck.clone(), STATE), 0, extended, i, blocksize);
                STATE = chunck;
            }
            System.arraycopy(extended, 0, decrypted, 0, decrypted.length);
            return decrypted;
        }

        byte[] encryptInCTR(byte[] input) {
            byte[] gamma = new byte[BitUtil.Extend.extendToSize(input.length, blocksize)];
            byte[] CTR = this.IV.clone();
            for (int i = 0; i < gamma.length; i += blocksize) {
                CTR = MathUtil.BigNumbersOperation.Sum(CTR,new byte[]{0,0,0,1});
                CTR = encryptInECB(CTR);
                System.arraycopy(CTR, 0, gamma, i, blocksize);
            }

            return BitUtil.Operation.XOR(input, gamma);
        }

        byte[] decryptInCTR(byte[] input) {
            byte[] gamma = new byte[BitUtil.Extend.extendToSize(input.length, blocksize)];
            byte[] CTR = this.IV.clone();
            for (int i = 0; i < gamma.length; i += blocksize) {
                CTR = MathUtil.BigNumbersOperation.Sum(CTR,new byte[]{0,0,0,1});
                CTR = encryptInECB(CTR);
                System.arraycopy(CTR, 0, gamma, i, blocksize);
            }

            return BitUtil.Operation.XOR(input, gamma);
        }


    }
}
