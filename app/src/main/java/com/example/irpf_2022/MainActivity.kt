package com.example.irpf_2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val calcButton = findViewById<Button>(R.id.calculo)
        val getSalario = findViewById<TextView>(R.id.numSal)
        val getInss = findViewById<TextView>(R.id.numInss)
        val getResultado = findViewById<TextView>(R.id.resultado)
        val getFilhos = findViewById<TextView>(R.id.numFilhos)


        val getSeekBar = findViewById<SeekBar>(R.id.filhosSeekBar)
        getSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                getFilhos.text = progress.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }
            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })



        calcButton.setOnClickListener {
            var salario: Float = getSalario.text.toString().toFloat()
            val inss: Float = getInss.text.toString().toFloat()
            val filhos: Byte = getFilhos.text.toString().toByte()
            val base = (salario - inss - (filhos * 189.59f))



            if (base <= 1903.98) {
                Toast.makeText(this,
                    "Não possui retenção seu salario base é $base",
                    Toast.LENGTH_LONG).show()
        }
            else if(base >= 1903.98f && base <= 2826.65f){
                var result = (base * 0.075f) - 142.80f
                getResultado.text = "Resultado: Deve reter R$${ "%.2f".format(result)}"

            }
            else if(base >= 2826.66f && base <= 3751.05f){
                var result = (base * 0.15f) - 354.80f
                getResultado.text = "Resultado: Deve reter R$${ "%.2f".format(result)}"
            }
            else{
                var result = (base * 0.225f) - 636.13f
                getResultado.text = "Resultado: Deve reter R$${ "%.2f".format(result)}"
            }
        }
    }
}