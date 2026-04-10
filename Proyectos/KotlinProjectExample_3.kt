// ============================================
// 🔧 PROYECTO: Sistema de Mantenimiento
// Equipo D
// ============================================

fun main() {
    println("╔═══════════════════════════════════════════════╗")
    println("║   🔧 SISTEMA DE MANTENIMIENTO Y DIAGNÓSTICO   ║")
    println("╚═══════════════════════════════════════════════╝\n")
    
    // Crear el sistema de diagnóstico
    val diagnostico = SistemaDiagnostico()
    
    // Registrar componentes del vehículo
    diagnostico.registrarComponente(
        Componente("BAT001", "Batería Principal", TipoComponente.BATERIA, 
                  fechaInstalacion = "2023-01-15", vidaUtilKm = 300000)
    )
    diagnostico.registrarComponente(
        Componente("NEU001", "Neumáticos Delanteros", TipoComponente.NEUMATICOS,
                  fechaInstalacion = "2024-01-10", vidaUtilKm = 40000)
    )
    diagnostico.registrarComponente(
        Componente("NEU002", "Neumáticos Traseros", TipoComponente.NEUMATICOS,
                  fechaInstalacion = "2024-01-10", vidaUtilKm = 40000)
    )
    diagnostico.registrarComponente(
        Componente("FRE001", "Sistema de Frenos", TipoComponente.FRENOS,
                  fechaInstalacion = "2023-01-15", vidaUtilKm = 80000)
    )
    diagnostico.registrarComponente(
        Componente("FIL001", "Filtro de Aire Habitáculo", TipoComponente.FILTRO,
                  fechaInstalacion = "2024-06-01", vidaUtilKm = 20000)
    )
    diagnostico.registrarComponente(
        Componente("LIQ001", "Líquido de Frenos", TipoComponente.LIQUIDO,
                  fechaInstalacion = "2023-06-15", vidaUtilKm = 50000)
    )
    
    // Simular kilometraje actual
    val kilometrajeActual = 45000
    
    // Ejecutar diagnóstico completo
    println("═".repeat(50))
    println("DIAGNÓSTICO COMPLETO DEL VEHÍCULO")
    println("Kilometraje actual: $kilometrajeActual km")
    println("═".repeat(50))
    
    diagnostico.ejecutarDiagnosticoCompleto(kilometrajeActual)
    
    // Simular lecturas de sensores
    println("\n${"═".repeat(50)}")
    println("LECTURA DE SENSORES EN TIEMPO REAL")
    println("═".repeat(50))
    
    val sensores = mapOf(
        "Temperatura batería" to 32.5,
        "Presión neumático FL" to 2.3,
        "Presión neumático FR" to 2.4,
        "Presión neumático RL" to 2.2,
        "Presión neumático RR" to 2.5,
        "Voltaje batería 12V" to 12.4,
        "Temperatura motor" to 45.0,
        "Nivel líquido frenos" to 85.0
    )
    
    diagnostico.analizarSensores(sensores)
    
    // Mostrar plan de mantenimiento
    println("\n${"═".repeat(50)}")
    println("PLAN DE MANTENIMIENTO PREVENTIVO")
    println("═".repeat(50))
    
    diagnostico.generarPlanMantenimiento(kilometrajeActual)
    
    // Registrar una incidencia
    println("\n${"═".repeat(50)}")
    println("REGISTRO DE INCIDENCIAS")
    println("═".repeat(50))
    
    diagnostico.registrarIncidencia(
        Incidencia(
            codigo = "P0A80",
            descripcion = "Degradación menor del pack de batería",
            severidad = Severidad.MEDIA,
            sistemaAfectado = "Batería",
            recomendacion = "Monitorizar. Revisar en próximo mantenimiento."
        )
    )
    
    diagnostico.mostrarIncidenciasActivas()
    
    // Historial de mantenimiento
    println("\n${"═".repeat(50)}")
    println("HISTORIAL DE MANTENIMIENTO")
    println("═".repeat(50))
    
    diagnostico.registrarMantenimiento(
        RegistroMantenimiento("2024-01-10", 40000, "Cambio de neumáticos", 450.0)
    )
    diagnostico.registrarMantenimiento(
        RegistroMantenimiento("2024-06-01", 42000, "Cambio filtro habitáculo", 35.0)
    )
    diagnostico.registrarMantenimiento(
        RegistroMantenimiento("2024-03-15", 41000, "Revisión general", 150.0)
    )
    
    diagnostico.mostrarHistorialMantenimiento()
    
    // Resumen final
    diagnostico.mostrarResumenGeneral()
}


// ==================== ENUMS ====================

enum class TipoComponente(val emoji: String, val descripcion: String) {
    BATERIA("🔋", "Sistema de batería"),
    NEUMATICOS("🛞", "Neumáticos"),
    FRENOS("🛑", "Sistema de frenado"),
    FILTRO("🌬️", "Filtros"),
    LIQUIDO("💧", "Líquidos"),
    MOTOR("⚡", "Motor eléctrico"),
    SUSPENSION("🔧", "Suspensión")
}

enum class Severidad(val emoji: String, val prioridad: Int) {
    BAJA("🟢", 1),
    MEDIA("🟡", 2),
    ALTA("🟠", 3),
    CRITICA("🔴", 4)
}

enum class EstadoComponente(val emoji: String) {
    OPTIMO("✅"),
    BUENO("🟢"),
    DESGASTADO("🟡"),
    CRITICO("🟠"),
    REEMPLAZAR("🔴")
}


// ==================== DATA CLASSES ====================

data class Componente(
    val id: String,
    val nombre: String,
    val tipo: TipoComponente,
    val fechaInstalacion: String,
    val vidaUtilKm: Int,
    var kmInstalacion: Int = 0
)

data class Incidencia(
    val codigo: String,
    val descripcion: String,
    val severidad: Severidad,
    val sistemaAfectado: String,
    val recomendacion: String,
    val fecha: String = "2024-11-15"
)

data class RegistroMantenimiento(
    val fecha: String,
    val kilometraje: Int,
    val descripcion: String,
    val coste: Double
)


// ==================== SISTEMA DE DIAGNÓSTICO ====================

class SistemaDiagnostico {
    private val componentes = mutableListOf<Componente>()
    private val incidencias = mutableListOf<Incidencia>()
    private val historialMantenimiento = mutableListOf<RegistroMantenimiento>()
    
    
    fun registrarComponente(componente: Componente) {
        componentes.add(componente)
    }
    
    
    fun ejecutarDiagnosticoCompleto(kmActual: Int) {
        println("\n🔍 Ejecutando diagnóstico de componentes...\n")
        
        componentes.forEach { componente ->
            val kmUso = kmActual - componente.kmInstalacion
            val porcentajeVida = (kmUso.toDouble() / componente.vidaUtilKm * 100)
            val estado = calcularEstado(porcentajeVida)
            
            println("${componente.tipo.emoji} ${componente.nombre}")
            println("   ID: ${componente.id}")
            println("   Uso: $kmUso / ${componente.vidaUtilKm} km (${"%.1f".format(porcentajeVida)}%)")
            println("   Estado: ${estado.emoji} ${estado.name}")
            
            if (estado == EstadoComponente.CRITICO || estado == EstadoComponente.REEMPLAZAR) {
                println("   ⚠️ ACCIÓN REQUERIDA: Programar reemplazo")
            }
            println()
        }
    }
    
    
    private fun calcularEstado(porcentajeVida: Double): EstadoComponente {
        return when {
            porcentajeVida < 25 -> EstadoComponente.OPTIMO
            porcentajeVida < 50 -> EstadoComponente.BUENO
            porcentajeVida < 75 -> EstadoComponente.DESGASTADO
            porcentajeVida < 90 -> EstadoComponente.CRITICO
            else -> EstadoComponente.REEMPLAZAR
        }
    }
    
    
    fun analizarSensores(sensores: Map<String, Double>) {
        println("\n📡 Análisis de sensores:\n")
        
        sensores.forEach { (sensor, valor) ->
            val (estado, mensaje) = evaluarSensor(sensor, valor)
            println("   $sensor: $valor ${estado.emoji}")
            if (mensaje != null) {
                println("      └─ $mensaje")
            }
        }
    }
    
    
    private fun evaluarSensor(sensor: String, valor: Double): Pair<Severidad, String?> {
        return when {
            sensor.contains("Presión") && (valor < 2.2 || valor > 2.6) ->
                Severidad.MEDIA to "Ajustar presión (óptimo: 2.4 bar)"
            sensor.contains("Temperatura batería") && valor > 40 ->
                Severidad.ALTA to "Temperatura elevada"
            sensor.contains("Voltaje") && valor < 12.0 ->
                Severidad.ALTA to "Batería 12V baja"
            sensor.contains("Nivel líquido") && valor < 70 ->
                Severidad.MEDIA to "Rellenar líquido de frenos"
            else -> Severidad.BAJA to null
        }
    }
    
    
    fun generarPlanMantenimiento(kmActual: Int) {
        println("\n📅 Plan de mantenimiento recomendado:\n")
        
        data class TareaMantenimiento(
            val componente: String,
            val accion: String,
            val kmRecomendado: Int,
            val prioridad: Severidad
        )
        
        val tareas = mutableListOf<TareaMantenimiento>()
        
        componentes.forEach { componente ->
            val kmUso = kmActual - componente.kmInstalacion
            val kmRestante = componente.vidaUtilKm - kmUso
            
            when {
                kmRestante <= 0 -> tareas.add(
                    TareaMantenimiento(componente.nombre, "REEMPLAZAR YA", kmActual, Severidad.CRITICA)
                )
                kmRestante <= 5000 -> tareas.add(
                    TareaMantenimiento(componente.nombre, "Reemplazar pronto", kmActual + kmRestante, Severidad.ALTA)
                )
                kmRestante <= 10000 -> tareas.add(
                    TareaMantenimiento(componente.nombre, "Programar reemplazo", kmActual + kmRestante, Severidad.MEDIA)
                )
            }
        }
        
        // Mantenimientos estándar periódicos
        if (kmActual % 20000 > 15000) {
            tareas.add(TareaMantenimiento("General", "Revisión periódica 20.000km", 
                                         ((kmActual / 20000) + 1) * 20000, Severidad.MEDIA))
        }
        
        // Ordenar por prioridad
        val tareasOrdenadas = tareas.sortedByDescending { it.prioridad.prioridad }
        
        if (tareasOrdenadas.isEmpty()) {
            println("   ✅ No hay mantenimientos pendientes")
        } else {
            tareasOrdenadas.forEach { tarea ->
                println("   ${tarea.prioridad.emoji} ${tarea.componente}")
                println("      Acción: ${tarea.accion}")
                println("      Km recomendado: ${tarea.kmRecomendado}")
                println()
            }
        }
    }
    
    
    fun registrarIncidencia(incidencia: Incidencia) {
        incidencias.add(incidencia)
        println("⚠️ Incidencia registrada:")
        println("   Código: ${incidencia.codigo}")
        println("   ${incidencia.severidad.emoji} Severidad: ${incidencia.severidad.name}")
        println("   Descripción: ${incidencia.descripcion}")
    }
    
    
    fun mostrarIncidenciasActivas() {
        println("\n📋 Incidencias activas:\n")
        
        if (incidencias.isEmpty()) {
            println("   ✅ No hay incidencias activas")
            return
        }
        
        incidencias.sortedByDescending { it.severidad.prioridad }.forEach { inc ->
            println("┌─────────────────────────────────────────┐")
            println("│ ${inc.severidad.emoji} ${inc.codigo.padEnd(37)}│")
            println("├─────────────────────────────────────────┤")
            println("│ ${inc.descripcion.take(39).padEnd(39)} │")
            println("│ Sistema: ${inc.sistemaAfectado.padEnd(30)}│")
            println("│ Recomendación:".padEnd(42) + "│")
            println("│  ${inc.recomendacion.take(38).padEnd(38)} │")
            println("└─────────────────────────────────────────┘")
        }
    }
    
    
    fun registrarMantenimiento(registro: RegistroMantenimiento) {
        historialMantenimiento.add(registro)
    }
    
    
    fun mostrarHistorialMantenimiento() {
        println("\n📜 Historial de mantenimiento:\n")
        
        if (historialMantenimiento.isEmpty()) {
            println("   Sin registros de mantenimiento")
            return
        }
        
        val historialOrdenado = historialMantenimiento.sortedByDescending { it.fecha }
        
        println("┌────────────┬──────────┬──────────────────────────┬──────────┐")
        println("│   Fecha    │    Km    │       Descripción        │  Coste   │")
        println("├────────────┼──────────┼──────────────────────────┼──────────┤")
        
        historialOrdenado.forEach { reg ->
            val fecha = reg.fecha.padEnd(10)
            val km = reg.kilometraje.toString().padStart(8)
            val desc = reg.descripcion.take(24).padEnd(24)
            val coste = "${"%.2f".format(reg.coste)}€".padStart(8)
            println("│ $fecha │ $km │ $desc │ $coste │")
        }
        
        println("└────────────┴──────────┴──────────────────────────┴──────────┘")
        
        val costoTotal = historialMantenimiento.sumOf { it.coste }
        println("\n   💰 Coste total de mantenimiento: ${"%.2f".format(costoTotal)}€")
    }
    
    
    fun mostrarResumenGeneral() {
        println("""
            
╔═══════════════════════════════════════════════════════╗
║              📊 RESUMEN DE DIAGNÓSTICO                ║
╠═══════════════════════════════════════════════════════╣
║                                                       ║
║  🔧 Componentes registrados: ${componentes.size}                       
║  ⚠️ Incidencias activas: ${incidencias.size}                          
║  📜 Mantenimientos realizados: ${historialMantenimiento.size}                   
║                                                       ║
║  Estado general del vehículo:                         ║
║  ${if (incidencias.any { it.severidad == Severidad.CRITICA }) 
        "🔴 ATENCIÓN REQUERIDA" 
      else if (incidencias.any { it.severidad == Severidad.ALTA }) 
        "🟠 REVISAR PRONTO" 
      else 
        "🟢 BUEN ESTADO"}                                  
║                                                       ║
╚═══════════════════════════════════════════════════════╝
        """.trimIndent())
    }
}
