package jsonschema;

import java.util.Arrays;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.saasquatch.jsonschemainferrer.*;
import org.testng.annotations.Test;

public class JsonSchemaTest {
    @Test
    public void test2() {
        ObjectMapper mapper = new ObjectMapper();
        JsonSchemaInferrer inferrer = JsonSchemaInferrer
                .newBuilder()
                .setSpecVersion(SpecVersion.DRAFT_06)
                .setAdditionalPropertiesPolicy(AdditionalPropertiesPolicies.noOp())
                .setRequiredPolicy(RequiredPolicies.nonNullCommonFields())
                .build();
        String json1 = "{\"a\":\"1\",\"c\":null,\"list\":[{\"a\":0,\"b\": \"\" },{\"a\":0,\"c\": null }]}";
        String json2 = "{\"a\":\"1\",\"b\":\"2\",\"list\":[{\"a\":0,\"b\": \"\" },{\"a\":0,\"c\": null }]}";

        ObjectNode inferForSamples2 = null;
        try {
            inferForSamples2 = inferrer.inferForSamples(Arrays.asList(
                    mapper.readTree(json1),
                    mapper.readTree(json2)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(inferForSamples2.toPrettyString());
    }

}
