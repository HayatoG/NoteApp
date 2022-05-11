package br.senac.noteapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import br.senac.noteapp.data.AppDatabase
import br.senac.noteapp.data.Note
import br.senac.noteapp.data.Notes
import br.senac.noteapp.databinding.ActivityNewNoteBinding

class NewNoteActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnAdd.setOnClickListener {

            val note = Note(title = binding.etTitle.text.toString(), desc = binding.etDesc.text.toString())

            //Notes.listNotes.add(note)
            Thread {
                insertNote(note)
                finish()
            }.start()


        }

    }

     fun insertNote(note: Note) {
         val db = Room.databaseBuilder(this, AppDatabase::class.java, "db").build()

         db.noteDao().insert(note)

     }

}