package Ciphers.Utils;

import java.util.Comparator;

public final class AlgorithmUtil {
    public static void reverseArray(byte[] input) {
        byte tmp;
        for (int i = 0; i < input.length >> 1; ++i) {
            tmp = input[i];
            input[i] = input[input.length - i - 1];
            input[input.length - i - 1] = tmp;
        }

    }

    public static void reverseArray(short[] input) {
        short tmp;
        for (int i = 0; i < input.length >> 1; ++i) {
            tmp = input[i];
            input[i] = input[input.length - i - 1];
            input[input.length - i - 1] = tmp;
        }

    }

    public static void reverseArray(int[] input) {
        int tmp;
        for (int i = 0; i < input.length >> 1; ++i) {
            tmp = input[i];
            input[i] = input[input.length - i - 1];
            input[input.length - i - 1] = tmp;
        }

    }

    public static void reverseArray(long[] input) {
        long tmp;
        for (int i = 0; i < input.length >> 1; ++i) {
            tmp = input[i];
            input[i] = input[input.length - i - 1];
            input[input.length - i - 1] = tmp;
        }

    }

    public static void reverseArray(char[] input) {
        char tmp;
        for (int i = 0; i < input.length >> 1; ++i) {
            tmp = input[i];
            input[i] = input[input.length - i - 1];
            input[input.length - i - 1] = tmp;
        }

    }

    public static void reverseArray(double[] input) {
        double tmp;
        for (int i = 0; i < input.length >> 1; ++i) {
            tmp = input[i];
            input[i] = input[input.length - i - 1];
            input[input.length - i - 1] = tmp;
        }

    }

    public static void reverseArray(float[] input) {
        float tmp;
        for (int i = 0; i < input.length >> 1; ++i) {
            tmp = input[i];
            input[i] = input[input.length - i - 1];
            input[input.length - i - 1] = tmp;
        }

    }

    public static void reverseArray(boolean[] input) {
        boolean tmp;
        for (int i = 0; i < input.length >> 1; ++i) {
            tmp = input[i];
            input[i] = input[input.length - i - 1];
            input[input.length - i - 1] = tmp;
        }

    }

    public static <Type> void reverseArray(Type[] input) {
        Type tmp;
        for (int i = 0; i < input.length >> 1; ++i) {
            tmp = input[i];
            input[i] = input[input.length - i - 1];
            input[input.length - i - 1] = tmp;
        }
    }


    public static void reverseMatrix(byte[][] input) {
        byte[] tmp;
        for (int i = 0; i < input.length >> 1; ++i) {
            tmp = input[i];
            input[i] = input[input.length - i - 1];
            input[input.length - i - 1] = tmp;
        }
    }

    public static void reverseMatrix(short[][] input) {
        short[] tmp;
        for (int i = 0; i < input.length >> 1; ++i) {
            tmp = input[i];
            input[i] = input[input.length - i - 1];
            input[input.length - i - 1] = tmp;
        }
    }

    public static void reverseMatrix(int[][] input) {
        int[] tmp;
        for (int i = 0; i < input.length >> 1; ++i) {
            tmp = input[i];
            input[i] = input[input.length - i - 1];
            input[input.length - i - 1] = tmp;
        }
    }

    public static void reverseMatrix(long[][] input) {
        long[] tmp;
        for (int i = 0; i < input.length >> 1; ++i) {
            tmp = input[i];
            input[i] = input[input.length - i - 1];
            input[input.length - i - 1] = tmp;
        }
    }

    public static void reverseMatrix(float[][] input) {
        float[] tmp;
        for (int i = 0; i < input.length >> 1; ++i) {
            tmp = input[i];
            input[i] = input[input.length - i - 1];
            input[input.length - i - 1] = tmp;
        }
    }

    public static void reverseMatrix(double[][] input) {
        double[] tmp;
        for (int i = 0; i < input.length >> 1; ++i) {
            tmp = input[i];
            input[i] = input[input.length - i - 1];
            input[input.length - i - 1] = tmp;
        }
    }

    public static void reverseMatrix(char[][] input) {
        char[] tmp;
        for (int i = 0; i < input.length >> 1; ++i) {
            tmp = input[i];
            input[i] = input[input.length - i - 1];
            input[input.length - i - 1] = tmp;
        }
    }

    public static void reverseMatrix(boolean[][] input) {
        boolean[] tmp;
        for (int i = 0; i < input.length >> 1; ++i) {
            tmp = input[i];
            input[i] = input[input.length - i - 1];
            input[input.length - i - 1] = tmp;
        }
    }

    public static <Type> void reverseMatrix(Type[][] input) {
        Type[] tmp;
        for (int i = 0; i < input.length >> 1; ++i) {
            tmp = input[i];
            input[i] = input[input.length - i - 1];
            input[input.length - i - 1] = tmp;
        }
    }


    public static int indexOfElement(byte[] input, byte element) {
        for (int i = 0; i < input.length; i++) {
            if (input[i] == element) return i;
        }
        return -1;
    }

    public static int indexOfElement(short[] input, short element) {
        for (int i = 0; i < input.length; i++) {
            if (input[i] == element) return i;
        }
        return -1;
    }

    public static int indexOfElement(int[] input, int element) {
        for (int i = 0; i < input.length; i++) {
            if (input[i] == element) return i;
        }
        return -1;
    }

    public static int indexOfElement(long[] input, long element) {
        for (int i = 0; i < input.length; i++) {
            if (input[i] == element) return i;
        }
        return -1;
    }

    public static int indexOfElement(float[] input, float element) {
        for (int i = 0; i < input.length; i++) {
            if (input[i] == element) return i;
        }
        return -1;
    }

    public static int indexOfElement(double[] input, double element) {
        for (int i = 0; i < input.length; i++) {
            if (input[i] == element) return i;
        }
        return -1;
    }

    public static int indexOfElement(char[] input, char element) {
        for (int i = 0; i < input.length; i++) {
            if (input[i] == element) return i;
        }
        return -1;
    }

    public static int indexOfElement(boolean[] input, boolean element) {
        for (int i = 0; i < input.length; i++) {
            if (input[i] == element) return i;
        }
        return -1;
    }

    public static <Type> int indexOfElement(Type[] input, Type element) {
        for (int i = 0; i < input.length; i++) {
            if (input[i].equals(element)) return i;
        }
        return -1;
    }


    public static int binarySearch(byte[] array, byte seek, int startpos, int endpos) {
        if (startpos == endpos) return -1;
        int l2 = startpos + (Math.abs(startpos - endpos) / 2);
        if (array[l2] == seek) return l2;
        if (seek > array[l2]) return binarySearch(array, seek, l2 + 1, endpos);
        if (seek < array[l2]) return binarySearch(array, seek, startpos, l2);
        return -1;
    }

    public static int binarySearch(short[] array, short seek, int startpos, int endpos) {
        if (startpos == endpos) return -1;
        int l2 = startpos + (Math.abs(startpos - endpos) / 2);
        if (array[l2] == seek) return l2;
        if (seek > array[l2]) return binarySearch(array, seek, l2 + 1, endpos);
        if (seek < array[l2]) return binarySearch(array, seek, startpos, l2);
        return -1;
    }

    public static int binarySearch(int[] array, int seek, int startpos, int endpos) {
        if (startpos == endpos) return -1;
        int l2 = startpos + (Math.abs(startpos - endpos) / 2);
        if (array[l2] == seek) return l2;
        if (seek > array[l2]) return binarySearch(array, seek, l2 + 1, endpos);
        if (seek < array[l2]) return binarySearch(array, seek, startpos, l2);
        return -1;
    }

    public static int binarySearch(long[] array, long seek, int startpos, int endpos) {
        if (startpos == endpos) return -1;
        int l2 = startpos + (Math.abs(startpos - endpos) / 2);
        if (array[l2] == seek) return l2;
        if (seek > array[l2]) return binarySearch(array, seek, l2 + 1, endpos);
        if (seek < array[l2]) return binarySearch(array, seek, startpos, l2);
        return -1;
    }

    public static int binarySearch(float[] array, float seek, int startpos, int endpos) {
        if (startpos == endpos) return -1;
        int l2 = startpos + (Math.abs(startpos - endpos) / 2);
        if (array[l2] == seek) return l2;
        if (seek > array[l2]) return binarySearch(array, seek, l2 + 1, endpos);
        if (seek < array[l2]) return binarySearch(array, seek, startpos, l2);
        return -1;
    }

    public static int binarySearch(double[] array, double seek, int startpos, int endpos) {
        if (startpos == endpos) return -1;
        int l2 = startpos + (Math.abs(startpos - endpos) / 2);
        if (array[l2] == seek) return l2;
        if (seek > array[l2]) return binarySearch(array, seek, l2 + 1, endpos);
        if (seek < array[l2]) return binarySearch(array, seek, startpos, l2);
        return -1;
    }

    public static int binarySearch(char[] array, char seek, int startpos, int endpos) {
        if (startpos == endpos) return -1;
        int l2 = startpos + (Math.abs(startpos - endpos) / 2);
        if (array[l2] == seek) return l2;
        if (seek > array[l2]) return binarySearch(array, seek, l2 + 1, endpos);
        if (seek < array[l2]) return binarySearch(array, seek, startpos, l2);
        return -1;
    }

    public static <Type> int binarySearch(Type[] array, Type seek, int startpos, int endpos, Comparator<Type> typeComparator) {
        if (startpos == endpos) return -1;
        int l2 = startpos + (Math.abs(startpos - endpos) / 2);
        if (typeComparator.compare(seek, array[l2]) == 0) return l2;
        if (typeComparator.compare(seek, array[l2]) < 0)
            return binarySearch(array, seek, l2 + 1, endpos, typeComparator);
        if (typeComparator.compare(seek, array[l2]) > 0) return binarySearch(array, seek, startpos, l2, typeComparator);
        return -1;
    }






}
