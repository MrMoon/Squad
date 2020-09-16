/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.squad.post.model;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class PostEvent extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -4252653974455467237L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"PostEvent\",\"namespace\":\"com.squad.post.model\",\"fields\":[{\"name\":\"eventId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"Post\",\"type\":{\"type\":\"record\",\"name\":\"Post\",\"fields\":[{\"name\":\"postId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"userId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"groupId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"documentId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"text\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"isNotifiable\",\"type\":\"boolean\"},{\"name\":\"isPublic\",\"type\":\"boolean\"},{\"name\":\"PostType\",\"type\":{\"type\":\"enum\",\"name\":\"PostType\",\"symbols\":[\"NORMAL\",\"DOCUMENT\",\"QNA\",\"ANNOUNCEMENTS\",\"EVENTS\"]}}]}},{\"name\":\"PostEventType\",\"type\":{\"type\":\"enum\",\"name\":\"PostEventType\",\"symbols\":[\"POST_CREATED\",\"POST_UPDATED\",\"POST_LIKED\",\"POST_UNLIKED\",\"POST_DELETED\",\"POST_NOTIFICATION\"]}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<PostEvent> ENCODER =
      new BinaryMessageEncoder<PostEvent>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<PostEvent> DECODER =
      new BinaryMessageDecoder<PostEvent>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<PostEvent> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<PostEvent> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<PostEvent> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<PostEvent>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this PostEvent to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a PostEvent from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a PostEvent instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static PostEvent fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.String eventId;
  @Deprecated public com.squad.post.model.Post Post;
  @Deprecated public com.squad.post.model.PostEventType PostEventType;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public PostEvent() {}

  /**
   * All-args constructor.
   * @param eventId The new value for eventId
   * @param Post The new value for Post
   * @param PostEventType The new value for PostEventType
   */
  public PostEvent(java.lang.String eventId, com.squad.post.model.Post Post, com.squad.post.model.PostEventType PostEventType) {
    this.eventId = eventId;
    this.Post = Post;
    this.PostEventType = PostEventType;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return eventId;
    case 1: return Post;
    case 2: return PostEventType;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: eventId = (java.lang.String)value$; break;
    case 1: Post = (com.squad.post.model.Post)value$; break;
    case 2: PostEventType = (com.squad.post.model.PostEventType)value$; break;
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
   * Gets the value of the 'Post' field.
   * @return The value of the 'Post' field.
   */
  public com.squad.post.model.Post getPost() {
    return Post;
  }


  /**
   * Sets the value of the 'Post' field.
   * @param value the value to set.
   */
  public void setPost(com.squad.post.model.Post value) {
    this.Post = value;
  }

  /**
   * Gets the value of the 'PostEventType' field.
   * @return The value of the 'PostEventType' field.
   */
  public com.squad.post.model.PostEventType getPostEventType() {
    return PostEventType;
  }


  /**
   * Sets the value of the 'PostEventType' field.
   * @param value the value to set.
   */
  public void setPostEventType(com.squad.post.model.PostEventType value) {
    this.PostEventType = value;
  }

  /**
   * Creates a new PostEvent RecordBuilder.
   * @return A new PostEvent RecordBuilder
   */
  public static com.squad.post.model.PostEvent.Builder newBuilder() {
    return new com.squad.post.model.PostEvent.Builder();
  }

  /**
   * Creates a new PostEvent RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new PostEvent RecordBuilder
   */
  public static com.squad.post.model.PostEvent.Builder newBuilder(com.squad.post.model.PostEvent.Builder other) {
    if (other == null) {
      return new com.squad.post.model.PostEvent.Builder();
    } else {
      return new com.squad.post.model.PostEvent.Builder(other);
    }
  }

  /**
   * Creates a new PostEvent RecordBuilder by copying an existing PostEvent instance.
   * @param other The existing instance to copy.
   * @return A new PostEvent RecordBuilder
   */
  public static com.squad.post.model.PostEvent.Builder newBuilder(com.squad.post.model.PostEvent other) {
    if (other == null) {
      return new com.squad.post.model.PostEvent.Builder();
    } else {
      return new com.squad.post.model.PostEvent.Builder(other);
    }
  }

  /**
   * RecordBuilder for PostEvent instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<PostEvent>
    implements org.apache.avro.data.RecordBuilder<PostEvent> {

    private java.lang.String eventId;
    private com.squad.post.model.Post Post;
    private com.squad.post.model.Post.Builder PostBuilder;
    private com.squad.post.model.PostEventType PostEventType;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.squad.post.model.PostEvent.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.eventId)) {
        this.eventId = data().deepCopy(fields()[0].schema(), other.eventId);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.Post)) {
        this.Post = data().deepCopy(fields()[1].schema(), other.Post);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (other.hasPostBuilder()) {
        this.PostBuilder = com.squad.post.model.Post.newBuilder(other.getPostBuilder());
      }
      if (isValidValue(fields()[2], other.PostEventType)) {
        this.PostEventType = data().deepCopy(fields()[2].schema(), other.PostEventType);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
    }

    /**
     * Creates a Builder by copying an existing PostEvent instance
     * @param other The existing instance to copy.
     */
    private Builder(com.squad.post.model.PostEvent other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.eventId)) {
        this.eventId = data().deepCopy(fields()[0].schema(), other.eventId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.Post)) {
        this.Post = data().deepCopy(fields()[1].schema(), other.Post);
        fieldSetFlags()[1] = true;
      }
      this.PostBuilder = null;
      if (isValidValue(fields()[2], other.PostEventType)) {
        this.PostEventType = data().deepCopy(fields()[2].schema(), other.PostEventType);
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
    public com.squad.post.model.PostEvent.Builder setEventId(java.lang.String value) {
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
    public com.squad.post.model.PostEvent.Builder clearEventId() {
      eventId = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'Post' field.
      * @return The value.
      */
    public com.squad.post.model.Post getPost() {
      return Post;
    }


    /**
      * Sets the value of the 'Post' field.
      * @param value The value of 'Post'.
      * @return This builder.
      */
    public com.squad.post.model.PostEvent.Builder setPost(com.squad.post.model.Post value) {
      validate(fields()[1], value);
      this.PostBuilder = null;
      this.Post = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'Post' field has been set.
      * @return True if the 'Post' field has been set, false otherwise.
      */
    public boolean hasPost() {
      return fieldSetFlags()[1];
    }

    /**
     * Gets the Builder instance for the 'Post' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public com.squad.post.model.Post.Builder getPostBuilder() {
      if (PostBuilder == null) {
        if (hasPost()) {
          setPostBuilder(com.squad.post.model.Post.newBuilder(Post));
        } else {
          setPostBuilder(com.squad.post.model.Post.newBuilder());
        }
      }
      return PostBuilder;
    }

    /**
     * Sets the Builder instance for the 'Post' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */
    public com.squad.post.model.PostEvent.Builder setPostBuilder(com.squad.post.model.Post.Builder value) {
      clearPost();
      PostBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'Post' field has an active Builder instance
     * @return True if the 'Post' field has an active Builder instance
     */
    public boolean hasPostBuilder() {
      return PostBuilder != null;
    }

    /**
      * Clears the value of the 'Post' field.
      * @return This builder.
      */
    public com.squad.post.model.PostEvent.Builder clearPost() {
      Post = null;
      PostBuilder = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'PostEventType' field.
      * @return The value.
      */
    public com.squad.post.model.PostEventType getPostEventType() {
      return PostEventType;
    }


    /**
      * Sets the value of the 'PostEventType' field.
      * @param value The value of 'PostEventType'.
      * @return This builder.
      */
    public com.squad.post.model.PostEvent.Builder setPostEventType(com.squad.post.model.PostEventType value) {
      validate(fields()[2], value);
      this.PostEventType = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'PostEventType' field has been set.
      * @return True if the 'PostEventType' field has been set, false otherwise.
      */
    public boolean hasPostEventType() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'PostEventType' field.
      * @return This builder.
      */
    public com.squad.post.model.PostEvent.Builder clearPostEventType() {
      PostEventType = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public PostEvent build() {
      try {
        PostEvent record = new PostEvent();
        record.eventId = fieldSetFlags()[0] ? this.eventId : (java.lang.String) defaultValue(fields()[0]);
        if (PostBuilder != null) {
          try {
            record.Post = this.PostBuilder.build();
          } catch (org.apache.avro.AvroMissingFieldException e) {
            e.addParentField(record.getSchema().getField("Post"));
            throw e;
          }
        } else {
          record.Post = fieldSetFlags()[1] ? this.Post : (com.squad.post.model.Post) defaultValue(fields()[1]);
        }
        record.PostEventType = fieldSetFlags()[2] ? this.PostEventType : (com.squad.post.model.PostEventType) defaultValue(fields()[2]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<PostEvent>
    WRITER$ = (org.apache.avro.io.DatumWriter<PostEvent>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<PostEvent>
    READER$ = (org.apache.avro.io.DatumReader<PostEvent>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.eventId);

    this.Post.customEncode(out);

    out.writeEnum(this.PostEventType.ordinal());

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.eventId = in.readString();

      if (this.Post == null) {
        this.Post = new com.squad.post.model.Post();
      }
      this.Post.customDecode(in);

      this.PostEventType = com.squad.post.model.PostEventType.values()[in.readEnum()];

    } else {
      for (int i = 0; i < 3; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.eventId = in.readString();
          break;

        case 1:
          if (this.Post == null) {
            this.Post = new com.squad.post.model.Post();
          }
          this.Post.customDecode(in);
          break;

        case 2:
          this.PostEventType = com.squad.post.model.PostEventType.values()[in.readEnum()];
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}









