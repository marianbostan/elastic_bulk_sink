package com.orange.malima.elasticrepo.entities;



import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.data.elasticsearch.annotations.FieldType.Integer;

@Document(indexName = "alarms", type = "alarm", shards = 1, replicas = 0, refreshInterval = "-1")
public class Alarm {

    @Id
    private String id;

    private String comment;

    private String status;

    private int score;

    public Alarm(String id, String comment, String status, int score) {
        this.id = id;
        this.comment = comment;
        this.status = status;
        this.score = score;
    }

    public Alarm(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}