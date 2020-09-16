package com.squad.notebook.controller;


import com.squad.notebook.model.Notebook;
import com.squad.notebook.service.NotebookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notebook")
@RequiredArgsConstructor
public class NotebookController {

    private final NotebookService notebookService;

    @GetMapping("/{notebookId}")
    public ResponseEntity<Notebook> getNoteById(@PathVariable("noteId") String notebookId) {
        Optional<Notebook> notebook = this.notebookService.getNotebookById(notebookId);
        return notebook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notebook>> getUserNotes(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(this.notebookService.getUsersNotebook(userId));
    }

    @PostMapping
    public ResponseEntity<Notebook> create(@RequestBody Notebook notebook) {
        return ResponseEntity.ok(this.notebookService.create(notebook));
    }

    @PutMapping
    public ResponseEntity<Notebook> update(@RequestBody Notebook notebook) {
        return ResponseEntity.ok(this.notebookService.update(notebook));
    }

    @DeleteMapping("/{notebookId}")
    public ResponseEntity<Boolean> delete(@PathVariable("notebookId") String notebookId) {
        return ResponseEntity.ok(this.notebookService.delete(notebookId));
    }


}
