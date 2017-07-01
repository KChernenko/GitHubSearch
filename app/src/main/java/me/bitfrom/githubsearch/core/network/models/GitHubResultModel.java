package me.bitfrom.githubsearch.core.network.models;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@AutoValue
public abstract class GitHubResultModel {

	@SerializedName("total_count")
	public abstract int totalCount();

	@SerializedName("incomplete_results")
	public abstract boolean incompleteResults();

	@SerializedName("items")
	public abstract List<RepositoryModel> items();

	public static TypeAdapter<GitHubResultModel> typeAdapter(Gson gson) {
		return new AutoValue_GitHubResultModel.GsonTypeAdapter(gson);
	}
}