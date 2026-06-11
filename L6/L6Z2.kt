package com.example.l6z2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.l6z2.ui.theme.L6Z2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            L6Z2Theme {
                AssignmentApp()
            }
        }
    }
}

data class Task(
    val id: Int,
    val description: String,
    val maxPoints: Int
)

data class AssignmentList(
    val id: String,
    val subject: String,
    val listNumber: Int,
    val grade: Double,
    val tasks: List<Task>
)

val sampleAssignmentLists = listOf(
    AssignmentList("PUM1_L1", "Programowanie Urządzeń Mobilnych 1", 1, 4.5, listOf(
        Task(1, "Implementacja FizzBuzz", 3), Task(2, "Sprawdzenie palindromu", 3), Task(3, "Trójkąt Pascala", 4)
    )),
    AssignmentList("PUM1_L2", "Programowanie Urządzeń Mobilnych 1", 2, 5.0, listOf(
        Task(1, "Funkcje rozszerzające", 4), Task(2, "Funkcje wyższego rzędu", 6)
    )),
    AssignmentList("SO_L1", "Systemy Operacyjne", 1, 3.5, listOf(
        Task(1, "Implementacja semafora", 5), Task(2, "Problem producenta-konsumenta", 5)
    )),
    AssignmentList("SO_L2", "Systemy Operacyjne", 2, 4.0, listOf(
        Task(1, "Algorytmy szeregowania CPU", 6), Task(2, "Zarządzanie pamięcią", 4)
    ))
)

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun AssignmentApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val topBarTitle = when {
        currentRoute == "assignment_lists" -> "Moje listy zadań"
        currentRoute == "grades_summary" -> "Moje oceny"
        currentRoute?.startsWith("list_detail") == true -> "Szczegóły listy"
        else -> "Aplikacja"
    }
    
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = topBarTitle) }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentRoute == "assignment_lists",
                    onClick = { navController.navigate("assignment_lists") { launchSingleTop = true } },
                    icon = { Icon(Icons.Default.List, contentDescription = "Listy") },
                    label = { Text("Listy") }
                )
                NavigationBarItem(
                    selected = currentRoute == "grades_summary",
                    onClick = { navController.navigate("grades_summary") { launchSingleTop = true } },
                    icon = { Icon(Icons.Default.Star, contentDescription = "Oceny") },
                    label = { Text("Oceny") }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "assignment_lists",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("assignment_lists") {
                AssignmentListsScreen(navController)
            }
            composable("grades_summary") {
                GradesSummaryScreen()
            }
            composable(
                route = "list_detail/{listId}",
                arguments = listOf(navArgument("listId") { type = NavType.StringType })
            ) { backStackEntry ->
                val listId = backStackEntry.arguments?.getString("listId")
                ListDetailScreen(listId, navController)
            }
        }
    }
}

@Composable
fun AssignmentListsScreen(navController: NavController) {
    LazyColumn( 
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)
    ) {
        items(sampleAssignmentLists) { assignmentList -> 
            Card( 
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable {
                        navController.navigate("list_detail/${assignmentList.id}")
                    }
            ) { 
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "${assignmentList.subject} - Lista ${assignmentList.listNumber}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Ocena: ${assignmentList.grade}")
                    Text(text = "Liczba zadań: ${assignmentList.tasks.size}")
                }
            }
        }
    }
}

@Composable
fun GradesSummaryScreen() {
    val gradesSummary = sampleAssignmentLists
        .groupBy { it.subject } 
        .mapValues { entry -> entry.value.map { it.grade }.average() } 

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)
    ) { 
        items(gradesSummary.toList()) { (subject, averageGrade) ->
            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = subject, style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Średnia z list: ${"%.2f".format(averageGrade)}")
                }
            }
        }
    }
}

@Composable
fun ListDetailScreen(listId: String?, navController: NavController) {
    val selectedList = sampleAssignmentLists.find { it.id == listId }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        if (selectedList != null) {
            Text(
                text = "${selectedList.subject} - Lista ${selectedList.listNumber}",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            LazyColumn { 
                items(selectedList.tasks) { task ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Zadanie ${task.id}: ${task.description}",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Maksymalna liczba punktów: ${task.maxPoints}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        } else {
            Text("Nie znaleziono szczegółów tej listy zadań.")
        }
    }
}
