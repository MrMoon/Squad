/**
 * Autogenerated by Avro
 * <p>
 * DO NOT EDIT DIRECTLY
 */
package com.squad.notebook.model;

import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.SchemaStore;
import org.apache.avro.specific.SpecificData;

@org.apache.avro.specific.AvroGenerated
public class NoteEvent extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"NoteEvent\",\"namespace\":\"com.squad.notebook.model\",\"fields\":[{\"name\":\"eventId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"note\",\"type\":{\"type\":\"record\",\"name\":\"Note\",\"fields\":[{\"name\":\"noteId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"userId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"name\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"groupId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"text\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"isPublic\",\"type\":\"boolean\"},{\"name\":\"isNotifiable\",\"type\":\"boolean\"},{\"name\":\"NoteType\",\"type\":{\"type\":\"enum\",\"name\":\"NoteType\",\"symbols\":[\"NOTE\",\"TASK\"]}}]}},{\"name\":\"NoteEventType\",\"type\":{\"type\":\"enum\",\"name\":\"NoteEventType\",\"symbols\":[\"NOTE_CREATED\",\"NOTE_UPDATED\",\"NOTE_DELETED\",\"NOTE_NOTIFICATION\"]}}]}");
  private static final long serialVersionUID = 7215480634495193604L;
  private static SpecificData MODEL$ = new SpecificData();
  private static final BinaryMessageEncoder<NoteEvent> ENCODER =
          new BinaryMessageEncoder<NoteEvent>(MODEL$ , SCHEMA$);
  private static final BinaryMessageDecoder<NoteEvent> DECODER =
          new BinaryMessageDecoder<NoteEvent>(MODEL$ , SCHEMA$);
  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<NoteEvent>
          WRITER$ = (org.apache.avro.io.DatumWriter<NoteEvent>) MODEL$.createDatumWriter(SCHEMA$);
  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<NoteEvent>
          READER$ = (org.apache.avro.io.DatumReader<NoteEvent>) MODEL$.createDatumReader(SCHEMA$);
  @Deprecated
  public java.lang.String eventId;
  @Deprecated
  public com.squad.notebook.model.Note note;
  @Deprecated
  public com.squad.notebook.model.NoteEventType NoteEventType;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public NoteEvent() {
  }

  /**
   * All-args constructor.
   * @param eventId The new value for eventId
   * @param note The new value for note
   * @param NoteEventType The new value for NoteEventType
   */
  public NoteEvent(java.lang.String eventId , com.squad.notebook.model.Note note , com.squad.notebook.model.NoteEventType NoteEventType) {
    this.eventId = eventId;
    this.note = note;
    this.NoteEventType = NoteEventType;
  }

  public static org.apache.avro.Schema getClassSchema() {
    return SCHEMA$;
  }

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<NoteEvent> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<NoteEvent> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<NoteEvent> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<NoteEvent>(MODEL$ , SCHEMA$ , resolver);
  }

  /**
   * Deserializes a NoteEvent from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a NoteEvent instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static NoteEvent fromByteBuffer(
          java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /**
   * Creates a new NoteEvent RecordBuilder.
   * @return A new NoteEvent RecordBuilder
   */
  public static com.squad.notebook.model.NoteEvent.Builder newBuilder() {
    return new com.squad.notebook.model.NoteEvent.Builder();
  }

  /**
   * Creates a new NoteEvent RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new NoteEvent RecordBuilder
   */
  public static com.squad.notebook.model.NoteEvent.Builder newBuilder(com.squad.notebook.model.NoteEvent.Builder other) {
    if (other == null) {
      return new com.squad.notebook.model.NoteEvent.Builder();
    } else {
      return new com.squad.notebook.model.NoteEvent.Builder(other);
    }
  }

  /**
   * Creates a new NoteEvent RecordBuilder by copying an existing NoteEvent instance.
   * @param other The existing instance to copy.
   * @return A new NoteEvent RecordBuilder
   */
  public static com.squad.notebook.model.NoteEvent.Builder newBuilder(com.squad.notebook.model.NoteEvent other) {
    if (other == null) {
      return new com.squad.notebook.model.NoteEvent.Builder();
    } else {
      return new com.squad.notebook.model.NoteEvent.Builder(other);
    }
  }

  /**
   * Serializes this NoteEvent to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  public org.apache.avro.specific.SpecificData getSpecificData() {
    return MODEL$;
  }

  public org.apache.avro.Schema getSchema() {
    return SCHEMA$;
  }

  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
      case 0:
        return eventId;
      case 1:
        return note;
      case 2:
        return NoteEventType;
      default:
        throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value = "unchecked")
  public void put(int field$ , java.lang.Object value$) {
    switch (field$) {
      case 0:
        eventId = (java.lang.String) value$;
        break;
      case 1:
        note = (com.squad.notebook.model.Note) value$;
        break;
      case 2:
        NoteEventType = (com.squad.notebook.model.NoteEventType) value$;
        break;
      default:
        throw new org.apache.avro.AvroRuntimeException("Bad index");
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
   * Gets the value of the 'note' field.
   * @return The value of the 'note' field.
   */
  public com.squad.notebook.model.Note getNote() {
    return note;
  }

  /**
   * Sets the value of the 'note' field.
   * @param value the value to set.
   */
  public void setNote(com.squad.notebook.model.Note value) {
    this.note = value;
  }

  /**
   * Gets the value of the 'NoteEventType' field.
   * @return The value of the 'NoteEventType' field.
   */
  public com.squad.notebook.model.NoteEventType getNoteEventType() {
    return NoteEventType;
  }

  /**
   * Sets the value of the 'NoteEventType' field.
   * @param value the value to set.
   */
  public void setNoteEventType(com.squad.notebook.model.NoteEventType value) {
    this.NoteEventType = value;
  }

  @Override
  public void writeExternal(java.io.ObjectOutput out)
          throws java.io.IOException {
    WRITER$.write(this , SpecificData.getEncoder(out));
  }

  @Override
  public void readExternal(java.io.ObjectInput in)
          throws java.io.IOException {
    READER$.read(this , SpecificData.getDecoder(in));
  }

  @Override
  protected boolean hasCustomCoders() {
    return true;
  }

  @Override
  public void customEncode(org.apache.avro.io.Encoder out)
          throws java.io.IOException {
    out.writeString(this.eventId);

    this.note.customEncode(out);

    out.writeEnum(this.NoteEventType.ordinal());

  }

  @Override
  public void customDecode(org.apache.avro.io.ResolvingDecoder in)
          throws java.io.IOException {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.eventId = in.readString();

      if (this.note == null) {
        this.note = new com.squad.notebook.model.Note();
      }
      this.note.customDecode(in);

      this.NoteEventType = com.squad.notebook.model.NoteEventType.values()[in.readEnum()];

    } else {
      for (int i = 0 ; i < 3 ; i++) {
        switch (fieldOrder[i].pos()) {
          case 0:
            this.eventId = in.readString();
            break;

          case 1:
            if (this.note == null) {
              this.note = new com.squad.notebook.model.Note();
            }
            this.note.customDecode(in);
            break;

          case 2:
            this.NoteEventType = com.squad.notebook.model.NoteEventType.values()[in.readEnum()];
            break;

          default:
            throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }

  /**
   * RecordBuilder for NoteEvent instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<NoteEvent>
          implements org.apache.avro.data.RecordBuilder<NoteEvent> {

    private java.lang.String eventId;
    private com.squad.notebook.model.Note note;
    private com.squad.notebook.model.Note.Builder noteBuilder;
    private com.squad.notebook.model.NoteEventType NoteEventType;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.squad.notebook.model.NoteEvent.Builder other) {
      super(other);
      if (isValidValue(fields()[0] , other.eventId)) {
        this.eventId = data().deepCopy(fields()[0].schema() , other.eventId);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1] , other.note)) {
        this.note = data().deepCopy(fields()[1].schema() , other.note);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (other.hasNoteBuilder()) {
        this.noteBuilder = com.squad.notebook.model.Note.newBuilder(other.getNoteBuilder());
      }
      if (isValidValue(fields()[2] , other.NoteEventType)) {
        this.NoteEventType = data().deepCopy(fields()[2].schema() , other.NoteEventType);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
    }

    /**
     * Creates a Builder by copying an existing NoteEvent instance
     * @param other The existing instance to copy.
     */
    private Builder(com.squad.notebook.model.NoteEvent other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0] , other.eventId)) {
        this.eventId = data().deepCopy(fields()[0].schema() , other.eventId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1] , other.note)) {
        this.note = data().deepCopy(fields()[1].schema() , other.note);
        fieldSetFlags()[1] = true;
      }
      this.noteBuilder = null;
      if (isValidValue(fields()[2] , other.NoteEventType)) {
        this.NoteEventType = data().deepCopy(fields()[2].schema() , other.NoteEventType);
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
    public com.squad.notebook.model.NoteEvent.Builder setEventId(java.lang.String value) {
      validate(fields()[0] , value);
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
    public com.squad.notebook.model.NoteEvent.Builder clearEventId() {
      eventId = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
     * Gets the value of the 'note' field.
     * @return The value.
     */
    public com.squad.notebook.model.Note getNote() {
      return note;
    }


    /**
     * Sets the value of the 'note' field.
     * @param value The value of 'note'.
     * @return This builder.
     */
    public com.squad.notebook.model.NoteEvent.Builder setNote(com.squad.notebook.model.Note value) {
      validate(fields()[1] , value);
      this.noteBuilder = null;
      this.note = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
     * Checks whether the 'note' field has been set.
     * @return True if the 'note' field has been set, false otherwise.
     */
    public boolean hasNote() {
      return fieldSetFlags()[1];
    }

    /**
     * Gets the Builder instance for the 'note' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public com.squad.notebook.model.Note.Builder getNoteBuilder() {
      if (noteBuilder == null) {
        if (hasNote()) {
          setNoteBuilder(com.squad.notebook.model.Note.newBuilder(note));
        } else {
          setNoteBuilder(com.squad.notebook.model.Note.newBuilder());
        }
      }
      return noteBuilder;
    }

    /**
     * Sets the Builder instance for the 'note' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */
    public com.squad.notebook.model.NoteEvent.Builder setNoteBuilder(com.squad.notebook.model.Note.Builder value) {
      clearNote();
      noteBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'note' field has an active Builder instance
     * @return True if the 'note' field has an active Builder instance
     */
    public boolean hasNoteBuilder() {
      return noteBuilder != null;
    }

    /**
     * Clears the value of the 'note' field.
     * @return This builder.
     */
    public com.squad.notebook.model.NoteEvent.Builder clearNote() {
      note = null;
      noteBuilder = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
     * Gets the value of the 'NoteEventType' field.
     * @return The value.
     */
    public com.squad.notebook.model.NoteEventType getNoteEventType() {
      return NoteEventType;
    }


    /**
     * Sets the value of the 'NoteEventType' field.
     * @param value The value of 'NoteEventType'.
     * @return This builder.
     */
    public com.squad.notebook.model.NoteEvent.Builder setNoteEventType(com.squad.notebook.model.NoteEventType value) {
      validate(fields()[2] , value);
      this.NoteEventType = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
     * Checks whether the 'NoteEventType' field has been set.
     * @return True if the 'NoteEventType' field has been set, false otherwise.
     */
    public boolean hasNoteEventType() {
      return fieldSetFlags()[2];
    }


    /**
     * Clears the value of the 'NoteEventType' field.
     * @return This builder.
     */
    public com.squad.notebook.model.NoteEvent.Builder clearNoteEventType() {
      NoteEventType = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public NoteEvent build() {
      try {
        NoteEvent record = new NoteEvent();
        record.eventId = fieldSetFlags()[0] ? this.eventId : (java.lang.String) defaultValue(fields()[0]);
        if (noteBuilder != null) {
          try {
            record.note = this.noteBuilder.build();
          } catch (org.apache.avro.AvroMissingFieldException e) {
            e.addParentField(record.getSchema().getField("note"));
            throw e;
          }
        } else {
          record.note = fieldSetFlags()[1] ? this.note : (com.squad.notebook.model.Note) defaultValue(fields()[1]);
        }
        record.NoteEventType = fieldSetFlags()[2] ? this.NoteEventType : (com.squad.notebook.model.NoteEventType) defaultValue(fields()[2]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}









