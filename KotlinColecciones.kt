// ============================================
// 🚗 KOTLIN ESSENTIALS - Colecciones
// Contexto: Gestión de datos del coche
// ============================================

fun main() {
    println("=== Gestión de Datos del Vehículo ===\n")
    
    
    // --- LISTAS INMUTABLES (listOf) ---
    val modosConduccion = listOf("Eco", "Normal", "Sport")
    
    println("🎮 Modos disponibles: $modosConduccion")
    println("   Primer modo: ${modosConduccion[0]}")
    println("   Último modo: ${modosConduccion.last()}")
    println("   Total: ${modosConduccion.size} modos")
    
    
    // --- LISTAS MUTABLES (mutableListOf) ---
    println("\n--- Historial de viajes ---")
    
    val historialViajes = mutableListOf("Madrid", "Barcelona")
    println("📍 Viajes iniciales: $historialViajes")
    
    historialViajes.add("Valencia")
    historialViajes.add("Sevilla")
    println("📍 Después de añadir: $historialViajes")
    
    historialViajes.removeAt(0)
    println("📍 Después de eliminar primero: $historialViajes")
    
    
    // --- RECORRER LISTAS ---
    println("\n--- Puntos de carga cercanos ---")
    
    val puntosRecarga = listOf(
        "Estación Central - 2km",
        "Centro Comercial - 5km",
        "Gasolinera Repsol - 8km"
    )
    
    // Con for
    for (punto in puntosRecarga) {
        println("⚡ $punto")
    }
    
    // Con índice
    println()
    for ((index, punto) in puntosRecarga.withIndex()) {
        println("${index + 1}. $punto")
    }
    
    // Con forEach (estilo funcional)
    println("\n--- Usando forEach ---")
    puntosRecarga.forEach { punto ->
        println("📍 $punto")
    }
    
    
    // --- MAPS (diccionarios clave-valor) ---
    println("\n--- Especificaciones del vehículo ---")
    
    val especificaciones = mapOf(
        "marca" to "CUPRA",
        "modelo" to "Born",
        "año" to 2025,
        "bateria" to "75 kWh",
        "autonomia" to "450 km"
    )
    
    println("🚗 Marca: ${especificaciones["marca"]}")
    println("📅 Año: ${especificaciones["año"]}")
    
    // Recorrer map
    println("\n📋 Ficha completa:")
    for ((clave, valor) in especificaciones) {
        println("   $clave: $valor")
    }
    
    
    // --- MAP MUTABLE ---
    println("\n--- Sensores en tiempo real ---")
    
    val sensores = mutableMapOf(
        "velocidad" to 80,
        "bateria" to 65,
        "temperatura" to 22
    )
    
    println("📊 Estado inicial: $sensores")
    
    // Actualizar valor
    sensores["velocidad"] = 100
    // Añadir nuevo sensor
    sensores["presionNeumaticos"] = 32
    
    println("📊 Estado actualizado: $sensores")
    
    
    // --- OPERACIONES ÚTILES CON LISTAS ---
    println("\n--- Análisis de consumos ---")
    
    val consumosUltimosViajes = listOf(14.5, 16.2, 13.8, 15.1, 14.9)
    
    println("📈 Consumos (kWh/100km): $consumosUltimosViajes")
    println("   Media: ${"%.2f".format(consumosUltimosViajes.average())}")
    println("   Mínimo: ${consumosUltimosViajes.min()}")
    println("   Máximo: ${consumosUltimosViajes.max()}")
    println("   Total: ${consumosUltimosViajes.sum()}")
    
    // Filtrar
    val consumosAltos = consumosUltimosViajes.filter { it > 15 }
    println("   Consumos altos (>15): $consumosAltos")
    
    // Transformar
    val consumosRedondeados = consumosUltimosViajes.map { it.toInt() }
    println("   Redondeados: $consumosRedondeados")
}
