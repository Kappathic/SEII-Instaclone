package de.cschilling.delaygrambackend

import de.cschilling.delaygrambackend.model.Post
import de.cschilling.delaygrambackend.model.User
import de.cschilling.delaygrambackend.repository.PostRepository
import de.cschilling.delaygrambackend.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*
import java.util.Base64

@SpringBootApplication
class DelaygramBackendApplication(
    val userRepository: UserRepository,
    val postRepository: PostRepository
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        val picture = "iVBORw0KGgoAAAANSUhEUgAAAN4AAACkAQMAAADCEAJTAAAAAXNSR0IB2cksfwAAAAlwSFlzAAALEwAACxMBAJqcGAAAAAZQTFRFbj8rzqSBCD6hnAAAA4ZJREFUeJzF101r1EAYB/AnTdkUWppVD7ZYmoIXwUPrScRDDn4QBf0C0oOIxQQK9iKIJ71tb/0IHhtZwWOPPTa1wl6EpvZgxDTjJJvJzPPMi28HB3YP+2Nm8vwzmdkA/M+25ULmsJBlwFhuxoB9Zk0zos+mzdj3YYeVCaMOjeMKM43r9VjqOO6xNvSMmXXSWTknD4O0wGdH1knf+yyxTzoo9/txU0xzcOmxnFSv9LpEMmkmg9fjLZpbJhsyH2AYKogqDVJ4qSK6ooD0LNC4DyBdTSRWISk1Ysw2MMBdhMrAs/yziVCWurTIv74hlOGvLfCvxIJtO0CIQ/JGGFEt/j5GlKBfu/AitqcQVLgnindQFwjxavgY2tHbjhCipeK9wnPiiHyMOKJFgiiixcSRwhXSE6Vwy4V0ThTRAp9zZEvhDsOIUljhCakJohTmht4PK/IWR9YU+KKPHYhvWuZClAJ4Ow5c3rZHBMvvHTgEhHSXS1Sk+/nvI80PzUk3ZRd2h4AoNzVgLQbIFBHDlhDrmHWLPhf7VS7RL2C+myrU8vOrbsu9txFqKXh1+qT9bf5toEckyru2FOgRCbzc7+g61msulM+xgkmP9p5saMD+6DX1nJEYaqUMBKYi29ow7K64KybMlKI0LHmZpzZs2h+h/w84ceHYhTHBSsVI4gHFrRDjhbr6NhQ8ahA92gS/u/C0sOCIf/ZyFSMV99leZsY6Zudf/dSKrAhcGKomls4U38GqBauY7cCKHd/AEsFxN20ZsxewhrESy4Fjyv8GqS0pxKNWxHW79aiYiQhzjrPIvCQVy8yAdSpWb4OktT8kZvRqUexZvk63ab/Hcxs2MeRZRI8GiSwNKXqVxKig+EXgIegoe0KU/zVm9Gp7fA4xRagdKOusDcNOBFaGnpsOnHmaCfTjlGLFcaW5K6U/otjWeaPFQMfz5rtdmcGRNmw+7BZYEZxSHDxLOyzDI6B4ONvt/YWOMx+gxwOK3iPoVrwB4RN0j2EZTegFwWvobmcRTTKKwx5jDb2bCubapLzdnuIJxfZpvd8hXZptW28xOS60bqIUG063BHZmeH0Vc5JdGmFufPEFuNrgseVtO3Bhe0Vj4yszTHeTEzMO2/wmtp5N8s1JZqBd018P2fG4w9SAcREbMZ5Wsm9FeXBnOiZWzPh+bMXchYX6L4MgPxyUt/ScYOnAiKln76/xJznoMo51BH8XAAAAAElFTkSuQmCC"
        val pic2 = "iVBORw0KGgoAAAANSUhEUgAAAMgAAAC9AQMAAADMeaCvAAAAAXNSR0IB2cksfwAAAAlwSFlzAAALEwAACxMBAJqcGAAAAAZQTFRFxbO5QTE0Re5R7AAABBdJREFUeJyd182O1TYUAGBnPG1aTalnVYo6EFZTpKoCaSqVBdPcHUtegUeo1AWthHBQH6LL8halUqUx4gHgDSZ00e0NZTEpk4mb+NjHPv4RiEh37tx8Oc7xsWPfy7TW04bZ42vtD7a8ZvZuuRfJVBQfIyLRRdmifBHJ687JpUh69m75NJIB5av3FoXSFO/TFuU4krEY4+9zM5L7pbqddyWZfAZXqfjhSfoTyJZKV5K3PuaYip8g7FtdirlCxReH/ULFd4cdFWMiGYoS5PaNLFXnJpWgOoeR+OocFrM+PCllUFMJ7lNHWfv7RM/pGSvJeL/YGpzdWV6XT3M1WIXTGJtbtbw+ovK2KD41xukMcd3pEvldeaGtzUHMg1KtIwnmQfU9ERXIH6UY9lvpPuxXIj+bksGxF7XGMUxE0mCF7hFRTGKFTqKKSpdFFdW60vOtrHTcj0QUI7AONGZeZ4zyMRdBaw2RKcha+grRmLXxPpvBWsV8bs81zpIdSWQ9XNapuPuk4p671p4+RdHZDGxz+2Fr5xJlBGnsxzc+ZgZxo33mRXdGgscRpf8kGgaUf6PkZv8v6dC89BDlaTgVh3bwAosmj+Qsloul6xgz0RSU/W9sQQ5sfWTvrhneOIHkZOfk1QgP8lVXBYx5NkkTcwwyNsqJmlojR1Cfod446abWtLYHJ3qfJZsuG/kcBk95qf46MvIdDF4nRoy5DbIPg9ftDSi3jtzyw90KYTNgP+GCLu10cfIAt5SDO2EFX7Jg69o4ebF2moebmpXBdFuEGwcca/MPhdaprCfucK3T1tYTuyInpkR1KhzeAumoBK1tiFxbM4BiVS6mMn8b+r0rEPE4FZvAq6L8VxL+RNNl28cMMty9w5ixKL0km1ogU5sOAsj8Qz41Vo03/lT5GK2+TK6Gv3NysRsFvUmkhrdHaYyVh10iAt7uptLC28dpa216Co4dWRIe1XHXpxbJZ8WYcmv+qD5ArmXkhkmtTaWCYT/JiDklZE46Zh6o9D5mocpI3RnhqQglSzKYqZ+T10YupdLAgySyMtnWetKZVUbTK83ITOfKLU1LDciiUv1tZdnAGX1u1vXRTPI2EtgaIRNGHoMaYxYhT67AGKFZ+JUvkv69pLHLupmjGVFWhmJMKC3GiKwMGZFGRitjRqpOnDOyFGkXs1k2Oj3txrK039eK/gp1Uo3wIZYe9pG8TPDheSoH8GEbyRY/zJGoovxYlKEryZiJgbpdFGX2Dew7abFDVm47gdHWT1KBWTVjbn7wQIJvWCh1nAEKfEf7JyMM504iJrmnORFujtpu+ynHMYHKXNWjVNhCZa7y4n8Wc3NTFYq9aQ3LhW/tdL6Owtrg8ebSTeZ1Z+LBnOfbMZDwENJl00TSSJdNvGc1uHC0/wOMtbYadc9ROAAAAABJRU5ErkJggg=="


        val admin = User(
            "admin",
            "\$2y\$10\$mt1Ev5vlAx2/RZrlFicF1uQNJk3SCGiCYLn.exBGEHL09hwWJfUNi",
            "admin",
            "Das ist das Profil vom Admin",
            "admin@example.com",
            null,
            mutableListOf(),
            0,
            mutableSetOf()
        )
        userRepository.save(admin)
        val post1 = Post("Das ist der erste Admin Post",
            null,Date(),
            mutableListOf(),
            mutableSetOf(),
            setOf("test1","test2"),admin)

        postRepository.save(post1)
        val user1 = userRepository.save(
            User(
                "user",
                "\$2y\$10\$mt1Ev5vlAx2/RZrlFicF1uQNJk3SCGiCYLn.exBGEHL09hwWJfUNi",
                "user",
                "Das ist das Profil vom User",
                "user@example.com",
                null,
                mutableListOf(),
                0,
                mutableSetOf(admin)
            )
        )

        postRepository.save(Post("Das ist der erste User Post",
            Base64.getDecoder().decode(picture),Date(),
            mutableListOf(),
            mutableSetOf(),
            setOf("test1","test2"),user1))
        postRepository.save(Post("Das ist der zweite User Post",
            Base64.getDecoder().decode(pic2),Date(),
            mutableListOf(),
            mutableSetOf(),
            setOf("test1","test2"),user1))
        postRepository.save(Post("Das ist der dritte User Post",
            Base64.getDecoder().decode(picture),Date(),
            mutableListOf(),
            mutableSetOf(),
            setOf("test1","test2"),user1))

        val user2 = User(
            "user2",
            "\$2y\$10\$mt1Ev5vlAx2/RZrlFicF1uQNJk3SCGiCYLn.exBGEHL09hwWJfUNi",
            "user",
            "Das ist das Profil vom User2",
            "user2@example.com",
            null,
            mutableListOf(),0,
            mutableSetOf(admin)
        )
        userRepository.save(user2)

    }

}

fun main(args: Array<String>) {
    runApplication<DelaygramBackendApplication>(*args)
}
