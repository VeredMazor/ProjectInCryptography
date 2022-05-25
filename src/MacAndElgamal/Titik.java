package MacAndElgamal;

public class Titik extends Kriptografi{
	public int x;
    public int y;

    public Titik(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        String result;
        if(this.isInf())
            result = "(INFINITY)";
        else
            result = "(" + x + "," + y + ")";
        return result;
    }

    public boolean isInf(){
        return (x == -1 && y == -1);
    }

    public Titik setInf(){
        return new Titik(-1,-1);
    }

    public Titik douPoint(int a, int p){
        int Xr,Yr;

        long s = (((long)3*(long)x*(long)x + a) * inverseModulo(2*y,p))%p;
        Xr = modulus(((int)s*(int)s)-(2*x),p);
        Yr = modulus((-1*y)+(int)s*(x-Xr),p);
        return new Titik(Xr,Yr);
    }

    public Titik addPoint(Titik Tp, int a, int p){
        if(this.isInf())
            return Tp;
        if(this == Tp)
            return this.douPoint(a,p);
        if(this.x == Tp.x)
            return setInf();
        int Xr,Yr;
        int s = modulus((y-Tp.y)*inverseModulo(x-Tp.x,p),p);
        Xr = modulus((s*s)-x-Tp.x,p);
        Yr = modulus((-1*y)+s*(x-Xr),p);
        return new Titik(Xr,Yr);
    }

    public Titik multiPoint(int n, int a, int p){
        if(n==1)
            return this;
        Titik nP, Tp;
        nP = this; Tp = this;
        for(int i=2;i<=n;i++){
            nP = nP.addPoint(Tp,a,p);
        }
        return nP;
    }
}
