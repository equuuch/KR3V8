package com.example.kr3v8;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class KindergartenController {

    // --- Поля для вкладки ДЕТИ ---
    @FXML private TextField searchField; // Поиск
    @FXML private TableView<Child> childTable;
    @FXML private TableColumn<Child, String> cNameCol;
    @FXML private TableColumn<Child, Integer> cAgeCol;
    @FXML private TableColumn<Child, String> cGroupCol;
    @FXML private TableColumn<Child, String> cParentCol;

    @FXML private TextField cNameInput;
    @FXML private TextField cAgeInput;
    @FXML private TextField cGroupInput;
    @FXML private TextField cParentInput;

    // --- Поля для вкладки СОТРУДНИКИ ---
    @FXML private TableView<Staff> staffTable;
    @FXML private TableColumn<Staff, String> sNameCol;
    @FXML private TableColumn<Staff, String> sPosCol;
    @FXML private TableColumn<Staff, String> sPhoneCol;

    @FXML private TextField sNameInput;
    @FXML private TextField sPosInput;
    @FXML private TextField sPhoneInput;

    // --- Данные ---
    private final ObservableList<Child> childrenData = FXCollections.observableArrayList(
            new Child("Маша Иванова", 5, "Ромашка", "Иванов И.И."),
            new Child("Петя Петров", 6, "Солнышко", "Петрова А.А."),
            new Child("Ваня Сидоров", 4, "Гномики", "Сидорова К.К.")
    );

    private final ObservableList<Staff> staffData = FXCollections.observableArrayList(
            new Staff("Сидорова Елена Петровна", "Воспитатель", "8-900-111-22-33"),
            new Staff("Кузнецова Анна Ивановна", "Нянечка", "8-900-555-66-77")
    );

    @FXML
    public void initialize() {
        setupChildTable();
        setupStaffTable();
    }

    private void setupChildTable() {
        cNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        cAgeCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        cGroupCol.setCellValueFactory(new PropertyValueFactory<>("group"));
        cParentCol.setCellValueFactory(new PropertyValueFactory<>("parentName"));

        // --- ПОИСК ---
        FilteredList<Child> filteredList = new FilteredList<>(childrenData, b -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(child -> {
                if (newValue == null || newValue.isEmpty()) return true;

                String lowerFilter = newValue.toLowerCase();

                if (child.getName().toLowerCase().contains(lowerFilter)) return true; // По имени
                if (child.getGroup().toLowerCase().contains(lowerFilter)) return true; // По группе

                return false;
            });
        });

        SortedList<Child> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(childTable.comparatorProperty());

        childTable.setItems(sortedList);
    }

    private void setupStaffTable() {
        sNameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        sPosCol.setCellValueFactory(new PropertyValueFactory<>("position"));
        sPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        staffTable.setItems(staffData);
    }

    @FXML
    protected void onAddChild() {
        try {
            String name = cNameInput.getText();
            String group = cGroupInput.getText();
            String parent = cParentInput.getText();

            if (name.isEmpty() || group.isEmpty()) return;

            int age = Integer.parseInt(cAgeInput.getText());
            childrenData.add(new Child(name, age, group, parent));
            cNameInput.clear(); cAgeInput.clear(); cGroupInput.clear(); cParentInput.clear();
        } catch (NumberFormatException e) {
            showAlert("Ошибка", "Возраст должен быть целым числом!");
        }
    }

    @FXML
    protected void onDeleteChild() {
        Child selected = childTable.getSelectionModel().getSelectedItem();
        if (selected != null) childrenData.remove(selected);
    }

    @FXML
    protected void onAddStaff() {
        String name = sNameInput.getText();
        String pos = sPosInput.getText();
        String phone = sPhoneInput.getText();

        if (!name.isEmpty() && !pos.isEmpty()) {
            staffData.add(new Staff(name, pos, phone));
            sNameInput.clear(); sPosInput.clear(); sPhoneInput.clear();
        } else {
            showAlert("Ошибка", "Введите ФИО и должность!");
        }
    }

    @FXML
    protected void onDeleteStaff() {
        Staff selected = staffTable.getSelectionModel().getSelectedItem();
        if (selected != null) staffData.remove(selected);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}