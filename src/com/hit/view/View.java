package com.hit.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;

public class View extends JFrame  {

    // Windows.
    public JPanel homeScreenPanel = new JPanel();
    public JPanel adminPanel = new JPanel();
    public JPanel userPanel = new JPanel();
    public JPanel restaurantAdminPanel = new JPanel();
    public JPanel adminAuthorization = new JPanel();
    public JPanel restaurantClientPanel = new JPanel();


    // Main Buttons.
    public JButton adminButton = new JButton("Enter as Admin");
    public JButton userButton = new JButton("Enter as User");
    public JButton exitFromAdminAuthorizationButton = new JButton("Go Back");
    public JButton exitFromAdminButton = new JButton("Logout");
    public JButton exitFromUserButton = new JButton("Go Back");  


    //  Admin Panels
    public JTextArea loginText = new JTextArea("Please enter your admin password");
    public JTextArea addUpdateRestText = new JTextArea("Add or Update a restaurant");
    public JTextArea deleteRestText = new JTextArea("Delete restaurant");

    public JTextArea categoryText = new JTextArea("Restaurant category:");
    public JTextArea nameText = new JTextArea("Restaurant name:");
    public JTextArea restaurantToDeleteText = new JTextArea("Restaurant name\n        to delete");
    public JTextArea addressText = new JTextArea("Restaurant address:");
    public JTextArea cityText = new JTextArea("Restaurant city:");
    public JTextArea phoneNumberText = new JTextArea("Restaurant phone number:");
    public JTextArea ratingText = new JTextArea("Restaurant rating:");
    public JButton addUpdateRestButton = new JButton("Add/Update");
    public JButton deleteRestButton = new JButton("Delete Restaurant");
    public JButton submitPasswordButton = new JButton("Login");
    public JPasswordField adminPassword = new JPasswordField();
    public JTextField restaurantCategoryAdmin = new JTextField();
    public JTextField restaurantNameToDeleteAdmin= new JTextField();
    public JTextField restaurantNameAdmin= new JTextField();
    public JTextField restaurantAddressAdmin= new JTextField();
    public JTextField restaurantCityAdmin= new JTextField();
    public JTextField restaurantPhoneAdmin = new JTextField();
    public JTextField restaurantRatingAdmin= new JTextField();


    //  User panel.
    public JTextArea findRestByCategoryText = new JTextArea("Find restaurants by category");
    public JTextArea findRestByNameText = new JTextArea("Find restaurants by name");
    public JTextField restaurantNameUser = new JTextField();
    public JTextField restaurantCategoryUser = new JTextField();
    public JButton searchAllButton = new JButton("Search All");
    public JButton searchByNameButton = new JButton("Search by name");
    public JButton searchByCategoryButton = new JButton("Search by category");

    public String[] columns = {"Category", "Name", "Address", "City", "Phone", "Rating"};
    public final DefaultTableModel[] dtm = {new DefaultTableModel(null, columns)};
    public JTable restaurantsTable = new JTable(dtm[0]);
    public final JScrollPane[] restPanel;


    // Background Images
    ImagePanel HomeScreenBack = new ImagePanel(
            new ImageIcon("src/com/hit/view/HomePage.jpg").getImage());
    ImagePanel AdminAuthorizationBack = new ImagePanel(
            new ImageIcon("src/com/hit/view/AdminPage.jpg").getImage());
    ImagePanel AdminBack = new ImagePanel(
            new ImageIcon("src/com/hit/view/AdminPage.jpg").getImage());
    ImagePanel UserBack = new ImagePanel(
            new ImageIcon("src/com/hit/view/UserPage.jpg").getImage());



    public View() {

         //------------------------------------Main Window-----------------------------------------//
        //---------------------------------------------------------------------------------------//
        setResizable(false); // else all the styling goes away..
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900,600); // app size.
        this.getContentPane().setLayout(new CardLayout(0,0));
        this.setTitle("Find my restaurant");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dimension.width/2 - 500, dimension.height/2 - 250); //Location on screen



        this.getContentPane().add(homeScreenPanel);
        homeScreenPanel.setLayout(null);

        this.getContentPane().add(adminPanel);
        adminPanel.setLayout(null);

        this.getContentPane().add(userPanel);
        userPanel.setLayout(null);

        this.getContentPane().add(restaurantAdminPanel);
        restaurantAdminPanel.setLayout(null);

        this.getContentPane().add(adminAuthorization);
        adminAuthorization.setLayout(null);

        this.getContentPane().add(restaurantClientPanel);
        restaurantClientPanel.setLayout(null);

        Color mustardColor = new Color(175, 110, 31);
        Border border = BorderFactory.createLineBorder(Color.black);


        userButton.addActionListener(e -> {
            homeScreenPanel.setVisible(false);
            userPanel.setVisible(true);
        });
        userButton.setFont(new Font("Default", Font.ITALIC, 20 ));
        userButton.setBounds(0, 200, 165, 60);
        userButton.setBackground(mustardColor);
        userButton.setBorder(border);
        userButton.setFocusPainted(false);

        adminButton.addActionListener(e -> {
            homeScreenPanel.setVisible(false);
            adminAuthorization.setVisible(true);
        });
        adminButton.setFont(new Font("Default", Font.ITALIC, 20 ));
        adminButton.setBackground(mustardColor);
        adminButton.setBounds(0, 290, 165, 60);
        adminButton.setBorder(border);
        adminButton.setFocusPainted(false);


        homeScreenPanel.add(userButton);
        homeScreenPanel.add(adminButton);
        homeScreenPanel.add(HomeScreenBack);



        //------------------------------------Admin  Authorization side---------------------------------------------//
        // ------------------------------------------------------------------------------------------//


        Color darkGreyColor = new Color(102, 102, 102);
        Color greyColor = new Color(173, 173, 173);
        adminPassword.setBounds(100,110,250,25);
        adminPassword.setBorder(border);

        loginText.setFont(new Font("Default", Font.ITALIC, 20 ));
        loginText.setForeground(darkGreyColor);
        loginText.setBounds(70, 60, 350, 50);
        loginText.setOpaque(false);
        loginText.setEditable(false);


        JFrame frame = this;
        submitPasswordButton.addActionListener(e -> {
          if(isPasswordCorrect(adminPassword.getPassword()))
          {
              adminAuthorization.setVisible(false);
              adminPanel.setVisible(true);
          }
          else
          {
              JOptionPane.showMessageDialog( frame, "It seems you're not the admin...");
          }
          adminPassword.setText("");

        });
        submitPasswordButton.setBounds(155,160,150,30);
        submitPasswordButton.setBackground(greyColor);
        submitPasswordButton.setBorder(border);
        submitPasswordButton.setFocusPainted(false);


        exitFromAdminAuthorizationButton.addActionListener(e->{

            adminPassword.setText("");
            adminAuthorization.setVisible(false);
            homeScreenPanel.setVisible(true);
        });

        exitFromAdminAuthorizationButton.setBounds(834,0,50,20);
        exitFromAdminAuthorizationButton.setBackground(greyColor);
        exitFromAdminAuthorizationButton.setBorder(border);

        adminAuthorization.add(loginText);
        adminAuthorization.add(adminPassword);
        adminAuthorization.add(submitPasswordButton);
        adminAuthorization.add(exitFromAdminAuthorizationButton);
        adminAuthorization.add(AdminAuthorizationBack);

    //------------------------------------Admin side---------------------------------------------//
    // ------------------------------------------------------------------------------------------//

        Color blackColor = new Color(0, 0, 0);

        exitFromAdminButton.setBounds(834,0,50,20);
        exitFromAdminButton.setBackground(greyColor);
        exitFromAdminButton.setBorder(border);

        exitFromAdminButton.addActionListener(e->{

            restaurantCategoryAdmin.setText("");
            restaurantNameAdmin.setText("");
            restaurantAddressAdmin.setText("");
            restaurantCityAdmin.setText("");
            restaurantPhoneAdmin.setText("");
            restaurantRatingAdmin.setText("");
            restaurantNameToDeleteAdmin.setText("");
            adminPanel.setVisible(false);
            homeScreenPanel.setVisible(true);
        });



        addUpdateRestText.setFont(new Font("Default", Font.ITALIC, 25 ));
        addUpdateRestText.setForeground(darkGreyColor);
        addUpdateRestText.setBounds(50, 20, 400, 50);
        addUpdateRestText.setOpaque(false);
        addUpdateRestText.setEditable(false);

        deleteRestText.setFont(new Font("Default", Font.ITALIC, 25 ));
        deleteRestText.setForeground(darkGreyColor);
        deleteRestText.setBounds(590, 20, 250, 50);
        deleteRestText.setOpaque(false);
        deleteRestText.setEditable(false);




        categoryText.setFont(new Font("Default", Font.ITALIC, 15 ));
        categoryText.setForeground(blackColor);
        categoryText.setBounds(2, 90, 150, 30);
        categoryText.setOpaque(false);
        categoryText.setEditable(false);

        restaurantCategoryAdmin.setBounds(182,90,150,25);
        restaurantCategoryAdmin.setBorder(border);


        nameText.setFont(new Font("Default", Font.ITALIC, 15 ));
        nameText.setForeground(blackColor);
        nameText.setBounds(2, 140, 150, 30);
        nameText.setOpaque(false);
        nameText.setEditable(false);

        restaurantNameAdmin.setBounds(182,140,150,25);
        restaurantNameAdmin.setBorder(border);

        addressText.setFont(new Font("Default", Font.ITALIC, 15 ));
        addressText.setForeground(blackColor);
        addressText.setBounds(2, 190, 150, 30);
        addressText.setOpaque(false);
        addressText.setEditable(false);


        restaurantAddressAdmin.setBounds(182,190,150,25);
        restaurantAddressAdmin.setBorder(border);


        cityText.setFont(new Font("Default", Font.ITALIC, 15 ));
        cityText.setForeground(blackColor);
        cityText.setBounds(2, 240, 150, 30);
        cityText.setOpaque(false);
        cityText.setEditable(false);

        restaurantCityAdmin.setBounds(182,240,150,25);
        restaurantCityAdmin.setBorder(border);

        phoneNumberText.setFont(new Font("Default", Font.ITALIC, 15 ));
        phoneNumberText.setForeground(blackColor);
        phoneNumberText.setBounds(2, 290, 180, 30);
        phoneNumberText.setOpaque(false);
        phoneNumberText.setEditable(false);


        restaurantPhoneAdmin.setBounds(182,290,150,25);
        restaurantPhoneAdmin.setBorder(border);


        ratingText.setFont(new Font("Default", Font.ITALIC, 15 ));
        ratingText.setForeground(blackColor);
        ratingText.setBounds(2, 340, 150, 30);
        ratingText.setOpaque(false);
        ratingText.setEditable(false);


        restaurantRatingAdmin.setBounds(182,340,150,25);
        restaurantRatingAdmin.setBorder(border);


        addUpdateRestButton.setBounds(82,410,200,50);
        addUpdateRestButton.setBackground(greyColor);
        addUpdateRestButton.setBorder(border);
        addUpdateRestButton.setFocusPainted(false);


        restaurantToDeleteText.setFont(new Font("Default", Font.ITALIC, 15 ));
        restaurantToDeleteText.setForeground(blackColor);
        restaurantToDeleteText.setBounds(630, 60, 250, 50);
        restaurantToDeleteText.setOpaque(false);
        restaurantToDeleteText.setEditable(false);


        restaurantNameToDeleteAdmin.setBounds(600,110,180,25);
        restaurantNameToDeleteAdmin.setBorder(border);

        deleteRestButton.setBounds(590,155,200,50);
        deleteRestButton.setBackground(greyColor);
        deleteRestButton.setBorder(border);
        deleteRestButton.setFocusPainted(false);


        adminPanel.add(addUpdateRestButton);
        adminPanel.add(deleteRestButton);
        adminPanel.add(addUpdateRestText);
        adminPanel.add(deleteRestText);
        adminPanel.add(exitFromAdminButton);
        adminPanel.add(categoryText);
        adminPanel.add(restaurantCategoryAdmin);
        adminPanel.add(nameText);
        adminPanel.add(restaurantNameAdmin);
        adminPanel.add(addressText);
        adminPanel.add(restaurantAddressAdmin);
        adminPanel.add(cityText);
        adminPanel.add(restaurantCityAdmin);
        adminPanel.add(phoneNumberText);
        adminPanel.add(restaurantPhoneAdmin);
        adminPanel.add(ratingText);
        adminPanel.add(restaurantRatingAdmin);
        adminPanel.add(restaurantToDeleteText);
        adminPanel.add(restaurantNameToDeleteAdmin);
        adminPanel.add(AdminBack);


    //------------------------------------User Side-----------------------------------------//
    //---------------------------------------------------------------------------------------//



        Color sheeshColor = new Color(212, 175, 55);
        findRestByNameText.setFont(new Font("Default", Font.ITALIC, 25 ));
        findRestByNameText.setForeground(blackColor);
        findRestByNameText.setBounds(65, 20, 400, 50);
        findRestByNameText.setOpaque(false);
        findRestByNameText.setEditable(false);


        restaurantNameUser.setBounds(110,70,200,25);
        restaurantNameUser.setBorder(border);

        searchByNameButton.setBounds(110,125,200,50);
        searchByNameButton.setBackground(sheeshColor);
        searchByNameButton.setBorder(border);
        searchByNameButton.setFocusPainted(false);


        findRestByCategoryText.setFont(new Font("Default", Font.ITALIC, 25 ));
        findRestByCategoryText.setForeground(blackColor);
        findRestByCategoryText.setBounds(500, 20, 400, 50);
        findRestByCategoryText.setOpaque(false);
        findRestByCategoryText.setEditable(false);

        restaurantCategoryUser.setBounds(565,70,200,25);
        restaurantCategoryUser.setBorder(border);


        searchByCategoryButton.setBounds(565,125,200,50);
        searchByCategoryButton.setBackground(sheeshColor);
        searchByCategoryButton.setBorder(border);
        searchByCategoryButton.setFocusPainted(false);


        searchAllButton.setBounds(810,525,65,25);
        searchAllButton.setBackground(sheeshColor);
        searchAllButton.setBorder(border);
        searchAllButton.setFocusPainted(false);


        restaurantsTable.getTableHeader().setReorderingAllowed(false);
        restaurantsTable.setAutoCreateColumnsFromModel(false);
        restaurantsTable.getTableHeader().setResizingAllowed(false);
        restPanel = new JScrollPane[]{new JScrollPane(restaurantsTable)};


        exitFromUserButton.setBounds(834,0,50,20);
        exitFromUserButton.setBackground(sheeshColor);
        exitFromUserButton.setBorder(border);

        exitFromUserButton.addActionListener(e->{
            restaurantCategoryUser.setText("");
            restaurantNameUser.setText("");
            userPanel.setVisible(false);
            homeScreenPanel.setVisible(true);
        });

        userPanel.add(exitFromUserButton);
        userPanel.add(findRestByNameText);
        userPanel.add(restaurantNameUser);
        userPanel.add(searchByNameButton);
        userPanel.add(findRestByCategoryText);
        userPanel.add(restaurantCategoryUser);
        userPanel.add(searchByCategoryButton);
        userPanel.add(searchAllButton);
        userPanel.add(UserBack);
        this.setVisible(true);

    }

    private static boolean isPasswordCorrect(char[] input) {
        boolean isCorrect;
        char[] correctPassword = { '1', '2', '3', '4', '5' };

        if (input.length != correctPassword.length) {
            isCorrect = false;
        } else {
            isCorrect = Arrays.equals (input, correctPassword);
        }

        //Zero out the password.
        Arrays.fill(correctPassword,'0');

        return isCorrect;
    }

}
