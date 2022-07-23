import org.junit.Test

import org.junit.Assert.*

class NoteServiceTestGetAndRestore {


    @Test
    fun getNotesReturnMutableList() {
        NoteService.add(
            Note(
                nId = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )
        NoteService.add(
            Note(
                nId = 2,
                title = "TitleNote2",
                text = "TextNote2",
            )
        )

        val actualResult = NoteService.read()
        val expectedResult = mutableListOf(
            Note(
                nId = 1,
                title = "TitleNote",
                text = "TextNote",
            ),
            Note(
                nId = 2,
                title = "TitleNote2",
                text = "TextNote2",
            )
        )

        assertEquals(expectedResult, actualResult)

    }

    @Test
    fun getNotesWithDeleteStatusNote() {
        NoteService.add(
            Note(
                nId = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )
        NoteService.add(
            Note(
                nId = 2,
                title = "TitleNote2",
                text = "TextNote2",
                isDelete = true
            )
        )

        val actualResult = NoteService.read()
        val expectedResult = mutableListOf(
            Note(
                nId = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        assertEquals(expectedResult, actualResult)

    }

    @Test
    fun getNoteByIdReturnNote() {
        NoteService.add(
            Note(
                nId = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        val actualResult = NoteService.getById(1)
        val expectedResult = Note(
            nId = 1,
            title = "TitleNote",
            text = "TextNote",
        )
        assertEquals(expectedResult, actualResult)

    }

    @Test(expected = NoteNotFoundException::class)
    fun getNoteByIdWithOtherIdNote() {
        NoteService.add(
            Note(
                nId = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        NoteService.getById(2)
    }

    @Test(expected = NoteNotFoundException::class)
    fun getNoteByIdWithDeleteStatusNote() {
        NoteService.add(
            Note(
                nId = 1,
                title = "TitleNote",
                text = "TextNote",
                isDelete = true
            )
        )

        NoteService.getById(1)
    }

    @Test
    fun getComments() {
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
        CommentsService.add(
            Comment(
                noteId = 1,
                commentId = 2,
                text = "TextComment2"
            )
        )

        val actualResult = CommentsService.getByIdNote(1)
        val expectedResult = mutableListOf(
            Comment(
                noteId = 1,
                commentId = 1,
                text = "TextComment"
            ), Comment(
                noteId = 1,
                commentId = 2,
                text = "TextComment2"
            )
        )

        assertEquals(expectedResult, actualResult)

    }

    @Test(expected = NoteNotFoundException::class)
    fun getCommentsIsFalseWithOtherIdNote() {
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
        CommentsService.add(
            Comment(
                noteId = 1,
                commentId = 2,
                text = "TextComment2"
            )
        )

        CommentsService.getByIdNote(6)


    }

    @Test(expected = NoteNotFoundException::class)
    fun getCommentsIsFalseWithDeleteStatusNote() {
        NoteService.add(
            Note(
                nId = 1,
                title = "TitleNote",
                text = "TextNote",
                isDelete = true
            )
        )

        CommentsService.add(
            Comment(
                noteId = 1,
                commentId = 1,
                text = "TextComment"
            )
        )
        CommentsService.add(
            Comment(
                noteId = 1,
                commentId = 2,
                text = "TextComment2"
            )
        )

        CommentsService.getByIdNote(1)

    }

    @Test
    fun getCommentsIsTrueWithDeleteStatusComment() {
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
        CommentsService.add(
            Comment(
                noteId = 1,
                commentId = 2,
                text = "TextComment2",
                isDelete = false
            )
        )

        val actualResult = CommentsService.getByIdNote(1)
        val expectedResult = mutableListOf(
            Comment(
                noteId = 1,
                commentId = 2,
                text = "TextComment2",
            )
        )


        assertEquals(expectedResult, actualResult)

    }

    @Test
    fun getCommentIsTrueByIdComment() {
        NoteService.add(
            Note(
                nId = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        NoteService.add(
            Note(
                nId = 2,
                title = "TitleNote2",
                text = "TextNote",
                isDelete = true
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
        CommentsService.add(
            Comment(
                noteId = 1,
                commentId = 2,
                text = "TextComment2",
            )
        )
        CommentsService.add(
            Comment(
                noteId = 1,
                commentId = 3,
                text = "TextComment3",
            )
        )

        val actualResult = CommentsService.getById(3)
        val expectedResult =
            Comment(
                noteId = 1,
                commentId = 3,
                text = "TextComment3",
            )


        assertEquals(expectedResult, actualResult)

    }


    @Test(expected = CommentNotFoundException::class)
    fun getCommentIsFalseByIdComment() {
        NoteService.add(
            Note(
                nId = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        NoteService.add(
            Note(
                nId = 2,
                title = "TitleNote2",
                text = "TextNote",
                isDelete = true
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
        CommentsService.add(
            Comment(
                noteId = 1,
                commentId = 2,
                text = "TextComment2",
            )
        )
        CommentsService.add(
            Comment(
                noteId = 1,
                commentId = 3,
                text = "TextComment2",
            )
        )

        CommentsService.getById(4)
    }

    @Test(expected = CommentNotFoundException::class)
    fun getCommentIsFalseByStatusComment() {
        NoteService.add(
            Note(
                nId = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        NoteService.add(
            Note(
                nId = 2,
                title = "TitleNote2",
                text = "TextNote",
                isDelete = true
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
        CommentsService.add(
            Comment(
                noteId = 1,
                commentId = 2,
                text = "TextComment2",
            )
        )

        CommentsService.getById(1)
    }

    @Test
    fun getAllComments() {
        NoteService.add(
            Note(
                nId = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        NoteService.add(
            Note(
                nId = 2,
                title = "TitleNote2",
                text = "TextNote",
                isDelete = true
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
        CommentsService.add(
            Comment(
                noteId = 1,
                commentId = 2,
                text = "TextComment2",
            )
        )

        val actualResult = CommentsService.read()
        val expectedResult = mutableListOf(
            Comment(
                noteId = 1,
                commentId = 2,
                text = "TextComment2"
            )
        )


        assertEquals(expectedResult, actualResult)

    }

    @Test
    fun restoreCommentIsTrue() {
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
        CommentsService.add(
            Comment(
                noteId = 1,
                commentId = 2,
                text = "TextComment2"
            )
        )
        CommentsService.delete(2)
        val result = CommentsService.restore(2)

        assertTrue(result)
    }

    @Test
    fun restoreCommentIsFalseWithOtherIdComment() {
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
        CommentsService.add(
            Comment(
                noteId = 1,
                commentId = 2,
                text = "TextComment2"
            )
        )
        CommentsService.delete(1)
        val result = CommentsService.restore(3)

        assertFalse(result)
    }

    @Test
    fun restoreCommentIsFalseWithNoDeleteStatusComment() {
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
        CommentsService.add(
            Comment(
                noteId = 1,
                commentId = 2,
                text = "TextComment2"
            )
        )

        val result = CommentsService.restore(2)

        assertFalse(result)
    }

    @Test
    fun restoreNoteIsTrue() {
        NoteService.add(
            Note(
                nId = 1,
                title = "TitleNote",
                text = "TextNote",
                isDelete = true
            )
        )

        val result = NoteService.restore(1)
        assertTrue(result)
    }

    @Test
    fun restoreNoteIsFalseWithOtherId() {
        NoteService.add(
            Note(
                nId = 1,
                title = "TitleNote",
                text = "TextNote",
                isDelete = true
            )
        )

        val result = NoteService.restore(2)
        assertFalse(result)
    }

    @Test
    fun restoreNoteIsFalse() {
        NoteService.add(
            Note(
                nId = 1,
                title = "TitleNote",
                text = "TextNote",
                isDelete = false
            )
        )

        val result = NoteService.restore(1)
        assertFalse(result)
    }

}
