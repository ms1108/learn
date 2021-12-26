package jsonschema;

import java.util.Arrays;

import java.util.Arrays;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saasquatch.jsonschemainferrer.*;
import org.testng.annotations.Test;

public class JsonSchemaTest {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final JsonSchemaInferrer inferrer = JsonSchemaInferrer.newBuilder()
            .setSpecVersion(SpecVersion.DRAFT_06)
            // Requires commons-validator
            .addFormatInferrers(FormatInferrers.email(), FormatInferrers.ip())
            .setAdditionalPropertiesPolicy(AdditionalPropertiesPolicies.notAllowed())
            .setRequiredPolicy(RequiredPolicies.nonNullCommonFields())
            .addEnumExtractors(EnumExtractors.validEnum(java.time.Month.class),
                    EnumExtractors.validEnum(java.time.DayOfWeek.class))
            .build();
    @Test
    public void test(){
        final JsonNode sample1;
        try {
            sample1 = mapper.readTree(
                    "{\"🙈\":\"https://saasquatch.com\",\"🙉\":[-1.5,2,\"hello@saasquat.ch\",false],\"🙊\":3,\"weekdays\":[\"MONDAY\",\"TUESDAY\"]}");
        final JsonNode sample2 = mapper.readTree(
                "{\"🙈\":1,\"🙉\":{\"🐒\":true,\"🐵\":[2,\"1234:5678::\"],\"🍌\":null},\"🙊\":null,\"months\":[\"JANUARY\",\"FEBRUARY\"]}");
        final JsonNode resultForSample1 = inferrer.inferForSample(sample1);
        final JsonNode resultForSample1And2 =
                inferrer.inferForSamples(Arrays.asList(sample1, sample2));
        for (JsonNode j : Arrays.asList(sample1, sample2, resultForSample1, resultForSample1And2)) {
            System.out.println(mapper.writeValueAsString(j));
        }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        ObjectMapper mapper = new ObjectMapper();
        JsonSchemaInferrer inferrer = JsonSchemaInferrer
                .newBuilder()
                .setSpecVersion(SpecVersion.DRAFT_07)
//                .setAdditionalPropertiesPolicy(AdditionalPropertiesPolicies.noOp())
//                .setRequiredPolicy(RequiredPolicies.nonNullCommonFields())
                .addFormatInferrers(FormatInferrers.email(), FormatInferrers.ip())
                .setAdditionalPropertiesPolicy(AdditionalPropertiesPolicies.notAllowed())
                .setRequiredPolicy(RequiredPolicies.nonNullCommonFields())
                .addEnumExtractors(EnumExtractors.validEnum(java.time.Month.class),
                        EnumExtractors.validEnum(java.time.DayOfWeek.class))
                .build();
        String json1 = "{\"a\":\"1\",\"c\":null,\"list\":[{\"a\":0,\"b\": \"\" },{\"a\":0,\"c\": null }]}";
        String json2 = "{\"a\":\"1\",\"b\":\"2\",\"list\":[{\"a\":0,\"b\": \"\" },{\"a\":0,\"c\": null }]}";

        try {
            JsonNode resultForSample1 = inferrer.inferForSample(mapper.readTree(json1));
            JsonNode resultForSample1And2 =
                    inferrer.inferForSamples(Arrays.asList(mapper.readTree(json1), mapper.readTree(json2)));
            System.out.println(resultForSample1And2);
            for (JsonNode j : Arrays.asList(mapper.readTree(json1), mapper.readTree(json2), resultForSample1, resultForSample1And2)) {
                System.out.println(mapper.writeValueAsString(j));
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
//        ObjectNode inferForSamples2 = null;
//        try {
//            inferForSamples2 = inferrer.inferForSamples(Arrays.asList(
//                    mapper.readTree(json1),
//                    mapper.readTree(json2)));
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        System.out.println(inferForSamples2.toPrettyString());
    }

}
