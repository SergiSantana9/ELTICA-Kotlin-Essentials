// ============================================
// 🚗 KOTLIN ESSENTIALS - Secuencias de escape
// Contexto: Secuencias de esc
// ============================================

fun main() {
    println("--- 1. SECUENCIAS DE FORMATO ---")
    // \n: Nueva línea
    // \t: Tabulación
    println("Lista de tareas:\n\t1. Aprender Kotlin\n\t2. Practicar escapes")
    
    // \r: Retorno de carro (Vuelve al inicio de la línea y sobrescribe)
    print("Descargando archivo... 0%\r")
    Thread.sleep(500) // Pausa breve para simular espera
    println("Descargando archivo... 100% (Completado)")

    println("\n--- 2. ESCAPAR CARACTERES ESPECIALES ---")
    // \": Comillas dentro de un String
    // \\: Imprimir una barra invertida
    // \$: Símbolo de dólar (evita que Kotlin piense que es una variable)
    val producto = "Laptop"
    val precio = 800
    println("El producto \"$producto\" cuesta \$ $precio.")
    println("Ruta del sistema: C:\\Archivos de programa\\Kotlin")

    println("\n--- 3. UNICODE (SÍMBOLOS) ---")
    // \uXXXX: Caracteres especiales mediante su código
    println("Matemáticas: \u2211 (Suma) y \u221E (Infinito)")
    println("Iconos: \u2615 (Café) y \u2714 (Check)")

    println("\n--- 4. RAW STRINGS (SIN ESCAPES) ---")
    // Con triple comilla no necesitas usar \n ni escapar comillas
    val mensajeVisual = """
        Este es un 'Raw String'.
        No necesito poner \n para saltar de línea.
        Puedo poner "comillas" y barras \ fácilmente.
        Ideal para bloques de texto grandes.
    """.trimIndent()
    
    println(mensajeVisual)
}
