data class Note(
    val nId: Int,
    val title: String,
    val text: String,
    val comments: MutableList<Comment> = mutableListOf(),
    val isDelete: Boolean = false
) {}

object NoteService : CrudService<Note> {
    internal val notes = mutableListOf<Note>()
    private var lastId = 0


    override fun add(item: Note): Boolean {
        lastId++
        val updatedNote = item.copy(nId = lastId)
        notes.add(updatedNote)
        return true
    }

    fun createComment(comment: Comment): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (note.nId == comment.noteId) {
                notes[index] = note.copy(comments = (note.comments + comment) as MutableList<Comment>)
                return true
            }
        }
        throw NoteNotFoundException("not note with id: ${comment.noteId}")
    }

    override fun delete(id: Int): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (id == note.nId && !note.isDelete) {
                val updateNote = note.copy(isDelete = true)
                notes[index] = updateNote
                return true
            }
        }
        throw NoteNotFoundException("not note with id: $id")
    }


    override fun edit(item: Note): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (note.nId == item.nId && !note.isDelete) {
                notes[index] = item
                return true
            }
        }
        throw NoteNotFoundException("not not with id: ${item.nId}")
    }


    override fun read(): MutableList<Note> {
        val notesForGet = mutableListOf<Note>()
        for (note in notes) {
            if (!note.isDelete) {
                notesForGet += note
            }
        }
        return notesForGet

    }

    override fun getById(id: Int): Note {
        for (note in notes) {
            if (note.nId == id && !note.isDelete) return note
        }
        throw NoteNotFoundException("not note with id: $id")
    }


    override fun restore(id: Int): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (id == note.nId && note.isDelete) {
                val updateNote = note.copy(isDelete = false)
                notes[index] = updateNote
                return true
            }
        }
        return false
    }
}




