package Ciphers.PrimitiveCiphers;
import Ciphers.Basic.Cipher;


public class Caesar extends Cipher {
    private int rot = 0;
    public Caesar(int rot){
        this.rot = rot;
    }
    public void setRot(int rot){
        this.rot = rot;
    }
    public Caesar(){

    }

    @Override
    public byte[] encrypt(byte[] input) {
        byte[] encrypted = input.clone();
        for (int i = 0; i < encrypted.length; i++){
          encrypted[i] = (byte)( ((int) (encrypted[i]) + rot) & 0xff);
        }
        return encrypted;
    }

    @Override
    public byte[] decrypt(byte[] input) {
        byte[] decrypted = input.clone();
        for (int i = 0; i < decrypted.length; i++){
            decrypted[i] = (byte)( ((int) (decrypted[i]) - rot) & 0xff);
        }
        return decrypted;
    }

}
