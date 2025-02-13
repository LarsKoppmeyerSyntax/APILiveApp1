package com.example.apiliveapp.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.SubcomposeAsyncImage
import com.example.apiliveapp.R
import com.example.apiliveapp.data.model.Meal

@Composable
fun MealList(
    meals: List<Meal>,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        items(meals) { meal: Meal ->

            ListItem(
                headlineContent = {
                    Text(meal.name)
                },
                leadingContent = {
                    SubcomposeAsyncImage(
                        model = meal.image,
                        contentDescription = null,
                        onLoading = {
                            Log.d("AsyncImage", "Image loading...")
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
            )
        }
    }
}

@Preview
@Composable
private fun MealListPreview() {

    Surface {
        MealList(
            meals = listOf(
                Meal("Meal 1", "https://images.lecker.de/nudeln-mit-hackfleisch-und-brokkoli,id=1e2c366a,b=lecker,w=850,ca=0,12.58,100,87.42,rm=sk.jpeg"),
                Meal("Meal 2", "https://images.lecker.de/nudeln-mit-hackfleisch-und-brokkoli,id=1e2c366a,b=lecker,w=850,ca=0,12.58,100,87.42,rm=sk.jpeg"),
                Meal("Meal 3", "https://images.lecker.de/nudeln-mit-hackfleisch-und-brokkoli,id=1e2c366a,b=lecker,w=850,ca=0,12.58,100,87.42,rm=sk.jpeg"),
            )
        )
    }
}