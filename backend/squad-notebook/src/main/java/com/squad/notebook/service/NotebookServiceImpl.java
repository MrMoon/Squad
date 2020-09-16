package com.squad.notebook.service;

import com.squad.notebook.model.Notebook;
import com.squad.notebook.model.NotebookEvent;
import com.squad.notebook.model.NotebookEventType;
import com.squad.notebook.producer.NotebookEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotebookServiceImpl implements NotebookService {

    private final NotebookEventProducer notebookEventProducer;

    @Override
    public Notebook create(Notebook notebook) {
        return this.produce(notebook , NotebookEventType.NOTEBOOK_CREATED);
    }

    @Override
    public Optional<Notebook> getNotebookById(String noteId) {
        return Optional.empty();
    }

    @Override
    public List<Notebook> getUsersNotebook(String userId) {
        return null;
    }

    private Notebook produce(Notebook notebook , NotebookEventType notebookEventType) {
        notebook.setNotebookId(UUID.randomUUID().toString());
        NotebookEvent notebookEvent = new NotebookEvent();
        notebookEvent.setEventId(UUID.randomUUID().toString());
        notebookEvent.setNotebook(notebook);
        notebookEvent.setNotebookEventType(notebookEventType);
        this.notebookEventProducer.produceNotebookEvent(notebook.getNotebookId() , notebookEvent);
        return notebook;
    }

    @Override
    public Notebook update(Notebook notebook) {
        return this.produce(notebook , NotebookEventType.NOTEBOOK_UPDATED);
    }

    @Override
    public boolean delete(String notebookId) {
        Optional<Notebook> notebook = this.getNotebookById(notebookId);
        if (notebook.isEmpty()) return false;
        this.produce(notebook.get() , NotebookEventType.NOTEBOOK_DELETED);
        return true;
    }
}
