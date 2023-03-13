
/**
 * @author nabil
 * This Class implements the interface Codec
 * Encrypt and decryt according to the Wuerfel method
 * */



import java.util.ArrayList;
import java.util.List;

public class Wuerfel implements Codec {

    // Declaration for the key word typ String
    private String keyword;

    // Declaration for the key word typ int Array
    private int[] keywordArray;

    /**
     * Wuerful constructor with  parameter
     * @param key
     * */
    Wuerfel(String key){

        setzeLosung(key);
    }

    // Wuerful constructor
    public Wuerfel() {

    }

    /**
     * overwrites kodiere from interface Codec Which has a parameter Typ String
     * In this Method we have two Variables that helps us to reform our text, and also we need one more
     * Variable that help us collect our Encrypted text and returns ciphertext type StringBilder
     * Than the text is going to be divided throw loops that haves the length of the keyword.
     * Both  variables are used here, the first being a further loop
     * which always has the same length as keyword
     * As soon as the inner loop has been run through, the value from support of the variable keyparts
     * added which was declared as an array, and the variable support is emptied.
     * This process is repeated until the outer loop
     * all letters of the string "Klartext" have been run through.
     * @param klartext
     * @return ciphertext
     * */


    @Override
    public String kodiere(String klartext) {

        int a =0;
        int b;
        int kl = keyword.length();
        List<StringBuilder> keypartes = new ArrayList<>();
        StringBuilder ciphertext = new StringBuilder();
        StringBuilder support = new StringBuilder();

        while (a < klartext.length()){
            for ( b = 0; b < kl; b++){

                if ( a >= klartext.length()){
                    break;
                }
                support.append(klartext.charAt(a));
                a++;

            }

            keypartes.add(support);
            support = new StringBuilder();
        }



        int postion;
        int counter;
        postion = 0;
        counter = 0;



        while (counter < klartext.length()){
            for (int x = 0; x < kl; x++){

                for (int  z = 0; z< kl; z++){

                    if (keywordArray[z] == x){
                        postion = z;
                        break;
                    }

                }

                for (StringBuilder keypart : keypartes){
                    support = keypart;
                    if (support.length() <= postion){
                        break;
                    }
                    ciphertext.append(support.toString().charAt(postion));
                    counter++;
                }

            }

        }
        return ciphertext.toString();
    }


    /**
     *overwrites dekodiere from interface Codec Which has a parameter Typ String
     * This Method is Similar to the kodieren method
     * @param geheimtext
     * @return text
     * */

    @Override
    public String dekodiere(String geheimtext) {

        int amount = geheimtext.length() / keywordArray.length;
        int amRest = geheimtext.length() % keywordArray.length;

        List<StringBuilder> keypartes = new ArrayList<>();
        StringBuilder support = new StringBuilder();
        StringBuilder text = new StringBuilder();

        int a = 0;


        int coun = amount;
        int i;


        while (a < geheimtext.length()){
            for (int b = 0; b < keywordArray.length; b++){

                for (int c = 0; c< amRest; c++){

                    if (keywordArray[c] == b){
                        coun = amount + 1;
                        break;
                    }else {
                        coun = amount;
                    }
                }
                for (i=0; i <coun; i++){
                    support.append(geheimtext.charAt(a));
                    a++;
                }
                keypartes.add(support);
                support = new StringBuilder();
            }
        }


        int z = 0;



        while (z < geheimtext.length()){
            for (int x = 0; x <= amount; x++){

                for (int y : keywordArray){
                    if (z >= geheimtext.length()){
                        break;
                    }
                    text.append(keypartes.get(y).toString().charAt(x));
                    z++;

                }
            }
        }


        return text.toString();
    }


    /**
     *overwrites gibLosung from interface Codec
     * @return keyword
     * */

    @Override
    public String gibLosung() {
        return keyword;
    }

    /**
     * overwrites setzLosung from interface Codec
     * This Method checks if our key consists only of letters
     * Solution is calculated over a loop which the letters as int numbers recognizes.
     * @param schluessel
     * @throws IllegalArgumentException*/
    @Override
    public void setzeLosung(String schluessel) throws IllegalArgumentException {
        int counter = 0;
        int capitalAlp = 65;
        int smallAlp = 97;
        int alphabet = 25;



        if (schluessel.length() <= 1){
            throw new IllegalArgumentException("Password must be more than one letter");
        }
        this.keyword = schluessel;
        keywordArray = new int[keyword.length()];

        while ((capitalAlp <= (65 + alphabet)) || (smallAlp <= (97 + alphabet))){

            char capital = (char) capitalAlp;
            char small = (char) smallAlp;

            for (int i = 0; i < keyword.length(); i++){
                if (keyword.charAt(i) == capital || keyword.charAt(i) == small){
                    keywordArray[i] = counter;
                    counter++;
                }
            }
            capitalAlp++;
            smallAlp++;

        }


    }
}