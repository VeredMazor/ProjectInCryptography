package MacAndElgamal;
import java.util.Random;

public class Kriptografi {
	 Random rand = new Random();

	    public int modulus(int nilai, int modulo){
	        return (nilai % modulo + modulo) % modulo;
	    }

	    public int inverseModulo(int nilai, int modulo){
	        int inv = 0;
	        nilai = modulus(nilai,modulo);
	        for(int i=0;i<modulo;i++){
	            if((nilai * i) % modulo == 1){
	                inv = i;
	                break;
	            }
	        }
	        return inv;
	    }

	    public int gcd(int a, int b) { return b==0 ? a : gcd(b, a%b); }

	    public boolean cekInvMod(int nilai, int modulo){
	        return gcd(nilai,modulo) == 1 && modulo!=1;
	    }

	    public static boolean isPrime(int n){
	        if(n <= 1)
	            return false;
	        else if(n <= 3)
	            return true;
	        else if(n % 2 == 0 || n % 3 == 0)
	            return false;
	        int i = 5;
	        while(i*i <= n){
	            if(n % i == 0 || n % (i+2) == 0)
	                return false;
	            i+=6;
	        }
	        return true;
	    }

	    public int genPrime(int min, int max){
	        int num = randInt(min,max);
	        while (!isPrime(num)) {
	            num = randInt(min,max);
	        }
	        return num;
	    }

	    public int randInt(int min, int max) {
	        return rand.nextInt((max - min) + 1) + min;
	    }
}
