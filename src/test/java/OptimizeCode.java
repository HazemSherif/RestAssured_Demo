import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;


public class OptimizeCode {
    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;


    @BeforeClass
    public static void createRequestSpecification() {

        requestSpec = new RequestSpecBuilder().
                setBaseUri("http://api.zippopotam.us").
                build();
    }

    @BeforeClass
    public static void createResponseSpecification() {

        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();
    }

    @Test
    public void testRequestSpecification() {

        given().
        spec(requestSpec).
        when().
        get("us/90210").
        then().
        assertThat().
        statusCode(200);
    }
    @Test
    public void testResponseSpec() {

        given().
                spec(requestSpec).
                when().
                get("http://zippopotam.us/us/90210").
                then().
                spec(responseSpec).
                and().
                assertThat().
                body("places[0].'place name'", equalTo("Beverly Hills"));
    }
    @Test
    public void testRequest() {

        String placeName =
        given().
            spec(requestSpec).
        when().
            get("http://zippopotam.us/us/90210").
        then().
            extract().
            path("places[0].'place name'");

        Assert.assertEquals("Beverly Hills",placeName);
    }
}
