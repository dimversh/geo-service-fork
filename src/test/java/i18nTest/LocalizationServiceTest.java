package i18nTest;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocalizationServiceTest {

    LocalizationServiceImpl localizationService;

    @BeforeEach
    public void beforeAll(){
        localizationService = new LocalizationServiceImpl();
    }


    @Test
    public void localeTest() {

        String expected = "Добро пожаловать";

        String actual = localizationService.locale(Country.RUSSIA);

        Assertions.assertEquals(expected, actual);
    }

}
