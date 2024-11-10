package aed;

public class Ciudades {
    private int id;
    private int earnings;
    private int losses;
    private int superavit;
    //? Habria que ver de que no se repitan id de ciudades? Igual si se crean en bulk con un for no haria falta;
    public Ciudades(int id){
        this.id = id;
        this.earnings = 0;
        this.losses = 0;
        this.superavit = 0;
    }

    public void addEarnings(int e){
        this.earnings = this.earnings + e;
    }

    public void addLosses(int l){
        this.losses = this.losses + l;
    }

    public int getId(){
        return this.id;
    }

    public int getEarnings(){
        return this.earnings;
    }

    public int getLosses(){
        return this.losses;
    }

    public int getSuperavit(){
        this.superavit = this.earnings - this.losses;
        return this.superavit;
    }
}
