package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material.icons.filled.Smartphone
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.BottomTab
import com.example.model.PortfolioProject
import com.example.ui.theme.BrandBlue
import com.example.ui.theme.BrandRed

@Composable
fun PortfolioScreen(
    isDarkMode: Boolean,
    unreadCount: Int,
    projects: List<PortfolioProject>,
    activeCategory: String,
    onSelectCategory: (String) -> Unit,
    onOpenProject: (PortfolioProject) -> Unit,
    onToggleDarkMode: () -> Unit,
    onOpenDrawer: () -> Unit,
    onOpenNotifications: () -> Unit,
    onOpenProfile: () -> Unit,
    onSelectBottomTab: (BottomTab) -> Unit,
    onNavigateHome: () -> Unit
) {
    BackHandler {
        onNavigateHome()
    }

    // Filter projects based on activeCategory
    val filteredProjects = if (activeCategory == "الكل") {
        projects
    } else {
        projects.filter { it.category == activeCategory }
    }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(if (isDarkMode) Color(0xFF071228) else Color(0xFFF3F7FD))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .navigationBarsPadding(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // ==================== 1. TOP APP BAR ====================
                PortfolioTopAppBar(
                    isDarkMode = isDarkMode,
                    unreadCount = unreadCount,
                    onOpenDrawer = onOpenDrawer,
                    onToggleDarkMode = onToggleDarkMode,
                    onOpenNotifications = onOpenNotifications,
                    onOpenProfile = onOpenProfile
                )

                // ==================== 2. MAIN SCROLLABLE BODY ====================
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 10.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(6.dp))

                    // HERO BANNER: "معرض أعمالنا" Laptop Mockup
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        shadowElevation = 5.dp,
                        border = BorderStroke(1.dp, if (isDarkMode) Color(0xFF233B70) else Color(0xFFB8D3F8)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(130.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.portfolio_hero_banner),
                            contentDescription = "معرض أعمالنا",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // 4 FILTER PILLS: "الكل", "التطبيقات", "المواقع", "التصاميم"
                    PortfolioFilterTabs(
                        activeCategory = activeCategory,
                        isDarkMode = isDarkMode,
                        onSelectCategory = onSelectCategory
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // 2-COLUMN PROJECTS GRID (6 Projects in 3 rows)
                    val rows = filteredProjects.chunked(2)
                    rows.forEach { rowProjects ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            rowProjects.forEach { project ->
                                PortfolioCardItem(
                                    project = project,
                                    isDarkMode = isDarkMode,
                                    onClick = { onOpenProject(project) },
                                    modifier = Modifier.weight(1f)
                                )
                            }
                            if (rowProjects.size == 1) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                }

                // ==================== 3. BOTTOM CURVED NAVBAR ====================
                PortfolioBottomBar(
                    isDarkMode = isDarkMode,
                    onSelectTab = { tab ->
                        if (tab == BottomTab.HOME) {
                            onNavigateHome()
                        } else {
                            onSelectBottomTab(tab)
                        }
                    }
                )
            }
        }
    }
}

// -------------------------------------------------------------------------------------------------
// FILTER TABS
// -------------------------------------------------------------------------------------------------
@Composable
private fun PortfolioFilterTabs(
    activeCategory: String,
    isDarkMode: Boolean,
    onSelectCategory: (String) -> Unit
) {
    val tabs = listOf(
        Triple("الكل", Icons.Default.GridView, "الكل"),
        Triple("التطبيقات", Icons.Default.Smartphone, "التطبيقات"),
        Triple("المواقع", Icons.Default.Language, "المواقع"),
        Triple("التصاميم", Icons.Default.Palette, "التصاميم")
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        tabs.forEach { (title, icon, category) ->
            val isSelected = activeCategory == category
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = if (isSelected) {
                    BrandBlue
                } else {
                    if (isDarkMode) Color(0xFF101F40) else Color.White
                },
                shadowElevation = if (isSelected) 3.dp else 1.dp,
                border = BorderStroke(
                    1.dp,
                    if (isSelected) BrandBlue else (if (isDarkMode) Color(0xFF223A6D) else Color(0xFFD6E4FA))
                ),
                modifier = Modifier
                    .weight(1f)
                    .clickable { onSelectCategory(category) }
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        tint = if (isSelected) Color.White else (if (isDarkMode) Color(0xFF90B4EE) else BrandBlue),
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = title,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold,
                            color = if (isSelected) Color.White else (if (isDarkMode) Color(0xFFD6E4FA) else Color(0xFF0F3B84)),
                            fontSize = 11.5.sp
                        )
                    )
                }
            }
        }
    }
}

// -------------------------------------------------------------------------------------------------
// PORTFOLIO CARD ITEM
// -------------------------------------------------------------------------------------------------
@Composable
private fun PortfolioCardItem(
    project: PortfolioProject,
    isDarkMode: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = if (isDarkMode) Color(0xFF0F1E3D) else Color.White,
        shadowElevation = 4.dp,
        border = BorderStroke(1.dp, if (isDarkMode) Color(0xFF233B6E) else Color(0xFFD3E3F8)),
        modifier = modifier
            .aspectRatio(384f / 374f)
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = project.imageRes),
            contentDescription = project.title,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }
}

// -------------------------------------------------------------------------------------------------
// TOP BAR
// -------------------------------------------------------------------------------------------------
@Composable
private fun PortfolioTopAppBar(
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
            // Hamburger Menu
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

            // Abu Talal Logo
            Image(
                painter = painterResource(id = R.drawable.logo_header),
                contentDescription = "شعار ابو طلال",
                modifier = Modifier
                    .width(135.dp)
                    .height(38.dp),
                contentScale = ContentScale.Fit
            )

            // Right Action Icons
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
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
                                    Badge(containerColor = BrandRed, contentColor = Color.White) {
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
// BOTTOM BAR
// -------------------------------------------------------------------------------------------------
@Composable
private fun PortfolioBottomBar(
    isDarkMode: Boolean,
    onSelectTab: (BottomTab) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
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
                // 1. Home
                PortfolioNavItem(
                    title = "الرئيسية",
                    icon = Icons.Default.Home,
                    isSelected = true,
                    isDarkMode = isDarkMode,
                    onClick = { onSelectTab(BottomTab.HOME) }
                )

                // 2. About Us
                PortfolioNavItem(
                    title = "من نحن",
                    icon = Icons.Default.Groups,
                    isSelected = false,
                    isDarkMode = isDarkMode,
                    onClick = { onSelectTab(BottomTab.ABOUT) }
                )

                Spacer(modifier = Modifier.width(48.dp))

                // 3. Contact Us
                PortfolioNavItem(
                    title = "اتصل بنا",
                    icon = Icons.Default.Call,
                    isSelected = false,
                    isDarkMode = isDarkMode,
                    onClick = { onSelectTab(BottomTab.CONTACT) }
                )

                // 4. Smart Assistant
                PortfolioNavItem(
                    title = "المساعد الذكي",
                    icon = Icons.Default.SmartToy,
                    isSelected = false,
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
private fun PortfolioNavItem(
    title: String,
    icon: ImageVector,
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
