
/**
 * @author nabil
 * This is the GUI class which show us an interface for the final project
 * Using the Swing library */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CodecGUI implements ActionListener {


    //       Declaration for the Text fild
    JTextField firsttext, secoundtext , scode1 , acode2;

    // Declaration for the Buttons
    JButton encrypt,decrypt;

    // Declaration for the RadioButtons
    JRadioButton wb,cb;

    // Declaration for the Label
    JLabel l1,l2,l3,l4;

    // Declaration
    Codec guiwuerfel;
    Codec guiceaser;

    /**
     * CodecGUI constructor with two parameters
     * @param guiwuerfel
     * @param guiceaser
     * */

    public   CodecGUI(Codec guiwuerfel, Codec guiceaser) {

        this.guiwuerfel = guiwuerfel;
        this.guiceaser = guiceaser;
    }


    /**
     * initialization for all Textfilds and Buttons ect.. and resizing them
     * */
    public void gui(){
        /**JFrame**/
        JFrame myframe = new JFrame("Cipher");

        /**JLabel key1**/
        l1 = new JLabel("Key1");
        l1.setBounds(105,18,300,50);

        /**JTextField 1**/
        scode1 = new JTextField("");
        scode1.setBounds(100, 50, 300, 50);

        /**JTextField 2**/
        acode2 = new JTextField("");
        acode2.setBounds(100, 130, 300, 50);

        /**JLabel key2**/
        l2 = new JLabel("key2");
        l2.setBounds(105,100,300,50);

        /**JTextField firsttext**/
        firsttext = new JTextField("");
        firsttext.setBounds(100, 220, 300, 100);

        /**JLabel input**/
        l3 = new JLabel("input");
        l3.setBounds(105,160,300,100);

        /**JTextField secoundtext**/
        secoundtext = new JTextField();
        secoundtext.setBounds(100, 350, 300, 100);

        /**JLabel output**/
        l4 = new JLabel("output");
        l4.setBounds(105,290,300,100);

        /**JButton Encrypt**/
        encrypt = new JButton("Encrypt");
        encrypt.setBounds(500, 200, 100, 50);

        /**JButton Decrypt**/
        decrypt = new JButton("Decrypt");
        decrypt.setBounds(500, 250, 100, 50);

        /**JRadioButton Würfel**/
        wb = new JRadioButton("Würfel");
        wb.setBounds(500, 100, 100, 30);

        /**JRadioButton Caesar**/
        cb = new JRadioButton("Caesar");
        cb.setBounds(570, 100, 100, 30);

        encrypt.addActionListener(this);
        decrypt.addActionListener(this);
        wb.addActionListener(this);
        cb.addActionListener(this);
        ButtonGroup buttons = new ButtonGroup();
        buttons.add(wb);
        buttons.add(cb);
        myframe.add(scode1);
        myframe.add(firsttext);
        myframe.add(secoundtext);
        myframe.add(encrypt);
        myframe.add(decrypt);
        myframe.add(wb);
        myframe.add(cb);
        myframe.add(acode2);
        myframe.add(l1);
        myframe.add(l2);
        myframe.add(l3);
        myframe.add(l4);
        myframe.setSize(800, 800);
        myframe.setLayout(null);
        myframe.setVisible(true);


    }






    /**
     * enabling encrypt and decrypt RadioButton and connecting them with our main methods(Wuerfel and Caesar)
     * */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (wb.isSelected()){
            acode2.setEnabled(true);

        }else if (cb.isSelected()){
            acode2.setEnabled(false);

        }else {
            JOptionPane.showMessageDialog(new JFrame(), " Please select  Würfel or Caesar", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }


        if ((e.getSource() == this.encrypt) && wb.isSelected()) {

            String helper = scode1.getText();


            try {
                guiwuerfel = new Wuerfel(helper);
                secoundtext.setText(guiwuerfel.kodiere(firsttext.getText()));
            }catch (IllegalArgumentException c){
                if (scode1.getText().length() <= 1){
                    JOptionPane.showMessageDialog(new JFrame(),"Password must be more than one letter", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            if (!acode2.getText().equals("")){
                helper = acode2.getText();
                String dectext = secoundtext.getText();
                guiwuerfel.setzeLosung(helper);
                secoundtext.setText(guiwuerfel.kodiere(dectext));
            }



        }  else if ((e.getSource() == this.decrypt) && wb.isSelected()){
            String helper = scode1.getText();
            guiwuerfel = new Wuerfel(helper);
            secoundtext.setText(guiwuerfel.dekodiere(firsttext.getText()));

            if (!acode2.getText().equals("")){
                helper = acode2.getText();
                String enctext = secoundtext.getText();
                guiwuerfel.setzeLosung(helper);
                secoundtext.setText(guiwuerfel.dekodiere(enctext));
            }




        }else if ((e.getSource() == this.encrypt ) && cb.isSelected()){
            guiceaser = new Caesar();

            try {
                guiceaser.setzeLosung(scode1.getText());
            }catch (IllegalArgumentException x){

                if (scode1.getText().length() > 26){
                    JOptionPane.showMessageDialog(new JFrame()," Password must be less than 26 letters ", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }else if (scode1.getText().length() == 0){
                    JOptionPane.showMessageDialog(new JFrame()," you must write a key Word ", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }else if(!scode1.getText().matches("[a-zA-Z]+")) {
                    JOptionPane.showMessageDialog(new JFrame(), " Only letters are allowed ", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

            }

            secoundtext.setText(guiceaser.kodiere(firsttext.getText()));




        } else if ((e.getSource() == this.decrypt) && cb.isSelected()) {

            try {
                guiceaser.setzeLosung(scode1.getText());
            }catch (IllegalArgumentException x){

                if (scode1.getText().length() > 26){
                    JOptionPane.showMessageDialog(new JFrame()," Password must be less than 26 letters ", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }else if (scode1.getText().length() == 0){
                    JOptionPane.showMessageDialog(new JFrame()," you must write a key Word ", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }else if(!scode1.getText().matches("[a-zA-Z]+")) {
                    JOptionPane.showMessageDialog(new JFrame(), " Only letters are allowed ", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

            }

            secoundtext.setText(guiceaser.dekodiere(firsttext.getText()));
        }

    }
}
