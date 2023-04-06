package dev.tiagosilva.finalprojectclassroom.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import dev.tiagosilva.finalprojectclassroom.R

class SenhaDificuldade : Fragment() {
    private lateinit var textViewDifficultyValue: TextView
    lateinit var etPassword: EditText
    lateinit var text: Editable;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_senha_dificuldade, container, false)

        textViewDifficultyValue = view.findViewById<TextView>(R.id.textViewDifficultyValue)
        etPassword = view.findViewById<EditText>(R.id.etPassword)

        etPassword.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (s != null) {
                    text = s
                }

                if(text.toString().length == 0){
                    textViewDifficultyValue.text = resources.getString(R.string.sem_senha)
                }else if(text.toString().length < 6) {
                    textViewDifficultyValue.text = resources.getString(R.string.senha_muito_fraca)
                }else if(text.toString().length < 8){
                    textViewDifficultyValue.text = resources.getString(R.string.senha_fraca)
                }else if(text.toString().length < 10) {
                    textViewDifficultyValue.text = resources.getString(R.string.senha_media)
                }else if(text.toString().length < 12) {
                    textViewDifficultyValue.text = resources.getString(R.string.senha_forte)
                }else {
                    textViewDifficultyValue.text = resources.getString(R.string.senha_muito_forte)
                }
            }
        })

        return view
    }
}