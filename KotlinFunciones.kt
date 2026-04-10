// ============================================
// 🚗 KOTLIN ESSENTIALS - Funciones
// Contexto: Funciones del sistema del coche
// ============================================

fun main() {
    println("=== Sistema del Vehículo ===\n")
    
    // Llamar funciones básicas
    saludarConductor("Carlos")
    mostrarEstado()
    
    // Funciones con retorno
    val autonomia = calcularAutonomia(80, 6.5)
    println("\n📏 Autonomía estimada: $autonomia km")
    
    // Función con valor por defecto
    println("\n--- Configuración de clima ---")
    configurarClima()                    // Usa valores por defecto
    configurarClima(25)                  // Solo temperatura
    configurarClima(22, "alta")          // Ambos parámetros
    
    // Argumentos nombrados
    configurarClima(ventilacion = "baja", temperatura = 20)
    
    // Función de una línea
    val consumoViaje = calcularConsumo(150.0, 12.5)
    println("\n⚡ Consumo del viaje: $consumoViaje kWh/100km")
    
    // Función con nullable
    println("\n--- Sistema de navegación ---")
    procesarDestino("Madrid")
    procesarDestino(null)
    
    // Función que no retorna nada útil (Unit)
    activarAlarma()
}


// --- FUNCIÓN BÁSICA (sin retorno) ---
fun saludarConductor(nombre: String) {
    println("👋 ¡Hola $nombre! Bienvenido a tu vehículo")
}

fun mostrarEstado() {
    println("✅ Sistemas inicializados correctamente")
}


// --- FUNCIÓN CON RETORNO ---
fun calcularAutonomia(bateria: Int, consumoPor100km: Double): Int {
    // bateria está en %, asumimos 75 kWh de capacidad total
    val energiaDisponible = 75.0 * bateria / 100
    val autonomiaKm = (energiaDisponible / consumoPor100km) * 100
    return autonomiaKm.toInt()
}


// --- FUNCIÓN CON VALORES POR DEFECTO ---
fun configurarClima(temperatura: Int = 22, ventilacion: String = "media") {
    println("🌡️ Clima: ${temperatura}°C | Ventilación: $ventilacion")
}


// --- FUNCIÓN DE UNA SOLA EXPRESIÓN ---
fun calcularConsumo(km: Double, kwhUsados: Double): Double = (kwhUsados / km) * 100


// --- FUNCIÓN CON NULLABLE (el parámetro puede ser null) ---
fun procesarDestino(destino: String?) {
    if (destino != null) {
        println("📍 Navegando hacia: $destino")
    } else {
        println("📍 Sin destino configurado")
    }
    
    // Forma más elegante con Elvis operator
    val destinoFinal = destino ?: "Sin destino"
    println("   Destino actual: $destinoFinal")
}


// --- FUNCIÓN QUE RETORNA UNIT (equivalente a void) ---
fun activarAlarma(): Unit {
    println("\n🚨 ¡ALARMA ACTIVADA!")
}
