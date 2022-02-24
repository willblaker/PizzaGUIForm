import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PizzaGUIFrame extends JFrame {
    private Double totalPrice = 0.0;
    private String all = "";

    JPanel mainPnl;
    JPanel crustPnl;
    JPanel sizePnl;
    JPanel toppingsPnl;
    JPanel orderPnl;
    JPanel buttonPnl;

    JRadioButton tButton, rButton, ddButton;

    JComboBox size;

    JCheckBox Pepperoni, Mushroom, Sausage, Onion, bOlives, gPepper, eCheese;

    JButton orderBtn, clearBtn, quitBtn;
    JTextArea total;
    JScrollPane scrollTotal;


    public PizzaGUIFrame(){
        mainPnl = new JPanel();
        mainPnl.setLayout(new GridLayout(5,1));

        createCrustPnl();
        mainPnl.add(crustPnl);

        createSizePnl();
        mainPnl.add(sizePnl);

        createToppingsPnl();
        mainPnl.add(toppingsPnl);

        createOrderPnl();
        mainPnl.add(orderPnl);

        createButtonPnl();
        mainPnl.add(buttonPnl);

        add(mainPnl);
        setSize(500,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    public void createCrustPnl() {
        crustPnl = new JPanel();
        crustPnl.setLayout(new GridLayout(1,3));
        crustPnl.setBorder(new TitledBorder(new EtchedBorder(), "Crust"));

        tButton = new JRadioButton("Thin");
        rButton = new JRadioButton("Regular");
        ddButton = new JRadioButton("Deep-dish");

        crustPnl.add(tButton);
        crustPnl.add(rButton);
        crustPnl.add(ddButton);

        rButton.setSelected(true);

        ButtonGroup group = new ButtonGroup();
        group.add(rButton);
        group.add(tButton);
        group.add(ddButton);
    }

    public void createSizePnl(){
        sizePnl = new JPanel();
        sizePnl.setBorder(new TitledBorder(new EtchedBorder(), "Size"));

        size = new JComboBox<>();
        size.addItem("Small");
        size.addItem("Medium");
        size.addItem("Large");
        size.addItem("Super");

        sizePnl.add(size);
    }

    public void createToppingsPnl(){
        toppingsPnl = new JPanel();
        toppingsPnl.setLayout(new GridLayout(2,3));
        toppingsPnl.setBorder(new TitledBorder(new EtchedBorder(),"Toppings"));

        Pepperoni = new JCheckBox("Pepperoni");
        Sausage = new JCheckBox("Sausage");
        Mushroom = new JCheckBox("Mushroom");
        eCheese = new JCheckBox("Extra Cheese");
        gPepper = new JCheckBox("Green Pepper");
        bOlives = new JCheckBox("Black Olives");

        toppingsPnl.add(Pepperoni);
        toppingsPnl.add(Sausage);
        toppingsPnl.add(Mushroom);
        toppingsPnl.add(eCheese);
        toppingsPnl.add(gPepper);
        toppingsPnl.add(bOlives);
    }

    public void createOrderPnl(){
        orderPnl = new JPanel();
        orderPnl.setBorder(new TitledBorder(new EtchedBorder(),"Receipt"));

        total = new JTextArea("",5,42);
        scrollTotal = new JScrollPane(total);

        orderPnl.add(scrollTotal);
    }

    public void createButtonPnl(){
        buttonPnl = new JPanel();
        buttonPnl.setLayout(new GridLayout(1,3));

        orderBtn = new JButton("Order");
        orderBtn.addActionListener((ActionEvent ae)
                -> {
                    all = "==========================================================\n";

                    if(ddButton.isSelected())
                        all +="Double-dish + ";
                    else if(tButton.isSelected())
                        all +="Thin + ";
                    else if(rButton.isSelected())
                        all +="Regular + ";

                    if((size.getSelectedItem()+"").equals("Small")){
                        all += size.getSelectedItem() + "\t 8.00\n";
                        totalPrice += 8.0;
                    }
                    else if((size.getSelectedItem()+"").equals("Medium")){
                        all += size.getSelectedItem() + "\t 12.00\n";
                        totalPrice += 12.0;
                    }
                    else if((size.getSelectedItem()+"").equals("Large")){
                        all += size.getSelectedItem() + "\t 16.00\n";
                        totalPrice += 16.0;
                    }
                    else if((size.getSelectedItem()+"").equals("Super")){
                        all += size.getSelectedItem() + "\t 20.00\n";
                        totalPrice += 20.0;
                    }

                    if(Pepperoni.isSelected()){
                        all += "Pepperoni\t\t" + " " + "1.00\n";
                        totalPrice += 1.0;
                    }
                    if(Sausage.isSelected()){
                        all += "Sausage\t\t" + " " + "1.00\n";
                        totalPrice += 1.0;
                    }
                    if(Mushroom.isSelected()){
                        all += "Mushroom\t\t" + " " + "1.00\n";
                        totalPrice += 1.0;
                    }
                    if(gPepper.isSelected()){
                        all += "Green Pepper\t\t" + " " + "1.00\n";
                        totalPrice += 1.0;
                    }
                    if(bOlives.isSelected()){
                        all += "Black Olives\t\t" + " " + "1.00\n";
                        totalPrice += 1.0;
                    }
                    if(eCheese.isSelected()){
                        all += "Extra Cheese\t\t" + " " + "1.00\n";
                        totalPrice += 1.0;
                    }

                    all +="\nSub-total:\t\t" + " "+ totalPrice;
                    all +="\nTax: \t\t" + " "+ Math.round((totalPrice * 0.07)*100.0)/100.0;
                    all +="\n--------------------------------------------------------------------------------------------------------";
                    all += "\nTotal:\t\t" + " " + (totalPrice + (totalPrice*0.07));
                    all += "\n==========================================================";

                    total.append(all);
                }
        );

        quitBtn = new JButton("Quit");
        quitBtn.addActionListener((ActionEvent ae)
                -> {
            int result = JOptionPane.showConfirmDialog(null,"Sure? You want to exit?", "Swing Tester",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION)
                System.exit(0);
            else if (result == JOptionPane.NO_OPTION)
                JOptionPane.showMessageDialog(null,"Quit cancelled");
        }
    );

        clearBtn = new JButton("Clear");
        clearBtn.addActionListener((ActionEvent ae)
                ->{
            total.selectAll();
            total.replaceSelection("");
        }
    );

        buttonPnl.add(orderBtn);
        buttonPnl.add(clearBtn);
        buttonPnl.add(quitBtn);
    }
}
