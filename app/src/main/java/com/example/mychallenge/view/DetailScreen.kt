//package com.example.mychallenge.view
//
//import android.util.Log
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavBackStackEntry
//import coil.annotation.ExperimentalCoilApi
//import coil.compose.rememberImagePainter
//import com.example.mychallenge.model.parsemodels.DetailParser
//import com.example.mychallenge.ui.theme.primaryMarvel
//import com.example.mychallenge.ui.theme.secondaryMarvel
//import com.example.mychallenge.viewmodel.AppViewModel
//
//@OptIn(ExperimentalCoilApi::class)
//@Composable
//fun DetailScreen(navBackStackEntry: NavBackStackEntry, viewModel: AppViewModel) {
//
//    val id = navBackStackEntry.arguments?.getInt("id")
//    requireNotNull(id)
//
//    val detailViewModel by viewModel.getDetailData(id.toString()).observeAsState()
//
//    detailViewModel?.let {
//        val details = it.body()?.data?.results
//        Log.e("DETAILS =", details.toString())
//        if (details != null) {
//            for (detail in details) {
//                val detailData = DetailParser(
//                    name = detail.name,
//                    description = detail.description,
//                    comics = detail.comics.items,
//                    image = detail.thumbnail
//                )
//                ShowDetails(detailData)
//            }
//        }
//    }
//}
//
//@OptIn(ExperimentalCoilApi::class)
//@Composable
//fun ShowDetails(detailData: DetailParser) {
//
//    Column(
//        modifier = Modifier
//            .fillMaxWidth(1f)
//            .fillMaxHeight(1f),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center,
//    ) {
//        Text(
//            text = detailData.name,
//            textAlign = TextAlign.Center,
//            fontSize = 26.sp,
//            fontWeight = FontWeight.Bold,
//            letterSpacing = 1.sp,
//            color = secondaryMarvel,
//            modifier = Modifier
//                .background(primaryMarvel)
//                .fillMaxWidth(1f)
//        )
//        Text(
//            text = detailData.description,
//            textAlign = TextAlign.Center,
//            fontSize = 16.sp,
//            fontWeight = FontWeight.Normal,
//            modifier = Modifier
//                .fillMaxWidth(0.8f)
//                .padding(5.dp),
//            color = secondaryMarvel
//        )
//        Image(
//            modifier = Modifier
//                .fillMaxWidth()
//                .aspectRatio(1f / 2f),
//            painter = rememberImagePainter(detailData.image.path + "." + detailData.image.extension),
//            contentDescription = null,
//            contentScale = ContentScale.Fit
//        )
//        Text(text = detailData.image.extension + ".jpg")
//    }
//
//}
