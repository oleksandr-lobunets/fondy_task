//package eu.fondy.task.utils;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import eu.fondy.task.entity.CoinBreakdown;
//import jakarta.persistence.AttributeConverter;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//
//
//@Slf4j
//public class BreakdownAttributeConverter<T extends CoinBreakdown> implements AttributeConverter<T, String> {
//    private static final ObjectMapper objectMapper = new ObjectMapper();
//
//    @Override
//    public String convertToDatabaseColumn(T t) {
//        try {
//            return objectMapper.writeValueAsString(t);
//        } catch (JsonProcessingException jpe) {
//            log.error("Cannot convert CoinBreakdown into JSON");
//            return null;
//        }
//    }
//
//    @Override
//    public T convertToEntityAttribute(String value) {
//        try {
//            return objectMapper.readValue(value, new TypeReference<JsonResponse<T>>() {}).getResult();
//        } catch (JsonProcessingException e) {
//            log.error("Cannot convert JSON into CoinBreakdown");
//            return null;
//        }
//    }
//
//    @Getter
//    @Setter
//    private static class JsonResponse<T> {
//        private T result;
//    }
//
//}
