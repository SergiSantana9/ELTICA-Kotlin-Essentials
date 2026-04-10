// ============================================
// 🚗 KOTLIN ESSENTIALS - Variables y Tipos
// Contexto: Sistema de un coche eléctrico
// ============================================

fun main() {
    // --- VAL vs VAR ---
    // val = inmutable (no puede cambiar)
    // var = mutable (puede cambiar)
    
    
    val marcaCoche = "CUPRA" // No cambiará
    val modeloCoche = "Raval" // No cambiará
    var nivelBateria = 85           // Cambiará durante el viaje
    
    println("🚗 Coche: $marcaCoche")
    println("🚗 Modelo: $modeloCoche")
    println("🔋 Batería inicial: $nivelBateria%")
    
    // Simulamos conducir
    nivelBateria = 72
    println("🔋 Batería después de conducir: $nivelBateria%")
    
    // ❌ Esto daría error (descomentar para probar):
    // marcaCoche = "SEAT"  // Val cannot be reassigned
    
    
    // --- TIPOS BÁSICOS ---
    val modelo: String = "Raval"             // Texto
    val año: Int = 2026                      // Número entero
    val capacidadBateria: Double = 75.5      // Número decimal
    val estaEncendido: Boolean = true        // Verdadero/Falso
    val autonomiaKm: Float = 450.5f          // Decimal (menos precisión)
    
    println("\n📋✅ Ficha técnica:")
    println("   Modelo: $modelo ($año)")
    println("   Batería: $capacidadBateria kWh")
    println("   Autonomía: $autonomiaKm km")
    println("   Encendido: $estaEncendido")
    
    
    // --- INFERENCIA DE TIPOS ---
    // Kotlin es inteligente, no siempre necesitas especificar el tipo
    val temperatura = 22.5          // Kotlin sabe que es Double
    val asientosDisponibles = 5     // Kotlin sabe que es Int
    val enMovimiento = false        // Kotlin sabe que es Boolean
    
    println("\n🌡️ Temperatura interior: $temperatura°C")
    
    
    // --- STRING TEMPLATES ---
    // Usar $ para insertar variables en textos
    val conductor = "María"
    val destino = "Madrid"
    val distancia = 150
    
    println("\n📍 $conductor viaja a $destino (${distancia}km)")
    println("⚡ Consumo estimado: ${distancia * 0.15} kWh")
}
