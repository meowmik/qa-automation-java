import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class CrudCountryTest {

    private static String PATH = "/api/countries";
    private static String PATH_WITH_ID = PATH + "/{id}";

    private Integer createdCountryId;

    @BeforeAll
    public static void setUpAuth() {
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("admin");
        authScheme.setPassword("admin");

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        RestAssured.requestSpecification = new RequestSpecBuilder()
                //.setBaseUri("localhost:8080")
                .setContentType(ContentType.JSON)
                .setAuth(authScheme)
                .build();
    }

    @AfterEach
    public void deleteCountry() {
        if (createdCountryId != null) {
            validateDelete(createdCountryId);
            createdCountryId = null;
        }
    }

    @Nested
    class PostTest {

        @Test
        public void postSuccessWithoutId() {
            createdCountryId = validatePost(createBody("qe"))
                    .statusCode(201)
                    .body(
                            "countryName", is("qe"),
                            "id", notNullValue())
                    .extract()
                    .path("id");

            validateGetById(createdCountryId)
                    .statusCode(200)
                    .body("id", is(createdCountryId),
                            "countryName", is("qe"));
        }

        @Test
        public void postErrorWithId() {
            validatePost(createBody("kj", 1005))
                    .statusCode(400)
                    .body(
                            "title", is("A new country cannot already have an ID"),
                            "status", is(400));
        }
    }

    @Nested
    class GetTest{

        @Test
        public void getWithId() {
            createdCountryId = createCountry("lp");
            validateGetById(createdCountryId)
                    .statusCode(200)
                    .body(
                            "id", is(createdCountryId),
                            "countryName", is("lp"),
                            "locations", nullValue());
        }
    }

    @Nested
    class PatchTest{
        @Test
        public void patchSuccess() {
            createdCountryId = createCountry("ps");
            given()
                    .body(createBody("jh",createdCountryId))
                    .when()
                    .pathParams("id",createdCountryId)
                    .patch(PATH_WITH_ID)
                    .then()
                    .statusCode(200)
                    .body(
                            "id", is(createdCountryId),
                            "countryName", is("jh"),
                            "locations", nullValue()
                    );
            validateGetById(createdCountryId)
                    .statusCode(200)
                    .body("id", is(createdCountryId),
                            "countryName", is("jh"));
        }

        @Test
        public void patchError() {
            createdCountryId = createCountry("pa");
            given()
                    .body(createBody("pa",createdCountryId))
                    .when().patch("/api/countries")
                    .then()
                    .statusCode(405)
                    .body(
                            "title", is("Method Not Allowed"),
                            "detail", is("Request method 'PATCH' not supported")
                    );
        }
    }

    @Nested
    class PutTest{
        @Test
        public void putSuccess() {
            createdCountryId = createCountry("pu");
            given()
                    .contentType("application/json")
                    .body(createBody("pu",createdCountryId))
                    .when()
                    .pathParams("id",createdCountryId)
                    .put(PATH_WITH_ID)
                    .then()
                    .statusCode(200)
                    .body(
                            "id", is(createdCountryId),
                            "countryName", is("pu"),
                            "locations", nullValue()
                    );
            validateGetById(createdCountryId)
                    .statusCode(200)
                    .body("id", is(createdCountryId),
                            "countryName", is("pu"));
        }
    }

    @Nested
    class DeleteTest{
        @Test
        public void deleteSuccess(){
            createdCountryId = createCountry("re");

            validateDelete(createdCountryId)
                    .statusCode(204);

            validateGetById(createdCountryId)
                    .statusCode(404)
                    .body("title", is("Not Found"),
                            "status", is(404));
            createdCountryId = null;
        }

        @Test
        public void deleteError(){
            validateDelete(2030)
                    .statusCode(500)
                    .body("title", is("Internal Server Error"),
                            "status", is(500));
        }
    }


    private String createBody(String countryName, int id) {
        return String.format("{\"countryName\" : \"%s\", \"id\" : \"%d\"}", countryName, id);
    }

    private String createBody(String countryName) {
        return String.format("{\"countryName\" : \"%s\"}", countryName);
    }

    private ValidatableResponse validatePost(String body) {
        return given()
                .body(body)
                .when().post(PATH)
                .then();

    }

    private Integer createCountry(String countryName){
        return validatePost(createBody(countryName))
                .statusCode(201)
                .body(
                        "countryName", is(countryName),
                        "id", notNullValue())
                .extract()
                .path("id");
    }


    private ValidatableResponse validateDelete(int id) {
        return given()
                .when()
                .pathParams("id", id)
                .delete(PATH_WITH_ID)
                .then();

    }

    private ValidatableResponse validateGetById(int id){
        return given()
                .when()
                .pathParams("id", id)
                .get(PATH_WITH_ID)
                .then();
    }
}
