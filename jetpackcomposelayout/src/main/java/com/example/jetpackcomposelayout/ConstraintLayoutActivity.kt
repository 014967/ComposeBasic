package com.example.jetpackcomposelayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.jetpackcomposelayout.ui.theme.ComposeBasicTheme

class ConstraintLayoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
            }
        }
    }
}

@Composable
fun ConstraintLayoutContent() {
    ConstraintLayout {
        // 컴포저블을 통제하기 위해 참조들을 생성한다
        val (button, text) = createRefs()
        Button(
            onClick = { /* Do SomeThing */ },
            // "button" 참조를 Button 컴포저블에 배정한다.
            // 그리고 ConstraintLayout의 top에 제약조건을 설정한다
            modifier = Modifier.constrainAs(button) {
                top.linkTo(parent.top, margin = 16.dp)
            }
        ) {
            Text("Button")
        }
        // "text" 참조를 Text 컴포저블에 배정한다
        // 그리고 Button 컴포저블 bottomdp 제약조건을 설정한다.
        Text(
            "Text",
            Modifier.constrainAs(text) {
                top.linkTo(button.bottom, margin = 16.dp)
                // 텍스트를 ConstraintLayout 중앙에 정렬한다.
                centerHorizontallyTo(parent)
            }
        )
    }
}

@Preview
@Composable
fun ConstraintsLayoutContentPreview() {
    ComposeBasicTheme {
        ConstraintLayoutContent()
//
    }
}
