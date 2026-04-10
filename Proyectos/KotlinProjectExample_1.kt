// ============================================
// 📍 PROYECTO: Sistema de Navegación y Carga
// Ejemplo 1
// ============================================

fun main() {
    println("╔═══════════════════════════════════════╗")
    println("║   📍 SISTEMA DE NAVEGACIÓN EV        ║")
    println("╚═══════════════════════════════════════╝\n")
    
    // Crear el sistema de navegación
    val navegacion = SistemaNavegacion()
    
    // Registrar puntos de carga
    navegacion.agregarPuntoCarga(
        PuntoDeCarga("PC001", "Electrolinera Central", 5.2, 150, TipoCargador.SUPERCHARGER, true)
    )
    navegacion.agregarPuntoCarga(
        PuntoDeCarga("PC002", "Centro Comercial Norte", 8.7, 50, TipoCargador.RAPIDO, true)
    )
    navegacion.agregarPuntoCarga(
        PuntoDeCarga("PC003", "Parking Municipal", 2.1, 22, TipoCargador.NORMAL, false)
    )
    navegacion.agregarPuntoCarga(
        PuntoDeCarga("PC004", "Gasolinera Repsol", 12.3, 50, TipoCargador.RAPIDO, true)
    )
    navegacion.agregarPuntoCarga(
        PuntoDeCarga("PC005", "Hotel Playa", 15.8, 7, TipoCargador.LENTO, true)
    )
    
    // Estado del vehículo
    val miCoche = EstadoVehiculo(
        bateriaActual = 25,
        consumoPor100km = 16.0
    )
    
    // Mostrar estado actual
    println("🚗 Estado del vehículo:")
    println("   Batería: ${miCoche.bateriaActual}%")
    println("   Autonomía: ${miCoche.autonomiaKm} km")
    
    // Buscar puntos de carga cercanos
    println("\n${"=".repeat(50)}")
    navegacion.buscarPuntosCercanos(maxDistancia = 10.0)
    
    // Filtrar por tipo
    println("\n${"=".repeat(50)}")
    navegacion.buscarPorTipo(TipoCargador.RAPIDO)
    
    // Planificar ruta con paradas de carga
    println("\n${"=".repeat(50)}")
    val destino = Destino("Madrid", 280.0)
    navegacion.planificarRuta(miCoche, destino)
    
    // Simular navegación
    println("\n${"=".repeat(50)}")
    navegacion.iniciarNavegacion(destino)
    
    // Estadísticas
    println("\n${"=".repeat(50)}")
    navegacion.mostrarEstadisticas()
}


// Enum para tipos de cargador
enum class TipoCargador(val potenciaKw: Int, val emoji: String) {
    LENTO(7, "🔌"),
    NORMAL(22, "⚡"),
    RAPIDO(50, "⚡⚡"),
    SUPERCHARGER(150, "🔥")
}


// Data class para puntos de carga
data class PuntoDeCarga(
    val id: String,
    val nombre: String,
    val distanciaKm: Double,
    val potenciaKw: Int,
    val tipo: TipoCargador,
    val disponible: Boolean
)


// Data class para destinos
data class Destino(
    val nombre: String,
    val distanciaKm: Double
)


// Estado del vehículo
data class EstadoVehiculo(
    val bateriaActual: Int,  // Porcentaje
    val consumoPor100km: Double  // kWh
) {
    val capacidadBateria = 75.0  // kWh
    val autonomiaKm: Int
        get() = ((bateriaActual / 100.0) * capacidadBateria / consumoPor100km * 100).toInt()
}


class SistemaNavegacion {
    private val puntosCarga = mutableListOf<PuntoDeCarga>()
    private val historialBusquedas = mutableListOf<String>()
    
    
    fun agregarPuntoCarga(punto: PuntoDeCarga) {
        puntosCarga.add(punto)
    }
    
    
    fun buscarPuntosCercanos(maxDistancia: Double) {
        println("📍 PUNTOS DE CARGA CERCANOS (< $maxDistancia km)")
        println("─".repeat(50))
        
        val cercanos = puntosCarga
            .filter { it.distanciaKm <= maxDistancia }
            .sortedBy { it.distanciaKm }
        
        if (cercanos.isEmpty()) {
            println("❌ No se encontraron puntos de carga cercanos")
            return
        }
        
        cercanos.forEach { punto ->
            val estadoDisp = if (punto.disponible) "🟢 Disponible" else "🔴 Ocupado"
            println("""
                |${punto.tipo.emoji} ${punto.nombre}
                |   📏 ${punto.distanciaKm} km | ⚡ ${punto.potenciaKw} kW | $estadoDisp
            """.trimMargin())
        }
        
        historialBusquedas.add("Búsqueda cercanos: ${cercanos.size} resultados")
    }
    
    
    fun buscarPorTipo(tipo: TipoCargador) {
        println("🔍 PUNTOS DE CARGA TIPO: ${tipo.name}")
        println("─".repeat(50))
        
        val filtrados = puntosCarga
            .filter { it.tipo == tipo && it.disponible }
            .sortedBy { it.distanciaKm }
        
        if (filtrados.isEmpty()) {
            println("❌ No se encontraron cargadores ${tipo.name} disponibles")
            return
        }
        
        filtrados.forEach { punto ->
            println("${tipo.emoji} ${punto.nombre} - ${punto.distanciaKm} km")
        }
        
        historialBusquedas.add("Búsqueda por tipo $tipo: ${filtrados.size} resultados")
    }
    
    
    fun planificarRuta(vehiculo: EstadoVehiculo, destino: Destino) {
        println("🗺️ PLANIFICACIÓN DE RUTA")
        println("─".repeat(50))
        println("📍 Destino: ${destino.nombre}")
        println("📏 Distancia total: ${destino.distanciaKm} km")
        println("🔋 Autonomía actual: ${vehiculo.autonomiaKm} km")
        
        if (vehiculo.autonomiaKm >= destino.distanciaKm) {
            println("\n✅ ¡Puedes llegar sin parar a cargar!")
            println("   Batería estimada al llegar: ${calcularBateriaRestante(vehiculo, destino.distanciaKm)}%")
        } else {
            println("\n⚠️ Necesitas parar a cargar")
            
            // Encontrar paradas óptimas
            val paradasNecesarias = calcularParadas(vehiculo, destino)
            
            println("\n📋 Ruta recomendada:")
            var distanciaAcumulada = 0.0
            var bateriaActual = vehiculo.bateriaActual
            
            println("   🏁 Inicio (Batería: $bateriaActual%)")
            
            paradasNecesarias.forEachIndexed { index, parada ->
                println("   ↓")
                println("   📍 Parada ${index + 1}: ${parada.nombre}")
                println("      Distancia: ${parada.distanciaKm} km | Carga: ${parada.potenciaKw} kW")
                println("      Tiempo estimado de carga: ${calcularTiempoCarga(parada.potenciaKw)} min")
            }
            
            println("   ↓")
            println("   🏁 ${destino.nombre}")
        }
    }
    
    
    private fun calcularBateriaRestante(vehiculo: EstadoVehiculo, distancia: Double): Int {
        val consumo = (distancia / 100) * vehiculo.consumoPor100km
        val consumoPorcentaje = (consumo / vehiculo.capacidadBateria * 100).toInt()
        return (vehiculo.bateriaActual - consumoPorcentaje).coerceAtLeast(0)
    }
    
    
    private fun calcularParadas(vehiculo: EstadoVehiculo, destino: Destino): List<PuntoDeCarga> {
        // Simplificación: recomendar cargadores rápidos disponibles en ruta
        return puntosCarga
            .filter { it.disponible && it.potenciaKw >= 50 }
            .sortedBy { it.distanciaKm }
            .take(2)
    }
    
    
    private fun calcularTiempoCarga(potenciaKw: Int): Int {
        // Tiempo para cargar del 20% al 80% (aprox 45 kWh)
        return (45.0 / potenciaKw * 60).toInt()
    }
    
    
    fun iniciarNavegacion(destino: Destino) {
        println("🧭 NAVEGACIÓN ACTIVA")
        println("─".repeat(50))
        println("📍 Navegando hacia: ${destino.nombre}")
        println()
        
        // Simulación de instrucciones
        val instrucciones = listOf(
            "➡️ Gira a la derecha en 200m",
            "⬆️ Continúa recto 5km",
            "↗️ Toma la salida hacia A-1",
            "⬆️ Continúa recto 15km",
            "⚡ Punto de carga disponible en 2km (opcional)",
            "⬆️ Continúa recto 8km",
            "🏁 Has llegado a tu destino"
        )
        
        instrucciones.forEach { instruccion ->
            println("   $instruccion")
        }
        
        println("\n⏱️ Tiempo estimado: ${(destino.distanciaKm / 80 * 60).toInt()} minutos")
    }
    
    
    fun mostrarEstadisticas() {
        println("📊 ESTADÍSTICAS DEL SISTEMA")
        println("─".repeat(50))
        println("   Total puntos de carga: ${puntosCarga.size}")
        println("   Disponibles: ${puntosCarga.count { it.disponible }}")
        println("   Ocupados: ${puntosCarga.count { !it.disponible }}")
        println()
        println("   Por tipo:")
        TipoCargador.values().forEach { tipo ->
            val cantidad = puntosCarga.count { it.tipo == tipo }
            println("     ${tipo.emoji} ${tipo.name}: $cantidad")
        }
        println()
        println("   Búsquedas realizadas: ${historialBusquedas.size}")
    }
}
