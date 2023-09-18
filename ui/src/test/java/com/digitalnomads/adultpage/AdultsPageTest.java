package com.digitalnomads.adultpage;

import com.codeborne.selenide.Condition;
import com.digitalnomads.BaseTest;
import com.digitalnomads.ui.pages.AdultsPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class AdultsPageTest extends BaseTest {
AdultsPage adultsPage = new AdultsPage();
    @Test
    void adultPageTest(){
        homePage.openNambaHomePage()
                .clickAdultsButton()
                .clickYesButton()
                .clickTobaccoAndHookah();
        boolean result = adultsPage.categoriesOfProducts.stream().anyMatch(e->e.getText().contains("Products"));
        Assert.assertTrue(result);
    }

    @Test
    void adultPageTestRandomCategory(){
        homePage.openNambaHomePage()
                .clickAdultsButton()
                .clickYesButton()
                .clickTobaccoAndHookah()
                .clickRandomOneOfCategories();
    }

    @Test
    void adultPageSupermarket(){
        homePage.openNambaHomePage()
                .clickAdultsButton()
                .clickYesButton()
                .clickTobaccoAndHookah();
//        boolean result = adultsPage.containsText(adultsPage.supermarkets, "Supermarkets");
//        Assert.assertTrue(result);
        adultsPage.supermarkets.shouldHave(Condition.text("Supermarkets"));

    }
}
