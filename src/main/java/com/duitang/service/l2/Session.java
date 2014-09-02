/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package com.duitang.service.l2;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public interface Session {
  public static final org.apache.avro.Protocol PROTOCOL = org.apache.avro.Protocol.parse("{\"protocol\":\"Session\",\"namespace\":\"com.duitang.service.l2\",\"types\":[],\"messages\":{\"session_getsession\":{\"request\":[{\"name\":\"sessionid\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}],\"response\":{\"type\":\"map\",\"values\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"},\"long\",\"int\",\"float\",\"double\"],\"avro.java.string\":\"String\"}},\"session_setsession\":{\"request\":[{\"name\":\"sessionid\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"sessiondata\",\"type\":{\"type\":\"map\",\"values\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"},\"long\",\"int\",\"float\",\"double\"],\"avro.java.string\":\"String\"}}],\"response\":\"boolean\"},\"session_get\":{\"request\":[{\"name\":\"sessionid\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}],\"response\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},\"session_set\":{\"request\":[{\"name\":\"sessionid\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"value\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}],\"response\":\"boolean\"},\"session_expire\":{\"request\":[{\"name\":\"sessionid\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"expiryage\",\"type\":\"int\"}],\"response\":\"long\"},\"session_delete\":{\"request\":[{\"name\":\"sessionid\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}],\"response\":\"long\"},\"session_genId\":{\"request\":[],\"response\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}}}");
  java.util.Map<java.lang.String,java.lang.Object> session_getsession(java.lang.String sessionid) throws org.apache.avro.AvroRemoteException;
  boolean session_setsession(java.lang.String sessionid, java.util.Map<java.lang.String,java.lang.Object> sessiondata) throws org.apache.avro.AvroRemoteException;
  java.lang.String session_get(java.lang.String sessionid) throws org.apache.avro.AvroRemoteException;
  boolean session_set(java.lang.String sessionid, java.lang.String value) throws org.apache.avro.AvroRemoteException;
  long session_expire(java.lang.String sessionid, int expiryage) throws org.apache.avro.AvroRemoteException;
  long session_delete(java.lang.String sessionid) throws org.apache.avro.AvroRemoteException;
  java.lang.String session_genId() throws org.apache.avro.AvroRemoteException;

  @SuppressWarnings("all")
  public interface Callback extends Session {
    public static final org.apache.avro.Protocol PROTOCOL = com.duitang.service.l2.Session.PROTOCOL;
    void session_getsession(java.lang.String sessionid, org.apache.avro.ipc.Callback<java.util.Map<java.lang.String,java.lang.Object>> callback) throws java.io.IOException;
    void session_setsession(java.lang.String sessionid, java.util.Map<java.lang.String,java.lang.Object> sessiondata, org.apache.avro.ipc.Callback<java.lang.Boolean> callback) throws java.io.IOException;
    void session_get(java.lang.String sessionid, org.apache.avro.ipc.Callback<java.lang.String> callback) throws java.io.IOException;
    void session_set(java.lang.String sessionid, java.lang.String value, org.apache.avro.ipc.Callback<java.lang.Boolean> callback) throws java.io.IOException;
    void session_expire(java.lang.String sessionid, int expiryage, org.apache.avro.ipc.Callback<java.lang.Long> callback) throws java.io.IOException;
    void session_delete(java.lang.String sessionid, org.apache.avro.ipc.Callback<java.lang.Long> callback) throws java.io.IOException;
    void session_genId(org.apache.avro.ipc.Callback<java.lang.String> callback) throws java.io.IOException;
  }
}