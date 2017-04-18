package com.schonherz.project.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

/**
 * Created by darvasr on 2016.09.02..
 */
@Entity
@Table(name = "COMMENT")
public class Comment extends Note implements Serializable {

    @ManyToOne(targetEntity = Comment.class, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "PARENT_COMMENT_ID", referencedColumnName = "id")
    private Comment parentComment;

    @ManyToOne(targetEntity = Task.class, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "TASK_ID", referencedColumnName = "id")
    private Task task;

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment comment) {
        this.parentComment = comment;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.parentComment);
        hash = 41 * hash + Objects.hashCode(this.task);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Comment other = (Comment) obj;
        if (!Objects.equals(this.parentComment, other.parentComment)) {
            return false;
        }
        if (!Objects.equals(this.task, other.task)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Comment{" + "parentComment=" + parentComment + ", task=" + task + '}';
    }

}
