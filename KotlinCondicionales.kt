// ============================================
// 🚗 KOTLIN ESSENTIALS - Condicionales
// Contexto: Sistema de alertas del coche
// ============================================

fun main() {
    val nivelBateria = 90
    val velocidad = 1
    val temperaturaMotor = 85
    
    
    // --- IF / ELSE básico ---
    println("=== Sistema de Alertas ===\n")
    
    if (nivelBateria < 20) {
        println("⚠️ ALERTA: Batería baja ($nivelBateria%)")
        println("   Busca un punto de carga cercano")
    } else {
        println("✅ Batería OK: $nivelBateria%")
    }
    
    
    // --- IF / ELSE IF / ELSE ---
    println("\n--- Estado de la batería ---")
    
    if (nivelBateria >= 80) {
        println("🟢 Batería alta")
    } else if (nivelBateria >= 50) {
        println("🟡 Batería media")
    } else if (nivelBateria >= 20) {
        println("🟠 Batería baja")
    } else {
        println("🔴 ¡Batería crítica!")
    }
    
    
    // --- IF como expresión (devuelve valor) ---
    val estadoBateria = if (nivelBateria > 50) "Suficiente" else "Insuficiente"
    println("\nEstado: $estadoBateria")
    
    
    // --- WHEN (similar a switch, pero más potente) ---
    println("\n--- Modo de conducción ---")
    
    val modo = "cupra"
    
    when (modo) {
        "eco" -> println("🌱 Modo ECO: Máxima eficiencia")
        "normal" -> println("⚖️ Modo NORMAL: Equilibrado")
        "cupra" -> println("🏎️ Modo CUPRA: Máximo rendimiento")
        else -> println("❓ Modo desconocido")
    }
    
    
    // --- WHEN con rangos ---
    println("\n--- Límite de velocidad ---")
    
    when (velocidad) {
        in 0..50 -> println("🏘️ Zona urbana OK")
        in 51..90 -> println("🛣️ Carretera OK")
        in 91..120 -> println("🛤️ Autovía OK")
        else -> println("⚠️ ¡Exceso de velocidad!")
    }
    
    
    // --- WHEN con condiciones ---
    println("\n--- Diagnóstico del sistema ---")
    
    when {
        nivelBateria < 10 -> println("🔴 CRÍTICO: Detener vehículo")
        temperaturaMotor > 90 -> println("🔴 CRÍTICO: Motor sobrecalentado")
        velocidad > 130 -> println("🟠 AVISO: Reducir velocidad")
        else -> println("🟢 Todos los sistemas OK")
    }
    
    
    // --- WHEN como expresión ---
    val iconoBateria = when {
        nivelBateria >= 75 -> "████"
        nivelBateria >= 50 -> "███░"
        nivelBateria >= 25 -> "██░░"
        else -> "█░░░"
    }
    println("\n🔋 [$iconoBateria] $nivelBateria%")
}

