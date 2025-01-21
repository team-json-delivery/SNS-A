package json.delivery.socialnetworkservice.app.domain

@JvmInline
value class UserId(private val id: Long) {
    init {
        require(id > 0) { "UserId must be a positive number, but was $id" }
    }
}
