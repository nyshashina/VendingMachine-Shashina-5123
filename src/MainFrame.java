import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import static javax.swing.GroupLayout.Alignment.*;

import Domen.Bottle;
import Domen.HotDrink;
import Domen.Product;
import Services.*;

//class AboutDialog extends JDialog {
//    private final int row;
//    private final int column;
//
//    public AboutDialog(JFrame owner, VendingMachine vm, int row, int column) {
//        super(owner, "About DialogTest", true);
//        this.row = row;
//        this.column = column;
//        setLayout(new BorderLayout(100,5));
//        add(new JLabel(String.format("Внесите %d р.", vm.getProduct(row, column).getPrice())), BorderLayout.CENTER);
//
//        JButton ok = new JButton("ok");
//        ok.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent event) {
//                setVisible(false);
//            }
//        });
//
//        // Кнопка ОК помещается в нижнюю часть окна.
//
//        JPanel panel = new JPanel();
//        panel.add(ok);
//        add(panel, BorderLayout.SOUTH);
//        setSize(260, 160);
//    }
//}

class PayBuyDialog extends JDialog {
    private final int row;
    private final int column;
    private final VendingMachine vm;
    private final JLabel totalLabel;
    private final JLabel changeLabel;
    private final JButton btnBuy;

    public PayBuyDialog(JFrame owner, VendingMachine vm, int row, int column) {
        super(owner, "Совершите покупку", true);
        this.vm = vm;
        this.row = row;
        this.column = column;
        Product pr = vm.getProduct(row, column);
        JLabel      label           = new JLabel(String.format("Внесите %d р.:", pr.getPrice()));
        JButton     fiveRubles           = new JButton("+ 5");
        JButton     twoRubles           = new JButton("+ 2");
        JButton     oneRuble           = new JButton("+ 1");
        JButton     tenRubles           = new JButton("+ 10");
        JButton     btnBuy         = new JButton("Купить"   );
        this.btnBuy = btnBuy;
        JButton     btnCancel       = new JButton("Отменить");
        JLabel      total           = new JLabel("Внесено: 0 р.");
        this.totalLabel = total;
        JLabel      change           = new JLabel("Ваша сдача: 0 р.");
        this.changeLabel = change;

        // Определение менеджера расположения
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        // Создание горизонтальной группы
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(LEADING)
                                .addComponent(label)
                                .addComponent(total)
                                .addComponent(change)))
                .addGroup(layout.createParallelGroup(LEADING)
//                        .addComponent(oneRuble)
//                        .addGroup(layout.createSequentialGroup()
//                                .addGroup(layout.createParallelGroup(LEADING)
//                                        .addComponent(total)
//                                        .addComponent(change))
//                                .addGroup(layout.createParallelGroup(LEADING)
//                                        .addComponent(ohno))
                        )
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(oneRuble))
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(twoRubles))
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(fiveRubles))
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(tenRubles))
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(btnBuy)
                        .addComponent(btnCancel))

        );

        layout.linkSize(SwingConstants.HORIZONTAL, btnBuy, btnCancel);

        // Создание вертикальной группы
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(label)
//                                .addGroup(layout.createParallelGroup(LEADING)
//                                        .addGroup(layout.createSequentialGroup()
//                                                .addGroup(layout.createParallelGroup(BASELINE)
//                                                                .addComponent(total)
////                                        .addComponent(ohno)
//                                                )
//                                                .addGroup(layout.createParallelGroup(BASELINE)
//                                                        .addComponent(change))))
                        .addComponent(oneRuble)
                        .addComponent(twoRubles)
                        .addComponent(fiveRubles)
                        .addComponent(tenRubles)
                        .addComponent(btnBuy))
                .addGroup(layout.createParallelGroup(LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(BASELINE)
                                        .addComponent(total)
//                                        .addComponent(ohno)
                                      )
                                .addGroup(layout.createParallelGroup(BASELINE)
                                        .addComponent(change)))
                        .addComponent(btnCancel))
        );

        setTitle("Покупка");
        btnBuy.setEnabled(false);
        oneRuble.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                coinCb(1);
            }
        });

        twoRubles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                coinCb(2);
            }
        });

        fiveRubles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                coinCb(5);
            }
        });

        tenRubles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                coinCb(10);
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                vm.getDispenser().returnc();
                setVisible(false);
            }
        });
        btnBuy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                int change = vm.getDispenser().giveChange(pr.getPrice());
                vm.getDispenser().acceptInserted();
                vm.getHolder().release(row, column);
                SuccessDialog sd = new SuccessDialog(owner, change);
                setVisible(false);
                sd.setVisible(true);
            }
        });
        pack();
    }
    String sumText(int sum) {
        return String.format("Внесено %d р.", sum);
    }

    String changeText(int sum) {
        return String.format("Ваша сдача: %d р.", sum);
    }

    void coinCb(int coin) {
        Product pr = vm.getProduct(row, column);
        CoinDispenser cd = vm.getDispenser();
        cd.insertc(coin);
        totalLabel.setText(sumText(cd.getInsertedSum()));
        if (cd.getInsertedSum() >= pr.getPrice()) {
            changeLabel.setText(changeText(cd.getInsertedSum() - pr.getPrice()));
            btnBuy.setEnabled(true);
        }
    }
}

class SuccessDialog extends JDialog {
    public SuccessDialog(JFrame owner, int change) {
        super(owner, "Успешно", true);
        add(new JLabel("Покупка совершена."), BorderLayout.NORTH);
        if (change > 0) {
            add(new JLabel(String.format("Ваша сдача: %d р.", change)));
        }
        JButton btnOK = new JButton("OK");
        JPanel panel = new JPanel();
        panel.add(btnOK);
        add(panel, BorderLayout.SOUTH);
//        setSize(260, 160);
        pack();
        btnOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                setVisible(false);
            }
        });
    }
}
public class MainFrame extends JFrame {
    private VendingMachine vm;

    final private Font mainFont = new Font("Segoe print", Font.BOLD, 18);

    JLabel lbWelcome;

    public void initialize(VendingMachine vendingMachine) {
        this.vm = vendingMachine;


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, vm.getHolder().getColumns(), 5, 5));
        Product[][] prs = vm.getHolder().getProducts();
        int l = Integer.toString(vm.getHolder().getColumns()).length();
        String fs = String.format("%%d%%0%dd. %%s - %%d р.", l);
        for (int i = 0; i < vm.getHolder().getRows(); i++) {
            for (int j = 0; j < vm.getHolder().getColumns(); j++) {
               Product pr = prs[i][j];
               if (pr != null && pr.getAmount() > 0) {
                   JButton btn1 = new JButton(String.format(fs, i + 1, j + 1, pr.getName(), pr.getPrice()));
                   MainFrame this1 = this;
                   final int row = i + 1;
                   final int column = j + 1;
                   btn1.addActionListener(new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent e) {
                           PayBuyDialog ad = new PayBuyDialog(this1, vm, row, column);
                           ad.setVisible(true);
                       }

                   });
                   buttonPanel.add(btn1);

               } else {
                   buttonPanel.add(new JLabel());
               }
           }
        }

        lbWelcome = new JLabel();
        lbWelcome.setFont(mainFont);

        JPanel mainPanel = new JPanel();
//        mainPanel.setLayout(new GridLayout(0, vm.holder.getColumns(), 5, 5));
        mainPanel.setBackground(new Color(128, 128, 255));

        mainPanel.add(lbWelcome);

        mainPanel.add(buttonPanel);

        add(mainPanel);

        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        buttonPanel.setOpaque(false);

        setTitle("VendingMachines");
        setSize(750, 600);
        setMaximumSize(new Dimension(750, 600));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        pack();


    }


//    public void initialize1(VendingMachine vendingMachine) {
//        this.vm = vendingMachine;
//
//        JLabel lbFirstName = new JLabel("First Name");
//        lbFirstName.setFont(mainFont);
//
//        tfFirstName = new JTextField();
//        tfFirstName.setFont(mainFont);
//
//
//        JLabel lbLastName = new JLabel("First Name");
//        lbLastName.setFont(mainFont);
//
//
//
//        tfLastName = new JTextField();
//        tfLastName.setFont(mainFont);
//
//
//
//        JPanel formPanel = new JPanel();
//        formPanel.setLayout(new GridLayout(4, 1, 5, 5));
//        formPanel.add(lbFirstName);
//        formPanel.add(tfFirstName);
//        formPanel.add(lbLastName);
//        formPanel.add(tfLastName);
//
//
//        lbWelcome = new JLabel();
//        lbWelcome.setFont(mainFont);
//
//
//
//        JButton btnOk = new JButton("Ok");
//        btnOk.setFont(mainFont);
//        btnOk.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//
//                String firstName = tfFirstName.getText();
//                String lastName = tfFirstName.getText();
//                lbWelcome.setText("Hello " + firstName + " " + lastName);
//
//
//            }
//
//        });
//
//
//        JButton btnClear = new JButton("Clear");
//        btnClear.setFont(mainFont);
//        btnClear.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                tfFirstName.setText("");
//                tfFirstName.setText("");
//                lbWelcome.setText("");
//
//            }
//
//        });
//
//
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setLayout(new GridLayout(1, 2, 5, 5));
//        buttonPanel.add(btnOk);
//        buttonPanel.add(btnClear);
//
//
//        JPanel mainPanel = new JPanel();
//        mainPanel.setLayout(new BorderLayout());
//        mainPanel.setBackground(new Color(128, 128, 255));
//
//        mainPanel.add(formPanel, BorderLayout.NORTH);
//
//
//        mainPanel.add(lbWelcome, BorderLayout.CENTER);
//
//
//        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
//
//
//        add(mainPanel);
//
//
//        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
//        formPanel.setOpaque(false);
//        buttonPanel.setOpaque(false);
//
//
//        setTitle("VendingMachines");
//        setSize(750, 600);
//        setMaximumSize(new Dimension(750, 400));
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        setVisible(true);
//    }

    public static void main(String[] arg) {
        Holder h = new Holder(3, 3, 10);
        VendingMachine vm = new VendingMachine(h, new CoinDispenser(1));
        Product item1 = new Product("Lays", 100, 10);
        Product item2 = new Product("cola", 50, 10);
        Product item3 = new Bottle("Mineral water", 101, 10, (float)1.5);
        Product item4 = new HotDrink("Nescafe", 75, 10, 70);
        Product item5 = new HotDrink("Black Tea", 50, 10, 75);
        Product item6 = new HotDrink("Green Tea", 60, 10, 60);
        vm.getHolder().addProduct(item1, 1, 1);
        vm.getHolder().addProduct(item2, 1, 2);
        vm.getHolder().addProduct(item3, 1, 3);
        vm.getHolder().addProduct(item4, 2, 1);
        vm.getHolder().addProduct(item5, 2, 2);
        vm.getHolder().addProduct(item6, 2, 3);
//        vm.holder.addProduct(item6, 3, 1);
//        vm.holder.addProduct(item6, 3, 2);
//        vm.holder.addProduct(item6, 3, 3);
//        vm.holder.addProduct(item6, 4, 2);

        MainFrame myFrame = new MainFrame();
        myFrame.initialize(vm);
    }

}