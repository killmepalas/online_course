package kill.me.palas.server;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import kill.me.palas.grpc.UpdateServiceGrpc;
import kill.me.palas.grpc.UpdateServiceOuterClass;
import kill.me.palas.models.Course;

public class Client {
    public static void notification(Course course, int typeElement, int typeAction, String name) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8090")
                .usePlaintext().build();

        UpdateServiceGrpc.UpdateServiceBlockingStub stub =
                UpdateServiceGrpc.newBlockingStub(channel);

        UpdateServiceOuterClass.ElementType[] elementTypes = UpdateServiceOuterClass.ElementType.values();
        UpdateServiceOuterClass.ActionType[] actionTypes = UpdateServiceOuterClass.ActionType.values();

        UpdateServiceOuterClass.UpdateRequest request = UpdateServiceOuterClass.UpdateRequest
                .newBuilder().setCourse(course.getName())
                .setElement(name)
                .setElementType(elementTypes[typeElement])
                .setActionType(actionTypes[typeAction])
                .build();

        UpdateServiceOuterClass.UpdateResponse response = stub.update(request);

        System.out.println(response);

        channel.shutdownNow();
    }
}
