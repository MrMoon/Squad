package com.squad.notebook.controller;

import com.squad.notebook.model.Note;
import com.squad.notebook.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/{noteId}")
    public ResponseEntity<Note> getNoteById(@PathVariable("noteId") String noteId) {
        Optional<Note> note = this.noteService.getNoteById(noteId);
        return note.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Note>> getUserNotes(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(this.noteService.getUsersNote(userId));
    }

    @PostMapping
    public ResponseEntity<Note> create(@RequestBody Note note) {
        return ResponseEntity.ok(this.noteService.create(note));
    }

    @PutMapping
    public ResponseEntity<Note> update(@RequestBody Note note) {
        return ResponseEntity.ok(this.noteService.update(note));
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<Boolean> delete(@PathVariable("noteId") String noteId) {
        return ResponseEntity.ok(this.noteService.delete(noteId));
    }
}
