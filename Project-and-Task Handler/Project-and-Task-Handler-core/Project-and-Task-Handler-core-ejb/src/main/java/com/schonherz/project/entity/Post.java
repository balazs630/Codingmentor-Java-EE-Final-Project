package com.schonherz.project.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

/**
 * Created by darvasr on 2016.09.02..
 */
@Entity
@Table(name = "POST")
public class Post extends Note implements Serializable {

    @ManyToOne(targetEntity = Post.class, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "PARENT_POST_ID", referencedColumnName = "id")
    private Post parentPost;

    @ManyToOne(targetEntity = Forum.class, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "FORUM_ID", referencedColumnName = "id")
    private Forum forum;

    public Post getParentPost() {
        return parentPost;
    }

    public void setParentPost(Post parentPost) {
        this.parentPost = parentPost;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.parentPost);
        hash = 97 * hash + Objects.hashCode(this.forum);
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
        final Post other = (Post) obj;
        if (!Objects.equals(this.parentPost, other.parentPost)) {
            return false;
        }
        if (!Objects.equals(this.forum, other.forum)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Post{" + "parentPost=" + parentPost + ", forum=" + forum + '}';
    }

}
