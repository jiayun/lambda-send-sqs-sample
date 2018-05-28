package com.example.demo;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageResult;

public class DemoHandler implements RequestHandler<Request, Response> {

    private final String queueUrl = "Change to queue URL";

    private AmazonSQSAsync sqs = AmazonSQSAsyncClientBuilder.standard()
            .withRegion(Regions.AP_NORTHEAST_1)
            .build();

    @Override
    public Response handleRequest(Request input, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log(input.toString());

        SendMessageResult result = sqs.sendMessage(queueUrl, input.toString());
        logger.log(result.toString());

        return new Response("00", "成功", "true");
    }

}
