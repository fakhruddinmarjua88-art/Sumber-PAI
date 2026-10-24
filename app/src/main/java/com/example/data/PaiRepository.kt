package com.example.data

import com.example.model.*

object PaiRepository {

  val stories: List<Story> = listOf(
    Story(
      id = "s1",
      title = "Nabi Nuh & Bahtera Penyelamat",
      prophet = "Nabi Nuh 'Alaihissalam",
      summary = "Kisah kesabaran mendakwahkan kebaikan selama 950 tahun dan perintah mukjizat membuat bahtera besar di atas bukit.",
      targetLevel = EducationLevel.SD,
      readingMinutes = 4,
      slides = listOf(
        StorySlide(
          page = 1,
          title = "Dakwah Penuh Kesabaran",
          text = "Nabi Nuh AS adalah salah satu nabi Ulul Azmi yang terkenal dengan kesabarannya yang luar biasa. Selama ratusan tahun, beliau tak kenal lelah mengajak kaumnya menyembah Allah SWT dengan tutur kata yang santun dan penuh kasih sayang.",
          audioText = "Nabi Nuh mengajak kaumnya dengan sabar dan santun menuju jalan kebaikan."
        ),
        StorySlide(
          page = 2,
          title = "Perintah Membangun Bahtera",
          text = "Meskipun sering diejek karena membangun kapal raksasa di atas bukit tandus yang jauh dari lautan, Nabi Nuh AS tetap teguh menjalankan wahyu Allah bersama segelintir pengikut setia yang beriman.",
          audioText = "Nabi Nuh tetap bertekad menyelesaikan bahtera meski banyak yang mencemooh."
        ),
        StorySlide(
          page = 3,
          title = "Gelombang Besar & Keselamatan",
          text = "Ketika air memancar dari bumi dan langit mencurahkan hujan lebat, bahtera Nabi Nuh membawa orang-orang beriman dan sepasang hewan dari setiap jenis berlayar dengan aman di atas ombak setinggi gunung.",
          audioText = "Dengan izin Allah, bahtera berlayar selamat diiringi kedamaian."
        )
      ),
      reflectionQuestion = "Apa nilai keteladanan utama yang bisa kita ambil dari perjuangan Nabi Nuh AS?",
      reflectionOptions = listOf(
        "Mudah menyerah jika orang lain menolak",
        "Sabar, istiqomah, dan teguh pada perintah Allah SWT",
        "Membangun kapal untuk berlibur",
        "Membalas ejekan dengan kemarahan"
      ),
      correctOptionIndex = 1,
      hikmah = "Kesabaran dan keimanan yang kokoh akan selalu mendatangkan pertolongan Allah, meski menghadapi rintangan yang tampak mustahil."
    ),
    Story(
      id = "s2",
      title = "Nabi Ibrahim & Pencarian Kebenaran",
      prophet = "Nabi Ibrahim 'Alaihissalam",
      summary = "Perjalanan akal dan hati Nabi Ibrahim menemukan Sang Pencipta sejati dan teladan ketabahan menghadapi api Raja Namrud.",
      targetLevel = EducationLevel.SMP,
      readingMinutes = 5,
      slides = listOf(
        StorySlide(
          page = 1,
          title = "Tafakur Mengamati Alam Semesta",
          text = "Nabi Ibrahim AS menggunakan akal pikiran yang jernih untuk merenungkan kebesaran alam. Ketika melihat bintang, bulan, dan matahari terbenam, beliau sadar bahwa benda yang tenggelam bukanlah Tuhan, melainkan ciptaan Allah Yang Maha Esa.",
          audioText = "Nabi Ibrahim bertafakur mengamati keindahan ciptaan Allah di langit."
        ),
        StorySlide(
          page = 2,
          title = "Mukjizat Dinginnya Kobaran Api",
          text = "Ketika Namrud melemparkannya ke dalam api yang menyala dahsyat, Allah berfirman: 'Wahai api! Jadilah dingin dan penyelamat bagi Ibrahim!' Api pun menjadi sejuk dan beraroma harum taman surga.",
          audioText = "Kobaran api menjadi sejuk dan damai melindungi hamba yang bertawakal."
        )
      ),
      reflectionQuestion = "Mengapa Nabi Ibrahim menolak menganggap matahari atau bulan sebagai Tuhan?",
      reflectionOptions = listOf(
        "Karena ukurannya terlalu kecil",
        "Karena benda langit terbenam dan merupakan ciptaan, bukan Pencipta",
        "Karena cuaca mendung",
        "Karena diperintah raja"
      ),
      correctOptionIndex = 1,
      hikmah = "Gunakan akal pikiran untuk selalu bertafakur mengagumi kebesaran Allah dan bersikap kritis mencari kebenaran."
    ),
    Story(
      id = "s3",
      title = "Kejujuran Sahabat Abu Bakar Ash-Shiddiq",
      prophet = "Abu Bakar Ash-Shiddiq RA",
      summary = "Keteguhan membenarkan peristiwa Isra Mi'raj Nabi Muhammad SAW dengan keimanan tanpa keraguan sedikit pun.",
      targetLevel = EducationLevel.SMA,
      readingMinutes = 4,
      slides = listOf(
        StorySlide(
          page = 1,
          title = "Gelar Ash-Shiddiq Yang Abadi",
          text = "Ketika orang-orang Quraisy meragukan perjalanan malam Rasulullah SAW dari Masjidil Haram ke Masjidil Aqsa lalu ke Sidratul Muntaha, Abu Bakar dengan mantap berkata: 'Jika beliau yang mengatakannya, maka sungguh itu adalah kebenaran!'",
          audioText = "Abu Bakar membenarkan kejujuran Rasulullah tanpa ragu sedetik pun."
        )
      ),
      reflectionQuestion = "Mengapa Abu Bakar diberi gelar 'Ash-Shiddiq'?",
      reflectionOptions = listOf(
        "Karena kaya raya",
        "Karena selalu membenarkan dan mempercayai kejujuran Rasulullah SAW",
        "Karena ahli berdagang",
        "Karena suka berkelana"
      ),
      correctOptionIndex = 1,
      hikmah = "Kejujuran dan integritas menghasilkan persahabatan sejati dan keyakinan hati yang tak tergoyahkan oleh opini miring."
    )
  )

  val wuduStepsMaster: List<WuduStep> = listOf(
    WuduStep(1, "Niat & Membasuh Telapak Tangan", "Bismillah", "Membasuh kedua telapak tangan hingga pergelangan 3 kali sambil berniat di dalam hati.", 1),
    WuduStep(2, "Berkumur-kumur", "Madhmadhah", "Berkumur-kumur membersihkan rongga mulut sebanyak 3 kali.", 2),
    WuduStep(3, "Memasukkan Air ke Hidung", "Istinsyaq", "Menghirup air ke hidung lalu mengeluarkannya sebanyak 3 kali.", 3),
    WuduStep(4, "Membasuh Wajah", "Ghaslul Wajh", "Membasuh seluruh wajah dari batas tumbuhnya rambut kepala hingga dagu sebanyak 3 kali.", 4),
    WuduStep(5, "Membasuh Kedua Tangan hingga Siku", "Ghaslul Yadain", "Membasuh tangan kanan lalu tangan kiri sampai melewati kedua siku sebanyak 3 kali.", 5),
    WuduStep(6, "Mengusap Sebagian Kepala/Rambut", "Mas-hur Ra's", "Mengusap sebagian kepala atau rambut dengan air bersih.", 6),
    WuduStep(7, "Membasuh Kedua Telinga", "Mas-hul Udzunain", "Mengusap bagian dalam dan luar kedua telinga menggunakan jari telunjuk dan ibu jari.", 7),
    WuduStep(8, "Membasuh Kedua Kaki hingga Mata Kaki", "Ghaslur Rijlain", "Membasuh kaki kanan lalu kiri sampai ke atas kedua mata kaki sambil menyela-nyela jari kaki.", 8),
    WuduStep(9, "Tertib & Membaca Doa Setelah Wudhu", "Ad-Du'a", "Menjalankan secara berurutan dan membaca doa menghadap kiblat.", 9)
  )

  val tajwidFlashcards: List<TajwidCard> = listOf(
    TajwidCard(
      id = "t1",
      hukum = "Idzhar Halqi",
      arti = "Jelas / Terang tanpa dengung",
      huruf = "ء , هـ , ع , ح , غ , خ",
      contohArab = "مَنْ آمَنَ  /  مِنْ خَوْفٍ",
      caraBaca = "Man Aamana / Min Khauf (Nun mati dibaca jelas murni)",
      penjelasan = "Terjadi jika Nun Sukun (نْ) atau Tanwin (ـًـٍـٌ) bertemu salah satu dari 6 huruf halqi (tenggorokan)."
    ),
    TajwidCard(
      id = "t2",
      hukum = "Idgham Bighunnah",
      arti = "Melebur disertai Dengung 2 harakat",
      huruf = "ي , ن , م , و  (Ya, Nun, Mim, Waw)",
      contohArab = "مَنْ يَقُولُ  /  مِنْ مَالٍ",
      caraBaca = "May yaquulu / Mim maalin (Suara nun melebur ke huruf berikutnya sambil mendengung)",
      penjelasan = "Terjadi jika Nun Sukun atau Tanwin bertemu huruf Ya, Nun, Mim, atau Waw. Harus berdengung lembut."
    ),
    TajwidCard(
      id = "t3",
      hukum = "Idgham Bilaghunnah",
      arti = "Melebur TANPA Dengung",
      huruf = "ل , ر  (Lam, Ra)",
      contohArab = "مِنْ رَبِّهِمْ  /  مِنْ لَدُنْهُ",
      caraBaca = "Mir rabbihim / Mil ladunhu (Langsung masuk ke huruf Lam atau Ra tanpa menahan dengung)",
      penjelasan = "Nun sukun atau tanwin langsung dileburkan sempurna ke huruf Lam atau Ra secara tegas."
    ),
    TajwidCard(
      id = "t4",
      hukum = "Iqlab",
      arti = "Mengganti bunyi Nun menjadi Mim samar",
      huruf = "ب  (Ba)",
      contohArab = "مِنْ بَعْدِ  /  أَنْبِئْهُمْ",
      caraBaca = "Mim ba'di / Ambi'hum (Bunyi nun berubah menjadi huruf mim lembut dengan dengung)",
      penjelasan = "Bila Nun mati atau tanwin bertemu huruf Ba, bunyinya ditukar menjadi 'm' samar dan dengung."
    ),
    TajwidCard(
      id = "t5",
      hukum = "Ikhfa Haqiqi",
      arti = "Samar-samar antara Idzhar & Idgham",
      huruf = "ت ث ج د ذ ز س ش ص ض ط ظ ف ق ك  (15 huruf)",
      contohArab = "مِنْ قَبْلُ  /  أَنْزَلْنَا",
      caraBaca = "Ming qablu / Angzalnaa (Samar antara 'n' dan 'ng' sesuai makhraj huruf berikutnya)",
      penjelasan = "Membaca nun sukun atau tanwin dengan samar dan menahannya dengan dengung sekitar 2 harakat."
    )
  )

  val quizList: List<QuizQuestion> = listOf(
    QuizQuestion(
      id = "q1",
      question = "Berapakah jumlah Rukun Islam yang menjadi pondasi utama seorang muslim?",
      options = listOf("3 Rukun", "4 Rukun", "5 Rukun", "6 Rukun"),
      correctIndex = 2,
      explanation = "Rukun Islam ada 5: Syahadat, Salat, Zakat, Puasa Ramadhan, dan Haji bagi yang mampu.",
      category = "Fiqih Ibadah",
      xpReward = 25
    ),
    QuizQuestion(
      id = "q2",
      question = "Hukum bacaan apakah yang terjadi jika huruf Nun Mati (نْ) bertemu huruf Ra (ر) dalam kalimat 'مِنْ رَبِّهِمْ'?",
      options = listOf("Idzhar Halqi", "Idgham Bighunnah", "Idgham Bilaghunnah", "Ikhfa Haqiqi"),
      correctIndex = 2,
      explanation = "Pertemuan Nun Sukun dengan huruf Ra (ر) dibaca lebur tanpa dengung, disebut Idgham Bilaghunnah.",
      category = "Tajwid Al-Qur'an",
      xpReward = 30
    ),
    QuizQuestion(
      id = "q3",
      question = "Nabi manakah yang mendapat julukan 'Khotamul Anbiya' wal Mursalin' (Penutup Para Nabi)?",
      options = listOf("Nabi Adam AS", "Nabi Musa AS", "Nabi Isa AS", "Nabi Muhammad SAW"),
      correctIndex = 3,
      explanation = "Nabi Muhammad SAW adalah nabi dan rasul terakhir yang diutus Allah SWT untuk seluruh alam semesta.",
      category = "Sirah Nabawiyah",
      xpReward = 25
    ),
    QuizQuestion(
      id = "q4",
      question = "Manakah urutan wudhu yang benar setelah membasuh wajah?",
      options = listOf("Membasuh kedua kaki", "Membasuh kedua tangan hingga siku", "Mengusap telinga", "Berkumur-kumur"),
      correctIndex = 1,
      explanation = "Sesuai rukun wudhu: setelah membasuh wajah adalah membasuh kedua tangan sampai ke siku.",
      category = "Fiqih Bersuci",
      xpReward = 25
    ),
    QuizQuestion(
      id = "q5",
      question = "Sikap berkata apa adanya dan tidak berbohong dalam ajaran Islam dinamakan perilaku...",
      options = listOf("Amanah", "Shiddiq (Jujur)", "Fathonah", "Tabligh"),
      correctIndex = 1,
      explanation = "Shiddiq berarti jujur, yaitu keselarasan antara perkataan, niat di dalam hati, dan perbuatan nyata.",
      category = "Akhlak Terpuji",
      xpReward = 25
    )
  )

  val initialAmalanList: List<DailyAmalan> = listOf(
    DailyAmalan("a1", "Salat Subuh Berjamaah", "Menegakkan salat subuh tepat waktu dengan penuh kesegaran.", true, 20, "Salat Wajib", "🌅"),
    DailyAmalan("a2", "Salat Dzuhur & Mengucap Syukur", "Istirahat sejenak di tengah aktivitas untuk mengingat Allah.", true, 20, "Salat Wajib", "☀️"),
    DailyAmalan("a3", "Salat Ashar", "Menjaga salat wustha di waktu sore hari.", true, 20, "Salat Wajib", "🌤️"),
    DailyAmalan("a4", "Salat Maghrib & Doa Senja", "Menyambut malam dengan bersimpuh memohon ampunan.", false, 20, "Salat Wajib", "🌇"),
    DailyAmalan("a5", "Salat Isya & Witir", "Menutup malam dengan ibadah tenang sebelum beristirahat.", false, 20, "Salat Wajib", "🌙"),
    DailyAmalan("a6", "Salat Sunnah Dhuha", "2 rakaat di pagi hari sebagai sedekah persendian tubuh.", true, 15, "Sunnah", "✨"),
    DailyAmalan("a7", "Tadarus Al-Qur'an (1 Ruku / Lembar)", "Membaca firman Allah dengan tartil dan penghayatan makna.", false, 25, "Al-Qur'an", "📖"),
    DailyAmalan("a8", "Tersenyum & Berbuat Kebaikan", "'Senyummu di hadapan saudaramu adalah sedekah' (HR. Tirmidzi)", true, 15, "Akhlak", "😊"),
    DailyAmalan("a9", "Membantu & Mendoakan Orang Tua", "Meringankan pekerjaan ayah bunda di rumah dengan ikhlas.", false, 20, "Birrul Walidain", "💖")
  )

  val surahs: List<SurahAudio> = listOf(
    SurahAudio(
      id = "s_fatihah",
      title = "Surah Al-Fatihah",
      arabicTitle = "الفاتحة",
      meaning = "Pembukaan",
      versesCount = 7,
      category = "Ummul Kitab",
      verses = listOf(
        VerseItem(1, "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ", "Bismillaahir-rohmaanir-rohiim", "Dengan nama Allah Yang Maha Pengasih, Maha Penyayang."),
        VerseItem(2, "الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ", "Al-hamdu lillaahi robbil-'aalamiin", "Segala puji bagi Allah, Tuhan semesta alam."),
        VerseItem(3, "الرَّحْمَٰنِ الرَّحِيمِ", "Ar-rohmaanir-rohiim", "Yang Maha Pengasih, Maha Penyayang."),
        VerseItem(4, "مَالِكِ يَوْمِ الدِّينِ", "Maaliki yaumid-diin", "Pemilik hari pembalasan."),
        VerseItem(5, "إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ", "Iyyaaka na'budu wa iyyaaka nasta'iin", "Hanya kepada Engkaulah kami menyembah dan hanya kepada Engkaulah kami memohon pertolongan."),
        VerseItem(6, "اهْدِنَا الصِّرَاطَ الْمُسْتَقِيمَ", "Ihdinash-shiroothol-mustaqiim", "Tunjukilah kami jalan yang lurus,"),
        VerseItem(7, "صِرَاطَ الَّذِينَ أَنْعَمْتَ عَلَيْهِمْ غَيْرِ الْمَغْضُوبِ عَلَيْهِمْ وَلَا الضَّالِّينَ", "Shirootholladziina an'amta 'alaihim ghoiril-maghdhuubi 'alaihim wa ladh-dhoolliin", "(yaitu) jalan orang-orang yang telah Engkau beri nikmat kepadanya; bukan (jalan) mereka yang dimurkai, dan bukan (pula jalan) mereka yang sesat.")
      )
    ),
    SurahAudio(
      id = "s_ikhlas",
      title = "Surah Al-Ikhlas",
      arabicTitle = "الإخلاص",
      meaning = "Kemurnian Keesaan Allah",
      versesCount = 4,
      category = "Juz 'Amma",
      verses = listOf(
        VerseItem(1, "قُلْ هُوَ اللَّهُ أَحَدٌ", "Qul huwallaahu ahad", "Katakanlah (Muhammad), 'Dialah Allah, Yang Maha Esa.'"),
        VerseItem(2, "اللَّهُ الصَّمَدُ", "Allaahush-shomad", "Allah tempat meminta segala sesuatu."),
        VerseItem(3, "لَمْ يَلِدْ وَلَمْ يُولَدْ", "Lam yalid wa lam yuulad", "(Allah) tidak beranak dan tidak pula diperanakkan,"),
        VerseItem(4, "وَلَمْ يَكُنْ لَهُ كُفُوًا أَحَدٌ", "Wa lam yakul lahuu kufuwan ahad", "Dan tidak ada sesuatu yang setara dengan Dia.")
      )
    ),
    SurahAudio(
      id = "s_falaq",
      title = "Surah Al-Falaq",
      arabicTitle = "الفلق",
      meaning = "Waktu Subuh",
      versesCount = 5,
      category = "Al-Mu'awwidzatain",
      verses = listOf(
        VerseItem(1, "قُلْ أَعُوذُ بِرَبِّ الْفَلَقِ", "Qul a'uudzu birobbil-falaq", "Katakanlah, 'Aku berlindung kepada Tuhan yang menguasai subuh (fajar),'"),
        VerseItem(2, "مِنْ شَرِّ مَا خَلَقَ", "Min syarri maa kholaq", "Dari kejahatan (makhluk yang) Dia ciptakan,"),
        VerseItem(3, "وَمِنْ شَرِّ غَاسِقٍ إِذَا وَقَبَ", "Wa min syarri ghoosiqin idzaa waqob", "Dan dari kejahatan malam apabila telah gelap gulita,"),
        VerseItem(4, "وَمِنْ شَرِّ النَّفَّاثَاتِ فِي الْعُقَدِ", "Wa min syarrin-naffaatsaati fil-'uqod", "Dan dari kejahatan (perempuan-perempuan) penyihir yang meniup pada buhul-buhul (talinya),"),
        VerseItem(5, "وَمِنْ شَرِّ حَاسِدٍ إِذَا حَسَدَ", "Wa min syarri haasidin idzaa hasad", "Dan dari kejahatan orang yang dengki apabila dia dengki.")
      )
    ),
    SurahAudio(
      id = "s_nas",
      title = "Surah An-Nas",
      arabicTitle = "الناس",
      meaning = "Manusia",
      versesCount = 6,
      category = "Al-Mu'awwidzatain",
      verses = listOf(
        VerseItem(1, "قُلْ أَعُوذُ بِرَبِّ النَّاسِ", "Qul a'uudzu birobbin-naas", "Katakanlah, 'Aku berlindung kepada Tuhannya manusia,'"),
        VerseItem(2, "مَلِكِ النَّاسِ", "Malikin-naas", "Raja manusia,"),
        VerseItem(3, "إِلَٰهِ النَّاسِ", "Ilaahin-naas", "Sembahan manusia,"),
        VerseItem(4, "مِنْ شَرِّ الْوَسْوَاسِ الْخَنَّاسِ", "Min syarril-waswaasil-khonnaas", "Dari kejahatan (bisikan) setan yang bersembunyi,"),
        VerseItem(5, "الَّذِي يُوَسْوِسُ فِي صُدُورِ النَّاسِ", "Alladzii yuwaswisu fii shuduurin-naas", "Yang membisikkan (kejahatan) ke dalam dada manusia,"),
        VerseItem(6, "مِنَ الْجِنَّةِ وَالنَّاسِ", "Minal-jinnati wan-naas", "Dari (golongan) jin dan manusia.")
      )
    )
  )

  fun getAiMentorResponse(userPrompt: String): String {
    val lower = userPrompt.lowercase()
    return when {
      lower.contains("wudhu") || lower.contains("wudu") ->
        "Assalamu'alaikum sahabat hebat! Wudhu adalah cara kita bersuci sebelum menghadap Allah SWT dalam salat. Tidak hanya membersihkan kotoran fisik, wudhu juga menggugurkan dosa-dosa kecil dari anggota tubuh kita yang dibasuh lho! Urutannya dimulai dari niat, membasuh tangan, berkumur, membasuh hidung, membasuh muka, kedua tangan sampai siku, mengusap kepala, telinga, hingga membasuh kaki. Mau coba mini game susun urutan wudhu di aplikasi ini?"

      lower.contains("tajwid") || lower.contains("idgham") || lower.contains("nun mati") ->
        "Belajar tajwid itu sangat berkah! Tajwid artinya memperindah bacaan Al-Qur'an sesuai hak setiap huruf. Untuk hukum Nun Sukun & Tanwin ada 5: Idzhar (jelas), Idgham Bighunnah (lebur berdengung), Idgham Bilaghunnah (lebur tanpa dengung), Iqlab (tukar bunyi jadi mim), dan Ikhfa (samar-samar). Kamu bisa cek tab Flashcard Tajwid untuk latihan interaktifnya ya!"

      lower.contains("doa orang tua") || lower.contains("ayah") || lower.contains("ibu") || lower.contains("orang tua") ->
        "Masya Allah, anak berbakti! Doa untuk kedua orang tua adalah:\n\n'Rabbighfir lii wa liwaalidayya warhamhumaa kamaa robbayaanii shoghiiroo'\n\nArtinya: 'Wahai Tuhanku, ampunilah aku dan kedua orang tuaku, serta sayangilah mereka berdua sebagaimana mereka telah mendidikku di waktu kecil.' Yuk baca setiap selesai salat!"

      lower.contains("nuh") || lower.contains("kapal") || lower.contains("bahtera") ->
        "Kisah Nabi Nuh AS mengajarkan kita arti pantang menyerah dan istiqomah. Beliau berdakwah selama 950 tahun! Saat membuat kapal besar di atas bukit, kaumnya menertawakan beliau, tapi Nabi Nuh tetap yakin dengan janji Allah. Dan akhirnya Allah menyelamatkan orang-orang beriman dari banjir besar."

      lower.contains("sholat") || lower.contains("salat") ->
        "Salat adalah tiang agama dan amalan yang pertama kali dihisab di hari kiamat. Salat lima waktu (Subuh, Dzuhur, Ashar, Maghrib, Isya) adalah perjumpaan terindah kita dengan Allah. Di fitur 'Amalan Harian', kamu bisa mencentang ibadahmu setiap hari untuk mengumpulkan streak dan poin XP lho!"

      lower.contains("sedekah") || lower.contains("senyum") ->
        "Sedekah itu tidak harus selalu dengan uang berlimpah lho! Rasulullah SAW bersabda: 'Tabassumuka fii wajhi akhiika laka shodaqoh' (Senyummu di hadapan saudaramu adalah sedekah). Berbicara sopan, membuang duri di jalan, dan membantu orang tua juga bernilai sedekah besar!"

      else ->
        "Pertanyaan yang sangat bagus, sahabat! Dalam ajaran Islam, menuntut ilmu adalah salah satu ibadah yang paling mulia ('Tholabul 'ilmi fariidhotun 'alaa kulli muslim'). Apakah ada topik tertentu yang ingin kamu pelajari hari ini? Kamu bisa bertanya tentang rukun iman, rukun islam, kisah para nabi, hukum tajwid, atau tata cara ibadah harian!"
    }
  }
}
