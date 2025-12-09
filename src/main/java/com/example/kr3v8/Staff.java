package com.example.kr3v8;

import javafx.beans.property.SimpleStringProperty;

public class Staff {
    private final SimpleStringProperty fullName;
    private final SimpleStringProperty position;
    private final SimpleStringProperty phone;

    public Staff(String fullName, String position, String phone) {
        this.fullName = new SimpleStringProperty(fullName);
        this.position = new SimpleStringProperty(position);
        this.phone = new SimpleStringProperty(phone);
    }

    public String getFullName() { return fullName.get(); }
    public String getPosition() { return position.get(); }
    public String getPhone() { return phone.get(); }

    public SimpleStringProperty fullNameProperty() { return fullName; }
    public SimpleStringProperty positionProperty() { return position; }
    public SimpleStringProperty phoneProperty() { return phone; }
}