package guru.qa;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class Steps {

    @Step("Открывает главную страницу")
    public void openMainPage() {
        open("");
    }

    @Step("Ищем репозиторий")
    public void searchForRepository(String repo) {
        $(".header-search-input").setValue(repo).pressEnter();
    }

    @Step("Кликаем по ссылке репозитория")
    public void clickOnRepositoryLink(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Открываем Issue")
    public void openIssueTab() {
        $("#issues-tab").click();
    }

    @Step("Проверяем наличие Issue с именем")
    public void shouldSeeIssueWithNumber(String issue) {
        $(withText(issue)).should(Condition.exist);
    }
}
