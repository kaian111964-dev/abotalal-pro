package com.example.data

import com.example.R
import com.example.model.AppNotification
import com.example.model.ChatMessage
import com.example.model.PortfolioProject
import com.example.model.ServiceItem
import com.example.model.SliderItem

object AppDataSource {

    const val PHONE_NUMBER = "778215553"
    const val INTERNATIONAL_PHONE = "+967778215553"
    const val WHATSAPP_URL = "https://wa.me/967778215553"

    val services: List<ServiceItem> = listOf(
        // Row 1
        ServiceItem(
            id = "search",
            title = "البحوثات",
            iconRes = R.drawable.ic_svc_search,
            cardImageRes = R.drawable.card_search,
            subtitle = "إعداد البحوث العلمية والتقنية",
            description = "نقدم خدمة إعداد البحوث الأكاديمية والتقنية الموثقة بأعلى معايير الدقة العلمية مع التنسيق والتدقيق اللغوي.",
            features = listOf(
                "بحوث تخرج ورسائل ماجستير",
                "أوراق عمل ودراسات جدوى",
                "ترجمة متخصصة وتدقيق لغوي",
                "إحصاء وتحليل بيانات SPSS"
            )
        ),
        ServiceItem(
            id = "variety",
            title = "متنوعة",
            iconRes = R.drawable.ic_svc_variety,
            cardImageRes = R.drawable.card_variety,
            subtitle = "خدمات رقمية وحلول عامة",
            description = "باقة شاملة من الخدمات الرقمية المتنوعة التي تلبي مختلف احتياجاتك الشخصية والمهنية بكفاءة عالية.",
            features = listOf(
                "معاملات إلكترونية وتسجيل",
                "استشارات تقنية وإدارية",
                "طباعة وتنسيق مستندات",
                "تصميم سيرة ذاتية احترافية"
            )
        ),
        ServiceItem(
            id = "ads",
            title = "اعلانات",
            iconRes = R.drawable.ic_svc_ads,
            cardImageRes = R.drawable.card_ads,
            subtitle = "حملات تسويقية وإعلانية ممولة",
            description = "إدارة الحملات الإعلانية الممولة على منصات التواصل الاجتماعي ومحركات البحث للوصول لأكبر شريحة مستهدفة.",
            features = listOf(
                "إعلانات فيسبوك وإنستغرام",
                "إعلانات تيك توك وسناب شات",
                "إعلانات جوجل ومحركات البحث",
                "استراتيجيات تسويق بالمحتوى"
            )
        ),
        ServiceItem(
            id = "clients",
            title = "عملائنا",
            iconRes = R.drawable.ic_svc_clients,
            cardImageRes = R.drawable.card_clients,
            subtitle = "شركاء النجاح وقصص التميز",
            description = "نفخر بثقة مئات العملاء والشركات التي ساهمنا في بناء هويتها الرقمية وتطوير منظوماتها البرمجية.",
            features = listOf(
                "أكثر من 500+ عميل سعيد",
                "تقييم رضا 99.4%",
                "دعم فني مستمر لما بعد البيع",
                "شراكات استراتيجية طويلة الأمد"
            )
        ),

        // Row 2
        ServiceItem(
            id = "education",
            title = "التعليم",
            iconRes = R.drawable.ic_svc_education,
            cardImageRes = R.drawable.card_education,
            subtitle = "دورات تدريبية ومنصات تعليمية",
            description = "برامج تعليمية ودورات تدريبية متخصصة في البرمجة، التسويق الإلكتروني، وأمن المعلومات بإشراف خبراء.",
            features = listOf(
                "دورات برمجة وتطوير تطبيقات",
                "كورسات ذكاء اصطناعي وتحليل بيانات",
                "شهادات إتمام معتمدة",
                "مشاريع تطبيقية عملية"
            )
        ),
        ServiceItem(
            id = "wifi",
            title = "شبكات Wifi",
            iconRes = R.drawable.ic_svc_wifi,
            cardImageRes = R.drawable.card_wifi,
            subtitle = "أنظمة شبكات وتوزيع إنترنت",
            description = "تصميم وإدارة شبكات المايكروتك وأنظمة توزيع الوايفاي للمنازل والشركات والكافيهات بحماية متقدمة.",
            features = listOf(
                "سيرفرات مايكروتك وكروت شحن",
                "تغطية لاسلكية واسعة المدى",
                "حماية وتأمين الشبكات من الاختراق",
                "دمج سرعات وتحديد الباندويث"
            )
        ),
        ServiceItem(
            id = "phone_dev",
            title = "خدمات وبرمجة الهاتف",
            iconRes = R.drawable.ic_svc_phone_dev,
            cardImageRes = R.drawable.card_phone_dev,
            subtitle = "سوفت وير وصيانة هواتف ذكية",
            description = "حلول متكاملة لبرمجة الهواتف الذكية وتخطي الحسابات وتركيب الرومات الرسمية والمعدلة بأمان.",
            features = listOf(
                "تخطي حسابات جوجل FRP و Xiaomi",
                "تعريب وتحديث أنظمة التشغيل",
                "فك شفرات الشبكات لجميع الأجهزة",
                "إصلاح مشاكل الإقلاع واستعادة البيانات"
            )
        ),
        ServiceItem(
            id = "security",
            title = "الحماية",
            iconRes = R.drawable.ic_svc_security,
            cardImageRes = R.drawable.card_security,
            subtitle = "الأمن السيبراني وحماية الحسابات",
            description = "تأمين وحماية الحسابات الشخصية والتجارية من الاختراق واسترجاع الحسابات المعطلة أو المخترقة.",
            features = listOf(
                "فحص أمان الحسابات وتفعيل 2FA",
                "استرجاع حسابات فيسبوك وإنستغرام",
                "حماية المواقع وتشفير البيانات SSL",
                "مكافحة الفيروسات وبرمجيات التجسس"
            )
        ),

        // Row 3
        ServiceItem(
            id = "packages",
            title = "الباقات والاشتراكات",
            iconRes = R.drawable.ic_svc_packages,
            cardImageRes = R.drawable.card_packages,
            subtitle = "اشتراكات رقمية بأسعار مميزة",
            description = "تفعيل أرقى الاشتراكات الرقمية العالمية الرسمية، السيرفرات السحابية، وخدمات البث والألعاب.",
            features = listOf(
                "اشتراكات برامج التصميم والذكاء الاصطناعي",
                "اشتراكات سيرفرات IPTV و VPN",
                "شحن ألعاب وبطاقات رقمية",
                "باقات دعم فني شهرية وسنوية"
            )
        ),
        ServiceItem(
            id = "store",
            title = "متجر التطبيقات",
            iconRes = R.drawable.ic_svc_store,
            cardImageRes = R.drawable.card_store,
            subtitle = "تطبيقات حصرية وأدوات مميزة",
            description = "مجموعة منتقاة من أفضل التطبيقات والأدوات البرمجية الجاهزة للاستخدام الفوري لخدمة عملك.",
            features = listOf(
                "تطبيقات إدارة الحسابات والمبيعات",
                "أدوات التسويق عبر واتساب وتيليجرام",
                "قوالب وتطبيقات جاهزة",
                "تحديثات مستمرة وترقيات مجانية"
            )
        ),
        ServiceItem(
            id = "tech_info",
            title = "معلومات تقنية",
            iconRes = R.drawable.ic_svc_tech_info,
            cardImageRes = R.drawable.card_tech_info,
            subtitle = "أحدث أخبار التقنية والنصائح",
            description = "دليل معرفي شامل يغطي أحدث التطورات التقنية والشروحات المفيدة لحماية وتطوير أجهزتك.",
            features = listOf(
                "شروحات تقنية مبسطة خطوة بخطوة",
                "مقارنات الأجهزة والأنظمة",
                "حلول للمشاكل الشائعة",
                "نصائح أمنية وإرشادات وقائية"
            )
        ),
        ServiceItem(
            id = "dev",
            title = "البرمجة والتطوير",
            iconRes = R.drawable.ic_svc_dev,
            cardImageRes = R.drawable.card_dev,
            subtitle = "تطوير تطبيقات ومواقع احترافية",
            description = "بناء تطبيقات الموبايل (Android / iOS) والمواقع الإلكترونية والأنظمة السحابية باستخدام أحدث التقنيات.",
            features = listOf(
                "تطبيقات موبايل سريعة وحديثة (Jetpack Compose / Flutter)",
                "مواقع ومتاجر إلكترونية متجاوبة",
                "لوحات تحكم متقدمة لإدارة الأعمال",
                "ربط واجهات برمجية API وقواعد بيانات"
            )
        ),

        // Row 4
        ServiceItem(
            id = "portfolio",
            title = "معرض أعمالنا",
            iconRes = R.drawable.ic_svc_portfolio,
            cardImageRes = R.drawable.card_portfolio,
            subtitle = "نماذج من مشاريعنا المنفذة",
            description = "استكشف نخبة من أعمالنا في تصميم الهويات البصرية، برمجة التطبيقات، والمتاجر الرقمية المتألقة.",
            features = listOf(
                "معارض تصاميم الجرافيك والهويات",
                "تطبيقات موبايل منشورة على المتاجر",
                "مواقع إلكترونية حية وناجحة",
                "حملات إعلانية حققت ملايين المشاهدات"
            )
        ),
        ServiceItem(
            id = "ai_tools",
            title = "أدوات الذكاء الاصطناعي",
            iconRes = R.drawable.ic_svc_ai_tools,
            cardImageRes = R.drawable.card_ai_tools,
            subtitle = "أحدث حلول الذكاء الاصطناعي التوليدي",
            description = "أدوات متطورة لتوليد المحتوى، الأتمتة الذكية، ومعالجة الصور والبيانات لرفع كفاءة أعمالك.",
            features = listOf(
                "توليد نصوص وإعلانات إبداعية",
                "معالجة وتحسين جودة الصور بالذكاء الاصطناعي",
                "روبوتات محادثة وخدمة عملاء ذكية",
                "تحليل المشاعر والبيانات الضخمة"
            )
        ),
        ServiceItem(
            id = "services",
            title = "خدماتنا",
            iconRes = R.drawable.ic_svc_services,
            cardImageRes = R.drawable.card_services,
            subtitle = "نظرة شاملة لكافة حلول ابو طلال",
            description = "مركزك المتكامل للحلول الرقمية: تصميم، تسويق، برمجة، وصيانة تحت مظلة واحدة متكاملة.",
            features = listOf(
                "حلول أعمال رقمية متكاملة 360 درجة",
                "فريق عمل محترف ومتعدد المهارات",
                "سرعة في التنفيذ والتسليم بالموعد",
                "ضمان جودة الأداء ودعم فني"
            )
        ),
        ServiceItem(
            id = "assistant",
            title = "المساعد الذكي",
            iconRes = R.drawable.ic_svc_assistant,
            cardImageRes = R.drawable.card_assistant,
            subtitle = "مساعدك الشخصي للرد الفوري",
            description = "تحدث مباشرة مع المساعد الذكي للحصول على إجابات فورية، مساعدة في اختيار الخدمات، وحجز المواعيد.",
            features = listOf(
                "إجابات فورية على مدار 24 ساعة",
                "مساعدة في تحديد باقة الخدمة المناسبة",
                "حجز الاستشارات ومتابعة الطلبات",
                "توجيه مباشر لقسم الدعم المختص"
            )
        )
    )

    val sliderItems: List<SliderItem> = listOf(
        SliderItem(
            id = 1,
            title = "مستقبلك الرقمي بين يديك",
            subtitle = "حلول ذكية .. خدمات متكاملة",
            ctaText = "اكتشف المزيد",
            bannerRes = R.drawable.hero_banner
        ),
        SliderItem(
            id = 2,
            title = "برمجة وتطوير الأنظمة",
            subtitle = "تطبيقات حديثة ومواقع احترافية متجاوبة",
            ctaText = "اطلب الآن",
            bannerRes = R.drawable.hero_banner
        ),
        SliderItem(
            id = 3,
            title = "حملات تسويقية متكاملة",
            subtitle = "نصل بمنتجك إلى آلاف العملاء المستهدفين",
            ctaText = "ابدأ حملتك",
            bannerRes = R.drawable.hero_banner
        ),
        SliderItem(
            id = 4,
            title = "دعم فني وصيانة فورية",
            subtitle = "خدمات هواتف وشبكات بأعلى سرعة وأمان",
            ctaText = "تواصل معنا",
            bannerRes = R.drawable.hero_banner
        )
    )

    val initialNotifications: List<AppNotification> = listOf(
        AppNotification(
            id = 1,
            title = "أهلاً بك في تطبيق ابو طلال",
            body = "يسعدنا انضمامك! استمتع بأقوى العروض على خدمات البرمجة والتسويق الرقمي.",
            timestamp = "منذ قليل"
        ),
        AppNotification(
            id = 2,
            title = "تحديث باقات الذكاء الاصطناعي",
            body = "تمت إضافة أدوات توليد الصور والمحتوى الآلي بأسعار مخفضة.",
            timestamp = "منذ ساعتين"
        ),
        AppNotification(
            id = 3,
            title = "خصم 20% على خدمات برمجة الهواتف",
            body = "لفترة محدودة، استفد من فحص وبرمجة الهواتف وفك الشفرات بخصم خاص.",
            timestamp = "منذ يوم"
        )
    )

    val initialChatMessages: List<ChatMessage> = listOf(
        ChatMessage(
            text = "مرحباً بك في تطبيق ابو طلال! أنا المساعد الذكي الخاص بك، كيف يمكنني مساعدتك اليوم؟",
            isUser = false,
            time = "10:00 ص"
        ),
        ChatMessage(
            text = "يمكنك سؤالي عن خدمات البرمجة، التسويق الإلكتروني، باقات الاشتراكات، أو طلب حجز خدمة مباشرة.",
            isUser = false,
            time = "10:01 ص"
        )
    )

    val portfolioProjects: List<PortfolioProject> = listOf(
        // 1. Websites (Top right)
        PortfolioProject(
            id = "web",
            category = "المواقع",
            badge = "مواقع",
            title = "مواقع ويب عصرية",
            subtitle = "تصميم وتطوير مواقع احترافية",
            description = "تصميم وتطوير مواقع إلكترونية ومتاجر سحابية عصرية متجاوبة مع كافة الشاشات، مدعومة بأحدث لوحات التحكم وتقنيات تحسين محركات البحث والأمان العالي.",
            imageRes = R.drawable.portfolio_card_web,
            technologies = listOf("React", "Next.js", "Tailwind CSS", "Node.js", "PostgreSQL"),
            features = listOf(
                "تصميم فخم ومتجاوب 100% مع الهواتف والشاشات",
                "سرعة تحميل فائقة وتوافق مع محركات البحث SEO",
                "لوحة تحكم إدارية باللغة العربية لإدارة المحتوى والطلبات",
                "بوابات دفع إلكترونية آمنة وشهادات تشفير SSL مجانية"
            )
        ),
        // 2. Mobile Applications (Top left)
        PortfolioProject(
            id = "apps",
            category = "التطبيقات",
            badge = "تطبيقات",
            title = "تطبيقات موبايل احترافية",
            subtitle = "تصميم وتطوير تطبيقات أندرويد وiOS",
            description = "بناء تطبيقات الهواتف الذكية بنظامي Android و iOS باستخدام أحدث تقنيات Jetpack Compose وتصميم واجهات M3 مع تجربة مستخدم سلسة وأداء فائق السرعة.",
            imageRes = R.drawable.portfolio_card_apps,
            technologies = listOf("Kotlin", "Jetpack Compose", "Flutter", "Firebase", "REST APIs"),
            features = listOf(
                "واجهات عربية أنيقة مع دعم الوضع الليلي Dark Mode",
                "إشعارات فورية تفاعلية ونظام مصادقة متقدم",
                "عمل في وضع عدم الاتصال Offline مع مزامنة سحابية",
                "نشر وتجهيز التطبيقات على Google Play و App Store"
            )
        ),
        // 3. Artificial Intelligence (Middle right)
        PortfolioProject(
            id = "ai",
            category = "الذكاء الاصطناعي",
            badge = "أدوات الذكاء",
            title = "أدوات الذكاء الاصطناعي",
            subtitle = "أدوات وتقنيات الذكاء الاصطناعي",
            description = "دمج وتطوير أحدث حلول الذكاء الاصطناعي التوليدي، معالجة اللغات الطبيعية، توليد الصور، والمساعدين الرقميين الذكيين لأتمتة أعمالك ورفع الكفاءة.",
            imageRes = R.drawable.portfolio_card_ai,
            technologies = listOf("Gemini AI", "OpenAI", "Python", "LangChain", "Vector DB"),
            features = listOf(
                "شات بوت ذكي للرد الفوري على العملاء على مدار الساعة",
                "توليد المحتوى الإعلاني والتسويقي التلقائي",
                "تحليل البيانات واستخراج الرؤى الذكية والتنبؤات",
                "أدوات معالجة الصور والتصميم بالذكاء الاصطناعي"
            )
        ),
        // 4. Creative Designs (Middle left)
        PortfolioProject(
            id = "designs",
            category = "التصاميم",
            badge = "تصميمات",
            title = "تصميمات إبداعية",
            subtitle = "شعارات وبوسترات وهوية بصرية",
            description = "ابتكار هويات بصرية كاملة، شعارات احترافية فريدة، وبوسترات إعلانية تسويقية جذابة تعكس تميز علامتك التجارية وتترك انطباعاً دائماً لدى عملائك.",
            imageRes = R.drawable.portfolio_card_designs,
            technologies = listOf("Adobe Illustrator", "Photoshop", "Figma", "After Effects"),
            features = listOf(
                "تصميم شعارات وهوية تجارية متكاملة بدليل استخدام",
                "بوسترات إعلانية لمنصات التواصل الاجتماعي",
                "تصميم واجهات المستخدم UI/UX للهواتف والمواقع",
                "ملفات فيكتور عالية الدقة جاهزة للطباعة والنشر"
            )
        ),
        // 5. Tech Services (Bottom right)
        PortfolioProject(
            id = "tech",
            category = "خدمات تقنية",
            badge = "خدمات تقنية",
            title = "خدمات تقنية متنوعة",
            subtitle = "خدمات تقنية وحلول متكاملة",
            description = "حلول تقنية شاملة تشمل إعداد الخوادم السحابية، حماية الشبكات من الاختراق، سيرفرات المايكروتك، وحلول الدعم الفني المستمر للمؤسسات والأفراد.",
            imageRes = R.drawable.portfolio_card_tech,
            technologies = listOf("Mikrotik", "Linux Servers", "Cloudflare", "Docker", "CyberSecurity"),
            features = listOf(
                "إعداد شبكات وتوزيع إنترنت بكروت شحن وأنظمة حماية",
                "استضافة خوادم سحابية وحلول نسخ احتياطي تلقائي",
                "فحص أمان الأنظمة وإصلاح الثغرات وتأمين الحسابات",
                "دعم فني وصيانة برمجية على مدار 24/7"
            )
        ),
        // 6. Systems & Programming (Bottom left)
        PortfolioProject(
            id = "code",
            category = "البرمجة",
            badge = "برمجة",
            title = "برمجة وتطوير الأنظمة",
            subtitle = "حلول برمجية مخصصة واحترافية",
            description = "تطوير أنظمة سحابية مخصصة لإدارة الأعمال، نقاط البيع (POS)، برامج المحاسبة والمخازن، وإدارة الموارد المؤسسية (ERP) المبرمجة بدقة تلبي كافة احتياجاتك.",
            imageRes = R.drawable.portfolio_card_code,
            technologies = listOf("Kotlin", "Spring Boot", "PostgreSQL", "Vue.js", "Docker"),
            features = listOf(
                "أنظمة إدارة مبيعات وفواتير متوافقة مع المتطلبات الرسمية",
                "إدارة المخزون وتتبع المنتجات والمستودعات",
                "صلاحيات مستخدمين متعددة المستويات وتقارير مالية تفصيلية",
                "ربط مباشر مع تطبيقات التوصيل والخدمات الخارجية"
            )
        )
    )
}
