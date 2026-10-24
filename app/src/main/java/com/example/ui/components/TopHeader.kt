package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.EducationLevel
import com.example.model.UserProgress
import com.example.ui.theme.*

@Composable
fun TopHeader(
  userProgress: UserProgress,
  selectedGrade: EducationLevel,
  onGradeChange: (EducationLevel) -> Unit,
  celebrationNotice: String?,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .background(
        brush = Brush.verticalGradient(
          colors = listOf(EmeraldDark, EmeraldPrimary)
        )
      )
      .statusBarsPadding()
      .padding(horizontal = 16.dp, vertical = 12.dp)
  ) {
    // Row 1: App Title & Offline Mode Pill
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Surface(
          shape = CircleShape,
          color = AmberGold,
          modifier = Modifier.size(34.dp)
        ) {
          Box(contentAlignment = Alignment.Center) {
            Text(text = "🌙", fontSize = 18.sp)
          }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column {
          Text(
            text = "Sahabat PAI",
            style = MaterialTheme.typography.titleLarge.copy(
              fontWeight = FontWeight.Bold,
              color = Color.White
            )
          )
          Text(
            text = "Pendidikan Agama Islam Interaktif",
            style = MaterialTheme.typography.labelSmall.copy(
              color = Color.White.copy(alpha = 0.85f)
            )
          )
        }
      }

      // Offline badge
      Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color.White.copy(alpha = 0.2f)
      ) {
        Row(
          modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Box(
            modifier = Modifier
              .size(8.dp)
              .clip(CircleShape)
              .background(Color(0xFF34D399))
          )
          Spacer(modifier = Modifier.width(4.dp))
          Text(
            text = "Offline Mode",
            style = MaterialTheme.typography.labelSmall.copy(
              color = Color.White,
              fontWeight = FontWeight.Medium
            )
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(10.dp))

    // Row 2: Gamification Stats (Streak, Points, Level)
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      // Streak Pill
      Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFFFEF3C7),
        modifier = Modifier.weight(1f)
      ) {
        Row(
          modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center
        ) {
          Icon(
            imageVector = Icons.Default.LocalFireDepartment,
            contentDescription = "Streak",
            tint = Color(0xFFD97706),
            modifier = Modifier.size(18.dp)
          )
          Spacer(modifier = Modifier.width(4.dp))
          Text(
            text = "${userProgress.streakDays} Hari Streak",
            style = MaterialTheme.typography.labelMedium.copy(
              fontWeight = FontWeight.Bold,
              color = Color(0xFF92400E)
            )
          )
        }
      }

      // Points Pill
      Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFFE0F2FE),
        modifier = Modifier.weight(1f)
      ) {
        Row(
          modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center
        ) {
          Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "Poin",
            tint = Color(0xFF0284C7),
            modifier = Modifier.size(18.dp)
          )
          Spacer(modifier = Modifier.width(4.dp))
          Text(
            text = "${userProgress.points} Poin",
            style = MaterialTheme.typography.labelMedium.copy(
              fontWeight = FontWeight.Bold,
              color = Color(0xFF075985)
            )
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(8.dp))

    // Level XP Bar
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(10.dp))
        .background(Color.White.copy(alpha = 0.15f))
        .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "Level ${userProgress.currentLevel}: ${userProgress.levelTitle}",
          style = MaterialTheme.typography.labelSmall.copy(
            color = Color.White,
            fontWeight = FontWeight.Bold
          )
        )
        Text(
          text = "${userProgress.xp} / ${userProgress.xpForNextLevel} XP",
          style = MaterialTheme.typography.labelSmall.copy(
            color = AmberGoldLight,
            fontWeight = FontWeight.SemiBold
          )
        )
      }
      Spacer(modifier = Modifier.height(4.dp))
      val progress = (userProgress.xp.toFloat() / userProgress.xpForNextLevel.toFloat()).coerceIn(0f, 1f)
      LinearProgressIndicator(
        progress = { progress },
        modifier = Modifier
          .fillMaxWidth()
          .height(6.dp)
          .clip(RoundedCornerShape(3.dp)),
        color = AmberGold,
        trackColor = Color.White.copy(alpha = 0.25f)
      )
    }

    Spacer(modifier = Modifier.height(8.dp))

    // Row 3: Target Grade Selector Chips (SD, SMP, SMA)
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = "Tingkat:",
        style = MaterialTheme.typography.labelSmall.copy(
          color = Color.White.copy(alpha = 0.9f),
          fontWeight = FontWeight.Medium
        )
      )
      EducationLevel.entries.forEach { level ->
        val isSelected = level == selectedGrade
        FilterChip(
          selected = isSelected,
          onClick = { onGradeChange(level) },
          label = {
            Text(
              text = level.label,
              fontSize = 11.sp,
              fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
            )
          },
          colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = AmberGold,
            selectedLabelColor = Color(0xFF451A03),
            containerColor = Color.White.copy(alpha = 0.2f),
            labelColor = Color.White
          ),
          border = null,
          shape = RoundedCornerShape(16.dp),
          modifier = Modifier.testTag("grade_chip_${level.name.lowercase()}")
        )
      }
    }

    // Celebration notice banner if triggered
    AnimatedVisibility(
      visible = celebrationNotice != null,
      enter = fadeIn(),
      exit = fadeOut()
    ) {
      celebrationNotice?.let { notice ->
        Surface(
          shape = RoundedCornerShape(10.dp),
          color = Color(0xFF10B981),
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
        ) {
          Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Icon(
              imageVector = Icons.Default.CheckCircle,
              contentDescription = null,
              tint = Color.White,
              modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = notice,
              style = MaterialTheme.typography.labelMedium.copy(
                color = Color.White,
                fontWeight = FontWeight.Bold
              )
            )
          }
        }
      }
    }
  }
}
