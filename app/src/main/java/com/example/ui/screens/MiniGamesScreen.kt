package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.QuizQuestion
import com.example.model.TajwidCard
import com.example.model.WuduStep
import com.example.ui.theme.*
import com.example.viewmodel.PaiScreen

@Composable
fun MiniGamesHubScreen(
  onNavigate: (PaiScreen) -> Unit,
  onBack: () -> Unit,
  modifier: Modifier = Modifier
) {
  BackHandler { onBack() }

  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(16.dp)
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically
    ) {
      IconButton(onClick = onBack, modifier = Modifier.testTag("btn_back_games_hub")) {
        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
      }
      Spacer(modifier = Modifier.width(4.dp))
      Column {
        Text(
          text = "Arena Mini Games PAI",
          style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.Bold,
            color = TextPrimary
          )
        )
        Text(
          text = "Bermain sambil mengasah pemahaman agama",
          style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
        )
      }
    }

    Spacer(modifier = Modifier.height(20.dp))

    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
      item {
        GameSelectionCard(
          iconEmoji = "❓",
          title = "Kuis Cepat PAI",
          desc = "Jawab 5 pertanyaan seputar Fiqih, Aqidah, dan Akhlak dengan pembahasan interaktif.",
          xpReward = "+150 XP Total",
          badgeColor = Color(0xFFE0F2FE),
          badgeTextColor = Color(0xFF0369A1),
          onClick = { onNavigate(PaiScreen.QuizPlay) },
          testTag = "btn_play_quiz"
        )
      }

      item {
        GameSelectionCard(
          iconEmoji = "💧",
          title = "Susun Urutan Wudhu",
          desc = "Tantangan menyusun 9 tahapan wudhu yang benar dari niat sampai doa penutup.",
          xpReward = "+75 XP",
          badgeColor = Color(0xFFDCFCE7),
          badgeTextColor = Color(0xFF166534),
          onClick = { onNavigate(PaiScreen.WuduGame) },
          testTag = "btn_play_wudu"
        )
      }

      item {
        GameSelectionCard(
          iconEmoji = "🎴",
          title = "Flashcards Tajwid",
          desc = "Kartu interaktif hukum Nun Sukun & Tanwin lengkap dengan contoh ayat dan cara pengucapan.",
          xpReward = "+30 XP Tiap Kartu",
          badgeColor = Color(0xFFFEF3C7),
          badgeTextColor = Color(0xFF92400E),
          onClick = { onNavigate(PaiScreen.TajwidFlashcards) },
          testTag = "btn_play_tajwid"
        )
      }
    }
  }
}

@Composable
fun GameSelectionCard(
  iconEmoji: String,
  title: String,
  desc: String,
  xpReward: String,
  badgeColor: Color,
  badgeTextColor: Color,
  onClick: () -> Unit,
  testTag: String
) {
  Card(
    shape = RoundedCornerShape(20.dp),
    colors = CardDefaults.cardColors(containerColor = Color.White),
    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
    modifier = Modifier
      .fillMaxWidth()
      .clickable { onClick() }
      .testTag(testTag)
  ) {
    Column(modifier = Modifier.padding(18.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Surface(
          shape = RoundedCornerShape(14.dp),
          color = SoftMintBg,
          modifier = Modifier.size(50.dp)
        ) {
          Box(contentAlignment = Alignment.Center) {
            Text(text = iconEmoji, fontSize = 26.sp)
          }
        }

        Surface(
          shape = RoundedCornerShape(10.dp),
          color = badgeColor
        ) {
          Text(
            text = xpReward,
            style = MaterialTheme.typography.labelSmall.copy(
              fontWeight = FontWeight.Bold,
              color = badgeTextColor
            ),
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(12.dp))

      Text(
        text = title,
        style = MaterialTheme.typography.titleMedium.copy(
          fontWeight = FontWeight.Bold,
          color = TextPrimary
        )
      )

      Spacer(modifier = Modifier.height(4.dp))

      Text(
        text = desc,
        style = MaterialTheme.typography.bodySmall.copy(
          color = TextSecondary,
          lineHeight = 18.sp
        )
      )

      Spacer(modifier = Modifier.height(14.dp))

      Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Text("Mainkan Sekarang", fontWeight = FontWeight.Bold)
      }
    }
  }
}

// ---------------- Quiz Screen ----------------
@Composable
fun QuizScreen(
  questions: List<QuizQuestion>,
  currentIndex: Int,
  selectedOption: Int?,
  isSubmitted: Boolean,
  score: Int,
  isFinished: Boolean,
  onSelectOption: (Int) -> Unit,
  onSubmitAnswer: () -> Unit,
  onNextQuestion: () -> Unit,
  onRestart: () -> Unit,
  onBack: () -> Unit,
  modifier: Modifier = Modifier
) {
  BackHandler { onBack() }

  val question = questions.getOrNull(currentIndex)

  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(16.dp)
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = onBack, modifier = Modifier.testTag("btn_back_quiz")) {
          Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
        }
        Text(
          text = "Kuis PAI Interaktif",
          style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Bold,
            color = TextPrimary
          )
        )
      }

      Surface(
        shape = RoundedCornerShape(10.dp),
        color = AmberGoldLight
      ) {
        Text(
          text = "Skor: $score/${questions.size}",
          style = MaterialTheme.typography.labelSmall.copy(
            fontWeight = FontWeight.Bold,
            color = AmberGoldDark
          ),
          modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
      }
    }

    Spacer(modifier = Modifier.height(10.dp))

    if (isFinished) {
      // Finished Summary
      Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 20.dp)
      ) {
        Column(
          modifier = Modifier.padding(24.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Text(text = "🏆", fontSize = 54.sp)
          Spacer(modifier = Modifier.height(12.dp))
          Text(
            text = "Kuis Selesai! Mumtaz!",
            style = MaterialTheme.typography.titleLarge.copy(
              fontWeight = FontWeight.Bold,
              color = EmeraldDark
            )
          )
          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = "Kamu menjawab $score dari ${questions.size} soal dengan benar.",
            style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary),
            textAlign = TextAlign.Center
          )
          Spacer(modifier = Modifier.height(20.dp))

          Button(
            onClick = onRestart,
            colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
              .fillMaxWidth()
              .testTag("btn_quiz_restart")
          ) {
            Text("Main Lagi", fontWeight = FontWeight.Bold)
          }

          Spacer(modifier = Modifier.height(10.dp))

          OutlinedButton(
            onClick = onBack,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
          ) {
            Text("Kembali ke Menu")
          }
        }
      }
    } else if (question != null) {
      // Progress Bar
      val progress = ((currentIndex + 1).toFloat() / questions.size.toFloat())
      LinearProgressIndicator(
        progress = { progress },
        modifier = Modifier
          .fillMaxWidth()
          .height(8.dp)
          .clip(RoundedCornerShape(4.dp)),
        color = EmeraldPrimary,
        trackColor = EmeraldContainer
      )

      Spacer(modifier = Modifier.height(12.dp))

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
            text = question.category,
            style = MaterialTheme.typography.labelSmall.copy(
              fontWeight = FontWeight.Bold,
              color = OnEmeraldContainer
            ),
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
          )
        }
        Text(
          text = "Soal ${currentIndex + 1} dari ${questions.size}",
          style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary)
        )
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Question Card
      Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(18.dp)) {
          Text(
            text = question.question,
            style = MaterialTheme.typography.bodyLarge.copy(
              fontWeight = FontWeight.Bold,
              color = TextPrimary,
              lineHeight = 24.sp
            )
          )
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Options List
      LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.weight(1f)
      ) {
        itemsIndexed(question.options) { index, option ->
          val isSelected = selectedOption == index
          val isCorrect = index == question.correctIndex

          val cardColor = when {
            isSubmitted && isCorrect -> Color(0xFFDCFCE7)
            isSubmitted && isSelected && !isCorrect -> Color(0xFFFEE2E2)
            isSelected -> EmeraldContainer
            else -> Color.White
          }

          val borderColor = when {
            isSubmitted && isCorrect -> EmeraldPrimary
            isSubmitted && isSelected && !isCorrect -> Color(0xFFEF4444)
            isSelected -> EmeraldPrimary
            else -> BorderSubtle
          }

          Surface(
            shape = RoundedCornerShape(14.dp),
            color = cardColor,
            border = androidx.compose.foundation.BorderStroke(1.5.dp, borderColor),
            modifier = Modifier
              .fillMaxWidth()
              .clickable(enabled = !isSubmitted) { onSelectOption(index) }
              .testTag("quiz_option_$index")
          ) {
            Row(
              modifier = Modifier.padding(14.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Surface(
                shape = CircleShape,
                color = if (isSelected) EmeraldPrimary else Color(0xFFF3F4F6),
                modifier = Modifier.size(28.dp)
              ) {
                Box(contentAlignment = Alignment.Center) {
                  Text(
                    text = ('A' + index).toString(),
                    fontWeight = FontWeight.Bold,
                    color = if (isSelected) Color.White else TextPrimary,
                    fontSize = 12.sp
                  )
                }
              }
              Spacer(modifier = Modifier.width(12.dp))
              Text(
                text = option,
                style = MaterialTheme.typography.bodyMedium.copy(
                  fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                  color = TextPrimary
                ),
                modifier = Modifier.weight(1f)
              )
              if (isSubmitted && isCorrect) {
                Icon(
                  imageVector = Icons.Default.CheckCircle,
                  contentDescription = null,
                  tint = EmeraldPrimary
                )
              }
            }
          }
        }

        // Explanation when submitted
        if (isSubmitted) {
          item {
            Surface(
              shape = RoundedCornerShape(12.dp),
              color = Color(0xFFF0FDF4),
              border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF86EFAC)),
              modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp)
            ) {
              Column(modifier = Modifier.padding(12.dp)) {
                Text(
                  text = "💡 Pembahasan:",
                  style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = EmeraldDark
                  )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                  text = question.explanation,
                  style = MaterialTheme.typography.bodySmall.copy(
                    color = TextPrimary,
                    lineHeight = 18.sp
                  )
                )
              }
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Bottom Action Button
      if (!isSubmitted) {
        Button(
          onClick = onSubmitAnswer,
          enabled = selectedOption != null,
          colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary),
          shape = RoundedCornerShape(12.dp),
          modifier = Modifier
            .fillMaxWidth()
            .testTag("btn_submit_quiz_answer")
        ) {
          Text("Kirim Jawaban", fontWeight = FontWeight.Bold)
        }
      } else {
        Button(
          onClick = onNextQuestion,
          colors = ButtonDefaults.buttonColors(containerColor = AmberGold),
          shape = RoundedCornerShape(12.dp),
          modifier = Modifier
            .fillMaxWidth()
            .testTag("btn_next_quiz_question")
        ) {
          Text(
            text = if (currentIndex < questions.size - 1) "Soal Berikutnya" else "Lihat Hasil Kuis",
            color = Color(0xFF451A03),
            fontWeight = FontWeight.Bold
          )
          Spacer(modifier = Modifier.width(6.dp))
          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = null,
            tint = Color(0xFF451A03),
            modifier = Modifier.size(16.dp)
          )
        }
      }
    }
  }
}

// ---------------- Wudu Game Screen ----------------
@Composable
fun WuduGameScreen(
  userOrder: List<WuduStep>,
  pool: List<WuduStep>,
  gameResult: Boolean?,
  onAddStep: (WuduStep) -> Unit,
  onRemoveStep: (WuduStep) -> Unit,
  onCheckOrder: () -> Unit,
  onReset: () -> Unit,
  onBack: () -> Unit,
  modifier: Modifier = Modifier
) {
  BackHandler { onBack() }

  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(16.dp)
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = onBack, modifier = Modifier.testTag("btn_back_wudu")) {
          Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
        }
        Text(
          text = "Urutan Wudhu Sempurna",
          style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Bold,
            color = TextPrimary
          )
        )
      }
      IconButton(onClick = onReset, modifier = Modifier.testTag("btn_reset_wudu")) {
        Icon(Icons.Default.Refresh, contentDescription = "Ulangi")
      }
    }

    Text(
      text = "Ketuk langkah wudhu di bawah untuk memasukkannya ke urutan yang benar (1 - 9):",
      style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary),
      modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp)
    )

    // Result banner if checked
    AnimatedVisibility(visible = gameResult != null) {
      val isSuccess = gameResult == true
      Surface(
        shape = RoundedCornerShape(12.dp),
        color = if (isSuccess) Color(0xFFDCFCE7) else Color(0xFFFEE2E2),
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 6.dp)
      ) {
        Row(
          modifier = Modifier.padding(12.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(
            imageVector = if (isSuccess) Icons.Default.CheckCircle else Icons.Default.Info,
            contentDescription = null,
            tint = if (isSuccess) EmeraldPrimary else Color(0xFFEF4444)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = if (isSuccess) "Mumtaz! Semua 9 urutan wudhu tepat sempurna! (+75 XP)" else "Ada urutan yang tertukar. Ketuk untuk koreksi ya!",
            style = MaterialTheme.typography.bodySmall.copy(
              fontWeight = FontWeight.Bold,
              color = if (isSuccess) EmeraldDark else Color(0xFF991B1B)
            )
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(8.dp))

    // User Ordered List
    Text(
      text = "Urutan Pilihanmu (${userOrder.size}/9):",
      style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold, color = EmeraldDark)
    )

    Spacer(modifier = Modifier.height(4.dp))

    LazyColumn(
      modifier = Modifier
        .fillMaxWidth()
        .weight(1f)
        .clip(RoundedCornerShape(14.dp))
        .background(Color.White)
        .padding(8.dp),
      verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
      if (userOrder.isEmpty()) {
        item {
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .height(100.dp),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = "Pilih langkah dari kotak di bawah...",
              style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
            )
          }
        }
      } else {
        itemsIndexed(userOrder) { index, step ->
          Surface(
            shape = RoundedCornerShape(10.dp),
            color = SoftMintBg,
            modifier = Modifier
              .fillMaxWidth()
              .clickable { onRemoveStep(step) }
              .testTag("user_step_$index")
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Surface(
                shape = CircleShape,
                color = EmeraldPrimary,
                modifier = Modifier.size(24.dp)
              ) {
                Box(contentAlignment = Alignment.Center) {
                  Text(
                    text = "${index + 1}",
                    style = MaterialTheme.typography.labelSmall.copy(
                      color = Color.White,
                      fontWeight = FontWeight.Bold
                    )
                  )
                }
              }
              Spacer(modifier = Modifier.width(10.dp))
              Column(modifier = Modifier.weight(1f)) {
                Text(
                  text = step.title,
                  style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                  )
                )
                Text(
                  text = step.arabicTitle,
                  style = MaterialTheme.typography.labelSmall.copy(color = EmeraldPrimary)
                )
              }
              Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Hapus",
                tint = Color.Gray,
                modifier = Modifier.size(16.dp)
              )
            }
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(10.dp))

    // Unassigned Pool
    Text(
      text = "Langkah Tersedia (Ketuk untuk Menambahkan):",
      style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold, color = TextPrimary)
    )

    Spacer(modifier = Modifier.height(4.dp))

    LazyColumn(
      modifier = Modifier
        .fillMaxWidth()
        .height(150.dp)
        .clip(RoundedCornerShape(14.dp))
        .background(Color(0xFFF3F4F6))
        .padding(6.dp),
      verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
      if (pool.isEmpty()) {
        item {
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .height(80.dp),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = "Semua langkah telah disusun! Tekan 'Cek Urutan' di bawah.",
              style = MaterialTheme.typography.bodySmall.copy(
                color = EmeraldDark,
                fontWeight = FontWeight.Medium
              )
            )
          }
        }
      } else {
        items(pool) { step ->
          Surface(
            shape = RoundedCornerShape(10.dp),
            color = Color.White,
            modifier = Modifier
              .fillMaxWidth()
              .clickable { onAddStep(step) }
              .testTag("pool_step_${step.id}")
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(text = "💧", fontSize = 16.sp)
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = step.title,
                style = MaterialTheme.typography.bodySmall.copy(
                  fontWeight = FontWeight.Medium,
                  color = TextPrimary
                ),
                modifier = Modifier.weight(1f)
              )
              Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = "Tambah",
                tint = EmeraldPrimary,
                modifier = Modifier.size(20.dp)
              )
            }
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    Button(
      onClick = onCheckOrder,
      enabled = userOrder.isNotEmpty(),
      colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary),
      shape = RoundedCornerShape(12.dp),
      modifier = Modifier
        .fillMaxWidth()
        .testTag("btn_check_wudu_order")
    ) {
      Text("Cek Urutan Wudhu", fontWeight = FontWeight.Bold)
    }
  }
}

// ---------------- Tajwid Flashcards Screen ----------------
@Composable
fun TajwidFlashcardsScreen(
  cards: List<TajwidCard>,
  currentIndex: Int,
  isFlipped: Boolean,
  masteredCards: Set<String>,
  onFlipCard: () -> Unit,
  onNextCard: () -> Unit,
  onPrevCard: () -> Unit,
  onMarkMastered: (String) -> Unit,
  onBack: () -> Unit,
  modifier: Modifier = Modifier
) {
  BackHandler { onBack() }

  val card = cards[currentIndex]
  val isMastered = masteredCards.contains(card.id)

  val rotation by animateFloatAsState(
    targetValue = if (isFlipped) 180f else 0f,
    animationSpec = tween(durationMillis = 400),
    label = "flip_card_anim"
  )

  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = onBack, modifier = Modifier.testTag("btn_back_tajwid")) {
          Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
        }
        Text(
          text = "Flashcard Tajwid",
          style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Bold,
            color = TextPrimary
          )
        )
      }

      Surface(
        shape = RoundedCornerShape(10.dp),
        color = EmeraldContainer
      ) {
        Text(
          text = "${currentIndex + 1} / ${cards.size}",
          style = MaterialTheme.typography.labelSmall.copy(
            fontWeight = FontWeight.Bold,
            color = OnEmeraldContainer
          ),
          modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
      }
    }

    Spacer(modifier = Modifier.height(10.dp))

    Text(
      text = "Ketuk kartu untuk membalik (Lihat Contoh & Cara Baca)",
      style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
    )

    Spacer(modifier = Modifier.height(16.dp))

    // 3D Flip Card
    Card(
      shape = RoundedCornerShape(24.dp),
      colors = CardDefaults.cardColors(
        containerColor = if (!isFlipped) Color.White else Color(0xFFF0FDF4)
      ),
      elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
      modifier = Modifier
        .fillMaxWidth()
        .height(300.dp)
        .graphicsLayer {
          rotationY = rotation
          cameraDistance = 12f * density
        }
        .clickable { onFlipCard() }
        .testTag("tajwid_card_surface")
    ) {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .padding(20.dp),
        contentAlignment = Alignment.Center
      ) {
        if (rotation <= 90f) {
          // FRONT FACE
          Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
          ) {
            Surface(
              shape = RoundedCornerShape(12.dp),
              color = EmeraldContainer
            ) {
              Text(
                text = "Nun Sukun & Tanwin",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontWeight = FontWeight.Bold,
                  color = OnEmeraldContainer
                ),
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
              )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
              text = card.hukum,
              style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = EmeraldDark
              )
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
              text = "Arti: ${card.arti}",
              style = MaterialTheme.typography.bodyMedium.copy(
                color = TextSecondary,
                fontWeight = FontWeight.Medium
              )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Surface(
              shape = RoundedCornerShape(14.dp),
              color = SoftMintBg,
              modifier = Modifier.padding(horizontal = 12.dp)
            ) {
              Column(
                modifier = Modifier.padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
              ) {
                Text(
                  text = "Huruf-Hurufnya:",
                  style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                  text = card.huruf,
                  style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = EmeraldPrimary
                  ),
                  textAlign = TextAlign.Center
                )
              }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
              text = "🔄 Ketuk untuk membalik kartu",
              style = MaterialTheme.typography.labelSmall.copy(color = Color.Gray)
            )
          }
        } else {
          // BACK FACE (Rotated 180 to be readable)
          Column(
            modifier = Modifier.graphicsLayer { rotationY = 180f },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
          ) {
            Surface(
              shape = RoundedCornerShape(10.dp),
              color = AmberGoldLight
            ) {
              Text(
                text = "Contoh Ayat Al-Qur'an",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontWeight = FontWeight.Bold,
                  color = AmberGoldDark
                ),
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
              )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
              text = card.contohArab,
              style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                color = TextPrimary
              ),
              textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
              text = card.caraBaca,
              style = MaterialTheme.typography.bodySmall.copy(
                color = EmeraldDark,
                fontWeight = FontWeight.SemiBold
              ),
              textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
              text = card.penjelasan,
              style = MaterialTheme.typography.bodySmall.copy(
                color = TextSecondary,
                lineHeight = 18.sp
              ),
              textAlign = TextAlign.Center
            )
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Mastery Action Button
    Button(
      onClick = { onMarkMastered(card.id) },
      colors = ButtonDefaults.buttonColors(
        containerColor = if (isMastered) Color(0xFF10B981) else AmberGold
      ),
      shape = RoundedCornerShape(12.dp),
      modifier = Modifier.testTag("btn_mark_mastered")
    ) {
      Icon(
        imageVector = if (isMastered) Icons.Default.CheckCircle else Icons.Default.Star,
        contentDescription = null,
        tint = if (isMastered) Color.White else Color(0xFF451A03),
        modifier = Modifier.size(18.dp)
      )
      Spacer(modifier = Modifier.width(8.dp))
      Text(
        text = if (isMastered) "Telah Dikuasai ✨" else "Tandai Sudah Paham (+30 XP)",
        color = if (isMastered) Color.White else Color(0xFF451A03),
        fontWeight = FontWeight.Bold
      )
    }

    Spacer(modifier = Modifier.height(20.dp))

    // Prev / Next Steppers
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      OutlinedButton(
        onClick = onPrevCard,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.testTag("btn_prev_tajwid")
      ) {
        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, modifier = Modifier.size(16.dp))
        Spacer(modifier = Modifier.width(4.dp))
        Text("Kartu Sebelumnya")
      }

      Button(
        onClick = onNextCard,
        colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.testTag("btn_next_tajwid")
      ) {
        Text("Kartu Berikutnya")
        Spacer(modifier = Modifier.width(4.dp))
        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
      }
    }
  }
}
