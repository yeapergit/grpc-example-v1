package calculator.server;

import com.proto.calculator.CalculatorRequest;
import com.proto.calculator.CalculatorResponse;
import com.proto.calculator.CalculatorServiceGrpc;
import io.grpc.stub.StreamObserver;

public class CalculatorServerImpl extends CalculatorServiceGrpc.CalculatorServiceImplBase {

    @Override
    public void sum(CalculatorRequest request, StreamObserver<CalculatorResponse> responseObserver) {
        responseObserver.onNext(CalculatorResponse.newBuilder().setResult(auxSum(request)).build());
        responseObserver.onCompleted();
    }

    private int auxSum(CalculatorRequest request) {
        return request.getFirstNumber() + request.getSecondNumber();
    }
}