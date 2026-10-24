package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AutoAwesome
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
import com.example.model.ChatMessage
import com.example.ui.theme.*

@Composable
fun AiMentorScreen(
  messages: List<ChatMessage>,
  isThinking: Boolean,
  onSendMessage: (String) -> Unit,
  onBack: () -> Unit,
  modifier: Modifier = Modifier
) {
  BackHandler { onBack() }

  var inputText by remember { mutableStateOf("") }
  val listState = rememberLazyListState()

  val promptChips = listOf(
    "Kenapa kita harus berwudhu?",
    "Apa beda Idgham Bighunnah & Bilaghunnah?",
    "Tuliskan doa untuk kedua orang tua",
    "Ceritakan kesabaran Nabi Nuh AS",
    "Bagaimana senyum bisa bernilai sedekah?"
  )

  LaunchedEffect(messages.size) {
    if (messages.isNotEmpty()) {
      listState.animateScrollToItem(messages.size - 1)
    }
  }

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
      IconButton(onClick = onBack, modifier = Modifier.testTag("btn_back_ai_mentor")) {
        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
      }
      Surface(
        shape = CircleShape,
        color = AmberGold,
        modifier = Modifier.size(38.dp)
      ) {
        Box(contentAlignment = Alignment.Center) {
          Text(text = "🤖", fontSize = 20.sp)
        }
      }
      Spacer(modifier = Modifier.width(10.dp))
      Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(
            text = "Kak Farhan",
            style = MaterialTheme.typography.titleMedium.copy(
              fontWeight = FontWeight.Bold,
              color = TextPrimary
            )
          )
          Spacer(modifier = Modifier.width(6.dp))
          Surface(
            shape = RoundedCornerShape(8.dp),
            color = Color(0xFFDCFCE7)
          ) {
            Text(
              text = "AI Mentor PAI",
              style = MaterialTheme.typography.labelSmall.copy(
                color = Color(0xFF166534),
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp
              ),
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }
        }
        Text(
          text = "Asisten ramah anak siap menjawab pertanyaanmu",
          style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
        )
      }
    }

    Spacer(modifier = Modifier.height(10.dp))

    // Suggested Prompt Chips
    LazyRow(
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      items(promptChips) { chip ->
        Surface(
          shape = RoundedCornerShape(16.dp),
          color = Color.White,
          border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
          modifier = Modifier.clickable {
            onSendMessage(chip)
          }
        ) {
          Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Icon(
              imageVector = Icons.Default.AutoAwesome,
              contentDescription = null,
              tint = AmberGold,
              modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = chip,
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Medium,
                color = TextPrimary
              )
            )
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(10.dp))

    // Message List
    LazyColumn(
      state = listState,
      modifier = Modifier
        .fillMaxWidth()
        .weight(1f),
      verticalArrangement = Arrangement.spacedBy(10.dp),
      contentPadding = PaddingValues(vertical = 8.dp)
    ) {
      items(messages) { message ->
        val isUser = message.isUser
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
        ) {
          if (!isUser) {
            Surface(
              shape = CircleShape,
              color = EmeraldContainer,
              modifier = Modifier
                .size(32.dp)
                .align(Alignment.Bottom)
            ) {
              Box(contentAlignment = Alignment.Center) {
                Text(text = "🤖", fontSize = 16.sp)
              }
            }
            Spacer(modifier = Modifier.width(8.dp))
          }

          Card(
            shape = RoundedCornerShape(
              topStart = 18.dp,
              topEnd = 18.dp,
              bottomStart = if (isUser) 18.dp else 4.dp,
              bottomEnd = if (isUser) 4.dp else 18.dp
            ),
            colors = CardDefaults.cardColors(
              containerColor = if (isUser) EmeraldPrimary else Color.White
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
            modifier = Modifier.widthIn(max = 280.dp)
          ) {
            Column(modifier = Modifier.padding(12.dp)) {
              Text(
                text = message.text,
                style = MaterialTheme.typography.bodyMedium.copy(
                  color = if (isUser) Color.White else TextPrimary,
                  lineHeight = 20.sp
                )
              )
            }
          }
        }
      }

      if (isThinking) {
        item {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
              shape = CircleShape,
              color = EmeraldContainer,
              modifier = Modifier.size(32.dp)
            ) {
              Box(contentAlignment = Alignment.Center) {
                Text(text = "🤖", fontSize = 16.sp)
              }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Surface(
              shape = RoundedCornerShape(14.dp),
              color = Color.White,
              modifier = Modifier.padding(8.dp)
            ) {
              Text(
                text = "Kak Farhan sedang menulis jawaban... ✍️",
                style = MaterialTheme.typography.bodySmall.copy(
                  color = TextSecondary,
                  fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                ),
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
              )
            }
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(8.dp))

    // Input Bar
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(24.dp))
        .background(Color.White)
        .padding(horizontal = 8.dp, vertical = 4.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      TextField(
        value = inputText,
        onValueChange = { inputText = it },
        placeholder = { Text("Tanyakan apa saja seputar PAI...") },
        colors = TextFieldDefaults.colors(
          focusedContainerColor = Color.Transparent,
          unfocusedContainerColor = Color.Transparent,
          focusedIndicatorColor = Color.Transparent,
          unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = Modifier
          .weight(1f)
          .testTag("input_ai_message")
      )

      IconButton(
        onClick = {
          if (inputText.isNotBlank()) {
            val textToSend = inputText
            inputText = ""
            onSendMessage(textToSend)
          }
        },
        enabled = inputText.isNotBlank(),
        modifier = Modifier.testTag("btn_send_ai_message")
      ) {
        Icon(
          imageVector = Icons.AutoMirrored.Filled.Send,
          contentDescription = "Kirim",
          tint = if (inputText.isNotBlank()) EmeraldPrimary else Color.Gray
        )
      }
    }
  }
}
