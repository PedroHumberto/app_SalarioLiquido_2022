package com.example.irpf_2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import com.example.irpf_2022.R.id


class MainActivity : AppCompatActivity() {


    private var salario: Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val calcButton = findViewById<Button>(id.calculo)
        val getSalario = findViewById<TextView>(id.numSal)
        val getResultado = findViewById<TextView>(id.resultado)
        val getPensao = findViewById<TextView>(id.numPensao)
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
            salario = getSalario.text.toString().toFloat()
            val pensao: Float = getPensao.text.toString().toFloat()
            val filhos: Byte = getFilhos.text.toString().toByte()
            var inss = calcInss(salario)
            var irpf = calculoIR(salario)
            val salarioLiquido = (salario - inss - pensao - irpf - (filhos * 189.59f))





            getResultado.text = "Salario Liquido: ${"%.2f".format(salarioLiquido)}"

        }
        //"%.2f".format(result)

    }

    private fun calcInss(contribuicaoInss: Float): Float {
        return if (contribuicaoInss <= 1659.38f) {
            contribuicaoInss * 0.08f
        } else if(contribuicaoInss in 1659.39f..2765.66f){
            contribuicaoInss * 0.09f
        } else if(contribuicaoInss in  2765.67f..5531.31f){
            contribuicaoInss * 0.11f
        } else{
            608.44f
        }
    }

}