package Ciphers.PrimitiveCiphers;

import Ciphers.Basic.Cipher;


public class Vigenere extends Cipher {
    private byte[] key;

    public Vigenere() {

    }

    public Vigenere(byte[] key) {
        this.key = key;
    }

    public void setKey(byte[] key){
        this.key = key;
    }

    @Override
    public byte[] encrypt(byte[] input) {
        byte[] encrypted = input.clone();
        if(input.length > key.length){
            byte[] key_max = new byte[input.length];
            for (int i = 0; i < input.length; i += key.length) {
                System.arraycopy(key, 0, key_max, i, (i + key.length > input.length? input.length - i : key.length));
            }
            key = key_max.clone();
        }
        else if(input.length < key.length){
            byte[] key_min = new byte[input.length];
            System.arraycopy(key,0,key_min,0,key_min.length);
            key = key_min.clone();
        }
        for (int i = 0; i < input.length; i++) {
            encrypted[i] = (byte) ( ((int)(input[i]) + (int)(key[i])) &0xff);
        }

        return encrypted;
    }

    @Override
    public byte[] decrypt(byte[] input) {
        byte[] encrypted = input.clone();
        if(input.length > key.length){
            byte[] key_max = new byte[input.length];
            for (int i = 0; i < input.length; i += key.length) {
                System.arraycopy(key, 0, key_max, i, (i + key.length > input.length? input.length - i : key.length));
            }
            key = key_max.clone();
        }
        else if(input.length < key.length){
            byte[] key_min = new byte[input.length];
            System.arraycopy(key,0,key_min,0,key_min.length);
            key = key_min.clone();
        }
        for (int i = 0; i < input.length; i++) {
            encrypted[i] = (byte) ( ((int)(input[i]) - (int)(key[i]))&0xff);
        }

        return encrypted;
    }
}
