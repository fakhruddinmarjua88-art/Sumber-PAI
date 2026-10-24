package com.example.model

enum class EducationLevel(val label: String, val badge: String) {
  SD("Tingkat SD", "Kelas 1-6"),
  SMP("Tingkat SMP", "Kelas 7-9"),
  SMA("Tingkat SMA", "Kelas 10-12")
}

data class BadgeItem(
  val id: String,
  val title: String,
  val description: String,
  val iconEmoji: String,
  val isUnlocked: Boolean,
  val unlockedDate: String = ""
)

data class UserProgress(
  val currentLevel: Int = 3,
  val levelTitle: String = "Penuntut Ilmu Gigih",
  val xp: Int = 360,
  val xpForNextLevel: Int = 500,
  val streakDays: Int = 5,
  val points: Int = 850,
  val completedStoriesCount: Int = 2,
  val completedQuizzesCount: Int = 4,
  val badges: List<BadgeItem> = listOf(
    BadgeItem("b1", "Bintang Wudhu", "Menyelesaikan simulasi urutan wudhu sempurna", "💧", true, "Kemarin"),
    BadgeItem("b2", "Penjelajah Sirah", "Membaca kisah Nabi Nuh dan Nabi Ibrahim", "📜", true, "Hari ini"),
    BadgeItem("b3", "Sahabat Tajwid", "Kuasai 5 hukum Nun Mati & Tanwin", "✨", true, "2 hari lalu"),
    BadgeItem("b4", "Pejuang Subuh", "Menyelesaikan amalan subuh berturut-turut", "🌅", false),
    BadgeItem("b5", "Hafiz Cilik", "Melafalkan 5 surah pendek dengan tartil", "📖", false)
  )
)

data class StorySlide(
  val page: Int,
  val title: String,
  val text: String,
  val audioText: String
)

data class Story(
  val id: String,
  val title: String,
  val prophet: String,
  val summary: String,
  val targetLevel: EducationLevel,
  val readingMinutes: Int,
  val slides: List<StorySlide>,
  val reflectionQuestion: String,
  val reflectionOptions: List<String>,
  val correctOptionIndex: Int,
  val hikmah: String
)

data class QuizQuestion(
  val id: String,
  val question: String,
  val options: List<String>,
  val correctIndex: Int,
  val explanation: String,
  val category: String,
  val xpReward: Int = 25
)

data class WuduStep(
  val id: Int,
  val title: String,
  val arabicTitle: String,
  val description: String,
  val correctOrder: Int
)

data class TajwidCard(
  val id: String,
  val hukum: String,
  val arti: String,
  val huruf: String,
  val contohArab: String,
  val caraBaca: String,
  val penjelasan: String
)

data class DailyAmalan(
  val id: String,
  val title: String,
  val description: String,
  val isCompleted: Boolean,
  val xpReward: Int,
  val category: String,
  val iconEmoji: String
)

data class VerseItem(
  val number: Int,
  val arabic: String,
  val transliteration: String,
  val translation: String
)

data class SurahAudio(
  val id: String,
  val title: String,
  val arabicTitle: String,
  val meaning: String,
  val versesCount: Int,
  val category: String,
  val verses: List<VerseItem>
)

data class ChatMessage(
  val id: String,
  val text: String,
  val isUser: Boolean,
  val timestamp: String
)
