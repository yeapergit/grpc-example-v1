package calculator.client;

import com.proto.calculator.CalculatorRequest;
import com.proto.calculator.CalculatorResponse;
import com.proto.calculator.CalculatorServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class CalculatorClient {

    private static void doSum(ManagedChannel channel, int a, int b) {
        System.out.println("enter doSum");
        CalculatorServiceGrpc.CalculatorServiceBlockingStub stub = CalculatorServiceGrpc.newBlockingStub(channel);
        CalculatorResponse response = stub.sum(CalculatorRequest.newBuilder().setFirstNumber(a).setSecondNumber(b).build());

        System.out.println("Sum is: " + response.getResult());
    }

    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("need 2 arguments");
            return;
        }

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50052)
                .usePlaintext()
                .build();

        try {
            int firstArg = Integer.parseInt(args[0]);
            int secondArg = Integer.parseInt(args[1]);

            doSum(channel, firstArg, secondArg);

        } catch (NumberFormatException ex) {
            System.out.println("Arguments must be integers");
        }

        System.out.println("Shutting down");
        channel.shutdown();
    }
}
