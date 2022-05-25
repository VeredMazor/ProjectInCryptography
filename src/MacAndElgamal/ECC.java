package MacAndElgamal;

import java.util.ArrayList;
import java.util.Collections;

public class ECC extends Kriptografi {
	
	    int p,a,b,n,h;
	    Titik G;

	    ArrayList<Integer> Qr = new ArrayList<>();
	    ArrayList<Titik> EC_Elements = new ArrayList<>();
	    ArrayList<Integer> factor = new ArrayList<>();

	    public ECC(){
	        generate();
	    }

	    public ECC(int p, int a, int b, Titik G, int n, int h){
	        this.p = p;
	        this.a = a;
	        this.b = b;
	        this.G = G;
	        this.n = n;
	        this.h = h;
	    }

	    public void generate(){
	        int N;
	        do {
	            do {
	                p = genPrime(1000, 10000);
	                a = randInt(1, 9);
	                b = randInt(1, 9);
	            } while (0 == (4 * a * a * a + 27 * b * b) % p);

	            Qr.clear();
	            EC_Elements.clear();
	            factor.clear();

	            for(int i=0;i<p;i++){
	                long temp = ((long)i*(long)i)%p;
	                Qr.add((int)temp);
	            }

	            for(int i=0;i<p;i++){
	                long temp = ((long)i*(long)i*(long)i)%p;
	                int y2 = ((int)temp+a*i+b)%p;
	                if(!Qr.contains(y2)){
	                    continue;
	                }
	                int index = Qr.indexOf(y2);
	                EC_Elements.add(new Titik(i,index));

	                if(y2 != 0)
	                    EC_Elements.add(new Titik(i,p-index));
	            }

	            N = EC_Elements.size() + 1;
	            factor = getFactor(N);

	            n=1;
	            for(int i=factor.size()-1; i>=0;i--){
	                if(isPrime(factor.get(i))){
	                    n = factor.get(i);
	                    break;
	                }
	            }
	            h = N/n;
	        }while(h > 4);

	        Titik P;
	        do{
	            do{
	                int index = randInt(0,N-2);
	                P = EC_Elements.get(index);
	            }while(P.y == 0);
	            G = P.multiPoint(h, a, p);
	        }while(G.y == 0);
	    }

	    public ArrayList<Integer> getFactor(int n){
	        ArrayList<Integer> fctr = new ArrayList<>();
	        int incrementer = n % 2 == 0 ? 1 : 2;
	        for (int i = 1; i <= Math.sqrt(n); i += incrementer) {
	            if (n % i == 0) {
	                fctr.add(i);
	                if (i != n / i) {
	                    fctr.add(n / i);
	                }
	            }
	        }
	        Collections.sort(fctr);
	        return fctr;
	    }
}

