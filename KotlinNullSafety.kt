// ============================================
// 🚗 KOTLIN ESSENTIALS - Null Safety
// Contexto: Manejo seguro de datos opcionales
// ============================================

fun main() {
    println("=== Sistema de Navegación ===\n")
    
    // --- TIPOS NULLABLE ---
    // En Kotlin, por defecto las variables NO pueden ser null
    var conductor: String = "Carlos"
    // conductor = null  // ❌ Error de compilación
    
    // Para permitir null, usar ?
    var copiloto: String? = "María"
    copiloto = null  // ✅ Permitido
    
    println("Conductor: $conductor")
    println("Copiloto: $copiloto")
    
    
    // --- SAFE CALL OPERATOR (?.) ---
    println("\n--- Safe Call (?.) ---")
    
    var destino: String? = "Madrid"
    
    // Sin safe call (MAL - no compila):
    // println(destino.length)  // ❌ destino podría ser null
    
    // Con safe call (BIEN):
    println("Destino: $destino")
    println("Longitud del nombre: ${destino?.length}")  // 6
    
    destino = null
    println("Destino: $destino")
    println("Longitud del nombre: ${destino?.length}")  // null (no crashea)
    
    
    // --- ELVIS OPERATOR (?:) ---
    println("\n--- Elvis Operator (?:) ---")
    
    var rutaFavorita: String? = null
    
    // Si es null, usar valor por defecto
    val rutaActual = rutaFavorita ?: "Sin ruta definida"
    println("Ruta actual: $rutaActual")
    
    rutaFavorita = "Casa → Trabajo"
    val rutaActual2 = rutaFavorita ?: "Sin ruta definida"
    println("Ruta actual: $rutaActual2")
    
    
    // --- EJEMPLO PRÁCTICO ---
    println("\n--- Panel del Vehículo ---")
    
    mostrarPanelVehiculo(
        temperatura = 22,
        destinoNavegacion = "Barcelona",
        nombreCancion = "Bohemian Rhapsody"
    )
    
    println()
    
    mostrarPanelVehiculo(
        temperatura = null,  // Sensor desconectado
        destinoNavegacion = null,  // Sin navegación activa
        nombreCancion = null  // Sin música
    )
    
    
    // --- NOT-NULL ASSERTION (!!) ---
    println("\n--- Not-null Assertion (!!) ---")
    println("⚠️ Usar con precaución - puede causar NullPointerException")
    
    var bateriaConfirmada: Int? = 85
    // Solo usar !! cuando estés 100% seguro de que no es null
    val nivel: Int = bateriaConfirmada!!
    println("Batería: $nivel%")
    
    // ❌ Esto causaría crash:
    // bateriaConfirmada = null
    // val nivel2: Int = bateriaConfirmada!!  // NullPointerException!
    
    
    // --- LET (ejecutar código si no es null) ---
    println("\n--- Safe call con let ---")
    
    val ultimoDestino: String? = "Valencia"
    
    ultimoDestino?.let { destino ->
        println("📍 Último destino guardado: $destino")
        println("   Distancia: ${destino.length * 50} km (simulado)")
    }
    
    val destinoVacio: String? = null
    destinoVacio?.let { 
        println("Esto no se ejecutará porque es null")
    }
    println("(El bloque let no se ejecutó porque destinoVacio es null)")
}


// --- FUNCIÓN CON PARÁMETROS NULLABLE ---
fun mostrarPanelVehiculo(
    temperatura: Int?,
    destinoNavegacion: String?,
    nombreCancion: String?
) {
    println("╔══════════════════════════════╗")
    println("║      PANEL DEL VEHÍCULO      ║")
    println("╠══════════════════════════════╣")
    
    // Usando Elvis operator para valores por defecto
    val tempMostrar = temperatura?.let { "$it°C" } ?: "--°C"
    val destinoMostrar = destinoNavegacion ?: "Sin destino"
    val cancionMostrar = nombreCancion ?: "Sin reproducir"
    
    println("║ 🌡️ Temp: $tempMostrar".padEnd(31) + "║")
    println("║ 📍 Nav: $destinoMostrar".padEnd(31) + "║")
    println("║ 🎵 $cancionMostrar".padEnd(31) + "║")
    println("╚══════════════════════════════╝")
}
