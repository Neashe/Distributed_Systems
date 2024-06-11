//package grpc.server;
//
//import com.google.protobuf.Empty;
//import io.grpc.Status;
//import io.grpc.stub.StreamObserver;
//import sr.grpc.gen.DynamicServiceGrpc;
//import sr.grpc.gen.Person;
//import sr.grpc.gen.PersonAgeUpdate;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DynamicServiceImpl extends DynamicServiceGrpc.DynamicServiceImplBase {
//    private List<Person> personList = new ArrayList<>();
//
//    @Override
//    public void addPerson(Person request, StreamObserver<Person> responseObserver) {
//        personList.add(request);
//        responseObserver.onNext(request);
//        responseObserver.onCompleted();
//        System.out.println("PERSON ADDED: "+ request);
//    }
//
//    @Override
//    public void getPersonList(Empty request, StreamObserver<Person> responseObserver) {
//        for (Person person : personList) {
//            responseObserver.onNext(person);
//        }
//        responseObserver.onCompleted();
//        System.out.println("ACTION: listing objects ("+personList.size()+")");
//    }
//    @Override
//    public void updatePersonAge(PersonAgeUpdate request, StreamObserver<Person> responseObserver) {
//        for (int i = 0; i < personList.size(); i++) {
//            Person person = personList.get(i);
//            if (person.getName().equals(request.getName())) {
//                System.out.println("UPDATE BEFORE: " + person);
//                // Aktualizacja wieku osoby w liście personList
//                Person updatedPerson = person.toBuilder().setAge(request.getNewAge()).build();
//                personList.set(i, updatedPerson);
//                responseObserver.onNext(updatedPerson);
//                responseObserver.onCompleted();
//                System.out.println("UPDATE AFTER:" + updatedPerson);
//                return;
//            }
//        }
//        responseObserver.onError(Status.NOT_FOUND.asRuntimeException());
//    }
//
//}

package grpc.server;

import com.google.protobuf.Empty;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import sr.grpc.gen.DynamicServiceGrpc;
import sr.grpc.gen.Person;
import sr.grpc.gen.PersonAgeUpdate;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class DynamicServiceImpl extends DynamicServiceGrpc.DynamicServiceImplBase {
    private final List<Person> personList = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void addPerson(Person request, StreamObserver<Person> responseObserver) {
        synchronized (personList) {
            personList.add(request);
        }
        responseObserver.onNext(request);
        responseObserver.onCompleted();
        System.out.println("PERSON ADDED: " + request);
    }

    @Override
    public void getPersonList(Empty request, StreamObserver<Person> responseObserver) {
        synchronized (personList) {
            for (Person person : personList) {
                responseObserver.onNext(person);
            }
        }
        responseObserver.onCompleted();
        System.out.println("ACTION: listing objects (" + personList.size() + ")");
    }

    @Override
    public void updatePersonAge(PersonAgeUpdate request, StreamObserver<Person> responseObserver) {
        synchronized (personList) {
            for (int i = 0; i < personList.size(); i++) {
                Person person = personList.get(i);
                if (person.getName().equals(request.getName())) {
                    System.out.println("UPDATE BEFORE: " + person);
                    Person updatedPerson = person.toBuilder().setAge(request.getNewAge()).build();
                    personList.set(i, updatedPerson);
                    responseObserver.onNext(updatedPerson);
                    responseObserver.onCompleted();
                    System.out.println("UPDATE AFTER: " + updatedPerson);
                    return;
                }
            }
        }
        responseObserver.onError(Status.NOT_FOUND.asRuntimeException());
    }
}
