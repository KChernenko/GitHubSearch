package me.bitfrom.githubsearch.core.network.models;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class RepositoryModel {

    @SerializedName("stargazers_count")
    public abstract int stargazersCount();

    @SerializedName("pushed_at")
    public abstract String pushedAt();

    @SerializedName("subscription_url")
    public abstract String subscriptionUrl();

    @SerializedName("language")
    public abstract String language();

    @SerializedName("branches_url")
    public abstract String branchesUrl();

    @SerializedName("issue_comment_url")
    public abstract String issueCommentUrl();

    @SerializedName("labels_url")
    public abstract String labelsUrl();

    @SerializedName("score")
    public abstract double score();

    @SerializedName("subscribers_url")
    public abstract String subscribersUrl();

    @SerializedName("releases_url")
    public abstract String releasesUrl();

    @SerializedName("svn_url")
    public abstract String svnUrl();

    @SerializedName("id")
    public abstract int id();

    @SerializedName("forks")
    public abstract int forks();

    @SerializedName("archive_url")
    public abstract String archiveUrl();

    @SerializedName("git_refs_url")
    public abstract String gitRefsUrl();

    @SerializedName("forks_url")
    public abstract String forksUrl();

    @SerializedName("statuses_url")
    public abstract String statusesUrl();

    @SerializedName("ssh_url")
    public abstract String sshUrl();

    @SerializedName("full_name")
    public abstract String fullName();

    @SerializedName("size")
    public abstract int size();

    @SerializedName("languages_url")
    public abstract String languagesUrl();

    @SerializedName("html_url")
    public abstract String htmlUrl();

    @SerializedName("collaborators_url")
    public abstract String collaboratorsUrl();

    @SerializedName("clone_url")
    public abstract String cloneUrl();

    @SerializedName("name")
    public abstract String name();

    @SerializedName("pulls_url")
    public abstract String pullsUrl();

    @SerializedName("default_branch")
    public abstract String defaultBranch();

    @SerializedName("hooks_url")
    public abstract String hooksUrl();

    @SerializedName("trees_url")
    public abstract String treesUrl();

    @SerializedName("tags_url")
    public abstract String tagsUrl();

    @SerializedName("private")
    public abstract boolean jsonMemberPrivate();

    @SerializedName("contributors_url")
    public abstract String contributorsUrl();

    @SerializedName("has_downloads")
    public abstract boolean hasDownloads();

    @SerializedName("notifications_url")
    public abstract String notificationsUrl();

    @SerializedName("open_issues_count")
    public abstract int openIssuesCount();

    @SerializedName("description")
    public abstract String description();

    @SerializedName("created_at")
    public abstract String createdAt();

    @SerializedName("watchers")
    public abstract int watchers();

    @SerializedName("keys_url")
    public abstract String keysUrl();

    @SerializedName("deployments_url")
    public abstract String deploymentsUrl();

    @SerializedName("has_projects")
    public abstract boolean hasProjects();

    @SerializedName("has_wiki")
    public abstract boolean hasWiki();

    @SerializedName("updated_at")
    public abstract String updatedAt();

    @SerializedName("comments_url")
    public abstract String commentsUrl();

    @SerializedName("stargazers_url")
    public abstract String stargazersUrl();

    @SerializedName("git_url")
    public abstract String gitUrl();

    @SerializedName("has_pages")
    public abstract boolean hasPages();

    @SerializedName("owner")
    public abstract OwnerModel owner();

    @SerializedName("commits_url")
    public abstract String commitsUrl();

    @SerializedName("compare_url")
    public abstract String compareUrl();

    @SerializedName("git_commits_url")
    public abstract String gitCommitsUrl();

    @SerializedName("blobs_url")
    public abstract String blobsUrl();

    @SerializedName("git_tags_url")
    public abstract String gitTagsUrl();

    @SerializedName("merges_url")
    public abstract String mergesUrl();

    @SerializedName("downloads_url")
    public abstract String downloadsUrl();

    @SerializedName("has_issues")
    public abstract boolean hasIssues();

    @SerializedName("url")
    public abstract String url();

    @SerializedName("contents_url")
    public abstract String contentsUrl();

    @SerializedName("mirror_url")
    public abstract String mirrorUrl();

    @SerializedName("milestones_url")
    public abstract String milestonesUrl();

    @SerializedName("teams_url")
    public abstract String teamsUrl();

    @SerializedName("fork")
    public abstract boolean fork();

    @SerializedName("issues_url")
    public abstract String issuesUrl();

    @SerializedName("events_url")
    public abstract String eventsUrl();

    @SerializedName("issue_events_url")
    public abstract String issueEventsUrl();

    @SerializedName("assignees_url")
    public abstract String assigneesUrl();

    @SerializedName("open_issues")
    public abstract int openIssues();

    @SerializedName("watchers_count")
    public abstract int watchersCount();

    @SerializedName("homepage")
    public abstract String homepage();

    @SerializedName("forks_count")
    public abstract int forksCount();

    public static TypeAdapter<RepositoryModel> typeAdapter(Gson gson) {
        return new AutoValue_RepositoryModel.GsonTypeAdapter(gson)
                .setDefaultPushedAt("")
                .setDefaultSubscriptionUrl("")
                .setDefaultLanguage("")
                .setDefaultBranchesUrl("")
                .setDefaultIssueCommentUrl("")
                .setDefaultLabelsUrl("")
                .setDefaultScore(0.0)
                .setDefaultSubscribersUrl("")
                .setDefaultReleasesUrl("")
                .setDefaultSvnUrl("")
                .setDefaultId(0)
                .setDefaultForks(0)
                .setDefaultArchiveUrl("")
                .setDefaultGitRefsUrl("")
                .setDefaultForksUrl("")
                .setDefaultStatusesUrl("")
                .setDefaultSshUrl("")
                .setDefaultFullName("")
                .setDefaultSize(0)
                .setDefaultLanguagesUrl("")
                .setDefaultHtmlUrl("")
                .setDefaultCollaboratorsUrl("")
                .setDefaultCloneUrl("")
                .setDefaultName("")
                .setDefaultPullsUrl("")
                .setDefaultDefaultBranch("")
                .setDefaultHooksUrl("")
                .setDefaultTreesUrl("")
                .setDefaultTagsUrl("")
                .setDefaultJsonMemberPrivate(false)
                .setDefaultContributorsUrl("")
                .setDefaultHasDownloads(false)
                .setDefaultNotificationsUrl("")
                .setDefaultOpenIssuesCount(0)
                .setDefaultDescription("")
                .setDefaultCreatedAt("")
                .setDefaultWatchers(0)
                .setDefaultKeysUrl("")
                .setDefaultDeploymentsUrl("")
                .setDefaultHasProjects(false)
                .setDefaultHasWiki(false)
                .setDefaultUpdatedAt("")
                .setDefaultCommentsUrl("")
                .setDefaultStargazersUrl("")
                .setDefaultGitUrl("")
                .setDefaultHasPages(false)
                .setDefaultCommitsUrl("")
                .setDefaultCompareUrl("")
                .setDefaultGitCommitsUrl("")
                .setDefaultBlobsUrl("")
                .setDefaultGitTagsUrl("")
                .setDefaultMergesUrl("")
                .setDefaultDownloadsUrl("")
                .setDefaultHasIssues(false)
                .setDefaultUrl("")
                .setDefaultContentsUrl("")
                .setDefaultMirrorUrl("")
                .setDefaultMilestonesUrl("")
                .setDefaultTeamsUrl("")
                .setDefaultFork(false)
                .setDefaultIssuesUrl("")
                .setDefaultEventsUrl("")
                .setDefaultIssueEventsUrl("")
                .setDefaultAssigneesUrl("")
                .setDefaultOpenIssues(0)
                .setDefaultWatchersCount(0)
                .setDefaultHomepage("")
                .setDefaultForksCount(0);
    }
}