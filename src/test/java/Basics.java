import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static  io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Basics {


        @Test
        public void testGetMethod(){
            given().
                    when().
                    get("http://zippopotam.us/us/90210").
                    then().
                    assertThat().
                    body("places[0].'place name'", equalTo("Beverly Hills"));
        }

        @Test
        public void testStatusCode(){
            given().
                    when().
                    get("http://zippopotam.us/us/90210").
                    then().
                    assertThat().
                    statusCode(200);
        }
        @Test
        public void testContentType(){
            given().
                    when().
                    get("http://zippopotam.us/us/90210").
                    then().
                    assertThat().

                    //enum
                            contentType(ContentType.JSON);

            // string literal representation
            // contentType("application/json");
        }
        @Test
        public void testLog(){
            given().
                    log().all().
                    when().
                    get("http://zippopotam.us/us/90210").
                    then().
                    log().body();
        }



}
