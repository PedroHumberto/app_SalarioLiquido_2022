package com.example.irpf_2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val calcButton = findViewById<Button>(R.id.calculo)
        val getSalario = findViewById<TextView>(R.id.numSal)
        val getInss = findViewById<TextView>(R.id.numInss)
        val getFilhos = findViewById<TextView>(R.id.numFilhos)
        val getTeste = findViewById<TextView>(R.id.resultado)

        calcButton.setOnClickListener {
            val salario = getSalario.text.toString().toDouble()
            val inss = getInss.text.toString().toDouble()
            val filhos = getFilhos.text.toString().toDouble()
            var base = (salario - inss - (filhos * 189.59))


            if (base <= 1903.98) {
                Toast.makeText(this,
                    "Não possui retenção seu salario base é $base",
                    Toast.LENGTH_LONG).show()
        }
            else if(base >= 1903.98 && base <= 2826.65){
                var result = (base * 0.075) - 142.80
                getTeste.text = "Deve reter ${ "%.2f".format(result)}"

            }else{
                Toast.makeText(this, "Deve reter", Toast.LENGTH_LONG).show()
            }

        }
    }
}