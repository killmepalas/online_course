package kill.me.palas.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.24.0)",
    comments = "Source: UpdateService.proto")
public final class UpdateServiceGrpc {

  private UpdateServiceGrpc() {}

  public static final String SERVICE_NAME = "kill.me.palas.grpc.UpdateService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<kill.me.palas.grpc.UpdateServiceOuterClass.UpdateRequest,
      kill.me.palas.grpc.UpdateServiceOuterClass.UpdateResponse> getUpdateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "update",
      requestType = kill.me.palas.grpc.UpdateServiceOuterClass.UpdateRequest.class,
      responseType = kill.me.palas.grpc.UpdateServiceOuterClass.UpdateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kill.me.palas.grpc.UpdateServiceOuterClass.UpdateRequest,
      kill.me.palas.grpc.UpdateServiceOuterClass.UpdateResponse> getUpdateMethod() {
    io.grpc.MethodDescriptor<kill.me.palas.grpc.UpdateServiceOuterClass.UpdateRequest, kill.me.palas.grpc.UpdateServiceOuterClass.UpdateResponse> getUpdateMethod;
    if ((getUpdateMethod = UpdateServiceGrpc.getUpdateMethod) == null) {
      synchronized (UpdateServiceGrpc.class) {
        if ((getUpdateMethod = UpdateServiceGrpc.getUpdateMethod) == null) {
          UpdateServiceGrpc.getUpdateMethod = getUpdateMethod =
              io.grpc.MethodDescriptor.<kill.me.palas.grpc.UpdateServiceOuterClass.UpdateRequest, kill.me.palas.grpc.UpdateServiceOuterClass.UpdateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "update"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kill.me.palas.grpc.UpdateServiceOuterClass.UpdateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kill.me.palas.grpc.UpdateServiceOuterClass.UpdateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UpdateServiceMethodDescriptorSupplier("update"))
              .build();
        }
      }
    }
    return getUpdateMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UpdateServiceStub newStub(io.grpc.Channel channel) {
    return new UpdateServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UpdateServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new UpdateServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UpdateServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new UpdateServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class UpdateServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void update(kill.me.palas.grpc.UpdateServiceOuterClass.UpdateRequest request,
        io.grpc.stub.StreamObserver<kill.me.palas.grpc.UpdateServiceOuterClass.UpdateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getUpdateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kill.me.palas.grpc.UpdateServiceOuterClass.UpdateRequest,
                kill.me.palas.grpc.UpdateServiceOuterClass.UpdateResponse>(
                  this, METHODID_UPDATE)))
          .build();
    }
  }

  /**
   */
  public static final class UpdateServiceStub extends io.grpc.stub.AbstractStub<UpdateServiceStub> {
    private UpdateServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UpdateServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected UpdateServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UpdateServiceStub(channel, callOptions);
    }

    /**
     */
    public void update(kill.me.palas.grpc.UpdateServiceOuterClass.UpdateRequest request,
        io.grpc.stub.StreamObserver<kill.me.palas.grpc.UpdateServiceOuterClass.UpdateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class UpdateServiceBlockingStub extends io.grpc.stub.AbstractStub<UpdateServiceBlockingStub> {
    private UpdateServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UpdateServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected UpdateServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UpdateServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public kill.me.palas.grpc.UpdateServiceOuterClass.UpdateResponse update(kill.me.palas.grpc.UpdateServiceOuterClass.UpdateRequest request) {
      return blockingUnaryCall(
          getChannel(), getUpdateMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class UpdateServiceFutureStub extends io.grpc.stub.AbstractStub<UpdateServiceFutureStub> {
    private UpdateServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UpdateServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected UpdateServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UpdateServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<kill.me.palas.grpc.UpdateServiceOuterClass.UpdateResponse> update(
        kill.me.palas.grpc.UpdateServiceOuterClass.UpdateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_UPDATE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final UpdateServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(UpdateServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_UPDATE:
          serviceImpl.update((kill.me.palas.grpc.UpdateServiceOuterClass.UpdateRequest) request,
              (io.grpc.stub.StreamObserver<kill.me.palas.grpc.UpdateServiceOuterClass.UpdateResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class UpdateServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UpdateServiceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return kill.me.palas.grpc.UpdateServiceOuterClass.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("UpdateService");
    }
  }

  private static final class UpdateServiceFileDescriptorSupplier
      extends UpdateServiceBaseDescriptorSupplier {
    UpdateServiceFileDescriptorSupplier() {}
  }

  private static final class UpdateServiceMethodDescriptorSupplier
      extends UpdateServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    UpdateServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (UpdateServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UpdateServiceFileDescriptorSupplier())
              .addMethod(getUpdateMethod())
              .build();
        }
      }
    }
    return result;
  }
}
