package com.microservices.productservice;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.productservice.dto.ProductRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.math.BigDecimal;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc

class ProductServiceApplicationTests {

	@Container
//	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo");

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
//    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
//		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer:: getReplicaSetUrl);
//
//
//	}
	@Test
	void shouldCreateProduct() throws Exception {

		mockMvc.perform
				(
						MockMvcRequestBuilders.post("api/product")

								.contentType(MediaType.APPLICATION_JSON)

								.content(

										objectMapper.writeValueAsString(getProductRequest()))

				).
				andExpect(status().isCreated());

	}
    private ProductRequest getProductRequest() {

		return ProductRequest.builder()
				.name("iPhone 14")
				.price(BigDecimal.valueOf(1500))
				.description("Iphone 14 with 68 mp camera")
				.build();
	}
}
