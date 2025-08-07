package com.example.laboratorio4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laboratorio4.ui.theme.Laboratorio4Theme

class MainActivity : ComponentActivity() {

    private val mascotas = listOf(
        Mascota("Nina", 4, "Bulldog Francés", R.drawable.perro1),
        Mascota("Luck", 2, "Salchicha", R.drawable.perro3),
        Mascota("Bob", 7, "Pug", R.drawable.perro2)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laboratorio4Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ListaMascotas(mascotas, Modifier.padding(innerPadding))
                }
            }
        }
    }
}


//Para mostrar la tarjeta de cada mascota en la lista de mascotas
@Composable
fun ListaMascotas(mascotas: List<Mascota>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(mascotas) { mascota ->
            Tarjeta(mascota)
        }
    }
}

@Composable
fun Tarjeta(mascota: Mascota) {
    //USO DE REMEMBER
    var adoptado by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        //Para cambiar de color
        //colors =CardDefaults.cardColors(containerColor=Color(0xFFE0F7FA)),
        elevation = CardDefaults.cardElevation(8.dp)

    ) {
        //La imagen y el texto  al lado del otro
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = mascota.imagenId),
                contentDescription = "Foto de ${mascota.nombre}",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            )

            //Espacio entre la foto y la descripción
            Spacer(modifier = Modifier.width(20.dp))

            //El texto en una columna con el boton, por debajo
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = mascota.nombre,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold //Para negrita
                )
                //Para mostrar un int se debe pasar a un text de esta forma
                Text(
                    text = "Tiene: ${mascota.edad} años",
                    fontSize = 14.sp,
                )

                Text(
                    text = "Raza: ${mascota.raza}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = { adoptado = true }) {
                    Text(if (adoptado) "¡Adoptado! ❤" else "Adoptar")
                }
            }
        }
    }
}

//Solo para vista previa
@Preview(showBackground = true)
@Composable
fun PreviewTarjeta(){
    Tarjeta(mascota =Mascota(nombre="Nina", edad=4, raza="Bulldog Francés", imagenId= R.drawable.perro1)
    )
}




















