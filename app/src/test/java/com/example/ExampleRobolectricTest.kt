package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.PaiRepository
import com.example.model.EducationLevel
import com.example.viewmodel.PaiScreen
import com.example.viewmodel.PaiViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Sahabat PAI", appName)
  }

  @Test
  fun `test PaiRepository contents`() {
    assertTrue(PaiRepository.stories.isNotEmpty())
    assertTrue(PaiRepository.quizList.isNotEmpty())
    assertEquals(9, PaiRepository.wuduStepsMaster.size)
    assertEquals(5, PaiRepository.tajwidFlashcards.size)
    assertTrue(PaiRepository.surahs.isNotEmpty())
  }

  @Test
  fun `test PaiViewModel gamification and navigation`() {
    val viewModel = PaiViewModel()
    assertEquals(PaiScreen.Dashboard, viewModel.currentScreen.value)

    viewModel.navigateTo(PaiScreen.MiniGamesHub)
    assertEquals(PaiScreen.MiniGamesHub, viewModel.currentScreen.value)

    val initialXp = viewModel.userProgress.value.xp
    viewModel.addXpAndPoints(50, 20, "Test Bonus")
    assertEquals(initialXp + 50, viewModel.userProgress.value.xp)

    val popped = viewModel.navigateBack()
    assertTrue(popped)
    assertEquals(PaiScreen.Dashboard, viewModel.currentScreen.value)
  }

  @Test
  fun `test AI mentor knowledge response`() {
    val replyWudu = PaiRepository.getAiMentorResponse("Bagaimana cara wudhu?")
    assertTrue(replyWudu.contains("wudhu", ignoreCase = true))

    val replyTajwid = PaiRepository.getAiMentorResponse("Jelaskan hukum tajwid idgham")
    assertTrue(replyTajwid.contains("tajwid", ignoreCase = true))
  }
}
