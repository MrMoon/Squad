/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.squad.post.model;
@org.apache.avro.specific.AvroGenerated
public enum PostType implements org.apache.avro.generic.GenericEnumSymbol<PostType> {
  NORMAL, DOCUMENT, QNA, ANNOUNCEMENTS, EVENTS  ;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"enum\",\"name\":\"PostType\",\"namespace\":\"com.squad.post.model\",\"symbols\":[\"NORMAL\",\"DOCUMENT\",\"QNA\",\"ANNOUNCEMENTS\",\"EVENTS\"]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
}
