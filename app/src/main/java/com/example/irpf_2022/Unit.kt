package com.example.irpf_2022

fun calculoIR(salarioLiquido: Float): Float {
    return if (salarioLiquido <= 1903.98) {
        0f
    }
    else if(salarioLiquido in 1903.98f..2826.65f) {
        (salarioLiquido * 0.075f)
    }
    else if(salarioLiquido in 2826.66f..3751.05f) {
        (salarioLiquido * 0.15f)
    }
    else {
        (salarioLiquido * 0.225f)
    }
}