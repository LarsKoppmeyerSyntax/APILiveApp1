package com.example.apiliveapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.SubcomposeAsyncImage
import com.example.apiliveapp.ui.theme.APILiveAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val viewModel: MealViewModel = viewModel()
            val randomMeal by viewModel.randomMeal.collectAsState()

            val context = LocalContext.current


            LaunchedEffect(randomMeal) {
                Toast.makeText(context, "Neues Meal geladen", Toast.LENGTH_SHORT).show()

            }


            APILiveAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        randomMeal?.let { meal ->
                            Text(meal.name)

                            SubcomposeAsyncImage(
                                model = meal.image ,
                                contentDescription = null,
                                onLoading = {
                                    Log.d("AsyncImage", "Image loading...")
                                },
                                onError = { state ->
                                    Toast.makeText(
                                        context,
                                        "Error loading Image",
                                        Toast.LENGTH_LONG
                                    ).show()
                                },
                                onSuccess = { state ->
                                    Log.d("AsyncImage", "Image loaded successfully")
                                },
                                loading = {

                                    Text("Bild lädt gerade")

//                                    CircularProgressIndicator(
//                                        Modifier.scale(.5f)
//                                    )
                                },
                                error = {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text("Fehler beim Laden des Bilds")
                                        Icon(
                                            painter = painterResource(R.drawable.baseline_error_24),
                                            null
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
