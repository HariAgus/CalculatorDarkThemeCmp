import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import calculatorcmp.composeapp.generated.resources.Res
import calculatorcmp.composeapp.generated.resources.ic_add
import calculatorcmp.composeapp.generated.resources.ic_arrow_delete
import calculatorcmp.composeapp.generated.resources.ic_change_positif_negatif
import calculatorcmp.composeapp.generated.resources.ic_divide
import calculatorcmp.composeapp.generated.resources.ic_equals
import calculatorcmp.composeapp.generated.resources.ic_modulo
import calculatorcmp.composeapp.generated.resources.ic_moon
import calculatorcmp.composeapp.generated.resources.ic_multiply
import calculatorcmp.composeapp.generated.resources.ic_subtract
import calculatorcmp.composeapp.generated.resources.ic_sun
import data.Operator
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.CalculatorTheme
import ui.colorBlue
import ui.colorDark
import utils.PreferencesFactory
import viewmodel.CalculatorViewModel

@Composable
@Preview
fun CalculatorApp(viewModel: CalculatorViewModel = viewModel { CalculatorViewModel() }) {

    val preferences = PreferencesFactory.getInstance()
    var isDarkTheme by remember { mutableStateOf(preferences.isDarkThemeEnabled()) }

    val display by viewModel.display.collectAsState()
    val result by viewModel.result.collectAsState()

    CalculatorTheme(
        darkTheme = isDarkTheme
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = getPadding(), horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Toggle for setup dark theme
                ToggleDarkThemeSwitch(
                    isDarkTheme = isDarkTheme,
                    onToggle = {
                        isDarkTheme = it
                        preferences.setDarkThemeEnabled(isEnabled = it)
                    }
                )

                Text(
                    modifier = Modifier.weight(1f),
                    text = display,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 28.sp
                )

                Text(
                    modifier = Modifier.weight(1f),
                    text = result,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 40.sp,
                    lineHeight = 4.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                NumberPad(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun NumberPad(modifier: Modifier = Modifier, viewModel: CalculatorViewModel) {
    val buttons = listOf(
        listOf("C", Operator.CHANGE, Operator.MODULO, Operator.DIVIDE),
        listOf("7", "8", "9", Operator.MULTIPLY),
        listOf("4", "5", "6", Operator.SUBTRACT),
        listOf("1", "2", "3", Operator.ADD),
        listOf(".", "0", Operator.DELETE, Operator.EQUALS),
    )

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        buttons.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                row.forEach { item ->
                    when (item) {
                        is String -> {
                            // Number
                            NumberButton(
                                modifier = Modifier.weight(1f),
                                number = item,
                                onClick = {
                                    when (item) {
                                        "C" -> viewModel.onClearClick()
                                        else -> viewModel.onNumberClick(number = item)
                                    }
                                }
                            )
                        }

                        is Operator -> {
                            // Operator
                            OperatorButton(
                                modifier = Modifier.weight(1f),
                                operator = item,
                                onClick = {
                                    when (item) {
                                        Operator.CHANGE -> viewModel.onToggleSignClick()
                                        Operator.EQUALS -> viewModel.onEqualsClick()
                                        Operator.DELETE -> viewModel.onBackspaceClick()
                                        else -> viewModel.onOperatorClick(op = item)
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

@Composable
fun NumberButton(modifier: Modifier = Modifier, number: String, onClick: () -> Unit) {
    Button(
        modifier = modifier.height(72.dp),
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary),
        onClick = onClick
    ) {
        Text(
            text = number,
            fontFamily = FontFamily.Monospace,
            fontSize = 26.sp,
            maxLines = 1,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun OperatorButton(modifier: Modifier = Modifier, operator: Operator, onClick: () -> Unit) {
    val icon: Painter = when (operator) {
        Operator.ADD -> painterResource(Res.drawable.ic_add)
        Operator.SUBTRACT -> painterResource(Res.drawable.ic_subtract)
        Operator.MULTIPLY -> painterResource(Res.drawable.ic_multiply)
        Operator.DIVIDE -> painterResource(Res.drawable.ic_divide)
        Operator.CHANGE -> painterResource(Res.drawable.ic_change_positif_negatif)
        Operator.MODULO -> painterResource(Res.drawable.ic_modulo)
        Operator.EQUALS -> painterResource(Res.drawable.ic_equals)
        Operator.DELETE -> painterResource(Res.drawable.ic_arrow_delete)
    }

    Button(
        modifier = modifier.height(72.dp),
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(containerColor = colorBlue),
        onClick = onClick
    ) {
        Image(
            modifier = Modifier.size(24.dp),
            painter = icon,
            contentDescription = operator.name,
        )
    }
}


@Composable
fun ToggleDarkThemeSwitch(
    isDarkTheme: Boolean,
    onToggle: (Boolean) -> Unit
) {
    Switch(
        checked = isDarkTheme,
        onCheckedChange = { onToggle(it) },
        colors = SwitchDefaults.colors(
            checkedBorderColor = Color.Transparent,
            uncheckedBorderColor = Color.Transparent,
            checkedIconColor = colorBlue,
            uncheckedIconColor = colorBlue,
            checkedTrackColor = colorDark,
            uncheckedTrackColor = Color.White,
            checkedThumbColor = Color.Transparent,
            uncheckedThumbColor = Color.Transparent,
        ),
        thumbContent = {
            Icon(
                painter = if (isDarkTheme) painterResource(Res.drawable.ic_moon)
                else painterResource(Res.drawable.ic_sun),
                contentDescription = ""
            )
        }
    )
}