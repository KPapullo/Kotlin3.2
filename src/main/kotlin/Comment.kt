data class Comment(
    var commentId:Int,
    val nId: Int,
    val ownerId: Int,
    val replyTo: Int?,
    val message: String,
    val guid: String,
) {
}