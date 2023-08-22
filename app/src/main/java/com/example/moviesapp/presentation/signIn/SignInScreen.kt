package com.example.moviesapp.presentation.signIn

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import codes.andreirozov.bottombaranimation.ui.theme.Blue500
import codes.andreirozov.bottombaranimation.ui.theme.Grey900
import com.example.moviesapp.R

@Composable
fun SignInScreen(
    state: SignInState,
    onSignInClick: () -> Unit,
    navController: NavController

) {
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    val coloredText = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Blue500)) {
            append("       Gala")
        }
        append("")
        withStyle(style = SpanStyle(color = Color(0xFF3DF596))) {
            append("x")
        }
        withStyle(style = SpanStyle(color = Blue500)) {
            append("y")
        }
        append(" ")
        withStyle(style = SpanStyle(color = Blue500)) {
            append("Play")
        }
    }
    val dieuKhoan = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.White)) {
            append("Khi tiếp tục, bạn đã đồng ý với")
        }
        append(" ")
        withStyle(
            style = SpanStyle(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append("Điều khoản dịch vụ")
        }
        append(" ")
        withStyle(style = SpanStyle(color = Color.White)) {
            append("của chúng tôi")
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(14.dp),
        modifier = Modifier
            .fillMaxSize()
            .drawBehind {
                val colorFrom = Color.DarkGray
                val colorTo = Grey900.copy(alpha = 0.5f)
                val gradient = Brush.verticalGradient(
                    0f to colorFrom,
                    size.height to colorTo
                )
                drawRect(brush = gradient)
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .absolutePadding(top = 10.dp, left = 5.dp, right = 8.dp)
        )
        {
            Text(
                text = coloredText,
                fontSize = 34.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f),
            )
            Text(
                text = "Để sau",
                textAlign = TextAlign.Right,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF949699),
                modifier = Modifier.absolutePadding(right = 2.dp).clickable {
                    navController.popBackStack()
                }
            )
        }
        Text(
            text = "Đăng nhập/Đăng ký",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier.absolutePadding(left = 3.dp)
        )
        Spacer(modifier = Modifier.absolutePadding(bottom = 10.dp))


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = onSignInClick,
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .width(265.dp)
                    .height(36.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = null,
                        modifier = Modifier.size(33.dp)
                    )
                    Spacer(modifier = Modifier.absolutePadding(left = 13.dp))
                    Text(
                        text = "Tiếp tục với Google",
                        color = Color.Black,
                        textAlign = TextAlign.Left,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            OutlinedButton(
                onClick = onSignInClick,
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .width(265.dp)
                    .height(37.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.facebook),
                        contentDescription = null,
                        modifier = Modifier.size(33.dp)
                    )
                    Spacer(modifier = Modifier.absolutePadding(right = 13.dp))
                    Text(
                        text = "Tiếp tục với Facebook",
                        color = Color.Black,
                        textAlign = TextAlign.Left,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.absolutePadding(bottom = 12.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    modifier = Modifier.width(120.dp),
                    thickness = 1.dp,
                    color = Color(0xFFA2A3A3)
                )
                Text(
                    text = "Hoặc",
                    color = Color(0xFFA2A3A3),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.absolutePadding(left = 5.dp, right = 5.dp)
                )
                Divider(
                    modifier = Modifier.width(120.dp),
                    thickness = 1.dp,
                    color = Color(0xFFA2A3A3)
                )
            }
            Spacer(modifier = Modifier.absolutePadding(bottom = 12.dp))

            OutlinedButton(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.Black,
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .width(265.dp)
                    .height(36.dp)

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Dùng số điên thoại của bạn",
                        color = Color.Black,
                        textAlign = TextAlign.Justify,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .width(260.dp)
                    .wrapContentHeight()
                    .absolutePadding(top = 5.dp)
            ) {
                Text(
                    text = dieuKhoan,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp)
            }
        }
    }
}