package com.example.irpf_2022

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.*
import com.example.irpf_2022.R.id
import java.io.*
import java.util.*


class MainActivity : AppCompatActivity() {


    private var salario: Float = 0f
    private var pensao: Float = 0f
    private var filhos: Byte = 0
    private var inss: Float = 0f
    private var irpf: Float = 0f
    private var salarioLiquido: Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val calcButton = findViewById<Button>(id.calculo)
        val getSalario = findViewById<TextView>(id.numSal)
        val getResultado = findViewById<TextView>(id.resultado)
        val getPensao = findViewById<TextView>(id.numPensao)
        val getFilhos = findViewById<TextView>(id.numFilhos)
        val btnSave = findViewById<Button>(id.btn_salvar)
        val btnHistorico = findViewById<Button>(id.btn_consultar)



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

            if(getSalario.text.isEmpty()){
                Toast.makeText(this, "PREENCHA O SALARIO", Toast.LENGTH_SHORT).show()
            }
            else{
                salario = getSalario.text.toString().toFloat()
                pensao = getPensao.text.toString().toFloat()
                filhos = getFilhos.text.toString().toByte()
                inss = calcInss(salario)
                irpf = calculoIR(salario)
                salarioLiquido = (salario - inss - pensao - irpf - (filhos * 189.59f))

                getResultado.text = "Salario Liquido: ${"%.2f".format(salarioLiquido)}"
            }

        }

        btnSave.setOnClickListener{

            try {
                var outWrite = OutputStreamWriter(openFileOutput("resultados.txt", MODE_PRIVATE))
                var currentTime :Date = Calendar.getInstance().time
                outWrite.write("Data:$currentTime \nSalario: $salario\nPensao: $pensao\n" +
                        "Filhos: $filhos\nINSS: $inss\nIRPF: $irpf\nSalario Liquido: $salarioLiquido\n" +
                        "Total dos Descontos: ${pensao + inss + irpf + (filhos * 189.59f)}\n\n")
                outWrite.close()
                Toast.makeText(this, "Arquivo Salvo", Toast.LENGTH_SHORT).show()
            }catch (e: FileNotFoundException){
                e.printStackTrace()

            }catch (e: IOException){
                e.printStackTrace()
            }

        }

        btnHistorico.setOnClickListener {
            val initIntent = Intent(this, ResultadoTxtActivity::class.java)
            startActivity(initIntent)
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