package dev.tiagosilva.finalprojectclassroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.view.View
import android.widget.ArrayAdapter
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private var gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        sharedPreferences = getSharedPreferences("lista_de_tarefas", Context.MODE_PRIVATE)

        val listViewTasks = findViewById<android.widget.ListView>(R.id.listViewTasks);
        val createTask = findViewById<android.widget.EditText>(R.id.createTask);

//        val itemList = arrayListOf<String>();
        val itemList = getData();

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, itemList);

        listViewTasks.adapter = adapter;
        adapter.notifyDataSetChanged()

        findViewById<View>(R.id.add).setOnClickListener {
            itemList.add(createTask.text.toString());
            listViewTasks.adapter = adapter;
            adapter.notifyDataSetChanged()

            saveData(itemList)
            createTask.text.clear()
        }

        findViewById<View>(R.id.delete).setOnClickListener {
            val position: SparseBooleanArray = listViewTasks.checkedItemPositions;
            val count = listViewTasks.count;
            var item = count - 1;
            while(item >= 0){
                if(position.get(item)){
                    adapter.remove(itemList.get(item))
                }
                item--;
            }

            saveData(itemList)
            position.clear();
            adapter.notifyDataSetChanged()
        }

        findViewById<View>(R.id.clear).setOnClickListener{
            itemList.clear()
            saveData(itemList)
            adapter.notifyDataSetChanged()
        }

        findViewById<View>(R.id.logout).setOnClickListener{
            val activity = Intent(this, LoginScreen::class.java);
            startActivity(activity);
        }
    }

    private fun getData(): ArrayList<String> {
        val arrayJson = sharedPreferences.getString("lista", null);
        return if(arrayJson.isNullOrEmpty()){
            arrayListOf();
        }else{
            gson.fromJson(arrayJson, object: TypeToken<ArrayList<String>>(){}.type)
        }
    }

    private fun saveData(array: ArrayList<String>){
        val arrayJson = gson.toJson(array);
        val editor = sharedPreferences.edit();
        editor.putString("lista", arrayJson);
        editor.apply();
    }
}