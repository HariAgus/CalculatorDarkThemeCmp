import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun getPadding(): Dp