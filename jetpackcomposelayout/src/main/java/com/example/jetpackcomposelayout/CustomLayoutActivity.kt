package com.example.jetpackcomposelayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposelayout.ui.theme.ComposeBasicTheme

class CustomLayoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                BodyContent()
            }
        }
    }
}

@Composable
fun CustomLayout(
    modifier: Modifier = Modifier,
    // custom layout attributes
    content: @Composable () -> Unit
) {
    Layout(
        content = content, modifier = modifier
    ) {
            measureables, constriants ->

        val placeables = measureables.map {
                measurable ->
            measurable.measure(constriants)
        }
        var yPosition = 0
        layout(constriants.maxWidth, constriants.maxHeight) {
            placeables.forEach {
                    placeable ->
                placeable.placeRelative(x = 0, y = yPosition)

                yPosition += placeable.height
            }
        }
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    CustomLayout(modifier.padding(8.dp)) {
        Text(text = "hi")
        Text(text = "places items")
        Text(text = "vertically")
        Text(text = "We've done it by hand!")
        Text(text = "hi")
    }
}

@Composable
fun Modifier.firstBaselineToTop(
    firstBaseLineToTop: Dp
) = this.then(
    layout {
            measurable, constraints ->

        // 컴포저블을 측정한다.
        val placeable = measurable.measure(constraints)
        check(placeable[FirstBaseline] != AlignmentLine.Unspecified)

        // val FirstBaseline = HorizontalAlignmentLine(::min)
        val firstBaseline = placeable[FirstBaseline]

        // 원하는 상단에서 베이스라인 높이에서 첫 번째 베이스라인을 뺀 컴포저블의 높이가 된다.
        val placeableY = firstBaseLineToTop.roundToPx() - firstBaseline

        val height = placeable.height + placeableY
        layout(placeable.width, height) {
            // Where the composable gets placed
            placeable.placeRelative(0, placeableY)
        }
    }
)

@Preview
@Composable
fun TextWithPaddingToBaselinePreview() {
    ComposeBasicTheme() {
        Text("Hi there!", Modifier.firstBaselineToTop(32.dp))
    }
}

@Preview
@Composable
fun TextWithNormalPaddingPreview() {
    ComposeBasicTheme() {
        Text("Hi there!", Modifier.padding(top = 32.dp))
    }
}
@Preview
@Composable
fun BodyContentPreview() {
    ComposeBasicTheme() {
        BodyContent()
    }
}
