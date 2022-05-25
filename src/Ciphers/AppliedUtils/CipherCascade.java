package Ciphers.AppliedUtils;

import Ciphers.Basic.Cipher;

import java.util.ArrayList;

/**
 * CipherCascade class.
 * Implementation of the utility to support cascade encryption,which allows you to use multiple algorithms to encrypt data.
 * This class supports encryption/decryption with all kinds of symmetric ciphers - from block ciphers to Caesar and Vigenere.
 * WARNING:recommended to use Block Encryption Algorithms in encryption mode other than ECB (due to different blocksizes).
 * @see Ciphers.BlockCiphers.BlockCipher
 * @author Israel Nickerson (Jewpigeon)
 * @see Cipher
 * @version 1.0
 */
public class CipherCascade {


    private java.util.ArrayList<Cipher> ciphers;
    /**
     * Constuctor
     * WARNING:recommended to use Block Encryption Algorithms in encryption mode other than ECB (due to different blocksizes).
     * @see Ciphers.BlockCiphers.BlockCipher
     * @param cipher_list - initialized array of Ciphers
     */
    public CipherCascade(java.util.ArrayList<Cipher> cipher_list) {
        this.ciphers = cipher_list;

    }

    /**
     * Empty Constuctor
     * WARNING:recommended to use Block Encryption Algorithms in encryption mode other than ECB (due to different blocksizes).
     * @see Ciphers.BlockCiphers.BlockCipher
     */

    public CipherCascade() {
        this.ciphers = new java.util.ArrayList<>();
    }


    /**
     * Function to encrypt plaintext with the Cascade encryption method.
     * WARNING:recommended to use Block Encryption Algorithms in encryption mode other than ECB (due to different blocksizes).
     * @see Ciphers.BlockCiphers.BlockCipher
     * @param input - plaintext, byte array
     * @return encrypted with cascade byte array
     */
    public byte[] encryptByCascade(byte[] input) {
        byte[] encrypted = input.clone();
        for (java.util.Iterator<Cipher> I_C = this.ciphers.iterator(); I_C.hasNext(); ) {
            Cipher currentState = I_C.next();
            encrypted = currentState.encrypt(encrypted);

        }
        return encrypted;
    }

    /**
     * Function to decrypt ciphertext with the Cascade encryption method.
     * WARNING:recommended to use Block Encryption Algorithms in encryption mode other than ECB (due to different blocksizes).
     * @see Ciphers.BlockCiphers.BlockCipher
     * @param input - ciphertext, byte array
     * @return decrypted with cascade byte array
     */
    public byte[] decryptByCascade(byte[] input) {
        byte[] decrypted = input.clone();
        java.util.ArrayList<Cipher> reversed = (java.util.ArrayList<Cipher>) this.ciphers.clone();
        //Needed to be reversed to decrypt in the correct order
        java.util.Collections.reverse(reversed);
        for (java.util.Iterator<Cipher> I_C = reversed.iterator(); I_C.hasNext(); ) {
            Cipher currentState = I_C.next();
            decrypted = currentState.decrypt(decrypted);

        }
        return decrypted;
    }

    /**
     * Function to add a cipher to internal ciphers list. Actually a wrapper of ArrayList.add method.
     * @see java.util.ArrayList#add(Object)
     * @see CipherCascade#ciphers
     * @param input - Cipher to add
     */
    public void add(Cipher input) {
        this.ciphers.add(input);
    }

    /**
     * Function to get a cipher from internal ciphers list. Actually a wrapper of ArrayList.get method with some exception handling.
     * @see java.util.ArrayList#get(int)
     * @see CipherCascade#ciphers
     * @param index - index of element
     * @throws IndexOutOfBoundsException
     * @return Cipher at index or null if operation wasn't sucessfull.
     */
    public Cipher get(int index) {
        try {
            return this.ciphers.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }

    }
    /**
     * Function to insert a cipher to internal ciphers list. Actually a wrapper of ArrayList.add method
     * @see java.util.ArrayList#add(int, Object)
     * @see CipherCascade#ciphers
     * @param index - index of element
     * @param cipher - Cipher to add
     */
    public void addTo(int index, Cipher cipher) {
        this.ciphers.add(index, cipher);
    }

    /**
     * Function to remove a cipher from internal ciphers list. Actually a wrapper of ArrayList.remove method
     * @see java.util.ArrayList#remove(int)
     * @see CipherCascade#ciphers
     * @param index - index of cipher
     */
    public void remove(int index) {
        this.ciphers.remove(index);
    }

    /**
     * Function to remove last cipher of list.
     * @see CipherCascade#ciphers
     */
    public void removeLast() {
        this.ciphers.remove(this.ciphers.size() - 1);
    }
    /**
     * Function to remove first cipher of list.
     * @see CipherCascade#ciphers
     */
    public void removeFirst() {
        this.ciphers.remove(0);
    }
    /**
     * Function to clear the cipherlist. Actually a wrapper of ArrayList.clear method.
     * @see CipherCascade#ciphers
     * @see ArrayList#clear()
     */
    public void clear() {
        this.ciphers.clear();
    }

    /**
     * Function to print the state of ciphers list. Get's System.out printStream and prints Cipher.toString() in it.
     * @see java.io.PrintStream#println(String)
     * @see Cipher#toString()
     */
    public void printState() {
        java.io.PrintStream stream = new java.io.PrintStream(System.out);
        for (java.util.Iterator<Cipher> I_C = this.ciphers.iterator(); I_C.hasNext(); ) {
            stream.println(I_C.next().toString());
        }
        stream.print("\n");

    }
}




