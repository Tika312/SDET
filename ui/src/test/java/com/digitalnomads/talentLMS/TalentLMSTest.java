package com.digitalnomads.talentLMS;

import com.digitalnomads.BaseTest;
import com.digitalnomads.ui.pages.talentLMS.TalentLMSMainPage;
import org.testng.annotations.Test;


public class TalentLMSTest extends BaseTest {

    TalentLMSMainPage tl = new TalentLMSMainPage();
    @Test
    void testMainPage(){
        tl.openMainPage()
                .clickUsers()
                .returnBack()
                .clickAddUsers()
                .returnBack()
                .clickCategories()
                .returnBack()
                .clickAddCategories();
    }
}
