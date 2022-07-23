import org.junit.Test
import org.junit.Assert.*

class NoteServiceTestDelete {


    @Test
    fun deleteNoteIsTrue() {
        NoteService.add(
            Note(
                nId = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )
        val resultDelete = NoteService.delete(1)
        assertTrue(resultDelete)
    }

    @Test(expected = NoteNotFoundException::class)
    fun deleteNoteIsFalseWithOtherIdNote() {
        NoteService.add(
            Note(
                nId = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )
        NoteService.delete(2)

    }

    @Test(expected = NoteNotFoundException::class)
    fun deleteNoteIsFalseWithDeleteStatusNote() {
        NoteService.add(
            Note(
                nId = 1,
                title = "TitleNote",
                text = "TextNote",
                isDelete = true
            )
        )
        NoteService.delete(1)

    }

    @Test
    fun deleteCommentIsTrue() {
        NoteService.add(
            Note(
                nId = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        NoteService.createComment(
            Comment(
                noteId = 1,
                commentId = 1,
                text = "TextComment"

            )
        )
        val resultComment = CommentsService.delete(1)
        assertTrue(resultComment)
    }

    @Test(expected = CommentNotFoundException::class)
    fun deleteCommentIsFalseWithOtherIdComm() {
        NoteService.add(
            Note(
                nId = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        NoteService.createComment(
            Comment(
                noteId = 1,
                commentId = 1,
                text = "TextComment"

            )
        )
        CommentsService.delete(2)

    }

    @Test(expected = CommentNotFoundException::class)
    fun deleteCommentIsFalseWithDeleteStatusComm() {
        NoteService.add(
            Note(
                nId = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        NoteService.createComment(
            Comment(
                noteId = 1,
                commentId = 1,
                text = "TextComment",
                isDelete = true

            )
        )
        CommentsService.delete(1)

    }


}