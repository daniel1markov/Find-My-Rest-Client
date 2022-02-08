package com.hit.driver;

import com.hit.controller.MyController;
import com.hit.model.MyModel;
import com.hit.view.View;

public class MVCDriver {

    public static void main(String [] args)
    {
        View view = new View();
        MyModel myModel = new MyModel(12345);

        MyController myController = new MyController(view, myModel);



    }
}
