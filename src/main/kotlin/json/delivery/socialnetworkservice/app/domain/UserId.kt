package json.delivery.socialnetworkservice.app.domain

data class UserId(val id: Long) {
    init {
        require(id > 0) { "UserId must be a positive number, but was $id" }
    }
}
