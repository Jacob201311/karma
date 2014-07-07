/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package com.duitang.service.l2;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public interface L2Service {
  public static final org.apache.avro.Protocol PROTOCOL = org.apache.avro.Protocol.parse("{\"protocol\":\"L2Service\",\"namespace\":\"com.duitang.service.l2\",\"types\":[],\"messages\":{\"cat_setstring\":{\"request\":[{\"name\":\"key\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"value\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"ttl\",\"type\":\"int\"}],\"response\":\"boolean\"},\"cat_addstring\":{\"request\":[{\"name\":\"key\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"value\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"ttl\",\"type\":\"int\"}],\"response\":\"boolean\"},\"cat_incr\":{\"request\":[{\"name\":\"key\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"delta\",\"type\":\"long\"}],\"response\":\"long\"},\"cat_getstring\":{\"request\":[{\"name\":\"key\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}],\"response\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},\"cat_delstring\":{\"request\":[{\"name\":\"key\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}],\"response\":\"boolean\"},\"cat_mgetstring\":{\"request\":[{\"name\":\"keys\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}],\"response\":{\"type\":\"map\",\"values\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"avro.java.string\":\"String\"}},\"session_getsession\":{\"request\":[{\"name\":\"sessionid\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}],\"response\":{\"type\":\"map\",\"values\":[{\"type\":\"string\",\"avro.java.string\":\"String\"},\"null\",\"long\",\"int\",\"float\",\"double\"],\"avro.java.string\":\"String\"}},\"session_get\":{\"request\":[{\"name\":\"sessionid\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}],\"response\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},\"session_set\":{\"request\":[{\"name\":\"sessionid\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"value\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}],\"response\":\"boolean\"},\"session_expire\":{\"request\":[{\"name\":\"sessionid\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"expiryage\",\"type\":\"int\"}],\"response\":\"long\"},\"session_delete\":{\"request\":[{\"name\":\"sessionid\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}],\"response\":\"long\"},\"session_genId\":{\"request\":[],\"response\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}}}");
  boolean cat_setstring(java.lang.String key, java.lang.String value, int ttl) throws org.apache.avro.AvroRemoteException;
  boolean cat_addstring(java.lang.String key, java.lang.String value, int ttl) throws org.apache.avro.AvroRemoteException;
  long cat_incr(java.lang.String key, long delta) throws org.apache.avro.AvroRemoteException;
  java.lang.String cat_getstring(java.lang.String key) throws org.apache.avro.AvroRemoteException;
  boolean cat_delstring(java.lang.String key) throws org.apache.avro.AvroRemoteException;
  java.util.Map<java.lang.String,java.lang.String> cat_mgetstring(java.lang.String keys) throws org.apache.avro.AvroRemoteException;
  java.util.Map<java.lang.String,java.lang.Object> session_getsession(java.lang.String sessionid) throws org.apache.avro.AvroRemoteException;
  java.lang.String session_get(java.lang.String sessionid) throws org.apache.avro.AvroRemoteException;
  boolean session_set(java.lang.String sessionid, java.lang.String value) throws org.apache.avro.AvroRemoteException;
  long session_expire(java.lang.String sessionid, int expiryage) throws org.apache.avro.AvroRemoteException;
  long session_delete(java.lang.String sessionid) throws org.apache.avro.AvroRemoteException;
  java.lang.String session_genId() throws org.apache.avro.AvroRemoteException;

  @SuppressWarnings("all")
  public interface Callback extends L2Service {
    public static final org.apache.avro.Protocol PROTOCOL = com.duitang.service.l2.L2Service.PROTOCOL;
    void cat_setstring(java.lang.String key, java.lang.String value, int ttl, org.apache.avro.ipc.Callback<java.lang.Boolean> callback) throws java.io.IOException;
    void cat_addstring(java.lang.String key, java.lang.String value, int ttl, org.apache.avro.ipc.Callback<java.lang.Boolean> callback) throws java.io.IOException;
    void cat_incr(java.lang.String key, long delta, org.apache.avro.ipc.Callback<java.lang.Long> callback) throws java.io.IOException;
    void cat_getstring(java.lang.String key, org.apache.avro.ipc.Callback<java.lang.String> callback) throws java.io.IOException;
    void cat_delstring(java.lang.String key, org.apache.avro.ipc.Callback<java.lang.Boolean> callback) throws java.io.IOException;
    void cat_mgetstring(java.lang.String keys, org.apache.avro.ipc.Callback<java.util.Map<java.lang.String,java.lang.String>> callback) throws java.io.IOException;
    void session_getsession(java.lang.String sessionid, org.apache.avro.ipc.Callback<java.util.Map<java.lang.String,java.lang.Object>> callback) throws java.io.IOException;
    void session_get(java.lang.String sessionid, org.apache.avro.ipc.Callback<java.lang.String> callback) throws java.io.IOException;
    void session_set(java.lang.String sessionid, java.lang.String value, org.apache.avro.ipc.Callback<java.lang.Boolean> callback) throws java.io.IOException;
    void session_expire(java.lang.String sessionid, int expiryage, org.apache.avro.ipc.Callback<java.lang.Long> callback) throws java.io.IOException;
    void session_delete(java.lang.String sessionid, org.apache.avro.ipc.Callback<java.lang.Long> callback) throws java.io.IOException;
    void session_genId(org.apache.avro.ipc.Callback<java.lang.String> callback) throws java.io.IOException;
  }
}