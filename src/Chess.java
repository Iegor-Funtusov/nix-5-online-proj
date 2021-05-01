import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Chess {

    public static void main(String[] args){

        ArrayList<Piece> ps = new ArrayList<>();

        JFrame frame = new JFrame();
        frame.setBounds(500, 150, 524, 550);
        JPanel pn = new JPanel(){
            @Override
            public void paint(Graphics g) {
                boolean colour = true;
                for( int y = 0; y < 8; y++){
                    for(int x = 0; x < 8; x++){
                        if(colour){
                            g.setColor(Color.WHITE);
                        }
                        else{
                            g.setColor(Color.BLACK);
                        }
                        g.fillRect(x*64, y*64, 64, 64);
                        colour = !colour;
                    }
                    colour = !colour;
                }
            }
        };
        frame.add(pn);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }
}
