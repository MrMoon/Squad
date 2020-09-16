package com.squad.notebook.service;

import com.squad.notebook.model.Note;
import com.squad.notebook.model.NoteEvent;
import com.squad.notebook.model.NoteEventType;
import com.squad.notebook.producer.NoteEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteEventProducer noteEventProducer;

    @Override
    public Note create(Note note) {
        return this.produce(note , NoteEventType.NOTE_CREATED);
    }

    @Override
    public Optional<Note> getNoteById(String noteId) {
        return Optional.empty();
    }

    @Override
    public List<Note> getUsersNote(String userId) {
        return null;
    }

    @Override
    public Note update(Note note) {
        return this.produce(note , NoteEventType.NOTE_UPDATED);
    }

    private Note produce(Note note , NoteEventType noteEventType) {
        note.setNoteId(UUID.randomUUID().toString());
        NoteEvent noteEvent = new NoteEvent();
        noteEvent.setNote(note);
        noteEvent.setEventId(UUID.randomUUID().toString());
        noteEvent.setNoteEventType(noteEventType);
        this.noteEventProducer.produceNoteEvent(note.getNoteId() , noteEvent);
        return note;
    }

    @Override
    public boolean delete(String noteId) {
        Optional<Note> note = this.getNoteById(noteId);
        if (note.isEmpty()) return false;
        this.produce(note.get() , NoteEventType.NOTE_DELETED);
        return true;
    }
}
