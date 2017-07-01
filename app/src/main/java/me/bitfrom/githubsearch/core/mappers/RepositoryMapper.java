package me.bitfrom.githubsearch.core.mappers;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import me.bitfrom.githubsearch.core.network.models.RepositoryModel;
import me.bitfrom.githubsearch.core.storage.entities.Owner;
import me.bitfrom.githubsearch.core.storage.entities.RepositoryEntity;
import me.bitfrom.githubsearch.utils.TextUtils;

/**
 * <p>Maps response objects to entity objects.</p>
 *
 * @author const
 * @version 1
 * @since 01.07.2017
 */
@Singleton
public class RepositoryMapper {

    @Inject
    public RepositoryMapper() {
        //Nothing to instantiate
    }

    /**
     * Maps {@link List} of the {@link RepositoryModel} to {@link List} of the {@link RepositoryEntity}.
     * It's final, because we don't want it to be overrode.
     *
     * @param repositoryModelList {@link List} of the {@link RepositoryModel} objects.
     * @param timeStamp time stamp that marks operation.
     *
     * @return {@link List} of the {@link RepositoryEntity} objects.
     **/
    public final List<RepositoryEntity> mapRepositories(@NonNull final List<RepositoryModel> repositoryModelList,
                                                        @NonNull final Long timeStamp) {
        final List<RepositoryEntity> mappedResults = new ArrayList<>();
        for (int i = 0; i < repositoryModelList.size(); i++) {
            final RepositoryEntity repositoryEntity = new RepositoryEntity();
            repositoryEntity.setRepositoryUrl(repositoryModelList.get(i).htmlUrl());
            repositoryEntity.setName(TextUtils.subString(repositoryModelList.get(i).name(), 0, 30));
            repositoryEntity.setLanguage(TextUtils.subString(repositoryModelList.get(i).language(), 0, 30));
            repositoryEntity.setDescription(TextUtils.subString(repositoryModelList.get(i).description(), 0, 30));
            repositoryEntity.setStars(repositoryModelList.get(i).stargazersCount());
            repositoryEntity.setForks(repositoryModelList.get(i).forks());
            final Owner owner = new Owner();
            owner.setOwnerId(repositoryModelList.get(i).owner().id());
            owner.setOwnerName(TextUtils.subString(repositoryModelList.get(i).owner().login(), 0, 30));
            repositoryEntity.setOwner(owner);
            repositoryEntity.setTimeStamp(timeStamp);
            mappedResults.add(repositoryEntity);
        }

        return mappedResults;
    }
}