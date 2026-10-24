package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.Story
import com.example.ui.theme.*

@Composable
fun StoriesListScreen(
  stories: List<Story>,
  onStoryClick: (Story) -> Unit,
  onBack: () -> Unit,
  modifier: Modifier = Modifier
) {
  BackHandler { onBack() }

  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(16.dp)
  ) {
    // Screen Top Row
    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically
    ) {
      IconButton(
        onClick = onBack,
        modifier = Modifier.testTag("btn_back_stories")
      ) {
        Icon(
          imageVector = Icons.AutoMirrored.Filled.ArrowBack,
          contentDescription = "Kembali ke Beranda"
        )
      }
      Spacer(modifier = Modifier.width(4.dp))
      Column {
        Text(
          text = "Interactive Storytelling",
          style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.Bold,
            color = TextPrimary
          )
        )
        Text(
          text = "Kisah Teladan Para Nabi & Sahabat Mulia",
          style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
        )
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    LazyColumn(
      verticalArrangement = Arrangement.spacedBy(14.dp),
      modifier = Modifier.fillMaxSize()
    ) {
      items(stories) { story ->
        Card(
          shape = RoundedCornerShape(18.dp),
          colors = CardDefaults.cardColors(containerColor = Color.White),
          elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
          modifier = Modifier
            .fillMaxWidth()
            .clickable { onStoryClick(story) }
            .testTag("story_card_${story.id}")
        ) {
          Column(modifier = Modifier.padding(16.dp)) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Surface(
                shape = RoundedCornerShape(8.dp),
                color = EmeraldContainer
              ) {
                Text(
                  text = story.prophet,
                  style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = OnEmeraldContainer
                  ),
                  modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
              }
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                  imageVector = Icons.Default.AccessTime,
                  contentDescription = null,
                  tint = Color.Gray,
                  modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                  text = "${story.readingMinutes} Menit Baca",
                  style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary)
                )
              }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
              text = story.title,
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = TextPrimary
              )
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
              text = story.summary,
              style = MaterialTheme.typography.bodySmall.copy(
                color = TextSecondary,
                lineHeight = 18.sp
              )
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Surface(
                shape = RoundedCornerShape(6.dp),
                color = AmberGoldLight.copy(alpha = 0.5f)
              ) {
                Text(
                  text = "+50 XP Refleksi",
                  style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = AmberGoldDark
                  ),
                  modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                )
              }

              Button(
                onClick = { onStoryClick(story) },
                colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp)
              ) {
                Text("Mulai Membaca", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                  imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                  contentDescription = null,
                  modifier = Modifier.size(14.dp)
                )
              }
            }
          }
        }
      }
    }
  }
}

@Composable
fun StoryDetailScreen(
  story: Story,
  activePage: Int,
  selectedReflectionAnswer: Int?,
  isReflectionSubmitted: Boolean,
  onNextSlide: () -> Unit,
  onPrevSlide: () -> Unit,
  onSubmitReflection: (Int) -> Unit,
  onBack: () -> Unit,
  modifier: Modifier = Modifier
) {
  BackHandler { onBack() }

  val totalSlides = story.slides.size
  val isReflectionPage = activePage > totalSlides
  val currentSlide = if (!isReflectionPage) story.slides[activePage - 1] else null

  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(16.dp)
  ) {
    // Header
    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(
          onClick = onBack,
          modifier = Modifier.testTag("btn_back_story_detail")
        ) {
          Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
        }
        Text(
          text = story.title,
          style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Bold,
            color = TextPrimary
          ),
          maxLines = 1
        )
      }

      Surface(
        shape = RoundedCornerShape(12.dp),
        color = EmeraldContainer
      ) {
        Text(
          text = if (!isReflectionPage) "Halaman $activePage / $totalSlides" else "Refleksi",
          style = MaterialTheme.typography.labelSmall.copy(
            fontWeight = FontWeight.Bold,
            color = OnEmeraldContainer
          ),
          modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    // Story Visual Artwork (if story is s1 / Nuh, show our generated artwork)
    if (story.id == "s1") {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(180.dp)
          .clip(RoundedCornerShape(18.dp))
      ) {
        Image(
          painter = painterResource(id = R.drawable.img_story_nuh),
          contentDescription = "Bahtera Nabi Nuh",
          contentScale = ContentScale.Crop,
          modifier = Modifier.fillMaxSize()
        )
      }
      Spacer(modifier = Modifier.height(14.dp))
    }

    // Slide Body / Reflection Card
    Card(
      shape = RoundedCornerShape(20.dp),
      colors = CardDefaults.cardColors(containerColor = Color.White),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
      modifier = Modifier
        .fillMaxWidth()
        .weight(1f)
    ) {
      LazyColumn(
        modifier = Modifier
          .fillMaxSize()
          .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
      ) {
        if (!isReflectionPage && currentSlide != null) {
          item {
            Text(
              text = currentSlide.title,
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = EmeraldDark
              )
            )
          }

          item {
            Text(
              text = currentSlide.text,
              style = MaterialTheme.typography.bodyLarge.copy(
                lineHeight = 26.sp,
                color = TextPrimary
              )
            )
          }

          item {
            Surface(
              shape = RoundedCornerShape(12.dp),
              color = SoftMintBg,
              modifier = Modifier.fillMaxWidth()
            ) {
              Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Icon(
                  imageVector = Icons.Default.VolumeUp,
                  contentDescription = "Narasi Audio",
                  tint = EmeraldPrimary,
                  modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                  text = "\"${currentSlide.audioText}\"",
                  style = MaterialTheme.typography.bodySmall.copy(
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                    color = EmeraldDark
                  )
                )
              }
            }
          }
        } else {
          // Reflection & Hikmah Page
          item {
            Text(
              text = "Tantangan Refleksi & Hikmah",
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = EmeraldPrimary
              )
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
              text = story.reflectionQuestion,
              style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary
              )
            )
          }

          items(story.reflectionOptions.indices.toList()) { index ->
            val optionText = story.reflectionOptions[index]
            val isSelected = selectedReflectionAnswer == index
            val isCorrect = index == story.correctOptionIndex

            val cardColor = when {
              isReflectionSubmitted && isCorrect -> Color(0xFFDCFCE7)
              isReflectionSubmitted && isSelected && !isCorrect -> Color(0xFFFEE2E2)
              isSelected -> EmeraldContainer
              else -> Color(0xFFF9FAFB)
            }

            Surface(
              shape = RoundedCornerShape(12.dp),
              color = cardColor,
              modifier = Modifier
                .fillMaxWidth()
                .clickable(enabled = !isReflectionSubmitted) {
                  onSubmitReflection(index)
                }
                .testTag("reflection_option_$index")
            ) {
              Row(
                modifier = Modifier.padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text(
                  text = "${'A' + index}.",
                  fontWeight = FontWeight.Bold,
                  color = if (isSelected) EmeraldDark else TextSecondary
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                  text = optionText,
                  style = MaterialTheme.typography.bodyMedium.copy(
                    color = TextPrimary,
                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                  ),
                  modifier = Modifier.weight(1f)
                )
                if (isReflectionSubmitted && isCorrect) {
                  Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = EmeraldPrimary
                  )
                }
              }
            }
          }

          item {
            AnimatedVisibility(visible = isReflectionSubmitted) {
              Surface(
                shape = RoundedCornerShape(14.dp),
                color = AmberGoldContainer,
                modifier = Modifier.fillMaxWidth()
              ) {
                Column(modifier = Modifier.padding(14.dp)) {
                  Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "✨", fontSize = 18.sp)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                      text = "Hikmah Utama Cerita",
                      style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = AmberGoldDark
                      )
                    )
                  }
                  Spacer(modifier = Modifier.height(6.dp))
                  Text(
                    text = story.hikmah,
                    style = MaterialTheme.typography.bodySmall.copy(
                      color = Color(0xFF451A03),
                      lineHeight = 18.sp
                    )
                  )
                }
              }
            }
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    // Navigation Buttons (Prev / Next)
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      OutlinedButton(
        onClick = onPrevSlide,
        enabled = activePage > 1,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.testTag("btn_prev_slide")
      ) {
        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, modifier = Modifier.size(16.dp))
        Spacer(modifier = Modifier.width(6.dp))
        Text("Sebelumnya")
      }

      if (!isReflectionPage) {
        Button(
          onClick = onNextSlide,
          colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary),
          shape = RoundedCornerShape(12.dp),
          modifier = Modifier.testTag("btn_next_slide")
        ) {
          Text(if (activePage < totalSlides) "Selanjutnya" else "Ke Refleksi")
          Spacer(modifier = Modifier.width(6.dp))
          Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
        }
      } else {
        Button(
          onClick = onBack,
          colors = ButtonDefaults.buttonColors(containerColor = AmberGold),
          shape = RoundedCornerShape(12.dp),
          modifier = Modifier.testTag("btn_finish_story")
        ) {
          Text("Selesai Membaca", color = Color(0xFF451A03), fontWeight = FontWeight.Bold)
          Spacer(modifier = Modifier.width(6.dp))
          Icon(Icons.Default.Check, contentDescription = null, tint = Color(0xFF451A03), modifier = Modifier.size(16.dp))
        }
      }
    }
  }
}
