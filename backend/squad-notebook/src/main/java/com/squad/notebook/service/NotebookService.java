package com.squad.notebook.service;

import com.squad.notebook.model.Notebook;

import java.util.List;
import java.util.Optional;

public interface NotebookService {

    Notebook create(Notebook note);

    Optional<Notebook> getNotebookById(String noteId);

    List<Notebook> getUsersNotebook(String userId);

    Notebook update(Notebook note);

    boolean delete(String noteId);

}
