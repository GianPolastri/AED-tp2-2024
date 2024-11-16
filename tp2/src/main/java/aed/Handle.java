package aed;

import java.lang.reflect.Type;

public class Handle{
    public int pos;
    Handle(int nuevo_){
    this.pos = nuevo_;   
    }
    public int setPos(int pos) {
        this.pos = pos;
        return pos;
    }
    
}
