package com.github.brotherlogic.graphplotter;

import java.util.concurrent.TimeUnit;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import recordcollection.RecordCollectionServiceGrpc;
import recordcollection.Recordcollection.GetRecordsResponse;
import recordcollection.Recordcollection;
import githubcard.GithubGrpc;
import githubcard.Githubcard.GetAllRequest;
import githubcard.Githubcard;

public class Getter {

    String host;
    int port;

    public Getter(String h, int p) {
        host = h;
        port = p;
    }

    public GetRecordsResponse getRecords() throws Exception {
        GetRecordsResponse response = null;
        if (host != null) {
            ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build();
            RecordCollectionServiceGrpc.RecordCollectionServiceBlockingStub client = RecordCollectionServiceGrpc.newBlockingStub(channel);
            response = client.withMaxInboundMessageSize(1024*1024*1024).withDeadlineAfter(5, TimeUnit.SECONDS).getRecords(Recordcollection.GetRecordsRequest.newBuilder().build());
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        }
        return response;
    }

    public String getLatestIssue() throws Exception {
        String response = "No Response";
        if (host != null) {
            ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build();

            GithubGrpc.GithubBlockingStub client = GithubGrpc.newBlockingStub(channel);
             response = client.withDeadlineAfter(30, TimeUnit.SECONDS).getAll(Githubcard.GetAllRequest.newBuilder().setLatestOnly(true).build()).getIssues(0).getTitle();
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        }
        return response;
    }
}
