/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.productcalculations;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mothi
 */
public class ProductApp extends JFrame {

    String name,phone;
    private final JLabel lblName = new JLabel("Customaer Name:");
    private final JLabel lblCount = new JLabel("Select Product:");
    private final JLabel lblCount = new JLabel("Enter Price: ");
    private final JTextField txtName = new JTextField(15);
    private final JTextField txtPhone = new JTextField(15);
    private final JButton btnSave = new JButton("PROCESS");
    
   ArrayList<String[]> contactList= new ArrayList<>();
    private final Path filePath = Paths.get("contacts.txt");
    private final JTextArea txtDisplay =  new JTextArea(10,60);
    
    private final DefaultTableModel tblModel = new DefaultTableModel(new String[]{"Name","Phone"},0){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
    }
  };
    
    private final JTable tblContacts = new JTable(tblModel);
    private final JScrollPane scrollPane = new JScrollPane(tblContacts);
    
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu fileMenu = new JMenu("File");
    private final JMenu viewMenu = new JMenu("View");
    private final JMenu helpMenu = new JMenu("Help");
    
    private final JMenuItem newItem = new JMenuItem("New Contact");
    private final JMenuItem loadItem = new JMenuItem("Load Contact");
    private final JMenuItem saveItem = new JMenuItem("Save Contact");
    private final JMenuItem reportItem = new JMenuItem("View Report");
    private final JMenuItem exitItem = new JMenuItem("Exit Contact");
    
    private final JMenuItem refreshItem = new JMenuItem("Refresh Contact");
    private final JMenuItem aboutItem = new JMenuItem("About");
    
    public ProductApp(){
        setTitle("Product App");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
         setLayout(new BorderLayout());
         buildMenu();
        add(buildInputPanel(),BorderLayout.NORTH);
        add(BuildDisplayPanel(),BorderLayout.CENTER);
        add(buildButtonPanel(),BorderLayout.SOUTH);
        tblContacts.addMouseListener(new MouseAdapter() {
        @Override
        
       public void mouseClicked(MouseEvent e) {
           selectContact();
    
       }

            private void selectContact() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
     });
           
       loadFromFile();
       displayContacts();
       setVisible(true);
    }
    private void buildMenu(){
        fileMenu.add(newItem);
        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(reportItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        
        viewMenu.add(refreshItem);
        
        helpMenu.add(aboutItem);
        
        menuBar.add(fileMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);
        
        newItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
                
            }
        });
        
        loadItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contactList.clear();
                loadFromFile();
                displayContacts();
                showLoadWindows();
            }
        });
        
        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToFile();
                JOptionPane.showMessageDialog(ProductApp.this, "Contact saved to contact.txt");
            }
        });
        
        reportItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                   System.exit(0);
               }
        });
        
        refreshItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayContacts();
                JOptionPane.showMessageDialog(ProductApp.this, "Contact table refreshed");
            }
        });
        
        aboutItem.addActionListener(new ActionListener() {
            @Override
           public void actionPerformed(ActionEvent e) {
               JOptionPane.showMessageDialog(ProductApp.this, "Contact Book \n"
                      + "Message,save,upadete and delete contacts.","About Book App",
                      JOptionPane.INFORMATION_MESSAGE);
           }
        }); 
        
        
    }
private void clearFields() {
                  tblContacts.clearSelection();
         txtName.setText("");
         txtPhone.setText("");
            }
        
private void showLoadWindows() {
   DefaultTableModel loadModel =     
   new DefaultTableModel(new String[]{"Name","Phone"}, 0) {
       @Override
       public boolean isCellEditable(int row,int colum) {
           return false;
       }
   };

JTable loadJTable = new JTable(loadModel);
JFrame loadFrame = new JFrame("Load Contacts");
loadFrame.setSize(450, 300);
loadFrame.setLocationRelativeTo(this);
loadFrame.setLayout(new BorderLayout());
loadFrame.add(new JLabel("Contact loaded from contacts.txt"),BorderLayout.NORTH);
loadFrame.add(new JScrollPane(loadJTable),BorderLayout.CENTER);

JButton closeButton = new JButton("Close");
closeButton.addActionListener(e -> loadFrame.dispose());
JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
buttonPanel.add(closeButton);
loadFrame.add(buttonPanel, BorderLayout.SOUTH);

loadFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
loadFrame.setVisible(true);
            }
    private JPanel buildInputPanel(){
        JPanel panel =new JPanel(new GridLayout(2, 2, 8, 8));
        panel.setBackground(new Color(230,240,245));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("New Contact"),
                BorderFactory.createEmptyBorder(10,10,10,10)));
        
        txtName.setToolTipText("Enter the contact's full name");
        txtPhone.setToolTipText("Enter the contact's phone number");
        
        panel.add(lblCount);
        panel.add(txtName);
        panel.add(lblPhone);
        panel.add(txtPhone);
        return panel;
    }
     private JPanel BuildDisplayPanel(){
        JPanel dPanel = new JPanel();
        dPanel.setBackground(new Color(230, 240, 245));
       dPanel.setBorder(BorderFactory.createCompoundBorder(
               BorderFactory.createTitledBorder("Contact Details"),
               BorderFactory.createEmptyBorder(5, 5, 5, 5)));

   dPanel.add(scrollPane,BorderLayout.CENTER);
   return dPanel;
   
    }
     private void selectContacts(){
      int row = tblContacts.getSelectedRow();
      if(row != -1){
          txtName.setText(contactList.get(row)[0]);
          txtPhone.setText(contactList.get(row)[1]);
      }
     }
     private void updateContact(){
         int row = tblContacts.getSelectedRow();    
         if(row == -1){
             JOptionPane.showMessageDialog(this, "Please select a contact to update!","No Selection",JOptionPane.WARNING_MESSAGE);
             return;
         }
         
         String newName =txtName.getText().trim();
         String newPhone =txtPhone.getText().trim();
         
         if(newName.isEmpty()||newPhone.isEmpty()){
            JOptionPane.showMessageDialog(this, "Name and Phone cannot be empty!","Vailation Error",JOptionPane.ERROR_MESSAGE);
            return;
         }
         
         // check for phone number only
         if(!newPhone.matches("\\d+")){
             JOptionPane.showMessageDialog(this, "Phone must be numbers only!","Validation Error",JOptionPane.ERROR_MESSAGE);
             return;
         }
         
         // check for duplicate
         for(int i = 0;i<contactList.size();i++){
             if(i!=row && contactList.get(i)[0].equalsIgnoreCase(newName)){
                 JOptionPane.showMessageDialog(this, "Contact with this name already exists!","Duplicte",JOptionPane.ERROR_MESSAGE);
                 return;
             }
         }
         
         contactList.set(row, new String[]{newName,newPhone});
         saveToFile();
         displayContacts();
         clearFields();
         JOptionPane.showMessageDialog(this, "Contact Updated Successfully!");
     }
     private void deleteContact(){
         int row = tblContacts.getSelectedRow();    
         if(row == -1){
             JOptionPane.showMessageDialog(this, "Please select a contact to delete!","No Selection",JOptionPane.WARNING_MESSAGE);
             return;
         }
         
         String nameToDelete = contactList.get(row)[0];
         int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete" + nameToDelete + "?",
                 "Confirm delete", JOptionPane.YES_NO_OPTION,
                 JOptionPane.QUESTION_MESSAGE);
         
         if(confirm == JOptionPane.YES_OPTION){
             contactList.remove(row);
             saveToFile();
             displayContacts();
             clearFields();
             JOptionPane.showMessageDialog(this, "Contact Delete!");
     }
    }

    private JPanel buildButtonPanel(){
        
     JPanel panel = new JPanel(new BorderLayout(10, 5));
    panel.setBackground(new Color(230, 240, 245));
    panel.setBorder(
        BorderFactory.createEmptyBorder(5, 10, 5, 10)
    );

    // Left side: contact count
    lblCount.setText("Contacts: " + contactList.size());
    panel.add(lblCount, BorderLayout.WEST);

    // Right side: buttons
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.setBackground(new Color(230, 240, 245));

    buttonPanel.add(btnSave);
    buttonPanel.add(btnUpdate);
    buttonPanel.add(btnDelete);
    buttonPanel.add(btnExit);

    panel.add(buttonPanel, BorderLayout.CENTER);

    btnSave.setToolTipText("Add this contact to your list");

    btnSave.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            name = txtName.getText().trim();
            phone = txtPhone.getText().trim();
            if (!name.isEmpty() && !phone.isEmpty()) {
                contactList.add(new String[]{name,phone});
                saveToFile();
               displayContacts();


                    }
                    txtName.setText("");
                    txtPhone.setText("");

                    JOptionPane.showMessageDialog(
                        ProductApp.this, "Contact Saved!"
                    );
    }
    
    });
    
    
    return panel;
    }
private void saveToFile(){
    try(BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)){
        for(String[] contact : contactList){
            writer.write(contact[0] +"\t" + contact[1]);
            writer.newLine();
        }
    }catch(IOException e)
    {
        JOptionPane.showMessageDialog(
                       this, "Error saving contacts: "+e.getMessage()
                    );
    }
}
private void loadFromFile(){
    if(!Files.exists(filePath))
    {
        return;
    }
    try(BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)){
        String line;
        while((line = reader.readLine())!= null){
            String[] data = line.split("\t", -1);
            if(data.length == 2){
                contactList.add(new String[]{data[0],data[1]});
            }
        }
    }catch (IOException e ){
         JOptionPane.showMessageDialog(
                       this, "Error reading contacts: "+e.getMessage()
                    );
    }       
}
private void displayContacts(){
   tblModel.setRowCount(0);
    for(String[] contact : contactList){
     tblModel.addRow(new Object[]{contact[0],contact[1]});
    }
    lblCount.setText("Contacts: " + contactList.size());
}

private void showReportWindow(){
   int totalContacts = contactList.size();
   String reportText = "CONTACT BOOK REPORT\n\n"
         + "Total contacts: " + totalContacts + "\n"
         + "Data files: " + filePath.toAbsolutePath() + "\n";
          
          JLabel reportLabel = new JLabel(
          "<html><div style='padding:12px;'>"
          + "<h2>Contact Book Report</h2>"
          + "<p><b>Total contact:</b> " + totalContacts + "</p>"
          + "<p><b>Data files:</b> contacts.txt</p>"
          + "</div></html>");        
           
    JFrame reportFrame = new JFrame("Contact Book Report");
    reportFrame.setSize(380,220);
    reportFrame.setLocationRelativeTo(this);
    reportFrame.setLayout(new BorderLayout());
    reportFrame.add(reportLabel,BorderLayout.CENTER);
    
    JButton closeButton = new JButton("Close");
    closeButton.addActionListener(e -> reportFrame.dispose());
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    buttonPanel.add(closeButton);
    reportFrame.add(buttonPanel,BorderLayout.SOUTH);
    
    reportFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    reportFrame.setVisible(true);
    }
private static void showWelcomeWindow(){
    JFrame welcomeFrame = new JFrame("Welcome to contact Book");
    welcomeFrame.setSize(430, 250);
    welcomeFrame.setLayout(new BorderLayout(10,10));
    welcomeFrame.setLocationRelativeTo(null);
    welcomeFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    JLabel titleLabel = new JLabel(
    "<html><div style='text-align:center;'>"
    + "<h1>Welcome to Contact Book</h1>"
       + "<p>Manage your contacts in one place</p>"
       + "</div></html",SwingConstants.CENTER);
    
    JLabel statusLabel = new JLabel("Ready to get started?",SwingConstants.CENTER);
    JButton startButton = new JButton("Open Contact Book");
    JButton exitButton = new JButton("Exit");
    
    JPanel centerPanel = new  JPanel(new BorderLayout());
    centerPanel.add(titleLabel, BorderLayout.CENTER);
    centerPanel.add(statusLabel, BorderLayout.SOUTH);
    
    JPanel buttonJPanel = new JPanel(
    new FlowLayout(FlowLayout.CENTER));
    
    buttonJPanel.add(startButton);
    buttonJPanel.add(exitButton);
    
    welcomeFrame.add(centerPanel, BorderLayout.CENTER);
    welcomeFrame.add(buttonJPanel, BorderLayout.SOUTH);
    
    startButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            welcomeFrame.dispose();
            new BookApps();
        }
    });
    exitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            welcomeFrame.dispose();
        }
    });
    welcomeFrame.setVisible(true);
}
   public static void main(String[] args) 
   { 
             SwingUtilities.invokeLater(BookApps::showWelcomeWindow);
   }
}

