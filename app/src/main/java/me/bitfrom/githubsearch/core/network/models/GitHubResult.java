package me.bitfrom.githubsearch.core.network.models;

import java.util.List;
import com.google.auto.value.AutoValue;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.Gson;

@AutoValue
public abstract class GitHubResult{

	@SerializedName("total_count")
	public abstract int totalCount();

	@SerializedName("incomplete_results")
	public abstract boolean incompleteResults();

	@SerializedName("items")
	public abstract List<ItemsItem> items();

	public static TypeAdapter<GitHubResult> typeAdapter(Gson gson) {
		return new AutoValue_GitHubResult.GsonTypeAdapter(gson);
	}
}