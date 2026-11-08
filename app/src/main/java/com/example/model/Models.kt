package com.example.model

import androidx.annotation.DrawableRes
import com.example.R

data class ServiceItem(
    val id: String,
    val title: String,
    @DrawableRes val iconRes: Int,
    @DrawableRes val cardImageRes: Int = iconRes,
    val subtitle: String,
    val description: String,
    val features: List<String>,
    val priceStartingFrom: String = "حسب الطلب"
)

data class SliderItem(
    val id: Int,
    val title: String,
    val subtitle: String,
    val ctaText: String,
    @DrawableRes val bannerRes: Int
)

data class AppNotification(
    val id: Int,
    val title: String,
    val body: String,
    val timestamp: String,
    val isRead: Boolean = false
)

data class ChatMessage(
    val text: String,
    val isUser: Boolean,
    val time: String
)

enum class Screen {
    SPLASH,
    LOGIN,
    REGISTER,
    HOME,
    PORTFOLIO
}

data class PortfolioProject(
    val id: String,
    val category: String,
    val badge: String,
    val title: String,
    val subtitle: String,
    val description: String,
    @DrawableRes val imageRes: Int,
    val technologies: List<String>,
    val features: List<String>
)

enum class BottomTab {
    HOME,
    ABOUT,
    BOOKING,
    CONTACT,
    ASSISTANT
}
