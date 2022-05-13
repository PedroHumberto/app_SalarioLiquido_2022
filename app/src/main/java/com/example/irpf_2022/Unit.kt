package com.example.irpf_2022

import android.widget.Toast

fun calculoIR(base: Float): String {
    if (base <= 1903.98) {
       return "Salario não retem"
    }
    else if(base >= 1903.98f && base <= 2826.65f){
        var result = (base * 0.075f) - 142.80f
       return "Resultado: Deve reter R$${ "%.2f".format(result)}"

    }
    else if(base >= 2826.66f && base <= 3751.05f){
        var result = (base * 0.15f) - 354.80f
       return "Resultado: Deve reter R$${ "%.2f".format(result)}"
    }
    else{
        var result = (base * 0.225f) - 636.13f
       return "Resultado: Deve reter R$${ "%.2f".format(result)}"
    }
}