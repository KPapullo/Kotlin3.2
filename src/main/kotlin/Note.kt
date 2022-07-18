data class Note(
    val nId: Int,
    val ownerId: Int,
    val title: String,
    val text: String,
    val privacy: Int,
    val commentPrivacy: Int,
    val privacyView: String,
    val privacyComment: String = ""
) {

}

