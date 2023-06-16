package com.example.healthyliving.utils

import com.example.healthyliving.remote.response.*


object DataDummy {
    fun generateDummyArticleEntity(): List<ArtikelItem> {
        val articleList = ArrayList<ArtikelItem>()
        for (i in 0..5) {
            val article = ArtikelItem(
                "Title $i",
                "https://www.halodoc.com/artikel/4-cara-mudah-memulai-hidup-vegetarian",
                "2023-06-13T03:07:46.000000Z",
                "This is name",
                "2023-06-03T09:09:52.000000Z",
                "This is description",
                "This is title",
                "http://34.101.218.239/images/4-cara-mudah-memulai-hidup-vegetarian-20230605142907.png"
            )
            articleList.add(article)
        }
        return articleList
    }

    fun generateDummyRecipeEntity(): List<ResepItem> {
        val recipeList = ArrayList<ResepItem>()
        for (i in 0..10) {
            val recipe = ResepItem(
                "Title $i",
                "https://cookpad.com/id/cari/indomie",
                "This is name",
                generateDummyCaraMembuats(),
                "This is description",
                "This is title",
                "http://34.101.218.239/images/indomie-20230603085343.jpeg",
                generateDummyBahans()
            )
            recipeList.add(recipe)
        }
        return recipeList
    }

    private fun generateDummyCaraMembuats(): List<CaraMembuatsItem> {
        val caraMembuatsList = ArrayList<CaraMembuatsItem>()
        for (i in 0..2) {
            val caraMembuat = CaraMembuatsItem(
                i,
                "Cara Membuat $i"
            )
            caraMembuatsList.add(caraMembuat)
        }
        return caraMembuatsList
    }

    private fun generateDummyBahans(): List<BahansItem> {
        val bahansList = ArrayList<BahansItem>()
        for (i in 0..2) {
            val bahan = BahansItem(
                i,
                "Bahan $i"
            )
            bahansList.add(bahan)
        }
        return bahansList
    }

    fun generateDummyRequestLogin(): RequestLogin {
        return RequestLogin("123@gmail.com", "123456")
    }

    fun generateDummyResponseLogin(): LoginResponse {
        val newLoginResult = ResultLogin("lkjhgf","test-token")
        return LoginResponse(newLoginResult, false, "Login Successfully")
    }

    fun generateDummyRequestSignUp(): RequestSignup {
        return RequestSignup("Helen Dj", "testing@test.com", "01928374")
    }
}