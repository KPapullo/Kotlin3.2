import NoteService.notes

data class Comment(
    var noteId: Int,
    var commentId: Int,
    val text: String,
    val isDelete: Boolean = false,
) {
}

object CommentsService : CrudService<Comment> {
    private var lastIdComment = 0


    override fun add(item: Comment): Boolean {
        lastIdComment++
        val updateComm = item.copy(commentId = lastIdComment)
        (updateComm)
        return true
    }

    override fun delete(id: Int): Boolean {
        for (note in notes) {
            for ((index, comment) in note.comments.withIndex()) {
                if (comment.noteId == id && !comment.isDelete) {
                    val updateComm = comment.copy(isDelete = true)
                    note.comments[index] = updateComm
                    return true
                }
            }
        }
        throw CommentNotFoundException("not comments with id: $id")
    }

    override fun edit(item: Comment): Boolean {
        for (note in notes) {
            if (note.nId == item.noteId) {
                for ((index, comment) in note.comments.withIndex()) {
                    if (comment.commentId == item.commentId && !comment.isDelete) {
                        note.comments[index] = item
                        return true
                    }
                }
            }
        }
        throw CommentNotFoundException("not comment with id: ${item.commentId}")
    }

    override fun read(): MutableList<Comment> {
        val commentsForGet = mutableListOf<Comment>()
        for (note in notes) {
            if (!note.isDelete) {
                for (comment in note.comments) {
                    if (!comment.isDelete) {
                        commentsForGet += comment
                    }
                }
            }
        }
        return commentsForGet
    }

    override fun getById(id: Int): Comment {
        for (note in notes) {
            if (!note.isDelete) {
                for (comment in note.comments) {
                    if (comment.commentId == id && !comment.isDelete) {
                        return comment
                    }
                }
            }
        }
        throw CommentNotFoundException("not comment with id: $id")
    }

    fun getByIdNote(noteId: Int): MutableList<Comment> {
        val commentsForGet = mutableListOf<Comment>()
        for (note in notes) {
            if (noteId == note.nId && !note.isDelete) {
                for (comment in note.comments) {
                    if (!comment.isDelete) {
                        commentsForGet += comment
                    }
                }
                return commentsForGet
            }
        }
        throw NoteNotFoundException("not note with id: $noteId")
    }

    override fun restore(id: Int): Boolean {
        for (note in notes) {
            for ((index, comment) in note.comments.withIndex()) {
                if (comment.commentId == id && comment.isDelete) {
                    val updateComm = comment.copy(isDelete = false)
                    note.comments[index] = updateComm
                    return true
                }
            }
        }
        return false
    }

}
