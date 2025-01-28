package com.jdbarad.jaypalsinhbarad.ui.home.part2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jdbarad.jaypalsinhbarad.R

@Composable
fun Part2Route(
    viewModel: Part2ViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Part2Screen(
        textFiledValue = state.inputText,
        onTextChange = viewModel::onTextChange,
        onGenerateClick = viewModel::onGenerateClick,
        boxCount = state.boxCount
    )
}

@Preview
@Composable
fun Part2Screen(
    modifier: Modifier = Modifier,
    textFiledValue: String = "",
    onTextChange: (String) -> Unit = {},
    onGenerateClick: () -> Unit = {},
    boxCount: Int = 8
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = textFiledValue,
            onValueChange = onTextChange,
            label = { Text(stringResource(R.string.count)) },
            maxLines = 1,
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(12.dp, 0.dp, 12.dp, 12.dp),
            onClick = onGenerateClick
        ) {
            Text(text = stringResource(R.string.generate))
        }
        val column = 2
        LazyVerticalGrid(
            columns = GridCells.Fixed(column),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(boxCount) { index ->
                val row = index / column
                val col = index % column
                val color = if ((row + col) % 2 == 0) Color.Green else Color.Red
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .background(color, RoundedCornerShape(4.dp))
                )
            }
        }
    }
}