package mySampleApplication.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

public interface MySampleApplicationServiceAsync {
    void getManagersList(AsyncCallback<List<Manager>> callback);
}
