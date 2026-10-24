package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.DailyAmalan
import com.example.model.EducationLevel
import com.example.model.UserProgress
import com.example.ui.theme.*
import com.example.viewmodel.PaiScreen

@Composable
fun DashboardScreen(
  userProgress: UserProgress,
  selectedGrade: EducationLevel,
  amalanList: List<DailyAmalan>,
  onNavigate: (PaiScreen) -> Unit,
  onToggleAmalan: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .padding(horizontal = 16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp),
    contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp)
  ) {
    // 1. Hero Card with generated visual artwork
    item {
      Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column {
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .height(160.dp)
          ) {
            Image(
              painter = painterResource(id = R.drawable.img_hero_banner),
              contentDescription = "Suasana Belajar PAI Ceria",
              contentScale = ContentScale.Crop,
              modifier = Modifier.fillMaxSize()
            )
            // Gradient Overlay for smooth text readability
            Box(
              modifier = Modifier
                .fillMaxSize()
                .background(
                  Brush.verticalGradient(
                    colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f))
                  )
                )
            )
            // Chip label
            Surface(
              shape = RoundedCornerShape(12.dp),
              color = AmberGold,
              modifier = Modifier
                .align(Alignment.TopStart)
                .padding(12.dp)
            ) {
              Text(
                text = "${selectedGrade.label} • Kurikulum Merdeka",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontWeight = FontWeight.Bold,
                  color = Color(0xFF451A03)
                ),
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
              )
            }
            // Welcome text inside hero
            Column(
              modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(14.dp)
            ) {
              Text(
                text = "Ahlan wa Sahlan, Sahabat!",
                style = MaterialTheme.typography.titleMedium.copy(
                  fontWeight = FontWeight.Bold,
                  color = Color.White
                )
              )
              Text(
                text = "Belajar Islam jadi seru, interaktif & berhadiah XP!",
                style = MaterialTheme.typography.bodySmall.copy(
                  color = Color.White.copy(alpha = 0.9f)
                )
              )
            }
          }

          // Quick motivator bar below hero
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .background(EmeraldContainer.copy(alpha = 0.4f))
              .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(text = "💡", fontSize = 18.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "\"Sebaik-baik kalian adalah yang mempelajari Al-Qur'an dan mengajarkannya.\" (HR. Bukhari)",
              style = MaterialTheme.typography.bodySmall.copy(
                color = OnEmeraldContainer,
                fontWeight = FontWeight.Medium
              )
            )
          }
        }
      }
    }

    // 2. Section Header: Menu Utama Pembelajaran
    item {
      Text(
        text = "Pilih Petualangan Belajarmu",
        style = MaterialTheme.typography.titleMedium.copy(
          fontWeight = FontWeight.Bold,
          color = TextPrimary
        )
      )
    }

    // 3. Feature Grid Buttons
    item {
      Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        // Feature 1: Interactive Storytelling
        FeatureMenuCard(
          iconEmoji = "📜",
          title = "Kisah Nabi & Sahabat",
          subtitle = "Interactive Storytelling dengan visual dan hikmah",
          badgeText = "3 Kisah Tersedia",
          badgeColor = Color(0xFFE0F2FE),
          badgeTextColor = Color(0xFF0369A1),
          onClick = { onNavigate(PaiScreen.StoriesList) },
          testTag = "menu_stories"
        )

        // Feature 2: Mini Games PAI
        FeatureMenuCard(
          iconEmoji = "🎮",
          title = "Mini Games Edukasi PAI",
          subtitle = "Kuis Cepat, Susun Urutan Wudhu, Flashcard Tajwid",
          badgeText = "3 Mini Games",
          badgeColor = Color(0xFFFEF3C7),
          badgeTextColor = Color(0xFF92400E),
          onClick = { onNavigate(PaiScreen.MiniGamesHub) },
          testTag = "menu_mini_games"
        )

        // Feature 3: Tracker Ibadah & Amalan
        FeatureMenuCard(
          iconEmoji = "🕌",
          title = "Tracker Amalan & Ibadah",
          subtitle = "Checklist ibadah harian santai tanpa menggurui",
          badgeText = "Streak Aktif 🔥",
          badgeColor = Color(0xFFDCFCE7),
          badgeTextColor = Color(0xFF166534),
          onClick = { onNavigate(PaiScreen.AmalanTracker) },
          testTag = "menu_amalan"
        )

        // Feature 4: Audio Murottal & Doa
        FeatureMenuCard(
          iconEmoji = "🎧",
          title = "Murottal & Cek Pelafalan",
          subtitle = "Audio tartil interaktif & latihan suara surah pendek",
          badgeText = "Audio Jernih",
          badgeColor = Color(0xFFF3E8FF),
          badgeTextColor = Color(0xFF7E22CE),
          onClick = { onNavigate(PaiScreen.AudioMurottal) },
          testTag = "menu_audio"
        )

        // Feature 5: AI Mentor (Kak Farhan)
        FeatureMenuCard(
          iconEmoji = "🤖",
          title = "Tanya Kak Farhan (Asisten AI)",
          subtitle = "Tanya jawab seputar ibadah, akhlak & fiqih ramah anak",
          badgeText = "Smart AI",
          badgeColor = Color(0xFFFFEDD5),
          badgeTextColor = Color(0xFFC2410C),
          onClick = { onNavigate(PaiScreen.AiMentor) },
          testTag = "menu_ai_mentor"
        )
      }
    }

    // 4. Daily Amalan Preview
    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = "Amalan Hari Ini",
            style = MaterialTheme.typography.titleMedium.copy(
              fontWeight = FontWeight.Bold,
              color = TextPrimary
            )
          )
          val completedCount = amalanList.count { it.isCompleted }
          Text(
            text = "$completedCount dari ${amalanList.size} amalan selesai",
            style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
          )
        }
        TextButton(
          onClick = { onNavigate(PaiScreen.AmalanTracker) },
          modifier = Modifier.testTag("see_all_amalan")
        ) {
          Text("Lihat Semua", color = EmeraldPrimary, fontWeight = FontWeight.Bold)
        }
      }
    }

    // Quick Amalan Items (3 items)
    items(amalanList.take(3)) { amalan ->
      Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggleAmalan(amalan.id) }
            .padding(14.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Checkbox(
            checked = amalan.isCompleted,
            onCheckedChange = { onToggleAmalan(amalan.id) },
            colors = CheckboxDefaults.colors(
              checkedColor = EmeraldPrimary,
              uncheckedColor = Color.Gray
            ),
            modifier = Modifier.testTag("checkbox_${amalan.id}")
          )
          Spacer(modifier = Modifier.width(8.dp))
          Column(modifier = Modifier.weight(1f)) {
            Text(
              text = "${amalan.iconEmoji} ${amalan.title}",
              style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.SemiBold,
                color = if (amalan.isCompleted) Color.Gray else TextPrimary
              )
            )
            Text(
              text = amalan.category,
              style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary)
            )
          }
          Surface(
            shape = RoundedCornerShape(8.dp),
            color = AmberGoldLight.copy(alpha = 0.5f)
          ) {
            Text(
              text = "+${amalan.xpReward} XP",
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                color = AmberGoldDark
              ),
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
          }
        }
      }
    }

    // 5. Badges & Pencapaian Shelf
    item {
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = "Koleksi Badge & Prestasi",
        style = MaterialTheme.typography.titleMedium.copy(
          fontWeight = FontWeight.Bold,
          color = TextPrimary
        )
      )
    }

    item {
      LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        items(userProgress.badges) { badge ->
          Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
              containerColor = if (badge.isUnlocked) Color.White else Color(0xFFF3F4F6)
            ),
            border = if (badge.isUnlocked) null else androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
            elevation = CardDefaults.cardElevation(defaultElevation = if (badge.isUnlocked) 2.dp else 0.dp),
            modifier = Modifier.width(135.dp)
          ) {
            Column(
              modifier = Modifier.padding(12.dp),
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              Surface(
                shape = CircleShape,
                color = if (badge.isUnlocked) AmberGoldContainer else Color(0xFFE5E7EB),
                modifier = Modifier.size(46.dp)
              ) {
                Box(contentAlignment = Alignment.Center) {
                  Text(
                    text = badge.iconEmoji,
                    fontSize = 24.sp
                  )
                }
              }
              Spacer(modifier = Modifier.height(8.dp))
              Text(
                text = badge.title,
                style = MaterialTheme.typography.labelMedium.copy(
                  fontWeight = FontWeight.Bold,
                  color = if (badge.isUnlocked) TextPrimary else Color.Gray
                ),
                maxLines = 1
              )
              Text(
                text = if (badge.isUnlocked) "Terbuka ✨" else "Terkunci",
                style = MaterialTheme.typography.labelSmall.copy(
                  color = if (badge.isUnlocked) EmeraldPrimary else Color.Gray,
                  fontWeight = FontWeight.Medium
                )
              )
            }
          }
        }
      }
    }
  }
}

@Composable
fun FeatureMenuCard(
  iconEmoji: String,
  title: String,
  subtitle: String,
  badgeText: String,
  badgeColor: Color,
  badgeTextColor: Color,
  onClick: () -> Unit,
  testTag: String
) {
  Card(
    shape = RoundedCornerShape(18.dp),
    colors = CardDefaults.cardColors(containerColor = Color.White),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    modifier = Modifier
      .fillMaxWidth()
      .testTag(testTag)
      .clickable { onClick() }
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Surface(
        shape = RoundedCornerShape(14.dp),
        color = SoftMintBg,
        modifier = Modifier.size(52.dp)
      ) {
        Box(contentAlignment = Alignment.Center) {
          Text(text = iconEmoji, fontSize = 28.sp)
        }
      }
      Spacer(modifier = Modifier.width(14.dp))
      Column(modifier = Modifier.weight(1f)) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Text(
            text = title,
            style = MaterialTheme.typography.titleSmall.copy(
              fontWeight = FontWeight.Bold,
              color = TextPrimary
            )
          )
          Surface(
            shape = RoundedCornerShape(6.dp),
            color = badgeColor
          ) {
            Text(
              text = badgeText,
              style = MaterialTheme.typography.labelSmall.copy(
                color = badgeTextColor,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp
              ),
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }
        }
        Spacer(modifier = Modifier.height(2.dp))
        Text(
          text = subtitle,
          style = MaterialTheme.typography.bodySmall.copy(
            color = TextSecondary,
            fontSize = 12.sp
          ),
          maxLines = 2
        )
      }
      Icon(
        imageVector = Icons.Default.ChevronRight,
        contentDescription = "Buka",
        tint = Color.Gray,
        modifier = Modifier.size(24.dp)
      )
    }
  }
}
