package com.schonherz.project.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

/**
 * Created by darvasr on 2016.09.02..
 */
@Entity
@Table(name = "FORUM")
public class Forum implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FORUM_TOPIC", length = 50)
    private String topic;

    @ManyToOne(targetEntity = Project.class, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "PROJECT_ID", referencedColumnName = "id")
    private Project project;

    @ManyToOne(targetEntity = User.class, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "USER_ID", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "forum", fetch = FetchType.LAZY, targetEntity = Post.class, cascade = {CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Post> posts;

    @Transient
    private long postCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public long getPostCount() {
        return postCount;
    }

    public void setPostCount(long postCount) {
        this.postCount = postCount;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.topic);
        hash = 53 * hash + Objects.hashCode(this.project);
        hash = 53 * hash + Objects.hashCode(this.user);
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
        final Forum other = (Forum) obj;
        if (!Objects.equals(this.topic, other.topic)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.project, other.project)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Forum{" + "id=" + id + ", topic=" + topic + ", project=" + project + ", user=" + user + '}';
    }

}
