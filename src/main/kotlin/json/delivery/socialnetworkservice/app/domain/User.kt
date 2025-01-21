package json.delivery.socialnetworkservice.app.domain

data class User(
    val id: UserId,
    val name: String,
) {
    init {
        require(name.isNotEmpty()) {
            "name should not be empty"
        }
    }
}
