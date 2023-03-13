public class runer {

    public static void main(String[] args){

        Codec x = new Caesar();
        Codec y = new Wuerfel();


        CodecGUI z = new CodecGUI(x,y);
        z.gui();



    }
}
