package dev.tiagosilva.finalprojectclassroom

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class TaskActivity : AppCompatActivity() {
    val uid = FirebaseAuth.getInstance().currentUser?.uid
    val db_ref = FirebaseDatabase.getInstance().getReference("/users/$uid/tasks")

    var taskId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        loadTask()

        val in_date = findViewById<EditText>(R.id.in_date)
        val in_time = findViewById<EditText>(R.id.in_time)

        val current_date_time = Calendar.getInstance()
        val day = current_date_time.get(Calendar.DAY_OF_MONTH)
        val month = current_date_time.get(Calendar.MONTH)
        val year = current_date_time.get(Calendar.YEAR)
        val hour = current_date_time.get(Calendar.HOUR_OF_DAY)
        val minute = current_date_time.get(Calendar.MINUTE)

        in_date.setText(String.format("%02d/%02d/%04d", day, month + 1, year))
        in_time.setText(String.format("%02d:%02d", hour, minute))

        findViewById<Button>(R.id.btn_date).setOnClickListener{
            val datePickerDialog = DatePickerDialog(this, {_, yearOfYear, monthOfYear, dayOfMonth ->
                in_date.setText(String.format("%02d/%02d/%04d", dayOfMonth, monthOfYear + 1, yearOfYear))
            }, year, month, day)
            datePickerDialog.show()
        }

        findViewById<Button>(R.id.btn_time).setOnClickListener{
            val timePickerDialog = TimePickerDialog(this, {_, hourOfDay, minuteOfHour ->
                in_time.setText(String.format("%02d:%02d", hourOfDay, minuteOfHour))
            }, hour, minute, true)
            timePickerDialog.show()
        }

        findViewById<Button>(R.id.btn_save_task).setOnClickListener{
            createUpdateTask()
        }
    }

    fun loadTask(){
        this.taskId = intent.getStringExtra("id") ?: ""
        if(taskId === "") return
//        TODO: Carregar tarefa
    }

    fun createUpdateTask(){
        if(taskId !== ""){
//            TODO: Atualizar tarefa
        }else{
            val titulo = findViewById<EditText>(R.id.titulo)
            val descricao = findViewById<EditText>(R.id.titulo)
            val data = findViewById<EditText>(R.id.titulo)
            val hora = findViewById<EditText>(R.id.titulo)

            val task =  hashMapOf(
                "titulo" to titulo.text.toString(),
                "descricao" to descricao.text.toString(),
                "data" to data.text.toString(),
                "hora" to hora.text.toString(),
            )

            val novoElemento = db_ref.push()
            novoElemento.setValue(task)

            Toast.makeText(this, "Tarefa criada com sucesso!", Toast.LENGTH_SHORT).show()
        }
    }
}