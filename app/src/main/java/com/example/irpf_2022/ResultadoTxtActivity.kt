package com.example.irpf_2022

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader

class ResultadoTxtActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado_txt)

        var btnCarregar = findViewById<Button>(R.id.btn_carregar)
        var resultado = findViewById<TextView>(R.id.edit_resultado)

        btnCarregar.setOnClickListener{
            try {
                val file = openFileInput("resultados.txt")
                val inputStreamReader = InputStreamReader(file)
                val bufferedReader = BufferedReader(inputStreamReader)
                val sb = StringBuilder()
                var line: String?
                while (bufferedReader.readLine().also { line = it } != null) {
                    sb.append("\n").append(line)
                }
                inputStreamReader.close()
                resultado.text = sb

            }catch (e: FileNotFoundException){
                e.printStackTrace()
            }catch (e: IOException){
                e.printStackTrace()
            }
        }


    }


}