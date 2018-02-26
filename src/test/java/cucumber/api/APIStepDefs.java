package cucumber.api;

import cucumber.api.DataTable;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import junit.framework.Assert;
import utility.Constant;

import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matcher;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonValidator;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.response.Response;

public class APIStepDefs {

	private final static String REG = "(?<!\\\\),";
	private RequestSpecification spec = RestAssured.with();
	private String accessToken = "";
	private Response response = null;

	@Before
	public void setup() {
		
		spec.contentType(ContentType.JSON).accept(ContentType.JSON).log().all();
	}
	
	@Given("^user dost not login$")
	public void user_dost_not_login() throws Throwable {

	}
	
	@Then("^save the following$")
	public void saveValues(DataTable valueList) throws Throwable {

		JsonPath path = response.jsonPath();
		for (DataTableRow row : valueList.getGherkinRows()) {
			List<String> data = row.getCells();
			String key = data.get(2);
			String value = path.getString(data.get(0));
			if(data.get(1).equalsIgnoreCase("saveSystemProperty")) {
				System.setProperty(key, value);
			}
		}
	}

	@Given("^set the following in request header$")
	public void setRequestHeader(DataTable valueList) throws Throwable {
		
		for(DataTableRow row: valueList.getGherkinRows()) {
			List<String> data = row.getCells();
			final String dest_key = data.get(0);
			final String action = data.get(1);
			final String src_key = data.get(2);
			
			final String value_temp = action.equalsIgnoreCase("getSystemProperty") ? System.getProperty(src_key): "";
			final String value = dest_key.equalsIgnoreCase("authorization") ? "Bearer " + value_temp : value_temp;
			
			spec.header(dest_key, value);
		}
	}
	
	private Response sendRequest(RequestSpecification spec, String method, String url) {
		
		if (method.equalsIgnoreCase("get")) {
			
			response = spec.when().get(url);
		}else if(method.equalsIgnoreCase("delete")) {

			response = spec.when().delete(url);
		}else if(method.equalsIgnoreCase("post")) {

			response = spec.when().post(url);
		}else if(method.equalsIgnoreCase("put")) {
			
			response = spec.when().put(url);
		}
//		response.getBody().prettyPrint();
		return response;
	}
	
	@When("^a user send a \"([^\"]*)\" request to \"([^\"]*)\"$")
	public void sendRequest(String method, String url) throws Throwable {

		this.sendRequest(spec, method, url);
	}

	@When("^a user send a \\\"([^\\\"]*)\\\" request to \\\"([^\\\"]*)\\\" with the following$")
	public void sendRequestWithParams(String method, String url, DataTable paraList) throws Throwable {

		Map<String, Object> jsonAsMap = new HashMap<>();
		if (method.equalsIgnoreCase("post") || method.equalsIgnoreCase("put")) {

			for (DataTableRow row : paraList.getGherkinRows()) {
				List<String> data = row.getCells();
				jsonAsMap.put(data.get(0), data.get(1));
			}
			spec.body(jsonAsMap);
			this.sendRequest(spec, method, url);
		}
	}

	@When("^a user send a \"([^\"]*)\" request to \"([^\"]*)\" with the body$")
	public void sendRequestWithBody(String method, String url, String bodyStr) throws Throwable {

		spec.body(bodyStr);
		this.sendRequest(spec, method, url);

	}

	@Then("^the status code should be (\\d+)$")
	public void validateStatusCode(int statusCode) throws Throwable {

		response.then().statusCode(statusCode);
	}

	@Then("^it follows schema defined by \\\"([^\\\"]*)\\\"$")
	public void validateSchema(String schemaFileName) throws Throwable {

		JsonNode data = JsonLoader.fromString(response.asString());
		JsonNode schema = JsonLoader
				.fromFile(new File(Constant.USRDIR + Constant.DIR_TO_API_SCHEMA_FILE + schemaFileName));
		JsonValidator validator = JsonSchemaFactory.byDefault().getValidator();
		Assert.assertTrue(validator.validate(schema, data).isSuccess());
	}

	@Then("^it should return within (\\d+) secs$")
	public void validateResponseTime(int arg1) throws Throwable {

		response.then().time(lessThan(Long.valueOf(arg1)), TimeUnit.SECONDS);
	}

	@Then("^the JSON response should have the following$")
	public void validateResponseParams(DataTable responseFields) throws Throwable {
		
		response.getBody().prettyPrint();
		Iterator<DataTableRow> rows = responseFields.getGherkinRows().iterator();
		while (rows.hasNext()) {
			List<String> row = rows.next().getCells();
			String key = row.get(0);
			String relation = row.get(1);
			String value = row.get(2);
			String actual_value = response.jsonPath().getString(key);

			if (StringUtils.isNumeric(actual_value) && StringUtils.isNumeric(value)) {
				Assert.assertEquals(Integer.parseInt(value), Integer.parseInt(actual_value));
			} else if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
				this.validateResponse(response, key, relation, Boolean.valueOf(value));
			} else {
				if (value.contains(",")) {
					String[] value_temp = value.split(REG);
					if (StringUtils.isNumeric(value_temp[0])) {
						List<Integer> values = (List<Integer>) Arrays.asList(value_temp).stream()
								.map(elem -> Integer.parseInt(((String) elem).replace("\\", "").trim()))
								.collect(Collectors.toList());
						this.validateResponse(response, key, relation, values.toArray(new Integer[values.size()]));
					} else {
						List<String> values = (List<String>) Arrays.asList(value.split(REG)).stream()
								.map(elem -> ((String) elem).replace("\\", "").trim()).collect(Collectors.toList());
						this.validateResponse(response, key, relation, values.toArray(new String[values.size()]));
					}
				} else {
					this.validateResponse(response, key, relation, value);
				}
			}
		}
	}

	private <T> void validateResponse(Response response, String key, String relation, T... values) {

		Matcher matcher = hasItem(values);
		switch (relation.toLowerCase()) {
		case "isoneof":
			matcher = isOneOf(values);
			break;
		case "containsinanyorder":
			matcher = containsInAnyOrder(values);
			break;
		default:
			break;
		}
		response.then().body(key, matcher);
	}

	private void validateResponse(Response response, String key, String relation, Object value) {

		Matcher matcher = hasItem(value);
		switch (relation.toLowerCase()) {
		case "equalto":
			matcher = equalTo(value);
			break;
		case "nothasitem":
			matcher = not(hasItem(value));
			break;
		case "greaterthanorequalto":
			matcher = greaterThanOrEqualTo((int) value);
			break;
		case "notequalto":
			matcher = not(equalTo(value));
			break;
		default:
			break;
		}

		response.then().body(key, matcher);
	}
}
