package com.squad.notebook.service;

import com.squad.notebook.model.Note;

import java.util.List;
import java.util.Optional;

public interface NoteService {

    Note create(Note note);

    Optional<Note> getNoteById(String noteId);

    List<Note> getUsersNote(String userId);

    Note update(Note note);

    boolean delete(String noteId);


}
