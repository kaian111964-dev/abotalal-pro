package com.example.ui.dialogs

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.MiscellaneousServices
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.AppDataSource
import com.example.ui.theme.AccentGreen
import com.example.ui.theme.BrandBlue
import com.example.ui.theme.BrandRed

@Composable
fun BookingDialog(
    presetService: String,
    successMessage: String?,
    isDarkMode: Boolean,
    onDismiss: () -> Unit,
    onSubmit: (name: String, phone: String, service: String, notes: String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var selectedService by remember {
        mutableStateOf(presetService.ifBlank { AppDataSource.services.firstOrNull()?.title ?: "البرمجة والتطوير" })
    }
    var notes by remember { mutableStateOf("") }
    var showServiceMenu by remember { mutableStateOf(false) }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Surface(
                shape = RoundedCornerShape(24.dp),
                color = if (isDarkMode) Color(0xFF101F40) else Color.White,
                border = BorderStroke(1.dp, if (isDarkMode) Color(0xFF263F76) else Color(0xFFD6E4F8)),
                modifier = Modifier
                    .fillMaxWidth(0.92f)
                    .padding(vertical = 20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Header
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = if (isDarkMode) Color(0xFF1A2F5E) else Color(0xFFEEF4FD),
                            modifier = Modifier.size(34.dp)
                        ) {
                            IconButton(onClick = onDismiss) {
                                Icon(Icons.Default.Close, contentDescription = "إغلاق", modifier = Modifier.size(18.dp))
                            }
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Surface(
                                shape = CircleShape,
                                color = BrandBlue,
                                modifier = Modifier.size(24.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(Icons.Default.Add, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                                }
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "حجز وطلب خدمة",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = if (isDarkMode) Color.White else Color(0xFF0F2B5C)
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    if (successMessage != null) {
                        // Success state banner
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = AccentGreen.copy(alpha = 0.15f),
                            border = BorderStroke(1.5.dp, AccentGreen),
                            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(18.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = AccentGreen,
                                    modifier = Modifier.size(46.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = successMessage,
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        color = if (isDarkMode) Color.White else Color(0xFF144D25),
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center
                                    )
                                )
                            }
                        }
                    } else {
                        // Full Name
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
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
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // Phone Number
                        OutlinedTextField(
                            value = phone,
                            onValueChange = { phone = it },
                            placeholder = { Text("رقم الهاتف (واتساب)", fontSize = 12.sp) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                            trailingIcon = {
                                Icon(Icons.Default.Call, contentDescription = null, tint = BrandBlue, modifier = Modifier.size(19.dp))
                            },
                            shape = RoundedCornerShape(10.dp),
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

                        // Service Selector
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                border = BorderStroke(1.dp, if (isDarkMode) Color(0xFF2C4378) else Color(0xFFD6E3F4)),
                                color = if (isDarkMode) Color(0xFF162548) else Color(0xFFF9FBFF),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(52.dp)
                                    .clickable { showServiceMenu = true }
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(horizontal = 12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(Icons.Default.MiscellaneousServices, contentDescription = null, tint = BrandBlue, modifier = Modifier.size(19.dp))
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = selectedService,
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                color = if (isDarkMode) Color.White else Color(0xFF1C2D4B),
                                                fontWeight = FontWeight.SemiBold
                                            )
                                        )
                                    }
                                    Text("▾", color = Color(0xFF7E8EAA))
                                }
                            }

                            DropdownMenu(
                                expanded = showServiceMenu,
                                onDismissRequest = { showServiceMenu = false }
                            ) {
                                AppDataSource.services.forEach { service ->
                                    DropdownMenuItem(
                                        text = { Text(service.title) },
                                        onClick = {
                                            selectedService = service.title
                                            showServiceMenu = false
                                        }
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Notes field
                        OutlinedTextField(
                            value = notes,
                            onValueChange = { notes = it },
                            placeholder = { Text("تفاصيل أو متطلبات إضافية ...", fontSize = 12.sp) },
                            trailingIcon = {
                                Icon(Icons.Default.EditNote, contentDescription = null, tint = BrandBlue, modifier = Modifier.size(20.dp))
                            },
                            shape = RoundedCornerShape(10.dp),
                            maxLines = 3,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = BrandBlue,
                                unfocusedBorderColor = if (isDarkMode) Color(0xFF2C4378) else Color(0xFFD6E3F4),
                                focusedContainerColor = if (isDarkMode) Color(0xFF162548) else Color(0xFFF9FBFF),
                                unfocusedContainerColor = if (isDarkMode) Color(0xFF162548) else Color(0xFFF9FBFF)
                            ),
                            modifier = Modifier.fillMaxWidth().height(80.dp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Submit Button
                        Button(
                            onClick = {
                                onSubmit(
                                    name.ifBlank { "عميل ابو طلال" },
                                    phone.ifBlank { "778215553" },
                                    selectedService,
                                    notes
                                )
                            },
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = BrandRed),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(46.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "تأكيد وإرسال الحجز",
                                    style = MaterialTheme.typography.labelLarge.copy(
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
