package com.example.demo.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="task")
public class Task {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator="native")
    @GenericGenerator(name="native",strategy="native")
    public Long id;
    @Column(name="description")
    private String description;
    @Column(name="done")
    private Boolean done;
    @Column(name="dueDate")
    private LocalDate dueDate;

    public Task() {
    }

    public Task(String description, Boolean done, LocalDate dueDate){
        this.description = description;
        this.done = done;
        this.dueDate = dueDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
