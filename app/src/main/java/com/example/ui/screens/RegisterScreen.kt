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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.HeadsetMic
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.AppDataSource
import com.example.ui.theme.AccentGreen
import com.example.ui.theme.BrandBlue
import com.example.ui.theme.BrandRed

@Composable
fun RegisterScreen(
    isDarkMode: Boolean,
    onToggleDarkMode: () -> Unit,
    onRegister: (fullName: String, phone: String, email: String) -> Unit,
    onNavigateLogin: () -> Unit
) {
    val context = LocalContext.current
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var selectedCountry by remember { mutableStateOf("اليمن") }
    var showCountryDropdown by remember { mutableStateOf(false) }
    var countryCode by remember { mutableStateOf("+967") }
    var phoneNumber by remember { mutableStateOf("") }
    var englishUsername by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("ذكر") } // "ذكر" or "أنثى"

    val countries = listOf("اليمن", "المملكة العربية السعودية", "الإمارات", "الكويت", "قطر", "سلطنة عمان", "مصر", "الأردن")

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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .navigationBarsPadding()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Top Action Bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = Color(0xFF0B3B8A).copy(alpha = 0.85f),
                        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.3f)),
                        modifier = Modifier.clickable { onToggleDarkMode() }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp)
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

                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = Color(0xFF0B3B8A).copy(alpha = 0.85f),
                        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.3f))
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp)
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

                // Logo
                Image(
                    painter = painterResource(id = R.drawable.logo_main),
                    contentDescription = "ابو طلال",
                    modifier = Modifier
                        .fillMaxWidth(0.70f)
                        .height(80.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Registration Card
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
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Blue Header Badge: "التسجيل +"
                        Surface(
                            shape = RoundedCornerShape(14.dp),
                            color = BrandBlue,
                            modifier = Modifier.padding(bottom = 6.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 24.dp, vertical = 6.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.PersonAdd,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "التسجيل",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                        }

                        Text(
                            text = "أنشئ حسابك للاستفادة من جميع الخدمات",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = if (isDarkMode) Color(0xFFA5B8D8) else Color(0xFF0D3269),
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp
                            )
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // Full Name
                        OutlinedTextField(
                            value = fullName,
                            onValueChange = { fullName = it },
                            placeholder = { Text("الاسم الكامل", fontSize = 12.sp) },
                            trailingIcon = {
                                Icon(Icons.Default.Person, contentDescription = null, tint = BrandBlue, modifier = Modifier.size(19.dp))
                            },
                            shape = RoundedCornerShape(10.dp),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = BrandBlue,
                                unfocusedBorderColor = if (isDarkMode) Color(0xFF2C4378) else Color(0xFFD6E3F4),
                                focusedContainerColor = if (isDarkMode) Color(0xFF162548) else Color(0xFFF9FBFF),
                                unfocusedContainerColor = if (isDarkMode) Color(0xFF162548) else Color(0xFFF9FBFF)
                            ),
                            modifier = Modifier.fillMaxWidth().height(50.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Email
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            placeholder = { Text("البريد الإلكتروني", fontSize = 12.sp) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            trailingIcon = {
                                Icon(Icons.Default.Email, contentDescription = null, tint = BrandBlue, modifier = Modifier.size(18.dp))
                            },
                            shape = RoundedCornerShape(10.dp),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = BrandBlue,
                                unfocusedBorderColor = if (isDarkMode) Color(0xFF2C4378) else Color(0xFFD6E3F4),
                                focusedContainerColor = if (isDarkMode) Color(0xFF162548) else Color(0xFFF9FBFF),
                                unfocusedContainerColor = if (isDarkMode) Color(0xFF162548) else Color(0xFFF9FBFF)
                            ),
                            modifier = Modifier.fillMaxWidth().height(50.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Country Selector Dropdown
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                border = BorderStroke(1.dp, if (isDarkMode) Color(0xFF2C4378) else Color(0xFFD6E3F4)),
                                color = if (isDarkMode) Color(0xFF162548) else Color(0xFFF9FBFF),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .clickable { showCountryDropdown = true }
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(horizontal = 12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(Icons.Default.Public, contentDescription = null, tint = BrandBlue, modifier = Modifier.size(19.dp))
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = selectedCountry,
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                color = if (isDarkMode) Color.White else Color(0xFF1C2D4B)
                                            )
                                        )
                                    }
                                    Text("▾", color = Color(0xFF7E8EAA))
                                }
                            }

                            DropdownMenu(
                                expanded = showCountryDropdown,
                                onDismissRequest = { showCountryDropdown = false }
                            ) {
                                countries.forEach { country ->
                                    DropdownMenuItem(
                                        text = { Text(country) },
                                        onClick = {
                                            selectedCountry = country
                                            countryCode = if (country == "اليمن") "+967" else if (country.contains("السعودية")) "+966" else "+971"
                                            showCountryDropdown = false
                                        }
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Phone Number with Country Code
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            // Phone input field
                            OutlinedTextField(
                                value = phoneNumber,
                                onValueChange = { phoneNumber = it },
                                placeholder = { Text("رقم الهاتف", fontSize = 12.sp) },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                                trailingIcon = {
                                    Icon(Icons.Default.Call, contentDescription = null, tint = BrandBlue, modifier = Modifier.size(18.dp))
                                },
                                shape = RoundedCornerShape(10.dp),
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = BrandBlue,
                                    unfocusedBorderColor = if (isDarkMode) Color(0xFF2C4378) else Color(0xFFD6E3F4),
                                    focusedContainerColor = if (isDarkMode) Color(0xFF162548) else Color(0xFFF9FBFF),
                                    unfocusedContainerColor = if (isDarkMode) Color(0xFF162548) else Color(0xFFF9FBFF)
                                ),
                                modifier = Modifier.weight(1f).height(50.dp)
                            )

                            // Country code pill
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = if (isDarkMode) Color(0xFF1A2B50) else Color(0xFFE2EDFC),
                                border = BorderStroke(1.dp, BrandBlue.copy(alpha = 0.4f)),
                                modifier = Modifier
                                    .width(85.dp)
                                    .height(50.dp)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Text(
                                        text = "$countryCode ▾",
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = BrandBlue,
                                            fontSize = 13.sp
                                        )
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // English Username field + hint
                        Column(modifier = Modifier.fillMaxWidth()) {
                            OutlinedTextField(
                                value = englishUsername,
                                onValueChange = { englishUsername = it },
                                placeholder = { Text("اسم المستخدم (بالإنجليزية)", fontSize = 12.sp) },
                                trailingIcon = {
                                    Icon(Icons.Default.AlternateEmail, contentDescription = null, tint = BrandBlue, modifier = Modifier.size(18.dp))
                                },
                                shape = RoundedCornerShape(10.dp),
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = BrandBlue,
                                    unfocusedBorderColor = if (isDarkMode) Color(0xFF2C4378) else Color(0xFFD6E3F4),
                                    focusedContainerColor = if (isDarkMode) Color(0xFF162548) else Color(0xFFF9FBFF),
                                    unfocusedContainerColor = if (isDarkMode) Color(0xFF162548) else Color(0xFFF9FBFF)
                                ),
                                modifier = Modifier.fillMaxWidth().height(50.dp)
                            )
                            Text(
                                text = "مثال: ahmed123",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = Color(0xFF7E8EAA),
                                    fontSize = 10.sp
                                ),
                                modifier = Modifier.padding(start = 6.dp, top = 2.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Gender Radio Selector: "ذكر" vs "أنثى"
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            // Male
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = if (selectedGender == "ذكر") BrandBlue else if (isDarkMode) Color(0xFF162548) else Color.White,
                                border = BorderStroke(1.dp, if (selectedGender == "ذكر") BrandBlue else Color(0xFFD6E3F4)),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(40.dp)
                                    .clickable { selectedGender = "ذكر" }
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Person,
                                        contentDescription = null,
                                        tint = if (selectedGender == "ذكر") Color.White else BrandBlue,
                                        modifier = Modifier.size(17.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "ذكر",
                                        style = MaterialTheme.typography.labelLarge.copy(
                                            color = if (selectedGender == "ذكر") Color.White else BrandBlue,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                            }

                            // Female
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = if (selectedGender == "أنثى") BrandBlue else if (isDarkMode) Color(0xFF162548) else Color.White,
                                border = BorderStroke(1.dp, if (selectedGender == "أنثى") BrandBlue else Color(0xFFD6E3F4)),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(40.dp)
                                    .clickable { selectedGender = "أنثى" }
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Person,
                                        contentDescription = null,
                                        tint = if (selectedGender == "أنثى") Color.White else BrandBlue,
                                        modifier = Modifier.size(17.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "أنثى",
                                        style = MaterialTheme.typography.labelLarge.copy(
                                            color = if (selectedGender == "أنثى") Color.White else BrandBlue,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Action Buttons: Blue "التسجيل" & Red "العودة لتسجيل الدخول"
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            // Register Button
                            Button(
                                onClick = {
                                    onRegister(
                                        fullName.ifBlank { "مستخدم جديد" },
                                        phoneNumber.ifBlank { "778215553" },
                                        email.ifBlank { "client@abotalalpro.com" }
                                    )
                                },
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = BrandBlue),
                                modifier = Modifier.weight(1f).height(44.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Icon(Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(17.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "التسجيل",
                                        style = MaterialTheme.typography.labelLarge.copy(
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                            }

                            // Return to Login Button (Red outline)
                            OutlinedButton(
                                onClick = { onNavigateLogin() },
                                shape = RoundedCornerShape(10.dp),
                                border = BorderStroke(1.5.dp, BrandRed),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = if (isDarkMode) Color(0xFF2C1620) else Color(0xFFFDECEF)
                                ),
                                modifier = Modifier.weight(1f).height(44.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = null,
                                        tint = BrandRed,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "العودة لتسجيل الدخول",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = BrandRed,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 11.sp
                                        )
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Divider & Contact Circles
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

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            ContactCircleButton(
                                label = "واتساب",
                                icon = { Icon(Icons.Default.Call, contentDescription = "واتساب", tint = AccentGreen, modifier = Modifier.size(20.dp)) },
                                circleBorderColor = AccentGreen.copy(alpha = 0.5f),
                                isDarkMode = isDarkMode,
                                onClick = {
                                    val intent = Intent(Intent.ACTION_VIEW).apply {
                                        data = Uri.parse(AppDataSource.WHATSAPP_URL)
                                    }
                                    context.startActivity(intent)
                                }
                            )

                            ContactCircleButton(
                                label = "اتصال",
                                icon = { Icon(Icons.Default.Call, contentDescription = "اتصال", tint = BrandBlue, modifier = Modifier.size(20.dp)) },
                                circleBorderColor = BrandBlue.copy(alpha = 0.5f),
                                isDarkMode = isDarkMode,
                                onClick = {
                                    val intent = Intent(Intent.ACTION_DIAL).apply {
                                        data = Uri.parse("tel:${AppDataSource.PHONE_NUMBER}")
                                    }
                                    context.startActivity(intent)
                                }
                            )

                            ContactCircleButton(
                                label = "خدمة العملاء",
                                icon = { Icon(Icons.Default.HeadsetMic, contentDescription = "خدمة العملاء", tint = BrandBlue, modifier = Modifier.size(20.dp)) },
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

                // Bottom Wave & Dots
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 6.dp)
                    ) {
                        Box(modifier = Modifier.size(7.dp).clip(CircleShape).background(BrandRed))
                        Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.8f)))
                        Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.8f)))
                    }

                    Image(
                        painter = painterResource(id = R.drawable.city_wave_bottom),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth().height(60.dp),
                        contentScale = ContentScale.FillWidth
                    )
                }
            }
        }
    }
}
