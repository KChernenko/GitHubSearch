package me.bitfrom.githubsearch.core.storage.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * <p>Mapped database row.</p>
 *
 * @author const
 * @version 1
 * @since 30.06.2017
 */
@Entity(tableName = "repositories")
public class RepositoryEntity {

    @PrimaryKey
    @ColumnInfo(name = "rep_url")
    private String repositoryUrl;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "full_name")
    private String fullName;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "stars")
    private Integer stars;

    @ColumnInfo(name = "forks")
    private Integer forks;

    @Embedded
    private Owner owner;

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Integer getForks() {
        return forks;
    }

    public void setForks(Integer forks) {
        this.forks = forks;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RepositoryEntity that = (RepositoryEntity) o;

        if (repositoryUrl != null ? !repositoryUrl.equals(that.repositoryUrl) : that.repositoryUrl != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (fullName != null ? !fullName.equals(that.fullName) : that.fullName != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        if (stars != null ? !stars.equals(that.stars) : that.stars != null) return false;
        if (forks != null ? !forks.equals(that.forks) : that.forks != null) return false;
        return owner != null ? owner.equals(that.owner) : that.owner == null;

    }

    @Override
    public int hashCode() {
        int result = repositoryUrl != null ? repositoryUrl.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (stars != null ? stars.hashCode() : 0);
        result = 31 * result + (forks != null ? forks.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RepositoryEntity{" +
                "repositoryUrl='" + repositoryUrl + '\'' +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", description='" + description + '\'' +
                ", stars=" + stars +
                ", forks=" + forks +
                ", owner=" + owner +
                '}';
    }
}