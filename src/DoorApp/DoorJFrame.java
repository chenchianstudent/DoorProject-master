package DoorApp;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DoorJFrame extends javax.swing.JFrame {

    private String rename;
    protected String ok = "3";

    public DoorJFrame() {
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        record = new javax.swing.JButton();
        add = new javax.swing.JButton();
        cardNumber = new javax.swing.JTextField();
        goBack = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        showPass = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        record.setText("��蕭??��?�蕭??��");
        record.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recordActionPerformed(evt);
            }
        });

        add.setText("??��?��?���?��");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        cardNumber.getDocument().addDocumentListener(new DocumentListener() {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://120.108.111.137:3306/105021043?characterEncoding=utf-8";
            String user = "root", passwd = "h3041723";
            public void changedUpdate(DocumentEvent e) {
                runPass();
            }

            public void removeUpdate(DocumentEvent e) {
                runPass();
            }

            public void insertUpdate(DocumentEvent e) {
                runPass();
            }

            public void runPass() {
            	
                try {
                    Class.forName(driver);
                    Connection con = DriverManager.getConnection(url, user, passwd);
                    Statement st = con.createStatement();

                    if (!cardNumber.getText().equals("")) {
                        String s = " SELECT * FROM Member";
                        PreparedStatement ps = con.prepareStatement(s);
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                        	
                            if (cardNumber.getText().equals(rs.getString("ID"))) {
                                ok = "1";
                                rename = rs.getString("Name");
                                SwingUtilities.invokeLater(new Runnable()////��?��?����?����?�����??��蹎��?�����??��謘extField���蕭??��
                                {
                                    public void run()
                                    {
                                        cardNumber.setText("");
                                    }
                                });
                                showPass.setForeground(Color.BLUE);
                                showPass.setText("??��?�蕭��?��?��?�����??�蕭謍�?�蕭謍�?��?��?��??��?��??�??��?��?��?��?��??��蕭!!");
                                TestClass tc = new TestClass();
                                tc.run();
//                                try{
//                                	Thread.sleep(1000);
//                                	showPass.setText("");//消除??�示
//                                }catch(Exception e){}
                                break;
                            }
                            
                            
                        }
                        if (ok.equals("1")) {
                            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                            Date date = new Date();
                            String strDate = sdFormat.format(date);
                            String s2 = "INSERT INTO Record (Name,Time,State) VALUES(?,?,'已註冊')";
                            PreparedStatement ps2 = con.prepareStatement(s2);
                            ps2.setString(1, rename);
                            ps2.setString(2, strDate);
                            ps2.executeUpdate();
                            ok="3";
                        } else {
                        	SwingUtilities.invokeLater(new Runnable()////��?��?����?����?�����??��蹎��?�����??��謘extField���蕭??��
                                    {
                                        public void run()
                                        {
                                            cardNumber.setText("");
                                        }
                                    });
                            showPass.setForeground(Color.RED);
                            showPass.setText("??��?�蕭��?��?����?��?��?��?��?�����蕭��蝴���??��?�蕭���??�?��?��??��?��??��?��?��?��?�蕭蹎刻?��??���?�蕭謕�?�!!");
                        }
                    }
                    con.close();
                } catch (Exception e) {
                }
            }
        });

        goBack.setText("??��?��蕭豯�?��?��?��?�??��?�蕭蹓遴��");
        goBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goBackActionPerformed(evt);
            }
        });

        jLabel1.setText("���蕭��??��?��?�����?");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(goBack)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cardNumber)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(93, 93, 93)
                                .addComponent(record, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(77, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(showPass, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(145, 145, 145))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(goBack)
                .addGap(18, 18, 18)
                .addComponent(cardNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showPass, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(record, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(111, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void recordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recordActionPerformed
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://120.108.111.137:3306/105021043?characterEncoding=utf-8";
        String user = "root", passwd = "h3041723";

        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, passwd);

            Statement st = con.createStatement();
            String s = " SELECT * FROM Record";
            PreparedStatement ps = con.prepareStatement(s);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("Name");
                String time = rs.getString("Time");
                System.out.println("ID: " + name + "  Time: " + time);
            }
            System.out.println("-----------------------------------");
            con.close();
        } catch (Exception e) {
        }

    }//GEN-LAST:event_recordActionPerformed

    
    
    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        new AddFrame().setVisible(true);
    }//GEN-LAST:event_addActionPerformed

    private void goBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goBackActionPerformed
        setVisible(false);
        new Login().setVisible(true);
    }//GEN-LAST:event_goBackActionPerformed

    public static void main(String args[]) {
        DoorJFrame df = new DoorJFrame();
        df.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JTextField cardNumber;
    private javax.swing.JButton goBack;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton record;
    private javax.swing.JLabel showPass;
    // End of variables declaration//GEN-END:variables
}
