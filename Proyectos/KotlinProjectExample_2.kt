// ============================================
// 🎛️ PROYECTO: Dashboard de Control del Vehículo
// Ejemplo 2
// ============================================

fun main() {
    println("╔═══════════════════════════════════════════════╗")
    println("║     🎛️ DASHBOARD - CONTROL DEL VEHÍCULO      ║")
    println("╚═══════════════════════════════════════════════╝\n")
    
    // Crear el vehículo con todos sus sistemas
    val vehiculo = VehiculoElectrico(
        marca = "CUPRA",
        modelo = "Raval",
        año = 2026
    )
    
    // Mostrar dashboard inicial
    vehiculo.mostrarDashboard()
    
    // Interactuar con los sistemas
    println("\n${"═".repeat(50)}")
    println("SECUENCIA DE PRUEBA DE SISTEMAS")
    println("═".repeat(50))
    
    // 1. Encender el vehículo
    vehiculo.encender()
    
    // 2. Configurar clima
    println("\n--- Configurando climatización ---")
    vehiculo.clima.establecerTemperatura(22)
    vehiculo.clima.establecerVentilacion(Ventilacion.MEDIA)
    vehiculo.clima.activarAsientosCalefactados(true)
    
    // 3. Configurar conducción
    println("\n--- Configurando modo de conducción ---")
    vehiculo.configuracion.cambiarModoConduccion(ModoConduccion.SPORT)
    vehiculo.configuracion.ajustarRegeneracion(NivelRegeneracion.ALTO)
    
    // 4. Simular viaje
    println("\n--- Simulando viaje ---")
    vehiculo.iniciarViaje()
    
    vehiculo.conducir(50, velocidadMedia = 80)
    vehiculo.conducir(30, velocidadMedia = 120)
    vehiculo.conducir(20, velocidadMedia = 50)
    
    vehiculo.finalizarViaje()
    
    // Dashboard actualizado
    vehiculo.mostrarDashboard()
    
    // 5. Probar luces
    println("\n--- Sistema de iluminación ---")
    vehiculo.luces.activar(TipoLuz.POSICION)
    vehiculo.luces.activar(TipoLuz.CRUCE)
    vehiculo.luces.desactivar(TipoLuz.POSICION)
    
    // Mostrar estado de todos los sistemas
    vehiculo.mostrarResumenSistemas()
    
    // Apagar vehículo
    println("\n--- Finalizando ---")
    vehiculo.apagar()
}


// ==================== ENUMS ====================

enum class ModoConduccion(val descripcion: String, val emoji: String) {
    ECO("Máxima eficiencia", "🌱"),
    NORMAL("Equilibrado", "⚖️"),
    SPORT("Máximo rendimiento", "🏎️"),
    CONFORT("Suavidad máxima", "🛋️")
}

enum class NivelRegeneracion(val porcentaje: Int) {
    BAJO(20),
    MEDIO(50),
    ALTO(80)
}

enum class Ventilacion(val nivel: Int, val emoji: String) {
    APAGADO(0, "💨"),
    BAJA(1, "💨"),
    MEDIA(2, "💨💨"),
    ALTA(3, "💨💨💨"),
    MAXIMA(4, "🌪️")
}

enum class TipoLuz(val emoji: String) {
    POSICION("💡"),
    CRUCE("🔦"),
    CARRETERA("🔆"),
    ANTINIEBLA("🌫️"),
    EMERGENCIA("🚨")
}


// ==================== DATA CLASSES ====================

data class DatosViaje(
    var distanciaKm: Double = 0.0,
    var duracionMinutos: Int = 0,
    var consumoKwh: Double = 0.0,
    var velocidadMaxima: Int = 0
)


// ==================== SISTEMAS DEL VEHÍCULO ====================

class SistemaClima {
    var temperaturaObjetivo: Int = 22
        private set
    var ventilacion: Ventilacion = Ventilacion.APAGADO
        private set
    var asientosCalefactados: Boolean = false
        private set
    var volanteCaleFactado: Boolean = false
        private set
    
    fun establecerTemperatura(temp: Int) {
        temperaturaObjetivo = temp.coerceIn(16, 28)
        println("🌡️ Temperatura ajustada a ${temperaturaObjetivo}°C")
    }
    
    fun establecerVentilacion(nivel: Ventilacion) {
        ventilacion = nivel
        println("${nivel.emoji} Ventilación: ${nivel.name}")
    }
    
    fun activarAsientosCalefactados(activar: Boolean) {
        asientosCalefactados = activar
        val estado = if (activar) "activados" else "desactivados"
        println("🔥 Asientos calefactados $estado")
    }
    
    fun mostrarEstado(): String {
        return "Clima: ${temperaturaObjetivo}°C | Vent: ${ventilacion.name}"
    }
}


class ConfiguracionConduccion {
    var modo: ModoConduccion = ModoConduccion.NORMAL
        private set
    var regeneracion: NivelRegeneracion = NivelRegeneracion.MEDIO
        private set
    var limitadorVelocidad: Int? = null
        private set
    
    fun cambiarModoConduccion(nuevoModo: ModoConduccion) {
        modo = nuevoModo
        println("${modo.emoji} Modo cambiado a: ${modo.name} - ${modo.descripcion}")
    }
    
    fun ajustarRegeneracion(nivel: NivelRegeneracion) {
        regeneracion = nivel
        println("🔄 Regeneración: ${nivel.name} (${nivel.porcentaje}%)")
    }
    
    fun establecerLimitador(velocidad: Int?) {
        limitadorVelocidad = velocidad
        if (velocidad != null) {
            println("⚠️ Limitador de velocidad activo: $velocidad km/h")
        } else {
            println("⚠️ Limitador de velocidad desactivado")
        }
    }
}


class SistemaLuces {
    private val lucesActivas = mutableSetOf<TipoLuz>()
    
    fun activar(luz: TipoLuz) {
        lucesActivas.add(luz)
        println("${luz.emoji} ${luz.name} activadas")
    }
    
    fun desactivar(luz: TipoLuz) {
        lucesActivas.remove(luz)
        println("${luz.emoji} ${luz.name} desactivadas")
    }
    
    fun getLucesActivas(): Set<TipoLuz> = lucesActivas.toSet()
    
    fun mostrarEstado(): String {
        return if (lucesActivas.isEmpty()) {
            "Luces: Todas apagadas"
        } else {
            "Luces: ${lucesActivas.joinToString(", ") { it.name }}"
        }
    }
}


// ==================== VEHÍCULO PRINCIPAL ====================

class VehiculoElectrico(
    val marca: String,
    val modelo: String,
    val año: Int
) {
    // Sistemas del vehículo
    val clima = SistemaClima()
    val configuracion = ConfiguracionConduccion()
    val luces = SistemaLuces()
    
    // Estado del vehículo
    var encendido: Boolean = false
        private set
    var velocidadActual: Int = 0
        private set
    var bateria: Int = 78
        private set
    var kilometraje: Int = 15420
        private set
    
    // Datos del viaje actual
    private var viajeActivo = false
    private var datosViaje = DatosViaje()
    
    // Historial
    private val historialViajes = mutableListOf<DatosViaje>()
    
    
    fun encender() {
        if (!encendido) {
            encendido = true
            println("🔑 Vehículo encendido")
            println("   ✅ Sistemas inicializados correctamente")
        } else {
            println("⚠️ El vehículo ya está encendido")
        }
    }
    
    fun apagar() {
        if (encendido) {
            if (velocidadActual > 0) {
                println("❌ No se puede apagar en movimiento")
                return
            }
            encendido = false
            println("🔑 Vehículo apagado")
            println("   🅿️ Modo estacionamiento activado")
        }
    }
    
    fun iniciarViaje() {
        if (!encendido) {
            println("❌ Enciende el vehículo primero")
            return
        }
        viajeActivo = true
        datosViaje = DatosViaje()
        println("🚗 Viaje iniciado")
    }
    
    fun conducir(distanciaKm: Int, velocidadMedia: Int) {
        if (!viajeActivo) {
            println("❌ Inicia un viaje primero")
            return
        }
        
        // Calcular consumo según modo y velocidad
        val factorModo = when (configuracion.modo) {
            ModoConduccion.ECO -> 0.85
            ModoConduccion.NORMAL -> 1.0
            ModoConduccion.SPORT -> 1.3
            ModoConduccion.CONFORT -> 1.1
        }
        
        val consumoPorKm = 0.15 * factorModo * (velocidadMedia / 100.0)
        val consumoTramo = distanciaKm * consumoPorKm
        val tiempoMinutos = (distanciaKm.toDouble() / velocidadMedia * 60).toInt()
        
        // Actualizar datos del viaje
        datosViaje.distanciaKm += distanciaKm
        datosViaje.duracionMinutos += tiempoMinutos
        datosViaje.consumoKwh += consumoTramo
        if (velocidadMedia > datosViaje.velocidadMaxima) {
            datosViaje.velocidadMaxima = velocidadMedia
        }
        
        // Actualizar batería y kilometraje
        bateria = (bateria - (consumoTramo / 75 * 100).toInt()).coerceAtLeast(0)
        kilometraje += distanciaKm
        velocidadActual = velocidadMedia
        
        println("   📍 Tramo: ${distanciaKm}km a ${velocidadMedia}km/h (${tiempoMinutos}min)")
        println("      Consumo: ${"%.2f".format(consumoTramo)} kWh | Batería: $bateria%")
    }
    
    fun finalizarViaje() {
        if (!viajeActivo) return
        
        viajeActivo = false
        velocidadActual = 0
        historialViajes.add(datosViaje.copy())
        
        println("\n📊 Resumen del viaje:")
        println("   Distancia: ${"%.1f".format(datosViaje.distanciaKm)} km")
        println("   Duración: ${datosViaje.duracionMinutos} min")
        println("   Consumo total: ${"%.2f".format(datosViaje.consumoKwh)} kWh")
        println("   Consumo medio: ${"%.1f".format(datosViaje.consumoKwh / datosViaje.distanciaKm * 100)} kWh/100km")
        println("   Velocidad máxima: ${datosViaje.velocidadMaxima} km/h")
    }
    
    
    fun mostrarDashboard() {
        val estadoVehiculo = if (encendido) "🟢 ON" else "🔴 OFF"
        val barraBateria = "█".repeat(bateria / 10) + "░".repeat(10 - bateria / 10)
        
        println("""
            
╔═══════════════════════════════════════════════════════╗
║                    🎛️ DASHBOARD                        ║
╠═══════════════════════════════════════════════════════╣
║  $marca $modelo ($año)                    $estadoVehiculo        ║
╠═══════════════════════════════════════════════════════╣
║                                                       ║
║     🔋 [$barraBateria] $bateria%                       
║                                                       ║
║     🚗 Velocidad: $velocidadActual km/h                         
║     📏 Kilometraje: $kilometraje km                      
║                                                       ║
║     ${configuracion.modo.emoji} Modo: ${configuracion.modo.name.padEnd(10)}                      
║     🔄 Regeneración: ${configuracion.regeneracion.name.padEnd(10)}               
║                                                       ║
║     🌡️ ${clima.mostrarEstado().padEnd(35)}
║     ${luces.mostrarEstado().padEnd(45)}
║                                                       ║
╚═══════════════════════════════════════════════════════╝
        """.trimIndent())
    }
    
    
    fun mostrarResumenSistemas() {
        println("""
            
┌───────────────────────────────────────────────────────┐
│              📋 RESUMEN DE SISTEMAS                   │
├───────────────────────────────────────────────────────┤
│  🔋 Batería: $bateria%                                  
│  🚗 Modo conducción: ${configuracion.modo.name} ${configuracion.modo.emoji}     
│  🔄 Regeneración: ${configuracion.regeneracion.name} (${configuracion.regeneracion.porcentaje}%)
│  🌡️ Clima: ${clima.temperaturaObjetivo}°C | Vent: ${clima.ventilacion.name}
│  🔥 Asientos calef.: ${if (clima.asientosCalefactados) "Sí" else "No"}
│  💡 Luces activas: ${luces.getLucesActivas().size}
│  📊 Viajes realizados: ${historialViajes.size}
└───────────────────────────────────────────────────────┘
        """.trimIndent())
    }
}
