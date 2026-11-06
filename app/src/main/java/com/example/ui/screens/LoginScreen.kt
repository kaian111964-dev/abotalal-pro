package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.HeadsetMic
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.AppDataSource
import com.example.model.Screen
import com.example.ui.theme.AccentGreen
import com.example.ui.theme.BrandBlue
import com.example.ui.theme.BrandRed

@Composable
fun LoginScreen(
    isDarkMode: Boolean,
    onToggleDarkMode: () -> Unit,
    onLogin: (String) -> Unit,
    onNavigateRegister: () -> Unit,
    onSkipLogin: () -> Unit
) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = if (isDarkMode) {
                            listOf(Color(0xFF040E24), Color(0xFF0B1B3D), Color(0xFF132A5B))
                        } else {
                            listOf(
                                Color(0xFF003D99),
                                Color(0xFF0A58CA),
                                Color(0xFFD6E4F8),
                                Color(0xFFFFFFFF),
                                Color(0xFF0052CC)
                            )
                        }
                    )
                )
        ) {
            // Main Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .navigationBarsPadding()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Top Action Bar (Night Mode toggle & Language)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Dark Mode Toggle Pill
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = Color(0xFF0B3B8A).copy(alpha = 0.85f),
                        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.3f)),
                        modifier = Modifier.clickable { onToggleDarkMode() }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Icon(
                                imageVector = if (isDarkMode) Icons.Default.LightMode else Icons.Default.DarkMode,
                                contentDescription = "الوضع الليلي",
                                tint = Color.White,
                                modifier = Modifier.size(15.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (isDarkMode) "الوضع النهاري" else "الوضع الليلي",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }

                    // Language Selector Pill
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = Color(0xFF0B3B8A).copy(alpha = 0.85f),
                        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.3f))
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Language,
                                contentDescription = "اللغة",
                                tint = Color.White,
                                modifier = Modifier.size(15.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "العربية ▾",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                }

                // 3D Chrome Logo
                Image(
                    painter = painterResource(id = R.drawable.logo_main),
                    contentDescription = "ابو طلال",
                    modifier = Modifier
                        .fillMaxWidth(0.80f)
                        .height(95.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Central Card
                Surface(
                    shape = RoundedCornerShape(24.dp),
                    color = if (isDarkMode) Color(0xFF0F1E3D) else Color.White,
                    shadowElevation = 8.dp,
                    border = BorderStroke(
                        1.dp,
                        if (isDarkMode) Color(0xFF233B70) else Color(0xFFE2EDFB)
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.92f)
                        .padding(horizontal = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Title: "— مرحبا بكم —"
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(28.dp)
                                    .height(2.5.dp)
                                    .background(BrandRed, RoundedCornerShape(2.dp))
                            )
                            Text(
                                text = " مرحبا بكم ",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = if (isDarkMode) Color.White else Color(0xFF0F2B5C)
                                )
                            )
                            Box(
                                modifier = Modifier
                                    .width(28.dp)
                                    .height(2.5.dp)
                                    .background(BrandRed, RoundedCornerShape(2.dp))
                            )
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "قم بتسجيل الدخول للمتابعة",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = if (isDarkMode) Color(0xFFA5B8D8) else Color(0xFF6B7E99),
                                fontSize = 12.sp
                            )
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        // Username Field
                        OutlinedTextField(
                            value = username,
                            onValueChange = { username = it },
                            placeholder = {
                                Text(
                                    "اسم المستخدم",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        color = Color(0xFF8E9BAE)
                                    )
                                )
                            },
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = null,
                                    tint = BrandBlue,
                                    modifier = Modifier.size(20.dp)
                                )
                            },
                            shape = RoundedCornerShape(12.dp),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = BrandBlue,
                                unfocusedBorderColor = if (isDarkMode) Color(0xFF2C4378) else Color(0xFFD6E3F4),
                                focusedContainerColor = if (isDarkMode) Color(0xFF162548) else Color(0xFFF9FBFF),
                                unfocusedContainerColor = if (isDarkMode) Color(0xFF162548) else Color(0xFFF9FBFF)
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // Password Field
                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            placeholder = {
                                Text(
                                    "كلمة المرور",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        color = Color(0xFF8E9BAE)
                                    )
                                )
                            },
                            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = null,
                                    tint = BrandBlue,
                                    modifier = Modifier.size(19.dp)
                                )
                            },
                            leadingIcon = {
                                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                                    Icon(
                                        imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                        contentDescription = "إظهار كلمة المرور",
                                        tint = Color(0xFF7E8EAA),
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            },
                            shape = RoundedCornerShape(12.dp),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = BrandBlue,
                                unfocusedBorderColor = if (isDarkMode) Color(0xFF2C4378) else Color(0xFFD6E3F4),
                                focusedContainerColor = if (isDarkMode) Color(0xFF162548) else Color(0xFFF9FBFF),
                                unfocusedContainerColor = if (isDarkMode) Color(0xFF162548) else Color(0xFFF9FBFF)
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        // Row: Login (Red) & Register (Blue Outline)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            // Red Gradient Login Button
                            Button(
                                onClick = { onLogin(username) },
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = BrandRed
                                ),
                                modifier = Modifier
                                    .weight(1.1f)
                                    .height(44.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.Login,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "تسجيل الدخول",
                                        style = MaterialTheme.typography.labelLarge.copy(
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                            }

                            // Outlined Register Button
                            OutlinedButton(
                                onClick = { onNavigateRegister() },
                                shape = RoundedCornerShape(12.dp),
                                border = BorderStroke(1.5.dp, BrandBlue),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = if (isDarkMode) Color(0xFF1B2C54) else Color(0xFFEBF3FE)
                                ),
                                modifier = Modifier
                                    .weight(0.9f)
                                    .height(44.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.PersonAdd,
                                        contentDescription = null,
                                        tint = BrandBlue,
                                        modifier = Modifier.size(17.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "التسجيل",
                                        style = MaterialTheme.typography.labelLarge.copy(
                                            color = BrandBlue,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Skip & Enter Button
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = if (isDarkMode) Color(0xFF182A52) else Color(0xFFDDEBFC),
                            border = BorderStroke(1.dp, BrandBlue.copy(alpha = 0.5f)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(42.dp)
                                .clickable { onSkipLogin() }
                        ) {
                            Row(
                                modifier = Modifier.fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "تخطي والدخول بدون تسجيل",
                                    style = MaterialTheme.typography.labelLarge.copy(
                                        color = if (isDarkMode) Color(0xFF6BA6FF) else Color(0xFF0B4CB8),
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = null,
                                    tint = if (isDarkMode) Color(0xFF6BA6FF) else Color(0xFF0B4CB8),
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Divider: "أو تواصل معنا عبر"
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            HorizontalDivider(
                                modifier = Modifier.weight(1f),
                                color = if (isDarkMode) Color(0xFF263B66) else Color(0xFFD6E2F2)
                            )
                            Text(
                                text = "  أو تواصل معنا عبر  ",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = if (isDarkMode) Color(0xFFA5B8D8) else Color(0xFF0D3269),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp
                                )
                            )
                            HorizontalDivider(
                                modifier = Modifier.weight(1f),
                                color = if (isDarkMode) Color(0xFF263B66) else Color(0xFFD6E2F2)
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // 3 Circular Contact Buttons
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            // WhatsApp
                            ContactCircleButton(
                                label = "واتساب",
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.Call,
                                        contentDescription = "واتساب",
                                        tint = AccentGreen,
                                        modifier = Modifier.size(22.dp)
                                    )
                                },
                                circleBorderColor = AccentGreen.copy(alpha = 0.5f),
                                isDarkMode = isDarkMode,
                                onClick = {
                                    val intent = Intent(Intent.ACTION_VIEW).apply {
                                        data = Uri.parse(AppDataSource.WHATSAPP_URL)
                                    }
                                    context.startActivity(intent)
                                }
                            )

                            // Phone Call
                            ContactCircleButton(
                                label = "اتصال",
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.Call,
                                        contentDescription = "اتصال",
                                        tint = BrandBlue,
                                        modifier = Modifier.size(22.dp)
                                    )
                                },
                                circleBorderColor = BrandBlue.copy(alpha = 0.5f),
                                isDarkMode = isDarkMode,
                                onClick = {
                                    val intent = Intent(Intent.ACTION_DIAL).apply {
                                        data = Uri.parse("tel:${AppDataSource.PHONE_NUMBER}")
                                    }
                                    context.startActivity(intent)
                                }
                            )

                            // Customer Service
                            ContactCircleButton(
                                label = "خدمة العملاء",
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.HeadsetMic,
                                        contentDescription = "خدمة العملاء",
                                        tint = BrandBlue,
                                        modifier = Modifier.size(22.dp)
                                    )
                                },
                                circleBorderColor = BrandBlue.copy(alpha = 0.5f),
                                isDarkMode = isDarkMode,
                                onClick = {
                                    val intent = Intent(Intent.ACTION_DIAL).apply {
                                        data = Uri.parse("tel:${AppDataSource.PHONE_NUMBER}")
                                    }
                                    context.startActivity(intent)
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Bottom Wave & 3 Carousel dots
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Pagination dots (first red, two white)
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(7.dp)
                                .clip(CircleShape)
                                .background(BrandRed)
                        )
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.8f))
                        )
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.8f))
                        )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.city_wave_bottom),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(65.dp),
                        contentScale = ContentScale.FillWidth
                    )
                }
            }
        }
    }
}

@Composable
fun ContactCircleButton(
    label: String,
    icon: @Composable () -> Unit,
    circleBorderColor: Color,
    isDarkMode: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
            .padding(4.dp)
    ) {
        Surface(
            shape = CircleShape,
            color = if (isDarkMode) Color(0xFF162548) else Color.White,
            shadowElevation = 3.dp,
            border = BorderStroke(1.5.dp, circleBorderColor),
            modifier = Modifier.size(46.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                icon()
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = if (isDarkMode) Color(0xFFA5B8D8) else Color(0xFF0D3269)
            )
        )
    }
}
