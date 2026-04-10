// ============================================
// 🚗 KOTLIN ESSENTIALS - Clases y Objetos
// Contexto: Modelar un coche eléctrico
// ============================================

fun main() {
    println("=== Sistema de Gestión de Flota ===\n")
    
    // Crear objetos (instancias de la clase)
    val miCoche = CocheElectrico(
        marca = "CUPRA",
        modelo = "Raval",
        capacidadBateria = 75.0
    )
    
    val cocheEmpresa = CocheElectrico("SEAT", "Leon", 80.0)
    
    // Usar el coche
    miCoche.mostrarInfo()
    
    println("\n--- Simulación de viaje ---")
    miCoche.conducir(50)
    miCoche.conducir(100)
    miCoche.conducir(200)
    
    println("\n--- Recargando ---")
    miCoche.cargar(30)
    miCoche.mostrarEstadoBateria()
    
    // Usar data class
    println("\n--- Punto de carga ---")
    val estacion1 = PuntoCarga("EC001", "Estación Central", 150, true)
    val estacion2 = PuntoCarga("EC002", "Centro Comercial", 50, false)
    
    println(estacion1)  // toString() automático
    println("¿Disponible? ${estacion1.disponible}")
    
    // Copy de data class
    val estacion1Ocupada = estacion1.copy(disponible = false)
    println("Actualizada: $estacion1Ocupada")
    
    // Comparación (data class genera equals automáticamente)
    val estacion3 = PuntoCarga("EC001", "Estación Central", 150, true)
    println("\n¿estacion1 == estacion3? ${estacion1 == estacion3}")  // true
}


// --- CLASE BÁSICA ---
class CocheElectrico(
    val marca: String,              // Propiedad inmutable
    val modelo: String,
    val capacidadBateria: Double    // kWh
) {
    // Propiedad con valor inicial
    var nivelBateria: Int = 100
        private set  // Solo se puede modificar desde dentro de la clase
    
    var kilometraje: Int = 0
        private set
    
    // Propiedad calculada (no almacena valor, se calcula cada vez)
    val autonomiaRestante: Int
        get() = ((nivelBateria / 100.0) * capacidadBateria / 0.15).toInt()
    
    
    // --- MÉTODOS ---
    fun mostrarInfo() {
        println("🚗 $marca $modelo")
        println("   Batería: $capacidadBateria kWh")
        println("   Kilometraje: $kilometraje km")
        mostrarEstadoBateria()
    }
    
    fun mostrarEstadoBateria() {
        val barra = "█".repeat(nivelBateria / 10) + "░".repeat(10 - nivelBateria / 10)
        println("   🔋 [$barra] $nivelBateria% | Autonomía: $autonomiaRestante km")
    }
    
    fun conducir(km: Int) {
        // Consumo aproximado: 15 kWh/100km
        val consumo = (km * 15.0 / 100.0)
        val bateriaConsumida = ((consumo / capacidadBateria) * 100).toInt()
        
        if (bateriaConsumida > nivelBateria) {
            println("❌ No hay suficiente batería para recorrer $km km")
            println("   Autonomía actual: $autonomiaRestante km")
            return
        }
        
        nivelBateria -= bateriaConsumida
        kilometraje += km
        
        println("✅ Recorridos $km km | Batería restante: $nivelBateria%")
    }
    
    fun cargar(porcentaje: Int) {
        nivelBateria = (nivelBateria + porcentaje).coerceAtMost(100)
        println("⚡ Cargado. Nivel actual: $nivelBateria%")
    }
}


// --- DATA CLASS (para modelar datos) ---
// Genera automáticamente: toString(), equals(), hashCode(), copy()
data class PuntoCarga(
    val id: String,
    val nombre: String,
    val potenciaKw: Int,
    val disponible: Boolean
)
