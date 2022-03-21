package com.example.openbankchallenge.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.openbankchallenge.model.character.Result
import com.example.openbankchallenge.ui.theme.primaryMarvel
import com.example.openbankchallenge.viewmodel.AppViewModel

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ReciveData(
    viewModel: AppViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val view by viewModel.getData("1").observeAsState()
    val loading = viewModel.loading_State

    val data = view?.body()?.data?.results
    MarvelScreen(arrListado = data, navController = navController, loading)
}

@ExperimentalCoilApi
@Composable
fun MarvelScreen(
    arrListado: ArrayList<Result>?,
    navController: NavHostController,
    loadingState: MutableState<Boolean>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Gray)
    ) {
        load(loading = loadingState.value)

        LazyColumn() {
            if (arrListado != null) {
                items(arrListado) { item ->
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate("DetailScreen/${item.id}")
                            },
                    ) {
                        Column {
                            Image(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(16f / 9f),
                                painter = rememberImagePainter(item.thumbnail.path + "." + item.thumbnail.extension),
                                contentDescription = null,
                                contentScale = ContentScale.FillWidth
                            )
                            Spacer(modifier = Modifier.fillMaxWidth(1f))

                            Column(Modifier.padding(8.dp)) {
                                Text(item.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                                Text(
                                    fff(item),
                                    maxLines = 4,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Light
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

fun fff(item: Result): String {
    return if (item.description != "") {
        item.description
    } else {
        "Description not found :("
    }
}

@Composable
fun load(loading: Boolean) {
    if (true) {
        Box(
            modifier = Modifier.fillMaxSize(1f),
            Alignment.Center
        ) {
            CircularProgressIndicator(
                color = primaryMarvel,
                modifier = Modifier.fillMaxSize(0.35f)
            )
        }
    }
}



