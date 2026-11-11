package com.atom.examen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Usamos Surface y MaterialTheme para asegurar compatibilidad con cualquier nombre de tema
            MaterialTheme {
                Surface {
                    MainAppContent()
                }
            }
        }
    }
}

@Composable
fun MainAppContent() {
    // Estado para controlar en qué pantalla estamos: "list" o "add"
    var currentScreen by remember { mutableStateOf("list") }

    // Lista de tareas inicial en memoria
    var taskList by remember {
        mutableStateOf(
            listOf(
                Task(1, "Estudiar Jetpack Compose", "Escuela", true, false),
                Task(2, "Comprar despensa", "Personal", false, false)
            )
        )
    }

    // Lógica de navegación y acciones
    if (currentScreen == "list") {
        TaskScreen(
            taskList = taskList,
            onTaskCheckedChange = { taskToUpdate, isChecked ->
                // Actualizar el estado de la tarea (terminada o pendiente)
                taskList = taskList.map { task ->
                    if (task.id == taskToUpdate.id) {
                        task.copy(isTerminada = isChecked)
                    } else {
                        task
                    }
                }
            },
            onNavigateToAdd = {
                currentScreen = "add" // Ir a la pantalla de registro
            }
        )
    } else if (currentScreen == "add") {
        AddTaskScreen(
            onSaveTask = { nombre, categoria, esPrioridadAlta ->
                // Crear la nueva tarea y agregarla a la lista
                val nuevaTarea = Task(
                    id = taskList.size + 1,
                    nombre = nombre,
                    categoria = categoria,
                    esPrioridadAlta = esPrioridadAlta,
                    isTerminada = false
                )
                taskList = taskList + nuevaTarea
                currentScreen = "list" // Regresar a la pantalla principal
            },
            onBack = {
                currentScreen = "list"
            }
        )
    }
}