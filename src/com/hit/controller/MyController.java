package com.hit.controller;

import com.hit.model.Restaurant;
import com.hit.model.MyModel;
import com.hit.view.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MyController implements ActionListener {

    View view;
    MyModel model;


    public MyController(View view, MyModel model){
        this.view = view;
        this.model = model;

        this.view.searchByNameButton.addActionListener(this);
        this.view.searchByCategoryButton.addActionListener(this);
        this.view.addUpdateRestButton.addActionListener(this);
        this.view.deleteRestButton.addActionListener(this);
        this.view.searchAllButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFrame frame = this.view;

        if(e.getSource() == view.addUpdateRestButton) {

            List<String> input  = new ArrayList <>();
            String category = view.restaurantCategoryAdmin.getText();
            String name = view.restaurantNameAdmin.getText();
            String address = view.restaurantAddressAdmin.getText();
            String city = view.restaurantCityAdmin.getText();
            String phone = view.restaurantPhoneAdmin.getText();
            String rating = view.restaurantRatingAdmin.getText();
            input.add(category);
            input.add(name);
            input.add(address);
            input.add(city);
            input.add(phone);
            input.add(rating);

            if (legalStrings(input) && category.length() > 2) {

                input = new ArrayList <>();
                category = view.restaurantCategoryAdmin.getText().substring(0,1).toUpperCase()
                        + view.restaurantCategoryAdmin.getText().substring(1).toLowerCase();
                name = view.restaurantNameAdmin.getText().substring(0,1).toUpperCase()
                        +view.restaurantNameAdmin.getText().substring(1).toLowerCase();
                address = view.restaurantAddressAdmin.getText().substring(0,1).toUpperCase()
                        + view.restaurantAddressAdmin.getText().substring(1).toLowerCase();
                city = view.restaurantCityAdmin.getText().substring(0,1).toUpperCase()
                        + view.restaurantCityAdmin.getText().substring(1).toLowerCase();
                phone = view.restaurantPhoneAdmin.getText().substring(0,1).toUpperCase()
                        + view.restaurantPhoneAdmin.getText().substring(1).toLowerCase();
                rating = view.restaurantRatingAdmin.getText().substring(0,1).toUpperCase()
                        + view.restaurantRatingAdmin.getText().substring(1).toLowerCase();
                input.add(category);
                input.add(name);
                input.add(address);
                input.add(city);
                input.add(phone);
                input.add(rating);

                String response = model.addUpdateRest(input);
                if( response.equals("OK"))
                {
                    JOptionPane.showMessageDialog( frame, name + " Restaurant was added successfully");
                }
                else
                {
                    JOptionPane.showMessageDialog( frame, "We having some issues with the server please try again");
                }
                clearAdminRestaurantDetails();
            }
            else
            {
                clearAdminRestaurantDetails();
                if(category.length() < 3)
                {
                    JOptionPane.showMessageDialog( frame, "Category cannot be smaller than 3 letters.");

                }
                else
                {
                    JOptionPane.showMessageDialog(frame, "Fields cannot be empty or having invalid input.");
                }
            }
        }

        if(e.getSource() == view.deleteRestButton)
        {
            String restNameDelete = view.restaurantNameToDeleteAdmin.getText();
            if(legalString(restNameDelete))
            {

                restNameDelete = view.restaurantNameToDeleteAdmin.getText().substring(0,1).toUpperCase()
                        +  view.restaurantNameToDeleteAdmin.getText().substring(1).toLowerCase();
                String response = model.deleteRest(restNameDelete);

                if( response.equals("OK"))
                {
                    JOptionPane.showMessageDialog( frame, restNameDelete + " Restaurant was deleted successfully");

                }
                else
                {
                    JOptionPane.showMessageDialog( frame, "There's no such restaurant: " + restNameDelete);

                }
                clearAdminRestaurantDetails();
            }
            else
            {
                clearAdminRestaurantDetails();
                JOptionPane.showMessageDialog( frame, "Fields cannot be empty or having invalid input.");
            }
        }

        if(e.getSource() == view.searchAllButton)
        {
            List <Restaurant> rests;
            rests = model.getAll();

            if (rests == null)
            {
                clearTable();
                clearUserRestaurantDetails();
                JOptionPane.showMessageDialog(frame, "We have some error with server, please try again");
            }
            else
            {
                setNewTable(rests);
                clearUserRestaurantDetails();
            }
        }

        if(e.getSource() == view.searchByNameButton)
        {
            String searchRestName = view.restaurantNameUser.getText();

            if(legalString(searchRestName)){

                searchRestName = view.restaurantNameUser.getText().substring(0, 1).toUpperCase()
                        + view.restaurantNameUser.getText().substring(1).toLowerCase();
                List <Restaurant> rests ;
                rests = model.getByName(searchRestName);

                if (rests == null)
                {
                    clearUserRestaurantDetails();
                    JOptionPane.showMessageDialog(frame, "Restaurant " + searchRestName + " doesn't exist");
                }
                else
                {
                    setNewTable(rests);
                    clearUserRestaurantDetails();
                }
            }
            else
            {
                clearUserRestaurantDetails();
                JOptionPane.showMessageDialog(frame, "Fields cannot be empty or having invalid input.");
            }
        }

        if(e.getSource() == view.searchByCategoryButton) {
            String searchRestCategory = view.restaurantCategoryUser.getText();

            if (legalString(searchRestCategory) && searchRestCategory.length() > 2) {

                searchRestCategory = view.restaurantCategoryUser.getText().substring(0, 1).toUpperCase()
                        + view.restaurantCategoryUser.getText().substring(1).toLowerCase();
                List <Restaurant> rests;
                rests = model.getByCategory(searchRestCategory);

                if (rests == null) {

                    clearUserRestaurantDetails();
                    JOptionPane.showMessageDialog(frame, "Category " + searchRestCategory + " doesn't exist");
                }
                else
                {
                    setNewTable(rests);
                    clearUserRestaurantDetails();
                }
            }
            else
            {
                clearUserRestaurantDetails();
                JOptionPane.showMessageDialog(frame, "Category cannot be empty, having invalid input or smaller than 3 letters.");
            }
        }
    }


    private void setNewTable(List<Restaurant> restaurants)
    {
        view.userPanel.remove(view.restPanel[0]);
        view.dtm[0] = new DefaultTableModel(null, view.columns);
        for (Restaurant restaurant : restaurants) {
            view.dtm[0].addRow(new Object[]{restaurant.getCategory(), restaurant.getName(),
                    restaurant.getAddress(), restaurant.getCity(), restaurant.getPhoneNumber(), restaurant.getRating()});
        }
        view.restaurantsTable = new JTable(view.dtm[0]);
        view.restaurantsTable.getTableHeader().setReorderingAllowed(false);
        view.restaurantsTable.setAutoCreateColumnsFromModel(false);
        view.restaurantsTable.getTableHeader().setResizingAllowed(false);
        view.restaurantsTable.setEnabled(false);
        view.restaurantsTable.getTableHeader().setBackground(new Color(212, 175, 55));
        view.restPanel[0] = new JScrollPane(view.restaurantsTable);
        view.restPanel[0].setBounds(100, 420, 700, 130);
        view.restPanel[0].getViewport().setBackground(new Color(188,144,133));
        view.userPanel.add(view.restPanel[0]);
    }

    private  void clearTable()
    {
        view.userPanel.remove(view.restPanel[0]);
        view.dtm[0] = new DefaultTableModel(null, view.columns);
        view.restaurantsTable = new JTable(view.dtm[0]);
        view.restaurantsTable.getTableHeader().setReorderingAllowed(false);
        view.restaurantsTable.setAutoCreateColumnsFromModel(false);
        view.restaurantsTable.getTableHeader().setResizingAllowed(false);
        view.restaurantsTable.setEnabled(false);
        view.restaurantsTable.getTableHeader().setBackground(new Color(212, 175, 55));
        view.restPanel[0] = new JScrollPane(view.restaurantsTable);
        view.restPanel[0].setBounds(100, 420, 700, 130);
        view.restPanel[0].getViewport().setBackground(new Color(188,144,133));
        view.userPanel.add(view.restPanel[0]);

    }
    private void clearAdminRestaurantDetails()
    {
        view.restaurantCategoryAdmin.setText("");
        view.restaurantNameAdmin.setText("");
        view.restaurantAddressAdmin.setText("");
        view.restaurantCityAdmin.setText("");
        view.restaurantPhoneAdmin.setText("");
        view.restaurantRatingAdmin.setText("");
        view.restaurantNameToDeleteAdmin.setText("");
    }

    private void clearUserRestaurantDetails()
    {
        view.restaurantNameUser.setText("");
        view.restaurantCategoryUser.setText("");

    }

    private boolean legalStrings(List<String> details)
    {
        for(String s: details)
        {
            if (!legalString(s))
            {
                return false;
            }
        }
        return true;
    }

    private boolean legalString(String details)
    {
        return !details.equals("") && !details.contains(" ") && englishWordsOnly(details);
    }

    private boolean englishWordsOnly(String details)
    {
        return details.matches("[a-zA-Z0-9]*");
    }


}
