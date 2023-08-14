package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderTest {

    GeoService geoService;
    LocalizationService localizationService;

    @BeforeEach
    public void beforeEach() {

        geoService = Mockito.mock(GeoServiceImpl.class);
        localizationService = Mockito.mock(LocalizationServiceImpl.class);

    }

    Map<String, String> headers = new HashMap<>(Map.of(
            MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149")
    );

    @Test
    void russian_language_test() {

        Mockito.when(geoService.byIp("172.0.32.11"))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15)
                );

        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        String actual = messageSender.send(headers);
        String expected = "Добро пожаловать";

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void english_language_test() {

        Mockito.when(geoService.byIp("96.44.183.149"))
                .thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32)
                );

        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        String actual = messageSender.send(headers);
        String expected = "Welcome";

        Assertions.assertEquals(expected, actual);
    }


}
