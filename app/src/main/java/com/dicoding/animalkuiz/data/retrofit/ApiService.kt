import com.dicoding.animalkuiz.data.response.GetAllAnimal
import com.dicoding.animalkuiz.data.response.GetAllAnimalTypeByIDResponse
import com.dicoding.animalkuiz.data.response.GetAllAnimalTypeResponse
import com.dicoding.animalkuiz.data.response.GetAllUserQuest
import com.dicoding.animalkuiz.data.response.GetAnimalById
import com.dicoding.animalkuiz.data.response.GetInventory
import com.dicoding.animalkuiz.data.response.GetQuiz
import com.dicoding.animalkuiz.data.response.LoginRequest
import com.dicoding.animalkuiz.data.response.LoginResponse
import com.dicoding.animalkuiz.data.response.MintaquestResponse
import com.dicoding.animalkuiz.data.response.RegisterResponse
import com.dicoding.animalkuiz.data.response.SetCooldown
import com.dicoding.animalkuiz.data.response.UserQuestAdvance
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("users/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("users")
    fun register(@Body request: LoginRequest): Call<RegisterResponse>

    @GET("animal-types")
    fun getAllAnimalType(): Call<GetAllAnimalTypeResponse>

    @GET("animal-types/{animal_type_id}")
    fun getAnimalTypeById(
        @Path("animal_type_id") id: String
    ): Call<GetAllAnimalTypeByIDResponse>

    @GET("animals")
    fun getAllAnimals(): Call<GetAllAnimal>

    @GET("animals/inventory")
    fun getAllAnimalsInventory(
        @Header("Authorization") token: String
    ): Call<GetInventory>

    @GET("animals/{animal_id}")
    fun getAnimalById(
        @Header("Authorization") token: String,
        @Path("animal_id") id: String
    ): Call<GetAnimalById>

    @GET("quests")
    fun getAllQuestofUser(): Call<GetAllUserQuest>

    @GET("quests/{animal_type_id}")
    fun getQuestForUserByAnimalId(
        @Path("animal_type_id") id: String,
        @Header("Authorization") token: String
    ): Call<MintaquestResponse>

    @PATCH("quests/{animal_type_id}")
    fun advanceQuestForUser(
        @Path("animal_type_id") id: String
    ): Call<UserQuestAdvance>

    @GET("quizzes/{animal_id}")
    fun getQuiz(
        @Path("animal_id") id: String,
        @Header("Authorization") token: String
    ): Call<GetQuiz>

    @PATCH("quizzes")
    fun setCooldown(): Call<SetCooldown>
}