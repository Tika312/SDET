package apiTests.categories;

import apiTests.BaseApiTest;
import com.digitalnomads.api.asserts.ApiAssert;
import com.digitalnomads.api.entities.Category;
import org.testng.annotations.Test;

import java.util.Arrays;

public class CategoriesApiTest extends BaseApiTest {

    @Test
    void getAllCategories(){
        Category[]categories = categoryController.getAllCategories().as(Category[].class);
        System.out.println(Arrays.toString(categories));
    }

    @Test
    void getCategoryById(){
        categoryController.getCategoryById("3");
        Category category = (Category) categoryController.extractObjectFromResponse(Category.class);
        ApiAssert.assertThat(categoryController.getResponse())
                .isCorrectStatusCode(200)
                .assertCategory()
                .isIdEqual("3");
    }
    @Test
    void getListOfCoursesInCategory(){
        categoryController.getListOfCourses("3");
        categoryController.getNumberOfCoursesInCategory("3");
    }
}
