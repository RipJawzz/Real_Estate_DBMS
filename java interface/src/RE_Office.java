import DBcons.AppData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.sql.*;

public class RE_Office extends JFrame implements ActionListener
{
        JLabel l1, l2;
        JButton b1, b2, back;
        JScrollPane output;
        JList list;
        String row[] = {""};
        ArrayList<String> op = new ArrayList<>();
        RE_Office()
        {
            super("RE_Office");
            getContentPane().setBackground(Color.black);


            l1 = new JLabel("Select the report to be viewed");
            l1.setBounds(20, 30, 1000, 60);
            l1.setFont(new Font("Times New Roman", Font.ITALIC, 45));
            l1.setForeground(Color.white);
            add(l1);

            l2 = new JLabel("Output:");
            l2.setBounds(50, 200, 200, 50);
            l2.setFont(new Font("Times New Roman", Font.ITALIC, 20));
            l2.setForeground(Color.white);
            add(l2);

            b1 = new JButton("All sales report");
            b1.setBounds(300, 100, 300, 100);
            b1.setForeground(AppData.SelectButtonFore);
            b1.setBackground(AppData.SelectButtonBack);
            b1.setFont(AppData.button_font);
            b1.addActionListener(this);
            add(b1);

            b2 = new JButton("Property rented report");
            b2.setBounds(600, 100, 300, 100);
            b2.setForeground(AppData.SelectButtonFore);
            b2.setBackground(AppData.SelectButtonBack);
            b2.setFont(AppData.button_font);
            b2.addActionListener(this);
            add(b2);

            back = new JButton("<-");
            back.setBounds(10,10,70,30);
            back.setForeground(Color.WHITE);
            back.setBackground(Color.RED);
            back.setFont(new Font("Pacifico", Font.BOLD, 15));
            back.addActionListener(this);
            //back.setVisible(false);
            add(back);

            list = new JList(row);
            output = new JScrollPane(list);
            output.setBounds(50, 250, 1300, 500);
            getContentPane().add(output);

            setLayout(null);
            setLocation(200, 300);
            setSize(400, 500);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            setVisible(true);
        }
        public void actionPerformed (ActionEvent e){
            op.clear();
            char space='_';
            try{
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+AppData.db_name, "root",AppData.db_pwd);
                Statement stmt = conn.createStatement();
                PreparedStatement st;
                ResultSet rs;
                ResultSetMetaData rm;
                int num_col,wp_len=20;
                String str,ins;

                if (e.getSource() == b1) {
                    op.add("Property sales:");
                    st=conn.prepareStatement("SELECT * \n" +
                            "FROM Sold NATURAL JOIN Property\n" +
                            "ORDER BY agent_id;");
                    rs=st.executeQuery();
                    rm= rs.getMetaData();
                    num_col = rm.getColumnCount();
                    str=" ";
                    ins="";
                    for (int i = 1; i <= num_col; i++) {
                        str = rm.getColumnName(i).toLowerCase();
                        ins=ins+str;
                        for (int i1 = 0; i1 < wp_len - str.length(); i1++)
                            ins+=space;
                    }
                    op.add(ins);
                    ins=" ";
                    op.add(ins);
                    ins="";
                    while (rs.next()) {
                        for (int i = 1; i <= num_col; i++) {
                            str = rs.getString(i).toLowerCase();
                            ins+=str;
                            for (int i1 = 0; i1 < wp_len - str.length(); i1++)
                                ins+=space;
                        }
                        op.add(ins);
                        ins="";
                    }
                    op.add(ins);
                    ins=" ";
                    op.add(ins);
                    ins="";
                    op.add("Property rented:");
                    st=conn.prepareStatement("SELECT * \n" +
                            "FROM Rented NATURAL JOIN Rental\n" +
                            "ORDER BY agent_id;");
                    rs=st.executeQuery();
                    rm= rs.getMetaData();
                    num_col = rm.getColumnCount();
                    str=" ";
                    ins="";
                    for (int i = 1; i <= num_col; i++) {
                        str = rm.getColumnName(i).toLowerCase();
                        ins=ins+str;
                        for (int i1 = 0; i1 < wp_len - str.length(); i1++)
                            ins+=space;
                    }
                    op.add(ins);
                    ins=" ";
                    op.add(ins);
                    ins="";
                    while (rs.next()) {
                        for (int i = 1; i <= num_col; i++) {
                            str = rs.getString(i).toLowerCase();
                            ins+=str;
                            for (int i1 = 0; i1 < wp_len - str.length(); i1++)
                                ins+=space;
                        }
                        op.add(ins);
                        ins="";
                    }
                } else if (e.getSource() == b2) {
                    op.add("Number of properties rented per agents");
                    st=conn.prepareStatement("SELECT agent_id,count(reg_no) as num_rented\n" +
                            "FROM rented\n" +
                            "GROUP BY agent_id;");
                    rs=st.executeQuery();
                    rm= rs.getMetaData();
                    num_col = rm.getColumnCount();
                    str=" ";
                    ins="";
                    for (int i = 1; i <= num_col; i++) {
                        str = rm.getColumnName(i).toLowerCase();
                        ins=ins+str;
                        for (int i1 = 0; i1 < wp_len - str.length(); i1++)
                            ins+=space;
                    }
                    op.add(ins);
                    ins=" ";
                    op.add(ins);
                    ins="";
                    while (rs.next()) {
                        for (int i = 1; i <= num_col; i++) {
                            str = rs.getString(i).toLowerCase();
                            ins+=str;
                            for (int i1 = 0; i1 < wp_len - str.length(); i1++)
                                ins+=space;
                        }
                        op.add(ins);
                        ins="";
                    }
                    op.add(ins);
                    ins=" ";
                    op.add(ins);
                    ins="";
                    op.add("Details of properties rented:");
                    st=conn.prepareStatement("SELECT agent_id,reg_no,start_date,end_date,rent,locality,address\n" +
                            "FROM rented NATURAL JOIN rental;");
                    rs=st.executeQuery();
                    rm= rs.getMetaData();
                    num_col = rm.getColumnCount();
                    str=" ";
                    ins="";
                    for (int i = 1; i <= num_col; i++) {
                        str = rm.getColumnName(i).toLowerCase();
                        ins=ins+str;
                        for (int i1 = 0; i1 < wp_len - str.length(); i1++)
                            ins+=space;
                    }
                    op.add(ins);
                    ins=" ";
                    op.add(ins);
                    ins="";
                    while (rs.next()) {
                        for (int i = 1; i <= num_col; i++) {
                            str = rs.getString(i).toLowerCase();
                            ins+=str;
                            for (int i1 = 0; i1 < wp_len - str.length(); i1++)
                                ins+=space;
                        }
                        op.add(ins);
                        ins="";
                    }

                }
            }
            catch(Exception ex){
                op.add("SQL exception occurred");
            }
            row=null;
            row=new String[op.size()];
            for(int i=0;i<op.size();i++) {
                row[i] = op.get(i);
                //System.out.println(row[i]);
            }
            output.setViewportView(new JList(row));
            if(e.getSource()==back){
                dispose();
                new DB_interface();
            }
        }

        public static void main (String[]args)
        {
            new RE_Office();

        }

}
