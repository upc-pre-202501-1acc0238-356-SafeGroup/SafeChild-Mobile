import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun View2(nav:NavHostController){
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                onOpenDrawer = {}
            )
        },
    ){
        innerPadding->Principal(
            modifier=Modifier.padding(innerPadding),
            nav
        )
    }

}

@Composable
fun Principal(modifier: Modifier, nav: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp)
            .padding(vertical = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Text(text = "View2",
            fontSize = 20.sp,
            color = Color.Blue,
            fontWeight = FontWeight.Bold)
    }


}