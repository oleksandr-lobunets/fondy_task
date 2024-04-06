package eu.fondy.task;


import eu.fondy.task.dto.ChangeRequestDto;
import eu.fondy.task.dto.ChangeResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
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

        int penceSubmitted = 50122;
        UUID id = UUID.randomUUID();

        Map<Integer, Integer> expectedPence = Map.of(
                50, 0,
                20, 1,
                10, 0,
                5, 0,
                2, 1,
                1, 0
        );

        Map<Integer, Integer> expectedPounds = Map.of(
                50, 0,
                20, 0,
                10, 0,
                5, 0,
                2, 0,
                1, 0
        );

        ChangeResponseDto response = this.restTemplate.postForObject(
                getHost() + "/change",
                requestDto(id, penceSubmitted),
                ChangeResponseDto.class);

        assertThat(response.getPenceSubmitted()).isEqualTo(penceSubmitted);
        assertThat(response.getExternalID()).isEqualTo(id);
        assertThat(response.getPence()).isEqualTo(expectedPence);
        assertThat(response.getPounds()).isEqualTo(expectedPounds);
        assertThat(response.getDateTime()).isNotNull();

    }


    @Test
    void postChange_withoutPence() {

        ResponseEntity<Map> response = this.restTemplate.exchange(
                getHost() + "/change",
                HttpMethod.POST,
                new HttpEntity<>(requestDto(UUID.randomUUID(), null)),
                Map.class,
                "");

        var expectedMsgs = List.of(
                "Invalid pence value: pence should not be empty, id is not specified",
                "Invalid pence value: pence should not be empty"
        );

        assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get("errors")).isNotNull();
        assertThat((List<String>)response.getBody().get("errors")).containsAnyElementsOf(expectedMsgs);

    }


    @Test
    void postChange_duplicationId() {

        int penceSubmitted = 50122;
        UUID id = UUID.fromString("f12ff389-7429-4639-9b0a-58e8e2bf51e7");

       this.restTemplate.postForObject(
                getHost() + "/change",
                requestDto(id, penceSubmitted),
                ChangeResponseDto.class);

        ResponseEntity<Map> response = this.restTemplate.exchange(
                getHost() + "/change",
                HttpMethod.POST,
                new HttpEntity<>(requestDto(id, penceSubmitted)),
                Map.class,
                "");


        var expectedMsgs = List.of("Change for id='f12ff389-7429-4639-9b0a-58e8e2bf51e7' already calculated");


        assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get("errors")).isNotNull();
        assertThat((List<String>)response.getBody().get("errors")).isEqualTo(expectedMsgs);

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
