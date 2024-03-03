package com.example.client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class SignController {
    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button buttonOk;

    @FXML
    private void setButtonOk() throws IOException {
        String isAuthorized = getAuthorized(loginField.getText(), passwordField.getText());
        if(isAuthorized.equals("AUTHORIZED")) {
            AdminsPage adminsPage = new AdminsPage();
            adminsPage.start(new Stage());
            Stage stage = (Stage) buttonOk.getScene().getWindow();
            stage.close();
        } else {
            System.out.println("Вы не авторизованы");
        }
    }

    /**
     * Отправка запроса к серверу на авторизацию
     * @param login
     * @param password
     * @return
     */
    private String getAuthorized(String login, String password) {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/authorized/" + login + "&" + password);
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            return in.readLine();
        } catch (IOException e) {
            return "Server is not available";
        }
    }
}