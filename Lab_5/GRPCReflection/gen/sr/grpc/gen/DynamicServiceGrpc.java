package sr.grpc.gen;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.62.2)",
    comments = "Source: person.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class DynamicServiceGrpc {

  private DynamicServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "DynamicService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sr.grpc.gen.Person,
      sr.grpc.gen.Person> getAddPersonMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddPerson",
      requestType = sr.grpc.gen.Person.class,
      responseType = sr.grpc.gen.Person.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sr.grpc.gen.Person,
      sr.grpc.gen.Person> getAddPersonMethod() {
    io.grpc.MethodDescriptor<sr.grpc.gen.Person, sr.grpc.gen.Person> getAddPersonMethod;
    if ((getAddPersonMethod = DynamicServiceGrpc.getAddPersonMethod) == null) {
      synchronized (DynamicServiceGrpc.class) {
        if ((getAddPersonMethod = DynamicServiceGrpc.getAddPersonMethod) == null) {
          DynamicServiceGrpc.getAddPersonMethod = getAddPersonMethod =
              io.grpc.MethodDescriptor.<sr.grpc.gen.Person, sr.grpc.gen.Person>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddPerson"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.Person.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.Person.getDefaultInstance()))
              .setSchemaDescriptor(new DynamicServiceMethodDescriptorSupplier("AddPerson"))
              .build();
        }
      }
    }
    return getAddPersonMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      sr.grpc.gen.Person> getGetPersonListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetPersonList",
      requestType = com.google.protobuf.Empty.class,
      responseType = sr.grpc.gen.Person.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      sr.grpc.gen.Person> getGetPersonListMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, sr.grpc.gen.Person> getGetPersonListMethod;
    if ((getGetPersonListMethod = DynamicServiceGrpc.getGetPersonListMethod) == null) {
      synchronized (DynamicServiceGrpc.class) {
        if ((getGetPersonListMethod = DynamicServiceGrpc.getGetPersonListMethod) == null) {
          DynamicServiceGrpc.getGetPersonListMethod = getGetPersonListMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, sr.grpc.gen.Person>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetPersonList"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.Person.getDefaultInstance()))
              .setSchemaDescriptor(new DynamicServiceMethodDescriptorSupplier("GetPersonList"))
              .build();
        }
      }
    }
    return getGetPersonListMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sr.grpc.gen.PersonAgeUpdate,
      sr.grpc.gen.Person> getUpdatePersonAgeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdatePersonAge",
      requestType = sr.grpc.gen.PersonAgeUpdate.class,
      responseType = sr.grpc.gen.Person.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sr.grpc.gen.PersonAgeUpdate,
      sr.grpc.gen.Person> getUpdatePersonAgeMethod() {
    io.grpc.MethodDescriptor<sr.grpc.gen.PersonAgeUpdate, sr.grpc.gen.Person> getUpdatePersonAgeMethod;
    if ((getUpdatePersonAgeMethod = DynamicServiceGrpc.getUpdatePersonAgeMethod) == null) {
      synchronized (DynamicServiceGrpc.class) {
        if ((getUpdatePersonAgeMethod = DynamicServiceGrpc.getUpdatePersonAgeMethod) == null) {
          DynamicServiceGrpc.getUpdatePersonAgeMethod = getUpdatePersonAgeMethod =
              io.grpc.MethodDescriptor.<sr.grpc.gen.PersonAgeUpdate, sr.grpc.gen.Person>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdatePersonAge"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.PersonAgeUpdate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.Person.getDefaultInstance()))
              .setSchemaDescriptor(new DynamicServiceMethodDescriptorSupplier("UpdatePersonAge"))
              .build();
        }
      }
    }
    return getUpdatePersonAgeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DynamicServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DynamicServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DynamicServiceStub>() {
        @java.lang.Override
        public DynamicServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DynamicServiceStub(channel, callOptions);
        }
      };
    return DynamicServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DynamicServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DynamicServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DynamicServiceBlockingStub>() {
        @java.lang.Override
        public DynamicServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DynamicServiceBlockingStub(channel, callOptions);
        }
      };
    return DynamicServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DynamicServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DynamicServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DynamicServiceFutureStub>() {
        @java.lang.Override
        public DynamicServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DynamicServiceFutureStub(channel, callOptions);
        }
      };
    return DynamicServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void addPerson(sr.grpc.gen.Person request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.Person> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddPersonMethod(), responseObserver);
    }

    /**
     */
    default void getPersonList(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.Person> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetPersonListMethod(), responseObserver);
    }

    /**
     */
    default void updatePersonAge(sr.grpc.gen.PersonAgeUpdate request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.Person> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdatePersonAgeMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service DynamicService.
   */
  public static abstract class DynamicServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return DynamicServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service DynamicService.
   */
  public static final class DynamicServiceStub
      extends io.grpc.stub.AbstractAsyncStub<DynamicServiceStub> {
    private DynamicServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DynamicServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DynamicServiceStub(channel, callOptions);
    }

    /**
     */
    public void addPerson(sr.grpc.gen.Person request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.Person> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddPersonMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getPersonList(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.Person> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getGetPersonListMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updatePersonAge(sr.grpc.gen.PersonAgeUpdate request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.Person> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdatePersonAgeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service DynamicService.
   */
  public static final class DynamicServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<DynamicServiceBlockingStub> {
    private DynamicServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DynamicServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DynamicServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public sr.grpc.gen.Person addPerson(sr.grpc.gen.Person request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddPersonMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<sr.grpc.gen.Person> getPersonList(
        com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getGetPersonListMethod(), getCallOptions(), request);
    }

    /**
     */
    public sr.grpc.gen.Person updatePersonAge(sr.grpc.gen.PersonAgeUpdate request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdatePersonAgeMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service DynamicService.
   */
  public static final class DynamicServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<DynamicServiceFutureStub> {
    private DynamicServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DynamicServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DynamicServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sr.grpc.gen.Person> addPerson(
        sr.grpc.gen.Person request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddPersonMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sr.grpc.gen.Person> updatePersonAge(
        sr.grpc.gen.PersonAgeUpdate request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdatePersonAgeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD_PERSON = 0;
  private static final int METHODID_GET_PERSON_LIST = 1;
  private static final int METHODID_UPDATE_PERSON_AGE = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADD_PERSON:
          serviceImpl.addPerson((sr.grpc.gen.Person) request,
              (io.grpc.stub.StreamObserver<sr.grpc.gen.Person>) responseObserver);
          break;
        case METHODID_GET_PERSON_LIST:
          serviceImpl.getPersonList((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<sr.grpc.gen.Person>) responseObserver);
          break;
        case METHODID_UPDATE_PERSON_AGE:
          serviceImpl.updatePersonAge((sr.grpc.gen.PersonAgeUpdate) request,
              (io.grpc.stub.StreamObserver<sr.grpc.gen.Person>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getAddPersonMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sr.grpc.gen.Person,
              sr.grpc.gen.Person>(
                service, METHODID_ADD_PERSON)))
        .addMethod(
          getGetPersonListMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              com.google.protobuf.Empty,
              sr.grpc.gen.Person>(
                service, METHODID_GET_PERSON_LIST)))
        .addMethod(
          getUpdatePersonAgeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sr.grpc.gen.PersonAgeUpdate,
              sr.grpc.gen.Person>(
                service, METHODID_UPDATE_PERSON_AGE)))
        .build();
  }

  private static abstract class DynamicServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DynamicServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return sr.grpc.gen.DynamicServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DynamicService");
    }
  }

  private static final class DynamicServiceFileDescriptorSupplier
      extends DynamicServiceBaseDescriptorSupplier {
    DynamicServiceFileDescriptorSupplier() {}
  }

  private static final class DynamicServiceMethodDescriptorSupplier
      extends DynamicServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    DynamicServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (DynamicServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DynamicServiceFileDescriptorSupplier())
              .addMethod(getAddPersonMethod())
              .addMethod(getGetPersonListMethod())
              .addMethod(getUpdatePersonAgeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
