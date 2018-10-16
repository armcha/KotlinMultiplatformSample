package domain.models

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable

@Serializable
data class UserFromJson(
    val login: String,
    val id: Int,
    @Optional
    val node_id: String = "",
    @Optional
    val avatar_url: String = "",
    @Optional
    val gravatar_id: String = "",
    @Optional
    val url: String="",
    @Optional
    val html_url: String="",
    @Optional
    val followers_url: String="",
    @Optional
    val following_url: String="",
    @Optional
    val gists_url: String="",
    @Optional
    val starred_url: String="",
    @Optional
    val subscriptions_url: String="",
    @Optional
    val organizations_url: String="",
    @Optional
    val repos_url: String?="",
    @Optional
    val events_url: String?="",
    @Optional
    val received_events_url: String?="",
    @Optional
    val type: String?="",
    @Optional
    val site_admin: Boolean = false,
    @Optional
    val name: String? ="",
    @Optional
    val company: String?="",
    @Optional
    val blog: String?="",
    @Optional
    val location: String?="",
    @Optional
    val email: String?="",
    @Optional
    val hireable: String?="",
    @Optional
    val bio: String?="",
    @Optional
    val public_repos: Int = 0,
    @Optional
    val public_gists: Int = 0 ,
    @Optional
    val followers: Int = 0,
    @Optional
    val following: Int = 0,
    @Optional
    val created_at: String = "",
    @Optional
    val updated_at: String = ""
)