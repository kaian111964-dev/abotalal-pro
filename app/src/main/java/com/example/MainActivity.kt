package com.example

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.model.Screen
import com.example.ui.dialogs.AboutUsDialog
import com.example.ui.dialogs.BookingDialog
import com.example.ui.dialogs.ContactUsDialog
import com.example.ui.dialogs.DrawerMenuDialog
import com.example.ui.dialogs.NotificationsDialog
import com.example.ui.dialogs.ProfileDialog
import com.example.ui.dialogs.ServiceDetailDialog
import com.example.ui.dialogs.SmartAssistantDialog
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LoginScreen
import com.example.ui.screens.RegisterScreen
import com.example.ui.screens.SplashScreen
import com.example.ui.theme.AbuTalalTheme
import com.example.util.NotificationHelper
import com.example.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AbuTalalApp()
        }
    }
}

@Composable
fun AbuTalalApp(
    viewModel: MainViewModel = viewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // Permissions to request: Notifications + Storage/Files
    val permissionsToRequest = remember {
        buildList {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                add(Manifest.permission.POST_NOTIFICATIONS)
                add(Manifest.permission.READ_MEDIA_IMAGES)
            } else {
                add(Manifest.permission.READ_EXTERNAL_STORAGE)
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                    add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                }
            }
        }.toTypedArray()
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        // When notification permission is handled, send welcome notification
        NotificationHelper.createNotificationChannel(context)
        NotificationHelper.sendWelcomeNotification(context)
    }

    LaunchedEffect(Unit) {
        NotificationHelper.createNotificationChannel(context)
        permissionLauncher.launch(permissionsToRequest)
    }

    AbuTalalTheme(darkTheme = uiState.isDarkMode) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            // Handle Back Button navigation
            BackHandler(enabled = uiState.currentScreen != Screen.HOME) {
                viewModel.navigateTo(Screen.HOME)
            }

            // Screen Switching
            when (uiState.currentScreen) {
                Screen.HOME -> {
                    HomeScreen(
                        isDarkMode = uiState.isDarkMode,
                        sliderIndex = uiState.sliderIndex,
                        services = uiState.services,
                        unreadCount = uiState.unreadNotificationsCount,
                        activeBottomTab = uiState.activeBottomTab,
                        onToggleDarkMode = { viewModel.toggleDarkMode() },
                        onOpenDrawer = { viewModel.openDrawerMenu() },
                        onOpenNotifications = { viewModel.openNotificationsDialog() },
                        onOpenProfile = { viewModel.openProfileDialog() },
                        onSelectService = { service -> viewModel.openServiceDetail(service) },
                        onSelectBottomTab = { tab -> viewModel.selectBottomTab(tab) },
                        onBannerAction = { viewModel.openBookingDialog("حلول الذكاء الاصطناعي") }
                    )
                }

                Screen.SPLASH -> {
                    SplashScreen(
                        onNavigate = { viewModel.navigateTo(it) }
                    )
                }

                Screen.LOGIN -> {
                    LoginScreen(
                        isDarkMode = uiState.isDarkMode,
                        onToggleDarkMode = { viewModel.toggleDarkMode() },
                        onLogin = { username -> viewModel.loginUser(username) },
                        onNavigateRegister = { viewModel.navigateTo(Screen.REGISTER) },
                        onSkipLogin = { viewModel.navigateTo(Screen.HOME) }
                    )
                }

                Screen.REGISTER -> {
                    RegisterScreen(
                        isDarkMode = uiState.isDarkMode,
                        onToggleDarkMode = { viewModel.toggleDarkMode() },
                        onRegister = { fullName, phone, email ->
                            viewModel.registerUser(fullName, phone, email)
                        },
                        onNavigateLogin = { viewModel.navigateTo(Screen.LOGIN) }
                    )
                }
            }

            // ==================== OVERLAY DIALOGS ====================

            // 1. Service Detail Dialog
            if (uiState.selectedService != null) {
                if (uiState.selectedService?.id == "assistant") {
                    SmartAssistantDialog(
                        messages = uiState.chatMessages,
                        isTyping = uiState.isAssistantTyping,
                        isDarkMode = uiState.isDarkMode,
                        onDismiss = { viewModel.closeServiceDetail() },
                        onSendMessage = { text -> viewModel.sendChatMessage(text) }
                    )
                } else {
                    ServiceDetailDialog(
                        service = uiState.selectedService,
                        isDarkMode = uiState.isDarkMode,
                        onDismiss = { viewModel.closeServiceDetail() },
                        onBookService = { serviceName -> viewModel.openBookingDialog(serviceName) }
                    )
                }
            }

            // 2. Booking / Service Request Dialog
            if (uiState.showBookingDialog) {
                BookingDialog(
                    presetService = uiState.bookingPresetService,
                    successMessage = uiState.bookingSuccessMessage,
                    isDarkMode = uiState.isDarkMode,
                    onDismiss = { viewModel.closeBookingDialog() },
                    onSubmit = { name, phone, service, notes ->
                        viewModel.submitBooking(name, phone, service, notes)
                        NotificationHelper.sendBookingNotification(context, service)
                    }
                )
            }

            // 3. Notifications Dialog
            if (uiState.showNotificationsDialog) {
                NotificationsDialog(
                    notifications = uiState.notifications,
                    isDarkMode = uiState.isDarkMode,
                    onDismiss = { viewModel.closeNotificationsDialog() }
                )
            }

            // 4. User Profile Dialog
            if (uiState.showProfileDialog) {
                ProfileDialog(
                    fullName = uiState.userFullName,
                    phone = uiState.userPhone,
                    email = uiState.userEmail,
                    isDarkMode = uiState.isDarkMode,
                    onDismiss = { viewModel.closeProfileDialog() },
                    onLogout = { viewModel.navigateTo(Screen.LOGIN) }
                )
            }

            // 5. About Us Dialog
            if (uiState.showAboutDialog) {
                AboutUsDialog(
                    isDarkMode = uiState.isDarkMode,
                    onDismiss = { viewModel.closeAboutDialog() }
                )
            }

            // 6. Contact Us Dialog
            if (uiState.showContactDialog) {
                ContactUsDialog(
                    isDarkMode = uiState.isDarkMode,
                    onDismiss = { viewModel.closeContactDialog() }
                )
            }

            // 7. Side Navigation Drawer
            if (uiState.showDrawerMenu) {
                DrawerMenuDialog(
                    userFullName = uiState.userFullName,
                    isDarkMode = uiState.isDarkMode,
                    onDismiss = { viewModel.closeDrawerMenu() },
                    onToggleDarkMode = { viewModel.toggleDarkMode() },
                    onNavigate = { screen -> viewModel.navigateTo(screen) },
                    onOpenBooking = { viewModel.openBookingDialog() },
                    onOpenNotifications = { viewModel.openNotificationsDialog() },
                    onOpenAssistant = { viewModel.openServiceById("assistant") },
                    onOpenAbout = { viewModel.openAboutDialog() },
                    onOpenContact = { viewModel.openContactDialog() }
                )
            }
        }
    }
}
