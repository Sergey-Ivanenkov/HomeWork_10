package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class SelenideTest {

    private static final String REPOSITORY = "allure-framework/allure2",
            ISSUE_NAME = "Tooltip shows up too high";

    @BeforeAll
    static void allureOn() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.baseUrl = "https://github.com";
    }

    @Test //Тест чистый Selenide
    public void testIssueSearch() {


        open("");
        $(".header-search-input").setValue(REPOSITORY).pressEnter();
        $(linkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $(withText(ISSUE_NAME)).should(Condition.exist);
    }


    @Test //Тест с использованием Lambda
    public void testLambdaSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открывает главную стрницу", () -> {
            open("");
        });

        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".header-search-input").setValue(REPOSITORY).pressEnter();
        });

        step("Кликаем по репозиторию " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });

        step("Открываем таб Issue", () -> {
            $("#issues-tab").click();
        });

        step("Проверяем наличие Issue с именем " + ISSUE_NAME, () -> {
            $(withText(ISSUE_NAME)).should(Condition.exist);
        });

    }

    @Test //Тест с анотацией @Step
    public void testAnnotationSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Steps steps = new Steps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssueTab();
        steps.shouldSeeIssueWithNumber(ISSUE_NAME);
    }
}
