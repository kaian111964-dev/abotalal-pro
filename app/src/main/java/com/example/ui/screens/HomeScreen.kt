package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.AppDataSource
import com.example.model.BottomTab
import com.example.model.ServiceItem
import com.example.model.SliderItem
import com.example.ui.theme.BrandBlue
import com.example.ui.theme.BrandRed

@Composable
fun HomeScreen(
    isDarkMode: Boolean,
    sliderIndex: Int,
    services: List<ServiceItem>,
    unreadCount: Int,
    activeBottomTab: BottomTab,
    onToggleDarkMode: () -> Unit,
    onOpenDrawer: () -> Unit,
    onOpenNotifications: () -> Unit,
    onOpenProfile: () -> Unit,
    onSelectService: (ServiceItem) -> Unit,
    onSelectBottomTab: (BottomTab) -> Unit,
    onBannerAction: () -> Unit,
    onNavigateToPortfolio: () -> Unit
) {
    val currentSlide = AppDataSource.sliderItems.getOrNull(sliderIndex) ?: AppDataSource.sliderItems.first()

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    if (isDarkMode) Color(0xFF071228) else Color(0xFFF3F7FD)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .navigationBarsPadding(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // ==================== 1. TOP APP BAR ====================
                HomeTopAppBar(
                    isDarkMode = isDarkMode,
                    unreadCount = unreadCount,
                    onOpenDrawer = onOpenDrawer,
                    onToggleDarkMode = onToggleDarkMode,
                    onOpenNotifications = onOpenNotifications,
                    onOpenProfile = onOpenProfile
                )

                // ==================== 2. MAIN 4-COLUMN BODY ====================
                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    val availableHeight = maxHeight
                    val isVerySmallScreen = availableHeight < 560.dp

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                            .then(
                                if (isVerySmallScreen) Modifier.verticalScroll(rememberScrollState())
                                else Modifier
                            ),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // HERO SLIDER BANNER
                        HeroBannerCard(
                            isDarkMode = isDarkMode,
                            sliderIndex = sliderIndex,
                            slide = currentSlide,
                            onActionClick = onBannerAction
                        )

                        Spacer(modifier = Modifier.height(3.dp))

                        // 4x4 SERVICES GRID (16 COMPLETE CARDS SHOWN DIRECTLY)
                        ServicesGrid(
                            services = services,
                            isDarkMode = isDarkMode,
                            onSelectService = { service ->
                                if (service.id == "portfolio") {
                                    onNavigateToPortfolio()
                                } else {
                                    onSelectService(service)
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        )

                        Spacer(modifier = Modifier.height(3.dp))

                        // SLOGAN DIVIDER: "— معاً نحو عالم رقمي أفضل —"
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 2.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(36.dp)
                                    .height(1.5.dp)
                                    .background(if (isDarkMode) Color(0xFF324D85) else Color(0xFF0F4CB8))
                            )
                            Text(
                                text = "  معاً نحو عالم رقمي أفضل  ",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.ExtraBold,
                                    color = if (isDarkMode) Color(0xFF7FA8ED) else Color(0xFF0D3D94),
                                    fontSize = 11.5.sp
                                )
                            )
                            Box(
                                modifier = Modifier
                                    .width(36.dp)
                                    .height(1.5.dp)
                                    .background(if (isDarkMode) Color(0xFF324D85) else Color(0xFF0F4CB8))
                            )
                        }
                    }
                }

                // ==================== 3. BOTTOM CURVED NAVBAR WITH CENTER FAB ====================
                BottomCurvedNavBar(
                    activeTab = activeBottomTab,
                    isDarkMode = isDarkMode,
                    onSelectTab = onSelectBottomTab
                )
            }
        }
    }
}

// -------------------------------------------------------------------------------------------------
// TOP BAR COMPONENT
// -------------------------------------------------------------------------------------------------
@Composable
private fun HomeTopAppBar(
    isDarkMode: Boolean,
    unreadCount: Int,
    onOpenDrawer: () -> Unit,
    onToggleDarkMode: () -> Unit,
    onOpenNotifications: () -> Unit,
    onOpenProfile: () -> Unit
) {
    Surface(
        color = if (isDarkMode) Color(0xFF0B1736) else Color.White,
        shadowElevation = 3.dp,
        border = BorderStroke(1.dp, if (isDarkMode) Color(0xFF1E3260) else Color(0xFFE2ECFA)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Hamburger Menu Button (Blue pill icon)
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = BrandBlue,
                modifier = Modifier
                    .size(36.dp)
                    .clickable { onOpenDrawer() }
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "القائمة",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            // Abu Talal Logo in Center
            Image(
                painter = painterResource(id = R.drawable.logo_header),
                contentDescription = "شعار ابو طلال",
                modifier = Modifier
                    .width(135.dp)
                    .height(38.dp),
                contentScale = ContentScale.Fit
            )

            // Right Action Icons (Night Mode, Notifications, User)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                // Day/Night Toggle Pill
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = if (isDarkMode) Color(0xFF192F5C) else Color(0xFF0F397A),
                    modifier = Modifier
                        .size(34.dp)
                        .clickable { onToggleDarkMode() }
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = if (isDarkMode) Icons.Default.LightMode else Icons.Default.DarkMode,
                            contentDescription = "الوضع الليلي",
                            tint = Color(0xFFFFD54F),
                            modifier = Modifier.size(17.dp)
                        )
                    }
                }

                // Notification Bell with Badge
                Surface(
                    shape = CircleShape,
                    color = if (isDarkMode) Color(0xFF192F5C) else Color(0xFFEAF1FC),
                    modifier = Modifier
                        .size(34.dp)
                        .clickable { onOpenNotifications() }
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        BadgedBox(
                            badge = {
                                if (unreadCount > 0) {
                                    Badge(
                                        containerColor = BrandRed,
                                        contentColor = Color.White
                                    ) {
                                        Text(text = unreadCount.toString(), fontSize = 9.sp)
                                    }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "الإشعارات",
                                tint = if (isDarkMode) Color.White else BrandBlue,
                                modifier = Modifier.size(19.dp)
                            )
                        }
                    }
                }

                // Profile Avatar Icon
                Surface(
                    shape = CircleShape,
                    color = BrandBlue,
                    modifier = Modifier
                        .size(34.dp)
                        .clickable { onOpenProfile() }
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "الملف الشخصي",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}

// -------------------------------------------------------------------------------------------------
// HERO BANNER SLIDER COMPONENT
// -------------------------------------------------------------------------------------------------
@Composable
private fun HeroBannerCard(
    isDarkMode: Boolean,
    sliderIndex: Int,
    slide: SliderItem,
    onActionClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 5.dp,
        border = BorderStroke(1.dp, if (isDarkMode) Color(0xFF233B70) else Color(0xFFB8D3F8)),
        modifier = Modifier
            .fillMaxWidth()
            .height(104.dp)
            .clickable { onActionClick() }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = slide.bannerRes),
                contentDescription = slide.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Dynamic Overlay Texts for secondary slides
            if (sliderIndex != 0) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFF001F5C).copy(alpha = 0.95f),
                                    Color(0xFF04388A).copy(alpha = 0.85f),
                                    Color.Transparent
                                )
                            )
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 16.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = slide.title,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 15.sp
                            )
                        )
                        Text(
                            text = slide.subtitle,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color(0xFFD6E4FA),
                                fontSize = 11.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Surface(
                            shape = RoundedCornerShape(14.dp),
                            color = BrandRed
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = slide.ctaText,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(12.dp)
                                )
                            }
                        }
                    }
                }
            }

            // Pagination Dots Indicator at bottom center
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(4) { idx ->
                    Box(
                        modifier = Modifier
                            .size(if (idx == sliderIndex) 6.dp else 4.dp)
                            .clip(CircleShape)
                            .background(
                                if (idx == sliderIndex) BrandRed else Color.White.copy(alpha = 0.8f)
                            )
                    )
                }
            }
        }
    }
}

// -------------------------------------------------------------------------------------------------
// 4x4 SERVICES GRID COMPONENT (16 CARDS IN 4 COLUMNS, FULLY VISIBLE)
// -------------------------------------------------------------------------------------------------
@Composable
private fun ServicesGrid(
    services: List<ServiceItem>,
    isDarkMode: Boolean,
    onSelectService: (ServiceItem) -> Unit,
    modifier: Modifier = Modifier
) {
    // 4 rows of 4 cards
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        val rows = services.chunked(4)
        rows.forEach { rowServices ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                rowServices.forEach { service ->
                    ServiceCardItem(
                        service = service,
                        isDarkMode = isDarkMode,
                        onClick = { onSelectService(service) },
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    )
                }
            }
        }
    }
}

@Composable
private fun ServiceCardItem(
    service: ServiceItem,
    isDarkMode: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = if (isDarkMode) Color(0xFF101E3E) else Color.White,
        shadowElevation = 2.dp,
        border = BorderStroke(
            0.8.dp,
            if (isDarkMode) Color(0xFF223868) else Color(0xFFD8E7FA)
        ),
        modifier = modifier
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = service.cardImageRes),
            contentDescription = service.title,
            modifier = Modifier
                .fillMaxSize()
                .padding(1.dp),
            contentScale = ContentScale.Fit
        )
    }
}

// -------------------------------------------------------------------------------------------------
// BOTTOM CURVED NAVBAR COMPONENT
// -------------------------------------------------------------------------------------------------
@Composable
private fun BottomCurvedNavBar(
    activeTab: BottomTab,
    isDarkMode: Boolean,
    onSelectTab: (BottomTab) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        // Main Navigation Surface
        Surface(
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            color = if (isDarkMode) Color(0xFF0B1736) else Color.White,
            shadowElevation = 8.dp,
            border = BorderStroke(1.dp, if (isDarkMode) Color(0xFF1E3260) else Color(0xFFDCE8F8)),
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                // 1. Home ("الرئيسية")
                NavBarItem(
                    title = "الرئيسية",
                    icon = Icons.Default.Home,
                    isSelected = activeTab == BottomTab.HOME,
                    isDarkMode = isDarkMode,
                    onClick = { onSelectTab(BottomTab.HOME) }
                )

                // 2. About Us ("من نحن")
                NavBarItem(
                    title = "من نحن",
                    icon = Icons.Default.Groups,
                    isSelected = activeTab == BottomTab.ABOUT,
                    isDarkMode = isDarkMode,
                    onClick = { onSelectTab(BottomTab.ABOUT) }
                )

                // Spacer for the center floating button
                Spacer(modifier = Modifier.width(48.dp))

                // 3. Contact Us ("اتصل بنا")
                NavBarItem(
                    title = "اتصل بنا",
                    icon = Icons.Default.Call,
                    isSelected = activeTab == BottomTab.CONTACT,
                    isDarkMode = isDarkMode,
                    onClick = { onSelectTab(BottomTab.CONTACT) }
                )

                // 4. Smart Assistant ("المساعد الذكي")
                NavBarItem(
                    title = "المساعد الذكي",
                    icon = Icons.Default.SmartToy,
                    isSelected = activeTab == BottomTab.ASSISTANT,
                    isDarkMode = isDarkMode,
                    onClick = { onSelectTab(BottomTab.ASSISTANT) }
                )
            }
        }

        // Center Floating Action Button: (+) "حجز"
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-4).dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                shape = CircleShape,
                color = BrandBlue,
                shadowElevation = 6.dp,
                border = BorderStroke(2.5.dp, if (isDarkMode) Color(0xFF071228) else Color(0xFFF3F7FD)),
                modifier = Modifier
                    .size(46.dp)
                    .clickable { onSelectTab(BottomTab.BOOKING) }
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "حجز",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Text(
                text = "حجز",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrandBlue
                )
            )
        }
    }
}

@Composable
private fun NavBarItem(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    isSelected: Boolean,
    isDarkMode: Boolean,
    onClick: () -> Unit
) {
    if (isSelected) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = BrandBlue,
            modifier = Modifier
                .padding(vertical = 4.dp)
                .clickable { onClick() }
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    )
                )
            }
        }
    } else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clickable { onClick() }
                .padding(vertical = 4.dp, horizontal = 6.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = if (isDarkMode) Color(0xFFA5B8D8) else Color(0xFF0F3B84),
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 9.5.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = if (isDarkMode) Color(0xFFA5B8D8) else Color(0xFF0F3B84)
                )
            )
        }
    }
}
