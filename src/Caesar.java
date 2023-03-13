
/**
 *@author nabil
 * This Class implements the interface Codec
 * Encrypt and decryt according to the Caeser method
 * The Caeser Encryption replaces the letters forward or backward according to the Cipher key
 * for example Thm is going to be our key for this example
 * key = [T,h,m] ==> [1,2,3]    key = 3
 * so in this case our key is 3 because we measure up our key which is 3, only letters are allowed
 * and our text is going to be for example ABCD
 * input = ABCD
 * output = DEFG
 * */

public class Caesar implements Codec {

    /**
     * Declaration for the key word typ String
     * */
    private String keyword;



    /**
     *overwrites  Kodiere from interface Codec Which has a parameter Typ String
     *This Method takes the Encrypted text and change in to a Decrypted Text
     * 1. First Step: a for loop to count the length of the Encrypted Text the i should not be bigger then the length of the Encrypted text
     * 2. second Step: it checks if the letter is between a - z or A - Z it will move the letter depending on the key
     * if there is a space or number  between the text it will stay the same
     * @param klartext takes the Encrypted Text
     * @return secretText
     * */

    @Override
    public String kodiere(String klartext) {

        // secrettext Typ StringBilder
        StringBuilder secretText = new StringBuilder();

        // measure the length of the key number
        int keynum = this.keyword.length();

        // 1.First Step
        for (int i = 0; i < klartext.length(); i++ ){

            // Declaration and initialization for ascii of our Encrypted Text (klartext) typ char
            char ascii = klartext.charAt(i);

            // 2.Second Step
            //             a     -        z    or          A              Z
            if ((ascii >= 97 && ascii <= 122) ||(ascii >= 65 && ascii <= 90)){


                if ((ascii >= 97 ) && 122 < (ascii + keynum)){
                    secretText.append((char)(96+(ascii + keynum) - 122));


                }else if ((ascii <= 90) && (ascii + keynum) > 90){
                    secretText.append((char)(64 + (ascii + keynum) - 90));

                }else {
                    secretText.append((char)(klartext.charAt(i) + keynum));
                }
            }else {
                secretText.append(ascii);
            }

        }
        return secretText.toString();
    }


    /**
     *overwrites dekodiere from interface Codec Which has a parameter Typ String
     *This Method takes the Decrypted text and change in to a Encrypted Text
     * 1. First Step: a for loop to count the length of the Encrypted Text the i should not be bigger then the length of the Encrypted text
     * 2. second Step: it checks if the letter is between a - z or A - Z it will move the letter depending on the key
     * if there is a space or number  between the text it will stay the same
     * @param geheimtext takes the Encrypted Text
     * @return decrypText
     * */

    @Override
    public String dekodiere(String geheimtext) {

        // decryptext Typ StringBilder
        StringBuilder decrypText = new StringBuilder();

        // measure the length of the key number
        int keynum = this.keyword.length();

        // 1.First Step
        for (int i = 0; i < geheimtext.length(); i++){

            // Declaration and initialization for ascii of our Encrypted Text (klartext) typ char
            char ascii = geheimtext.charAt(i);

            // 2.Second Step
            //             a     -        z    or          A              Z
            if ((ascii >= 97 && ascii <= 122) ||(ascii >= 65 && ascii <= 90)) {

                if ((ascii >= 97) && (ascii - keynum) < 97) {
                    decrypText.append((char) (ascii - keynum + 26));

                } else if ((ascii <= 90) && (ascii - keynum) < 65) {
                    decrypText.append((char) (ascii - keynum + 26));

                }else {
                    decrypText.append((char) (ascii - keynum));
                }
            }else {
                decrypText.append(ascii);
            }
        }
        return decrypText.toString();
    }


    /**
     *overwrites gibLosung from interface Codec
     * gives the keyword as a String
     * @return keyword
     * */
    @Override
    public String gibLosung() {
        return keyword;
    }


    /**
     *overwrites setzeLosung from interface Codec
     * makes the keyword as the parameter schlussel
     * @param schluessel
     * @throws IllegalArgumentException
     * */
    @Override
    public void setzeLosung(String schluessel) throws IllegalArgumentException {

        if (schluessel.length() > 26 || schluessel.length() == 0 || (!schluessel.matches("[a-zA-Z]+"))) {
            throw new IllegalArgumentException("");
        }
        this.keyword = schluessel;
    }
}
