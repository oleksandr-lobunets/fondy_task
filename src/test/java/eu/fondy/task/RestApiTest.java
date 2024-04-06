package eu.fondy.task;


import eu.fondy.task.dto.ChangeRequestDto;
import eu.fondy.task.dto.ChangeResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j

class RestApiTest extends BaseTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    void postChange() {

        int penceSubmitted = 5;
        ChangeResponseDto response = this.restTemplate.postForObject(
                getHost() + "/change",
                requestDto(UUID.randomUUID(), penceSubmitted),
                ChangeResponseDto.class);

        //todo asserts
        assertThat(penceSubmitted).isEqualTo(response.getPenceSubmitted());

        System.out.println("response: " + response);
    }


    private ChangeRequestDto requestDto(UUID id, Integer pence) {
        return ChangeRequestDto.builder()
                        .id(id)
                        .pence(pence)
                        .build();
    }


    private String getHost() {
        return "http://localhost:" + port;
    }


}
