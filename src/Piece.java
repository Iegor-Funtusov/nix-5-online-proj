import java.util.ArrayList;

public class Piece {

    int x;
    int y;
    boolean isColour;
    ArrayList<Piece> ps;
    String type;

    public Piece(int x, int y, boolean isColour, String type, ArrayList<Piece> ps){
        this.x = x;
        this.y = y;
        this.isColour = isColour;
        this.type = type;
        this.ps = ps;
        ps.add(this);
    }

    public void move(int xp, int yp){

        for(Piece p: ps){
            if(p.x == xp && p.y == yp){
                p.kill();
            }
        }

        this.x = xp;
        this.y = yp;

    }

    public void kill(){
        ps.remove(this);
    }
}
