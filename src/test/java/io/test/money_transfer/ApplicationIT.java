package io.test.money_transfer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.test.money_transfer.config.validation.ErrorResponse;
import io.test.money_transfer.utils.PostgresDbCleaner;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("local")
@SpringBootTest(classes = ITMoneyTransferServiceApplication.class)
@AutoConfigureMockMvc(printOnlyOnFailure = false)
public abstract class ApplicationIT {

    @Autowired
    protected MockMvc mvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected RedissonClient redissonClient;

    @Autowired
    protected PostgresDbCleaner postgresDbCleaner;

    protected String toJson(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    protected <T> T toModel(final byte[] result, final Class<T> modelClass) {
        try {
            return objectMapper.readValue(result, modelClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected <T> List<T> toList(final byte[] result, final TypeReference<List<T>> ref) {
        try {
            return objectMapper.readValue(result, ref);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void assertErrorResponse(final ResultActions operation, HttpStatus httpStatus, String message, String status) throws Exception {
        var bytes = operation
                .andExpect(status().is(httpStatus.value()))
                .andReturn().getResponse().getContentAsByteArray();
        var response = toModel(bytes, ErrorResponse.class);
        assertEquals(message, response.getMessage());
        assertEquals(status, response.getStatus());
    }
}
