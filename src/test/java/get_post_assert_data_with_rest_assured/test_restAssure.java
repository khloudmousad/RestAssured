package get_post_assert_data_with_rest_assured;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class test_restAssure {

    @Test
    public void test_response_time() {

        // Sample XML URL
        Response response = RestAssured
                .get("https://www.w3schools.com/xml/simple.xml")
                .then()
                .assertThat()
                .statusCode(200)  // ✅ Assert HTTP Status
                .extract()
                .response();

        System.out.println("Response:\n" + response.asString());
    }
    @Test
    public void name_of_first_item_menu() {

        // Sample XML URL
        Response response = RestAssured
                .get("https://www.w3schools.com/xml/simple.xml")
                .then()
                .assertThat()
                .body("breakfast_menu.food[0].name", equalTo("Belgian Waffles")) // ✅ Assert XML body content
                .extract()
                .response();

    }
    @Test
    public void second_item_setails_menu() {

        // Sample XML URL
        Response response = RestAssured
                .get("https://www.w3schools.com/xml/simple.xml")
                .then()
                .assertThat()
                .body("breakfast_menu.food[1].name",equalTo("Strawberry Belgian Waffles"))
                .body("breakfast_menu.food[1].price",equalTo("$7.95"))
                .body("breakfast_menu.food[1].description",equalTo("Light Belgian waffles covered with strawberries and whipped cream"))
                .body("breaakfast_menu.food[1].calories",equalTo("900"))
                .extract()
                .response();


    }

    @Test
    public void post_on_website(){
        String xml = "<user><name>Kholoud</name><role>Tester</role></user>";

        RestAssured
                .given()
                .header("Content-Type", "application/xml")
                .body(xml)
                .when()
                .post("https://webhook.site/9cd6c863-3564-4286-9958-3b7ae263cd54") // Replace with your real URL
                .then()
                .statusCode(200); // Webhook.site often responds with 200
    }
}



