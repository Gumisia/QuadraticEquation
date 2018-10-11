import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;


public class Main extends JFrame {

    private Main(){
        initcomponents();
    }

    private void initcomponents(){

        this.setTitle("Quadratic Equation");
        this.setBounds(700, 300, 500, 250);

        jPanel.setLayout(new GridLayout(4,1));


        jLabel.setHorizontalAlignment(JLabel.CENTER);
        jLabel.setVerticalAlignment(JLabel.CENTER);
        jLabel.setOpaque(true);
        jLabel.setBackground(Color.WHITE);

        container.setLayout(new GridLayout(1,3));

        a.setToolTipText("Enter a");
        b.setToolTipText("Enter b");
        c.setToolTipText("Enter c");

        clear.setToolTipText("Clear everything? Click!");
        solve.setToolTipText("Check the solution of the quadratic equation");

        solve.setMnemonic(KeyEvent.VK_S);
        clear.setMnemonic(KeyEvent.VK_C);

        container.add(a);
        container.add(b);
        container.add(c);

        jPanel.add(jLabel);
        jPanel.add(container);
        jPanel.add(solve);
        jPanel.add(clear);

        a.setText("3");
        setAx2(3);
        a.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                String text = a.getText();


                try {

                    if(checkNegative(text)){
                        text = text.substring(1);
                        if(numberTest(text)) {
                            setAx2(Integer.parseInt(text) * (-1));
                        }
                    }
                    else if(numberTest(text)){
                        setAx2(Integer.parseInt(text));
                    }
                } catch (StringIndexOutOfBoundsException ex){
                    System.out.println("Empty String A");
                    setAx2(0); setBx(0); setC0(0);
                }


            }

        });


        b.setText("2");
        setBx(2);
        b.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                String text = b.getText();

                try{

                    if(checkNegative(text)){
                        text = text.substring(1);
                        if(numberTest(text)) {
                            setBx(Integer.parseInt(text) * (-1));
                        }
                    }
                    else if(numberTest(text)) {
                        setBx(Integer.parseInt(text));
                    }
                } catch (StringIndexOutOfBoundsException ex){
                    System.out.println("Empty String B");
                    setAx2(0); setBx(0); setC0(0);
                }
            }
        });


        c.setText("-8");
        setC0(-8);
        c.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                String text = c.getText();

                try{

                    if(checkNegative(text)){
                        text = text.substring(1);
                        if(numberTest(text)) {
                            setC0(Integer.parseInt(text) * (-1));
                        }
                    }
                    else if(numberTest(text)) {
                        setC0(Integer.parseInt(text));
                    }
                }catch (StringIndexOutOfBoundsException ex){
                    System.out.println("Empty String C");
                    setAx2(0); setBx(0); setC0(0);
                }
            }
        });


        clear.addActionListener(ae->{
            jLabel.setText(null);
            setBackgroundOnMac(jLabel, Color.WHITE);
            a.setText("");
            b.setText("");
            c.setText("");

            setAx2(0);
            setBx(0);
            setC0(0);

        });

        solve.addActionListener(ae->{
            setBackgroundOnMac(jLabel, Color.WHITE);


            if(quadraticEquation()) {
                jLabel.setText(countQuadraticEquation());
                setBackgroundOnMac(jLabel, Color.BLUE);

            }
        });

        this.setContentPane(jPanel);
        this.setDefaultCloseOperation(3);

    }

    private JPanel jPanel = new JPanel();
    private JButton solve = new JButton("Solve");
    private JButton clear = new JButton("Clear");
    private JLabel jLabel = new JLabel("Hello in QuadraticEquation :) !");
    private Container container = new Container();
    private JTextField a = new JTextField();
    private JTextField b = new JTextField();
    private JTextField c = new JTextField();

    double x1, x2, delta;

    public static void main(String[] args) {
        new Main().setVisible(true);
    }

    private int ax2, bx, c1;


    private void setAx2(int ax2) {
        this.ax2 = ax2;
    }

    private void setBx(int bx) {
        this.bx = bx;
    }

    private void setC0(int c1) {
        this.c1 = c1;
    }

    private boolean numberTest(String text){
        char tab[] = text.toCharArray();
        boolean test = true;
        for(int i=0; (i<tab.length)&&(test); i++){
            if(tab[i]<'0'||tab[i]>'9') test = false;
        }
        if(!test){
            jLabel.setText("Number format error!");
            delta=0; x1=0; x2=0;
            setBackgroundOnMac(jLabel, Color.RED);
        }
        if(test && (ax2!=0) && (bx!=0) && (c1!=0)){
            jLabel.setText(null);
            setBackgroundOnMac(jLabel, Color.WHITE);

        }
        return test;
    }

    private boolean checkNegative(String text){
        boolean test=false;
        if(text.charAt(0)=='-'){
            test=true;
        }
        return test;
    }

    private boolean quadraticEquation(){
        boolean test = true;
        if(ax2==0){
            test = false;
            jLabel.setText("Not a quadratic equation!");
            setBackgroundOnMac(jLabel, Color.RED);
        }

        return test;
    }

    private void setBackgroundOnMac(JComponent component, Color color){
        component.setBackground(color);
        component.setOpaque(true);
    }

    private String countQuadraticEquation(){


            delta = bx*bx + (-4)*ax2*c1;


            if(delta<0) {
                delta = 0;
                setBackgroundOnMac(jLabel, Color.RED);
                return "Delta < 0, no solutions :(";
            }
            else if(delta>0){
                double deltSqrt = Math.sqrt(delta);

                x1 = (((-1)*bx)+deltSqrt)/(2.0*ax2);
                x2 = (((-1)*bx)-deltSqrt)/(2.0*ax2);
                delta=0;
                return "x1 = "+ x1 + ", x2 = " + x2;
            } else {
                x1 = ((-1) * bx) / (2.0 * ax2);
                return "x= " + x1;
        }




//        return "";

    }

}