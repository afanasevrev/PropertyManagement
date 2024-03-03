package com.example.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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

}
