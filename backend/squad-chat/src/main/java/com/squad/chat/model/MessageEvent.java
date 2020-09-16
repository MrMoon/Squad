/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.squad.chat.model;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class MessageEvent extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -7820982507572284218L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"MessageEvent\",\"namespace\":\"com.squad.chat.model\",\"fields\":[{\"name\":\"eventId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"message\",\"type\":{\"type\":\"record\",\"name\":\"Message\",\"fields\":[{\"name\":\"messageId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"userId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"senderId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"roomId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"text\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]}},{\"name\":\"MessageEventType\",\"type\":{\"type\":\"enum\",\"name\":\"MessageEventType\",\"symbols\":[\"MESSAGE_SEND\",\"MESSAGE_NOFITICATION\"]}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<MessageEvent> ENCODER =
      new BinaryMessageEncoder<MessageEvent>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<MessageEvent> DECODER =
      new BinaryMessageDecoder<MessageEvent>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<MessageEvent> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<MessageEvent> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<MessageEvent> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<MessageEvent>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this MessageEvent to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a MessageEvent from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a MessageEvent instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static MessageEvent fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.String eventId;
  @Deprecated public com.squad.chat.model.Message message;
  @Deprecated public com.squad.chat.model.MessageEventType MessageEventType;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public MessageEvent() {}

  /**
   * All-args constructor.
   * @param eventId The new value for eventId
   * @param message The new value for message
   * @param MessageEventType The new value for MessageEventType
   */
  public MessageEvent(java.lang.String eventId, com.squad.chat.model.Message message, com.squad.chat.model.MessageEventType MessageEventType) {
    this.eventId = eventId;
    this.message = message;
    this.MessageEventType = MessageEventType;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return eventId;
    case 1: return message;
    case 2: return MessageEventType;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: eventId = (java.lang.String)value$; break;
    case 1: message = (com.squad.chat.model.Message)value$; break;
    case 2: MessageEventType = (com.squad.chat.model.MessageEventType)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'eventId' field.
   * @return The value of the 'eventId' field.
   */
  public java.lang.String getEventId() {
    return eventId;
  }


  /**
   * Sets the value of the 'eventId' field.
   * @param value the value to set.
   */
  public void setEventId(java.lang.String value) {
    this.eventId = value;
  }

  /**
   * Gets the value of the 'message' field.
   * @return The value of the 'message' field.
   */
  public com.squad.chat.model.Message getMessage() {
    return message;
  }


  /**
   * Sets the value of the 'message' field.
   * @param value the value to set.
   */
  public void setMessage(com.squad.chat.model.Message value) {
    this.message = value;
  }

  /**
   * Gets the value of the 'MessageEventType' field.
   * @return The value of the 'MessageEventType' field.
   */
  public com.squad.chat.model.MessageEventType getMessageEventType() {
    return MessageEventType;
  }


  /**
   * Sets the value of the 'MessageEventType' field.
   * @param value the value to set.
   */
  public void setMessageEventType(com.squad.chat.model.MessageEventType value) {
    this.MessageEventType = value;
  }

  /**
   * Creates a new MessageEvent RecordBuilder.
   * @return A new MessageEvent RecordBuilder
   */
  public static com.squad.chat.model.MessageEvent.Builder newBuilder() {
    return new com.squad.chat.model.MessageEvent.Builder();
  }

  /**
   * Creates a new MessageEvent RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new MessageEvent RecordBuilder
   */
  public static com.squad.chat.model.MessageEvent.Builder newBuilder(com.squad.chat.model.MessageEvent.Builder other) {
    if (other == null) {
      return new com.squad.chat.model.MessageEvent.Builder();
    } else {
      return new com.squad.chat.model.MessageEvent.Builder(other);
    }
  }

  /**
   * Creates a new MessageEvent RecordBuilder by copying an existing MessageEvent instance.
   * @param other The existing instance to copy.
   * @return A new MessageEvent RecordBuilder
   */
  public static com.squad.chat.model.MessageEvent.Builder newBuilder(com.squad.chat.model.MessageEvent other) {
    if (other == null) {
      return new com.squad.chat.model.MessageEvent.Builder();
    } else {
      return new com.squad.chat.model.MessageEvent.Builder(other);
    }
  }

  /**
   * RecordBuilder for MessageEvent instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<MessageEvent>
    implements org.apache.avro.data.RecordBuilder<MessageEvent> {

    private java.lang.String eventId;
    private com.squad.chat.model.Message message;
    private com.squad.chat.model.Message.Builder messageBuilder;
    private com.squad.chat.model.MessageEventType MessageEventType;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.squad.chat.model.MessageEvent.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.eventId)) {
        this.eventId = data().deepCopy(fields()[0].schema(), other.eventId);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.message)) {
        this.message = data().deepCopy(fields()[1].schema(), other.message);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (other.hasMessageBuilder()) {
        this.messageBuilder = com.squad.chat.model.Message.newBuilder(other.getMessageBuilder());
      }
      if (isValidValue(fields()[2], other.MessageEventType)) {
        this.MessageEventType = data().deepCopy(fields()[2].schema(), other.MessageEventType);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
    }

    /**
     * Creates a Builder by copying an existing MessageEvent instance
     * @param other The existing instance to copy.
     */
    private Builder(com.squad.chat.model.MessageEvent other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.eventId)) {
        this.eventId = data().deepCopy(fields()[0].schema(), other.eventId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.message)) {
        this.message = data().deepCopy(fields()[1].schema(), other.message);
        fieldSetFlags()[1] = true;
      }
      this.messageBuilder = null;
      if (isValidValue(fields()[2], other.MessageEventType)) {
        this.MessageEventType = data().deepCopy(fields()[2].schema(), other.MessageEventType);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'eventId' field.
      * @return The value.
      */
    public java.lang.String getEventId() {
      return eventId;
    }


    /**
      * Sets the value of the 'eventId' field.
      * @param value The value of 'eventId'.
      * @return This builder.
      */
    public com.squad.chat.model.MessageEvent.Builder setEventId(java.lang.String value) {
      validate(fields()[0], value);
      this.eventId = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'eventId' field has been set.
      * @return True if the 'eventId' field has been set, false otherwise.
      */
    public boolean hasEventId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'eventId' field.
      * @return This builder.
      */
    public com.squad.chat.model.MessageEvent.Builder clearEventId() {
      eventId = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'message' field.
      * @return The value.
      */
    public com.squad.chat.model.Message getMessage() {
      return message;
    }


    /**
      * Sets the value of the 'message' field.
      * @param value The value of 'message'.
      * @return This builder.
      */
    public com.squad.chat.model.MessageEvent.Builder setMessage(com.squad.chat.model.Message value) {
      validate(fields()[1], value);
      this.messageBuilder = null;
      this.message = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'message' field has been set.
      * @return True if the 'message' field has been set, false otherwise.
      */
    public boolean hasMessage() {
      return fieldSetFlags()[1];
    }

    /**
     * Gets the Builder instance for the 'message' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public com.squad.chat.model.Message.Builder getMessageBuilder() {
      if (messageBuilder == null) {
        if (hasMessage()) {
          setMessageBuilder(com.squad.chat.model.Message.newBuilder(message));
        } else {
          setMessageBuilder(com.squad.chat.model.Message.newBuilder());
        }
      }
      return messageBuilder;
    }

    /**
     * Sets the Builder instance for the 'message' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */
    public com.squad.chat.model.MessageEvent.Builder setMessageBuilder(com.squad.chat.model.Message.Builder value) {
      clearMessage();
      messageBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'message' field has an active Builder instance
     * @return True if the 'message' field has an active Builder instance
     */
    public boolean hasMessageBuilder() {
      return messageBuilder != null;
    }

    /**
      * Clears the value of the 'message' field.
      * @return This builder.
      */
    public com.squad.chat.model.MessageEvent.Builder clearMessage() {
      message = null;
      messageBuilder = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'MessageEventType' field.
      * @return The value.
      */
    public com.squad.chat.model.MessageEventType getMessageEventType() {
      return MessageEventType;
    }


    /**
      * Sets the value of the 'MessageEventType' field.
      * @param value The value of 'MessageEventType'.
      * @return This builder.
      */
    public com.squad.chat.model.MessageEvent.Builder setMessageEventType(com.squad.chat.model.MessageEventType value) {
      validate(fields()[2], value);
      this.MessageEventType = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'MessageEventType' field has been set.
      * @return True if the 'MessageEventType' field has been set, false otherwise.
      */
    public boolean hasMessageEventType() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'MessageEventType' field.
      * @return This builder.
      */
    public com.squad.chat.model.MessageEvent.Builder clearMessageEventType() {
      MessageEventType = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public MessageEvent build() {
      try {
        MessageEvent record = new MessageEvent();
        record.eventId = fieldSetFlags()[0] ? this.eventId : (java.lang.String) defaultValue(fields()[0]);
        if (messageBuilder != null) {
          try {
            record.message = this.messageBuilder.build();
          } catch (org.apache.avro.AvroMissingFieldException e) {
            e.addParentField(record.getSchema().getField("message"));
            throw e;
          }
        } else {
          record.message = fieldSetFlags()[1] ? this.message : (com.squad.chat.model.Message) defaultValue(fields()[1]);
        }
        record.MessageEventType = fieldSetFlags()[2] ? this.MessageEventType : (com.squad.chat.model.MessageEventType) defaultValue(fields()[2]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<MessageEvent>
    WRITER$ = (org.apache.avro.io.DatumWriter<MessageEvent>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<MessageEvent>
    READER$ = (org.apache.avro.io.DatumReader<MessageEvent>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.eventId);

    this.message.customEncode(out);

    out.writeEnum(this.MessageEventType.ordinal());

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.eventId = in.readString();

      if (this.message == null) {
        this.message = new com.squad.chat.model.Message();
      }
      this.message.customDecode(in);

      this.MessageEventType = com.squad.chat.model.MessageEventType.values()[in.readEnum()];

    } else {
      for (int i = 0; i < 3; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.eventId = in.readString();
          break;

        case 1:
          if (this.message == null) {
            this.message = new com.squad.chat.model.Message();
          }
          this.message.customDecode(in);
          break;

        case 2:
          this.MessageEventType = com.squad.chat.model.MessageEventType.values()[in.readEnum()];
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










