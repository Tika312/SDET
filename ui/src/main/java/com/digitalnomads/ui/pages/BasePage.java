package com.digitalnomads.ui.pages;


import com.digitalnomads.ui.config.FakeDataProvider;
import com.digitalnomads.ui.helper.ElementActions;



public abstract class BasePage {


    public ElementActions elementActions = new ElementActions();
    public FakeDataProvider faker = new FakeDataProvider();


}
