package br.com.jonathanzanella.myexpenses.models;

import br.com.jonathanzanella.myexpenses.server.UnsyncModelApi;

/**
 * Created by jzanella on 6/6/16.
 */
public interface UnsyncModel {
    long getId();
    String getServerId();
    void setServerId(String serverId);
    long getCreatedAt();
    void setCreatedAt(long createdAt);
    long getUpdatedAt();
    void setUpdatedAt(long updatedAt);
    String getData();
    void setSync(boolean sync);
    void save();

    UnsyncModelApi getServerApi();
}