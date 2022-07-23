
import org.junit.Test
import org.junit.Assert.*

class NoteServiceTestAdd {

    @Test
    fun addNote() {
        val resultNote = NoteService.add(
            Note(
                nId = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )
        assertTrue(resultNote)
    }

    @Test
    fun createCommentIsTrue() {
        NoteService.add(
            Note(
                nId = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        val resultComment = CommentsService.add(
            Comment(
                noteId = 1,
                commentId = 1,
                text = "TextComment"

            )
        )
        assertTrue(resultComment)
    }

    @Test(expected = NoteNotFoundException::class)
    fun createCommentIsFalse() {
        NoteService.add(
            Note(
                nId = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        CommentsService.add(
            Comment(
                noteId = 5,
                commentId = 1,
                text = "TextComment"

            )
        )
    }
}