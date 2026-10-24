package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.PaiRepository
import com.example.model.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class PaiScreen {
  object Dashboard : PaiScreen()
  object StoriesList : PaiScreen()
  data class StoryDetail(val story: Story) : PaiScreen()
  object MiniGamesHub : PaiScreen()
  object QuizPlay : PaiScreen()
  object WuduGame : PaiScreen()
  object TajwidFlashcards : PaiScreen()
  object AmalanTracker : PaiScreen()
  object AudioMurottal : PaiScreen()
  object AiMentor : PaiScreen()
}

class PaiViewModel : ViewModel() {

  private val _screenStack = MutableStateFlow<List<PaiScreen>>(listOf(PaiScreen.Dashboard))
  val currentScreen = MutableStateFlow<PaiScreen>(PaiScreen.Dashboard)

  private val _userProgress = MutableStateFlow(UserProgress())
  val userProgress: StateFlow<UserProgress> = _userProgress.asStateFlow()

  private val _selectedGrade = MutableStateFlow(EducationLevel.SD)
  val selectedGrade: StateFlow<EducationLevel> = _selectedGrade.asStateFlow()

  // Amalan
  private val _amalanList = MutableStateFlow(PaiRepository.initialAmalanList)
  val amalanList: StateFlow<List<DailyAmalan>> = _amalanList.asStateFlow()

  // Stories
  val stories = PaiRepository.stories
  private val _activeStory = MutableStateFlow<Story?>(null)
  val activeStory: StateFlow<Story?> = _activeStory.asStateFlow()
  private val _activeSlidePage = MutableStateFlow(1)
  val activeSlidePage: StateFlow<Int> = _activeSlidePage.asStateFlow()
  private val _selectedReflectionAnswer = MutableStateFlow<Int?>(null)
  val selectedReflectionAnswer: StateFlow<Int?> = _selectedReflectionAnswer.asStateFlow()
  private val _reflectionSubmitted = MutableStateFlow(false)
  val reflectionSubmitted: StateFlow<Boolean> = _reflectionSubmitted.asStateFlow()

  // Quiz
  val quizQuestions = PaiRepository.quizList
  private val _currentQuizIndex = MutableStateFlow(0)
  val currentQuizIndex: StateFlow<Int> = _currentQuizIndex.asStateFlow()
  private val _selectedQuizOption = MutableStateFlow<Int?>(null)
  val selectedQuizOption: StateFlow<Int?> = _selectedQuizOption.asStateFlow()
  private val _isQuizAnswerSubmitted = MutableStateFlow(false)
  val isQuizAnswerSubmitted: StateFlow<Boolean> = _isQuizAnswerSubmitted.asStateFlow()
  private val _quizScore = MutableStateFlow(0)
  val quizScore: StateFlow<Int> = _quizScore.asStateFlow()
  private val _isQuizFinished = MutableStateFlow(false)
  val isQuizFinished: StateFlow<Boolean> = _isQuizFinished.asStateFlow()

  // Wudu Game
  private val _wuduUserOrder = MutableStateFlow<List<WuduStep>>(emptyList())
  val wuduUserOrder: StateFlow<List<WuduStep>> = _wuduUserOrder.asStateFlow()
  private val _wuduPool = MutableStateFlow<List<WuduStep>>(emptyList())
  val wuduPool: StateFlow<List<WuduStep>> = _wuduPool.asStateFlow()
  private val _wuduGameResult = MutableStateFlow<Boolean?>(null)
  val wuduGameResult: StateFlow<Boolean?> = _wuduGameResult.asStateFlow()

  // Tajwid Flashcards
  val tajwidCards = PaiRepository.tajwidFlashcards
  private val _tajwidIndex = MutableStateFlow(0)
  val tajwidIndex: StateFlow<Int> = _tajwidIndex.asStateFlow()
  private val _isCardFlipped = MutableStateFlow(false)
  val isCardFlipped: StateFlow<Boolean> = _isCardFlipped.asStateFlow()
  private val _masteredCards = MutableStateFlow<Set<String>>(emptySet())
  val masteredCards: StateFlow<Set<String>> = _masteredCards.asStateFlow()

  // Audio / Murottal
  val surahs = PaiRepository.surahs
  private val _activeSurah = MutableStateFlow(PaiRepository.surahs.first())
  val activeSurah: StateFlow<SurahAudio> = _activeSurah.asStateFlow()
  private val _isPlayingAudio = MutableStateFlow(false)
  val isPlayingAudio: StateFlow<Boolean> = _isPlayingAudio.asStateFlow()
  private val _playbackSpeed = MutableStateFlow("1.0x")
  val playbackSpeed: StateFlow<String> = _playbackSpeed.asStateFlow()
  private val _activeAyahIndex = MutableStateFlow(0)
  val activeAyahIndex: StateFlow<Int> = _activeAyahIndex.asStateFlow()
  private val _recitationFeedback = MutableStateFlow<String?>(null)
  val recitationFeedback: StateFlow<String?> = _recitationFeedback.asStateFlow()

  // AI Mentor
  private val _chatMessages = MutableStateFlow(
    listOf(
      ChatMessage(
        id = "c1",
        text = "Assalamu'alaikum sahabat pintar! Aku Kak Farhan, asisten belajarmu di Sahabat PAI. Kamu mau tanya apa hari ini seputar pelajaran agama, kisah nabi, tajwid, atau tata cara ibadah?",
        isUser = false,
        timestamp = "Sekarang"
      )
    )
  )
  val chatMessages: StateFlow<List<ChatMessage>> = _chatMessages.asStateFlow()
  private val _isAiThinking = MutableStateFlow(false)
  val isAiThinking: StateFlow<Boolean> = _isAiThinking.asStateFlow()

  // Toast / Celebration feedback banner
  private val _celebrationNotice = MutableStateFlow<String?>(null)
  val celebrationNotice: StateFlow<String?> = _celebrationNotice.asStateFlow()

  init {
    initWuduGame()
  }

  fun navigateTo(screen: PaiScreen) {
    val current = _screenStack.value.toMutableList()
    current.add(screen)
    _screenStack.value = current
    this.currentScreen.value = screen
  }

  fun navigateBack(): Boolean {
    val current = _screenStack.value.toMutableList()
    if (current.size > 1) {
      current.removeAt(current.size - 1)
      _screenStack.value = current
      this.currentScreen.value = current.last()
      return true
    }
    return false
  }

  fun selectGrade(grade: EducationLevel) {
    _selectedGrade.value = grade
    showNotice("Tingkat pembelajaran diubah ke ${grade.label} (${grade.badge})")
  }

  fun addXpAndPoints(xp: Int, points: Int, notice: String) {
    val current = _userProgress.value
    val newXp = current.xp + xp
    val newPoints = current.points + points
    val newLevel = if (newXp >= current.xpForNextLevel) current.currentLevel + 1 else current.currentLevel
    val newXpTarget = if (newXp >= current.xpForNextLevel) current.xpForNextLevel + 250 else current.xpForNextLevel
    val levelTitle = when (newLevel) {
      1 -> "Pemula Shalih"
      2 -> "Bintang Pembelajar"
      3 -> "Penuntut Ilmu Gigih"
      4 -> "Duta Kebaikan"
      else -> "Cendekiawan Muslim Cilik"
    }

    _userProgress.value = current.copy(
      currentLevel = newLevel,
      levelTitle = levelTitle,
      xp = if (newXp >= current.xpForNextLevel) newXp - current.xpForNextLevel else newXp,
      xpForNextLevel = newXpTarget,
      points = newPoints
    )
    showNotice(notice)
  }

  fun showNotice(msg: String) {
    _celebrationNotice.value = msg
    viewModelScope.launch {
      delay(3000)
      if (_celebrationNotice.value == msg) {
        _celebrationNotice.value = null
      }
    }
  }

  // --- Amalan Tracker ---
  fun toggleAmalan(id: String) {
    val list = _amalanList.value.map { item ->
      if (item.id == id) {
        val newStatus = !item.isCompleted
        if (newStatus) {
          addXpAndPoints(item.xpReward, 10, "+${item.xpReward} XP: ${item.title} selesai! Masya Allah ✨")
        }
        item.copy(isCompleted = newStatus)
      } else {
        item
      }
    }
    _amalanList.value = list
  }

  // --- Stories ---
  fun openStory(story: Story) {
    _activeStory.value = story
    _activeSlidePage.value = 1
    _selectedReflectionAnswer.value = null
    _reflectionSubmitted.value = false
    navigateTo(PaiScreen.StoryDetail(story))
  }

  fun nextSlide(total: Int) {
    if (_activeSlidePage.value < total) {
      _activeSlidePage.value += 1
    }
  }

  fun prevSlide() {
    if (_activeSlidePage.value > 1) {
      _activeSlidePage.value -= 1
    }
  }

  fun submitReflection(index: Int, story: Story) {
    _selectedReflectionAnswer.value = index
    _reflectionSubmitted.value = true
    if (index == story.correctOptionIndex) {
      addXpAndPoints(50, 30, "+50 XP: Jawaban Refleksi Tepat! Mumtaz! 🌟")
    } else {
      showNotice("Refleksi tersimpan. Teruslah merenungkan hikmah mulia ini.")
    }
  }

  // --- Quiz ---
  fun selectQuizOption(index: Int) {
    if (!_isQuizAnswerSubmitted.value) {
      _selectedQuizOption.value = index
    }
  }

  fun submitQuizAnswer() {
    val selected = _selectedQuizOption.value ?: return
    _isQuizAnswerSubmitted.value = true
    val currentQ = quizQuestions[_currentQuizIndex.value]
    if (selected == currentQ.correctIndex) {
      _quizScore.value += 1
      addXpAndPoints(currentQ.xpReward, 15, "Jawaban Benar! +${currentQ.xpReward} XP 🎉")
    }
  }

  fun nextQuizQuestion() {
    if (_currentQuizIndex.value < quizQuestions.size - 1) {
      _currentQuizIndex.value += 1
      _selectedQuizOption.value = null
      _isQuizAnswerSubmitted.value = false
    } else {
      _isQuizFinished.value = true
      addXpAndPoints(50, 50, "Kuis Selesai! Kamu Hebat! 🏆")
    }
  }

  fun restartQuiz() {
    _currentQuizIndex.value = 0
    _selectedQuizOption.value = null
    _isQuizAnswerSubmitted.value = false
    _quizScore.value = 0
    _isQuizFinished.value = false
  }

  // --- Wudu Game ---
  fun initWuduGame() {
    val shuffled = PaiRepository.wuduStepsMaster.shuffled()
    _wuduPool.value = shuffled
    _wuduUserOrder.value = emptyList()
    _wuduGameResult.value = null
  }

  fun addStepToUserOrder(step: WuduStep) {
    val pool = _wuduPool.value.filter { it.id != step.id }
    val userOrder = _wuduUserOrder.value + step
    _wuduPool.value = pool
    _wuduUserOrder.value = userOrder
    _wuduGameResult.value = null
  }

  fun removeStepFromUserOrder(step: WuduStep) {
    val userOrder = _wuduUserOrder.value.filter { it.id != step.id }
    val pool = _wuduPool.value + step
    _wuduUserOrder.value = userOrder
    _wuduPool.value = pool
    _wuduGameResult.value = null
  }

  fun checkWuduOrder() {
    val current = _wuduUserOrder.value
    if (current.size != PaiRepository.wuduStepsMaster.size) {
      showNotice("Susun semua 9 langkah wudhu terlebih dahulu ya!")
      return
    }
    val isCorrect = current.indices.all { i ->
      current[i].correctOrder == i + 1
    }
    _wuduGameResult.value = isCorrect
    if (isCorrect) {
      addXpAndPoints(75, 50, "Sempurna! Urutan Wudhu 100% Benar! 💧✨")
    } else {
      showNotice("Masih ada urutan yang belum pas. Cek kembali ya!")
    }
  }

  // --- Tajwid Flashcards ---
  fun flipCard() {
    _isCardFlipped.value = !_isCardFlipped.value
  }

  fun nextCard() {
    _isCardFlipped.value = false
    if (_tajwidIndex.value < tajwidCards.size - 1) {
      _tajwidIndex.value += 1
    } else {
      _tajwidIndex.value = 0
    }
  }

  fun prevCard() {
    _isCardFlipped.value = false
    if (_tajwidIndex.value > 0) {
      _tajwidIndex.value -= 1
    } else {
      _tajwidIndex.value = tajwidCards.size - 1
    }
  }

  fun markCardMastered(id: String) {
    val current = _masteredCards.value + id
    _masteredCards.value = current
    addXpAndPoints(30, 20, "Hukum tajwid dikuasai! +30 XP 🌟")
  }

  // --- Audio Murottal ---
  fun selectSurah(surah: SurahAudio) {
    _activeSurah.value = surah
    _activeAyahIndex.value = 0
    _isPlayingAudio.value = false
    _recitationFeedback.value = null
  }

  fun toggleAudioPlay() {
    val newState = !_isPlayingAudio.value
    _isPlayingAudio.value = newState
    if (newState) {
      simulateAudioPlay()
    }
  }

  private fun simulateAudioPlay() {
    viewModelScope.launch {
      while (_isPlayingAudio.value && _activeAyahIndex.value < _activeSurah.value.verses.size) {
        delay(2500)
        if (_isPlayingAudio.value) {
          if (_activeAyahIndex.value < _activeSurah.value.verses.size - 1) {
            _activeAyahIndex.value += 1
          } else {
            _isPlayingAudio.value = false
            showNotice("Murottal surah selesai dibacakan dengan tartil.")
            break
          }
        }
      }
    }
  }

  fun cycleSpeed() {
    _playbackSpeed.value = when (_playbackSpeed.value) {
      "0.75x" -> "1.0x"
      "1.0x" -> "1.25x"
      else -> "0.75x"
    }
  }

  fun testRecitation() {
    viewModelScope.launch {
      _recitationFeedback.value = "Mendengarkan pelafalan kamu..."
      delay(1500)
      _recitationFeedback.value = "Mumtaz! Makhraj huruf dan panjang harakat tepat. Pertahankan ya! 🌟"
      addXpAndPoints(25, 15, "Latihan pelafalan sukses! +25 XP")
    }
  }

  // --- AI Mentor Chat ---
  fun sendChatMessage(userText: String) {
    if (userText.isBlank()) return
    val userMsg = ChatMessage(
      id = System.currentTimeMillis().toString(),
      text = userText,
      isUser = true,
      timestamp = "Sekarang"
    )
    _chatMessages.value = _chatMessages.value + userMsg

    viewModelScope.launch {
      _isAiThinking.value = true
      delay(1000)
      val reply = PaiRepository.getAiMentorResponse(userText)
      val aiMsg = ChatMessage(
        id = (System.currentTimeMillis() + 1).toString(),
        text = reply,
        isUser = false,
        timestamp = "Sekarang"
      )
      _chatMessages.value = _chatMessages.value + aiMsg
      _isAiThinking.value = false
      addXpAndPoints(10, 5, "+10 XP: Bertanya dan belajar hal baru!")
    }
  }
}
