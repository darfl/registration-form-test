import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void successfulRegistrationTest() {
        String userName = "egor";

        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        Selenide.executeJavaScript("$('#fisedban').remove()");
        Selenide.executeJavaScript("$('footer').remove()");


        $("#firstName").setValue("userName");
        $("#lastName").setValue("petrov");
        $("#userEmail").setValue("egor.petrov@gmail.com");

        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("7123456789");


        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOptionByValue("2008");
        $(".react-datepicker__month-select").selectOption("July");
        $("react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click();


        $("#subjectsInput").setValue("Maths").pressEnter();


        $("#submit").scrollTo();
        $("#hobbiesWrapper").$(byText("Reading")).click();


        $("#uploadPicture").uploadFromClasspath("img/test1.jpg");

        $("#currentAddress").setValue("Pushkina str 2");


        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();

        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();
        $("#submit").click();


        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(exactText("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text(userName), text("petrov"), text("egor.petrov@gmail.com"), text("7123456789"));

    }
}

