package id.nns.my_canvas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var shapeIndex by remember {
                mutableStateOf(0)
            }
            var x by remember {
                mutableStateOf("0")
            }
            var y by remember {
                mutableStateOf("0")
            }

            Scaffold(
                topBar = {
                    MyTopAppBar(
                        oldX = x,
                        oldY = y,
                        newX = {
                            x = it
                        },
                        newY = {
                            y = it
                        }
                    )
                },
                bottomBar = {
                    MyBottomAppBar(shapeIndex) {
                        shapeIndex = it
                    }
                }
            ) {
                MyCanvas(shapeIndex = shapeIndex, x = x, y = y)
            }
        }
    }
}

@Composable
fun MyTopAppBar(oldX: String, oldY: String, newX: (String) -> Unit, newY: (String) -> Unit) {
    TopAppBar(
        modifier = Modifier
            .height(100.dp),
        backgroundColor = colorResource(id = R.color.purple_700)
    ) {
        TextField(
            modifier = Modifier
                .width(100.dp)
                .padding(top = 12.dp, bottom = 12.dp, start = 20.dp),
            value = oldX,
            onValueChange = {
                newX(it)
            },
            label = {
                Text(
                    text = "X"
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = colorResource(id = R.color.white),
                textColor = colorResource(id = R.color.purple_500)
            )
        )
        TextField(
            modifier = Modifier
                .width(100.dp)
                .padding(top = 12.dp, bottom = 12.dp, start = 20.dp),
            value = oldY,
            onValueChange = {
                newY(it)
            },
            label = {
                Text(
                    text = "Y"
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = colorResource(id = R.color.white),
                textColor = colorResource(id = R.color.purple_500)
            )
        )
    }
}

@Composable
fun MyBottomAppBar(oldIndex: Int, newIndex: (Int) -> Unit) {
    BottomAppBar(
        backgroundColor = colorResource(id = R.color.purple_700)
    ) {
        BottomNavigationItem(
            selected = oldIndex == 0,
            onClick = {
                      newIndex(0)
            },
            icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_square),
                    contentDescription = "Square"
                )
            }
        )
        BottomNavigationItem(
            selected = oldIndex == 1,
            onClick = {
                      newIndex(1)
            },
            icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_circle),
                    contentDescription = "Circle"
                )
            }
        )
    }
}

@Composable
fun MyCanvas(shapeIndex: Int, x: String, y: String) {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(id = R.color.white)
            )
    ) {
        if (shapeIndex == 0) {
            drawRect(
                color = Color.Magenta,
                topLeft = Offset(
                    x = if(x == "") "0".toFloat() else x.toFloat(),
                    y = if(y == "") "0".toFloat() else y.toFloat()
                ),
                size = Size(
                    width = 200F,
                    height = 200F
                )
            )
        } else {
            drawCircle(
                color = Color.Magenta,
                center = Offset(
                    x = if(x == "") "0".toFloat() else x.toFloat(),
                    y = if(y == "") "0".toFloat() else y.toFloat()
                ),
                radius = 100F
            )
        }
    }
}