package geoTest;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

public class GeoServiceTest {

    GeoServiceImpl geoService;

    @BeforeEach
    public void beforeEach() {
        geoService = new GeoServiceImpl();
    }



    @Test
    public void byIpTest() {

        String moscowIp = GeoServiceImpl.MOSCOW_IP;
        String newYorkIp = GeoServiceImpl.NEW_YORK_IP;

        Location expected = new Location("Moscow", Country.RUSSIA, "Lenina", 15);

        Location actual = geoService.byIp(moscowIp);

        Assertions.assertEquals(expected, actual);


    }


    @Test
    public void byCoordinatesTest() {
        Assertions.assertThrows(RuntimeException.class, () -> geoService.byCoordinates(34.1, 25.6));
    }

}
