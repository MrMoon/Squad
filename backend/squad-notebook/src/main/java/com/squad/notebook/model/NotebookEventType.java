/**
 * Autogenerated by Avro
 * <p>
 * DO NOT EDIT DIRECTLY
 */
package com.squad.notebook.model;

@org.apache.avro.specific.AvroGenerated
public enum NotebookEventType implements org.apache.avro.generic.GenericEnumSymbol<NotebookEventType> {
    NOTEBOOK_CREATED, NOTEBOOK_UPDATED, NOTEBOOK_DELETED;
    public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"enum\",\"name\":\"NotebookEventType\",\"namespace\":\"com.squad.notebook.model\",\"symbols\":[\"NOTEBOOK_CREATED\",\"NOTEBOOK_UPDATED\",\"NOTEBOOK_DELETED\"]}");

    public static org.apache.avro.Schema getClassSchema() {
        return SCHEMA$;
    }

    public org.apache.avro.Schema getSchema() {
        return SCHEMA$;
    }
}
