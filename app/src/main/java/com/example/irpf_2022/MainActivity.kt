package com.example.irpf_2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import com.example.irpf_2022.R.id


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val calcButton = findViewById<Button>(id.calculo)
        val getSalario = findViewById<TextView>(id.numSal)
        val getInss = findViewById<TextView>(id.numInss)
        val getResultado = findViewById<TextView>(id.resultado)
        val getFilhos = findViewById<TextView>(id.numFilhos)


        val getSeekBar = findViewById<SeekBar>(id.filhosSeekBar)
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
            //function que faz o calculo
            getResultado.text = calculoIR(base)

        }
    }
}