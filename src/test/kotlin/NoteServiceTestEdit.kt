import org.junit.Test

import org.junit.Assert.*

class NoteServiceTestEdit {


    @Test
    fun editNoteIsTrue() {
        NoteService.add(
            Note(
                nId = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )
        val resultEdit = NoteService.edit(
            Note(
                nId = 1,
                title = "TitleNoteEdit",
                text = "TextNoteEdit",
            )
        )

        assertTrue(resultEdit)
    }

    @Test(expected = NoteNotFoundException::class)
    fun editNoteIsFalseWithDeleteStatusNote() {
        NoteService.add(
            Note(
                nId = 1,
                title = "TitleNote",
                text = "TextNote",
                isDelete = true
            )
        )
        NoteService.edit(
            Note(
                nId = 1,
                title = "TitleNoteEdit",
                text = "TextNoteEdit",
            )
        )
    }

    @Test(expected = NoteNotFoundException::class)
    fun editNoteIsFalseWithOtherIdNote() {
        NoteService.add(
            Note(
                nId = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )
        NoteService.edit(
            Note(
                nId = 3,
                title = "TitleNoteEdit",
                text = "TextNoteEdit",
            )
        )
    }

    @Test
    fun editCommentIsTrue() {
        NoteService.add(
            Note(
                nId = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        CommentsService.add(
            Comment(
                noteId = 1,
                commentId = 1,
                text = "TextComment"
            )
        )

        val resultEdit = CommentsService.edit(
            Comment(
                noteId = 1,
                commentId = 1,
                text = "TextCommentEdit"
            )
        )

        assertTrue(resultEdit)
    }

    @Test(expected = CommentNotFoundException::class)
    fun editCommentIsFalseWithOtherIdNotes() {
        NoteService.add(
            Note(
                nId = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        CommentsService.add(
            Comment(
                noteId = 1,
                commentId = 1,
                text = "TextComment"
            )
        )

        CommentsService.edit(
            Comment(
                noteId = 2,
                commentId = 1,
                text = "TextCommentEdit"
            )
        )
    }

    @Test(expected = CommentNotFoundException::class)
    fun editCommentIsFalseWithOtherIdComment() {
        NoteService.add(
            Note(
                nId = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        CommentsService.add(
            Comment(
                noteId = 1,
                commentId = 1,
                text = "TextComment"
            )
        )

        CommentsService.edit(
            Comment(
                noteId = 1,
                commentId = 2,
                text = "TextCommentEdit"
            )
        )
    }

    @Test(expected = CommentNotFoundException::class)
    fun editCommentIsFalseWithDeleteStatusOfComment() {
        NoteService.add(
            Note(
                nId = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        CommentsService.add(
            Comment(
                noteId = 1,
                commentId = 1,
                text = "TextComment",
                isDelete = true
            )
        )

        CommentsService.edit(
            Comment(
                noteId = 1,
                commentId = 1,
                text = "TextCommentEdit"
            )
        )
    }

}
