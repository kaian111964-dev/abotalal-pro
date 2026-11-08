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
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.data.AppDataSource
import com.example.model.PortfolioProject
import com.example.ui.theme.AccentGreen
import com.example.ui.theme.BrandBlue
import com.example.ui.theme.BrandRed

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProjectDetailDialog(
    project: PortfolioProject?,
    isDarkMode: Boolean,
    onDismiss: () -> Unit,
    onOrderProject: (String) -> Unit
) {
    if (project == null) return
    val context = LocalContext.current

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.55f))
                    .clickable { onDismiss() },
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    shape = RoundedCornerShape(22.dp),
                    color = if (isDarkMode) Color(0xFF0F1E3D) else Color.White,
                    border = BorderStroke(1.2.dp, if (isDarkMode) Color(0xFF263C70) else Color(0xFFCCE0FA)),
                    shadowElevation = 10.dp,
                    modifier = Modifier
                        .fillMaxWidth(0.92f)
                        .padding(vertical = 24.dp)
                        .clickable(enabled = false) {}
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                            .padding(18.dp)
                    ) {
                        // Header with Close and Tag
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                shape = RoundedCornerShape(20.dp),
                                color = BrandRed
                            ) {
                                Text(
                                    text = project.badge,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 11.sp
                                    ),
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                                )
                            }

                            Surface(
                                shape = CircleShape,
                                color = if (isDarkMode) Color(0xFF1B2C54) else Color(0xFFEEF4FD),
                                modifier = Modifier.size(34.dp)
                            ) {
                                IconButton(onClick = onDismiss) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "إغلاق",
                                        tint = if (isDarkMode) Color.White else BrandBlue,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Project Card Graphic
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            border = BorderStroke(1.dp, if (isDarkMode) Color(0xFF243B6E) else Color(0xFFD6E4FA)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(190.dp)
                        ) {
                            Image(
                                painter = painterResource(id = project.imageRes),
                                contentDescription = project.title,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Title & Subtitle
                        Text(
                            text = project.title,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = if (isDarkMode) Color.White else Color(0xFF0F2B5C),
                                fontSize = 18.sp
                            )
                        )

                        Text(
                            text = project.subtitle,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = if (isDarkMode) Color(0xFFA5BBDD) else Color(0xFF265296),
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 13.sp
                            )
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // Description
                        Text(
                            text = project.description,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = if (isDarkMode) Color(0xFFCBD7E8) else Color(0xFF4A5D7C),
                                fontSize = 12.5.sp,
                                lineHeight = 18.sp
                            )
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        // Technologies
                        Text(
                            text = "التقنيات المستخدمة:",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = if (isDarkMode) Color.White else Color(0xFF0F2B5C),
                                fontSize = 13.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(6.dp))

                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            project.technologies.forEach { tech ->
                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = if (isDarkMode) Color(0xFF192F5C) else Color(0xFFEEF5FF),
                                    border = BorderStroke(1.dp, if (isDarkMode) Color(0xFF2A4580) else Color(0xFFC8DCF8))
                                ) {
                                    Text(
                                        text = tech,
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = if (isDarkMode) Color(0xFF90B7F4) else BrandBlue,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 11.sp
                                        ),
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Key Features
                        Text(
                            text = "مزايا ومواصفات المشروع:",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = if (isDarkMode) Color.White else Color(0xFF0F2B5C),
                                fontSize = 13.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(6.dp))

                        project.features.forEach { feat ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 3.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = AccentGreen,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = feat,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = if (isDarkMode) Color(0xFFD6E3F5) else Color(0xFF253754),
                                        fontSize = 12.sp
                                    )
                                )
                            }
                        }

                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 14.dp),
                            color = if (isDarkMode) Color(0xFF20345C) else Color(0xFFE2EDFB)
                        )

                        // Action Buttons
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = {
                                    onDismiss()
                                    onOrderProject(project.title)
                                },
                                shape = RoundedCornerShape(14.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = BrandRed),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(44.dp)
                            ) {
                                Text(
                                    text = "طلب مشروع مماثل",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp
                                    )
                                )
                            }

                            OutlinedButton(
                                onClick = {
                                    val url = "https://wa.me/967778215553?text=${Uri.encode("مرحباً، أود الاستفسار عن مشروع: ${project.title}")}"
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                    context.startActivity(intent)
                                },
                                shape = RoundedCornerShape(14.dp),
                                border = BorderStroke(1.2.dp, AccentGreen),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = AccentGreen),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(44.dp)
                            ) {
                                Text(
                                    text = "استفسار واتساب",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp
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
