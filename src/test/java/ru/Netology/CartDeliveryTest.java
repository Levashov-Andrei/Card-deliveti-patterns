package ru.Netology;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CartDeliveryTest {

    private String generateDate (int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void shouldRegisterDeliverycard() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Ря");
        $$(".menu-item__control").findBy(text("Рязань")).click();
        String planningDate = generateDate(4,"dd.MM.yyyy");
        if(!generateDate(3,"MM").equals(generateDate(7,"MM"))){
     $("[calendar__arrow calendar__arrow_direction_right]").click();
        }
        $$("").findBy(text(generateDate(7,"dd"))).click();
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Петров Петр");
        $("[data-test-id='phone'] input").setValue("+79112133442");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(visible, Duration.ofSeconds(30))
                .shouldHave(exactText("Встреча успешно забронирована на "+ planningDate))

    }

}


//document.addEventListener('keydown', function (e) {
//        if (e.keyCode == 119) { // F8
//        debugger;
//        }
//        }, {
//        capture: true
//        });