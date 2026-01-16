package com.restfulbooker.utils;

import io.restassured.builder.ResponseSpecBuilder;
import static org.hamcrest.Matchers.lessThan;

import java.util.concurrent.TimeUnit;

import io.restassured.specification.ResponseSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ResponseSpecBuilderUtil {

	public static ResponseSpecification getSuccessResponseSpec() {
		return new ResponseSpecBuilder().expectStatusCode(200).build();
	}
	
	public static ResponseSpecification getCreatedResponseSpec() {
		return new ResponseSpecBuilder().expectStatusCode(201).build();
	}

	public static ResponseSpecification getBookingResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200) 
                .expectResponseTime(lessThan(2000L), TimeUnit.MILLISECONDS)
                .expectBody(matchesJsonSchemaInClasspath("schemas/booking_schema.json"))
                .build();
    }
	
	public static ResponseSpecification getBookingResponseByIdSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200) 
                .expectResponseTime(lessThan(2000L), TimeUnit.MILLISECONDS)
                .expectBody(matchesJsonSchemaInClasspath("schemas/booking_by_id_schema.json"))
                .build();
    }
	
	public static ResponseSpecification getBookingIdsResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200) 
                .expectResponseTime(lessThan(2000L), TimeUnit.MILLISECONDS)
                .expectBody(matchesJsonSchemaInClasspath("schemas/booking_ids_schema.json"))
                .build();
    }
	

	public static ResponseSpecification getErrorResponseSpec(int statusCode) {
		return new ResponseSpecBuilder().expectStatusCode(statusCode).build();
	}
}
