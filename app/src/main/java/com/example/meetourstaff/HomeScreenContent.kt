package com.example.meetourstaff

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    appViewModel: AppViewModel = viewModel()
) {
    val appUiState = appViewModel.uiState.collectAsState()

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(start = 10.dp, top = 10.dp)
    ) {

        itemsIndexed(appViewModel.getStaffList()) { index, person ->
            ProfileCard(index, person, appViewModel = appViewModel)
        }
    }

    if (appUiState.value.personSelected) {
        PersonDetailsScreen(
            id = appUiState.value.currentPersonId!!,
            staff = appUiState.value.currentPerson!!
        )
    }
}

@Composable
fun ProfileCard(
    id: Int,
    staff: Person,
    modifier: Modifier = Modifier,
    appViewModel: AppViewModel = viewModel()
) {
    ElevatedCard(
        onClick = { appViewModel.onCardClick(id) },
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        modifier = modifier
            .height(300.dp)
            .padding(end = 16.dp, bottom = 16.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = staff.profilePic),
                contentDescription = stringResource(
                    R.string.profile_card_description,
                    staff.firstName
                ),
                contentScale = ContentScale.Crop
            )

            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(colors = listOf(Color.Transparent, Color.Black)),
                        alpha = 0.8f
                    )
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column(verticalArrangement = Arrangement.Center) {
                    CustomText(
                        text = appViewModel.getAbbreviatedName(
                            firstName = stringResource(id = staff.firstName),
                            lastName = stringResource(id = staff.lastName)
                        )
                    )
                    CustomText(text = "${staff.age}")
                }
            }
        }
    }
}