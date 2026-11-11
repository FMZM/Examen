package com.atom.examen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    onSaveTask: (String, String, Boolean) -> Unit,
    onBack: () -> Unit
) {
    // Estados requeridos por la rúbrica
    var nombre by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("Escuela") }
    var esPrioridadAlta by remember { mutableStateOf(false) }

    // Estado para controlar el menú desplegable de categorías
    var expanded by remember { mutableStateOf(false) }
    val categorias = listOf("Escuela", "Trabajo", "Personal")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nueva Tarea") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 1. Campo de texto para el nombre
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre de la tarea") },
                modifier = Modifier.fillMaxWidth()
            )

            // 2. Selector de categoría (DropdownMenu)
            Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedButton(
                    onClick = { expanded = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Categoría: $categoria")
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    categorias.forEach { cat ->
                        DropdownMenuItem(
                            text = { Text(cat) },
                            onClick = {
                                categoria = cat
                                expanded = false
                            }
                        )
                    }
                }
            }

            // 3. Switch de Prioridad alta
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Prioridad alta")
                Switch(
                    checked = esPrioridadAlta,
                    onCheckedChange = { esPrioridadAlta = it }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 4. Botón Guardar tarea
            Button(
                onClick = {
                    if (nombre.isNotBlank()) {
                        onSaveTask(nombre, categoria, esPrioridadAlta)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Guardar tarea")
            }
        }
    }
}