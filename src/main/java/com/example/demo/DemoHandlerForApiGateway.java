package com.example.demo;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DemoHandlerForApiGateway {

    private final String queueUrl = "Change to queue URL";

    private static ObjectMapper mapper = new ObjectMapper();

    private static AmazonSQSAsync sqs = AmazonSQSAsyncClientBuilder.standard()
            .withRegion(Regions.AP_NORTHEAST_1)
            .build();

    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent requestEvent, Context context)
            throws IOException {
        final APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
        responseEvent.setHeaders(defaultHeaders());

        LambdaLogger logger = context.getLogger();
        logger.log(requestEvent.getBody());

        Request request = mapper.readValue(requestEvent.getBody(), Request.class);
        logger.log(request.toString());

        SendMessageResult result = sqs.sendMessage(queueUrl, requestEvent.getBody());
        logger.log(result.toString());

        responseEvent.setStatusCode(200);
        responseEvent.setBody(mapper.writeValueAsString(new ResponseBody("00", "成功", "true")));

        return responseEvent;
    }

    private Map<String, String> defaultHeaders() {
        Map<String, String> result = new HashMap<>();
        result.put("Content-Type", "application/json");
        return result;
    }
}
