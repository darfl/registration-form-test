import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormTestWithComments {

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
        Selenide.executeJavaScript("$('#fisedban').remove()");//костыльная команда, чтобы убрать рекламные банеры, выполняем скрипт на стороне браузера
        Selenide.executeJavaScript("$('footer').remove()");


        $("#firstName").setValue("userName");
        $("#lastName").setValue("petrov");
        $("#userEmail").setValue("egor.petrov@gmail.com");

        $("#genterWrapper").$(byText("Male")).click();// просто byText не подходит для тестинга сайта на разных языках
        //$("#gender-radio-1").parent().click(); //выбираем родительский элемет этого айдишника
        //$("label[for=gender-radio-1]").click();

        $("#userNumber").setValue("7123456789");


        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOptionByValue("2008");
        $(".react-datepicker__month-select").selectOption("July");//это типовой html, месяцы под тегами option
        //$(".react-datepicker__month-select").selectOptionByValue("6");//менее читаемо
        //$("#dateOfBirth").$(".react-datepicker__month").find(byText("13")).click();
        $("react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click();//:not(selector) чтобы выбрать день в конкретном месяце


        $("#subjectsInput").setValue("Maths").pressEnter();
        //$("#subjectsWrapper input").setValue("Maths").pressEnter();


        $("#submit").scrollTo();
        $("#hobbiesWrapper").$(byText("Reading")).click();


        $("#uploadPicture").uploadFromClasspath("img/test1.jpg");//вариант короче, предполагает, что уже лежит в resources, работает только для "type=file";
        //$("#uploadPicture").uploadFile(new File("src/test/resources/img/test1.jpg"));//через new File("") и copy path


        $("#currentAddress").setValue("Pushkina str 2");


        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        //$("#react-select-3-option-0").click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();
        $("#submit").click();


        //$(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(exactText("Thanks for submitting the form"));
        //$(".table-responsive").shouldHave(text(userName), text("petrov"), text("egor.petrov@gmail.com"), text("7123456789"));

        $(".table-responsive").$(byText("Student Name")).parent()
                .shouldHave(text("egor petrov"));
        $(".table-responsive").$(byText("Student Email")).parent()
                .shouldHave(text("egor.petrov@gmail.com"));
        $(".table-responsive").$(byText("Gender")).parent()
                .shouldHave(text("Male"));
        $(".table-responsive").$(byText("Mobile")).parent()
                .shouldHave(text("7123456789"));
        $(".table-responsive").$(byText("Date of Birth")).parent()
                .shouldHave(text("13 June,2009"));
        $(".table-responsive").$(byText("Subjects")).parent()
                .shouldHave(text("Maths"));
        $(".table-responsive").$(byText("Hobbies")).parent()
                .shouldHave(text("Reading"));
        $(".table-responsive").$(byText("Picture")).parent()
                .shouldHave(text("img/test1.jpg"));
        $(".table-responsive").$(byText("Address")).parent()
                .shouldHave(text("Pushkina str 2"));
        $(".table-responsive").$(byText("State and City")).parent()
                .shouldHave(text("NCR Delhi"));
    }
}