package models;

import utils.Utilities;

public class Owner {

    private String name = "";
    private String phoneNumber = "";

    public Owner(String name, String phoneNumber) {
        this.name = Utilities.truncateString(name, 30);
        this.phoneNumber = Utilities.truncateString(phoneNumber, 20);
    }

    public Owner() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (Utilities.validateStringLength(name, 30)) {
            this.name = name;
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (Utilities.validateStringLength(phoneNumber, 20)) {
            this.phoneNumber = phoneNumber;
        }
    }

    @Override
    public String toString() {
        return "Owner{name='" + name + "', phoneNumber='" + phoneNumber + "'}";
    }
}
