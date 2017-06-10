import javax.swing.JFrame;

public class Jogo extends JFrame{

    Jogo(){

        super("Wave");
        this.setContentPane(new Tela());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(new Tela());
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[]args){

        new Jogo();
    }

}