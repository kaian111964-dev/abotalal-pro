package com.example.ui.dialogs

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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.R
import com.example.data.AppDataSource
import com.example.model.Screen
import com.example.ui.theme.AccentGreen
import com.example.ui.theme.BrandBlue
import com.example.ui.theme.BrandRed

@Composable
fun DrawerMenuDialog(
    userFullName: String,
    isDarkMode: Boolean,
    onDismiss: () -> Unit,
    onToggleDarkMode: () -> Unit,
    onNavigate: (Screen) -> Unit,
    onOpenBooking: () -> Unit,
    onOpenNotifications: () -> Unit,
    onOpenAssistant: () -> Unit,
    onOpenAbout: () -> Unit,
    onOpenContact: () -> Unit,
    onOpenPortfolio: () -> Unit
) {
    val context = LocalContext.current

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable { onDismiss() }
            ) {
                Surface(
                    shape = RoundedCornerShape(topStart = 24.dp, bottomStart = 24.dp),
                    color = if (isDarkMode) Color(0xFF0F1E3D) else Color.White,
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.82f)
                        .align(Alignment.CenterStart)
                        .clickable(enabled = false) {}
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .statusBarsPadding()
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            // Close Button & Header
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Surface(
                                    shape = CircleShape,
                                    color = if (isDarkMode) Color(0xFF1B2C54) else Color(0xFFEEF4FD),
                                    modifier = Modifier.size(34.dp)
                                ) {
                                    IconButton(onClick = onDismiss) {
                                        Icon(Icons.Default.Close, contentDescription = "إغلاق", modifier = Modifier.size(18.dp))
                                    }
                                }

                                Image(
                                    painter = painterResource(id = R.drawable.logo_header),
                                    contentDescription = "ابو طلال",
                                    modifier = Modifier
                                        .width(130.dp)
                                        .height(42.dp),
                                    contentScale = ContentScale.Fit
                                )
                            }

                            Spacer(modifier = Modifier.height(14.dp))

                            // User Profile Card
                            Surface(
                                shape = RoundedCornerShape(14.dp),
                                color = if (isDarkMode) Color(0xFF162548) else Color(0xFFF3F7FD),
                                border = BorderStroke(1.dp, if (isDarkMode) Color(0xFF263C70) else Color(0xFFD6E3F4)),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Surface(
                                        shape = CircleShape,
                                        color = BrandBlue,
                                        modifier = Modifier.size(42.dp)
                                    ) {
                                        Box(contentAlignment = Alignment.Center) {
                                            Text(
                                                text = userFullName.take(1),
                                                style = MaterialTheme.typography.titleMedium.copy(
                                                    color = Color.White,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.width(10.dp))

                                    Column {
                                        Text(
                                            text = userFullName,
                                            style = MaterialTheme.typography.titleSmall.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = if (isDarkMode) Color.White else Color(0xFF0F2B5C)
                                            )
                                        )
                                        Text(
                                            text = AppDataSource.PHONE_NUMBER,
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                color = Color(0xFF7E8EAA),
                                                fontSize = 11.sp
                                            )
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            // Menu items
                            DrawerItem(
                                icon = Icons.Default.Home,
                                title = "الرئيسية",
                                isDarkMode = isDarkMode,
                                onClick = onDismiss
                            )

                            DrawerItem(
                                icon = Icons.Default.AddCircle,
                                title = "حجز وطلب خدمة",
                                isDarkMode = isDarkMode,
                                onClick = {
                                    onDismiss()
                                    onOpenBooking()
                                }
                            )

                            DrawerItem(
                                icon = Icons.Default.GridView,
                                title = "معرض أعمالنا",
                                isDarkMode = isDarkMode,
                                onClick = {
                                    onDismiss()
                                    onOpenPortfolio()
                                }
                            )

                            DrawerItem(
                                icon = Icons.Default.SmartToy,
                                title = "المساعد الذكي",
                                isDarkMode = isDarkMode,
                                onClick = {
                                    onDismiss()
                                    onOpenAssistant()
                                }
                            )

                            DrawerItem(
                                icon = Icons.Default.Notifications,
                                title = "الإشعارات والعروض",
                                isDarkMode = isDarkMode,
                                onClick = {
                                    onDismiss()
                                    onOpenNotifications()
                                }
                            )

                            DrawerItem(
                                icon = Icons.Default.Phone,
                                title = "اتصل بنا",
                                isDarkMode = isDarkMode,
                                onClick = {
                                    onDismiss()
                                    onOpenContact()
                                }
                            )

                            DrawerItem(
                                icon = Icons.Default.Info,
                                title = "من نحن ورؤيتنا",
                                isDarkMode = isDarkMode,
                                onClick = {
                                    onDismiss()
                                    onOpenAbout()
                                }
                            )

                            HorizontalDivider(
                                modifier = Modifier.padding(vertical = 10.dp),
                                color = if (isDarkMode) Color(0xFF22355C) else Color(0xFFE2ECFA)
                            )

                            // Night Mode Toggle item
                            DrawerItem(
                                icon = if (isDarkMode) Icons.Default.LightMode else Icons.Default.DarkMode,
                                title = if (isDarkMode) "تفعيل الوضع النهاري" else "تفعيل الوضع الليلي",
                                isDarkMode = isDarkMode,
                                onClick = onToggleDarkMode
                            )

                            // Logout / Re-login
                            DrawerItem(
                                icon = Icons.AutoMirrored.Filled.ExitToApp,
                                title = "تسجيل الخروج",
                                isDarkMode = isDarkMode,
                                iconTint = BrandRed,
                                onClick = {
                                    onDismiss()
                                    onNavigate(Screen.LOGIN)
                                }
                            )
                        }

                        // Bottom Direct Call Action
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = BrandBlue,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    val intent = Intent(Intent.ACTION_DIAL).apply {
                                        data = Uri.parse("tel:${AppDataSource.PHONE_NUMBER}")
                                    }
                                    context.startActivity(intent)
                                }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(Icons.Default.Call, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "اتصال مباشر: ${AppDataSource.PHONE_NUMBER}",
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

@Composable
fun DrawerItem(
    icon: ImageVector,
    title: String,
    isDarkMode: Boolean,
    iconTint: Color = BrandBlue,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = Color.Transparent,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 11.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = if (isDarkMode) Color.White else Color(0xFF132B54),
                    fontSize = 13.sp
                )
            )
        }
    }
}
