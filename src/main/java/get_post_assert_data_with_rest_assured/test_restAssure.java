package get_post_assert_data_with_rest_assured;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

public class test_restAssure {

    public static void test_response_time() {
        System.out.println("🕒 [TEST] Running: test_response_time");

        Response response = RestAssured
                .given()
                .get("https://www.w3schools.com/xml/simple.xml")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("✅ [PASSED] Status 200 OK");
        System.out.println("📦 Response:\n" + response.asString());
    }

    public static void name_of_first_item_menu() {
        System.out.println("📄 [TEST] Running: name_of_first_item_menu");

        RestAssured
                .get("https://www.w3schools.com/xml/simple.xml")
                .then()
                .assertThat()
                .body("breakfast_menu.food[0].name", equalTo("Belgian Waffles"));

        System.out.println("✅ [PASSED] First item name matched");
    }

    public static void second_item_setails_menu() {
        System.out.println("📄 [TEST] Running: second_item_setails_menu");

        RestAssured
                .get("https://www.w3schools.com/xml/simple.xml")
                .then()
                .assertThat()
                .body("breakfast_menu.food[1].name", equalTo("Strawberry Belgian Waffles"))
                .body("breakfast_menu.food[1].price", equalTo("$7.95"))
                .body("breakfast_menu.food[1].description", equalTo("Light Belgian waffles covered with strawberries and whipped cream"))
                .body("breakfast_menu.food[1].calories", equalTo("900")); // ✅ fixed typo

        System.out.println("✅ [PASSED] Second item details matched");
    }

    public static void post_on_website() {
        System.out.println("📡 [TEST] Running: post_on_website");

        String xml = "<user><name>Kholoud</name><role>Tester</role></user>";

        RestAssured
                .given()
                .header("Content-Type", "application/xml")
                .body(xml)
                .when()
                .post("https://webhook.site/9cd6c863-3564-4286-9958-3b7ae263cd54")
                .then()
                .statusCode(200);

        System.out.println("✅ [PASSED] XML posted to webhook");
    }

    public static void main(String[] args) {
        System.out.println("🚀 Starting all RestAssured XML tests...");

        test_response_time();
        post_on_website();
        second_item_setails_menu();
        name_of_first_item_menu();

        System.out.println("🏁 All tests completed successfully.");
    }
}
