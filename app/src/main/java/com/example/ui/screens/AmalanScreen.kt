package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.DailyAmalan
import com.example.model.UserProgress
import com.example.ui.theme.*

@Composable
fun AmalanScreen(
  amalanList: List<DailyAmalan>,
  userProgress: UserProgress,
  onToggleAmalan: (String) -> Unit,
  onBack: () -> Unit,
  modifier: Modifier = Modifier
) {
  BackHandler { onBack() }

  var selectedFilter by remember { mutableStateOf("Semua") }
  val filterCategories = listOf("Semua", "Salat Wajib", "Sunnah", "Al-Qur'an", "Akhlak", "Birrul Walidain")

  val filteredList = if (selectedFilter == "Semua") {
    amalanList
  } else {
    amalanList.filter { it.category == selectedFilter }
  }

  val completedCount = amalanList.count { it.isCompleted }
  val progressPercent = (completedCount.toFloat() / amalanList.size.toFloat()).coerceIn(0f, 1f)

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
      IconButton(onClick = onBack, modifier = Modifier.testTag("btn_back_amalan")) {
        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
      }
      Spacer(modifier = Modifier.width(4.dp))
      Column {
        Text(
          text = "Tracker Amalan & Ibadah",
          style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.Bold,
            color = TextPrimary
          )
        )
        Text(
          text = "Ibadah harian santai & penuh kehangatan",
          style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
        )
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    // Friendly Motivation & Streak Banner
    Card(
      shape = RoundedCornerShape(18.dp),
      colors = CardDefaults.cardColors(containerColor = Color.White),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(16.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
              shape = CircleShape,
              color = Color(0xFFFEF3C7),
              modifier = Modifier.size(36.dp)
            ) {
              Box(contentAlignment = Alignment.Center) {
                Icon(
                  imageVector = Icons.Default.LocalFireDepartment,
                  contentDescription = null,
                  tint = Color(0xFFD97706),
                  modifier = Modifier.size(20.dp)
                )
              }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
              Text(
                text = "${userProgress.streakDays} Hari Istiqomah",
                style = MaterialTheme.typography.titleSmall.copy(
                  fontWeight = FontWeight.Bold,
                  color = Color(0xFF92400E)
                )
              )
              Text(
                text = "Terus jaga kebiasaan baikmu!",
                style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary)
              )
            }
          }

          Text(
            text = "$completedCount/${amalanList.size} Selesai",
            style = MaterialTheme.typography.titleSmall.copy(
              fontWeight = FontWeight.Bold,
              color = EmeraldPrimary
            )
          )
        }

        Spacer(modifier = Modifier.height(10.dp))

        LinearProgressIndicator(
          progress = { progressPercent },
          modifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
            .clip(RoundedCornerShape(4.dp)),
          color = EmeraldPrimary,
          trackColor = EmeraldContainer
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
          text = "\"Amalan yang paling dicintai Allah adalah yang terus-menerus meskipun sedikit.\" (HR. Muslim)",
          style = MaterialTheme.typography.bodySmall.copy(
            color = TextSecondary,
            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
          )
        )
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    // Filter Chips
    LazyRow(
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      items(filterCategories) { category ->
        val isSelected = category == selectedFilter
        FilterChip(
          selected = isSelected,
          onClick = { selectedFilter = category },
          label = {
            Text(
              text = category,
              fontSize = 12.sp,
              fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
            )
          },
          colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = EmeraldPrimary,
            selectedLabelColor = Color.White,
            containerColor = Color.White,
            labelColor = TextPrimary
          ),
          border = null,
          shape = RoundedCornerShape(14.dp)
        )
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    // Checklist Items
    LazyColumn(
      verticalArrangement = Arrangement.spacedBy(10.dp),
      modifier = Modifier.fillMaxSize()
    ) {
      items(filteredList) { amalan ->
        Card(
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(
            containerColor = if (amalan.isCompleted) Color(0xFFF0FDF4) else Color.White
          ),
          border = if (amalan.isCompleted) androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF86EFAC)) else null,
          elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
          modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggleAmalan(amalan.id) }
            .testTag("amalan_item_${amalan.id}")
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Checkbox(
              checked = amalan.isCompleted,
              onCheckedChange = { onToggleAmalan(amalan.id) },
              colors = CheckboxDefaults.colors(
                checkedColor = EmeraldPrimary,
                uncheckedColor = Color.Gray
              )
            )

            Spacer(modifier = Modifier.width(10.dp))

            Surface(
              shape = CircleShape,
              color = SoftMintBg,
              modifier = Modifier.size(38.dp)
            ) {
              Box(contentAlignment = Alignment.Center) {
                Text(text = amalan.iconEmoji, fontSize = 20.sp)
              }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
              Text(
                text = amalan.title,
                style = MaterialTheme.typography.bodyMedium.copy(
                  fontWeight = FontWeight.Bold,
                  color = if (amalan.isCompleted) EmeraldDark else TextPrimary
                )
              )
              Spacer(modifier = Modifier.height(2.dp))
              Text(
                text = amalan.description,
                style = MaterialTheme.typography.bodySmall.copy(
                  color = TextSecondary,
                  fontSize = 11.sp
                )
              )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Surface(
              shape = RoundedCornerShape(8.dp),
              color = if (amalan.isCompleted) Color(0xFFDCFCE7) else AmberGoldLight.copy(alpha = 0.5f)
            ) {
              Text(
                text = "+${amalan.xpReward} XP",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontWeight = FontWeight.Bold,
                  color = if (amalan.isCompleted) EmeraldDark else AmberGoldDark
                ),
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
              )
            }
          }
        }
      }
    }
  }
}
