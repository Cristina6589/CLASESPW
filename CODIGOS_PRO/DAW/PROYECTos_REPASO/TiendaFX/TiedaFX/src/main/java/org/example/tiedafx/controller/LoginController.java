package org.example.tiedafx.controller;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.tiedafx.dao.UserDAO;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField editMail, editPass;

    @FXML
    private Button btnLogin;
    private UserDAO userDAO;
    private Task<int[]> taskLogin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        instances();
        actions();
    }

    private void instances() {
        userDAO = new UserDAO();

    }

    private void actions() {
        btnLogin.setOnAction(event -> {
            String correo = editMail.getText();
            String pass = editPass.getText();

            // con task
            taskLogin = new Task<int[]>() {
                @Override
                protected int[] call() throws Exception {
                    return userDAO.getLogin(correo,pass);
                }
            };

            new Thread(taskLogin).start();
            taskLogin.setOnSucceeded(data->{
                taskLogin.get();
            });

            // comprobacion
            int[] reponse = userDAO.getLogin(correo, pass);
            if (reponse == null) {
                System.out.println("No esta el usuario");
            } else {
                switch (reponse[1]) {
                    case 1 -> {
                    }
                    case 2,3 -> {
                        if(reponse[1]==2){

                        } else if(reponse[1]==3){

                        }
                    }

                }
            }
        });
    }
}
