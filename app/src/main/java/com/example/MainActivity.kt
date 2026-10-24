package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.TopHeader
import com.example.ui.screens.*
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.SoftMintBg
import com.example.viewmodel.PaiScreen
import com.example.viewmodel.PaiViewModel

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        SahabatPaiApp()
      }
    }
  }
}

@Composable
fun SahabatPaiApp(
  viewModel: PaiViewModel = viewModel()
) {
  val currentScreen by viewModel.currentScreen.collectAsState()
  val userProgress by viewModel.userProgress.collectAsState()
  val selectedGrade by viewModel.selectedGrade.collectAsState()
  val celebrationNotice by viewModel.celebrationNotice.collectAsState()
  val amalanList by viewModel.amalanList.collectAsState()

  Scaffold(
    modifier = Modifier
      .fillMaxSize()
      .background(SoftMintBg),
    containerColor = SoftMintBg,
    contentWindowInsets = WindowInsets(0.dp),
    topBar = {
      TopHeader(
        userProgress = userProgress,
        selectedGrade = selectedGrade,
        onGradeChange = { viewModel.selectGrade(it) },
        celebrationNotice = celebrationNotice
      )
    },
    bottomBar = {
      if (currentScreen == PaiScreen.Dashboard) {
        NavigationBar(
          containerColor = Color.White,
          tonalElevation = 8.dp,
          modifier = Modifier
            .windowInsetsPadding(WindowInsets.navigationBars)
            .testTag("main_navigation_bar")
        ) {
          NavigationBarItem(
            selected = currentScreen == PaiScreen.Dashboard,
            onClick = { /* already on dashboard */ },
            icon = { Icon(Icons.Default.Home, contentDescription = "Beranda") },
            label = { Text("Beranda") },
            colors = NavigationBarItemDefaults.colors(
              selectedIconColor = EmeraldPrimary,
              selectedTextColor = EmeraldPrimary,
              indicatorColor = Color(0xFFD1E8DE)
            )
          )
          NavigationBarItem(
            selected = currentScreen == PaiScreen.StoriesList,
            onClick = { viewModel.navigateTo(PaiScreen.StoriesList) },
            icon = { Icon(Icons.Default.MenuBook, contentDescription = "Kisah") },
            label = { Text("Kisah") },
            colors = NavigationBarItemDefaults.colors(
              selectedIconColor = EmeraldPrimary,
              selectedTextColor = EmeraldPrimary,
              indicatorColor = Color(0xFFD1E8DE)
            )
          )
          NavigationBarItem(
            selected = currentScreen == PaiScreen.MiniGamesHub,
            onClick = { viewModel.navigateTo(PaiScreen.MiniGamesHub) },
            icon = { Icon(Icons.Default.SportsEsports, contentDescription = "Games") },
            label = { Text("Games") },
            colors = NavigationBarItemDefaults.colors(
              selectedIconColor = EmeraldPrimary,
              selectedTextColor = EmeraldPrimary,
              indicatorColor = Color(0xFFD1E8DE)
            )
          )
          NavigationBarItem(
            selected = currentScreen == PaiScreen.AmalanTracker,
            onClick = { viewModel.navigateTo(PaiScreen.AmalanTracker) },
            icon = { Icon(Icons.Default.Checklist, contentDescription = "Amalan") },
            label = { Text("Amalan") },
            colors = NavigationBarItemDefaults.colors(
              selectedIconColor = EmeraldPrimary,
              selectedTextColor = EmeraldPrimary,
              indicatorColor = Color(0xFFD1E8DE)
            )
          )
          NavigationBarItem(
            selected = currentScreen == PaiScreen.AiMentor,
            onClick = { viewModel.navigateTo(PaiScreen.AiMentor) },
            icon = { Icon(Icons.Default.SmartToy, contentDescription = "Tanya AI") },
            label = { Text("Tanya AI") },
            colors = NavigationBarItemDefaults.colors(
              selectedIconColor = EmeraldPrimary,
              selectedTextColor = EmeraldPrimary,
              indicatorColor = Color(0xFFD1E8DE)
            )
          )
        }
      }
    }
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
    ) {
      when (val screen = currentScreen) {
        is PaiScreen.Dashboard -> {
          DashboardScreen(
            userProgress = userProgress,
            selectedGrade = selectedGrade,
            amalanList = amalanList,
            onNavigate = { viewModel.navigateTo(it) },
            onToggleAmalan = { viewModel.toggleAmalan(it) }
          )
        }

        is PaiScreen.StoriesList -> {
          StoriesListScreen(
            stories = viewModel.stories,
            onStoryClick = { viewModel.openStory(it) },
            onBack = { viewModel.navigateBack() }
          )
        }

        is PaiScreen.StoryDetail -> {
          val activeStory by viewModel.activeStory.collectAsState()
          val activeSlidePage by viewModel.activeSlidePage.collectAsState()
          val selectedReflectionAnswer by viewModel.selectedReflectionAnswer.collectAsState()
          val isReflectionSubmitted by viewModel.reflectionSubmitted.collectAsState()

          activeStory?.let { story ->
            StoryDetailScreen(
              story = story,
              activePage = activeSlidePage,
              selectedReflectionAnswer = selectedReflectionAnswer,
              isReflectionSubmitted = isReflectionSubmitted,
              onNextSlide = { viewModel.nextSlide(story.slides.size) },
              onPrevSlide = { viewModel.prevSlide() },
              onSubmitReflection = { viewModel.submitReflection(it, story) },
              onBack = { viewModel.navigateBack() }
            )
          }
        }

        is PaiScreen.MiniGamesHub -> {
          MiniGamesHubScreen(
            onNavigate = { viewModel.navigateTo(it) },
            onBack = { viewModel.navigateBack() }
          )
        }

        is PaiScreen.QuizPlay -> {
          val currentQuizIndex by viewModel.currentQuizIndex.collectAsState()
          val selectedQuizOption by viewModel.selectedQuizOption.collectAsState()
          val isQuizAnswerSubmitted by viewModel.isQuizAnswerSubmitted.collectAsState()
          val quizScore by viewModel.quizScore.collectAsState()
          val isQuizFinished by viewModel.isQuizFinished.collectAsState()

          QuizScreen(
            questions = viewModel.quizQuestions,
            currentIndex = currentQuizIndex,
            selectedOption = selectedQuizOption,
            isSubmitted = isQuizAnswerSubmitted,
            score = quizScore,
            isFinished = isQuizFinished,
            onSelectOption = { viewModel.selectQuizOption(it) },
            onSubmitAnswer = { viewModel.submitQuizAnswer() },
            onNextQuestion = { viewModel.nextQuizQuestion() },
            onRestart = { viewModel.restartQuiz() },
            onBack = { viewModel.navigateBack() }
          )
        }

        is PaiScreen.WuduGame -> {
          val userOrder by viewModel.wuduUserOrder.collectAsState()
          val pool by viewModel.wuduPool.collectAsState()
          val gameResult by viewModel.wuduGameResult.collectAsState()

          WuduGameScreen(
            userOrder = userOrder,
            pool = pool,
            gameResult = gameResult,
            onAddStep = { viewModel.addStepToUserOrder(it) },
            onRemoveStep = { viewModel.removeStepFromUserOrder(it) },
            onCheckOrder = { viewModel.checkWuduOrder() },
            onReset = { viewModel.initWuduGame() },
            onBack = { viewModel.navigateBack() }
          )
        }

        is PaiScreen.TajwidFlashcards -> {
          val currentIndex by viewModel.tajwidIndex.collectAsState()
          val isFlipped by viewModel.isCardFlipped.collectAsState()
          val masteredCards by viewModel.masteredCards.collectAsState()

          TajwidFlashcardsScreen(
            cards = viewModel.tajwidCards,
            currentIndex = currentIndex,
            isFlipped = isFlipped,
            masteredCards = masteredCards,
            onFlipCard = { viewModel.flipCard() },
            onNextCard = { viewModel.nextCard() },
            onPrevCard = { viewModel.prevCard() },
            onMarkMastered = { viewModel.markCardMastered(it) },
            onBack = { viewModel.navigateBack() }
          )
        }

        is PaiScreen.AmalanTracker -> {
          AmalanScreen(
            amalanList = amalanList,
            userProgress = userProgress,
            onToggleAmalan = { viewModel.toggleAmalan(it) },
            onBack = { viewModel.navigateBack() }
          )
        }

        is PaiScreen.AudioMurottal -> {
          val activeSurah by viewModel.activeSurah.collectAsState()
          val isPlaying by viewModel.isPlayingAudio.collectAsState()
          val speed by viewModel.playbackSpeed.collectAsState()
          val activeAyahIndex by viewModel.activeAyahIndex.collectAsState()
          val recitationFeedback by viewModel.recitationFeedback.collectAsState()

          MurottalScreen(
            surahs = viewModel.surahs,
            activeSurah = activeSurah,
            isPlaying = isPlaying,
            speed = speed,
            activeAyahIndex = activeAyahIndex,
            recitationFeedback = recitationFeedback,
            onSelectSurah = { viewModel.selectSurah(it) },
            onTogglePlay = { viewModel.toggleAudioPlay() },
            onCycleSpeed = { viewModel.cycleSpeed() },
            onTestRecitation = { viewModel.testRecitation() },
            onBack = { viewModel.navigateBack() }
          )
        }

        is PaiScreen.AiMentor -> {
          val messages by viewModel.chatMessages.collectAsState()
          val isThinking by viewModel.isAiThinking.collectAsState()

          AiMentorScreen(
            messages = messages,
            isThinking = isThinking,
            onSendMessage = { viewModel.sendChatMessage(it) },
            onBack = { viewModel.navigateBack() }
          )
        }
      }
    }
  }
}
