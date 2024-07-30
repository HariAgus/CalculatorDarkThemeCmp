import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import calculatorcmp.composeapp.generated.resources.Res
import calculatorcmp.composeapp.generated.resources.ic_moon
import calculatorcmp.composeapp.generated.resources.ic_sun
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.CalculatorTheme
import ui.colorBlue
import ui.colorDark

@Composable
@Preview
fun CalculatorApp() {
    var isDarkTheme by remember { mutableStateOf(false) }

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
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Toggle for setup dark theme
                ToggleDarkThemeSwitch(
                    isDarkTheme = isDarkTheme,
                    onToggle = {
                        isDarkTheme = it
                    }
                )
            }
        }
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