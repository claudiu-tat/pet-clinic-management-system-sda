package com.sda.claudiu.petclinicmanagementsystem.menu;

import javax.print.DocFlavor;

public enum UserOption {
    CREATE_VETERINARIAN(1, "Create a veterinarian"),
    SHOW_ALL_VETERINARIANS(2, "Display all veterinarians"),
    UPDATE_VETERINARIAN(3, "Update veterinarian"),
    DELETE_VETERINARIAN(4, "Delete veterinarian"),
    CREATE_PET(5, "Create a pet"),
    SHOW_ALL_PETS(6, "Display all pets"),
    UPDATE_PET(7, "Update pet"),
    DELETE_PET(8, "Delete pet"),
    ADD_CONSULT(9, "Add consult"),
    SHOW_ALL_CONSULTS(10, "Display all consults"),
    UPDATE_CONSULT(11, "Update consult"),
    EXIT(99, "Exit"),
    UNKNOWN(100, "Unknown option");

    private int numericOption;      // to have the possibility to pick a number, we have to declare the numeric option here
    private String displayValue;    // here is the option displayed for specific number

    UserOption(int numericOption, String displayValue) {    // with the constructor we can give the numeric option and the value in our menu
        this.numericOption = numericOption;
        this.displayValue = displayValue;
    }

    public int getNumericOption() {         // we need just getters because we don't want to change the value with setters, we just want to display the menu
        return numericOption;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public static void printAllOptions() {      // we need this method to display all options avl
        for (UserOption value : UserOption.values()) {
            if (value != UNKNOWN) {             // validate not to be unknown
                System.out.println(value.getNumericOption() + " - " + value.getDisplayValue());     // display the option
            }
        }
    }

    public static UserOption findUserOption(int numericValue) {    // numericValue is the parameter we just give
        for (UserOption value : UserOption.values()) {
            if (value.numericOption == numericValue) {      // validate is numericValue is wright with the numericOption from menu
                return value;
            }
        }
        return UNKNOWN;
    }
}
