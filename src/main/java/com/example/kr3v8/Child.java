package com.example.kr3v8;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Child {
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty age;
    private final SimpleStringProperty group;
    private final SimpleStringProperty parentName;

    public Child(String name, int age, String group, String parentName) {
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
        this.group = new SimpleStringProperty(group);
        this.parentName = new SimpleStringProperty(parentName);
    }

    // Геттеры для PropertyValueFactory
    public String getName() { return name.get(); }
    public int getAge() { return age.get(); }
    public String getGroup() { return group.get(); }
    public String getParentName() { return parentName.get(); }

    // Свойства JavaFX
    public SimpleStringProperty nameProperty() { return name; }
    public SimpleIntegerProperty ageProperty() { return age; }
    public SimpleStringProperty groupProperty() { return group; }
    public SimpleStringProperty parentNameProperty() { return parentName; }
}