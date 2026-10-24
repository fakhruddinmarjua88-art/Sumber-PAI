package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.SurahAudio
import com.example.ui.theme.*

@Composable
fun MurottalScreen(
  surahs: List<SurahAudio>,
  activeSurah: SurahAudio,
  isPlaying: Boolean,
  speed: String,
  activeAyahIndex: Int,
  recitationFeedback: String?,
  onSelectSurah: (SurahAudio) -> Unit,
  onTogglePlay: () -> Unit,
  onCycleSpeed: () -> Unit,
  onTestRecitation: () -> Unit,
  onBack: () -> Unit,
  modifier: Modifier = Modifier
) {
  BackHandler { onBack() }

  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(16.dp)
  ) {
    // Header
    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically
    ) {
      IconButton(onClick = onBack, modifier = Modifier.testTag("btn_back_murottal")) {
        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
      }
      Spacer(modifier = Modifier.width(4.dp))
      Column {
        Text(
          text = "Murottal & Cek Pelafalan",
          style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.Bold,
            color = TextPrimary
          )
        )
        Text(
          text = "Dengarkan tartil Al-Qur'an dan latih bacaanmu",
          style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
        )
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    // Surah Selection Chips
    LazyRow(
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      items(surahs) { surah ->
        val isSelected = surah.id == activeSurah.id
        FilterChip(
          selected = isSelected,
          onClick = { onSelectSurah(surah) },
          label = {
            Text(
              text = "${surah.title} (${surah.arabicTitle})",
              fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
            )
          },
          colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = EmeraldPrimary,
            selectedLabelColor = Color.White,
            containerColor = Color.White,
            labelColor = TextPrimary
          ),
          shape = RoundedCornerShape(14.dp)
        )
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    // Audio Player Card
    Card(
      shape = RoundedCornerShape(20.dp),
      colors = CardDefaults.cardColors(containerColor = Color.White),
      elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(
        modifier = Modifier.padding(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Text(
              text = activeSurah.title,
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = TextPrimary
              )
            )
            Text(
              text = "Arti: ${activeSurah.meaning} • ${activeSurah.versesCount} Ayat",
              style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
            )
          }

          Text(
            text = activeSurah.arabicTitle,
            style = MaterialTheme.typography.headlineSmall.copy(
              fontWeight = FontWeight.Bold,
              color = EmeraldPrimary
            )
          )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Play Controls
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceEvenly,
          verticalAlignment = Alignment.CenterVertically
        ) {
          // Speed button
          OutlinedButton(
            onClick = onCycleSpeed,
            shape = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp)
          ) {
            Text(text = "Kecepatan: $speed", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
          }

          // Main Play / Pause Button
          FilledIconButton(
            onClick = onTogglePlay,
            colors = IconButtonDefaults.filledIconButtonColors(containerColor = EmeraldPrimary),
            modifier = Modifier
              .size(56.dp)
              .testTag("btn_toggle_audio_play")
          ) {
            Icon(
              imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
              contentDescription = if (isPlaying) "Jeda" else "Putar",
              tint = Color.White,
              modifier = Modifier.size(32.dp)
            )
          }

          // Recitation test button
          Button(
            onClick = onTestRecitation,
            colors = ButtonDefaults.buttonColors(containerColor = AmberGold),
            shape = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
            modifier = Modifier.testTag("btn_recitation_check")
          ) {
            Icon(Icons.Default.Mic, contentDescription = null, tint = Color(0xFF451A03), modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text("Cek Suara", color = Color(0xFF451A03), fontSize = 12.sp, fontWeight = FontWeight.Bold)
          }
        }

        // Recitation Feedback Banner
        AnimatedVisibility(visible = recitationFeedback != null) {
          recitationFeedback?.let { feedback ->
            Surface(
              shape = RoundedCornerShape(12.dp),
              color = Color(0xFFF0FDF4),
              border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF86EFAC)),
              modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp)
            ) {
              Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Icon(
                  imageVector = Icons.Default.CheckCircle,
                  contentDescription = null,
                  tint = EmeraldPrimary,
                  modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                  text = feedback,
                  style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = EmeraldDark
                  )
                )
              }
            }
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    Text(
      text = "Daftar Ayat & Terjemahan:",
      style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold, color = TextPrimary)
    )

    Spacer(modifier = Modifier.height(8.dp))

    // Ayah List
    LazyColumn(
      verticalArrangement = Arrangement.spacedBy(10.dp),
      modifier = Modifier.fillMaxSize()
    ) {
      itemsIndexed(activeSurah.verses) { index, verse ->
        val isCurrentlyPlaying = isPlaying && activeAyahIndex == index

        Card(
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(
            containerColor = if (isCurrentlyPlaying) Color(0xFFF0FDF4) else Color.White
          ),
          border = if (isCurrentlyPlaying) androidx.compose.foundation.BorderStroke(1.5.dp, EmeraldPrimary) else null,
          elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(16.dp)) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Surface(
                shape = CircleShape,
                color = if (isCurrentlyPlaying) EmeraldPrimary else SoftMintBg,
                modifier = Modifier.size(30.dp)
              ) {
                Box(contentAlignment = Alignment.Center) {
                  Text(
                    text = "${verse.number}",
                    style = MaterialTheme.typography.labelSmall.copy(
                      fontWeight = FontWeight.Bold,
                      color = if (isCurrentlyPlaying) Color.White else EmeraldDark
                    )
                  )
                }
              }

              if (isCurrentlyPlaying) {
                Text(
                  text = "🔊 Sedang Berputar",
                  style = MaterialTheme.typography.labelSmall.copy(
                    color = EmeraldPrimary,
                    fontWeight = FontWeight.Bold
                  )
                )
              }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Arabic text
            Text(
              text = verse.arabic,
              style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                lineHeight = 38.sp
              ),
              textAlign = TextAlign.End,
              modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Transliteration
            Text(
              text = verse.transliteration,
              style = MaterialTheme.typography.bodySmall.copy(
                color = EmeraldDark,
                fontWeight = FontWeight.Medium,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
              )
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Translation
            Text(
              text = verse.translation,
              style = MaterialTheme.typography.bodySmall.copy(
                color = TextSecondary,
                lineHeight = 18.sp
              )
            )
          }
        }
      }
    }
  }
}
