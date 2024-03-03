package com.example.client;

import com.google.gson.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class AdminsPageController {
    @FXML
    private Button buttonUpdate;
    @FXML
    private TableView tableView = new TableView();

    private ObservableList<Suggestion> suggestionsData = FXCollections.<Suggestion>observableArrayList();
    @FXML
    private TableColumn<Suggestion, String> name = new TableColumn<Suggestion, String>("Имя");
    @FXML
    private TableColumn<Suggestion, String> email = new TableColumn<Suggestion, String>("Email");
    @FXML
    private TableColumn<Suggestion, String> price = new TableColumn<Suggestion, String>("Цена");
    @FXML
    private void setButtonUpdate() {
        suggestionsData.clear();
        String text = getSuggestions();

        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        try {
            JsonArray jsonArray = jsonParser.parse(text).getAsJsonArray();
            for (JsonElement jsonElement: jsonArray) {
                //Помещаем jsonElement в класс ResumeTemp(временный класс)
                SuggestionTemp suggestionTemp = gson.fromJson(jsonElement, SuggestionTemp.class);
                //SuggestionTemp помещаем в класс Suggestion
                Suggestion resume = new Suggestion(suggestionTemp.name,
                        suggestionTemp.email,
                        suggestionTemp.price
                );

                //Suggestion помещаем в массив suggestionsData, другими словами, в таблицу для отображения данных
                suggestionsData.add(resume);

                tableView.setItems(suggestionsData);
                name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
                email.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
                price.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
            }
        } catch (JsonSyntaxException e){
        }
    }

    /**
     * Отправка запроса к серверу с целью получения сделок
     * @return
     */
    private String getSuggestions() {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/suggestions");
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            return in.readLine();
        } catch (IOException e) {
            return "Server is not available";
        }
    }
}
