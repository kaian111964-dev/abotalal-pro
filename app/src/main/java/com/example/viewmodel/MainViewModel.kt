package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.AppDataSource
import com.example.model.AppNotification
import com.example.model.BottomTab
import com.example.model.ChatMessage
import com.example.model.Screen
import com.example.model.ServiceItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class MainUiState(
    val currentScreen: Screen = Screen.HOME,
    val isDarkMode: Boolean = false,
    val selectedLanguage: String = "العربية",
    val activeBottomTab: BottomTab = BottomTab.HOME,
    val sliderIndex: Int = 0,
    val services: List<ServiceItem> = AppDataSource.services,
    val selectedService: ServiceItem? = null,
    val showBookingDialog: Boolean = false,
    val bookingPresetService: String = "",
    val showNotificationsDialog: Boolean = false,
    val notifications: List<AppNotification> = AppDataSource.initialNotifications,
    val unreadNotificationsCount: Int = 3,
    val showProfileDialog: Boolean = false,
    val showAboutDialog: Boolean = false,
    val showContactDialog: Boolean = false,
    val showDrawerMenu: Boolean = false,
    val chatMessages: List<ChatMessage> = AppDataSource.initialChatMessages,
    val isAssistantTyping: Boolean = false,
    val userFullName: String = "مستخدم ابو طلال",
    val userPhone: String = "778215553",
    val userEmail: String = "client@abotalalpro.com",
    val isLoggedIn: Boolean = false,
    val bookingSuccessMessage: String? = null,
    val portfolioProjects: List<com.example.model.PortfolioProject> = AppDataSource.portfolioProjects,
    val selectedPortfolioProject: com.example.model.PortfolioProject? = null,
    val activePortfolioCategory: String = "الكل"
)

class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {

        // Hero banner auto-scroll loop
        viewModelScope.launch {
            while (true) {
                delay(4000)
                _uiState.update {
                    val nextIndex = (it.sliderIndex + 1) % AppDataSource.sliderItems.size
                    it.copy(sliderIndex = nextIndex)
                }
            }
        }
    }

    fun navigateTo(screen: Screen) {
        _uiState.update { it.copy(currentScreen = screen) }
    }

    fun toggleDarkMode() {
        _uiState.update { it.copy(isDarkMode = !it.isDarkMode) }
    }

    fun setLanguage(lang: String) {
        _uiState.update { it.copy(selectedLanguage = lang) }
    }

    fun selectBottomTab(tab: BottomTab) {
        _uiState.update { it.copy(activeBottomTab = tab) }
        when (tab) {
            BottomTab.HOME -> { /* Home view */ }
            BottomTab.ABOUT -> openAboutDialog()
            BottomTab.BOOKING -> openBookingDialog()
            BottomTab.CONTACT -> openContactDialog()
            BottomTab.ASSISTANT -> openServiceById("assistant")
        }
    }

    fun setSliderIndex(index: Int) {
        _uiState.update { it.copy(sliderIndex = index) }
    }

    fun openServiceDetail(service: ServiceItem) {
        _uiState.update { it.copy(selectedService = service) }
    }

    fun openServiceById(id: String) {
        val service = AppDataSource.services.find { it.id == id }
        if (service != null) {
            _uiState.update { it.copy(selectedService = service) }
        }
    }

    fun closeServiceDetail() {
        _uiState.update { it.copy(selectedService = null) }
    }

    fun selectPortfolioCategory(category: String) {
        _uiState.update { it.copy(activePortfolioCategory = category) }
    }

    fun openPortfolioProject(project: com.example.model.PortfolioProject) {
        _uiState.update { it.copy(selectedPortfolioProject = project) }
    }

    fun closePortfolioProject() {
        _uiState.update { it.copy(selectedPortfolioProject = null) }
    }

    fun openBookingDialog(presetService: String = "") {
        _uiState.update {
            it.copy(
                showBookingDialog = true,
                bookingPresetService = presetService,
                bookingSuccessMessage = null
            )
        }
    }

    fun closeBookingDialog() {
        _uiState.update { it.copy(showBookingDialog = false) }
    }

    fun submitBooking(name: String, phone: String, service: String, notes: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    bookingSuccessMessage = "تم استلام طلبك بنجاح! سيتواصل معك فريق ابو طلال قريباً."
                )
            }
            delay(2000)
            _uiState.update {
                it.copy(
                    showBookingDialog = false,
                    bookingSuccessMessage = null
                )
            }
        }
    }

    fun openNotificationsDialog() {
        _uiState.update {
            it.copy(
                showNotificationsDialog = true,
                unreadNotificationsCount = 0
            )
        }
    }

    fun closeNotificationsDialog() {
        _uiState.update { it.copy(showNotificationsDialog = false) }
    }

    fun openProfileDialog() {
        _uiState.update { it.copy(showProfileDialog = true) }
    }

    fun closeProfileDialog() {
        _uiState.update { it.copy(showProfileDialog = false) }
    }

    fun openAboutDialog() {
        _uiState.update { it.copy(showAboutDialog = true) }
    }

    fun closeAboutDialog() {
        _uiState.update { it.copy(showAboutDialog = false) }
    }

    fun openContactDialog() {
        _uiState.update { it.copy(showContactDialog = true) }
    }

    fun closeContactDialog() {
        _uiState.update { it.copy(showContactDialog = false) }
    }

    fun openDrawerMenu() {
        _uiState.update { it.copy(showDrawerMenu = true) }
    }

    fun closeDrawerMenu() {
        _uiState.update { it.copy(showDrawerMenu = false) }
    }

    fun loginUser(username: String) {
        _uiState.update {
            it.copy(
                isLoggedIn = true,
                userFullName = username.ifBlank { "مستخدم ابو طلال" },
                currentScreen = Screen.HOME
            )
        }
    }

    fun registerUser(fullName: String, phone: String, email: String) {
        _uiState.update {
            it.copy(
                isLoggedIn = true,
                userFullName = fullName.ifBlank { "مستخدم ابو طلال" },
                userPhone = phone.ifBlank { "778215553" },
                userEmail = email.ifBlank { "client@abotalalpro.com" },
                currentScreen = Screen.HOME
            )
        }
    }

    fun sendChatMessage(userText: String) {
        if (userText.isBlank()) return
        val timeFormat = SimpleDateFormat("h:mm a", Locale("ar"))
        val currentTime = timeFormat.format(Date())

        val userMessage = ChatMessage(text = userText, isUser = true, time = currentTime)
        _uiState.update {
            it.copy(
                chatMessages = it.chatMessages + userMessage,
                isAssistantTyping = true
            )
        }

        viewModelScope.launch {
            delay(1000)
            val replyText = generateBotReply(userText)
            val botMessage = ChatMessage(text = replyText, isUser = false, time = timeFormat.format(Date()))
            _uiState.update {
                it.copy(
                    chatMessages = it.chatMessages + botMessage,
                    isAssistantTyping = false
                )
            }
        }
    }

    private fun generateBotReply(input: String): String {
        val lower = input.lowercase(Locale.ROOT)
        return when {
            lower.contains("برمج") || lower.contains("تطبيق") || lower.contains("موقع") ->
                "يسعدنا خدمتك في قسم البرمجة والتطوير! نقوم بتطوير تطبيقات أندرويد وiOS ومواقع متجاوبة بأعلى معايير الجودة. يمكنك حجز استشارة برمجية الآن عبر زر الحجز."
            lower.contains("سعر") || lower.contains("باقة") || lower.contains("تكلف") ->
                "أسعارنا تنافسية وتعتمد على حجم المشروع والميزات المطلوبة. يمكنك مراجعة قسم الباقات أو الضغط على زر الاتصال (778215553) للحصول على عرض سعر فوري!"
            lower.contains("هاتف") || lower.contains("سوفت") || lower.contains("شفر") || lower.contains("frp") ->
                "نقدم أسرع خدمات فك شفرات الهواتف وتخطي الحسابات وصيانة السوفت وير بأحدث البوكسات والأدوات الرسمية."
            lower.contains("تسويق") || lower.contains("اعلان") || lower.contains("حمل") ->
                "فريق التسويق في ابو طلال يدير حملات ممولة احترافية على منصات ميتا وتيك توك وجوجل لتحقيق أعلى عائد على الاستثمار."
            lower.contains("حجز") || lower.contains("طلب") ->
                "يمكنك حجز خدمتك مباشرة بالضغط على زر (+) في أسفل الشاشة أو التواصل معنا عبر واتساب على الرقم 778215553."
            else ->
                "شكراً لتواصلك معنا! في مركز ابو طلال للخدمات الرقمية والبرمجة والتسويق نسعى دائماً لتقديم الأفضل. هل ترغب بالتحدث مع مسؤول الدعم الفني مباشرة؟"
        }
    }
}
