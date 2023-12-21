package com.example.tanahku.data.local

import com.example.tanahku.R

object SoilList {
    val soils = listOf(
        SoilEntity(
            type = "Tanah Kapur",
            englishName = "Chalk",
            desc = "Tanah berkapur biasanya ringan dan mudah dikerjakan di musim apa pun. Pada saat yang sama, tanah ini mengandung inklusi berbatu. Dapat dikeringkan dengan baik. Namun demikian, drainase yang cepat, khususnya pada cuaca panas, bisa menyebabkan tanah mengering. Masalah dengan tanah berkapur adalah tanah ini terlalu basa dan kekurangan mineral tertentu, seperti besi dan mangan. Tanaman yang tumbuh di tanah berkapur sering kali kerdil dan daunnya berwarna kekuningan. Oleh karena itu, Anda harus menggunakan jenis pupuk yang tepat untuk menyeimbangkan pH untuk menumbuhkan tanaman yang sehat.",
            image = R.drawable.chalk
        ),
        SoilEntity(
            type = "Tanah Liat",
            englishName = "Clay",
            desc = "Tanah liat merupakan tanah yang terdiri dari partikel-partikel halus, dan ini membuatnya sangat baik dalam menahan air sehingga dapat menjadi media yang baik untuk pertumbuhan tanaman tertentu. Sifat-sifat tanah liat dapat bervariasi, namun pada umumnya tanah liat cenderung menjadi lengket dan sulit untuk diolah. Hal tersebut dapat menjadi tantangan bagi pertanian dan konstruksi tanah liat, karena tanah liat dapat dengan mudah menjadi lunak dan berlumpur ketika basah, dan keras serta retak-retak ketika kering.",
            image = R.drawable.clay
        ),
        SoilEntity(
            type = "Tanah Lempung",
            englishName = "Loam",
            desc = "Lempung terdiri dari tiga bahan yang berbeda: lumpur, tanah liat, dan pasir. Variasi ukuran partikel menciptakan celah di tanah yang memungkinkan udara, air, dan akar melewatinya dengan bebas. Lempung tidak terlalu cepat kering; lempung lembut dan hampir mudah diolah. Jenis tanah lempung mengandung semua unsur hara yang dibutuhkan untuk pertumbuhan tanaman yang aktif. Tanah lempung juga memiliki kadar kalsium dan pH yang tinggi, serta hummus. Mineral yang larut dalam air dengan mudah menyehatkan akar tanaman, sehingga menghasilkan panen yang tinggi.",
            image = R.drawable.loam
        ),
        SoilEntity(
            type = "Tanah Gambut",
            englishName = "Peat",
            desc = "Tanah gambut atau peat soil adalah jenis tanah yang terbentuk secara utama dari bahan organik yang terdekomposisi. Struktur tanah gambut bersifat empuk dan tahan terhadap pemadatan, sehingga tanah ini dapat dengan cepat menghangat dan mempertahankan air dengan baik. Keberadaan udara yang cukup dan kemampuan tanah ini untuk memungkinkan akar tanaman bernapas membuatnya menjadi pilihan yang baik untuk penanaman benih. Salah satu ciri khas tanah gambut adalah ketiadaan patogen, sehingga tanah jenis ini cocok untuk memulai penanaman benih. Namun, tanah gambut bersifat asam, membatasi ketersediaan nutrisi tanaman, sehingga perlu dilakukan suplementasi dengan pemupukan.\n" +
                    "\n" +
                    "Kelemahan utama dari tanah gambut adalah sifatnya yang merupakan sumber daya alam yang tidak dapat diperbaharui. Penurunan jumlah tanah gambut dapat berkontribusi terhadap perubahan iklim dengan melepaskan gas rumah kaca ke atmosfer. Tanah gambut umumnya berwarna hitam atau coklat gelap, memiliki tekstur lembut dan empuk, dan tidak dapat membentuk bongkahan meskipun digulung. Kandungan air yang tinggi membuatnya dapat melepaskan air dengan mudah saat diperas. Sifat retensi air yang baik sangat membantu pada musim kemarau ketika tanaman memerlukan penyiraman ekstra.\n" +
                    "\n" +
                    "Meskipun tanah gambut memiliki kandungan organik yang tinggi, namun keterbatasan nutrisi dan kemungkinan terjadinya genangan air perlu diperhatikan. Tanah gambut juga dapat menjadi bahaya kebakaran pada musim panas karena sifatnya yang mudah terbakar. Oleh karena itu, manfaat dan tantangan tanah gambut perlu seimbang untuk mendukung pertanian yang berhasil dan penggunaan lahan yang berkelanjutan.",
            image = R.drawable.peat

        ),
        SoilEntity(
            type = "Tanah Pasir",
            englishName = "Sand",
            desc = "Tanah berpasir (sandy soil) dalah jenis tanah yang memiliki karakteristik khusus dan keunikan dalam sifat fisik dan kimianya. Salah satu keunggulan utama tanah berpasir adalah kemampuannya yang cocok untuk penanaman awal setelah musim dingin karena cepat menghangat. Struktur tanah ini tergolong longgar karena partikel pasir yang cukup besar, membuatnya mudah untuk digarap tanpa memerlukan usaha yang berat.\n" +
                    "\n" +
                    "Namun, tanah berpasir juga memiliki beberapa kelemahan. Kandungan pasir yang tinggi membuat tanah ini rentan terhadap erosi oleh angin dan air. Struktur longgar tanah berpasir juga menyebabkan air dengan cepat meresap ke lapisan bawah tanah, membawa bersamanya nutrisi yang diperlukan oleh tanaman. Selain itu, tanah berpasir cenderung bersifat asam dengan tingkat pH rendah, menyebabkan tanaman yang tumbuh di dalamnya mengalami kekurangan nutrisi dan kelembaban yang diperlukan untuk pertumbuhan optimal.\n" +
                    "\n" +
                    "Tanah berpasir dikenal sebagai \"tanah lapar\" karena membutuhkan penyiraman yang lebih sering dan memiliki kecepatan pengeringan yang tinggi. Meskipun memiliki kelemahan tersebut, tanah berpasir memiliki kelebihan, seperti kemudahan dalam pengolahan dan pemanasan yang cepat selama musim semi. Identifikasi tanah berpasir dapat dilakukan dengan menguji teksturnya; jika setelah menambahkan air, tanah terasa kasar dan serpihannya jatuh melalui jari, maka itu adalah tanah berpasir.\n",
                    image = R.drawable.sand
        ),
        SoilEntity(
            type = "Tanah Lumpur",
            englishName = "Silt",
            desc = "Tanah lumpur merupakan tanah yang memiliki partikel berukuran kecil yang mana tidak melebihi 0,005 cm, lebih kecil dari tanah berpasir namun lebih besar dari tanah liat. Tanah berlumpur dapat ditemukan terutama di tepi sungai, dasar sungai, ataupun di area yang pernah mengalami banjir. Tanah lumpur juga cukup baik dalam menahan air sehingga bagus untuk lahan pertanian dan memiliki tingkat kesuburan tanah yang bagus.",
            image = R.drawable.silt
        ),
    )
}