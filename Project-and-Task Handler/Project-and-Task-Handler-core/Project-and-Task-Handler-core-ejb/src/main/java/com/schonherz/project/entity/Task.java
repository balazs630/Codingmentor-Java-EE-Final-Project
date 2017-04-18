package com.schonherz.project.entity;

import com.schonherz.project.model.Priority;
import com.schonherz.project.model.TaskStatus;
import com.schonherz.project.model.TaskType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by darvasr on 2016.09.02..
 */
@Entity
@Table(name = "TASK")
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", length = 50, unique = true)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(targetEntity = Project.class)
    @JoinColumn(name = "PROJECT_ID", referencedColumnName = "id")
    private Project project;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRIORITY", length = 50)
    private Priority priority;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "USER_ID", referencedColumnName = "id")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DEADLINE")
    private Date deadline;

    @ManyToOne(targetEntity = Task.class, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "PARENT_TASK_ID", referencedColumnName = "id")
    private Task parentTask;

    @Enumerated(EnumType.STRING)
    @Column(name = "TASK_TYPE", length = 25)
    private TaskType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "TASK_STATUS", length = 25)
    private TaskStatus status;

    @OneToMany(mappedBy = "parentTask", targetEntity = Task.class, fetch = FetchType.EAGER)
    private List<Task> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Task getParentTask() {
        return parentTask;
    }

    public void setParentTask(Task parentTask) {
        this.parentTask = parentTask;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Task> getChildren() {
        return children;
    }

    public void setChildren(List<Task> children) {
        this.children = children;
    }

    //TODO : Clear
    @PrePersist
    public void persistListener() {
        System.out.println(getId() + " " + getName());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.name);
        hash = 47 * hash + Objects.hashCode(this.description);
        hash = 47 * hash + Objects.hashCode(this.project);
        hash = 47 * hash + Objects.hashCode(this.priority);
        hash = 47 * hash + Objects.hashCode(this.user);
        hash = 47 * hash + Objects.hashCode(this.deadline);
        hash = 47 * hash + Objects.hashCode(this.parentTask);
        hash = 47 * hash + Objects.hashCode(this.type);
        hash = 47 * hash + Objects.hashCode(this.status);
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
        final Task other = (Task) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.project, other.project)) {
            return false;
        }
        if (this.priority != other.priority) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.deadline, other.deadline)) {
            return false;
        }
        if (!Objects.equals(this.parentTask, other.parentTask)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Task{" + "id=" + id + ", name=" + name + ", description=" + description + ", project=" + project + ", priority=" + priority + ", user=" + user + ", deadline=" + deadline + ", parentTask=" + parentTask + ", type=" + type + ", status=" + status + '}';
    }

}
