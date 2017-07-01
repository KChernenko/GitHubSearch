package me.bitfrom.githubsearch.core.storage.entities;

import android.arch.persistence.room.ColumnInfo;

/**
 * <p>Represents mapped owner row.</p>
 *
 * @author const
 * @version 1
 * @since 30.06.2017
 */
public class Owner {

    @ColumnInfo(name = "owner_id")
    private Integer ownerId;

    @ColumnInfo(name = "owner_name")
    private String ownerName;

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Owner that = (Owner) o;

        return ownerId != null ? ownerId.equals(that.ownerId) : that.ownerId == null
                && (ownerName != null ? ownerName.equals(that.ownerName) : that.ownerName == null);
    }

    @Override
    public int hashCode() {
        int result = ownerId != null ? ownerId.hashCode() : 0;
        result = 31 * result + (ownerName != null ? ownerName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OwnerModel{" +
                "ownerId=" + ownerId +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }
}