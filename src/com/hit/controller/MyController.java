package com.hit.controller;

import com.hit.client.Restaurant;
import com.hit.model.MyModel;
import com.hit.view.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyController implements ActionListener {

    View view;
    MyModel model;
    List<String> input ;

    public MyController(View view, MyModel model){
        this.view = view;
        this.model = model;

        this.view.searchByNameButton.addActionListener(this);
        this.view.searchByCategoryButton.addActionListener(this);
        this.view.addUpdateRestButton.addActionListener(this);
        this.view.deleteRestButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFrame frame = this.view;

        if(e.getSource() == view.addUpdateRestButton) {

            input = new ArrayList <>();
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

            if (legalStrings(input)) {

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
                        + view.restaurantRatingAdmin.getText().substring(1).toLowerCase();;
                input.add(category);
                input.add(name);
                input.add(address);
                input.add(city);
                input.add(phone);
                input.add(rating);

                String response = null;
                try {
                    response = model.addUpdateRest(input);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                if( response.equals("OK"))
                {
                    JOptionPane.showMessageDialog( frame, name + " Restaurant was added successfully");

                }
                else
                {
                    JOptionPane.showMessageDialog( frame, "We having some issues with the server please try again");

                }
                System.out.println(input);
                clearAdminRestaurantDetails();
            }
            else
            {
                clearAdminRestaurantDetails();
                JOptionPane.showMessageDialog( frame, "One of the fields cannot be empty.");
            }

        }

        if(e.getSource() == view.deleteRestButton)
        {
            String restNameDelete = view.restaurantNameToDeleteAdmin.getText();
            if(legalString(restNameDelete))
            {

                restNameDelete = view.restaurantNameToDeleteAdmin.getText().substring(0,1).toUpperCase()
                        +  view.restaurantNameToDeleteAdmin.getText().substring(1).toLowerCase();


                String response = null;
                try {
                    response = model.deleteRest(restNameDelete);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                if( response.equals("OK"))
                {
                    JOptionPane.showMessageDialog( frame, restNameDelete + " Restaurant was deleted successfully");

                }
                else
                {
                    JOptionPane.showMessageDialog( frame, "There's no such restaurant: " + restNameDelete);

                }
                System.out.println(input);
                clearAdminRestaurantDetails();
            }
            else
            {
                clearAdminRestaurantDetails();
                JOptionPane.showMessageDialog( frame, "One of the fields cannot be empty.");
            }

        }


        if(e.getSource() == view.searchByNameButton)
        {
            String searchRestName = view.restaurantNameUser.getText();

            if(legalString(searchRestName)){

                searchRestName = view.restaurantNameUser.getText().substring(0, 1).toUpperCase()
                        + view.restaurantNameUser.getText().substring(1).toLowerCase();

                List <Restaurant> rests = null;
                try {
                    rests = model.getByName(searchRestName);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                view.dtm[0] = new DefaultTableModel(null, view.columns);

                if (rests.size() == 0) {
                    view.restaurantsTable = new JTable(view.dtm[0]);
                    view.restaurantsTable.getTableHeader().setReorderingAllowed(false);
                    view.restaurantsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    view.userPanel.remove(view.restPanel[0]);
                    view.restPanel[0] = new JScrollPane(view.restaurantsTable);
                    view.restPanel[0].setBounds(400, 20, 450, 200);
                    view.userPanel.add(view.restPanel[0]);
                    view.restaurantNameUser.setText("");
                    JOptionPane.showMessageDialog(frame, "Restaurant " + searchRestName + " doesn't exist");
                } else {

                    for (Restaurant r : rests) {
                        view.dtm[0].addRow(new Object[]{r.getCategory(), r.getName(),
                                r.getAddress(), r.getCity(), r.getPhoneNumber(), r.getRating()});
                    }
                    view.restaurantsTable = new JTable(view.dtm[0]);
                    view.restaurantsTable.getTableHeader().setReorderingAllowed(false);
                    view.restaurantsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    view.userPanel.remove(view.restPanel[0]);
                    view.restPanel[0] = new JScrollPane(view.restaurantsTable);
                    view.restPanel[0].setBounds(400, 20, 450, 200);
                    view.userPanel.add(view.restPanel[0]);
                    view.restaurantNameUser.setText("");
                }
            }
            else {
                clearUserRestaurantDetails();
                JOptionPane.showMessageDialog(frame, "One of the fields cannot be empty.");
            }
        }

        if(e.getSource() == view.searchByCategoryButton) {

            String searchRestCategory = view.restaurantCategoryUser.getText();

            if (legalString(searchRestCategory)) {

                searchRestCategory = view.restaurantCategoryUser.getText().substring(0, 1).toUpperCase()
                        + view.restaurantCategoryUser.getText().substring(1).toLowerCase();
                List <Restaurant> rests = null;

                try {
                    rests = model.getByCategory(searchRestCategory);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                view.dtm[0] = new DefaultTableModel(null, view.columns);

                if (rests == null) {
                    view.restaurantsTable = new JTable(view.dtm[0]);
                    view.restaurantsTable.getTableHeader().setReorderingAllowed(false);
                    view.restaurantsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    view.userPanel.remove(view.restPanel[0]);
                    view.restPanel[0] = new JScrollPane(view.restaurantsTable);
                    view.restPanel[0].setBounds(400, 20, 450, 200);
                    view.userPanel.add(view.restPanel[0]);
                    view.restaurantCategoryUser.setText("");
                    JOptionPane.showMessageDialog(frame, "Category " + searchRestCategory + " doesn't exist");
                } else {

                    for (Restaurant r : rests) {
                        view.dtm[0].addRow(new Object[]{r.getCategory(), r.getName(),
                                r.getAddress(), r.getCity(), r.getPhoneNumber(), r.getRating()});
                    }
                    view.restaurantsTable = new JTable(view.dtm[0]);
                    view.restaurantsTable.getTableHeader().setReorderingAllowed(false);
                    view.restaurantsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    view.userPanel.remove(view.restPanel[0]);
                    view.restPanel[0] = new JScrollPane(view.restaurantsTable);
                    view.restPanel[0].setBounds(400, 20, 450, 200);
                    view.userPanel.add(view.restPanel[0]);
                    view.restaurantCategoryUser.setText("");
                }
            }
            else {

                clearUserRestaurantDetails();
                JOptionPane.showMessageDialog(frame, "One of the fields cannot be empty.");
            }
        }
    }


    public void clearAdminRestaurantDetails()
    {
        view.restaurantCategoryAdmin.setText("");
        view.restaurantNameAdmin.setText("");
        view.restaurantAddressAdmin.setText("");
        view.restaurantCityAdmin.setText("");
        view.restaurantPhoneAdmin.setText("");
        view.restaurantRatingAdmin.setText("");
        view.restaurantNameToDeleteAdmin.setText("");
    }

    public  void clearUserRestaurantDetails()
    {
        view.restaurantNameUser.setText("");
        view.restaurantCategoryUser.setText("");

    }

    public boolean legalStrings(List<String> details)
    {
        for(String s: details)
        {
            if (s.equals("") || s.equals(null))
            {
                return false;
            }
        }

        return true;
    }


    public boolean legalString(String details)
    {
        if (details.equals("") || details.equals(null))
        {
            return false;
        }

        return true;
    }
}
